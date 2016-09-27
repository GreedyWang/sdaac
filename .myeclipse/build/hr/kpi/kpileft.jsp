<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="mytag"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<title>控制面板</title>
		<style type="text/css">
.menutitle {
	cursor: pointer;
	margin-bottom: 0px; background-image =url('images/menu_1.gif');
	color: #FFFFFF;
	width: 158px;
	text-align: center;
	font-weight: bold;
	background-color: #698CC3;
	padding-left: 0px;
	padding-right: 0px;
	padding-top: 2px;
	padding-bottom: 2px
}

}
.submenu {
	margin-bottom: 0em;
}

.cn {
	FONT-SIZE: 9pt;
	COLOR: #006599;
	FONT-FAMILY: 宋体
}
</style>

		<script language="javascript" type="text/javascript">
if (document.getElementById){ 
document.write('<style type="text/css">\n')
document.write('.submenu{display: none;}\n')
document.write('</style>\n')
}

function SwitchMenu(obj){
	if(document.getElementById){
	var el = document.getElementById(obj);
	var ar = document.getElementById("masterdiv").getElementsByTagName("span"); 
		if(el.style.display != "block"){ 
			for (var i=0; i<ar.length; i++){
				if (ar[i].className=="submenu") 
				ar[i].style.display = "none";
			}
			el.style.display = "block";
		}else{
			el.style.display = "none";
		}
	}
}

function killErrors() {
return true;
}

window.onerror = killErrors;
</script>

		<link rel="stylesheet" type="text/css"
			href="manager/css/style_admin.css">
		<base target="main">
	</head>

	<body topmargin="0" leftmargin="2" rightmargin="2" bottommargin="2"
		style="background-color: #698CC3;">
		<div id="masterdiv">

		</div>





		<mytag:rights needRoleID="indexManager"
					hasRoles="${logineduser.emp.roleids_b}">
		<div class="menutitle" onclick="SwitchMenu('sub13')">
			.公司级指标管理
			<TABLE width="158" cellSpacing="0" cellPadding="0" border="0">
				<TR>
					<TD height="3"></TD>
				</TR>
				<TR>
					<TD bgColor="#ffffff" height="1"></TD>
				</TR>
			</TABLE>
		</div>
		<!-- 1 -->
		<span class="submenu" id="sub13">
			<TABLE cellSpacing="0" cellPadding="0" width="158"
				background="images/menu_2.gif" border="0">
				
					<TR>
						<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
							<img border="0" src="images/icon_arrow_r.gif" width="13"
								height="13">

							<a class="menu" target="main"
								href="departIndex.do?actionType=doSelectDepartmentDetails2">公司级指标管理</a>
						</TD>
					</TR>
					
			</table> 
			</span>
		<div class="menutitle" onclick="SwitchMenu('sub14')">
			.指标管理-HR
			<TABLE width="158" cellSpacing="0" cellPadding="0" border="0">
				<TR>
					<TD height="3"></TD>
				</TR>
				<TR>
					<TD bgColor="#ffffff" height="1"></TD>
				</TR>
			</TABLE>
		</div>
		<!-- 2 -->
			<span class="submenu" id="sub14">
			<TABLE cellSpacing="0" cellPadding="0" width="158"
				background="images/menu_2.gif" border="0">				
					<TR>
						<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
							<img border="0" src="images/icon_arrow_r.gif" width="13"
								height="13">

							<a class="menu" target="main"
								href="empIndex.do?actionType=showDepartmentIndexState">各部门指标状态</a>
						</TD>
					</TR>
					<TR>
						<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
							<img border="0" src="images/icon_arrow_r.gif" width="13"
								height="13">

							<a class="menu" target="main"
								href="index/show/EmpIndexManager.jsp">HR审核指标</a>
						</TD>
					</TR>
					<TR>
						<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
							<img border="0" src="images/icon_arrow_r.gif" width="13"
								height="13">

							<a class="menu" target="main"
								href="index/temp/KpiAccount.jsp">指标总汇</a>
						</TD>
					</TR>
				</table> 
			</span>
		</mytag:rights>
		<mytag:rights needRoleID="indexPM"
					hasRoles="${logineduser.emp.roleids_b}">
		<div class="menutitle" onclick="SwitchMenu('sub15')">
			.部门级指标管理
			<TABLE width="158" cellSpacing="0" cellPadding="0" border="0">
				<TR>
					<TD height="3"></TD>
				</TR>
				<TR>
					<TD bgColor="#ffffff" height="1"></TD>
				</TR>
			</TABLE>
		</div>
			<!-- 3 -->
			<span class="submenu" id="sub15">
			<TABLE cellSpacing="0" cellPadding="0" width="158"
				background="images/menu_2.gif" border="0">				
					<TR>
						<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
							<img border="0" src="images/icon_arrow_r.gif" width="13"
								height="13">

							<a class="menu" target="main"
								href="index.do?actionType=selectDepartState">定义/修改员工个人指标</a>
						</TD>
					</TR>


					<TR>
						<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
							<img border="0" src="images/icon_arrow_r.gif" width="13"
								height="13">

							<a class="menu" target="main"
								href="index.do?actionType=selectDepartStateScore">指标结果录入</a>
						</TD>
					</TR>
					<TR>
						<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
							<img border="0" src="images/icon_arrow_r.gif" width="13"
								height="13">

							<a class="menu" target="main"
								href="index/departEmpIndex/ShowSelfDempEmpIndexs.jsp">指标查看/打印</a>
						</TD>
					</TR>
					
				</table> 
			</span>
		</mytag:rights>
	
		<div class="menutitle">
			<a style="color: #FFFFFF" href="help_info/kpi_Help.doc">.使用说明</a>
		</div>
	</body>

</html>
