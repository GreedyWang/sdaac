<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'wym.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>


<body>

<strong>java.io.tmpdir:</strong>

<ul>

<li><%=System.getProperty("java.io.tmpdir")%></li>

</ul>

<br/>

<strong>Memory:</strong>

<ol>

<li>freeMemory=<%=Runtime.getRuntime().freeMemory()/(1024*1024)%>M</li>

    <li>totalMemory=<%=Runtime.getRuntime().totalMemory()/(1024*1024)%>M</li>

    <li>maxMemory=<%=Runtime.getRuntime().maxMemory()/(1024*1024)%>M</li>

</ol>

<br/>

<strong>Thread:</strong>

<ol>

<%for(Thread t : list_threads()){%>

<li><%=t.getName()%>(<b><%=t.getState()%></b>) : <%=t.getClass().getName()%></li>

<%}%>

</ol>

<%!

public static java.util.List<Thread> list_threads(){

    int tc = Thread.activeCount();

    Thread[] ts = new Thread[tc];

    Thread.enumerate(ts);

    return java.util.Arrays.asList(ts);

}

%>

</body>



</html>
