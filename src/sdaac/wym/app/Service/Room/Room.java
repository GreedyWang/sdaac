package sdaac.wym.app.Service.Room;
/**
 * 实体类:会议室
 * @author SA1KV5
 *
 */
public class Room {
	private Integer id;
	private String name;
	private String remark;
	private String location;

	// Constructors

	/** default constructor */
	public Room() {
	}

	/** full constructor */
	public Room(String name, String remark, String location) {
		this.name = name;
		this.remark = remark;
		this.location = location;
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

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
