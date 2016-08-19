package app.entity;
/**
 * this is the TcompanyIndex and TempIndex 's father Class
 * @author SA1KV5
 *
 */
public class Index {
	private Integer id;

	private TindexTarget index=new TindexTarget();

	private Tdepartment tdepartment =new Tdepartment();

	private Float score;
	
	private Float percentage;
	
	private String act_output;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public TindexTarget getIndex() {
		return index;
	}

	public void setIndex(TindexTarget index) {
		this.index = index;
	}

	public Tdepartment getTdepartment() {
		return tdepartment;
	}

	public void setTdepartment(Tdepartment tdepartment) {
		this.tdepartment = tdepartment;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Float getPercentage() {
		return percentage;
	}

	public void setPercentage(Float percentage) {
		this.percentage = percentage;
	}

	public String getAct_output() {
		return act_output;
	}

	public void setAct_output(String act_output) {
		this.act_output = act_output;
	}
	/**
	 * TcompanyIndex Transform to index
	 * @param index
	 */
	public void copyValue(TcompanyIndex index){
		id=index.getId();
		this.index=index.getTindexTarget();
		tdepartment =index.getTdepartment();
		score=index.getScore();
		percentage=index.getPercentage();		
		act_output=index.getAct_output()+"";
	}
}
