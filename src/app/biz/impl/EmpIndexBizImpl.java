package app.biz.impl;

import java.util.List;
import java.util.Vector;

import app.common.CalIndicator;
import common.dao.CommonDAO;
import app.biz.DepartmentIndexBiz;
import app.biz.EmpIndexBiz;
import app.biz.IndexTargetBiz;
import app.entity.TcompanyIndex;
import app.entity.Tdepartment;
import app.entity.TempIndex;
import app.entity.Tempolyee;
import app.entity.TindexTarget;
import app.hr.DAO.IndexDao;

public class EmpIndexBizImpl implements EmpIndexBiz {

	private CommonDAO<TempIndex> empIndexdao=null; 
	private IndexDao indexdao=null;
	private CommonDAO<TcompanyIndex> departIndexdao=null;
	private IndexTargetBiz indexBiz;
	
	public IndexTargetBiz getIndexBiz() {
		return indexBiz;
	}
	public void setIndexBiz(IndexTargetBiz indexBiz) {
		this.indexBiz = indexBiz;
	}
	public CommonDAO<TempIndex> getEmpIndexdao() {
		return empIndexdao;
	}
	public void setEmpIndexdao(CommonDAO<TempIndex> empIndexdao) {
		this.empIndexdao = empIndexdao;
	}
	public EmpIndexBizImpl()
	{
		
	}
	public EmpIndexBizImpl(CommonDAO<TempIndex> empIndexdao)
	{
		this.empIndexdao=empIndexdao;
	}
	/**
	 * 根据部门id 查员工指标
	 */
	public List<TempIndex> doSelectByIndexID(int id) {
		// TODO 自动生成方法存根
		String hql="from TempIndex as e where e.tempolyee.tdepartment.id=?";
		return empIndexdao.select(hql, id);
//		Map map=new HashMap();
//		for (TempIndex index : list) {
//			map.put(index.getTempolyee(), arg1)
//		}
		
	}
	public void doInsert(TempIndex item) {
		// TODO 自动生成方法存根
//		IndexTargetBiz indexBiz= new IndexTargetBizImpl(this.indexdao);
		if(indexBiz.doSelectByPK(item.getIndex().getId())==null)
		{
			indexBiz.insertIndex(item.getIndex());
		}
		empIndexdao.insert(item);
	}
	public List<TempIndex> doSelectByUid(String id,String version) {
		// TODO 自动生成方法存根
		String  hql="from TempIndex as e inner join fetch e.tempolyee inner join fetch e.index where e.tempolyee.uid=? and e.index.version=?";
		Vector<Object> params=new Vector<Object>();
		params.add(id);
		
		if(version==null)
		{
			version=this.indexdao.selectMaxVersionIndex();
		}
		params.add(version);		
		return empIndexdao.select(hql, params.toArray());
		
	}
	public boolean checkDate(String uid, int indexID) {
		// TODO 自动生成方法存根
		boolean flag=false;
		String hql="from TempIndex as e where e.tempolyee.uid=? and e.index.id=?";
		Object[] params=new Object[2];
		params[0]=uid;
		params[1]=indexID;
		if(empIndexdao.select(hql, params)!=null)
		{
			flag= true;
		}
		return flag;
	}
	//cp
	public void doUpdate(TempIndex item) {
		// TODO 自动生成方法存根
		String hql="update TempIndex as e set e.percentage=? , e.act_output=? ,e.score=? where e.id=?";
		Object[] params=new Object[4];
		params[0]=item.getPercentage();
		params[1]=item.getAct_output();
		params[2]=item.getScore();	
		params[3]=item.getId();
		empIndexdao.bulkUpdate(hql, params);
	}
	/**
	 * 修改实际值和得分
	 */
	private void doUpdateScore(TempIndex item) {
		// TODO 自动生成方法存根
		String hql="update TempIndex as e set  e.act_output=? ,e.score=? where e.id=?";
		Object[] params=new Object[3];
		params[0]=item.getAct_output();
		params[1]=item.getScore();	
		params[2]=item.getId();
		System.out.println(item.getTempolyee().getName()+":"+item.getId());
		empIndexdao.bulkUpdate(hql, params);
	}
	
	public void doDelete(TempIndex item)
	{
		//empIndexdao.delete(item);
		this.doDeleteByPk(item.getId());
	}
	public void doDeleteByPk(Integer indexID)
	{
		empIndexdao.deleteByPK(indexID);
	}
	/***
	 * add empIndex and DepartIndex
	 * @return
	 */
	public boolean doInsertIndex(TempIndex item,Integer departID)
	{
		boolean flag=true;
		try {
			this.doInsert(item);
			TcompanyIndex departIndex=new TcompanyIndex();
			departIndex.setTindexTarget(item.getIndex());
			departIndex.setTdepartment(new Tdepartment(departID));
			DepartmentIndexBiz departIndexBiz=new DepartmentIndexBizImpl(departIndexdao);
			departIndexBiz.doInsert(departIndex);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag=false;
		}
		return flag;
	}
	/**
	 * copy部门经理得分到员工
	 * @param list
	 */
	public void doCopy(List<TcompanyIndex> list){
		for(TcompanyIndex ci:list){
		
			String hql="from TempIndex as ei join fetch ei.index join fetch ei.tempolyee where ei.index.name like '%"+ci.getTindexTarget().getName()+"%' and " +
			" UPPER(ei.index.a)=?" +
			" and UPPER(ei.index.b)=? and UPPER(ei.index.c)=? and ei.index.version=? and ei.tempolyee.tdepartment.id=? ";
			List<TempIndex> rs= empIndexdao.select(hql, new Object[]{
					//ci.getTindexTarget().getFormula(),
					ci.getTindexTarget().getA().toUpperCase(),ci.getTindexTarget().getB().toUpperCase(),ci.getTindexTarget().getC().toUpperCase(),
					ci.getTindexTarget().getVersion(),ci.getTdepartment().getId()});
			//System.out.println(ci.getTindexTarget().getName()+":"+rs.size());
			for(TempIndex ei:rs){
				ei.setScore(ci.getScore());
				ei.setAct_output(ci.getAct_output()+"");
				doUpdateScore(ei);
			}
		}
		
	}
	/**
	 * copy部门经理得分到员工 外加员工条件
	 * @param list
	 * @param uids
	 */
	public void doCopyByCondition(List<TcompanyIndex> list,String[] uids){
		String theUid="'-1'";	
		if(uids!=null&&uids.length>0){
			String[] theUids=uids[0].split(",");
			for(int i=0;i<theUids.length;i++){
				theUid+=" ,'"+theUids[i]+"'";
			}		
		}
		//System.out.println(theUid);
		for(TcompanyIndex ci:list){
		
			String hql="from TempIndex as ei join fetch ei.index join fetch ei.tempolyee where ei.index.name like '%"+ci.getTindexTarget().getName()+"%' and " +
			" UPPER(ei.index.a)=?" +
			" and UPPER(ei.index.b)=? and UPPER(ei.index.c)=? and ei.index.version=? and ei.tempolyee.tdepartment.id=? and ei.tempolyee.uid in ("+theUid+")";//and ei.tempolyee.uid in ("+theUid+")
			
			List<TempIndex> rs= empIndexdao.select(hql, new Object[]{
					//ci.getTindexTarget().getFormula(),
					ci.getTindexTarget().getA().toUpperCase(),ci.getTindexTarget().getB().toUpperCase(),
					ci.getTindexTarget().getC().toUpperCase(),
					ci.getTindexTarget().getVersion(),ci.getTdepartment().getId()});//,theUid
			
			for(TempIndex ei:rs){				
				ei.setScore(ci.getScore());
				ei.setAct_output(ci.getAct_output()+"");
				doUpdateScore(ei);
			}
		}
		
	}
	
	public TempIndex doSelectByPk(int indexId){
		String hql="from TempIndex e join fetch e.tempolyee inner join fetch e.index where e.index.id=?";
		List<TempIndex> rs = empIndexdao.select(hql, indexId);
		if(rs !=null && !rs.isEmpty() ) {
			return empIndexdao.select(hql, indexId).get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * delete empIndex by uid & version
	 * @param uid
	 * @param version
	 */
	public void doDeletebyUidnVersion(String uid , String version){
//		String hql = "delete from TempIndex where tempolyee.uid = "+uid+" and index.version= "+version;
		String hql = "from TempIndex t where t.tempolyee.uid = ? and t.index.version= ?";
//		empIndexdao.blukDelete(hql);
		List<TempIndex> rs = empIndexdao.select(hql,new String[]{uid, version});
		for(TempIndex item : rs) {
			empIndexdao.deleteByPK(item.getId());
		}

	}
	
	public CommonDAO<TcompanyIndex> getDepartIndexdao() {
		return departIndexdao;
	}
	public void setDepartIndexdao(CommonDAO<TcompanyIndex> departIndexdao) {
		this.departIndexdao = departIndexdao;
	}
	public IndexDao getIndexdao() {
		return indexdao;
	}
	public void setIndexdao(IndexDao indexdao) {
		this.indexdao = indexdao;
	}
	
}
