package sdaac.wym.app.Service.plm;

import java.util.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import common.util.Page;
import sdaac.wym.app.Dao.plm.BomDao;
import sdaac.wym.app.Dao.plm.PartDao;
import sdaac.wym.app.entity.plm.Bom;
import sdaac.wym.app.entity.plm.MyTree;
import sdaac.wym.app.entity.plm.Part;


public class PlmManager {
	protected BomDao bomDao = null;
	protected PartDao partDao = null;

	private Map<Integer, List<Bom>> rs = new HashMap<Integer, List<Bom>>();

	public List<Part> selectPartByCond(String cond, Page page, boolean isA) {
		if (isA) {
			return this.partDao.selectMPartByCondA(cond, page);
		} else {
			return this.partDao.selectMPartByCond(cond, page);
		}
	}

	/**
	 * 生成树，找到pk下所有零件
	 * 
	 * @param pk
	 * @param myTree
	 */
	public void selectSeries(String pk, MyTree<Bom> myTree)// Map<Integer,
															// List<MBom>> rs
	{
		//System.out.println(">>>>>pk"+pk);
		List<Bom> mboms = bomDao.isLeaf(pk);

		if (mboms != null) {

			for (int i = 0; i < mboms.size(); i++) {
				MyTree<Bom> node = new MyTree<Bom>();
				node.setNode(mboms.get(i));
				if (myTree.getChildren() == null) {
					List<MyTree<Bom>> children = new ArrayList<MyTree<Bom>>();
					children.add(node);
					myTree.setChildren(children);
				} else {
					myTree.getChildren().add(node);
				}
				//System.out.println(">>>>>"+mboms.get(i).getCpart().getId());
				selectSeries(mboms.get(i).getCpart().getId(), myTree.getChildren().get(i));
			}
		}

	}
	/**
	 * 查找下一层的所有子节点
	 * @param pk
	 * @return
	 */
	public List<Bom> selectNextLevelChildren(String pk)
	{
		return bomDao.isLeaf(pk);
	}
	/**
	 * 查找上一层的所有子节点
	 * @param pk
	 * @return
	 */
	public List<Bom> selectPreLevelChildren(String pk)
	{
		return bomDao.selectFather(pk);
	}
	// /**
	// * 反向生成树，找到pk下所有零件
	// * @param pk
	// * @param myTree
	// */
	// public void reSelectSeries(String pk,MyTree<Bom> myTree)//Map<Integer,
	// List<MBom>> rs
	// {
	//
	// List<Bom> mboms=isRoot(pk);
	//		
	// if(mboms!=null)
	// {
	//			
	// for(int i=0;i<mboms.size();i++)
	// {
	// MyTree<Bom> node=new MyTree<Bom>();
	// node.setNode(mboms.get(i));
	// if(myTree.getChildren()==null)
	// {
	// List<MyTree<Bom>> children=new ArrayList<MyTree<Bom>>();
	// children.add(node);
	// myTree.setChildren(children);
	// }else{
	// myTree.getChildren().add(node);
	// }
	// reSelectSeries(mboms.get(i).getCid(),myTree.getChildren().get(i));
	// }
	// }
	//		
	// }
	// /**
	// * 生成树，找到pk下所有零件
	// * @param pk
	// * @param myTree
	// */
	// public void selectSeries2(String pk,MyTree<Bom> myTree)//Map<Integer,
	// List<MBom>> rs
	// {
	//
	// List<Ebom> mboms=isLeaf2(pk);
	// if(mboms!=null)
	// {
	//			
	// for(int i=0;i<mboms.size();i++)
	// {
	// MyTree<Bom> node=new MyTree<Bom>();
	// node.setNode(mboms.get(i));
	// if(myTree.getChildren()==null)
	// {
	// List<MyTree<Bom>> children=new ArrayList<MyTree<Bom>>();
	// children.add(node);
	// myTree.setChildren(children);
	// }else{
	// myTree.getChildren().add(node);
	// }
	// selectSeries2(mboms.get(i).getCid(),myTree.getChildren().get(i));
	// }
	// }
	// }
	// /**
	// * 反向生成树，找到pk下所有零件
	// * @param pk
	// * @param myTree
	// */
	// public void reSelectSeries2(String pk,MyTree<Bom> myTree)//Map<Integer,
	// List<MBom>> rs
	// {
	//
	// List<Bom> mboms=isRoot2(pk);
	// if(mboms!=null)
	// {
	//			
	// for(int i=0;i<mboms.size();i++)
	// {
	// MyTree<Bom> node=new MyTree<Bom>();
	// node.setNode(mboms.get(i));
	// if(myTree.getChildren()==null)
	// {
	// List<MyTree<Bom>> children=new ArrayList<MyTree<Bom>>();
	// children.add(node);
	// myTree.setChildren(children);
	// }else{
	// myTree.getChildren().add(node);
	// }
	// reSelectSeries2(mboms.get(i).getCid(),myTree.getChildren().get(i));
	// }
	// }
	// }
	//	
	//	
	//

	// /**
	// * 是否有父节点
	// * @param mpart.id
	// * @return
	// */
	// public List<Bom> isRoot(String pk)
	// {
	// List<Bom> mboms=mbomDao.selectFather(pk);
	// if(mboms!=null&&!mboms.isEmpty())
	// {
	// return mboms;
	// }else
	// {
	// return null;
	// }
	// }
	// public List<Bom> isRoot2(String pk)
	// {
	// List<Bom> eboms=ebomDao.selectFather(pk);
	// if(eboms!=null&&!eboms.isEmpty())
	// {
	// return eboms;
	// }else
	// {
	// return null;
	// }
	// }
	//	
	// /**
	// * 是否有节点
	// * @param mpart.id
	// * @return
	// */
	// public List<Ebom> isLeaf2(String pk)
	// {
	// List<Ebom> mboms=ebomDao.selectChild(pk);
	// if(mboms!=null&&!mboms.isEmpty())
	// {
	// return mboms;
	// }else
	// {
	// return null;
	// }
	// }
	// /**
	// * 把mbom树转换成mpart树
	// * @param item
	// * @param mp2
	// * @return
	// */
	// public Map<Integer, List<Part>> changetoMbom(MyTree<Bom>
	// item,MyTree<Part> mp2)
	// {
	// if(item.getNode()!=null)
	// {
	// Part mp= mpartDao.selectByPk(item.getNode().getCid());
	// mp.setQuant(item.getNode().getBnum());
	// mp2.setNode(mp);
	// }else{
	// System.out.println("top");
	// }
	//		
	// if(item.getChildren()!=null)
	// {
	// //item.getNode().setl
	// for(MyTree<Bom> tmp:item.getChildren())
	// {
	// Part mp= mpartDao.selectByPk(tmp.getNode().getCid());
	// MyTree<Part> aa=new MyTree<Part>();
	//				
	// if(mp2.getChildren()==null)
	// {
	// List<MyTree<Part>> children=new ArrayList<MyTree<Part>>();
	// aa.setNode(mp);
	// children.add(aa);
	// mp2.setChildren(children);
	// }else
	// {
	//					
	// mp2.getChildren().add(aa);
	// }
	//		
	//			
	// changetoMbom(tmp,aa);
	//	
	//			
	// }
	// }
	// return null;
	// }
	// public Map<Integer, List<Part>> changetoMbom2(MyTree<Bom>
	// item,MyTree<Part> mp2)
	// {
	// if(item.getNode()!=null)
	// {
	// Part mp= epartDao.selectByPk(item.getNode().getCid());
	// mp.setQuant(item.getNode().getBnum());
	// mp2.setNode(mp);
	// }else{
	// System.out.println("top");
	// }
	//		
	// if(item.getChildren()!=null)
	// {
	// //item.getNode().setl
	// for(MyTree<Bom> tmp:item.getChildren())
	// {
	// Part mp= epartDao.selectByPk(tmp.getNode().getCid());
	// MyTree<Part> aa=new MyTree<Part>();
	//				
	// if(mp2.getChildren()==null)
	// {
	// List<MyTree<Part>> children=new ArrayList<MyTree<Part>>();
	// aa.setNode(mp);
	// children.add(aa);
	// mp2.setChildren(children);
	// }else
	// {
	//					
	// mp2.getChildren().add(aa);
	// }
	//		
	//			
	// changetoMbom2(tmp,aa);
	//	
	//			
	// }
	// }
	// return null;
	// }
	// public Map<Integer, List<MPart>> changetoMbom2(MyTree<Ebom>
	// item,MyTree<Epart> mp2)
	// {
	//		
	//		
	// if(item.getNode()!=null)
	// {
	// Epart mp= epartDao.selectByPk(item.getNode().getCid());
	// mp2.setNode(mp);
	// }else{
	// System.out.println("top");
	// }
	//		
	// if(item.getChildren()!=null)
	// {
	// //item.getNode().setl
	// for(MyTree<Ebom> tmp:item.getChildren())
	// {
	// Epart mp= epartDao.selectByPk(tmp.getNode().getCid());
	// MyTree<Epart> aa=new MyTree<Epart>();
	//				
	// if(mp2.getChildren()==null)
	// {
	// List<MyTree<Epart>> children=new ArrayList<MyTree<Epart>>();
	// aa.setNode(mp);
	// children.add(aa);
	// mp2.setChildren(children);
	// }else
	// {
	//					
	// mp2.getChildren().add(aa);
	// }
	//		
	//			
	// changetoMbom2(tmp,aa);
	//	
	//			
	// }
	// }
	//		
	// return null;
	//		
	// }

	public Part selectByPk(String pk) {
		return this.partDao.selectByPk(pk);
	}

	// public Epart selectByPk2(String pk) {
	// return this.epartDao.selectByPk(pk );
	// }
	// /**
	// * no useful
	// * @param pid
	// * @param map
	// * @param key
	// */
	//	
	// private void dd(String pid,Map<Integer, List<MPart>> map,int key)
	// {
	// MPart mp= this.mpartDao.selectByPk(pid);
	// if(map.get(key)==null||map.get(key).isEmpty())
	// {
	// List<MPart> ls=new ArrayList<MPart>();
	// ls.add(mp);
	// map.put(key, ls);
	// }else
	// {
	// map.get(key).add(mp);
	// }
	//		
	// List<MBom> mboms=this.mbomDao.selectChild(pid);
	// while(mboms!=null&&!mboms.isEmpty())
	// {
	// for(MBom item:mboms)
	// {
	// this.dd(item.getCid(),map,key);
	// }
	// key+=1;
	// }
	// }

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		PlmManager pm = (PlmManager) ac.getBean("plmManager");
		// pm.test();
	}

	// --------------------------------

	public Map<Integer, List<Bom>> getRs() {
		return rs;
	}

	public void setRs(Map<Integer, List<Bom>> rs) {
		this.rs = rs;
	}

	public BomDao getBomDao() {
		return bomDao;
	}

	public void setBomDao(BomDao bomDao) {
		this.bomDao = bomDao;
	}

	public PartDao getPartDao() {
		return partDao;
	}

	public void setPartDao(PartDao partDao) {
		this.partDao = partDao;
	}

}
