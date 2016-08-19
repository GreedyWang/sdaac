package common.util.io;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
/**
 * PCL每周1实际收货数据
 * @author SA1KV5
 *
 */
public class PostingDateExcelReaderQin extends ExcelReader2007 {
	private static int FIRST_LINE = 1;//开始行
	private static short[] ROWS = {24,16};//读取列 -> 起始坐标(1,24),(1,16)
	private boolean flag = true;//是否有重复
	@Override
	protected List<ArrayList<String>> read(Workbook wb) {
		// TODO Auto-generated method stub
		 List<ArrayList<String>> dataLst = new ArrayList<ArrayList<String>>();
        
        /** *//** 得到第一个shell */
        Sheet sheet = wb.getSheetAt(0);
        this.totalRows = sheet.getPhysicalNumberOfRows();
        if (this.totalRows >= 1 && sheet.getRow(0) != null)
        {
            this.totalCells = 20;//sheet.getRow(0).getPhysicalNumberOfCells();
        }
        
        /** *//** 循环Excel的行 */
        for (int r = FIRST_LINE; r < this.totalRows; r++)
        {
            Row row = sheet.getRow(r);
            if (row == null)
            {
                continue;
            }
            
            ArrayList<String> rowLst = new ArrayList<String>();
            /** *//** 循环Excel的列 */           
            for (short c :ROWS)
            {
                Cell cell = row.getCell(c);
                String cellValue = "";
                if (cell == null)
                {
                    rowLst.add(cellValue);
                    continue;
                }
                
                /** *//** 处理数字型的,自动去零 */
                if (Cell.CELL_TYPE_NUMERIC == cell.getCellType())
                {
                    /** *//** 在excel里,日期也是数字,在此要进行判断 */
                    if (HSSFDateUtil.isCellDateFormatted(cell))
                    {
                        cellValue = cell.getDateCellValue().toString();
                    }
                    else
                    {
                        cellValue = getRightStr(cell.getNumericCellValue() + "");
                    }
                }
                /** *//** 处理字符串型 */
                else if (Cell.CELL_TYPE_STRING == cell.getCellType())
                {
                    cellValue = cell.getStringCellValue();
                }
                /** *//** 处理布尔型 */
                else if (Cell.CELL_TYPE_BOOLEAN == cell.getCellType())
                {
                    cellValue = cell.getBooleanCellValue() + "";
                }
                /** *//** 其它的,非以上几种数据类型 */
                else
                {
                    cellValue = cell.toString() + "";
                }

                rowLst.add(cellValue);
            }
            //去除重复项
            for(ArrayList<String> item :dataLst){
            	if(item.get(1).equals(rowLst.get(1))){
            		flag = false;
            		item.set(0, rowLst.get(0));
            	}
            }
            if(flag){
            	dataLst.add(rowLst);
            }
            flag = true;
           
        }
        System.out.println(dataLst.size());;
        return dataLst;
	}		

}