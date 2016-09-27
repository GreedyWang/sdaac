<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>上海德尔福汽车空调系统有限公司--Shanghai Delphi Auto A/C Systems</title>
		<LINK media=screen href="welcome/master.css" type=text/css rel=stylesheet>
		<link media=screen href="css/common.css" type=text/css rel=stylesheet>
	</head>


	<script type="text/javascript">  
  function submit()
  {
  	var theForm=document.getElementById('loginFrom');
  	theForm.submit();
  }
  
  function keyDown()
  {
  	if(event.keyCode==13)
  	{
  		submit();
  	}
  }
  </script>
	<body>
	<div style="position: relative;left: 100; top: 100">
		<ul>
			<li><a href="http://10.243.75.13/iisadmpwd/aexp2b.asp">Changing SDAAC Password</a></li>
			<li>Login Before Choice The Right OU!</li>
		</ul>
	</div>
	 <form action="login.do?actionType=doLogin" method="post" id="loginFrom">
		<DIV id=login style="position: relative; left: 800; top: 0">
			
			<DIV id=cap-body>
				<DIV>
					<DIV class="title" style="font-size: 36px; color: #004567">
						<IMG id=imgLogo src="welcome/w1.png">
						<bean:message key='login' />
					</DIV>
					
					<DIV id=panelLogin>
						<DIV>
							
							<input class="input" type="text" name="uname" id="uname"
								value="<bean:message key='username' />"
								onfocus="if(value==defaultValue){value='';this.style.color='#000'}"
								placeholder="<bean:message key='username' />"
								onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
								style="color: #999" />
						</DIV>
						<DIV>
							<LABEL>

							</LABEL>
							<input class="input" type="password" name="upass" id="upass"
								value="<bean:message key='passwd' />"
								onfocus="if(value==defaultValue){value='';this.style.color='#000'}"
								placeholder="<bean:message key='pswd' />"
								onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
								style="color: #999;" autocomplete="off" onkeydown="keyDown()" />
							<DIV style="position: relative; top: 15px;">
								<LABEL style="color: #004567" >
									OU
								</LABEL>
								<select class='textbox340' name="OU" style="color: #999;">
									<option value="ASIA" selected="selected">
										ASIA
									</option>
									<option value="SDAAC" >
										SDAAC
									</option>
									<option value="EUR">
										EUR
									</option>
									<option value="NA">
										North America
									</option>
								</select>
							</DIV>
						</DIV>
						<DIV class="submit clearfix" style="position: relative; top: 30px;">
							<INPUT algin="left" id="btnLogin" type="button"
								value="<bean:message key='login' />" class="signIn_btn"
								name=btnLogin onClick="submit();"
								style="position: relative; right: 1px">
						</DIV>
						<DIV style="position: relative; top: 30px;" id="panelErrorMsg">
							<html:errors />
						</DIV>
					</DIV>
					<div
						style="position: relative; top: 50px; right: 0px; font-size: 12; color: #004567">
						<bean:message key='question' /><br>
						
					</DIV>
					</DIV></DIV></DIV></form>
	</body>
</html>

