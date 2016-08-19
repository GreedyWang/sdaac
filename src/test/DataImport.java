package test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.biz.EmpBiz;
import app.biz.PersonalPBiz;
import app.entity.TemployeeProduct;
import app.entity.Tempolyee;
import app.entity.Tpost;
import sdaac.wym.app.Hr.BounceFigureImpl;
import sdaac.wym.app.entity.hr.BounceFigure;

import common.util.ExcelReader;

/**
 * 数据导入测试
 * 
 * @author SA1KV5
 * 
 */
public class DataImport {

	private int type = 7;// ep's type
	private BounceFigureImpl bfBiz;
	// private BouncePostImpl bpBiz;
	private PersonalPBiz ppBiz;
	private EmpBiz empBiz;

	/**
	 * 102日产量数据导入
	 * 
	 * @throws Exception
	 * @throws IOException
	 */
	public void importDailyRecord102() throws IOException, Exception {
		List<BounceFigure> bfs = bfBiz.selectBounceFigure("102");
		boolean flag;
		List<TemployeeProduct> list = new ArrayList<TemployeeProduct>();
		String fileName = "C:/Documents and Settings/sa1kv5/桌面/HR_Date/102.xls";
		ExcelReader er = new ExcelReader(fileName);
		List<Object[]> rs = er.getList();
		for (Object[] item : rs) {
			flag = false;
			// System.out.println(item[0] instanceof String );
			for (BounceFigure bf : bfs) {
				if (bf.getRemark().equals(
						item[1].toString() + item[2].toString())) {
					flag = true;
				}
			}
			if (!flag) {
				BounceFigure aa = new BounceFigure();
				// aa.setFigureId(item[0].toString());
				aa.setFigureId(item[1].toString() + item[2].toString());
				aa.setRemark(item[1].toString() + item[2].toString());
				// aa.setStandWorkTime(standWorkTime);
				aa.setType("零件");
				aa.setProductArea("102");
				bfBiz.addBounceFigure(aa);
			}
			if (!item[0].toString().equals("")) {
				String uname = empBiz.doSelectById(item[0].toString());

				try {
					System.out.println("uname="+uname);
					if (uname != null && !uname.equals("") && !uname.equals("[]")) {
						TemployeeProduct ep = new TemployeeProduct();
						ep.setFigureId(item[1].toString() + item[2].toString());
						ep.setOutput(Float.parseFloat(item[4].toString()));
						ep.setWorktime(Float.parseFloat(item[3].toString()));
						ep.setTeamLeaderId(item[6].toString());
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
						try {
							ep.setDatatime(sdf.parse(item[5].toString()));
						} catch (ParseException e) {
							// TODO �Զ���ￄ1�7 catch ��
							e.printStackTrace();
						}

						ep.setRegisterTime(new Date());
						ep.setTempolyee(new Tempolyee(item[0].toString()));
						String post = item[1].toString()+ item[2].toString() + "_102_"
								+ item[2].toString();
						//System.out.println(post);
						ep.setTpost(new Tpost(post));
						ep.setType(type);
						// System.out.println(item[1].toString());
						list.add(ep);
					} else {
						System.out.println(item[0].toString());
					}

				} catch (Exception e) {
					// TODO: handle exception
					continue;
				}
			}
		}
		ppBiz.FlushInsert(list);
	}

	public void doTest() throws Exception, Exception {
		String fileName = "d:/102test.xls";
		ExcelReader er = new ExcelReader(fileName);
		List<Object[]> rs = er.getList();
		for (Object[] item : rs) {
			System.out.println(item[0] + "," + item[1] + "," + item[2] + ","
					+ item[3] + "," + item[4] + "," + item[5] + "," + item[6]
					+ ",");
		}
	}

	public static void main(String[] args) {
		// DataImport di = new DataImport();
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		DataImport di = (DataImport) ac.getBean("dataImportManager");

		try {
			di.importDailyRecord102();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// DataImport di = new DataImport();
		// try {
		// di.doTest();
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	public BounceFigureImpl getBfBiz() {
		return bfBiz;
	}

	public void setBfBiz(BounceFigureImpl bfBiz) {
		this.bfBiz = bfBiz;
	}

	public PersonalPBiz getPpBiz() {
		return ppBiz;
	}

	public void setPpBiz(PersonalPBiz ppBiz) {
		this.ppBiz = ppBiz;
	}

	public EmpBiz getEmpBiz() {
		return empBiz;
	}

	public void setEmpBiz(EmpBiz empBiz) {
		this.empBiz = empBiz;
	}

}
