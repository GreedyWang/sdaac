<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src='HumanResource/assembly_room/js/dhtmlxscheduler/dhtmlxscheduler_debug.js' type="text/javascript" ></script>
	<script src='HumanResource/assembly_room/js/dhtmlxscheduler/dhtmlxscheduler_timeline.js' type="text/javascript"></script>
	<script src='HumanResource/assembly_room/js/dhtmlxscheduler/dhtmlxscheduler_treetimeline.js' type="text/javascript"></script>
	
	<link rel='stylesheet' type='text/css' href='HumanResource/assembly_room/css/dhtmlxscheduler.css'>
	<link rel="stylesheet" href="HumanResource/assembly_room/css/dhtmlxscheduler_ext.css" type="text/css" >
	<style type="text/css">
	html, body{
		margin:0px;
		padding:0px;
		height:100%;
		overflow:hidden;
	}	
	.one_line{
		white-space:nowrap;
		overflow:hidden;
		padding-top:5px; padding-left:5px;
		text-align:left !important;
	}
	</style>
	<script type="text/javascript" charset="utf-8">
	function init() {

		scheduler.locale.labels.timeline_tab = "Timeline";
		scheduler.locale.labels.section_custom="Section";
		scheduler.config.details_on_create=true;
		scheduler.config.details_on_dblclick=true;
		scheduler.config.xml_date="%Y-%m-%d %H:%i";

		//===============
		//Configuration
		//===============	
	
		elements = [ // original hierarhical array to display
			{key:10, label:"行政楼", open: true, children: [
				{key:1, label:"行政楼1#会议室带投影仪"},		
				{key:2, label:"行政楼2#会议室"},
				{key:3, label:"行政楼3#会议室"},
				{key:4, label:"行政楼4#电话会议室"}
			]},
			{key:60, label:"技术中心", open: true, children: [	
				{key:5, label:"技术中心VIP贵宾室"},
				{key:6, label:"技术中心培训室"}
			]}
		];
	
		
		scheduler.createTimelineView({
			section_autoheight: false,
			name:	"timeline",
			x_unit:	"minute",
			x_date:	"%H:%i",
			x_step:	30,
			x_size: 24,
			x_start: 16,
			x_length:	48,
			y_unit: elements,
			y_property:	"section_id",
			render: "tree",
			folder_events_available: true,
			dy:60
		});
		
		

		//===============
		//Data loading
		//===============
		scheduler.config.lightbox.sections=[	
			{name:"description", height:130, map_to:"text", type:"textarea" , focus:true},
			{name:"custom", height:23, type:"timeline", options:null , map_to:"section_id" }, //type should be the same as name of the tab
			{name:"time", height:72, type:"time", map_to:"auto"}
		]
		
		scheduler.init('scheduler_here',new Date(),"timeline");
		// scheduler.config.show_loading=true;
		var url = 'roomSystem.do?actionType=doShowSchedule&num ='+new Date()
		scheduler.load(url);
	//	scheduler.load("../common/events20102.xml");


		//var dp = new dataProcessor("url");
		//dp.init(scheduler);
	}
	
</script>
  </head>
 
<body onload="init();">
	<div id="scheduler_here" class="dhx_cal_container" style='width:100%; height:100%;'>
		<div class="dhx_cal_navline">
			<div class="dhx_cal_prev_button">&nbsp;</div>
			<div class="dhx_cal_next_button">&nbsp;</div>
			<div class="dhx_cal_today_button"></div>
			<div class="dhx_cal_date"></div>
			<div class="dhx_cal_tab" name="day_tab" style="right:204px;"></div>
			<div class="dhx_cal_tab" name="week_tab" style="right:140px;"></div>
			<div class="dhx_cal_tab" name="timeline_tab" style="right:280px;"></div>
			<div class="dhx_cal_tab" name="month_tab" style="right:76px;"></div>
		</div>
		<div class="dhx_cal_header">
		</div>
		<div class="dhx_cal_data">
		</div>		
	</div>
</body>
</html>
