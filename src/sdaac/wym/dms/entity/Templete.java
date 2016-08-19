package sdaac.wym.dms.entity;

public class Templete {
	private String district;
	private Integer workgroup;
	private Indicate indicate = new Indicate();

	private int id;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getWorkgroup() {
		return workgroup;
	}
	public void setWorkgroup(Integer workgroup) {
		this.workgroup = workgroup;
	}
	public Indicate getIndicate() {
		return indicate;
	}
	public void setIndicate(Indicate indicate) {
		this.indicate = indicate;
	}

	
	
}
