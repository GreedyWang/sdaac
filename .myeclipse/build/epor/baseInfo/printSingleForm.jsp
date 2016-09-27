<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
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

		<title>单一供应商的申请批准表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

		<link rel="stylesheet" type="text/css"
			href="css/themes/gray/easyui.css">
		<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="css/demo.css">
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

	</head>

	<body onLoad="init()">
		<div class="easyui-panel"
			title="单一供应商的申请批准表/Single Source JUSTIFICATION AND Approval (J&A) Form"
			style="width: 900px">
			<div style="padding: 10px 0 10px 60px">
				<form id="form1"  action="epor.do?actionType=doSingle" method="post">
					<table>
						<tr>
							<td rowspan=2>
								<label style="size: 8">
									<h2>
										No.:${singleForm.sno }
									</h2>
								</label>
							</td>
							<td align="right">
								<h3>
									SDAAC: DA-PUR-FOR 03.A
								</h3>
							</td>
						</tr>
						<tr>

							<td align="right">
								Effective Date: June 5, 2014
							</td>
						</tr>
						<tr>
							<td colspan="2" align="middle">
								<h3>
									单一供应商的比价采购不被允许，除非有清楚和完整的证据表明只有该一家供应商可选
								</h3>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="middle">
								<h3>
									Single Source purchase are not permitted except when clearly
									and thoroughly justified
								</h3>
							</td>
						</tr>
						<tr>
							<td width="250">
								采购编号/POR Number:

							</td>
							<td>
								<input class="easyui-validatebox" type="text"
									name="singleForm.prno" value="${singleForm.prno }"
									readOnly="true">
							</td>
						</tr>
						<tr>
							<td>
								申请日期/Requst Date:
							</td>
							<td>
								<input class="easyui-validatebox" type="text"
									name="singleForm.requestdate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${singleForm.requestdate}" />">
							</td>
												
						
					
						</tr>
						<tr>
							<td>
								预计数量/Estimated Amount:

							</td>
							<td>

								<input type="text" name="singleForm.es_amount"
									value="${singleForm.es_amount}">
							</td>
						</tr>
						<tr>
							<td>
								预计金额/Estimated Cost:

							</td>
							<td>

								<input type="text" name="singleForm.es_cost"
									value="${singleForm.es_cost}">
							</td>
						</tr>
						<tr>
							<td>
								申请零件或服务的描述或目的/Description of Requested Items/Service and
								Purpose:

							</td>
							<td>
								<textarea class="easyui-validatebox" rows="3" cols="25"
									 name="singleForm.des">${singleForm.des}</textarea>
						
							</td>
						</tr>
						<tr>
							<td colspan="1">
								<h3>
									供应商联系方式/Supplier Contact
								</h3>
							</td>

						</tr>
						<tr>
							<td>
								名称/Name:

							</td>
							<td>

								<input type="text" name="singleForm.sname"
									value="${singleForm.sname}">
							</td>
						</tr>
						<tr>
							<td>
								联系人/contacts:

							</td>
							<td>

								<input type="text" name="singleForm.scontact"
									value="${singleForm.scontact}">
							</td>
						</tr>
						<tr>
							<td>
								联系电话/Phone No.:

							</td>
							<td>

								<input type="text" name="singleForm.sphone"
									value="${singleForm.sphone}">
							</td>
						</tr>
						<tr>
							<td>
								邮件地址/Email:

							</td>
							<td>

								<input type="text" name="singleForm.semail"
									value="${singleForm.semail}">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<h3>
									只选一家供应商的原因/Reason(s) for Single Source:
								</h3>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="checkbox" value="客户指定(福特, 通用, 克莱施勒, 等.)"
									name="reason">
								客户指定(福特, 通用, 克莱施勒, 等.)/External Customer directive(Ford, GM,
								Chrysler, etc.)
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="checkbox" value="现有业务的延续，并可减少新的开发费用" name="reason">
								现有业务的延续，并可减少新的开发费用/Continuation of current business processes
								and eliminates new infrastructure
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="checkbox" value="因设计专利的约束" name="reason">
								因设计专利的约束/Proprietary design
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="checkbox" value="供应商为该行业的领导者" name="reason">
								供应商为该行业的领导者/Supplier is a proven leader in the inductry
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<h3>
									没有进行比价的技术和业务的原因(请从以下复选框中选择适合的情况)/Technical and business reasons
									why a no-bid situation:
								</h3>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="checkbox" value="原始的制造商或提供商,无本土的供应商存在"
									name="reason2">
								原始的制造商或提供商,无本土的供应商存在/Original manufacturer or provider, no other
								local distributors exist
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="checkbox" value="只有原始的制造商或提供商的分销商" name="reason2">
								只有原始的制造商或提供商的分销商/ Only distributor for the original manufacturer
								or provider
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="checkbox" value="元件或设备无法与其它制造商的相似元件互换"
									name="reason2">
								元件或设备无法与其它制造商的相似元件互换/ Parts or equipment not interchangeable
								with similar parts of another
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="checkbox" value="仅仅知道该产品或服务是匹配需求或能够执行所要求的任务" name="reason2">
								仅仅知道该产品或服务是匹配需求或能够执行所要求的任务/Only known item or service matching the requested
								needs or performing the task
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="checkbox" value="该专利产品或服务的唯一提供商" name="reason2">
								该专利产品或服务的唯一提供商/Sole provider of a licensed or patented
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="checkbox" value="符合现有设备，库存，系统，项目或服务的唯一提供商" name="reason2">
								符合现有设备，库存，系统，项目或服务的唯一提供商/Sole provider of items compatible
								with existing equipment, inventory, systems, programs or
								services
								good or service
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="checkbox" value="标准化商品或服务的唯一提供商 (请列出所涉及的标准)" name="reason2">
								标准化商品或服务的唯一提供商 (请列出所涉及的标准)/Sole provider of goods or services established
								as standard (Pls provide evidence of such a standard)
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="checkbox" value="授权的售后服务工厂的唯一提供商" name="reason2">
								授权的售后服务工厂的唯一提供商/Sole provider of factory-authorized warranty service
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="checkbox" value="具有很好的价值和显著优势" name="reason2">
								具有很好的价值和显著优势/ Used item representing good value and advantage
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="checkbox" value="由德尔福内部提供或转移过来的业务或服务" name="reason2">
								由内部提供或转移过来的业务或服务/Goods or services provided or transferred in
								internally
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="checkbox" value="德尔福全求合同的业务或服务" name="reason2">
								全求合同的业务或服务/ Goods or services with Global contract
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="checkbox" value="以上都不是. 请说明" name="reason2">
								以上都不是. 请说明/None of above applies. Justification
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input size=80 type="text" name="singleForm.otherReason"
									value="${singleForm.otherReason }">

							</td>
						</tr>
						<tr>
							<td colspan="2" align=middle>
								<h3>
									我保证以上所述情况完全属实，请求批准对该产品或服务只能进行单一供应商的采购
									<h3>
							</td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</table>
					<table cellspacing="10" cellpadding="10">
						<tr>
							<td>
								申请人姓名/部门
							</td>
							<td width="220">
								${singleForm.applicantId.name}
							</td>
							<td>
								批准日期
							</td>

							<td>
								
							</td>
						</tr>
						<tr>
							<td>
								部门经理批准
							</td>
							<td>
								
							</td>
							<td>
								批准日期
							</td>
							<td>
								
							</td>
						</tr>
						<tr>
							<td>
								工程总监/工厂经理（小于$3K）
							</td>
							<td>
								
							</td>
							<td>
								批准日期
							</td>
							<td>
								
							</td>
						</tr>
						<tr>
							<td>
								总经理（大于$3K 且小于$1M）
							</td>
							<td>
								
							</td>
							<td>
								批准日期
							</td>
							<td>
								
							</td>
						</tr>
						<tr>
							<td>
								亚太区热系统（大于$1M）
							</td>
							<td>
								
							</td>
							<td>
								批准日期
							</td>
							<td>
								
							</td>
						</tr>
					</table>
					<table width=800px>
						<tr>
							<td>

							</td>
						</tr>
						<tr>
							<td colspan="2" align="middle">
								<h3>
									仅供采购部门使用
								</h3>
							</td>
						</tr>
						<tr>
							<td colspan=2>
								<input type="checkbox"> 批准<input type="checkbox">不批准
							</td>

						</tr>
						
						<tr>
						<td>
							不批准原因:
							</td>
						</tr>
						<tr height=50>
							<td>
								姓名/日期
							</td>
							<td></td>
						</tr>

					</table>
				</form>


			</div>
			<div style="text-align: center; padding: 5px">
			<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height=0 id=wb name=wb width=0></OBJECT> 
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">打印/Print</a>
			</div>
		</div>
	</body>
		
	<script type="text/javascript">
				var HKEY_Root,HKEY_Path,HKEY_Key;   
HKEY_Root="HKEY_CURRENT_USER";   
HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
var head,foot,top,bottom,left,right; 
			
			function submitForm(){
				window.print();
				//previewPrint();
				//wb.execwb(7,1); 
			}
			
			 function init(){
			 	//window.open()
			 	var reason1 = '${singleForm.reason}'
			 	
			 	var rs1 = reason1.split(';')
			 	var rrs1 = document.getElementsByName("reason");
			 	for(var i =0;i<rrs1.length;i++){
			 		for(var j =0;j<rs1.length;j++){
			 			if(rrs1[i].value = rs1[i]){
			 				rrs1[i].checked = true;
			 			}
			 		}
			 	}
			 	//reason2
			 	var reason2 = '${singleForm.reason2}'
			 	
			 	var rs2 = reason2.split(';')
			 	var rrs2 = document.getElementsByName("reason2");
			 	for(var i =0;i<rrs2.length;i++){
			 		for(var j =0;j<rs2.length;j++){
			 			if(rrs2[i].value = rs1[i]){
			 				rrs2[i].checked = true;
			 			}
			 		}
			 	}
			 	//initPrintSet();
			 	//window.print();
			 }  
  
 function setPrint()
{
factory.printing.PageSetup();
}
function previewPrint()
{
WB.ExecWB(7,1)
}
function initPrintSet()
{
//factory.printing.header = "Hello Ken";
  	//factory.printing.footer = "Good Bye Ken";
  	factory.printing.leftMargin = 8;
  	factory.printing.topMargin = 1.5;
  	factory.printing.rightMargin = 0.75;
  	factory.printing.bottomMargin = 1.5;
  	//factory.printing.printBackground = true;
  	factory.printing.portrait = false;
}
    
									
	</script>

</html>


