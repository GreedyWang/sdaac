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




		<mytag:rights needRoleID="baseInfo"
			hasRoles="${logineduser.emp.roleids_b}">
			<div class="menutitle" onClick="SwitchMenu('sub10')">
				.员工管理
				<TABLE width="158" cellSpacing="0" cellPadding="0" border="0">
					<TR>
						<TD height="3"></TD>
					</TR>
					<TR>
						<TD bgColor="#ffffff" height="1"></TD>
					</TR>
				</TABLE>
			</div>
			<span class="submenu" id="sub10">
				<TABLE cellSpacing="0" cellPadding="0" width="158"
					background="images/menu_2.gif" border="0">


					<TR>
						<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
							<img border="0" src="images/icon_arrow_r.gif" width="13"
								height="13">
							<a class="menu" target="main"
								href="employee.do?actionType=beforeAddEmp">员工添加</a>
						</TD>
					</TR>

					<TR>
						<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
							<img border="0" src="images/icon_arrow_r.gif" width="13"
								height="13">
							<a class="menu" target="main" href="Manager/EmpManager.jsp">员工信息修改</a>
						</TD>
					</TR>

					<mytag:rights needRoleID="ROLE_ADMIN"
						hasRoles="${logineduser.emp.roleids_b}">


						<TR>
							<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
								<img border="0" src="images/icon_arrow_r.gif" width="13"
									height="13">
								<a class="menu" target="main" href="epor.do?actionType=doTest">ePR Reports</a>
							</TD>
						</TR>
						<TR>
							<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
								<img border="0" src="images/icon_arrow_r.gif" width="13"
									height="13">
								<a class="menu" target="main" href="admin/role/index.jsp">权限管理</a>
							</TD>
						</TR>
						<TR>
							<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
								<img border="0" src="images/icon_arrow_r.gif" width="13"
									height="13">
								<a class="menu" target="main" href="epor/adminshowList.jsp">PR修改</a>
							</TD>
						</TR>
					</mytag:rights>
				</table> </span>
			<div class="menutitle" onClick="SwitchMenu('sub11')">
				.工人小组管理
				<TABLE width="158" cellSpacing="0" cellPadding="0" border="0">
					<TR>
						<TD height="3"></TD>
					</TR>
					<TR>
						<TD bgColor="#ffffff" height="1"></TD>
					</TR>
				</TABLE>
			</div>
			<span class="submenu" id="sub11">
				<TABLE cellSpacing="0" cellPadding="0" width="158"
					background="images/menu_2.gif" border="0">
					<TR>
						<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
							<img border="0" src="images/icon_arrow_r.gif" width="13"
								height="13">
							<a class="menu" target="main" href="admin/teamWork.jsp">工人小组 管理</a>
						</TD>
					</TR>
					<TR>
						<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
							<img border="0" src="images/icon_arrow_r.gif" width="13"
								height="13">
							<a class="menu" target="main" href="admin/dprt/dprt.jsp">部门管理</a>
						</TD>
					</TR>
					<TR>
						<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
							<img border="0" src="images/icon_arrow_r.gif" width="13"
								height="13">
							<a class="menu" target="main" href="admin/dprt/dprt.jsp">部门经理的主管管理</a>
						</TD>
					</TR>
					<TR>
						<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
							<img border="0" src="images/icon_arrow_r.gif" width="13"
								height="13">
							<a class="menu" target="main" href="admin/role/totalRoles.jsp">权限管理</a>
						</TD>
					</TR>
					<TR>
						<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
							<img border="0" src="images/icon_arrow_r.gif" width="13"
								height="13">
							<a class="menu" target="main" href="admin/pr/changeApprovor.jsp">修改审批者</a>
						</TD>
					</TR>
					<TR>
						<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
							<img border="0" src="images/icon_arrow_r.gif" width="13"
								height="13">
							<a class="menu" target="main" href="admin/pr/changestate.jsp">修改审批节点</a>
						</TD>
					</TR>
					<TR>
						<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
							<img border="0" src="images/icon_arrow_r.gif" width="13"
								height="13">
							<a class="menu" target="main" href="">**Deploy</a>
							<!-- epor.do?actionType=doDeploy -->
						</TD>
					</TR>
				</table> </span>				
		</mytag:rights>

	</body>

</html>
