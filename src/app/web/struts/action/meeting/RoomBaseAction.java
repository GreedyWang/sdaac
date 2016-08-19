package app.web.struts.action.meeting;

import org.springframework.web.struts.DispatchActionSupport;

import sdaac.wym.app.Service.Room.RoomManager;
import sdaac.wym.app.Service.Room.RoomRightsManager;


public class RoomBaseAction extends DispatchActionSupport{
	private RoomManager rsManager=null;
	private RoomRightsManager rightsManager=null;
	
	public RoomManager getRsManager() {
		rsManager=(RoomManager)this.getWebApplicationContext().getBean("RoomManager");
		return rsManager;
	}

	public RoomRightsManager getRightsManager() {
		rightsManager=(RoomRightsManager)this.getWebApplicationContext().getBean("RoomRightsManager");
		return rightsManager;
	}

	
	
}
