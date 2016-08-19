package sdaac.wym.app.Service.cw;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import sdaac.wym.app.entity.cw.BuyCompany;
import sdaac.wym.app.entity.cw.SellCompnay;
import sdaac.wym.app.entity.cw.Tax;
import sdaac.wym.app.entity.cw.Tax2;
import sdaac.wym.app.entity.cw.TaxItem;
import common.util.TxtReader;
/**
 * 发票参照excel
 * @author SA1KV5
 *
 */
public class Tax2Reader extends TxtReader {

	private String comment;
	private String info;
	private String no;
	private int flag=0; //跳过几行
	private int line=0;
	private List<Tax2> rs = new ArrayList<Tax2>();
	private Tax2 tax2;
	
	public Tax2Reader(InputStream is) {
		super(is);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void dealHandler(String resource) {
		// TODO Auto-generated method stub
		if(line==0){
			comment=resource;
		}
		else if(line==2)
		{
			info=resource;
			String[] rows=resource.split("~~");
			flag=Integer.parseInt(rows[5]);
			no=rows[8];
			//texts.add(context.split("~~"));
			
		}else{
			if(line==(flag+2))
			{
				line=0;
				tax2=new Tax2();
				tax2.setComment(comment);
				tax2.setInfo(info);
				tax2.setNo(no);
				rs.add(tax2);
			}
		}
		line++;
	}
	
	public List<Tax2> getRs() {
		this.getContext();
		return rs;
	}
}
