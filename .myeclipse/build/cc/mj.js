Ext.onReady(function(){
	Ext.QuickTips.init();//提示link，如果a标签有title的话
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());//以cookie的方式记住用户对面板的部署
	Ext.BLANK_IMAGE_URL='image/blank.gif'
	var projectGridStore;
	
/**加载部门信息*/
	var dplistdata=[
  {did:'', name:"sdaac"},
  {did:10025, name:"物流部PC&L"}, 
  {did:10026, name:"运营支持与技术服务部"}, 
  {did:10030, name:"人事行政部HR&Admin"}, 
  {did:10031, name:"党工办"}, 
  {did:10036, name:"项目管理部"}, 
  {did:10038, name:"管理组(HVAC)"}, 
  {did:10039, name:"管理组(PTC)"}, 
  {did:10040, name:"产品工程部PE"}, 
  {did:10041, name:"客户满意部CS"}, 
  {did:10044, name:"制造工程部ME"}, 
  {did:10046, name:"IT"}, 
  {did:10047, name:"Finance"}, 
  {did:10048, name:"采购部"}, 
  {did:10049, name:"Sales"}, 
  {did:10052, name:"SAP"}, 
  {did:10057, name:"环保EHS"}, 
  {did:10058, name:"实验室"}
]
	
	var dplistStore=new Ext.data.JsonStore({
		data:dplistdata,
		fields:['did','name']
		
	})
	
		var departList=    new Ext.form.ComboBox({
                      id:'depart',
						fieldLabel: '部门',
                        name:'depart',
                      // hiddenName:'depart',
                        store:dplistStore,
                         displayField:'name',
                        valueField:'did',
                       
                       typeAhead: true,
                        mode: 'local',
                        triggerAction: 'all',
                        emptyText:'选择部门',
                        selectOnFocus:true
                       // width:190,
                       // blankText:'empty',
                      //  allowBlank:false
                    })
                
	var tab = new Ext.FormPanel({
       
       labelAlign: 'top',
	   applyTo: 'search',
       bodyStyle:'padding:5px,5px,0',
       frame:true,
       width: 500,
       items: [{
          layout:'column',
          items:
          [
          {
              columnWidth:.2,
              layout: 'form',
              items:[{
                fieldLabel: '项目负责人',
                xtype:'textfield',
                name: 'uid',
                id:'uid',
                tabIndex:0,
                anchor:'80%'
              }]              
          },
          {          
              columnWidth:.2,
              layout: 'form',
              items:[departList]
          },
          {          
              columnWidth:.2,
              layout: 'form',
              items:[{
                fieldLabel: '项目名称',
                xtype:'textfield',              
                name: 'teamName',
                id:'teamName',
                tabIndex:1,
                anchor:'80%'
              }]
          },
          {
              columnWidth:.2,
              layout: 'form',
              items:[{
                fieldLabel: '从',
               
                xtype:'datefield',
                format:'Y-m-d',
                value:new Date(),
                name: 'beginTime',
                id:'beginTime',
                anchor:'80%'              
         	 }]              
        }]              
        },
         {
              columnWidth:.2,
              layout: 'form',
              buttons:[{
			text:'search',
			handler:function(event)
			{
				var uid=Ext.get('uid').dom.value
				var depart=Ext.get('depart').dom.value
				//alert(depart)
				var benginTime=Ext.get('beginTime').dom.value
				var teamName=Ext.get('teamName').dom.value
				//var endTime=Ext.get('endTime').dom.value
				projectGridStore.load({   
				  params: {departmentName:depart, projectManagerName:uid,teamName:teamName},   
				  callback: function(r, options, success){   
				    if(success){   
				      Ext.Msg.alert('操作','成功！');   
				    }else{   
				      Ext.Msg.alert('操作','失败！');   
				    }   
				  }
				}); 

			}
		}]         
        }
        ]
     	
     
    });
	
	
	
	
	/**
	 * 子菜单
	 */
	var win=false;
		function search(){
		//alert('!')
//			var p = new Ext.Panel({
//		        title: 'My Panel',//标题
//		        collapsible:true,//右上角上的那个收缩按钮，设为false则不显示
//		       
//		        floating: true,
//		         renderTo: 'search',//这个panel显示在html中id为container的层中
//		        width:400,
//		        height:200,
//		        html: "<p>我是内容，我包含的html可以被执行！</p>"//panel主体中的内容，可以执行html代码
//   			 });
			/**
   			 var p=new Ext.Panel({
				    title: 'Drag me',
				    x: 100,
				    y: 100,
				    renderTo: 'search',
				    floating: true,
				    frame: true,
				    width: 400,
				    height: 200,
				    draggable: true,
				    closable:true,
				    plugins: new Ext.ux.TabCloseMenu()
				})
			*/
			

			 if(!win){
            win = new Ext.Window({
                applyTo     : 'body',
                layout      : 'fit',
                width       : 800,
                height      : 300,
                closeAction :'hide',
                plain       : true,
                items       : tab,
                    
                    //html:'<iframe frameBorder="0" scrolling="auto" height="100%" width="100%" src="vave/task/SearchTaskTbar_ext.jsp"></iframe>'
              

                buttons: [{
                    text     : '关闭',
                    handler  : function(){
                        //alert(document.getElementById('search').innerHTML)
                        win.hide();
                    }

                }
                ]
            });
        }
        win.show();

		}
	
	 
	
	  
	
	/**
	 * 主菜单
	 */

	 var btn1=new Ext.Button({
	 text:"搜索",
	  iconCls  :'icon-search',

	   handler:search
	 });
	
	this.toolbar=new Ext.Toolbar({
	 items:[btn1]
	});


		
//	    var tb = new Ext.Toolbar();
//  		tb.render('header');
//
//	    tb.add({
//	            text:'Button w/ Menu',
//	            iconCls: 'bmenu',  // <-- icon
//	            menu: menu  // assign menu by instance
//	        });
	
	function action(btn){
		 Ext.Msg.alert(btn.text)
		 
	}
	
	
	/**
	 * 菜单
	 
	function search(){
		alert('!')
//		new Ext.Panel({
//			id:'search',
//			title:'过滤',
//			width:'200',
//			height:'150',
//			applyTo:'search',
//			html:'<iframe frameBorder="0" id="main" name="main" scrolling="auto" height="100%" width="100%"></iframe>'
//			
//		})
	
	}
	
	var sear=new Ext.menu.BaseItem({
		text :'搜索'
		//toggleHandler: search
	})
	//sear.addListener('click' , search())
	
	var tableMenu=new Ext.menu.Menu({
		items:[
			sear
		]
	})
	
	
	
	toolbar=new Ext.Toolbar({
	 items:[sear]
	});
	/***/
	//var myData =
		
	
    function showUrl1(value)
	{
		
	  return  "<a class='x-btn' target='main' href='proposal.do?actionType=doSelecetMyTaskTeam&proposalID="+value+"'>详细</a>";
	
	}
	
	 projectGridStore=new Ext.data.JsonStore({
		autoLoad:true,
		url:'task.do?actionType=doSelectMyTeamTask',		
    	fields:[
    	'proposalId','planfinishtime','teamName',
    	{name:'estimatedCostSavings', mapping:'estimatedCostSavings',type:'float'},
    	{name:'projectManagerName',mapping:'name'},
    	{name:'projectManagerDepartName',mapping:'departmentName'},'state'
    	]
	})
	//projectGridStore.loadData(myData)
	  function change(val){
        if(val == 4){
            return '<span style="color:blue;">项目建立</span>';
        }else if(val ==5){
            return '<span style="color:red;">项目终止</span>';
        }else if(val ==6){
            return '<span style="color:green;">项目实施</span>';
        }else if(val ==7){
            return '<span style="color:green;">项目完成</span>';
        }else if(val ==9){
            return '<span style="color:brown;">等待产品/成本切换</span>';
        }
        return val;
    }
	
    function money(val)
    {
    		  return '<span style="padding-right:2px;color:red">'+'aaa'+'</span>';
    }
		  // row expander
    var expander = new Ext.grid.RowExpander({
        tpl : new Ext.Template(
            '<p><b>项目负责人:</b> {projectManagerName}</p><b>项目负责人部门:</b> {projectManagerDepartName}</p>' +
            '<b>计划完成日期:</b> {planfinishtime}</p><br>'+
               '<b>预期节约金额:</b> {estimatedCostSavings}</p><br>'
          //  '<p><b>Summary:</b> {desc}</p>'
        )
    });
    
    
    
	var cm=new Ext.grid.ColumnModel([
		 expander,
		  {
		 id:'proposalId',
         header: "详细",
         dataIndex: 'proposalId',
         renderer:showUrl1,
         width: 20
		},
		 {
		 id:'teamName',
         header: "项目名称",
         dataIndex: 'teamName',
         //renderer:showUrl1,
         width: 80
		},{
		 id:'estimatedCostSavings',
         header: "预期节约金额",
         dataIndex: 'estimatedCostSavings',
     //    rerenderer:   change,
		 sortable: true,
         width: 30
		},{
		 id:'planfinishtime',
         header: "预期完成日期",
         dataIndex: 'planfinishtime',
		 sortable: true,
         width: 50
		}
		,{
		 id:'state',
         header: "状态",
         dataIndex: 'state',
         renderer:change,
		 sortable: true,
         width: 50
		}
	])	
    
	var projectGrid=new Ext.grid.GridPanel({
		title:'项目列表',
		id:'west2',
		region:'center',
		width:'320',
		cm:cm,
			collapsible:true,
 		autoScroll:true,
		split:true,
		store:projectGridStore,	
		viewConfig:{
		 		 forceFit : true,
                getRowClass : function(record,rowIndex,rowParams,store){
                    //禁用数据显示红色
                    if(record.data.state==4){
                    
                        return  '<span style="color:green;"></span>';
                    }else{
                   
                        return '';
                    }     
                }

			
		},
		plugins: expander
	})
	
	
	
//	projectGridStore.load();	
	var rightP=new Ext.Panel({
		id:'centre2',
		region:'east',
		title:'项目内容',
		width:'600',
	//	tbar:rightpBar,
		collapsible:true,
 		autoScroll:true,
		split:true,
		html:'<iframe frameBorder="0" id="main" name="main" scrolling="auto" height="100%" width="100%"></iframe>'
		//html:'<iframe  scrolling="auto" frameborder="0" width="100%" height="100%" src="left.jsp"></iframe>'
	})
		
	var rightPanel=new Ext.Panel({
			id:'centre',
			region:'center',
			tbar:toolbar,
			width:'900',
			title:'项目管理',
			layout:'border',
			items:[projectGrid,rightP]
			
		
	})
	
	new Ext.Viewport({
		layout:'border',
		width:'1000',
		
		items:[rightPanel]
	})
})

  