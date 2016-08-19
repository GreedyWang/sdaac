package ldapBizImpl;

import java.util.Properties;    

import javax.naming.*;    
import javax.naming.ldap.*;    
import javax.naming.directory.*;    
   
/**   
 *你AD Server的SSL配置没配好吧，netstat查看有没有打开636端口号
 *    修改AD的用户密码，有几点注意事项：1、AD域控上启用证书服务；2、客户端使用keytool导入域控的证书；3、域安全策略相关配置；4、修改密码使用remove/add操作；5、重置密码使用replace操作；6、密码必须是unicode编码；7、使用SSL端口连接AD

以下是调试过程中遇到的错误与处理方式：
1、sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target
表示证书未导入。由于使用的是SUN Glassfish，trusted keystore在D:\Sun\SDK\domains\domain1\config\cacerts.jks，使用keytool -import -keystore store_file_name -file cert_file_name导入，并重启glassfish后，这个问题解决。默认keystore在JDK的jre/lib/security/cacerts里

2、javax.naming.NameNotFoundException: [LDAP: error code 32 - 0000208D: NameErr: DSID-031001CD, problem 2001 (NO_OBJECT)
这个是由于contextSource.setBase后，所有的操作都是基于这个base操作的，后面的DN要做相应的修改

3、javax.naming.NoPermissionException: [LDAP: error code 50 - 00000005: SecErr: DSID-031A0F44, problem 4003 (INSUFF_ACCESS_RIGHTS), data 0
这个是最初代码使用的replace操作，这个在AD里对应的是密码重设（普通用户默认没有这个权限，管理员可以操作），另外remove操作时提供的旧密码错误也可能报这个异常

4、javax.naming.AuthenticationException: [LDAP: error code 49 - 80090308: LdapErr: DSID-0C090334, comment: AcceptSecurityContext error, data 52e, vece
contextSource.setPassword设置了错误的密码

5、javax.naming.directory.InvalidAttributeValueException: [LDAP: error code 19 - 00000056: AtrErr: DSID-03190F00, #1:
0: 00000056: DSID-03190F00, problem 1005 (CONSTRAINT_ATT_TYPE), data 0, Att 9005a (unicodePwd)
这个最大的可能是不满足域安全策略：如密码复杂性、密码最短使用期限、强制密码历史。即长度、包含的字符、多久可以修改密码、是否可以使用历史密码等。

6、javax.naming.OperationNotSupportedException: [LDAP: error code 53 - 0000052D: SvcErr: DSID-031A0FC0, problem 5003 (WILL_NOT_PERFORM), data 0
这个错不记得什么原因导致了，估计也与安全策略有关

7、java.lang.NullPointerException at org.springframework.ldap.core.support.AbstractContextSource.getReadWriteContext(AbstractContextSource.java:138)
这个是创建contextSource后未调用contextSource.afterPropertiesSet()导致

MS的参考http://support.microsoft.com/kb/269190/zh-cn

 */   
public class AddAdUser {    
    private static final String SUN_JNDI_PROVIDER = "com.sun.jndi.ldap.LdapCtxFactory";    
   
    public static void main(String[] args) throws Exception {    
        String keystore = "F:\\jdk1.5.0_08\\jre\\lib\\security\\cacerts";    
        System.setProperty("javax.net.ssl.trustStore", keystore);    
   
        Properties env = new Properties();    
   
        env.put(Context.INITIAL_CONTEXT_FACTORY, SUN_JNDI_PROVIDER);// java.naming.factory.initial    
        env.put(Context.PROVIDER_URL, "ldap://sdaac.com:389");// java.naming.provider.url    
        env.put(Context.SECURITY_AUTHENTICATION, "simple");// java.naming.security.authentication    
        env.put(Context.SECURITY_PRINCIPAL,    
                "cn=Administrator,cn=Users,dc=comwave,dc=com");// java.naming.security.principal    
        env.put(Context.SECURITY_CREDENTIALS, "password");// java.naming.security.credentials    
        env.put(Context.SECURITY_PROTOCOL, "ssl");    
   
        String userName = "ou=Shanghai,dc=sdaac,dc=com";    
        String groupName = "CN=Domain Admins,CN=Users,DC=comwave,DC=com";    
   
        LdapContext ctx = new InitialLdapContext(env, null);    
   
        // Create attributes to be associated with the new user    
        Attributes attrs = new BasicAttributes(true);    
   
        // These are the mandatory attributes for a user object    
        // Note that Win2K3 will automagically create a random    
        // samAccountName if it is not present. (Win2K does not)    
      
   
        // These are some optional (but useful) attributes    
      
        attrs.put("mail", "test@comwave.com");    
   
   
        // some useful constants from lmaccess.h    
        int UF_ACCOUNTDISABLE = 0x0002;    
        int UF_PASSWD_NOTREQD = 0x0020;    
        int UF_PASSWD_CANT_CHANGE = 0x0040;    
        int UF_NORMAL_ACCOUNT = 0x0200;    
        int UF_DONT_EXPIRE_PASSWD = 0x10000;    
        int UF_PASSWORD_EXPIRED = 0x800000;    
   
        // Note that you need to create the user object before you can    
        // set the password. Therefore as the user is created with no    
        // password, user AccountControl must be set to the following    
        // otherwise the Win2K3 password filter will return error 53    
        // unwilling to perform.    
   
        attrs.put("userAccountControl", Integer.toString(UF_NORMAL_ACCOUNT    
                + UF_PASSWD_NOTREQD + UF_PASSWORD_EXPIRED + UF_ACCOUNTDISABLE));    
   
        // Create the context    
        Context result = ctx.createSubcontext(userName, attrs);    
        System.out.println("Created disabled account for: " + userName);    
   
        ModificationItem[] mods = new ModificationItem[2];    
   
        // Replace the "unicdodePwd" attribute with a new value    
        // Password must be both Unicode and a quoted string    
        String newQuotedPassword = "\"Password2000\"";    
        byte[] newUnicodePassword = newQuotedPassword.getBytes("UTF-16LE");    
   
        mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,    
                new BasicAttribute("unicodePwd", newUnicodePassword));    
        mods[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,    
                new BasicAttribute("userAccountControl", Integer    
                        .toString(UF_NORMAL_ACCOUNT + UF_PASSWORD_EXPIRED)));    
   
        // Perform the update    
        ctx.modifyAttributes(userName, mods);    
        System.out.println("Set password & updated userccountControl");    
        // now add the user to a group.    
   
        try {    
            ModificationItem member[] = new ModificationItem[1];    
            member[0] = new ModificationItem(DirContext.ADD_ATTRIBUTE,    
                    new BasicAttribute("member", userName));    
   
            ctx.modifyAttributes(groupName, member);    
            System.out.println("Added user to group: " + groupName);    
   
        } catch (NamingException e) {    
            System.err.println("Problem adding user to group: " + e);    
        }    
        // Could have put tls.close() prior to the group modification    
        // but it seems to screw up the connection or context ?    
   
        ctx.close();    
   
        System.out.println("Successfully created User: " + userName);    
   
    }    
   
}   
