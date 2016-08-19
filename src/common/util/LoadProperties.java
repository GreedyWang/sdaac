package common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * 读取配置文件
 * 单例模式
 * 所有自己的配置文件在src/conf路径下
 * @author SA1KV5
 *
 */
public class LoadProperties  {

	private static LoadProperties instance ;
	private static Properties prop;
	
	private LoadProperties() {
		String PR_FILE_UPLOAD_ADD = "sdaac.properties"; 
		String DIR = "/conf/";
		prop = new Properties();
		String url = new MyUtil().getClass().getResource(DIR+PR_FILE_UPLOAD_ADD).toString();
		try {
			InputStream in = new FileInputStream(new File(url));	
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static LoadProperties newInstance(){
		if(instance == null){
			instance = new LoadProperties();
		}
		return instance;
	}	

	public String getValue(String key){
		return (String) prop.get(key);
	}
	
	//--------------------------------------
	public static String K_PR_UPLAODDIR = "PR_UPLAODDIR";
	
//	public static void main(String[] args) {
//		String filePath = LoadProperties.newInstance().getValue(LoadProperties.K_PR_UPLAODDIR);
//		System.out.println(filePath);
//	}
}	

