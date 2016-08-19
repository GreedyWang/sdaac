package sdaac.wym.app.entity.vave;
/**
 * vave審批实体类
 * @author SA1KV5
 *
 */
public class ApproveRateItem {
	private String name;
	private int count;
	private int countOverC;
	private double avgApprove;
	private String vavePmName="";
	private String vavePm="";
	private int all = 0 ;
	private int all7;
	
	public int getAll() {
		return all;
	}

	public void setAll(int all) {
		this.all = all;
	}

	public int getAll7() {
		return all7;
	}

	public void setAll7(int all7) {
		this.all7 = all7;
	}

	public void addCountOverC(int pAccount){
		countOverC+=pAccount;
	}
	
	public void addAll(int pAccount){
		all+=pAccount;
	}
	
	public void addAll7(int pAccount){
		all7+=pAccount;
	}
	
	public void add(int pAccount){
		count+=pAccount;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCountOverC() {
		return countOverC;
	}
	public void setCountOverC(int countOverC) {
		this.countOverC = countOverC;
	}
	public double getAvgApprove() {
		if(all!=0){
			return all7/all;
		}else{
			return 0;
		}
		
	}
	public void setAvgApprove(double avgApprove) {
		this.avgApprove = avgApprove;
	}

	public String getVavePmName() {
		return vavePmName;
	}

	public void setVavePmName(String vavePmName) {
		this.vavePmName = vavePmName;
	}

	public String getVavePm() {
		return vavePm;
	}

	public void setVavePm(String vavePm) {
		this.vavePm = vavePm;
	}


}
