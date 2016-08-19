package common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;

import app.entity.TemployeeProduct;
import app.entity.Tempolyee;
import app.entity.Tpost;

public class ExcelReader {
	// 创建文件输入流
	private BufferedReader reader = null;

	private List<Object[]> list = new ArrayList<Object[]>();
	// 文件类型
	private String filetype;
	// 文件二进制输入流
	private InputStream is = null;
	// 当前的Sheet
	private int currSheet;
	// 当前位置
	private int currPosition = 1;
	// Sheet数量
	private int numOfSheets;

	private int maxRow;
	private String masterName;
	private Date date;

	private Object[] values;
	// HSSFWorkbook
	HSSFWorkbook workbook = null;
	// 设置Cell之间以空格分割
	private static String EXCEL_LINE_DELIMITER = " ";
	// 设置最大列数
	private static int MAX_EXCEL_COLUMNS = 64;
	
	public ExcelReader(){
		
	}
	
	public void init(InputStream is) throws IOException, Exception {
		workbook = new HSSFWorkbook(is);
		numOfSheets = workbook.getNumberOfSheets();
		// System.out.println("numOfSheets====>"+numOfSheets);
		for (int i = 0; i < numOfSheets; i++) {
			HSSFSheet sheet = workbook.getSheetAt(i);
			maxRow = sheet.getLastRowNum();
			System.out.println("maxRow====>" + maxRow);
			while (currPosition <= maxRow) {
				// System.out.println(currPosition);
				// TemployeeProduct item=new TemployeeProduct();
				HSSFRow rowline = sheet.getRow(currPosition);
				// System.out.println("rowline============>"+rowline);
				if (rowline != null) {
					paint(rowline);
				}
				currPosition++;
				list.add(values);

			}
			// System.out.println("");
		}
		currPosition = 0;
	}

	// 构造函数创建一个ExcelReader
	public ExcelReader(String inputfile) throws IOException, Exception {
		is = new FileInputStream(inputfile);
		init(is);
	}

	private void paint(HSSFRow rowline) {

		int filledColumns = rowline.getLastCellNum();
		// if(currPosition==0)
		values = new Object[20];
		for (int i = 0; i < filledColumns; i++) {
			HSSFCell cell = rowline.getCell((short) i);
			// String cellvalue = null;
			// System.out.println("========"+cell+"========");
			if (cell != null && !cell.equals("")) {
				// 判断当前Cell的Type
				switch (cell.getCellType()) {
				// 如果当前Cell的Type为NUMERIC
				case HSSFCell.CELL_TYPE_NUMERIC: {
					// 判断当前的cell是否为Date
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						// 如果是Date类型则，取得该Cell的Date值
						Date date = cell.getDateCellValue();
						// 把Date转换成本地格式的字符串
						values[i] = cell.getDateCellValue().toLocaleString();
					}
					// 如果是纯数字
					else {
						// 取得当前Cell的数值
						String tmp = "";
						Float num = new Float((float) cell
								.getNumericCellValue());
						// tmp
						if (i == 6) {
							if (num.intValue() < 1000) {
								tmp += "0";
								if (num.intValue() < 100)
									tmp += "0";
								if (num.intValue() < 10)
									tmp += "0";

								System.out.println(tmp);
								values[i] = tmp + String.valueOf(num);
							} else {
								values[i] = String.valueOf(num);
							}
						} else {
							values[i] = String.valueOf(num);
						}
						if (i == 5) {
							Float dd = new Float((float) cell
									.getNumericCellValue());
							values[i] = String.valueOf(dd);
						}
					}
					break;
				}
					// 如果当前Cell的Type为STRIN
				case HSSFCell.CELL_TYPE_STRING:
					// 取得当前的Cell字符串
					values[i] = cell.getStringCellValue().replaceAll("'", "''");
					break;
				// 默认的Cell值
				default:
					values[i] = "";
				}
			} else {
				values[i] = "";
			}
			// System.out.print(cellvalue);
		}
	}

	// close函数执行流的关闭操作
	public void close() {
		// 如果is不为空，则关闭InputSteam文件输入流
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				is = null;
			}
		}
		// 如果reader不为空则关闭BufferedReader文件输入流
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				reader = null;
			}
		}
	}

	public List<Object[]> getList() {
		return list;
	}


}