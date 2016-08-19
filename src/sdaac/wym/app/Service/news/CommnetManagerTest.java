package sdaac.wym.app.Service.news;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.entity.Tempolyee;

import sdaac.wym.app.entity.News.Comments;
import sdaac.wym.app.entity.News.News;

import junit.framework.TestCase;

public class CommnetManagerTest extends TestCase {
	ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	CommnetManager cm=(CommnetManager)ac.getBean("commentManager");
	NewsManager nm=(NewsManager)ac.getBean("newsManager");
	News news=new News();
	Comments com1=new Comments();
	Comments com2=new Comments();
	public void init(){
		news.setTitle("测试");
		news.setReleaseTime(new Date());
		news.setReleaseEmployeeId(new Tempolyee("8002"));
		news.setContext("111112345678900123456789021345678923456789dfghjkl" +
				"fhnlsd;afhsdjlafjlskdafjldksfjlksdafjlsdfjdslafjdslfajlfdls");
		news.setType(1);
		news.setState(1);
		com1.setCommentsTime(new Date());
		com1.setContext("not  bad");
		com1.setNews(news);
		com2.setCommentsTime(new Date());
		com2.setContext("not  bad2");
		com2.setNews(news);
		
	}

	
	public void testRelease() {
		 init();
		 nm.release(news);
	}

	public void testSave() {
		fail("Not yet implemented");
	}

	public void testDeleten() {
		fail("Not yet implemented");
	}

	public void testModify() {
		fail("Not yet implemented");
	}

	public void testSearch() {
		fail("Not yet implemented");
	}

	public void testSearchImportantNews() {
		fail("Not yet implemented");
	}

	public void testSearchHotNews() {
		fail("Not yet implemented");
	}

	public void testShow() {
		fail("Not yet implemented");
	}
	
	//-----------------------------------------//
	public void testComment() {
		cm.comment(com1);
		fail("Not yet implemented");
	}

	public void testDelete() {
		fail("Not yet implemented");
	}

}
