package common.util;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import common.Logger;

import sdaac.wym.app.entity.cw.Tax;
import sdaac.wym.app.entity.cw.Tax2;

public class CwTxtWrite extends TxtWrite {
	private List<Tax> taxs;
	private String text;
	private Tax2 tax2;
	private List<Tax2> tax2s;
	public String tip;
	public CwTxtWrite( String fileName,List<Tax> items,String firstRow) {
		super( fileName);
		taxs=items;
		text=firstRow;
		// TODO Auto-generated constructor stub
	}

	public CwTxtWrite( List<Tax2> pTax2s,String fileName,String firstRow) {
		super( fileName);
		tax2s=pTax2s;
		//taxs=items;
		text=firstRow;
		// TODO Auto-generated constructor stub
	}
	
	public CwTxtWrite( Tax2 pTax2,String fileName,List<Tax> pTaxs,String firstRow) {
		super( fileName);
		tax2=pTax2;
		taxs=pTaxs;
		text=firstRow;
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 合并2行内容
	 * @param one sap
	 * @param two jinsui
	 * @return
	 */
	private String changeContext(Tax one,String two){
		//String[] ones=one.split("~~");
		String[] twos=two.split("~~");
		StringBuffer result = new StringBuffer();
		for(int i=0;i<twos.length;i++){
			if(i==8){
				result.append(one.getNo()+"~~");
			}else if(i==9){
				result.append(1+"~~");
			}else{
				result.append(twos[i]+"~~");
			}			
		}
		return result.toString();
	}
	
	
	@Override
	public void write()   {
		// TODO Auto-generated method stub
		StringBuilder sb=new StringBuilder();
		int j=0,k=0;
		sb.append(text+"\r\n");
		//有几条发票
		for(Tax2 item: tax2s){			
			for(int i=0;i<item.getTax().size();i++){
				k++;
			}
		}
		String [] row2s =tax2s.get(0).getComment().split("~~");
		String row2=k+"~~"+row2s[1]+"~~"+row2s[2];
		sb.append(row2+"\r\n");
		for(Tax2 item: tax2s){			
			for(int i=0;i<item.getTax().size();i++){
				sb.append("//"+tip+(j+1)+"\r\n");
				sb.append(changeContext(item.getTax().get(i),item.getInfo())+"\r\n");
				j++;
			}
		}

		try {
			BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(fileName));
			String a=sb.toString();
			byte[] b=(a).getBytes();
			bos.write(b, 0, b.length);
			bos.close();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
	}
	
	/**
	 * OLD
	 */
	public void writeOld()   {
		// TODO Auto-generated method stub
		StringBuilder sb=new StringBuilder();
		int j=0;
		sb.append(text+"\r\n");
		
		String [] row2s =tax2.getComment().split("~~");
		String row2=taxs.size()+"~~"+row2s[1]+"~~"+row2s[2];
		sb.append(row2+"\r\n");
		for(Tax item: taxs){			
			
				sb.append("//发票"+(j+1)+"\r\n");
				sb.append(changeContext(item,tax2.getInfo())+"\r\n");
				j++;
			
		}

		try {
			BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(fileName));
			String a=sb.toString();
			byte[] b=(a).getBytes();
			bos.write(b, 0, b.length);
			bos.close();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
	}
	
	
	/**
	 * backup
	 */
	public void writeOtherType()   {
		// TODO Auto-generated method stub
		StringBuilder sb=new StringBuilder();
		sb.append(text+"\r\n");
		for(Tax item: taxs)
		{
			sb.append(item.toString());
		}
		try {
//			BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream("cccc.txt"));
			BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(fileName));
			//byte[] b=(sb.toString()).getBytes();
		//	byte[] b=new byte[2048];
			String a=sb.toString();
			byte[] b=(a).getBytes();
			bos.write(b, 0, b.length);
			bos.close();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		try {
			String s = new String("//发票".getBytes("iso-8859-1"), "utf-8");
			System.out.println();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
