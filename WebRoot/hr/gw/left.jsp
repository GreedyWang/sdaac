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


		<mytag:rights needRoleID="hrRecorder"
			hasRoles="${logineduser.emp.roleids_b}">
			<div class="menutitle" onClick="SwitchMenu('sub4')">
				.数据录入
				<TABLE width="158" cellSpacing="0" cellPadding="0" border="0">
					<TR>
						<TD height="3"></TD>
					</TR>
					<TR>
						<TD bgColor="#ffffff" height="1"></TD>
					</TR>
				</TABLE>
			</div>
			<span class="submenu" id="sub4">
				<table cellspacing="1" cellpadding="4" width="158"
					class="tableborder">

					<TR class=altbg1>
						<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
							<img border="0" src="images/icon_arrow_r.gif" width="13"
								height="13">
							<a class="menu" target="main"
								href="front/bounce/PostBounceRegister.jsp">车间生产记录录入</a>
						</TD>
					</TR>
					<TR class=altbg1>
						<TD height=25 width="100%" align="center"
							style="background-color: #698CC3;">
							<mytag:rights needRoleID="baseInfo"
								hasRoles="${logineduser.emp.roleids_b}">

								<a class="menu" style="color: #FFFFFF;"
									onClick="SwitchMenu('sub3')">.基础数据维护</a>
								<TABLE width="100%" cellSpacing="0" cellPadding="0" border="0">
									<TR>
										<TD height="3"></TD>
									</TR>
									<TR>
										<TD bgColor="#ffffff" height="1"></TD>
									</TR>
								</TABLE>
								<span class="submenu" id="sub3">
									<TABLE cellSpacing="0" cellPadding="0" width="100%"
										background="images/menu_2.gif" border="0">
										<TR>
											<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
												<img border="0" src="images/icon_arrow_r.gif" width="13"
													height="13">
												<a class="menu" target="main"
													href="front/hrpost/th/FigureAdd.jsp">图号添加</a>
											</TD>
										</TR>
										<TR>
											<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
												<img border="0" src="images/icon_arrow_r.gif" width="13"
													height="13">
												<a class="menu" target="main"
													href="front/hrpost/th/FigureAccount.jsp">图号维护</a>
											</TD>
										</TR>
										<TR>
											<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
												<img border="0" src="images/icon_arrow_r.gif" width="13"
													height="13">
												<a class="menu" target="main"
													href="front/hrpost/gx/BouncePostAdd.jsp">工序添加</a>
											</TD>
										</TR>
										<TR>
											<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
												<img border="0" src="images/icon_arrow_r.gif" width="13"
													height="13">
												<a class="menu" target="main"
													href="front/hrpost/gx/BouncePostupdate.jsp">工序维护</a>
											</TD>
										</TR>
										
										<TR>
											<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
												<img border="0" src="images/icon_arrow_r.gif" width="13"
													height="13">
												<a class="menu" target="main"
													href="front/hrpost/PostUpdate.jsp">所有岗位号查询/更改</a>
											</TD>
										</TR>
									</table> </span>
							</mytag:rights>
						</TD>
					</TR>
				</table> </span>
		

		<div class="menutitle" onClick="SwitchMenu('sub9')">
			.数据查询
			<TABLE width="158" cellSpacing="0" cellPadding="0" border="0">
				<TR>
					<TD height="3"></TD>
				</TR>
				<TR>
					<TD bgColor="#ffffff" height="1"></TD>
				</TR>
			</TABLE>
		</div>
		<span class="submenu" id="sub9">
			<table cellspacing="1" cellpadding="4" width="158"
				class="tableborder">
				<TR class=altbg1>
					<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
						<img border="0" src="images/icon_arrow_r.gif" width="13"
							height="13">
						<a class="menu" target="main"
							href="front/PersonalEffectSelect.jsp">车间生产记录查询</a>
					</TD>
				</TR>
				<mytag:rights needRoleID="hrManager"
					hasRoles="${logineduser.emp.roleids_b}">
					<TR class=altbg1>
						<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
							<img border="0" src="images/icon_arrow_r.gif" width="13"
								height="13">
							<a class="menu" target="main"
								href="front/hrpost/productScore.jsp">产品绩效及报表查询</a>
						</TD>
					</TR>
					<TR class=altbg1>
						<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
							<img border="0" src="images/icon_arrow_r.gif" width="13"
								height="13">
							<a class="menu" target="main"
								href="chejianjiangjin.do?actionType=doConfirmFinish">加班时间统计</a>
						</TD>
					</TR>
				</mytag:rights>
			</table> </span>
			
			<div class="menutitle" onClick="SwitchMenu('sub7')">
			.数据修改
			<TABLE width="158" cellSpacing="0" cellPadding="0" border="0">
				<TR>
					<TD height="3"></TD>
				</TR>
				<TR>
					<TD bgColor="#ffffff" height="1"></TD>
				</TR>
			</TABLE>
		</div>
		<span class="submenu" id="sub7">
			<table cellspacing="1" cellpadding="4" width="158"
				class="tableborder">
				<TR class=altbg1>
					<TD height=25 width="100%" align="center" bgcolor="#D6E0EF">
						<img border="0" src="images/icon_arrow_r.gif" width="13"
							height="13">
						<a class="menu" target="main"
							href="front/PersonalEffectSelect.jsp">车间生产记录修改</a>
					</TD>
				</TR>				
			</table> </span>
			</mytag:rights>
	</body>

</html>
