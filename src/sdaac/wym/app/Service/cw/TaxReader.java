package sdaac.wym.app.Service.cw;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import sdaac.wym.app.entity.cw.BuyCompany;
import sdaac.wym.app.entity.cw.SellCompnay;
import sdaac.wym.app.entity.cw.Tax;
import sdaac.wym.app.entity.cw.TaxItem;

import common.util.TxtReader;

public class TaxReader extends TxtReader {

	private int line=1;
	
	private int itemNum;
	private List<Tax> rs=new ArrayList<Tax>();
	private Tax tax;
	private BuyCompany buy;
	private SellCompnay sell;

	
	
	public TaxReader(InputStream pIs) {
		super(pIs);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	@Override
	protected void dealHandler(String resource) {
		// TODO Auto-generated method stub
		//System.out.println(line+":"+resource);
		if(line==1)
		{
			//System.out.println(line+":"+resource);
			//context=resource;
			String[] rows=resource.split("~~");
			itemNum=Integer.parseInt(rows[1]);
			//0076825534 2 上海通用汽车有限公司 310046607371748 上海浦东申江路1500号 (021)-28902890 工行浦东分大道分 1001181309016288873
			buy=new BuyCompany("",rows[2],rows[3],rows[4],rows[5]);
			// PO: JQ02763\n\nDelivery No: 0055960588    20090801 交通银行上海浦东分行 310066580018002229956 
			sell=new SellCompnay("","","",rows[11],rows[6]);
			tax=new Tax(rows[0],rows[10],buy,sell);
			
			//texts.add(context.split("~~"));
			line++;
		}
		else
		{
			//System.out.println(line+":"+resource);
			String[] rows=resource.split("~~");
			//软管总成~~PC~~5484337~~24.000000~~747.84~~0.17~~4001~~0~~127.13~~0~~0.000~~31.16000~~0
			TaxItem taxItem=new TaxItem(rows[0],rows[2],rows[1],rows[3],rows[11],rows[4],rows[5],rows[8],rows[6],rows[7],rows[9],rows[10],rows[12]);
			tax.getTaxItems().add(taxItem);
			line++;
			if(line==(itemNum+2))
			{
				//System.out.println(line+":"+resource);
				line=1;
				rs.add(tax);
			}
		}
		 
	}
		
	public List<Tax> getRs() {
		this.getContext();
		return rs;
	}



	
}
