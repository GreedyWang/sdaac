package sdaac.wym.app.Service.Room;
/**
 * 实体类： 配件
 * @author SA1KV5
 *
 */
public class Accessory {
	// Fields

	private Integer id;
	private String name;
	private String remark;
	private Integer state;
	private Integer type;
	public static int[] types={0,1};//0是电话，1是投影仪

	// Constructors

	/** default constructor */
	public Accessory() {
	}

	/** full constructor */
	public Accessory(String name, String remark, Integer state,
			Integer type) {
		this.name = name;
		this.remark = remark;
		this.state = state;
		this.type = type;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
