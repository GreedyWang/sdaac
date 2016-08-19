function ca(paramUrl)
{
		Ext.QuickTips.init();//提示link，如果a标签有title的话
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());//以cookie的方式记住用户对面板的部署
	Ext.BLANK_IMAGE_URL='image/blank.gif'
	var projectGridStore;
	
	/**加载部门信息*/
	var dplistdata=[
  {id:'', name:"sdaac"},
  {id:10025, name:"物流部PC&L"}, 
  {id:10026, name:"运营支持与技术服务部"}, 
  {id:10030, name:"人事行政部HR&Admin"}, 
  {id:10031, name:"党工办"}, 
  {id:10036, name:"项目管理部"}, 
  {id:10038, name:"管理组(HVAC)"}, 
  {id:10039, name:"管理组(PTC)"}, 
  {id:10040, name:"产品工程部PE"}, 
  {id:10041, name:"客户满意部CS"}, 
  {id:10044, name:"制造工程部ME"}, 
  {id:10046, name:"IT"}, 
  {id:10047, name:"Finance"}, 
  {id:10048, name:"采购部"}, 
  {id:10049, name:"Sales"}, 
  {id:10052, name:"SAP"}, 
  {id:10057, name:"环保EHS"}, 
  {id:10058, name:"实验室"}
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
                        emptyText:'选择部门',
                        selectOnFocus:true,
                        width:190,
                        blankText:'empty',
                        allowBlank:false
                    })

//	var departList=new Ext.form.ComboBox({
//		fieldLabel:'部门',
//		name:'depart',
//		store:dplistStore,
//		valueField:'id',
//		displayField:'name',
//		emptyText:'选择部门'
//	
//	})
	
	/**搜索栏*/
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
                fieldLabel: '姓名',
                xtype:'textfield',
                name: 'uname',
                id:'uname',
                tabIndex:0,
                anchor:'80%'
              }]              
          },departList
         ]              
        },
         {
              columnWidth:.2,
              layout: 'form',
              buttons:[{
			text:'search',
			handler:function(event)
			{
				var uname=Ext.get('uname').dom.value
			//	var depart=Ext.get('depart').dom.value
				var depart=departList.getValue() 
				//var endTime=Ext.get('endTime').dom.value
				projectGridStore.load({   
				  params: {departId:depart, uname:uname},   
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
		}]         
        }
        ]
    });
	
		var cm=new Ext.grid.ColumnModel([
		// expander,
		  {
		 id:'uid',
         header: "详细",
         dataIndex: 'uid',
         renderer:showUrl1,
         width: 50
		},
		{
		 id:'uname',
         header: "姓名",
         dataIndex: 'uname',
         width:50
		},
		{
		 id:'departId',
         header: "部门",
         dataIndex: 'departId',
         width:50
		}
	])	
	
	
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
                width       : 300,
                height      : 100,
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
	
	 function toExcel()
	 {
	 	window.location.href='/bpp/index.do?actionType=selectDepEmpsIndexs ';
	 }
	 
	  var btn2=new Ext.Button({
	 text:"到出excel",
	  iconCls  :'icon-grid',
	   handler:toExcel
	 });
	 
	this.toolbar=new Ext.Toolbar({
	 items:[btn1,btn2]
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
	//[{"uid":"0338","name":"蔡震"},{"uid":"5003","name":"许磊敏"},{"uid":"5004","name":"闫少龙"},{"uid":"8001","name"
	//:"储赵波"},{"uid":"8002","name":"王永敏"},{"uid":"8019","name":"张嘉羽"}]
		

    function showUrl1(value)
	{
	  return  "<a class='x-btn' target='context' href='"+paramUrl+value+"'>详细</a>";
	}
	
	var reader=new Ext.data.JsonReader({
		fields:[
    	'uid',
    	{name:'uname',mapping:'uname'},
    	'departId'
    	]
	
	})
	
	 projectGridStore=new Ext.data.GroupingStore({
		autoLoad:true,
		url:'employee.do?actionType=doSelectAllSalary',		
    	reader:reader,
    	  sortInfo:{field: 'departId', direction: "ASC"},
    	groupField:'departId'
	})
	//projectGridStore.loadData(myData)
	
		  // row expander
    var expander = new Ext.grid.RowExpander({
        tpl : new Ext.Template(
            '<p><b>工号:</b> {uid}</p><br>'
          //  '<p><b>Summary:</b> {desc}</p>'
        )
    });
    

    
	var projectGrid=new Ext.grid.GridPanel({
		title:'人员列表',
		id:'west2',
		region:'center',
		width:'25%',
		cm:cm,	
		store:projectGridStore,	
		view: new Ext.grid.GroupingView({
            autoFill :false,
			forceFit:false,
            groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "Items" : "Item"]})'
        })

	//	plugins: expander
		
	})
//	projectGridStore.load();	
	var rightP=new Ext.Panel({
		id:'centre2',
		region:'east',
		title:'指标管理',
		width:'75%',
		collapsible:true,
 		autoScroll:true,
		split:true,
		html:'<iframe frameBorder="0" id="context" name="context" scrolling="auto" height="100%" width="100%"></iframe>'
		//html:'<iframe  scrolling="auto" frameborder="0" width="100%" height="100%" src="left.jsp"></iframe>'
	})
		
	var rightPanel=new Ext.Panel({
			id:'centre',
			region:'center',
			tbar:toolbar,
			width:'100%',
			title:'KPI',
			layout:'border',
			items:[projectGrid,rightP]
			
		
	})
	
	new Ext.Viewport({
		layout:'border',
		width:'100%',
		
		items:[rightPanel]
	})
}