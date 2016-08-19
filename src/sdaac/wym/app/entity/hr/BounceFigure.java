package sdaac.wym.app.entity.hr;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// default package

/**
 * BounceFigure entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BounceFigure implements java.io.Serializable {

	// Fields
	private int id;
	private String figureId;
	private String carType;
	private String productArea;
	private String remark;
	private Float standWorkTime; // 标准工时
	private String type;
	private String name;
	private String lineNO="3"; 		//车线
	// private Inbound inbound=new Inbound();//入库数

	// private Set<Inbound> inbounds=new HashSet<Inbound>();//入库数

	//private Set<Inbound> inbounds = new HashSet(0);
	private Long inboundNum=0l; // 入库数
	private Integer picesNum; // 次片数
	private Integer need; // 是否需要加工
	private float score; // 图号得分
	private float score2; // 图号得分
	private float realWorkTime;
//	private BigDecimal dScore;
//	private BigDecimal dstandWorkTime;
//	private BigDecimal drealWorkTime;
//    public Set getInbounds() {
//        return this.inbounds;
//    }
//    
//    public void setInbounds(Set inbounds) {
//        this.inbounds = inbounds;
//    }
	
//	public BigDecimal getDrealWorkTime() {
//		drealWorkTime.valueOf(realWorkTime);
//		return drealWorkTime;
//	}
//
//	public void setDrealWorkTime(BigDecimal drealWorkTime) {
//		this.drealWorkTime = drealWorkTime;
//	}
//
//	public BigDecimal getDScore() {
//		dScore.valueOf(score);
//		return dScore;
//	}
//
//	public void setDScore(BigDecimal score) {
//		dScore = score;
//	}

	public float getRealWorkTime() {
		return realWorkTime;
	}

	public void setRealWorkTime(float realWorkTime) {
		this.realWorkTime = realWorkTime;
	}

	/**
	 * 计算图号得分
	 * @param 实际工时
	 * @return
	 */

	public float getScore() {
		if(standWorkTime!=null)
		{
			return  standWorkTime * inboundNum / 3600/realWorkTime;
		}else{
			return 0;
		}
	}

	public void setScore(float score) {
		if (inboundNum != null && standWorkTime != null) {
			this.score = standWorkTime * inboundNum / 3600 / realWorkTime;
		} else
			this.score = 0;

	}

	public Long getInboundNum() {
	
		return inboundNum;
	}

	public Integer getPicesNum() {
		return picesNum;
	}

	public void setPicesNum(Integer picesNum) {
		this.picesNum = picesNum;
	}

	public Integer getNeed() {
		return need;
	}

	public void setNeed(Integer need) {
		this.need = need;
	}

	public Float getStandWorkTime() {
	//	DecimalFormat df=new DecimalFormat("0.00");
		//System.out.println(standWorkTime);
		return standWorkTime;
	
		//return standWorkTime;
	}

	public void setStandWorkTime(Float standWorkTime) {
		this.standWorkTime = standWorkTime;
	}

	/** default constructor */
	public BounceFigure() {
	}

	/** minimal constructor */
	public BounceFigure(String figureId) {
		this.figureId = figureId;
	}

	/** full constructor */
	public BounceFigure(String figureId, String carType, String productArea,
			String remark) {
		this.figureId = figureId;
		this.carType = carType;
		this.productArea = productArea;
		this.remark = remark;
	}

	// Property accessors

	public String getFigureId() {
		return this.figureId;
	}

	public void setFigureId(String figureId) {
		this.figureId = figureId;
	}

	public String getCarType() {
		return this.carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getProductArea() {
		return this.productArea;
	}

	public void setProductArea(String productArea) {
		this.productArea = productArea;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 得到总入库数
	 * 
	 * @param bfs
	 * @return
	 */
	public static Float getAllInputs(List<BounceFigure> bfs) {
		Float allInputs = 0f;
		for (BounceFigure item : bfs) {
			if (item.getInboundNum() != null) {
				allInputs += item.getInboundNum();
			}
		}
		return allInputs;
	}

	/**
	 * 得到总入库数
	 * 
	 * @param bfs
	 * @return
	 */
	public static Float getAllInputsPercent(List<BounceFigure> bfs) {
		Float allInputs = 0f;
		for (BounceFigure item : bfs) {
			if (item.getInboundNum() != null) {
				allInputs += item.getInboundNum() * item.getPicesNum();
			}
		}
		return allInputs;
	}

	/**
	 * 得到入库数比
	 * 
	 * @param bfs
	 * @return
	 */
	public Float getPercent(Float bfs) {
		if (inboundNum != null && picesNum != null&&bfs!=0) {
			return this.inboundNum / bfs;
		} else
			return 0f;

	}

	/**
	 * 得到入库数*翅片数比
	 * 
	 * @param bfs
	 * @return
	 */
	public Float getPercentpices(Float bfs) {
//		System.out.print(figureId+",");
//			System.out.print(this.inboundNum+",");
//			System.out.print(this.picesNum+",");
//			System.out.print(bfs);
//			System.out.println(this.inboundNum * this.picesNum / bfs);
	
//		if (inboundNum != null && picesNum != null&&bfs!=0) {
//			return this.inboundNum * this.picesNum / bfs;
//		} else
			return 0f;

	}

	public void setInboundNum(long inboundNum) {
		this.inboundNum = inboundNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLineNO() {
		return lineNO;
	}

	public void setLineNO(String lineNO) {
		this.lineNO = lineNO;
	}

	public float getScore2() {
		if(standWorkTime!=null)
		{
			return  standWorkTime * inboundNum / 3600;
		}else{
			return 0;
		}
	}

	public void setScore2(float score2) {
		this.score2 = score2;
	}

//	public BigDecimal getDstandWorkTime() {
//		dstandWorkTime.valueOf(standWorkTime);
//		return dstandWorkTime;
//	}
//
//	public void setDstandWorkTime(BigDecimal dstandWorkTime) {
//		this.dstandWorkTime = dstandWorkTime;
//	}



}