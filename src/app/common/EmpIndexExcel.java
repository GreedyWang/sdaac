package app.common;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.format.Colour;
import jxl.write.Alignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

import app.entity.TcompanyIndex;
import app.entity.TempIndex;
import app.entity.Tempolyee;

import common.util.WritetoExcel;

public class EmpIndexExcel extends WritetoExcel {

	public EmpIndexExcel(String outputFile) throws Exception {
		super(outputFile);
		// TODO Auto-generated constructor stub
	}

	public void write(Map<Tempolyee, List<TempIndex>> aa,List<TcompanyIndex> bb)
	{
		Set<Tempolyee> keys=aa.keySet();
		for(Tempolyee key:keys)
		{
			Float result1=0f;
			Float result2=0f;
			createNewSheet(key.getName());
			String[] firstRow={"姓 名",key.getName()};
			creatNewRow(firstRow);
			String [] title={"名称","计算公式", "类型", "是否必选", "70%", "100%", "150%", "权重(100%)", "实际值", "得分" };
			creatNewRow(title);
			creatNewRow(new String[]{"公司指标"});
			for(TcompanyIndex target:bb)
			{
				String[] context={target.getTindexTarget().getName(),target.getTindexTarget().getFormula(),target.getTindexTarget().getType(),
		 			target.getTindexTarget().getIsChoice(),target.getTindexTarget().getA()+"",target.getTindexTarget().getB()+""
		 			,target.getTindexTarget().getC()+"",target.getPercentage()+"",target.getAct_output()+"",
		 			target.getScore()+""
 					};
				 creatNewRow(context);
				 if(target.getPercentage()!=null&&target.getScore()!=null)
				 {
					 result1+=target.getPercentage()*target.getScore()/10000;
				 }
			}
			creatNewRow(new String[]{"个人指标"});
			List<TempIndex> contextList=aa.get(key);
			for(TempIndex target:contextList)
			{
				 String[] context={target.getIndex().getName(),target.getIndex().getFormula(),target.getIndex().getType(),
						 			target.getIndex().getIsChoice(),target.getIndex().getA()+"",target.getIndex().getB()+""
						 			,target.getIndex().getC()+"",target.getPercentage()+"",target.getAct_output()+"",
						 			target.getScore()+""
				 					};
				 creatNewRow(context);
				 if(target.getPercentage()!=null&&target.getScore()!=null)
				 {
					 result2+=target.getPercentage()*target.getScore()/10000;
				 }
			 }
			String rs= this.jisuan(result1, result2);
			creatNewRow(new String[]{rs});
			close();
		}
	}
	
	private WritableFont wf;
	private WritableCellFormat wcfF;
	public void write2(Map<Tempolyee, List<TempIndex>> aa,Map<Integer,List<TcompanyIndex>> departIndexs) throws WriteException
	{
		Set<Tempolyee> keys=aa.keySet();
		for(Tempolyee key:keys)
		{	
			String [] title={"名称","计算公式", "类型", "是否必选", "70%", "100%", "150%", "权重(100%)", "实际值", "得分" };
			
			wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, true);
			wf.setItalic(false);	
			wcfF=new WritableCellFormat(wf);
			wcfF.setBackground(Colour.BLUE2);
			wcfF.setAlignment(Alignment.CENTRE);//单元格居中
			wcfF.setWrap(true); //自动换行
//			writeRow(title);
			wcfF = new WritableCellFormat();
			wcfF.setAlignment(Alignment.CENTRE);//单元格居中
			wcfF.setWrap(true); //自动换行
			//company index
			
			Float result1=0f;
			Float result2=0f;
			createNewSheet(key.getName());
			String[] firstRow={"姓 名",key.getName()};
			creatNewRow(firstRow);
			
			creatNewRow(title);
			creatNewRow(new String[]{"公司指标"});
			List<TcompanyIndex> bb=departIndexs.get(key.getTdepartment().getId());
			if(bb!=null) {
				for(TcompanyIndex target:bb)
				{
					String[] context={target.getTindexTarget().getName(),target.getTindexTarget().getFormula(),target.getTindexTarget().getType(),
			 			target.getTindexTarget().getIsChoice(),target.getTindexTarget().getA()+"",target.getTindexTarget().getB()+""
			 			,target.getTindexTarget().getC()+"",target.getPercentage()+"",target.getAct_output()+"",
			 			target.getScore()+""
	 					};
					 creatNewRow(context);
					 if(target.getPercentage()!=null&&target.getScore()!=null)
					 {
						 result1+=target.getPercentage()*target.getScore()/10000;
					 }
				}
			}
			
			creatNewRow(new String[]{"个人指标"});
			List<TempIndex> contextList=aa.get(key);
			for(TempIndex target:contextList)
			{
				 String[] context={target.getIndex().getName(),target.getIndex().getFormula(),target.getIndex().getType(),
						 			target.getIndex().getIsChoice(),target.getIndex().getA()+"",target.getIndex().getB()+""
						 			,target.getIndex().getC()+"",target.getPercentage()+"",target.getAct_output()+"",
						 			target.getScore()+""
				 					};
				 creatNewRow(context);
				 if(target.getPercentage()!=null&&target.getScore()!=null)
				 {
					 result2+=target.getPercentage()*target.getScore()/10000;
				 }
			 }
			String rs= this.jisuan(result1, result2);
			creatNewRow(new String[]{rs});
			close();
		}
	}
	private String jisuan(Float result1,Float result2)
	{
		Float result=0f;
		if(result2==0)
		{
			result=result1;
		}else{
			result=result1*result2;
		}
		 String score=result1+"*"+result2+"="+new DecimalFormat("#.00000").format(result);		
		 return score;
	}
}
