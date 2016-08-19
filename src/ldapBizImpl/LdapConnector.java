package ldapBizImpl;

import java.io.IOException;
import java.util.Calendar;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import ldapBiz.ILdap;

public class LdapConnector implements ILdap {
	
	private Attributes attrs = null;
	private DirContext context = null;
	private String[] props = new String[5];
	private String uname = null;
	private String upass = null;
	private String OU = "";
	private String baseDN = "";
	private String url = "";// Delphi Domain

	public LdapConnector(String uname, String upass, String OU) {
		this.uname = uname;
		this.upass = upass;
		this.OU = OU;
	}

	/**
	 * 建立连接
	 */
	public DirContext getConnect() throws IOException, NamingException {
		/**
		 * 从配置文件读出ldap服务器地址 Properties props=new Properties(); FileInputStream
		 * in=null; try { in = new FileInputStream("c:/ldapServer.properties");
		 * } catch (FileNotFoundException e) { // TODO �Զ���� catch ��
		 * e.printStackTrace(); } props.load(in); in.close(); String
		 * username=props.getProperty("ldap.username"); String
		 * password=props.getProperty("ldap.password");
		 */
		// String url="ldap://sdaac.com:389"; //SDAAC domain
		Hashtable<String, String> env = new Hashtable<String, String>();
		if (OU.equals("ASIA")) {
			url = "ldap://asia.delphiauto.net:389";// Delphi Domain
			baseDN = "OU=People,DC=Asia,DC=DelphiAuto,DC=net";
			env.put(Context.SECURITY_PRINCIPAL, uname + "@asia.delphiauto.net");
		} else if (OU.equals("NA")) {
			url = "ldap://NorthAmerica.delphiauto.net:389";// Delphi Domain
			baseDN = "OU=People,DC=NorthAmerica,DC=DelphiAuto,DC=net";
			env.put(Context.SECURITY_PRINCIPAL, uname
					+ "@NorthAmerica.delphiauto.net");
		} else if (OU.equals("EUR")) {
			url = "ldap://Europe.delphiauto.net:389";// Delphi Domain
			baseDN = "OU=People,DC=Europe,DC=DelphiAuto,DC=net";
			env.put(Context.SECURITY_PRINCIPAL, uname
					+ "@Europe.delphiauto.net");
			// "CN=Gandon, Arnaud,OU=LUBAS,OU=People,DC=Europe,DC=DelphiAuto,DC=net"
		} else if (OU.equals("SDAAC")) {
			url = "ldap://sdaac.com:389";// Delphi Domain
			baseDN = "OU=People,DC=sdaac,DC=com";
			env.put(Context.SECURITY_PRINCIPAL, uname
					+ "@Europe.delphiauto.net");
			// "CN=Gandon, Arnaud,OU=LUBAS,OU=People,DC=Europe,DC=DelphiAuto,DC=net"
		}

		// env.put(Context.SECURITY_PRINCIPAL,uname+"@sdaac.com" );

		env.put(Context.SECURITY_CREDENTIALS, upass);
		DirContext inital = new InitialDirContext(env);
		context = (DirContext) inital.lookup(url);
		return context;
	}

	/**
	 * 查找每个登录者pager(工号字段)
	 * 
	 * @param 域帐号
	 */
	public String searchByFilter(String uid, String ou) {

		String empID = null;
		String filter = "sAMAccountName=" + uid;
		SearchResult entry = null;
		SearchControls cons = new SearchControls();
		cons.setSearchScope(SearchControls.SUBTREE_SCOPE);
		try {
			// DirContext cnt = getConnect();
			NamingEnumeration<SearchResult> ne = context.search(baseDN, filter,
					cons);

			for (; ne.hasMore();) {
				entry = ne.next();
				Attribute pager_attribute = entry.getAttributes().get(
						"sAMAccountName");// NetID
				if (pager_attribute != null && !pager_attribute.equals("")) {
					String pager = pager_attribute.toString();
					String[] uids = pager.split(": ");
					empID = uids[1];
					// user.setUid(empID);
				}
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return empID;
	}

	public static void main(String[] args) {
		long t1 = Calendar.MILLISECOND;
		LdapConnector t = new LdapConnector("", "", "ASIA");
		try {
			t.getConnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String rs = t.searchByFilter("", null);
		long t2 = Calendar.MILLISECOND;
		System.out.println("=======>TIME:" + (t2 - t1));
		System.out.println(rs);
	}

}
