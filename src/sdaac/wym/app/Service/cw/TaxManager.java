package sdaac.wym.app.Service.cw;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.entity.Logs;

import common.dao.CommonDAO;
import common.util.CwTxtWrite;

import sdaac.wym.app.entity.cw.CwLogs;
import sdaac.wym.app.entity.cw.Tax;
import sdaac.wym.app.entity.cw.Tax2;
import sdaac.wym.app.entity.cw.TaxItem;

public class TaxManager {
	
	protected CommonDAO<CwLogs> cwLogsDao;
	private TaxReader input;
	private Tax2Reader input2;
	public Date nowTime ;//当前时间
	protected List<Tax> taxs;
	protected List<List<String>> nos ;
	//日志记录附加数据，[0]=本币金额,[1]=分配号,[2]顾客号
	protected Map<String, String[]> map = new HashMap<String, String[]>();
	//public String customerNo; 
	public String uid;
	public List<CwLogs> cwlogs ;
	public TaxManager(){		
	}
	
	public List<Tax>  getContext(InputStream is)
	{
		nowTime = Calendar.getInstance().getTime();	
		input=new TaxReader(is);
		return  input.getRs();
	}
	
	public List<Tax>  getContext(InputStream is,String fileName)
	{
		nowTime = Calendar.getInstance().getTime();	
		input=new TaxReader(is);
		return  input.getRs();
	}
	
	public List<Tax2>  getContext2(InputStream is)
	{
		nowTime = Calendar.getInstance().getTime();	
		input2=new Tax2Reader(is);
		return  input2.getRs();
	}
	
	/**
	 * 得到第一行
	 */
	public String getFirstRow()
	{
		return input.getFirstRow();	
	}
	
	/**
	 * 得到第一行
	 */
	public String getFirstRow2()
	{
		return input2.getFirstRow();	
	}
	
	/**
	 * 二分法查找到要修改的条目
	 * @param no
	 */
	public int searchByNo(String no,List<Tax> taxs)
	{
//		int front =0;
		int rear=taxs.size();
//		while(rear>front)
//		{
////			System.out.println("!!rear="+rear+"front="+front);
//			int middle=(rear-front)/2+front;
//			//大于半数
//			if(Integer.parseInt(no)<Integer.parseInt(taxs.get(middle).getNo()))
//			{
//				rear=middle;
//			}else if(Integer.parseInt(no)==Integer.parseInt(taxs.get(middle).getNo())){
//				front=middle;
//				break;
//				
//			}else {
//				front=middle;
//			}
//		}
		//return front;
		/**
		 * Debug
		 * 2分发死循环
		 * changed by : wym
		 * time : 2013-05-17
		 */
		for(int i = 0;i<rear;i++){
			if(no.equals(taxs.get(i).getNo())){
				return i;
			}
		}
		return 999;//0未找到
		
	}
	/**
	 * 删除
	 * @param no
	 * @param taxs
	 */
	public void remove(String no,List<Tax> taxs)
	{
		int index=this.searchByNo(no, taxs);
		taxs.remove(index);
	}
	/**
	 * 批删除
	 * @param nos
	 * @param taxs
	 */
	public void removeBulk(String[] nos,List<Tax> taxs) {
		for(int i=0;i<nos.length;i++)
		{
			remove(nos[i],taxs);
		}
	}
	/**
	 * 得到指定tax
	 * @param no
	 * @param taxs
	 * @return
	 */
	public Tax getTaxByNo(String no,List<Tax> taxs) {
		return taxs.get(this.searchByNo(no, taxs));
	}
	
	public void go(InputStream is,List<Tax> pTaxs){
		
	}
	
	public void go(InputStream is,List<Tax> pTaxs,String fileName){
		
	}
	
	/**
	 * 合并多个发票到同一个发票；
	 * @param args
	 */
	public void MergeComplex(String[] nos,List<Tax> taxs)
	{
		List<TaxItem> taxitems=new ArrayList<TaxItem>();
		int index=searchByNo(nos[0], taxs);//得到合并的第一项
		for(int i=0;i<nos.length;i++)//得到合并的所有物品
		{	
			taxitems.addAll(getTaxByNo(nos[i],taxs).getTaxItems());			
		}
		//物品数量处理
		for(int i=0;i<taxitems.size()-1;i++)
		{
			//System.out.println("i="+i);
			for(int j=i+1;j<taxitems.size();j++)
			{
				if(taxitems.get(i).getProduceName().equals(taxitems.get(j).getProduceName())&&
						taxitems.get(i).getStandardType().equals(taxitems.get(j).getStandardType()))
				{
					//如果是同一物品
					//System.out.println(taxitems.get(i).getStandardType()+"=="+taxitems.get(j).getStandardType());
					taxitems.get(i).add(taxitems.get(j));//数量相加
					taxitems.remove(j);
					j--;
				}
			}
		}
		taxs.get(index).setTaxItems(taxitems);
		//删除除了第一项的信息
		for(int i=1;i<nos.length;i++)
		{
			remove(nos[i],taxs);
		}
	}
	
	/**
	 * 合并多个发票到同一个发票；
	 * @param args
	 */
	public void Merge(String[] nos,List<Tax> taxs)
	{
		List<TaxItem> taxitems=new ArrayList<TaxItem>();
		int index=searchByNo(nos[0], taxs);//得到合并的第一项
		for(int i=0;i<nos.length;i++)//得到合并的所有物品
		{	
			taxitems.addAll(getTaxByNo(nos[i],taxs).getTaxItems());			
		}
		taxs.get(index).setTaxItems(taxitems);
		//删除除了第一项的信息
		if(nos.length>1){
			for(int i=1;i<nos.length;i++)
			{
				remove(nos[i],taxs);
			}
		}
	}
	
	
	/**
	 *  拆分一个发票到几个发票；
	 */
	public void Split()
	{
		
	}
	private void setRemark(Tax tax,Tax item)
	{
		if(tax.getSeller().getRemarks()!=null&&!"".equals(tax.getSeller().getRemarks()))
		{
			item.getSeller().setRemarks(tax.getSeller().getRemarks());
		}
	}
	/**
	 * 按条件搜索
	 * @param tax
	 * @param taxs
	 * @return
	 */
	public List<Tax>  searchByCondititon(Tax tax, List<Tax> taxs) {
		// TODO Auto-generated method stub
		//无任何搜索条件，全部显示
		if((tax.getNo()==null||"".equals(tax.getNo()))&&(tax.getBuyer().getName()==null||
				"".equals(tax.getBuyer().getName())))
		{
			return taxs;
		}
		else{
			List<Tax> rs=new ArrayList<Tax>();
			for(Tax item:taxs)
			{
				if(tax.getNo()!=null&&!"".equals(tax.getNo()))
				{
					if(item.getNo().equals(tax.getNo().trim()))
					{
						setRemark(tax,item);
						rs.add(item);
					}
				}
				if(tax.getBuyer().getName()!=null&&!"".equals(tax.getBuyer().getName()))
				{
					if(item.getBuyer().getName().equals(tax.getBuyer().getName().trim()))
					{
						setRemark(tax,item);
						rs.add(item);
					}
				}
			}
			return rs;
		}
	}
	/**
	 * 清空备注
	 * @param taxs
	 * @return
	 */
	public List<Tax> updateRemarktoBlank(List<Tax> taxs)
	{
		for(Tax item:taxs)
		{
			item.getSeller().setRemarks(" ");
		}
		return taxs;
	}
	
	
	public void writeToFileOld(Tax2 pTax2, String fileName,List<Tax> taxs,String fristRow)
	{
		CwTxtWrite cw=new CwTxtWrite(pTax2,fileName,taxs,fristRow);
		cw.writeOld();		
	}
	
	public void writeToFile(List<Tax2> pTax2s, String fileName,String fristRow,String tip)
	{
		for(Tax2 item:pTax2s){
			String hql="from CwLogs where mergeTaxNo=? and type=0";
			List<CwLogs> rs =cwLogsDao.select(hql, item.getNo());
			for(CwLogs cwItem:rs){
				item.addTax(cwItem.getTaxno());				
			}
			doLogsBack(item);
		}
		
		CwTxtWrite cw=new CwTxtWrite(pTax2s,fileName,fristRow);
		cw.tip=tip;
		cw.write();
		
	}
	
	public void writeToFile2(String fileName,List<Tax> taxs,String fristRow)
	{
		CwTxtWrite cw=new CwTxtWrite(fileName,taxs,fristRow);
		cw.writeOtherType();
		
	}
	
	/**
	 * 删除日志
	 */
	public void delete(String mergeTaxNo){
		String hql="update CwLogs set type=1 where mergeTaxNo=?";
		cwLogsDao.bulkUpdate(hql, mergeTaxNo);
	}
	
	/**
	 * 对删除的发票回填新的金穗号
	 */
	public void feedback(CwLogs pItem){
		String hql="update CwLogs set feedbackNo=? where jinsuiNo=?";
		cwLogsDao.bulkUpdate(hql, new String[]{pItem.getFeedbackNo(),pItem.getJinsuiNo()});
	}
	
	/**
	 * 记录日志
	 */
	protected void doLogs(){
		//List<CwLogs> logs = new ArrayList<CwLogs>();
		for(List<String> no:nos){
			if(no!=null&&!no.isEmpty()){
				for(String temp: no){
					CwLogs cwLogs = new CwLogs();
					cwLogs.setCreateDate(nowTime);
					cwLogs.setOperator(uid);
					cwLogs.setMergeTaxNo(no.get(0));
					cwLogs.setTaxno(temp);
					cwLogs.setJinsuiNo("e");
					cwLogs.setType(0);
					cwLogs.setMoney(Float.parseFloat(map.get(temp)[0]));
					cwLogs.setCustomerNo(map.get(temp)[2]);
					cwLogs.setFenpeiNo(map.get(temp)[1]);
					//nowTime,"1382",temp,no.get(0)
				//	System.out.println(cwLogs.getTaxno());
					cwLogsDao.insert(cwLogs);
					//logs.add(cwLogs);
				}		
			}
		}
	}
	
	protected void doLogsBack(Tax2 item){
		String hql2="update CwLogs set jinsuiFaxMoney=?,jinsuiNo=?,jinsuiMoney=? where taxno=?";
		cwLogsDao.bulkUpdate(hql2, new Object[]{item.getFaxMoney(),item.getJinSuiNo(),item.getJinSuiMoney(),item.getNo()});
	
	}
	
	
	/**
	 * 检查是否已经合并过
	 */
	protected void checkIsMerge(List<String> nos){
		List<String> temp = new ArrayList<String>();
		//System.out.println(nowTime);
		String hql="from CwLogs where taxno=? and type=0";
		for(String no:nos){
			//System.out.println("no="+no);
			if(cwLogsDao.selectOne(hql, no)!=null){
				temp.add(no);				
			 }
		}
		for(String noid:temp){
			int index=0;
			boolean flag=false;
			for(int i=0;i<taxs.size();i++){
				if(taxs.get(i).getNo().equals(noid)){
					index=i;
					flag=true;
					break;
				}
			}
			if(flag){
				//System.out.println("error"+noid);
				nos.remove(noid);
				taxs.remove(index);
			}
			
		}
	}

	public List<CwLogs> selectLogs(CwLogs pItem){
		String hql="from CwLogs where 1=1";
		Vector<Object> params = new Vector<Object>();
		if(pItem.getJinsuiNo()!=null&&!pItem.getJinsuiNo().equals("")){
			String hql2="from CwLogs where jinsuiNo=?";
			List<CwLogs> rs=cwLogsDao.select(hql2, pItem.getJinsuiNo());
			if(rs==null||rs.isEmpty()){
				return null;
			}else{
				for(CwLogs item:rs){
					CwLogs item2=new CwLogs();
					item2.setMergeTaxNo(item.getMergeTaxNo());
					cwlogs.addAll(selectLogs(item2));
				}				
				return cwlogs;			
			}
		}
		if(pItem.getTaxno()!=null&&!pItem.getTaxno().equals("")){
			String hql2="from CwLogs where taxno=?";
			List<CwLogs> aa=cwLogsDao.select(hql2, pItem.getTaxno());
			if(aa==null||aa.isEmpty()){
				return null;
			}else{		
				for(CwLogs item:aa){
					CwLogs item2=new CwLogs();
					item2.setMergeTaxNo(item.getMergeTaxNo());		
					System.out.println(item.getMergeTaxNo());
					cwlogs.addAll(selectLogs(item2));
				}				
				return cwlogs;
			}						
		}
		if(pItem.getMergeTaxNo()!=null&&!pItem.getMergeTaxNo().equals("")){
			hql+=" and mergeTaxNo=?";
			params.add(pItem.getMergeTaxNo());
		}
		if(pItem.getCustomerNo()!=null&&!pItem.getCustomerNo().equals("")){
			hql+=" and customerNo=?";
			params.add(pItem.getCustomerNo());
		}
		if(pItem.getOperator()!=null&&!pItem.getOperator().equals("")){
			hql+=" and operator=?";
			params.add(pItem.getOperator());
		}
		if(pItem.getBegin()!=null&&!pItem.getBegin().equals("")){
			hql+=" and createDate>'"+pItem.getBegin()+"'";
		}
		if(pItem.getEnd()!=null&&!pItem.getEnd().equals("")){
			hql+=" and createDate<'"+pItem.getEnd()+"'";
			
		}
		if(pItem.getType()!=null&&pItem.getType()!=-1){
			hql+=" and type=? ";
			params.add(pItem.getType());
			
		}
		hql+=" order by createDate";
		return cwLogsDao.select(hql, params.toArray());
	}
	
	public List<CwLogs> selectLogsNoSame(CwLogs pItem){
		String hql="select distinct mergeTaxNo  from CwLogs where 1=1";
		Vector<Object> params = new Vector<Object>();
		if(pItem.getTaxno()!=null&&!pItem.getTaxno().equals("")){
			hql+=" and taxno=?";
			params.add(pItem.getTaxno());
		}
		if(pItem.getMergeTaxNo()!=null&&!pItem.getMergeTaxNo().equals("")){
			hql+=" and mergeTaxNo=?";
			params.add(pItem.getMergeTaxNo());
		}
		if(pItem.getOperator()!=null&&!pItem.getOperator().equals("")){
			hql+=" and operator=?";
			params.add(pItem.getOperator());
		}
		if(pItem.getBegin()!=null&&pItem.getBegin().equals("")){
			hql+=" and createDate>'"+pItem.getBegin()+"'";
		}
		if(pItem.getEnd()!=null&&!pItem.getEnd().equals("")){
			hql+=" and createDate<'"+pItem.getEnd()+"'";
			
		}
		return cwLogsDao.select(hql, params.toArray());
	}
	
	public CommonDAO<CwLogs> getCwLogsDao() {
		return cwLogsDao;
	}

	public void setCwLogsDao(CommonDAO<CwLogs> cwLogsDao) {
		this.cwLogsDao = cwLogsDao;
	}
	
	public List<CwLogs> test() {
		String hql2="from CwLogs where taxno=?";
		return cwLogsDao.select(hql2, "0077832022");
	}
	
	
}
