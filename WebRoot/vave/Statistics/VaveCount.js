Ext.onReady(function(){
	Ext.QuickTips.init();//提示link，如果a标签有title的话
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());//以cookie的方式记住用户对面板的部署
	Ext.BLANK_IMAGE_URL='image/blank.gif'
	var projectGridStore;
	
	/**加载部门信息*/
	var dplistdata=[
  {id:1, name:"1"},
  {id:2, name:"2"}, 
  {id:3, name:"3"}, 
  {id:10030, name:"4"}
//  {id:10031, name:"党工办"}, 
//  {id:10036, name:"项目管理部"}, 
//  {id:10038, name:"管理组(HVAC)"}, 
//  {id:10039, name:"管理组(PTC)"}, 
//  {id:10040, name:"产品工程部PE"}, 
//  {id:10041, name:"客户满意部CS"}, 
//  {id:10044, name:"制造工程部ME"}, 
//  {id:10046, name:"IT"}, 
//  {id:10047, name:"Finance"}, 
//  {id:10048, name:"采购部"}, 
//  {id:10049, name:"Sales"}, 
//  {id:10052, name:"SAP"}, 
//  {id:10057, name:"环保EHS"}, 
//  {id:10058, name:"实验室"}
]
	
	var dplistStore=new Ext.data.JsonStore({
		data:dplistdata,
		fields:['id','name'	]
	})
//	DepartmemntManager.getAll(function(data){
//			dplistStore.loadData(data)
//	})
	
	var departList=    new Ext.form.ComboBox({
                      id:'depart',
						fieldLabel: '部门',
				//		fieldLabel
                        name:'depart',
                     //  hiddenName:'depart',
                        store:dplistStore,
                        valueField:'id',
                        displayField:'name',
                       typeAhead: true,
                        mode: 'local',
                        triggerAction: 'all',
                        emptyText:'选择季度',
                        selectOnFocus:true,
                        width:80,
                        blankText:'empty'
                    })

	
//	/**搜索栏*/
//	var tab = new Ext.FormPanel({
//       
//       labelAlign: 'top',
//	   applyTo: 'search',
//       bodyStyle:'padding:5px,5px,0',
//       frame:true,
//       width: 500,
//       items: [{
//          layout:'column',
//          items:
//          [
//          {
//              columnWidth:.2,
//              layout: 'form',
//              items:[{
//                fieldLabel: '姓名',
//                xtype:'textfield',
//                name: 'uname',
//                id:'uname',
//                tabIndex:0,
//                anchor:'80%'
//              }]              
//          },departList
//         ]              
//        },
//         {
//              columnWidth:.2,
//              layout: 'form',
//              buttons:[{
//			text:'search',
//			handler:function(event)
//			{
//
//				var year=Ext.get('year').dom.value
//				projectGridStore.load({   
//				  params: {searchYear:year} 
////				  callback: function(r, options, success){   
////				    if(success){   
////				      Ext.Msg.alert('操作','成功！');   
////				    }else{   
////				      Ext.Msg.alert('操作','失败！');   
////				    }   
////				  }//callback function end   
//				}); 
//				//projectGridStore.load(params:{})
//				
//				
//			}
//		}]         
//        }
//        ]
//    });
	//排名 员工名称 提案人部门 提案总数 批准数 完成数   
    //	'departmentName','proposalNum','finishNum','doNum'
		var cm=new Ext.grid.ColumnModel([
		// expander,
	
		{
		 id:'uname',
         header: "姓名",
         dataIndex: 'uname',
         width:100
		},
		{
		 id:'departmentName',
         header: "部门",
         dataIndex: 'departmentName',
         width:100
		},{
			id:'proposalNum',
			header:'提案总数',
			dataIndex:'proposalNum',
					 summaryType:'sum',
			 width:100
		},{
			id:'doNum',
			header:'批准数',
			dataIndex:'doNum',
			sortable:true,
					 summaryType:'sum',
			 width:60
		},{
			id:'hlh',
			header:'合理化建议数',
			dataIndex:'hlh',
			summaryType:'sum',
			 width:80
		},{
			id:'total_saving',
			header:'节约总金额',
			dataIndex:'total_saving',
			 summaryType:'sum',
			 width:70
		},{
			id:'level',
			header:'星级',
			dataIndex:'level',
			// summaryType:'sum',
			 width:50
		},{
			id:'allProjects',
			header:'负责项目数',
			dataIndex:'allProjects',
			 summaryType:'sum',
			 width:60
		},{
			id:'WAITCHANGE',
			header:'等待产品/成本切换数',
			dataIndex:'WAITCHANGE',
			 summaryType:'sum',
			 width:100
		},{
			id:'DO_PROJECT',
			header:'项目完成数',
			dataIndex:'DO_PROJECT',
			 summaryType:'sum',
			 width:80
		},{
			id:'finishNum',
			header:'完成数',
			dataIndex:'finishNum',
			 summaryType:'sum',
			 width:50
		},{
			id:'total_saving',
			header:'当年度预计节约金额',
			dataIndex:'curesyearSaving',
			 summaryType:'sum',
			 width:70
		},{
			id:'total_saving',
			header:'年度预计节约金额',
			dataIndex:'esyearSaving',
			 summaryType:'sum',
			 width:70
		}
		,{
			id:'curyearSaving',
			header:'当年度完成节约金额',
			dataIndex:'curyearSaving',
			 summaryType:'sum',
			 width:70
		},{
			id:'yearSaving',
			header:'年度完成节约金额',
			dataIndex:'yearSaving',
			 summaryType:'sum',
			 width:70
		}
	])	


	
	/**
	 * 子菜单
	 */
	var win=false;
		function search(){

				var year=Ext.get('year').dom.value
				var m=Ext.get('depart').dom.value
				projectGridStore.load({   
				  params: {searchYear:year,m:m},   
				  callback: function(r, options, success){   
				    if(success){   
				      Ext.Msg.alert('操作','成功！');   
				    }else{   
				      Ext.Msg.alert('操作','失败！');   
				    }   
				  }//callback function end   
				}); 
				//projectGridStore.load(params:{})

		}	  
	
	/**
	 * 主菜单
	 */


	
	 var btn1=new Ext.form.DateField({
	 	id:'year',
	 	format :'Y'
	 })
	 

	 
	 function toExcel()
	 {
	 	window.location.href='/bpp/index.do?actionType=selectDepEmpsIndexs ';
	 }
	 
	  var btn2=new Ext.Button({
	 text:"查询",
	  iconCls  :'icon-grid',
	   handler:search
	 });
	 
	this.toolbar=new Ext.Toolbar({
	 items:[btn1,departList,btn2]
	});

	
	function action(btn){
		 Ext.Msg.alert(btn.text)
		 
	}	
	
    function showUrl1(value)
	{
	  return  "<a class='x-btn' target='context' href='index.do?actionType=doSelectEmpIndex&empUid="+value+"'>详细</a>";
	}		
	
	var reader=new Ext.data.JsonReader({
		fields:[
    	'uid',
    	{name:'uname',mapping:'uname'},
    	{name:'level',mapping:'level'},
    	{name:'total_saving', type: 'float'},
    	{name: 'hlh', type: 'int'},
    	{name: 'proposalNum', type: 'int'},
    	{name: 'finishNum', type: 'int'},
    	{name: 'doNum', type: 'int'},
    	{name: 'WAITCHANGE', type: 'int'},
    	{name: 'DO_PROJECT', type: 'int'},
    	{name: 'BEGIN_PROJECT', type: 'int'},
    	{name: 'allProjects', type: 'int'},
    	'departmentName','esyearSaving','curesyearSaving','curyearSaving','yearSaving'
 	 
    	]
	
	})
	
	 projectGridStore=new Ext.data.GroupingStore({
		autoLoad:true,
	//	url:'employee.do?actionType=doSelectAllSalary',		
		url:'statistics.do?actionType=count',
    	reader:reader,
    	sortInfo:{field: 'proposalNum', direction: "DESC"},
    	groupField:'departmentName'
	})
	//projectGridStore.loadData(myData)
	
		  // row expander
    var expander = new Ext.grid.RowExpander({
        tpl : new Ext.Template(
            '<p><b>工号:</b> {uid}</p><br>'
          //  '<p><b>Summary:</b> {desc}</p>'
        )
    });
    
    var summary = new Ext.grid.GroupSummary(); 
    
	var projectGrid=new Ext.grid.GridPanel({
		title:'统计列表-点击部门展开',
		id:'west2',
		region:'center',
		width:'100%',
		height:'50%',
		cm:cm,	
		store:projectGridStore,	
		 
		plugins: summary,
		view: new Ext.grid.GroupingView({
            autoFill :false,
			forceFit:false,
			 startCollapsed: true,
            groupTextTpl: '{text} ({[values.rs.length]} {[ "人"]})'
        })

	//	plugins: expander
		
	})

		
	var rightPanel=new Ext.Panel({
			id:'centre',
			region:'center',
			tbar:toolbar,
			width:'100%',
			layout:'border',
			collapsible : true,
			items:[projectGrid]
			
		
	})
	
//	var bottom = new Ext.Panel({
//			id:'aa',
//			region:'south',
//			title:'参与率',
//			collapsible : true,
//			split: true,
//			html:"<iframe scrolling='auto' frameborder='0' width='100%' height='100%' src='statistics.do?actionType=rateAcount'></iframe>",
//			height:350
//	})
	
	new Ext.Viewport({
		layout:'border',
		width:'100%',
		
		items:[rightPanel]
	})
})

  