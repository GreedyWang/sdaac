package app.biz.impl.epor;

import common.dao.CommonDAO;

import app.biz.epor.PrFormStateBiz;
import app.entity.epor.PrPrFormState;

public class PrFormStateBizImpl implements PrFormStateBiz {

	private CommonDAO<PrPrFormState> prFormState=null;
	public CommonDAO<PrPrFormState> getPrFormState() {
		return prFormState;
	}
	public void setPrFormState(CommonDAO<PrPrFormState> prFormState) {
		this.prFormState = prFormState;
	}
	public void doInsert(PrPrFormState item) {
		// TODO Auto-generated method stub

	}

}
