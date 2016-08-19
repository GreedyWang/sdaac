package sdaac.wym.app.Service.KPI;

import java.util.ArrayList;
import java.util.List;

import app.entity.TempIndex;
import app.entity.Tempolyee;
import app.entity.TindexTarget;

import common.util.ExcelReader;
import common.util.ExcelReader2007;

/**
 * read the KPI Excel for bulk update
 * @author SA1KV5
 *
 */
public class ExcelReader4PKI {
	private ExcelReader2007 reader = new ExcelReader2007(); 
	
	public List<TempIndex> doUpdate(String fileName){
		List<TempIndex> empIndexs=new ArrayList<TempIndex>();
		List<ArrayList<String>> rs = reader.read(fileName);
		int i = 0;
		String uname="",uid="",departmentName;
		boolean flag1=true,flag2=true;
		float weight=0f;
		//cols
		int sorce = 11;
		for(ArrayList<String> temp : rs) {
			if( flag1&&temp.get(3).trim().equals("Name:")){
				uname = temp.get(4).trim();
//				departmentName = temp.get(8);
				//check has uid 
				if(!temp.get(5).equals("")) {
					uid=temp.get(5).split(":")[1].trim();
				}
				flag1 = false;
			}
			if(temp.get(0).equals("B. Individual Target")) i=50;
			i++;
			if(i>52) {
				if(temp.get(6).equals("Sub-Total")) break;
				if(!temp.get(6).trim().equals("")){						 							
					 temp.get(6).replace("%", "");
					 weight = Float.parseFloat( temp.get(6))* 100;
//					 System.out.println("+"+weight+"+");
				 }
				//if weight small 0.01 than it's invalid index
				if(weight<0.01) continue;
				
					 TempIndex item=new TempIndex();
						//new index
						 TindexTarget index=new TindexTarget();
						 index.setA(temp.get(7));
						 index.setB(temp.get(8));
						 index.setC(temp.get(9));
						 index.setName(temp.get(3));
						 index.setFormula(temp.get(4));
						 index.setDepartID(3);
						 index.setIsChoice("必选");
						 index.setVersion("2009-07");
						 index.setType("非量化指标");
						 //new empIndex
						 item.setIndex(index);

							 item.setPercentage(weight);
							 weight=0f;
						
						 item.setAct_output(temp.get(10));
						 if(!temp.get(sorce).equals("") && !temp.get(sorce).equals("null")){	
							 	item.setScore(Float.parseFloat(temp.get(sorce)));
						 }
						 if(!uid.equals(""))uname="";
						 item.setTempolyee(new Tempolyee(uid,uname));	 
						 //add list
						 empIndexs.add(item);
			}
		}
		return empIndexs;
	}
	
	public static void  main(String[] args){
		ExcelReader4PKI m = new ExcelReader4PKI();
		List<TempIndex> rs = m.doUpdate("E:/KPI_Data/2011_KPI_PE_ge.yajing.xls");
		for(TempIndex item : rs) {
			System.out.print(item.getTempolyee().getName()+" ,");
			System.out.print(item.getIndex().getName()+" ,");
			System.out.print(item.getPercentage()+" ,");
			System.out.print(item.getIndex().getA()+" ,");
			System.out.print(item.getIndex().getB()+" ,");
			System.out.print(item.getIndex().getC()+" ,");
			System.out.print(item.getScore()+" ,");
			System.out.println();
		}
		
	}
}
