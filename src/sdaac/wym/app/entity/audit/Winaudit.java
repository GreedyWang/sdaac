package sdaac.wym.app.entity.audit;

import java.util.Date;

public class Winaudit {
	// Fields

	protected Integer recordId;

//	protected Integer auditId;

//	protected String userDb;

	protected Date dateTimeDb;

	protected String computer;

//	protected Short itemOrder;
	protected String start;
	protected String end;

	protected String category;

	protected String itemName;

	protected String itemValue1;

	protected String itemValue2;
	
	protected String operator; //比较符 》 《 =
	
	protected String unit;

//	protected String itemValue3;

//	protected String itemValue4;

//	protected String itemValue5;

	// Constructors

	public String getUnit() {
		return unit;
	}



	public void setUnit(String unit) {
		this.unit = unit;
	}



	/** default constructor */
	public Winaudit() {
	}

	

	// Property accessors

	public Winaudit(Integer recordId, Date dateTimeDb, String computer,
			String category, String itemName, String itemValue1,
			String itemValue2) {
		super();
		this.recordId = recordId;
		this.dateTimeDb = dateTimeDb;
		this.computer = computer;
		this.category = category;
		this.itemName = itemName;
		this.itemValue1 = itemValue1;
		this.itemValue2 = itemValue2;
	}



	public Integer getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}



	public Date getDateTimeDb() {
		return this.dateTimeDb;
	}

	public void setDateTimeDb(Date dateTimeDb) {
		this.dateTimeDb = dateTimeDb;
	}

	public String getComputer() {
		return this.computer;
	}

	public void setComputer(String computer) {
		this.computer = computer;
	}


	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemValue1() {
		return this.itemValue1;
	}

	public void setItemValue1(String itemValue1) {
		this.itemValue1 = itemValue1;
	}

	public String getItemValue2() {
		return this.itemValue2;
	}

	public void setItemValue2(String itemValue2) {
		this.itemValue2 = itemValue2;
	}



	public String getStart() {
		return start;
	}



	public void setStart(String start) {
		this.start = start;
	}



	public String getEnd() {
		return end;
	}



	public void setEnd(String end) {
		this.end = end;
	}



	public String getOperator() {
		return operator;
	}



	public void setOperator(String operator) {
		this.operator = operator;
	}

	
}
