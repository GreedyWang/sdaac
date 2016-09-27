<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
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
		<title>上海爱斯达克汽车空调系统有限公司</title>

		<link rel="Shortcut Icon" href="welcome/sdaac.ico">
		<link rel="stylesheet" href="welcome/sdaac.ico">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

		<link rel="stylesheet" type="text/css" href="frontIndex/style.css"
			media="screen" />
		<LINK href="frontIndex/menu.css" type=text/css rel=stylesheet />
		<link rel="stylesheet" type="text/css"
			href="css/themes/gray/easyui.css">
		<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<SCRIPT language=javascript>
	function qiehuan(num){
		for(var id = 0;id<=6;id++)
		{			
			if(id==num)
			{
				document.getElementById("qh_con"+id).style.display="block";
				//document.getElementById("mynav"+id).className="nav_on";			
			}
			else
			{
				document.getElementById("qh_con"+id).style.display="none";
				//document.getElementById("mynav"+id).className="";
			}
		}
	}
	
	function loginBPM() {
		//document.getElementById('form1').submit();
		
		var tmp=window.open("about:blank","")
		tmp.moveTo(0,0)
		tmp.resizeTo(screen.width-20,screen.height-80)
		tmp.focus()
		tmp.location='frontIndex/bpmLogin.jsp'

	}
	
			function init(){
			var back = '${logineduser.emp.backer}'			
			var roles = document.getElementById('backer')
			if(back!=null && back!=''){
				var backs = back.split(',');
				for (var i = 0; i < backs.length; i++) {
						var opt = document.createElement('Option');
						opt.text = backs[i]
						opt.value = backs[i]
						roles.options.add(opt);
					}
			}
		}
	
</SCRIPT>
	</head>

	<body onload="init()">
		<DIV id=menu_out>
			<DIV id=menu_in>
				<DIV id=menu>
					<!-- 一级菜单 href="Lab/Experiment_Apply/ePorMainForm.jsp" -->
					<UL id=nav>


						<LI>
							<A class=nav_off id="mynav1" onmouseover=javascript:qiehuan(1)>
								<SPAN>HR</SPAN> </A>
						</LI>
						<LI class=menu_line></LI>
						<LI>
							<A class=nav_off id=mynav2 onmouseover=javascript:qiehuan(2)
								target="context" href="VAVEindex.jsp"><SPAN>VAVE</SPAN> </A>
						</LI>
						<LI class=menu_line></LI>
						<LI>
							<A class=nav_off id=mynav3 onmouseover=javascript:qiehuan(3)
								target="context" href="plm/PlmManager.jsp"><SPAN>PLM</SPAN>
							</A>
						</LI>

						<LI class=menu_line></LI>
						<LI>
							<A class=nav_off id=mynav4 onmouseover=javascript:qiehuan(4)><SPAN>财务</SPAN>
							</A>
						</LI>
						
						<LI class=menu_line></LI>
						<LI>
							<A class=nav_off id=mynav6 onmouseover=javascript:qiehuan(6)
								target="context" href="epor/ePorMainForm.jsp"><SPAN>PR
									& 间接物料主数据申请单</SPAN> </A>
						</LI>


						<LI class=menu_line></LI>
						
						<LI>
							<A class=nav_off id=mynav8 onmouseover=javascript:qiehuan(8)
								target="context" href="SAP_REPORTS/MainForm.jsp"><SPAN>SAP基础数据</SPAN>
							</A>
						</LI>

						<LI class=menu_line></LI>
						<LI>
							<A class=nav_off id=mynav9 onmouseover=javascript:qiehuan(9)
								target="context" href="DMS/MainForm.jsp"><SPAN>DMS</SPAN> </A>
						</LI>

						<LI class=menu_line></LI>
						<LI>
							<A class=nav_off id=mynav10 onmouseover=javascript:qiehuan(10)
								target="context" href="admin/admin.jsp"><SPAN>ADMIN</SPAN> </A>
						</LI>

						<LI class=menu_line></LI>
						<LI>
							<a href="#" class="easyui-menubutton" data-options="menu:'#mm1'"
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; COLOR: #ffffff; LINE-HEIGHT: 14px; PADDING-TOP: 7px;">${logineduser.emp.name}</a>
						</LI>

					</UL>



					<DIV id=menu_con>
						<!-- 2级菜单 -->
						<DIV id=qh_con0 style="DISPLAY: block">
							<UL>

							</UL>
						</DIV>

						<DIV id=qh_con1 style="DISPLAY: none">
							<UL>
								<LI>
									<A target="context" href="hr/gw/HR2.jsp"><SPAN>浮动岗位工资</SPAN>
									</A>
								</LI>
								<LI class=menu_line2></LI>
								<LI>
									<A target="context" href="hr/gw/HR.jsp"><SPAN>产品绩效</SPAN> </A>
								</LI>
								<LI class=menu_line2></LI>
								<LI>
									<A target="context" href="hr/kpi/kpi.jsp"><SPAN>KPI</SPAN>
									</A>
								</LI>
								<LI>
									<A target="context"
										href="HumanResource/assembly_room/mainForm.jsp"><SPAN>预定会议室</SPAN>
									</A>
								</LI>
							</UL>
						</DIV>


						<DIV id=qh_con2 style="DISPLAY: none">
							<UL>
							</UL>
						</DIV>

						<DIV id=qh_con3 style="DISPLAY: none">
							<UL>
							</UL>
						</DIV>

						

						<DIV id=qh_con4 style="DISPLAY: none">
							<UL>
								<LI>
									<A target="context" href="cw/UploadSap.jsp"><SPAN>查询</SPAN>
									</A>
								</LI>
								<LI class=menu_line2></LI>
								<LI>
									<A target="context" href="cw/MyJsp.jsp"><SPAN>合并</SPAN> </A>
								</LI>
								<LI class=menu_line2></LI>
								<LI>
									<A target="context" href="cw/UploadSource.jsp"><SPAN>导回</SPAN>
									</A>
								</LI>

								<LI>
									<A target="context" href="cw/log.jsp"><SPAN>日志</SPAN> </A>
								</LI>
							</UL>
						</DIV>
						<DIV id=qh_con6 style="DISPLAY: none">
							<UL>
							</UL>
						</DIV>
						


						<DIV id=qh_con8 style="DISPLAY: none">
							<UL>
							</UL>
						</DIV>

						<DIV id=qh_con9 style="DISPLAY: none">
							<UL>
							</UL>
						</DIV>
						<DIV id=qh_con10 style="DISPLAY: none">
							<UL>
							</UL>
						</DIV>
					</div>

				</DIV>
			</DIV>
		</DIV>

		<div id="mm1" style="width: 150px;">
			<div data-options="iconCls:'icon-undo'">
				<a href="#">注销</a>
			</div>
			<div data-options="iconCls:'icon-group'">
				<a target="context" href="hr/salary/show.jsp">查询工资</a>
			</div>
			<div data-options="iconCls:'icon-group'">
				<a href="javascript:void(0)" 
					onclick="$('#w').window('open')">切换角色</a>
			</div>

		</div>
		<div id="w" class="easyui-window" title="Basic Window"
			data-options="modal:true,closed:true,iconCls:'icon-save'"
			style="width: 500px; height: 200px; padding: 10px;">
			<form action="login.do?actionType=doLoginByUid" method="post">
				<table>
					<tr>
						<td>
							选择一个工号：
							<select name="uname" id="backer">

							</select>
						</td>
					</tr>
					<tr>
						<td>
							<input type="submit" value="确定">
						</td>
					</tr>
				</table>
			</form>
		</div>


		<div id="context">

			<iframe frameBorder="0" name="context" scrolling="auto" height="85%"
				width="100%" src="">
			</iframe>
		</div>

		<div id="footer" height="5%">
			<p class="right"></p>
			<p>
				上海爱斯达克汽车空调系统有限公司 地址：中国上海浦东沪南路1768号 总机：(8621)38663000
				传真：(8621)58912279 邮编：201204
				<br />
			</p>

		</div>
		</div>
	</body>
</html>
