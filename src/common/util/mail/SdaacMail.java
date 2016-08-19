//package common.util.mail;
//
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.util.Properties;
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//import javax.mail.Address;
//import javax.mail.BodyPart;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Multipart;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.Message.RecipientType;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//import javax.mail.internet.MimeUtility;
//
//import org.apache.log4j.Logger;
//import common.entity.MyMail;
//
//public class SdaacMail {
//	private static Logger log = Logger.getLogger(SdaacMail.class);
//
//	public static void send(MyMail item) throws MessagingException {
//		// 以下变量为用户根据自己的情况设置
//		String smtphost = "mail.sdaac.com"; // 发送邮件服务器
//		String user = "helpdesk@sdaac.com"; // 邮件服务器登录用户名
//		String password = "sdaac=0987"; // sdaac=0987邮件服务器登录密码
//
//		String from = "helpdesk@sdaac.com"; // 发送人邮件地址1
//		String to = "yongmin.wang@delphi.com";//item.getEmp().getMail();//
//											 // 接受人邮件地址
//
//		log.info("mail has sent to " + to);
//		String subject = "电子PR邮件提醒/ePR Mail Tip";// item.getType();
//																	// // 邮件标题
//		String body = "<table bordercolor='#91D5E3'><tr><td><a href='http://10.243.75.13:9090/bpp'>系统地址/System URL</a></td></tr>"
//				+ item.getContext() + "</table>"; // 邮件内容
//
//		// 以下为发送程序，配置文件
//
//		Properties props = new Properties();
//		props.put("mail.smtp.host", smtphost);
//		props.put("mail.smtp.auth", "true");
//
//		Session ssn = Session.getInstance(props, null);
////		ssn.setDebug(true);
//		MimeMessage message = new MimeMessage(ssn);
//
//		InternetAddress fromAddress = new InternetAddress(from);
//		message.setFrom(fromAddress);
//		InternetAddress toAddress = new InternetAddress(to);
//		message.addRecipient(Message.RecipientType.TO, toAddress);
//		// add cc
//		if (item.getEmp().getEmailcc() != null
//				&& !"".endsWith(item.getEmp().getEmailcc())) {
//			Address[] ccAdresses = new InternetAddress[1];
//			ccAdresses[0] = new InternetAddress(item.getEmp().getEmailcc());
//			message.setRecipients(Message.RecipientType.CC, ccAdresses);
//		}
//
//		message.setSubject(subject);
//
//		Multipart mp = new MimeMultipart("related");
//		BodyPart bodyPart = new MimeBodyPart();// 正文
//		bodyPart.setDataHandler(new DataHandler(body, "text/html;charset=GBK"));// 网页格式
//		mp.addBodyPart(bodyPart);
//		message.setContent(mp);// 设置邮件内容对象
//
//		Transport transport = ssn.getTransport("smtp");
//		transport.connect(smtphost, user, password);
//		transport.sendMessage(message, message.getAllRecipients());
//
//		transport.close();
//
//	}
//	
//	public static void send2(MyMail item) throws MessagingException {
//		// 以下变量为用户根据自己的情况设置
//		String smtphost = "mail.sdaac.com"; // 发送邮件服务器
//		String user = "helpdesk@sdaac.com"; // 邮件服务器登录用户名
//		String password = "sdaac=0987"; // sdaac=0987邮件服务器登录密码
//
//		String from = "helpdesk@sdaac.com"; // 发送人邮件地址1
//		String to = "yongmin.wang@delphi.com";//item.getEmp().getMail();//
//											 // 接受人邮件地址
//
//		log.info("mail has sent to " + to);
//		String subject = "电子PR邮件提醒/ePR Mail Tip";// item.getType();
//			
//
//		
//		// // 邮件标题
//		String body = "<table bordercolor='#91D5E3'>" +				
//				"<tr style='color: #0087cc'> <td colspan='4' style='font-size: 18px'><a href='http://10.243.75.13:9090/bpp'>ePR Mail Tips</a></td> </tr>"+
//				
//				"<tr style='background-color: #0087cc'> <td>yongmin.wang</td> <td>购买虚拟空间</td> <td>2013-3-1</td> <td>200,000RMB</td> </tr>"+
//				"<tr > <td></td> <td>购买虚拟空间</td> <td>2013-3-1</td> <td>200,000RMB</td> </tr>"+
//				"<tr > <td></td> <td style='background-color: #C0C0C0'>购买虚拟空间</td> <td style='background-color: #C0C0C0'>2013-3-1</td> <td style='background-color: #C0C0C0'>200,000RMB</td> </tr>"+
//				"<tr> <td></td> <td>购买虚拟空间</td> <td>2013-3-1</td> <td>200,000RMB</td> </tr>"+
//				"</table>"; // 邮件内容
//
//		// 以下为发送程序，配置文件
//
//		Properties props = new Properties();
//		props.put("mail.smtp.host", smtphost);
//		props.put("mail.smtp.auth", "true");
//
//		Session ssn = Session.getInstance(props, null);
////		ssn.setDebug(true);
//		MimeMessage message = new MimeMessage(ssn);
//
//		InternetAddress fromAddress = new InternetAddress(from);
//		message.setFrom(fromAddress);
//		InternetAddress toAddress = new InternetAddress(to);
//		message.addRecipient(Message.RecipientType.TO, toAddress);
//		// add cc
//		if (item.getEmp().getEmailcc() != null
//				&& !"".endsWith(item.getEmp().getEmailcc())) {
//			Address[] ccAdresses = new InternetAddress[1];
//			ccAdresses[0] = new InternetAddress(item.getEmp().getEmailcc());
//			message.setRecipients(Message.RecipientType.CC, ccAdresses);
//		}
//
//		message.setSubject(subject);
//
//		Multipart mp = new MimeMultipart("related");
//		
//
//		
//		
//		
//		BodyPart bodyPart = new MimeBodyPart();// 正文
//		bodyPart.setDataHandler(new DataHandler(body, "text/html;charset=GBK"));// 网页格式
//		mp.addBodyPart(bodyPart);
//		
//		//-------------------------------------
//		//MimeMultipart bodyMultipart = new MimeMultipart("related");
//		//content.setContent(bodyMultipart);
//		MimeBodyPart gifPart = new MimeBodyPart();		
//		mp.addBodyPart(gifPart);		
//
//		DataSource gifds = new FileDataSource(
//				"resource\\logo.gif"	
//			);
//		DataHandler gifdh = new DataHandler(gifds);		
//		gifPart.setDataHandler(gifdh);
//		gifPart.setHeader("Content-Location", "http://www.itcast.cn/logo.gif");
//		//----------------------------------
//		
//		
//		message.setContent(mp);// 设置邮件内容对象
//
//		
//		
//		
//
//		
//		Transport transport = ssn.getTransport("smtp");
//		transport.connect(smtphost, user, password);
//		transport.sendMessage(message, message.getAllRecipients());
//
//		transport.close();
//
//	}
//	
//	
//	
//	/**
//	 * 带图片附件的邮件发送
//	 */
//	public static void sendWithRich()throws Exception{
//		Session session = Session.getInstance(new Properties());
//		MimeMessage msg = new MimeMessage(session);
//		msg.setFrom(new InternetAddress("\"" + MimeUtility.encodeText("传智播客") + "\" <itcast_test@sina.com>"));
//		msg.setSubject("你们的Java培训真的是最牛的吗？");		
//		msg.setReplyTo(new Address[]{new InternetAddress("yongmin.wang@delphi.com")});
//		msg.setRecipients(RecipientType.TO,InternetAddress.parse("neyowang@gmail.com"));
//		MimeMultipart msgMultipart = new MimeMultipart("mixed");
//		msg.setContent(msgMultipart);
//
//		MimeBodyPart attch1 = new MimeBodyPart();		
//		MimeBodyPart attch2 = new MimeBodyPart();		
//		MimeBodyPart content = new MimeBodyPart();
//		msgMultipart.addBodyPart(attch1);		
//		msgMultipart.addBodyPart(attch2);		
//		msgMultipart.addBodyPart(content);
//
//		DataSource ds1 = new FileDataSource(
//				"resource\\Java培训.txt"	
//			);
//		DataHandler dh1 = new DataHandler(ds1 );
//		attch1.setDataHandler(dh1);
//		attch1.setFileName(
//				MimeUtility.encodeText("java培训.txt")
//				);
//		
//		DataSource ds2 = new FileDataSource(
//				"resource\\slogo.gif"		
//			);
//		DataHandler dh2 = new DataHandler(ds2 );
//		attch2.setDataHandler(dh2);		
//		attch2.setFileName("slogo.gif");
//		
//		MimeMultipart bodyMultipart = new MimeMultipart("related");
//		content.setContent(bodyMultipart);
//		MimeBodyPart htmlPart = new MimeBodyPart();		
//		MimeBodyPart gifPart = new MimeBodyPart();		
//		bodyMultipart.addBodyPart(htmlPart);
//		bodyMultipart.addBodyPart(gifPart);		
//
//		DataSource gifds = new FileDataSource(
//				"resource\\logo.gif"	
//			);
//		DataHandler gifdh = new DataHandler(gifds);		
//		gifPart.setDataHandler(gifdh);
//		gifPart.setHeader("Content-Location", "http://www.itcast.cn/logo.gif");
//		
//		htmlPart.setContent("你们的Java培训真的是最牛的吗？大家都这么说,我想跟你们比试一下！这可是我自己用程序生成和发送的邮件哦！<img src='http://www.itcast.cn/logo.gif'>"
//					, "text/html;charset=gbk");
//		
//		msg.saveChanges();
//		
//		OutputStream ips = new FileOutputStream("resource\\demo3.eml");
//		msg.writeTo(ips);
//		ips.close();
//	}
//	
//
//	public static void main(String[] args) throws MessagingException {
//		String context = "<table border='1' bordercolor='#91D5E3'><tr><td colspan = '4'>你有一个PR单需要审批</td></tr>";
//		context += "<tr><td>aaa</td><td>bbb</td><td>ccc</td><td>ddd</td></tr>";
//		context += "</table>";
//		MyMail dd = new MyMail("yongmin.wang@delphi.com", "4 testing", context, 0);
//		SdaacMail.send2(dd);
//	}
//
//}
