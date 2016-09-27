var scoreList =function (){
	var reader=new Ext.data.JsonReader({
		fields:[
    	'uid','departmentName',
    	{name:'name',mapping:'name'},
    	{name: 'score', type: 'float'} 
    	]	
	})
	var projectGridStore=new Ext.data.GroupingStore({
    	reader:reader,
    	sortInfo:{field: 'score', direction: "DESC"},
    	groupField:'departmentName'
	})
	
	EmpManager.doAccount({uid:'5004'},function(data){
		projectGridStore.loadData(data);
	});
	var cm=new Ext.grid.ColumnModel([
		// expander,
	
		{
		 id:'name',
         header: "姓名",
         dataIndex: 'name',    	       
         width:100
		},
		{
		 id:'departmentName',
         header: "部门",
         dataIndex: 'departmentName',
         width:100
		},{
			id:'score',
			header:'KPI得分',
			dataIndex:'score',
			summaryType:'average',
			width:100
		}
	])	
	
	var summary = new Ext.grid.GroupSummary(); 
	var theDownLoadUrl='empIndex.do?actionType=doDownLoadKpiAccount';
	var btn1 = new Ext.Button({
				text : "下载",
				iconCls : 'icon-grid',
				handler : function(){
					window.location.href=theDownLoadUrl;
				}
			});

	var toolbar = new Ext.Toolbar({
				items : [btn1]
			});
	var projectGrid=new Ext.grid.GridPanel({
		title:'列表',
		id:'west2',
		tbar:toolbar,
		width:400,
		height:600,
		cm:cm,	
		store:projectGridStore,	
		renderTo:'search',
		plugins: summary,
		view: new Ext.grid.GroupingView({
            autoFill :false,
			forceFit:false,
			 startCollapsed: true,
            groupTextTpl: '{text} ({[values.rs.length]} {[ "人"]})'
        })	
	})	
	projectGrid.render();
}