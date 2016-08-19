package sdaac.wym.dms.entity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Indicate implements java.io.Serializable{
	
	private Integer id;
	private String type;
	private String typeName;
	private String remark;
	private String calc;
	private int worder;
	private int weight;
	private int state;
	private String name;
	//new add
	private int isScore;//是否显示得分
	private String area;
	private String group;
	private String groupID;
	
	public Indicate(int id){
		this.id=id;
	}
	public Indicate(){
		
	}
	public String getGroupID() {
		return groupID;
	}
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCalc() {
		return calc;
	}
	public void setCalc(String calc) {
		this.calc = calc;
	}
	public int getWorder() {
		return worder;
	}
	public void setWorder(int worder) {
		this.worder = worder;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getIsScore() {
		return isScore;
	}
	public void setIsScore(int isScore) {
		this.isScore = isScore;
	}
	
	/**
	 * //指标头 为了合并同类型的使用
	 * @param indicates
	 * @return
	 */
	public static Map<String, Integer> getTitle(List<Indicate> indicates){
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		for(Indicate item : indicates){
			if(map.containsKey(item.getTypeName())){
				Integer value = map.get(item.getTypeName());
				value++;
				map.put(item.getTypeName(), value);
				
			}else{
				//如果类型大于2 增加一列放系数
				if(Integer.parseInt(item.getType())>2){
					map.put(item.getTypeName(), 2);
				}else{
					map.put(item.getTypeName(), 1);
				}
			}
		}
		return map;
	}
	
}
