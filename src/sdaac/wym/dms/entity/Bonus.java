package sdaac.wym.dms.entity;
/**
 * 记录每月奖金总数
 * @author SA1KV5
 *
 */
public class Bonus {
	
	private String area;
	private String version;
	private float bonus;
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public float getBonus() {
		return bonus;
	}
	public void setBonus(float bonus) {
		this.bonus = bonus;
	} 
	
	
}
