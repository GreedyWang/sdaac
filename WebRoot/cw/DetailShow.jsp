<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'DetailShow.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  <script type="text/javascript">

  	//上（下）一条
  	function showOther(Direction)
  	{
  		if(Direction=='next')
  		{
  			if('${index[1] -index[0]}'<2)
  			{
  				alert('已经是最后条')
  				return null
  			}
  			
  		}
  		else if(Direction=='previous')
  		{
  			if('${index[0]}'==0)
  			{
  				alert('已经是第一条')
  				return null
  			}
  		}
  		window.location.href='cw.do?actionType=showDetials&next='+Direction+'&index=${index[0]}';
  	}
  	//增加一行
  	 function Tadd()
  {
  	
   // var index=document.getElementById('table3').rows.length-long;
  	var newTr = table3.insertRow(2);
	var newTd0 = newTr.insertCell();	
	var newTd1 = newTr.insertCell();
	var newTd2 = newTr.insertCell();
	var newTd3 = newTr.insertCell();
	var newTd4 = newTr.insertCell();
	var newTd5 = newTr.insertCell();
	var newTd6 = newTr.insertCell();
	var newTd7 = newTr.insertCell();
	var newTd8 = newTr.insertCell();


	newTd0.innerHTML= '<input type="text" name="tax.taxItems.produceName" > '; 
	newTd1.innerHTML= '<input type="text" name="tax.taxItems.standardType" >'; 
	newTd2.innerHTML='<input type="text" name="tax.taxItems.unit " size="5">';
	newTd3.innerHTML='<input type="text" name="tax.taxItems.quantity" size="12">';
	newTd4.innerHTML= '<input type="text" name="tax.taxItems.price" >';
	newTd5.innerHTML= '<input type="text" name="tax.taxItems.sumPrice" >';
	newTd6.innerHTML= '<input type="text" name="tax.taxItems.fax" size="5">';
	newTd7.innerHTML= '<input type="text" name="tax.taxItems.faxPrice" >';
	newTd8.innerHTML= '<img src="images/del.gif" id="image"  onclick="Tdelete(this)" >';
	
  }
  //删除一行
    function Tdelete(elem)
  {
  	var t=document.getElementById('table3')   
  	t.deleteRow(elem.parentNode.parentNode.rowIndex)
  
  }
  </script>
   <body>
  <form action="cw.do?actionType=detailUpdate" method="post">

			<table border="1" id="table3">
				<tr>
					<td>
						购货单位
					</td>
					<td colspan="4">
						<table>
							<tr>
								<td>
									名称:
									<input type="text" name="tax.buyer.name" value="${rs.buyer.name }" size="35">
								</td>
							</tr>
							<tr>
								<td>
									纳税人识别号：
									<input type="text" name="tax.buyer.taxpayersCode" value="${rs.buyer.taxpayersCode }" size="35">
								</td>
							</tr>
							<tr>
								<td>
									地址、电话：
									<input type="text" name="tax.buyer.address" value="${rs.buyer.address }"
										size="35">
								</td>
							</tr>
							<tr>
								<td>
									开户行及帐号：
									<input type="text" name="tax.buyer.account" value="${rs.buyer.account }"
										size="35">
								</td>
							</tr>
						</table>
					</td>
					<td>
						密码区
					</td>
					<td colspan="2"></td>
				</tr>
				<tr style="background-color: #34E123">
					<td>
						货物名称
					</td>
					<td>
						规格
					</td>
					<td>
						单位
					</td>
					<td>
						数量
					</td>
					<td>
						单价
					</td>
					<td>
						金额
					</td>
					<td>
						税率
					</td>
					<td>
						税额
					</td>
					<td>
						<label onclick="Tadd()">增加一行</label>
					</td>
				</tr>
				<!-- private String produceName;
	private String standardType;//规格型号
	private String unit;
	private String quantity;
	private String price;
	private String sumPrice;//金额
	private String fax;
	private String faxPrice;//税额 -->
				<c:forEach items="${rs.taxItems}" var="items">
					<tr>
						<td>
							<input type="text" name="tax.taxItems.produceName" value="${items.produceName }">
						</td>
						<td>
							<input type="text" name="tax.taxItems.standardType" value="${items.standardType }">
						</td>
						<td>
							<input type="text" name="tax.taxItems.unit " value="${items.unit }" size="5">
						</td>
						<td>
							<input type="text" name="tax.taxItems.quantity" value="${items.quantity }" size="12">
						</td>
						<td>
							<input type="text" name="tax.taxItems.price" value="${items.price }">
						</td>
						<td>
							<input type="text" name="tax.taxItems.sumPrice" value="${items.sumPrice }">
						</td>
						<td>
							<input type="text" name="tax.taxItems.fax" value="${items.fax }" size="5">
						</td>
						<td>
							<input type="text" name="tax.taxItems.faxPrice" value="${items.faxPrice }">
						</td>
						<td>
							<img src="images/del.gif" id="image"  onclick="Tdelete(this)" >
						</td>
					</tr>
				</c:forEach>

				<tr>
					<td>
						收货单位
					</td>
					<td></td>
					<td>
						备注
					</td>
					<td colspan="3">
						<textarea rows="3" cols="50" name="remarks">${rs.seller.remarks }</textarea>
				</tr>
				<tr>
					<td>
						<input type="submit" value="修改">
					</td>

					<td>
						<input type="button" value="上一条" onclick="showOther('previous')">
					</td>
					<td>
						当前${index[0] }条,共${index[1]-1 }条
					</td>
					<td>
						<input type="button" value="下一条" onclick="showOther('next')">
					</td>
				</tr>
				<input type="hidden" name="index" value="${index[0] }">
					<input type="hidden" name="no" value="${rs.no }">
			</table>
		</form>
  </body>
</html>
