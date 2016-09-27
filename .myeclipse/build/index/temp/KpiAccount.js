Ext.BLANK_IMAGE_URL='img/blank.gif'
Ext.onReady(function(){
	var reader=new Ext.data.JsonReader({
		fields:[
    	'uid','departmentName',
    	{name:'name',mapping:'name'},
    	{name: 'score', type: 'float'} 
    	]	
	})
	var projectGridStore=new Ext.data.GroupingStore({
		//autoLoad:true,
	//	url:'employee.do?actionType=doSelectAllSalary',		
	//	url:'statistics.do?actionType=count',
    	reader:reader,
    	sortInfo:{field: 'score', direction: "DESC"},
    	groupField:'departmentName'
	})
	
	EmpManager.doAccount({uid:''},'2011-05',function(data){
		projectGridStore.loadData(data);
	});
	var cm=new Ext.grid.ColumnModel([
		// expander,
	
		{
		 id:'name',
         header: "姓名",
         dataIndex: 'name',
//         reader:function(val){		
//	        if(val =='闫少龙'){
//	            return '<span style="color:green;">' + val + '</span>';
//	        }else if(val < 0){
//	            return '<span style="color:red;">' + val + '</span>';
//	        }
//	        return val;
//    	} ,      	       
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
			 renderer:function( val ) { var rs = Math.round(val*1*10000)/10000;
			 							if(rs > 2) return 2;
			 							return rs},
			dataIndex:'score',
			summaryType:'average',
			width:100
		}
	])	
	
	var summary = new Ext.grid.GroupSummary(); 
	var theDownLoadUrl='../../empIndex.do?actionType=doDownLoadKpiAccount';
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
		//region:'center',
			tbar:toolbar,
		width:400,
		height:800,
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
})