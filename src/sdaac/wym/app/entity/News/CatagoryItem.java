package sdaac.wym.app.entity.News;

import java.util.UUID;

/**
 * 树菜单 node 对象
 * @author SA1KV5
 *	
 */
public class CatagoryItem {
	
	private String isMenu;
	private String fileName;
	private String name;
	private String image;
	private String url;
	private String introduce;
	private String pid;
	private String method;
	private String id=UUID.randomUUID().toString();;
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public CatagoryItem() {
		super();
	}
	public CatagoryItem(String name, String image, String url, String introduce) {
		super();
		this.name = name;
		this.image = image;
		this.url = url;
		this.introduce = introduce;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getIsMenu() {
		return isMenu;
	}
	public void setIsMenu(String isMenu) {
		this.isMenu = isMenu;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
}
