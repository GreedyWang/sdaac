package sdaac.wym.app.entity.hr;

// default package

import java.util.Date;

import common.util.MyUtil;

/**
 * Inbound entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Inbound implements java.io.Serializable {

	// Fields

	private Integer id;
	private String figureId;
	private Long inbound;
	private Date inboundTime;

	// Constructors

	/** default constructor */
	public Inbound() {
	}

	/** full constructor */
	public Inbound(String figureId, Long inbound, Date inboundTime) {
		this.figureId = figureId;
		this.inbound = inbound;
		this.inboundTime = inboundTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFigureId() {
		return this.figureId;
	}

	public void setFigureId(String figureId) {
		this.figureId = figureId;
	}

	public Long getInbound() {
		return this.inbound;
	}

	public void setInbound(Long inbound) {
		this.inbound = inbound;
	}

	public Date getInboundTime() {
		return this.inboundTime;
	}

	public void setInboundTime(String inboundTime) {
		
		this.inboundTime = MyUtil.formatDateNoDay(inboundTime);
	}

	public void setInboundTime(Date inboundTime) {
		this.inboundTime = inboundTime;
	}
	

}