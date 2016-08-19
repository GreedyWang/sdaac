package app.biz.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import common.dao.CommonDAO;
import app.biz.DepartmentBiz;
import app.biz.DepartmentIndexBiz;
import app.biz.EmpBiz;
import app.biz.EmpIndexBiz;
import app.biz.IndexTargetBiz;
import app.entity.TcompanyIndex;
import app.entity.Tdepartment;
import app.entity.TempIndex;
import app.entity.Tempolyee;
import app.entity.TindexTarget;
import app.hr.DAO.IndexDao;

public class DepartmentIndexBizImpl implements DepartmentIndexBiz {
	private CommonDAO<TcompanyIndex> departIndexdao=null;
	private CommonDAO<Tdepartment> departdao=null;
	private CommonDAO<Tempolyee> empdao=null;
	private CommonDAO<TempIndex> empIndexdao;
	private IndexDao indexdao;
	
	public DepartmentIndexBizImpl()
	{
		
	}
	
	public DepartmentIndexBizImpl(CommonDAO<TcompanyIndex> departIndexdao)
	{
		this.departIndexdao=departIndexdao;
	}
	public CommonDAO<Tdepartment> getDepartdao() {
		return departdao;
	}

	public void setDepartdao(CommonDAO<Tdepartment> departdao) {
		this.departdao = departdao;
	}

	public List<TcompanyIndex> doSelectByindexid(Integer id)
	{
		String hql="from TcompanyIndex as departIndex inner join fetch departIndex.tdepartment where departIndex.tindexTarget =?";
		return departIndexdao.select(hql, id);
	}
	
	public void doInsert(TcompanyIndex item) {
	
		departIndexdao.insert(item);
	}
	/**
	 * 更新部门指标
	 * @param weight,output,score,id
	 */
	public void update(TcompanyIndex item)
	{	
		String hql="update TcompanyIndex as ci ";
		Vector<Object> params=new Vector<Object>();
		
		if(item.getPercentage()!=null)
		{
			hql+="set ci.percentage=? ";
			params.add(item.getPercentage());
		}
		if(item.getAct_output()!=null)
		{
			hql+=" ,ci.act_output=? ";
			params.add(item.getAct_output());
		}
		if(item.getScore()!=null)
		{
			hql+=" , ci.score=? ";
			params.add(item.getScore());
		}
		if(params.size()>1)
		{
			hql+=" where ci.tdepartment.id=? and ci.tindexTarget.id=?";
			params.add(item.getTdepartment().getId());
			params.add(item.getTindexTarget().getId());
			departIndexdao.bulkUpdate(hql, params.toArray(new Object[1] ));
		}
	}
	/**
	 * 查询部门指标
	 * @param departID
	 */
	public List<TcompanyIndex> doSelectCompanyIndex(Integer departmemtID,String version)
	{
		String hql="from TcompanyIndex as companyIndex inner join fetch companyIndex.tindexTarget as index where companyIndex.tdepartment.id=?";
		Vector<Object> ve=new Vector<Object>();
		ve.add(departmemtID);
		if(version==null||version.equals(""))
		{
			version=indexdao.selectMaxVersionIndex();
			if(version!=null)
			{
				hql+=" and index.version=?";
				ve.add(version);
			}			
		}else
		{
			hql+=" and index.version=?";
			ve.add(version);
		}
		return departIndexdao.select(hql, ve.toArray());
	}
	
	/**
	 * 查询部门指标
	 * @param departID
	 */
	public List<TcompanyIndex> doSelectDepartmentIndex(Integer departmemtID,String version)
	{
		String hql="from TcompanyIndex as companyIndex inner join fetch companyIndex.tindexTarget as index where companyIndex.tindexTarget.departID=2 and companyIndex.tdepartment.id=?";
		Vector<Object> ve=new Vector<Object>();
		ve.add(departmemtID);
		if(version==null||version.equals(""))
		{
			version=indexdao.selectMaxVersionIndex();
			if(version!=null)
			{
				hql+=" and index.version=?";
				ve.add(version);
			}			
		}else
		{
			hql+=" and index.version=?";
			ve.add(version);
		}
		return departIndexdao.select(hql, ve.toArray());
	}
	
	
	public Map<Integer,List<TcompanyIndex>> selectEveryDepartCompanyIndexs(Integer departmemtID,String version)
	{
		Map<Integer,List<TcompanyIndex>> departIndexs=new HashMap<Integer, List<TcompanyIndex>>();
		String hql="from TcompanyIndex as companyIndex inner join fetch companyIndex.tindexTarget as index where 1=1 ";
		Vector<Object> ve=new Vector<Object>();
		if(departmemtID==1)
		{
			hql+="and index.departID=1";
		}else{
			hql+="and companyIndex.tdepartment.id=?";
			ve.add(departmemtID);
		}	
		if(version==null||version.equals(""))
		{
			version=indexdao.selectMaxVersionIndex();
			if(version!=null)
			{
				hql+=" and index.version=?";
				ve.add(version);
			}			
		}else
		{
			hql+=" and index.version=?";
			ve.add(version);
		}
		List<TcompanyIndex> selectRs=departIndexdao.select(hql, ve.toArray());
		for(TcompanyIndex cIndexs:selectRs)
		{
			if(departIndexs.get(cIndexs.getTdepartment().getId())!=null)
			{
				departIndexs.get(cIndexs.getTdepartment().getId()).add(cIndexs);
			}else{
				List<TcompanyIndex> temp=new ArrayList<TcompanyIndex>();
				temp.add(cIndexs);
				departIndexs.put(cIndexs.getTdepartment().getId(), temp);
			}
		}
		return departIndexs;
	}
	
	/**公司指标拷贝到部门指标*/
	private TcompanyIndex copyDpIndex(Tdepartment elem,TcompanyIndex dpindex)
	{
		TcompanyIndex cindex=new TcompanyIndex();
		cindex.setTindexTarget(dpindex.getTindexTarget());
		cindex.setTdepartment(elem);								
		cindex.setPercentage(dpindex.getPercentage());
		cindex.setAct_output(dpindex.getAct_output());
		cindex.setScore(dpindex.getScore());	
		return cindex;
	}
	/**部门指标拷贝到员工指标*/
	private TempIndex copyEmpIndex(TcompanyIndex dpindex,Tempolyee uid)
	{
		TempIndex managerIndex=new TempIndex();
		managerIndex.setIndex(dpindex.getTindexTarget());
		managerIndex.setTempolyee(uid);
		managerIndex.setPercentage(dpindex.getPercentage());
		managerIndex.setAct_output(dpindex.getAct_output()+"");
		managerIndex.setScore(dpindex.getScore());
		
		return managerIndex;
	}
	/**
	 * 批量更新和插入
	 */
	public boolean addnUpdate(List<TcompanyIndex> dpIndexs,Integer departID,List<TcompanyIndex> indexUpdates,String version)
	{
		boolean flag=true;
		List<TempIndex> empIndexs= new LinkedList<TempIndex>();
		List<TempIndex> managerIndexs= new ArrayList<TempIndex>();
		List<TindexTarget> indexs=new ArrayList<TindexTarget>();
		List<TcompanyIndex> dpIndexss=new ArrayList<TcompanyIndex>();
		try {
			if(dpIndexs!=null)
			{
				if(departID==1)//如果是公司指标
				{
					//得到所有部门
					DepartmentBiz departBiz=new DepartmentBizImpl(this.departdao);
					List<Tdepartment> departs=departBiz.getAll();
						
					for(TcompanyIndex dpindex:dpIndexs)
					{
						indexs.add(dpindex.getTindexTarget());
						for(Tdepartment elem:departs)
						{
							dpIndexss.add(this.copyDpIndex(elem,dpindex));
						}
					}
					//更新指标
					if(indexUpdates!=null)
					{
						for(TcompanyIndex element:indexUpdates)
						{	
							for(Tdepartment elem:departs)
							{
								this.update(this.copyDpIndex(elem,element));
							}
						}
					}
				}
				else
				{
					//更新指标
					if(indexUpdates!=null)
					{
						for(TcompanyIndex element:indexUpdates)
						{											
								this.updateAD(element);
						}
					}
					dpIndexss=dpIndexs;
					Tdepartment depart=this.departdao.select("from Tdepartment as dp where dp.id=?", departID).get(0);
					Tempolyee departManager=new Tempolyee(depart.getPmangerid());
					for(TcompanyIndex dpindex:dpIndexs)
					{
						//更新指标
						this.update(dpindex);
						managerIndexs.add(this.copyEmpIndex(dpindex,departManager));
						
						
						dpindex.setTdepartment(new Tdepartment(departID));	
						EmpBiz empBiz=new EmpBizImpl(empdao);//员工service
						List<Tempolyee> emps=empBiz.selectEmpsExpectManager(departID);
						indexs.add(dpindex.getTindexTarget());
						if(!dpindex.getTindexTarget().getIsChoice().equals("可选"))
						{
							for(Tempolyee emp:emps)
							{
								if(emp.getUid()!=departManager.getUid())
								{
									if(dpindex.getTindexTarget().getIsChoice().equals("必选"))
									{
										TempIndex empIndex=new TempIndex();
										empIndex.setIndex(dpindex.getTindexTarget());
										empIndex.setTempolyee(emp);
										empIndexs.add(empIndex);
									}
									if(dpindex.getTindexTarget().getIsChoice().equals("必选且权重不可变"))
									{
										TempIndex empIndex=new TempIndex();
										empIndex.setIndex(dpindex.getTindexTarget());
										empIndex.setPercentage(dpindex.getPercentage());
										empIndex.setTempolyee(emp);
										empIndexs.add(empIndex);
									}
								}
							}	
						}
					}
				
				}
				if(version==null||version.equals(""))
				{
					this.indexdao.createVersionBlukFlushInsert(indexs);
				}else{
					this.indexdao.hasVersionBlukFlushInsert(indexs,version);
				}
				
				this.departIndexdao.blukFlushInsert(dpIndexss);
				this.empIndexdao.blukFlushInsert(managerIndexs);
				
//				if(!empIndexs.isEmpty())//插入个人指标
//				{					
//					for(TempIndex elem:empIndexs)
//					//empIndexBiz.doInsert(elem);
//					this.doInsert(elem);
//				}
			}
			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag=false;
			//throw(new RuntimeException("服务器响应超时，请再操作次"));
			
		}
		return flag;
	}
	public void doInsert(TempIndex item) {
		// TODO 自动生成方法存根
		IndexTargetBiz indexBiz=new IndexTargetBizImpl(indexdao);
		if(indexBiz.doSelectByPK(item.getIndex().getId())==null)
		{
			indexBiz.insertIndex(item.getIndex());
		}
		empIndexdao.insert(item);
	}
	//get and set
	public CommonDAO<TcompanyIndex> getDepartIndexdao() {
		return departIndexdao;
	}
	
	public void setDepartIndexdao(CommonDAO<TcompanyIndex> departIndexdao) {
		this.departIndexdao = departIndexdao;
	}
	/**
	 * 更新score,
	 * 因为改变了指标，所以score会连带更改
	 * @param entity Index
	 */
	public boolean updateScore(TindexTarget index)
	{
		boolean flag=true;
		//float a=
		List<TcompanyIndex> dpIndexs=this.doSelectByindexid(index.getId());
		for(TcompanyIndex elem:dpIndexs)
		{
			
		}
		
		return flag;
	}
	/**
	 * 删除部门指标
	 * @param pk
	 */
	public boolean deleteByPK(Integer pk) {
		// TODO Auto-generated method stub
		boolean flag=true;
		try {
			this.departIndexdao.deleteByPK(pk);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag=false;
		}
		return flag;
		
	}
	
	public CommonDAO<Tempolyee> getEmpdao() {
		return empdao;
	}

	public void setEmpdao(CommonDAO<Tempolyee> empdao) {
		this.empdao = empdao;
	}

	public CommonDAO<TempIndex> getEmpIndexdao() {
		return empIndexdao;
	}

	public void setEmpIndexdao(CommonDAO<TempIndex> empIndexdao) {
		this.empIndexdao = empIndexdao;
	}

	public IndexDao getIndexdao() {
		return indexdao;
	}

	public void setIndexdao(IndexDao indexdao) {
		this.indexdao = indexdao;
	}

	
	
	
	/**
	public Map<TindexTarget, List<TcompanyIndex>> doSelect(List<TindexTarget> indexList) {
		// TODO 自动生成方法存根
	//	System.out.println("=================================>"+this.doSelectByindexid(1));		
		Map<TindexTarget, List<TcompanyIndex>>map=new HashMap<TindexTarget, List<TcompanyIndex>>();
		for (TindexTarget target : indexList) {
		//	System.out.println("==============>"+target.getId());
			List<TcompanyIndex>list=this.doSelectByindexid(target.getName());
			System.out.println(list);
			map.put(target, list);
		}
		return map;

	}
	public Map<String,Float>  test()
	{
		String hql="from TcompanyIndex as departIndex inner join fetch departIndex.tdepartment inner join fetch departIndex.tindexTarget";
		List<TcompanyIndex> list=departIndexdao.select(hql, null);
		Map<String,Float> map=new HashMap<String, Float>();
		for (TcompanyIndex index : list) {
			String name=(index.getTdepartment().getName())+index.getTindexTarget().getName();
			if(index.getPercentage()==0.0)
			{
				//map.put(name,"");
			}
			else
			{
				map.put(name,index.getPercentage());
			}		
			map.put("LevelA"+index.getTindexTarget().getName(), index.getTindexTarget().getA());
			map.put("LevelB"+index.getTindexTarget().getName(), index.getTindexTarget().getB());
			map.put("LevelC"+index.getTindexTarget().getName(), index.getTindexTarget().getC());
			map.put("output"+index.getTindexTarget().getName(), index.getTindexTarget().getOutput());
			map.put("score"+index.getTindexTarget().getName(), index.getTindexTarget().getScore());
		}
		
		return map;
	}
*/
	/**
	 * 更新部门指标得分 a
	 * 更新部门经理得分 b
	 * 如果指标是必选且权重不可变也更新 c
	 */
	private void updateAD(TcompanyIndex item)
	{
		this.update(item);//a
		Integer departId=item.getTdepartment().getId();
		String hql="update TempIndex as ei set ei.act_output=? ,ei.percentage=? ,ei.score=? where ei.index.id=? ";
		if(!item.getTindexTarget().getIsChoice().equals("必选且权重不可变"))
		{
			//String cc="from Tdepartment where  ";
			Tdepartment dmp= this.departdao.selectByPk(departId);
			hql+="and ei.tempolyee.id='"+dmp.getPmangerid()+"'";
		}
		
		this.empIndexdao.bulkUpdate(hql, new Object[]{item.getAct_output(),item.getPercentage(),item.getScore(),item.getTindexTarget().getId()});
		
	}
	
	public void copyDepartmentIndexsResultToEmp(int departmentId,String version){
		List<TcompanyIndex> rs=doSelectCompanyIndex(departmentId, version);
		
		for(TcompanyIndex companyIndex:rs){
			
		}
	}
}
