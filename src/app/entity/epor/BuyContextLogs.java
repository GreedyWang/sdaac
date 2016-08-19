package app.entity.epor;

import java.util.Date;

public class BuyContextLogs extends BasePrBuyContext {
	
	private String operator;
	private Date operatorDate;
	
	
	public BuyContextLogs() {
		super();
	}

	public BuyContextLogs(PrBuyContext context,String uid ,Date pOperatorDate) {
		super(context.getId(), context.getPrPrForm().getId(), context.getDescription(), 
				context.getQuantity(), context.getUnit(),context.getExpectDeliveryDate(),
				context.getUnitPrice(),context.getAmount(), context.getCurrency(),
				context.getGlAccount(),context.getOrderNo(),
				context.getUnitPriceE(),context.getBuyerId());
		this.operator = uid;
		this.operatorDate = pOperatorDate;
		// TODO Auto-generated constructor stub
//		(Integer id, PrPrForm prPrForm, String description,
//				Integer quantity, String unit, Date expectDeliveryDate,
//				Float unitPrice, Float amount, String currency, String glAccount,
//				String orderNo,Float unitPriceE,String buyerId)
	}
	
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getOperatorDate() {
		return operatorDate;
	}
	public void setOperatorDate(Date operatorDate) {
		this.operatorDate = operatorDate;
	}
	
}
