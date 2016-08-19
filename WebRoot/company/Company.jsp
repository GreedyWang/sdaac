<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@	taglib uri="/WEB-INF/fmt.tld"  prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>SDAAC内部网站</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link type="text/css" href="company/css/style.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css" />

  </head>
  
  <BODY >
		<DIV id=main>
			<DIV id=logo>
				<H1>
					SDAAC		
				</H1>
			</DIV>
			<UL id=menu>
				<LI >
						<A target="context"
						href="http://localhost:8080/wea/info/index.htm">
						<SPAN class=key></SPAN>HOME</A></LI>
				<LI >
						<A target="context"
						href="company/AppList.jsp?xml=HR.xml">
						<SPAN class=key></SPAN>HR</A></LI>
				<LI>
					<A target="context"
						href="company/AppList.jsp?xml=IT.xml">
						<SPAN class=key></SPAN>IT</A></LI>
				<LI>
					<A accessKey=r
						href="#"><SPAN
						class=key></SPAN>PE</A></LI>
				<LI>
					<A accessKey=p
						href="#"><SPAN
						class=key></SPAN>PM</A></LI>
				<LI>
					<A accessKey=o
						href="#"><SPAN
						class=key></SPAN>LU</A></LI>
				<LI>
					<A accessKey=m
						href="#"><SPAN
						class=key></SPAN>ME</A>				
				</LI>
				<li>
					<A accessKey=m
						href="#"><SPAN
						class=key></SPAN>PTC</A>
				</li>
				<li>
					<A accessKey=m
						href="#"><SPAN
						class=key></SPAN>SAP</A>
				</li>
				<li>
					<A accessKey=m
						href="#"><SPAN
						class=key></SPAN>EHS</A>
				</li>
					<li>
					<A accessKey=m
						href="#"><SPAN
						class=key></SPAN>HAVC</A>
				</li>
					<li>
					<A accessKey=m
						href="#"><SPAN
						class=key></SPAN>PC&L</A>
				</li>
					<li>
					<A accessKey=m
						href="#"><SPAN
						class=key></SPAN>Market</A>
				</li>
					<li>
					<A accessKey=m
						href="#"><SPAN
						class=key></SPAN>Finance</A>
				</li>
					<li class=active >
					<a accessKey=登录 href="welcome/Login2.jsp" target="blank"
						 onclick="showWindow()"><SPAN
						></SPAN>登录</a>
				</li>
			</UL>
		
			<DIV id=intro_right>
				<P class=white >
				<div style="float: left;">
				<ul>
					<li><a target="context" href="http://10.243.75.13/iisadmpwd/aexp2b.asp">SDAAC ID Change Password SDAAC帐号修改密码 </a></li>
					<li><a target="context" href="http://localhost:8080/wea/info/address list.htm">Address List </a></li>
					<li><a target="blank" href="http://localhost:8080/FCKeditor-2.3/FCKeditor/editor/filemanager/browser/default/browser.html?Connector=connectors/jsp/connector">web P盘 </a></li>
				</ul>
				</div>
				<div style="float: right;" >
						<iframe  width="380" height="100%" src="company/tianqi.jsp"  allowtransparency="true" style="background-color=transparent" title="test" frameborder="0"  scrolling="no">
				
						</iframe>
					</div>
				</P>			
			</DIV>
			
			
			<DIV id=left>
			<h3>公司新闻</h3><h3 align="right"><img src=""></h3>
				<c:forEach items="${rs}" var="news">
				
					<div class=rightcol style="border-top: 1;border-left: 1;height: 20px">
						<table>
							<tr><td style="font-size: 14;font-style:oblique; color: #0033aa"><a target="context" href="news.do?actionType=showDetails&pk=${news.id }">${news.title }</a> </td></tr>
							<tr><td align="right"  style=" padding-bottom: 1;font-size: 10px;font-style: italic;color: #BBBBBB;height: 10">${news.releaseEmployeeId.tdepartment.name }----<fmt:formatDate pattern="yyyy-MM-dd" value="${news.releaseTime }"/> </td></tr>
						</table>
					</div>
				
				</c:forEach>
				
			</DIV>
			<DIV id=right>
				<iframe id="context" name="context" class="leftcol"  width="100%" height="100%" allowtransparency="true" style="background-color=transparent"
				 title="test" frameborder="0"  scrolling="auto" src="http://localhost:8080/wea/info/index.htm">
				
				</iframe>
				
			</DIV>
			
			<DIV id=footer>
				<P>
					&copy; Copyright
					<A href="#">SDAAC</A> 2009 &middot; Design:
					<A title="Information Architecture and Web Design" href="#">IT</A>
			</DIV>
		</DIV>
	</BODY>
</html>
