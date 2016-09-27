<%@ page language="java" pageEncoding="utf-8"%>
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

		<title></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link type="text/css" href="css/themes/base/ui.all.css"
			rel="stylesheet" />
				<link type="text/css" href="css/demos.css"
			rel="stylesheet" />
		<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="js/ui/ui.core.js"></script>
		<script type="text/javascript" src="js/ui/ui.accordion.js"></script>

		<script type="text/javascript">
	$(function() {
		$("#accordion").accordion();
		$("#accordion2").accordion();
		$('#context tr').mouseover(function()
			{
				$(this).addClass('highlight2')
			}).mouseout(function(){
				$(this).removeClass('highlight2')
			})	
	
		
	
	});
	
	

	
	
	</script>
	<style type="text/css">
		.highlight2 {
        background:#bcd4ec;  /*这个将是鼠标高亮行的背景色*/
        cursor:pointer;}

		.aa{width: 27%;margin: 0px;float: right}
		.bb{font-size: 10px;}
		label, input { display:block; }
		input.text { margin-bottom:12px; width:95%; padding: .4em; }
		fieldset { padding:0; border:0; margin-top:25px; }
		h1 { font-size: 1.2em; margin: .6em 0; }
		
		
	</style>
	</head>
	<body style="background-image: url('../iamges/icons/group.png')">
	
	
	
	
	<div  style="width:70%;float: left;height: 100%;" class="ui-widget ui-widget-content">
		<table width="100%" id="context">
				<thead>
		
					<tr class="ui-widget-header ">
						<th>Name</th>
						<th>Email</th>
						<th>Password</th>
					</tr>
				</thead>
				<tbody>
		
					<tr>
						<td>John Doe</td>
						<td>john.doe@example.com</td>
						<td>johndoe1</td>
					</tr>
						<tr>
						<td>John Doe</td>
						<td>john.doe@example.com</td>
						<td>johndoe1</td>
					</tr>
						<tr>
						<td>John Doe</td>
						<td>john.doe@example.com</td>
						<td>johndoe1</td>
					</tr>
				</tbody>
		</table>
	</div>

	
		<div   >

			<div id="accordion" style="height:  30%" class="aa " >
					<h3>
						<a href="#">Section 1</a>
					</h3>
					<div>
						<p>
							Mauris mauris ante, blandit et, ultrices a, suscipit eget, quam.
							Integer ut neque. Vivamus nisi metus, molestie vel, gravida in,
							condimentum sit amet, nunc. Nam a nibh. Donec suscipit eros. Nam
							mi. Proin viverra leo ut odio. Curabitur malesuada. Vestibulum a
							velit eu ante scelerisque vulputate.
						</p>
					</div>
					<h3>
						<a href="#">Section 2</a>
					</h3>
					<div>
						<p>
							Sed non urna. Donec et ante. Phasellus eu ligula. Vestibulum sit
							amet purus. Vivamus hendrerit, dolor at aliquet laoreet, mauris
							turpis porttitor velit, faucibus interdum tellus libero ac justo.
							Vivamus non quam. In suscipit faucibus urna.
						</p>
					</div>
					<h3>
						<a href="#">Section 3</a>
					</h3>
					<div>
						<p>
							Nam enim risus, molestie et, porta ac, aliquam ac, risus. Quisque
							lobortis. Phasellus pellentesque purus in massa. Aenean in pede.
							Phasellus ac libero ac tellus pellentesque semper. Sed ac felis.
							Sed commodo, magna quis lacinia ornare, quam ante aliquam nisi, eu
							iaculis leo purus venenatis dui.
						</p>
						<ul>
							<li>
								List item one
							</li>
							<li>
								List item two
							</li>
							<li>
								List item three
							</li>
						</ul>
					</div>
					<h3>
						<a href="#">Section 4</a>
					</h3>
					<div>
						<p>
							Cras dictum. Pellentesque habitant morbi tristique senectus et
							netus et malesuada fames ac turpis egestas. Vestibulum ante ipsum
							primis in faucibus orci luctus et ultrices posuere cubilia Curae;
							Aenean lacinia mauris vel est.
						</p>
						<p>
							Suspendisse eu nisl. Nullam ut libero. Integer dignissim consequat
							lectus. Class aptent taciti sociosqu ad litora torquent per
							conubia nostra, per inceptos himenaeos.
						</p>
					</div>
			</div>
			
	</div>
			
	</body>
</html>
