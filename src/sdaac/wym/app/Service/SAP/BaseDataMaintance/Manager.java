package sdaac.wym.app.Service.SAP.BaseDataMaintance;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Manager implements Imanager {

	private static String SUFFIX ="xls"; 
	
	@Override
	public void doUpload(BaseData data) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * check the data
	 */
	protected void doCheck() {
		//first check excel version
		checkFileType("");
	}
	
	private void checkFileType (String fileName) {
		if(fileName.split(".")[1].equals(SUFFIX)) {
						
		}
	}

	@Override
	public void doUpload(String url,String type) {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String toDay = df.format(new Date());
//		doCheck();
		if(type.equals("")) {
			StringBuffer sql = new StringBuffer("insert into customer(entrydate,CustNo,CustName,SearchTerm,City,Inco,allied,category,[Group])");
			sql.append(" SELECT '"+toDay+"',CustNo,CustName,SearchTerm,City,Inco,allied,category,[Group] FROM OPENROWSET('MICROSOFT.JET.OLEDB.4.0','Excel 5.0;HDR=YES;DATABASE="+url+"',sheet1$)");
			doJDBC(sql.toString());
		}

	}
	
	private void doJDBC(String sql) {
		 String RL = "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=hr1";  
		          String user ="bpp";//这里替换成你自已的数据库用户名  
		          String password = "bpp";//这里替换成你自已的数据库用户密码  
		          String sqlStr = sql;  
		  
		          try{     //这里的异常处理语句是必需的.否则不能通过编译!      
		              Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");  
//		              System.out.println( "类实例化成功!" );  
//		              System.out.println("slkdjf");  
		              Connection con = DriverManager.getConnection(RL,user,password);  
//		              System.out.println( "创建连接对像成功!" );  
		  
		              Statement st = con.createStatement();  
//		              System.out.println( "创建Statement成功!" );  
		              st.execute(sqlStr);
//		              st.executeQuery( sqlStr );  
//		              System.out.println( "操作数据表成功!" );  
//		              System.out.println( "----------------!" );  
		  
//		              while(rs.next())  
//		              {  
//		                  System.out.print(rs.getInt("Sno") + "     ");  
//		                  System.out.print(rs.getString("Sname") + "     ");  
//		                  System.out.print(rs.getInt("Sage") + "     ");  
//		                  System.out.print(rs.getString("Ssex") + "     ");  
//		                  System.out.println(rs.getString("Sclass"));  
//		              }  
//		              ResultSet rs =  rs.close();  
		              st.close();  
		              con.close();  
		          }  
		          catch(Exception err){  
		              err.printStackTrace(System.out);  
		          }  
		       
	}
	
	public static void main(String[] args) {
		Manager m = new Manager();
		String url ="E:\\Delete_src\\新建 Microsoft Excel Worksheet.xls";
		m.doUpload(url,"");
	}
}
