package app.entity.vave;

import java.util.Date;
import app.entity.Tproposal;

/**
 * VaveCompanySuggestion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VaveCompanySuggestion implements java.io.Serializable {

	// Fields

	private Integer id;
	private Tproposal tproposal=new Tproposal();
	private String suggestionType;
	private Date suggestionDate;
	private String suggestionContext;

	// Constructors

	/** default constructor */
	public VaveCompanySuggestion() {
	}

	/** minimal constructor */
	public VaveCompanySuggestion(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public VaveCompanySuggestion(Integer id, Tproposal tproposal,
			String suggestionType, Date suggestionDate, String suggestionContext) {
		this.id = id;
		this.tproposal = tproposal;
		this.suggestionType = suggestionType;
		this.suggestionDate = suggestionDate;
		this.suggestionContext = suggestionContext;
	}

	// Property accessors


	public Tproposal getTproposal() {
		return this.tproposal;
	}

	public void setTproposal(Tproposal tproposal) {
		this.tproposal = tproposal;
	}

	public String getSuggestionType() {
		return this.suggestionType;
	}

	public void setSuggestionType(String suggestionType) {
		this.suggestionType = suggestionType;
	}



	public String getSuggestionContext() {
		return this.suggestionContext;
	}

	public void setSuggestionContext(String suggestionContext) {
		this.suggestionContext = suggestionContext;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getSuggestionDate() {
		return suggestionDate;
	}

	public void setSuggestionDate(Date suggestionDate) {
		this.suggestionDate = suggestionDate;
	}


}
	
	