<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>vave提案单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<style type="text/css" media="screen">@import url(css/vave.css);</style>


  </head>

  <body >
	<tr><jsp:include flush="true" page="The Department/Select_TopBar_Form.jsp"></jsp:include></tr>
   <table id="idTable" width="100%">
   
   <thead><tr style="background-color: #eeeeee">
   <td><a href="javascript:void(0)" id="idIsOpen">是否</a> </td>
   <td>状态</td>
   <td><a href="javascript:void(0)" id="idAddtime">日期</a></td>
   <td><a href="javascript:void(0)" id="idTitle">提案人</a></td>
 
   <td colspan="2">主题</td>
   </tr></thead>
   <tbody>
   <c:forEach items="${selectVaveOthers}" var="item" >
   <tr>
   <td><img src="img/mail/${item.isOpen }.gif"></td>
   <td>${item.state_context }</td>
   <td>${item.lastModifyTime }</td>
   <td>${item.proposalPerson.name }</td>           
   <td colspan="2"><a href="proposal.do?actionType=doSelectByProposalIDD&proposalID=${item.id }">${item.title }</a></td>
   </tr>
   </c:forEach>
   </tbody>
  </table>
  <script type="text/javascript">
var $ = function (id) {
    return "string" == typeof id ? document.getElementById(id) : id;
};
var Class = {
  create: function() {
    return function() {
      this.initialize.apply(this, arguments);
    }
  }
}
Object.extend = function(destination, source) {
	for (var property in source) {
		destination[property] = source[property];
	}
	return destination;
}

Function.prototype.bind = function(object) {
  var __method = this, args = Array.prototype.slice.call(arguments); args.shift();
  return function() {
	return __method.apply(object, args.concat(Array.prototype.slice.call(arguments)));
  }
}

function Each(list, fun){
	for (var i = 0, len = list.length; i < len; i++) { fun(list[i], i); }
};


var TableOrder = Class.create();
TableOrder.prototype = {
  initialize: function(table) {
	this.tBody = $(table).tBodies[0];//tbody对象
	this.Rows = [];//行集合
	this._order = null;//排序对象
	
	Each(this.tBody.rows, function(o){ this.Rows.push(o); }.bind(this));
  },
  //排序并显示
  Sort: function() {
	//没有排序对象返回
	if(!this._order){ return false };
	//排序
	this.Rows.sort(this._order.Compare || this.Compare.bind(this));
	this._order.Down && this.Rows.reverse();//取反
	//显示表格
	var oFragment = document.createDocumentFragment();
	Each(this.Rows, function(o){ oFragment.appendChild(o); });
	this.tBody.appendChild(oFragment);
	//执行附加函数
	this._order.onSort();
  },
  //比较函数
  Compare: function(o1, o2) {
	var value1 = this.GetValue(o1), value2 = this.GetValue(o2);
	return value1 < value2 ? -1 : value1 > value2 ? 1 : 0;
  },
  //获取比较值
  GetValue: function(tr) {
	var td = tr.getElementsByTagName("td")[this._order.Index], data = td[this._order.Attri] || td.getAttribute(this._order.Attri);
	
	//数据转换
	switch (this._order.DataType.toLowerCase()) {
		case "int":
			return parseInt(data) || 0;
		case "float":
			return parseFloat(data) || 0;
		case "date":
			return Date.parse(data) || 0;
		case "string":
		default:
			return data.toString() || "";
	}
  },
  //添加并返回一个排序对象
  Add: function(index, options) {
	var oThis = this;
	return new function(){
		//默认属性
		this.Attri = "innerHTML";//获取数据的属性
		this.DataType = "string";//比较的数据类型
		this.Down = true;//是否按顺序
		this.onSort = function(){};//排序时执行
		this.Compare = null;//自定义排序函数
		Object.extend(this, options || {});
		//td索引
		this.Index = index;
		this.Sort = function(){ oThis._order = this; oThis.Sort(); };
	};
  }
}

var to = new TableOrder("idTable");
function SetOrder(obj, index, options){
	var o = $(obj);
	//_arr是记录排序项目（这里主要用来设置样式）
	!SetOrder._arr && (SetOrder._arr = []); SetOrder._arr.push(o);
	//添加一个排序对象
	var order = to.Add(index, options);
	order.onSort = function(){
		//设置样式
		Each(SetOrder._arr, function(o){ o.className = ""; });
		o.className = order.Down ? "down" : "up";
		//取相反排序
		order.Down = !order.Down
	}
	o.onclick = function(){ order.Sort(); return false; }
}
SetOrder("idTitle", 0);
SetOrder("idIsOpen",0);
SetOrder("idAddtime", 0, { Attri: "_order", DataType: "date" });


var order2 = to.Add(0, {
	onSort: function(){ Each(SetOrder._arr, function(o){ o.className = ""; }); },
	Compare: function(o1, o2) {
		var value1 = /x/i.test(to.GetValue(o1)), value2 = /x/i.test(to.GetValue(o2));
		return value1 && !value2 ? 1 : !value1 && value2 ? -1 : 0;
	}
});



</script>	
  </body>
  
</html>
