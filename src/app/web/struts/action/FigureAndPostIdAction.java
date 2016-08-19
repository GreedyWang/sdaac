/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package app.web.struts.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import common.util.DownLoad;
import common.util.ExcelReader;
import common.util.FileUpload;
import common.util.MyUtil;

import sdaac.wym.app.entity.hr.BounceFigure;
import sdaac.wym.app.entity.hr.BouncePost;
import sdaac.wym.app.entity.hr.Inbound;
import app.common.ProductStoreExcel;
import app.common.web.ToJson;
import app.entity.TemployeeProduct;
import app.web.struts.form.FigureAndPostIdForm;

/**
 * MyEclipse Struts Creation date: 07-03-2009
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/figureAndPostId" name="figureAndPostIdForm"
 *                input="/form/figureAndPostId.jsp" parameter="actionType"
 *                scope="request" validate="true"
 */
public class FigureAndPostIdAction extends BaseAction {
	/*
	 * Generated Methods
	 */

	/**
	 * @see 增加图号
	 */
	public ActionForward addFigure(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		FigureAndPostIdForm figureAndPostIdForm = (FigureAndPostIdForm) form;// TODO
		// Auto-generated
		// method
		// stub
		BounceFigure bf = figureAndPostIdForm.getBf();
		this.getBfManager().addBounceFigure(bf);
		return mapping.findForward("FigureAdd");
	}

	/**
	 * @see增加工序
	 */
	public ActionForward addPostId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		FigureAndPostIdForm figureAndPostIdForm = (FigureAndPostIdForm) form;// TODO
		// Auto-generated
		// method
		// stub
		BouncePost bp = figureAndPostIdForm.getBp();
		this.getBfManager().addBouncePost(bp);
		return mapping.findForward("BouncePostManager");
	}

	/**
	 * @see查看工序
	 */
	public ActionForward selectBouncePost(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		FigureAndPostIdForm figureAndPostIdForm = (FigureAndPostIdForm) form;// TODO
		// Auto-generated
		// method
		// stub
		BouncePost bp = figureAndPostIdForm.getBp();
		List<BouncePost> rs = this.getBpManager().select(bp);
		request.setAttribute("BouncePosts", rs);
		return mapping.findForward("BouncePostUpdate");
	}

	/**
	 * 查看图号-图号绩效
	 * @throws UnsupportedEncodingException 
	 */
	public ActionForward showFigure(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		List<BounceFigure> rs = new ArrayList<BounceFigure>();
		// 得到参数
		boolean flag=Boolean.parseBoolean(request.getParameter("isDownLoad"));
		String date = request.getParameter("date");
		String type = request.getParameter("produceType"); //产品类型
		String proudctNo = request.getParameter("proudctNo");

		
		//type="All";
	//	name = java.net.URLDecoder.decode(name,"UTF-8"); 
		if(type!=null){
			type = URLDecoder.decode(type, "UTF-8");
			String area = request.getParameter("produceArea"); //生产区域
			BounceFigure pBf = new BounceFigure();
			pBf.setType(type);
			TemployeeProduct ep = new TemployeeProduct();
			if (area != null) {
				ep.setTypes(area);
			} 
	
			if (date == null || date.equals("")) {
				date = MyUtil.getMonth();
			}
			
			if(proudctNo!=null&&!proudctNo.equals("")){
				ep.setFigureId(proudctNo);
			}
			ep.setBeginDatatime(date + "-01");
			ep.setEndDatatime(date + "-15");
			Float[] pq = this.getPpBiz().selectFigureAllTime(ep,pBf,rs);// 添加实际工作时间
			// 附加计算
			Float allInputs = BounceFigure.getAllInputs(rs);// 总入库数
			Float allInputsP = BounceFigure.getAllInputsPercent(rs);// 总入库数*吃片数
		//	System.out.println(allInputs);
		//	System.out.println(pq[0]);
			//System.out.println(pq[1]);
			Map<String, BounceFigure> map = new HashMap<String, BounceFigure>();
			for (BounceFigure bf : rs) {
				// 把清洗等工作时间按照入库数比例添加到每个总成的实际工时
		//		System.out.print(bf.getFigureId()+"："+bf.getRealWorkTime()+","+bf.getPercent(allInputs) * pq[1]);
				bf.setRealWorkTime(bf.getRealWorkTime()
						+ bf.getPercentpices(allInputsP) * pq[0]
						+ bf.getPercent(allInputs) * pq[1]);
			//	System.out.println(bf.getFigureId()+"："+bf.getRealWorkTime());
				map.put(bf.getFigureId()+bf.getRealWorkTime(), bf);
			}
			List<BounceFigure> rs2 = new ArrayList<BounceFigure>();
			for(String key : map.keySet()){
				rs2.add(map.get(key));
			}
			if(flag)
			{
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-HH-mm");
				String now=sdf.format(new Date());
				String fileName=request.getRealPath("/")+"excelDown\\HR_"+now+".xls";	
				try {
					ProductStoreExcel pse=new ProductStoreExcel(fileName);
					pse.write(rs2, new String[]{date,ep.getTypesName(),type});
					DownLoad dl=new DownLoad();
					dl.dLoad(request, response, fileName);	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				ToJson.listToJsonArrart(rs2, response);
			}
		}
		return null;// mapping.findForward("FigureAccount");
	}

	/**
	 * 查看图号 图号管理
	 */
	public ActionForward selectFigure(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		FigureAndPostIdForm fpForm = (FigureAndPostIdForm) form;
		request.setAttribute("BounceFigures", this.getBfManager().selectByCond(
				fpForm.getBf()));
		return mapping.findForward("BounceFigureUpdate");
	}

	/**
	 * 批量图号
	 */
	public ActionForward updateFigure(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		FigureAndPostIdForm fpForm = (FigureAndPostIdForm) form;
		List<BounceFigure> bfs = fpForm.getBfs();
		this.getBfManager().updateBluk(bfs);
		return mapping.findForward("BounceFigureUpdate");
	}
	/**
	 * 查看图号入库数量
	 */
	public ActionForward selectInbounds(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String figureId=request.getParameter("figureId"); //得到图号		
		request.setAttribute("rs", this.getBiManager().selectByFigure(figureId));//查询入库数
		return mapping.findForward("FigureInbounds");
	}
	/**
	 * 通过excel批量修改图号
	 */
	public ActionForward updateFigureByExcel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		FigureAndPostIdForm fpForm = (FigureAndPostIdForm) form;
		String excelType = request.getParameter("excelType");
		FormFile file = fpForm.getFormFile();
		String filePath = request.getRealPath("/uploadFolder");// 上传到指定的upload包中
		String fileName = FileUpload.upLoad(file, filePath);
		try {
			ExcelReader excelReader = new ExcelReader(fileName);
			List<Object[]> results = excelReader.getList();
			List<BounceFigure> bfs = new ArrayList<BounceFigure>();
			if (excelType.equals("standworktime")) {
				for (Object[] oo : results) {
					BounceFigure bf = new BounceFigure();
					bf.setRemark(oo[0].toString());
					System.out.println(oo[0].toString()+"==="+oo[1].toString());
					bf.setStandWorkTime(Float.parseFloat(oo[1].toString()));
					bfs.add(bf);
				}
				this.getBfManager().updateBluk(bfs);
				return mapping.findForward("addExcel1");
			} else if (excelType.equals("inbounceNum")) {
				String inboundTime = request.getParameter("inboundTime"); // 入库日期
				List<Inbound> inbounds = new ArrayList<Inbound>();
				for (Object[] oo : results) {
					Inbound inbound = new Inbound();
					inbound.setInboundTime(inboundTime);
					inbound.setFigureId(oo[0].toString());
					if (oo[1].equals("")) {
						oo[1] = 0;
					}
					Float tmp=Float.parseFloat(oo[1].toString());
					inbound.setInbound(Long.parseLong(tmp.intValue()+""));
					inbounds.add(inbound);
				}
				this.getBfManager().updateInbound(inbounds);
				return mapping.findForward("addExcel2");
			} else if (excelType.equals("picesNum")) {
				for (Object[] oo : results) {
					BounceFigure bf = new BounceFigure();
					bf.setFigureId(oo[0].toString());
					bf.setPicesNum(Integer.parseInt(oo[1].toString()));
					bfs.add(bf);
				}
				this.getBfManager().updateBluk(bfs);
				return mapping.findForward("addExcel3");
			} else if (excelType.equals("remarks")) {
				for (Object[] oo : results) {
					BounceFigure bf = new BounceFigure();
					bf.setFigureId(oo[0].toString());
					bf.setRemark(oo[1].toString());
					bfs.add(bf);
				}
				this.getBfManager().updateBluk(bfs);
				return mapping.findForward("addExcel0");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}