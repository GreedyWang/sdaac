Ext.onReady(function() {
	 
	  var btn2=new Ext.Button({
	 	text:"导出KPI报表",
	  	iconCls  :'icon-grid',
	   	handler:function(){
	   		window.location.href='/bpp/index.do?actionType=selectOgIndexs ';
	   	}
	 });
	mainPanel(btn2,'/bpp/index.do?actionType=showEmpIndex&empUid=');
})