package app.common;

import java.io.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;
import app.entity.Index;
import app.entity.TcompanyIndex;
import app.entity.TempIndex;
import app.entity.Tempolyee;
import jxl.*;
import jxl.format.Colour;
import jxl.format.PageOrientation;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
/**
 * 员工kpi导出模板
 * @author wangyongmin
 *
 */
public class CreateEmpIndexExcel {
	//private String flieName;
	private WritableWorkbook book;//Excel 
	private WritableSheet sheet; //Sheet
	private WritableFont wf;
	private WritableCellFormat wcfF;
	private Label labelC;
	private int ROW_INDEX=5;
	private int SHEET_INDEX=0;
	private Float result1=0f;//company index score
	private Float result2=0f;//self index score
	private String[] title={"No.","Category","关键绩效指标 KPI","指标计算公式 Formula","备注 Remark","数据来源Dara Form", "权重Weight","70%", "100%", "150%", "达成指标 Result", "Score" };
	
	public CreateEmpIndexExcel(String fileName) throws IOException{
		 book = Workbook.createWorkbook(new File(fileName));
	}
	
	private void writeRow(String[] context){
		for(int i=0;i<context.length;i++){
			wirteCell(new Label(i+1,ROW_INDEX,context[i],wcfF));	
		}
		ROW_INDEX++;
	}
	
	/**
	 * 写excel
	 * @param aa
	 * @param bb
	 * @throws WriteException 
	 */
	public void write(Map<Tempolyee, List<TempIndex>> aa,List<TcompanyIndex> bb) throws WriteException{	
		Set<Tempolyee> keys=aa.keySet();
		for(Tempolyee key:keys)
		{			
			createNewSheet(key.getName(),SHEET_INDEX++,key);		
			wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, true);
			wf.setItalic(false);	
			wcfF=new WritableCellFormat(wf);
			wcfF.setBackground(Colour.BLUE2);
			wcfF.setAlignment(Alignment.CENTRE);//单元格居中
			wcfF.setWrap(true); //自动换行
			writeRow(title);
			wcfF = new WritableCellFormat();
			wcfF.setAlignment(Alignment.CENTRE);//单元格居中
			wcfF.setWrap(true); //自动换行
			//company index
			float percentage=0;
			int i=1;
			for(TcompanyIndex target:bb)
			{
				Index index=new Index();
				index.copyValue(target);
				percentage=printContext(index,i++,percentage,1);			
			}
			printWeight(percentage);
			wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, true);
			wf.setItalic(false);	
			wcfF=new WritableCellFormat(wf);
			wcfF.setAlignment(Alignment.CENTRE);//单元格居中
			wcfF.setWrap(true); //自动换行
			wirteCell(new Label(0,++ROW_INDEX,"B. Individual Target",wcfF));
			wirteCell(new Label(9,++ROW_INDEX,"设定目标 Target Setting",wcfF));
			writeRow(title);
			wcfF = new WritableCellFormat();
			wcfF.setAlignment(Alignment.CENTRE);//单元格居中
			wcfF.setWrap(true); //自动换行

			List<TempIndex> contextList=aa.get(key);
			//self index
			percentage=0;
			i=1;
			for(TempIndex target:contextList)
			{
				percentage=printContext(target,i++,percentage,2); 				
			}
			printWeight(percentage);
			String rs = this.getScore(result1, result2);
			wirteCell(new Label(10,ROW_INDEX++,"最后考核得分(Final Score)"+rs));
			printEnd();
			
		}
		close();
	}
	
	
	public void write2(Map<Tempolyee, List<TempIndex>> aa,Map<Integer,List<TcompanyIndex>> departIndexs) throws WriteException
	{
		Set<Tempolyee> keys=aa.keySet();
		for(Tempolyee key:keys)
		{	
//			String [] title={"名称","计算公式", "类型", "是否必选", "70%", "100%", "150%", "权重(100%)", "实际值", "得分" };
			createNewSheet(key.getName()+SHEET_INDEX,SHEET_INDEX++,key);	
			wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, true);
			wf.setItalic(false);	
			wcfF=new WritableCellFormat(wf);
			wcfF.setBackground(Colour.BLUE2);
			wcfF.setAlignment(Alignment.CENTRE);//单元格居中
			wcfF.setWrap(true); //自动换行
			writeRow(title);
			wcfF = new WritableCellFormat();
			wcfF.setAlignment(Alignment.CENTRE);//单元格居中
			wcfF.setWrap(true); //自动换行
			//company index
			float percentage=0;
			int i=1;
			List<TcompanyIndex> bb=departIndexs.get(key.getTdepartment().getId());
			if(bb!=null){
				for(TcompanyIndex target:bb)
				{
					Index index=new Index();
					index.copyValue(target);
					percentage=printContext(index,i++,percentage,1);			
				}
				
			}
			printWeight(percentage);
//			Float result1=0f;
//			Float result2=0f;
//			createNewSheet(key.getName(),SHEET_INDEX++,key);
//			createNewSheet(key.getName());
//			String[] firstRow={"姓 名",key.getName()};
//			writeRow(firstRow);
//			
//			writeRow(title);
//			writeRow(new String[]{"公司指标"});
			
//			if(bb!=null) {
//				for(TcompanyIndex target:bb)
//				{
//					String[] context={target.getTindexTarget().getName(),target.getTindexTarget().getFormula(),target.getTindexTarget().getType(),
//			 			target.getTindexTarget().getIsChoice(),target.getTindexTarget().getA()+"",target.getTindexTarget().getB()+""
//			 			,target.getTindexTarget().getC()+"",target.getPercentage()+"",target.getAct_output()+"",
//			 			target.getScore()+""
//	 					};
//					writeRow(context);
//					 if(target.getPercentage()!=null&&target.getScore()!=null)
//					 {
//						 result1+=target.getPercentage()*target.getScore()/10000;
//					 }
//				}
//			}
			
//			writeRow(new String[]{"个人指标"});
			
			wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, true);
			wf.setItalic(false);	
			wcfF=new WritableCellFormat(wf);
			wcfF.setAlignment(Alignment.CENTRE);//单元格居中
			wcfF.setWrap(true); //自动换行
			wirteCell(new Label(0,++ROW_INDEX,"B. Individual Target",wcfF));
			wirteCell(new Label(9,++ROW_INDEX,"设定目标 Target Setting",wcfF));
			writeRow(title);
			wcfF = new WritableCellFormat();
			wcfF.setAlignment(Alignment.CENTRE);//单元格居中
			wcfF.setWrap(true); //自动换行
			
			List<TempIndex> contextList=aa.get(key);
//			for(TempIndex target:contextList)
//			{
//				 String[] context={target.getIndex().getName(),target.getIndex().getFormula(),target.getIndex().getType(),
//						 			target.getIndex().getIsChoice(),target.getIndex().getA()+"",target.getIndex().getB()+""
//						 			,target.getIndex().getC()+"",target.getPercentage()+"",target.getAct_output()+"",
//						 			target.getScore()+""
//				 					};
//				 writeRow(context);
//				 if(target.getPercentage()!=null&&target.getScore()!=null)
//				 {
//					 result2+=target.getPercentage()*target.getScore()/10000;
//				 }
//			 }
			//self index
			percentage=0;
			i=1;
			for(TempIndex target:contextList)
			{
				percentage=printContext(target,i++,percentage,2); 				
			}
			printWeight(percentage);
			String rs = this.getScore(result1, result2);
//			writeRow(new String[]{rs});
//			printWeight(percentage);
//			String rs = this.getScore(result1, result2);
			wirteCell(new Label(10,ROW_INDEX++,"最后考核得分(Final Score)"+rs));
			printEnd();
		}
		close();
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
	
	
	private void printWeight(float percentage){
		try {
			wcfF = new WritableCellFormat();
			wcfF.setBackground(Colour.GRAY_50);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=2;i<6;i++){
			wirteCell(new Label(i,ROW_INDEX," ",wcfF));
		}
		wirteCell(new Label(6,ROW_INDEX,"Sub-Total",wcfF));
		wirteCell(new Label(7,ROW_INDEX++,percentage+"%"));
	}
	
	/**
	 * input the data
	 * @param target
	 * @param i
	 * @param percentage
	 * @return
	 */
	private float printContext(Index target,int i,float percentage,int flag){
		//Float.parseFloat(target.getAct_output());
//		System.out.println(Float.parseFloat(target.getAct_output()));
//		System.out.println(new DecimalFormat("#.000").format(Float.parseFloat(target.getAct_output())));
		String act_output = new DecimalFormat("#.000").format(Float.parseFloat(target.getAct_output())).toString();
//		System.out.println("init");
			String[] context={i+"","",target.getIndex().getName(),target.getIndex().getFormula(),"","",target.getPercentage()+"",target.getIndex().getA()+"",target.getIndex().getB()+""
	 			,target.getIndex().getC()+"",act_output,
	 			target.getScore()+""
					};
		//	System.out.println(target.getIndex().getId());
			if(target!=null&&target.getPercentage()!=null){
				 percentage+=target.getPercentage();
			}			
			 writeRow(context);		
			 if(target.getPercentage()!=null&&target.getScore()!=null)
			 {
				 if(flag==1){
					 result1+=target.getPercentage()*target.getScore()/10000;
				 }else if(flag==2){
					 result2+=target.getPercentage()*target.getScore()/10000;
				 }		 
			 }
			 return percentage;
	}
	
	/**
	 * 新建一个SHEET
	 * @param sheetName
	 * @param index
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	private void createNewSheet(String sheetName,int index,Tempolyee empolyee) {
		 sheet = book.createSheet(sheetName, index);
		 sheet.getSettings().setScaleFactor(60);
		 sheet.getSettings().setOrientation(PageOrientation.LANDSCAPE);
		 sheet.getSettings().setTopMargin(0);
		 sheet.setColumnView(0, 4);//设置第一列的宽度为4 
		 sheet.setColumnView(1, 6);//设置第一列的宽度为4
		 sheet.setColumnView(2, 12);//设置第一列的宽度为4
		 sheet.setColumnView(3, 36);//设置第一列的宽度为4
		 sheet.setColumnView(4, 18);//设置第一列的宽度为4
		 sheet.setColumnView(5, 12);//设置第一列的宽度为4
		 sheet.setColumnView(6, 16);//设置第一列的宽度为4
		 sheet.setColumnView(7, 10);//设置第一列的宽度为4
		 sheet.setColumnView(8, 15);//设置第一列的宽度为4
		 sheet.setColumnView(9, 15);//设置第一列的宽度为4
		 sheet.setColumnView(10, 15);//设置第一列的宽度为4
		 sheet.setColumnView(11, 20);//设置第一列的宽度为4
		 sheet.setColumnView(12, 15);//设置第一列的宽度为4
		
		
		//第一行
		 wf=new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, true);
		 wcfF = new jxl.write.WritableCellFormat(wf);
	

		 wirteCell(new jxl.write.Label(0, 0, "SDAAC度激励奖协议(SDAAC Annual Incentive Bonus Agreement)",wcfF)); 
		// sheet.addCell(labelC); 
		 try {
			sheet.mergeCells(0, 0, 6, 0);
			sheet.mergeCells(0, 10, 3, 10);
			wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, true);
			wf.setItalic(false);
			wcfF=new WritableCellFormat(wf);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //第二行
		 
		 wirteCell(new Label(3,1,"Name:",wcfF));
		 wirteCell(new Label(4,1,empolyee.getName(),wcfF));
		 wirteCell(new Label(8,1,"Dept:",wcfF));
		 wirteCell(new Label(9,1,empolyee.getTdepartment().getName(),wcfF));
		 //第三行
		 wirteCell(new Label(3,3,"Position Title:",wcfF));
		 wirteCell(new Label(4,3,"",wcfF));
		 wirteCell(new Label(8,3,"Report To:",wcfF));
		 wirteCell(new Label(9,3,"",wcfF));
		//第四行
		 wirteCell(new Label(0,4,"A. Company Target",wcfF));
		 
	}
	/**
	 * print sheet end
	 * initial parameters
	 */
	private void printEnd(){
		 wirteCell(new Label(3,ROW_INDEX,"Employee:"));
		 wirteCell(new Label(8,ROW_INDEX++,"Mgr:"));
		 ROW_INDEX++;
		 wirteCell(new Label(3,ROW_INDEX,"Date:"));
		 wirteCell(new Label(8,ROW_INDEX,"Date:"));
		 ROW_INDEX=5;
		 result1=0f;//company index score
		 result2=0f;//self index score
	}
	
	/**
	 * write to one cell
	 * @param lable
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	private void wirteCell(Label lable) {
		 try {
			sheet.addCell(lable);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * Calculation the Indicators reslut
	 * @param result1
	 * @param result2
	 * @return
	 */
	private String getScore(Float result1,Float result2)
	{
		Float result=0f;
		if(result2==0)
		{
			result=result1;
		}else{
			result=result1*result2;
		}
		if(result > 2) result = 2f;
		 String score=result1+"*"+new DecimalFormat("#.00000").format(result2)+"="+new DecimalFormat("#.00000").format(result);
		 return score;
	}
	
	/**
	 * write to the file
	 * close io 
	 */
	private void close(){
		try {
			book.write();
			book.close();		 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
