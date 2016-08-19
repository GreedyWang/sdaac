package common.util;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Logger;
import common.entity.MyMail;

public class SdaacMail {
	private static Logger log = Logger.getLogger(SdaacMail.class);

	public static void send(MyMail item) throws MessagingException {
//		 以下变量为用户根据自己的情况设置
		String smtphost = "mail.sdaac.com"; // 发送邮件服务器
		String user = "helpdesk@sdaac.com"; // 邮件服务器登录用户名
		String password = "sdaac=0987"; // sdaac=0987邮件服务器登录密码
		String from = "helpdesk@sdaac.com"; // 发送人邮件地址1
		String to = item.getEmp().getMail();//"jim.cai@delphi.com";//接受人邮件地址 item.getEmp().getMail();//
		//to = "510721771@qq.com";
		log.warn("===============mail has sent to " + to);
		String subject = "ePR Mail Tip";//+item.getEmp().getMail();// 邮件标题
		String body = "<table><tr style='color: #0087cc'> <td colspan='4' style='font-size: 18px'><a href='http://10.243.75.22:9090/bpp'>ePR Mail Tips</a></td> </tr>"	
				+ item.getContext() + "</table>"; // 邮件内容
		// 以下为发送程序，配置文件
		Properties props = new Properties();
		props.put("mail.smtp.host", smtphost);
		props.put("mail.smtp.auth", "true");
		Session ssn = Session.getInstance(props, null);
//		ssn.setDebug(true);
		MimeMessage message = new MimeMessage(ssn);

		InternetAddress fromAddress = new InternetAddress(from);
		message.setFrom(fromAddress);
		InternetAddress toAddress = new InternetAddress(to);
		message.addRecipient(Message.RecipientType.TO, toAddress);
		// add cc
		if (item.getEmp().getEmailcc() != null
				&& !"".endsWith(item.getEmp().getEmailcc())) {
			Address[] ccAdresses = new InternetAddress[1];
			ccAdresses[0] = new InternetAddress(item.getEmp().getEmailcc());
			message.setRecipients(Message.RecipientType.CC, ccAdresses);
		}

		message.setSubject(subject);

		Multipart mp = new MimeMultipart("related");
		BodyPart bodyPart = new MimeBodyPart();// 正文
		bodyPart.setDataHandler(new DataHandler(body, "text/html;charset=GBK"));// 网页格式
		mp.addBodyPart(bodyPart);
		message.setContent(mp);// 设置邮件内容对象
		Transport transport = ssn.getTransport("smtp");
		transport.connect(smtphost, user, password);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}	
	
	public static void  main(String[] args){
		MyMail m = new MyMail();
		m.setContext("test");
		try {
			SdaacMail.send(m);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
