package sdaac.wym.app.entity.plm;

import java.util.ArrayList;
import java.util.List;

/**
 * Ebom entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Ebom extends Bom implements java.io.Serializable {

	private Epart cpart = new Epart();
	private Epart fpart = new Epart();



	public Epart getCpart() {
		return cpart;
	}

	public void setCpart(Epart cpart) {
		this.cpart = cpart;
	}

	public Epart getFpart() {
		return fpart;
	}

	public void setFpart(Epart fpart) {
		this.fpart = fpart;
	}

	public Ebom() {
		// TODO Auto-generated constructor stub

	}

	public Ebom(String pid) {
		// TODO Auto-generated constructor stub
		this.fpart.setId(pid);
	}

	public static List<Bom> change(List<Ebom> item) {
		List<Bom> bom = new ArrayList<Bom>();
		for (Ebom mbom : item) {
			bom.add(mbom);
		}
		return bom;
	}
}