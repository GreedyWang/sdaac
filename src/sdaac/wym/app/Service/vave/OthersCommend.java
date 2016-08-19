package sdaac.wym.app.Service.vave;

import java.util.List;

import sdaac.wym.app.entity.vave.Suggestion;

public class OthersCommend extends SuggestBiz {

	private List<Suggestion> otherSug;
	private Integer count;
	
	public void commend(List<Suggestion> otherSug,Integer count) {
	//	String[] rs=new String[count];
		if(otherSug.size()==count)
		{
			super.commend(otherSug.get(0));
		}
	}

	public void approve(Suggestion item) {
		// TODO Auto-generated method stub
		if(item.getSuggestionType().endsWith("批准"))
		{
			this.commend(otherSug,count);
		}else if(item.getSuggestionType().endsWith("合理化意见"))
		{
			this.Rationalization(item);
		}else if(item.getSuggestionType().endsWith("需完善")||(item.getSuggestionType().endsWith("不予批准")))
		{
			this.NeedtoImprove(item);
		}
	}

//	public List<Suggestion> getOtherSug() {
//		return otherSug;
//	}

	public void setOtherSug(List<Suggestion> otherSug) {
		this.otherSug = otherSug;
	}

//	public Integer getCount() {
//		return count;
//	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
