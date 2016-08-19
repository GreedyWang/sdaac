package app.entity.epor;

import java.math.BigDecimal;

/**
 * 付款详细方式
 * @author SA1KV5
 *
 */
public class Paydetails {
	private int id;
	private String PRID;
	private String io;
	private float f_amount;
	private String f_date;
	private float a_amount;
	private String a_date;
	private int isPaied;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPRID() {
		return PRID;
	}
	public void setPRID(String prid) {
		PRID = prid;
	}
	public String getIo() {
		return io;
	}
	public void setIo(String io) {
		this.io = io;
	}
	public float getF_amount() {
		return f_amount;
	}
	public void setF_amount(float f_amount) {
		this.f_amount = f_amount;
	}
	public String getF_date() {
		return f_date;
	}
	public void setF_date(String f_date) {
		this.f_date = f_date;
	}
	public float getA_amount() {
		return a_amount;
	}
	public void setA_amount(float a_amount) {
		this.a_amount = a_amount;
	}
	public String getA_date() {
		return a_date;
	}
	public void setA_date(String a_date) {
		this.a_date = a_date;
	}
	public int getIsPaied() {
		return isPaied;
	}
	public void setIsPaied(int isPaied) {
		this.isPaied = isPaied;
	}
}
