package common.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import java.io.FileOutputStream;
import java.io.IOException;

public class WritetoExcel {
	private HSSFWorkbook workbook;//工作本
	private HSSFSheet currSheet;// 当前表Sheet
	private HSSFRow currRow;//当前行Row
	private HSSFCell currCell;//当前单元格Cell
	//	 private HSSFDataFormat dataFormat;
	private HSSFCellStyle dateStyle;
	private String fileName;
	private String sheetName;
	HSSFCellStyle cellStyle;
	private static int MAX_EXCEL_COLUMNS = 256;//设置一个sheet最大列数
	private static int MAX_EXCEL_ROW = 100000;//设置一个sheet的最大行数
	private FileOutputStream os = null;// 文件二进制输出流
	//private int currPosition; // 当前位置   
	private int numOfSheets;// Sheet数量
	private short currNumOfColumns;//记录当前的列数
	private int currNumOfRow;//记录当前的行数

	//	public  void write(List<Object> list,String sheetName)
	//	{		
	//		createNewSheet(sheetName);
	//		setTitle("xxx");
	//		String[] context={"xiaoA","80","70"};
	//		creatNewRow(context);
	//		creatNewRow(context);
	//		close();
	//	}
	//	public static void main(String[] args) {
	//		try {
	//			WritetoExcel item=new WritetoExcel("D:\\doTest.xls");
	//			item.write(null, "doExam");
	//		} catch (Exception e) {
	//			// TODO 自动生成 catch 块
	//			e.printStackTrace();
	//		}
	//	}

	/**
	 * 构造函数
	 * @param outputFile 写入的文件名
	 * @throws IOException
	 */
	public WritetoExcel(String outputFile) throws Exception {
		if (outputFile == null || "".equals(outputFile)) {
			throw new Exception("请输入要写入的文件名");
		}
		//判断文件的后缀是否文xls,
		//	  String fileType = outputFile.substring(outputFile.lastIndexOf(".")+1,
		//	    outputFile.length());
		//	  if(fileType==null||!fileType.equalsIgnoreCase("xls")){
		//	   throw new Exception("文件后缀不支持");
		//	  }
		
       

		fileName = outputFile;
		workbook = new HSSFWorkbook();
		cellStyle=workbook.createCellStyle();       
		cellStyle.setWrapText(true); 
		currSheet = null;
		currRow = null;
		currCell = null;
		numOfSheets = 0;
		currNumOfRow = 0;
		currNumOfColumns = 0;
	}

	/**
	 * 创建一张新的Sheet工作表
	 */
	public void createNewSheet(String sheetName) {
		this.sheetName = sheetName;
		System.out.println("*"+sheetName+"*");
		currSheet = workbook.createSheet(sheetName + numOfSheets);
		numOfSheets++;//记录sheet的数量
		currNumOfRow = 0;
	}

	/**
	 * 设置sheet工作区的标题－－－－－设置标题属性部分未完成
	 * @param title
	 * @param height
	 * @param color
	 * @return
	 */
	public boolean setTitle(String title) {
		if (currNumOfRow + 1 > MAX_EXCEL_ROW) {//如果当前的行数超过规定的行数
			return false;
		}
		HSSFRow titleRow = currSheet.createRow(currNumOfRow++);//创建行对象
		//设置标题属性
		HSSFFont font = workbook.createFont();
		//	  font.setFontHeightInPoints(height);//字体大小
		font.setFontHeight((short) HSSFFont.BOLDWEIGHT_NORMAL);
		//	  font.setColor(color);//颜色
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		//创建单元格对象,写入标题
		HSSFCell titleCell = titleRow.createCell((short) 1);
		titleCell.setCellStyle(style);
		titleCell.setCellValue(new HSSFRichTextString(title));
		return true;
	}

	/**
	 * 设置sheet的表头
	 * @param field
	 * @param height
	 * @param color
	 * @return
	 */
	public boolean setField(String[] field, short height, short color) {
		if (field.length > MAX_EXCEL_COLUMNS)//如果超出了规定的列数
			return false;
		HSSFRow row = currSheet.createRow(currNumOfRow++);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);//表头样式
		HSSFFont font = workbook.createFont();//设置字体属性
		font.setFontHeightInPoints(height);
		font.setColor(color);
		style.setFont(font);
		style.setWrapText(true);
		for (short i = 0; i < field.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			cell.setCellValue(new HSSFRichTextString(field[i]));
		}
		return true;
	}

	/**
	 * 创建新的一行
	 * @return
	 */
	public boolean createNewRow() {
		if (currNumOfRow + 1 > MAX_EXCEL_ROW)//如果超出规定的行数最大值
			this.createNewSheet(sheetName + numOfSheets);
		currNumOfColumns = 0;
		currRow = currSheet.createRow(currNumOfRow++);
		return true;
	}

	public void setRowValue(String[] dates) {
		for (String date : dates) {
			this.createNewCell(date);
		}
	}

	/**
	 * 创建新的一行，并对其赋值
	 * @param cellStrings
	 * @return
	 */
	public boolean creatNewRow(String[] dates) {
		if (!this.createNewRow())
			return false;
		this.setRowValue(dates);
		return true;
	}

	/**
	 * 创建新的一个的单元格
	 * @return
	 */
	public boolean createNewCell() {
		if (currNumOfColumns + 1 > MAX_EXCEL_COLUMNS)//如果超出规定的列数最大值
			return false;
		currCell = currRow.createCell(currNumOfColumns++);
		return true;
	}

	/**
	 * 对当前的单元格cell赋值
	 * @param date
	 */
	public void setCellValue(String date) {
		currCell.setCellValue(new HSSFRichTextString(date));
	}

	/**
	 * 创建一个新的单元格，并对其赋值
	 * @param date
	 * @return
	 */
	public boolean createNewCell(String date) {
		if (!this.createNewCell())
			return false;
		currCell.setCellStyle(cellStyle);
		if (dateStyle != null)
			currCell.setCellStyle(dateStyle);
		this.setCellValue(date);
		return true;
	}

	/**
	 * 设置日期的显示格式
	 * @param format
	 */
	public void setCellDataFormat(String format) {
		if (dateStyle == null)
			dateStyle = workbook.createCellStyle();
		HSSFDataFormat dataFormat = workbook.createDataFormat();
		if ("".equals(format) || format == null)
			format = "yyyy-mm-dd";
		dateStyle.setDataFormat(dataFormat.getFormat(format));
	}

	public void close() {
		try {
			os = new FileOutputStream(fileName);
			workbook.write(os);
			if (os != null) {//关闭输出流POI不负责关闭
				os.close();
			}
		} catch (IOException e) {
			os = null;
			e.printStackTrace();
		}
	}
}
