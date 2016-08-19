package sdaac.wym.app.entity.plm;
// default package

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * MBom entity. @author MyEclipse Persistence Tools
 */

public class MBom extends Bom implements java.io.Serializable {

	private MPart cpart = new MPart();
	private MPart fpart = new MPart();
    
    public static List<Bom> change(List<MBom> item)
    {
 	   
    	List<Bom> bom=new ArrayList<Bom>();
 	   for(MBom mbom:item)
 	  {
 		   bom.add(mbom);
 	  }
 	   return bom;
    }

	public MPart getCpart() {
		return cpart;
	}

	public void setCpart(MPart cpart) {
		this.cpart = cpart;
	}

	public MPart getFpart() {
		return fpart;
	}

	public void setFpart(MPart fpart) {
		this.fpart = fpart;
	}


    
    






}