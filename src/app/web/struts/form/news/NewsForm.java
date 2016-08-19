package app.web.struts.form.news;

import org.apache.struts.action.ActionForm;

import sdaac.wym.app.entity.News.News;

public class NewsForm extends ActionForm {

	private News item = new News();

	public News getItem() {
		return item;
	}

	public void setItem(News item) {
		this.item = item;
	}
	
	
}
