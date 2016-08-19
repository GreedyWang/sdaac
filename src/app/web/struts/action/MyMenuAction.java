package app.web.struts.action;

/**
import com.Hibernate.SessionFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
public class MyMenuAction extends BaseAction { 
 private static final String role="admin";

 

  private void buildMenuPermissions(HttpServletRequest request) {
        PermissionsAdapter permession = new PermissionsAdapter() {
            public boolean isAllowed(MenuComponent menu) {
               if(role.equals("贾00")){
                return !"我".equalsIgnoreCase(menu.getName());
                }
             if(role.equals("我")){
              return !"贾00".equalsIgnoreCase(menu.getName());
             }             
             return true;
            }
        };
        request.setAttribute("examplesPermession", permession);
    }


 public ActionForward execute(
  ActionMapping mapping,
  ActionForm form,
  HttpServletRequest request,
  HttpServletResponse response) {  
	 //--------------------------------
	 
  //权限控制
  buildMenuPermissions(request);
  //创建连接
  Session session=SessionFactory.currentSession();
  //创建事务
  Transaction tx=session.beginTransaction();
  //创建对话
  Query query=session.createQuery("FROM MenuItem m order by id");
  List list=query.list();
  //事务提交
  tx.commit();
  //========================================dao!
  if(list.size()<0)
   return mapping.getInputForward();
  MenuRepository repository = new MenuRepository();
  HttpSession httpsession=(HttpSession)request.getSession();
  ServletContext application=(ServletContext)httpsession.getServletContext();
  MenuRepository defaultRepository = (MenuRepository)application.getAttribute(MenuRepository.MENU_REPOSITORY_KEY);
  repository.setDisplayers(defaultRepository.getDisplayers());
   for (int i=0; i < list.size(); i++) {
    MenuComponent mc = new MenuComponent();
    MenuItem mi=(MenuItem) list.get(i);
    String name = mi.getName();
            mc.setName(name);
            String parent = (String) mi.getParentName();
            System.out.println(name + ", parent is: " + parent);
            if (parent != null) {
                MenuComponent parentMenu = repository.getMenu(parent);
                if (parentMenu == null) {                    
                    parentMenu = new MenuComponent();
                    parentMenu.setName(parent);
                    repository.addMenu(parentMenu);
                }
                mc.setParent(parentMenu);
            }
            String title = (String)mi.getTitle();
            mc.setTitle(title);
            String location = (String) mi.getLocation();
            mc.setLocation(location);
            repository.addMenu(mc);
  }
   request.setAttribute("repository", repository);
  return mapping.findForward("okGo");
 }
 
  
  }
**/