package com.sdaac.pr;

import javax.mail.Authenticator; 
import javax.mail.PasswordAuthentication; 
   
public class MyAuthenticator extends Authenticator { 
   
    public MyAuthenticator() { 
        System.out.println("自定义验证器加载..."); 
    } 
   
    private String userName; 
    private String passWord; 
   
    /** 
     * @param userName 
     *            the userName to set 
     */
    public void setUserName(String userName) { 
        this.userName = userName; 
    } 
   
    public void setPassWord(String passWord) { 
        this.passWord = passWord; 
    } 
   
    @Override
    protected PasswordAuthentication getPasswordAuthentication() { 
        return new PasswordAuthentication("helpdesk@sdaac.com", "sdaac=0987"); 
    } 
   
}