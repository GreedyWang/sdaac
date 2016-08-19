package app.entity.epor;

/**
 * PrPrFormState entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PrPrFormState implements java.io.Serializable {

	// Fields

	private Integer id;
	private PrPrForm prPrForm;
	private String ownerId;
	private Byte isOpen;
	private Byte isMail;

	// Constructors
	public void sendMail()
	{
		
	}
	/** default constructor */
	public PrPrFormState() {
	}

	/** full constructor */
	public PrPrFormState(Integer id, PrPrForm prPrForm, String ownerId,
			Byte isOpen, Byte isMail) {
		this.id = id;
		this.prPrForm = prPrForm;
		this.ownerId = ownerId;
		this.isOpen = isOpen;
		this.isMail = isMail;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PrPrForm getPrPrForm() {
		return this.prPrForm;
	}

	public void setPrPrForm(PrPrForm prPrForm) {
		this.prPrForm = prPrForm;
	}

	public String getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public Byte getIsOpen() {
		return this.isOpen;
	}

	public void setIsOpen(Byte isOpen) {
		this.isOpen = isOpen;
	}

	public Byte getIsMail() {
		return this.isMail;
	}

	public void setIsMail(Byte isMail) {
		this.isMail = isMail;
	}

}