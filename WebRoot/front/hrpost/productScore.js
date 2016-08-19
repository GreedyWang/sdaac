Ext.onReady(function (){
		Ext.BLANK_IMAGE_URL='images/default/blank.gif'
			var gridPanel;
			//var zone='104A';
			  function change(val){
			val = Math.round(val*100)/100;
        if(val > 1){
            return '<span style="color:green;">' + val + '</span>';
        }else {
            return '<span style="color:red;">' + val + '</span>';
        }
        return val;
    }
			
	var cm=new Ext.grid.ColumnModel([	  
//			'carType','figureId','inboundNum','picesNum','need','productArea','realWorkTime','remark','standWorkTime'		  
//	图号 物料号 名称 标准工时  入库数 目标工时 加工 翅片数 实际工作时间 得分 
				{
			      	id:'1',
			      	header:'图号',
			      	width:80,
			      	sortable:true,
			      	dataIndex:'figureId'
			      },    {
			      	id:'2',
			      	header:'物料号(sap号)',
			      	width:80,
			      	sortable:true,
			      	dataIndex:'remark'
			      },   {
			      	id:'3',
			      	header:'名称',
			      	width:80,
			      	sortable:true,
			      	dataIndex:'carType'
			      },{
			      	id:'productArea',
			      	header:'生产区间',
			      	width:60,
			      	sortable:true,
			      	dataIndex:'productArea'
			      },{
			      	id:'4',
			      	header:'备注',
			      	width:80,
			      	sortable:true,
			      	dataIndex:'name'
			      },{
			      	id:'5',
			      	header:'类型',
			      	width:60,
			      	sortable:true,
			      	dataIndex:'type'
			      },{
			      	id:'6',
			      	header:'标准工时 ',
			      	width:70,
			      	sortable:true,
			      		 renderer: function(v, params, record){
                    		  return   Math.round(record.data.standWorkTime *100)/100;
              		  }
			      },   {
			      	id:'7',
			      	header:'入库数',
			      	width:50,
			      	sortable:true,
			      	dataIndex:'inboundNum'
			      }, 
//			      {
//			      	id:'6',
//			      	header:'目标工时',
//			      	width:'',
//			      	sortable:true,
//			      	dataIndex:''
//			      }, 
			      {
			      	id:'lineNO',
			      	header:'车线',
			      	width:50,
			      	sortable:true,
			      	dataIndex:'lineNO'
			      },
			      {
			      	id:'8',
			      	header:'翅片数',
			      	width:50,
			      	sortable:true,
			      	summaryType:'sum',
			      	dataIndex:'picesNum'
			      },   {
			      	id:'9',
			      	header:'实际工作时间',
			      	width:100,
			      	sortable:true,
			      	 renderer: function(v, params, record){
                    		  return   Math.round(record.data.realWorkTime *100)/100;
              		  },
              		  dataIndex:'realWorkTime',
			      	summaryType:'sum'
			      },    {
			      	id:'10',
			      	header:'得分',
			      	width:70,
			      	//summaryType:'sum',
			      	sortable:true,
			      	  renderer: function(v, params, record){
                    	if(record.data.inboundNum*record.data.standWorkTime==0)
                    	{
                    		return 0;
                    	}else{ 
                    		return change(record.data.inboundNum*record.data.standWorkTime/ ( record.data.realWorkTime*3600));
                    	} 
              		  }
				 }
				 /**
				  * 临时注解
				  */
//				 ,  
//				 	{
//			      	id:'sc2',
//			      	header:'得分2',
//			      	width:100,
//			      	sortable:true,
//			      	summaryType:'sum',
//			      	 dataIndex:'score2'
//				 	}                   		    
	]);
	
	
	
	var reader=new Ext.data.JsonReader({
		fields:[
    	'score2','carType','figureId', 'lineNO','inboundNum','picesNum','need','productArea','realWorkTime','remark','standWorkTime','type','name','productArea'
    	]
	
	})
	
	var dataStore=new Ext.data.GroupingStore({
			autoLoad:true,
			url:'figureAndPostId.do?actionType=showFigure',
			reader:reader,
			sortInfo:{field: 'realWorkTime', direction: "DESC"},
			groupField:'lineNO'
	})
	/**
	 * 下载
	 */
	 function toExcel()
	 {
	 	var year=Ext.get('date').dom.value
	 	var productNo = Ext.get('proudctNo').dom.value
	 	//if(produceType)
	 	var produceArea='';
			for(var i = 0 ;i<pas.length;i++){
				produceArea+=pas[i]+','
			}
	 	var url = '/bpp/figureAndPostId.do?actionType=showFigure&produceType='+produceType+'&produceArea='+produceArea+'&productNo='+productNo+'&date='+year+'&isDownLoad=true';
	 	url=encodeURI(url);
	 	window.location.href= encodeURI(url);
	 }
		
	 /**
	  * 查询
	  */
	 function search(){
			var year=Ext.get('date').dom.value
			var productNo = Ext.get('proudctNo').dom.value
			var produceArea='';
			for(var i = 0 ;i<pas.length;i++){
				produceArea+=pas[i]+','
			}
			dataStore.reload({   
			  params: {proudctNo:productNo,produceType:produceType,produceArea:produceArea,date:year},   
			  callback: function(r, options, success){   
			    if(success){   
			      Ext.Msg.alert('操作','成功！');   
			      
			    }else{   
			      Ext.Msg.alert('操作','失败！');   
			    }   
			  }
			}); 
		}
		
		var menu = new Ext.menu.Menu({
        id: 'mainMenu',
        items: [
         {
                text: '车间选择',
          
                menu: {        // <-- submenu by nested config object
                    items: [
                        // stick any markup in a menu
                        '<b class="menu-title">选择数据来源</b>',
                        {
                            //xtype:'checkboxgroup',
                            text: '104A',
                            checked: false,                       
                         //   group: 'theme',
                            checkHandler: onProduceAreaClick
                        }, {
                            text: '104B',
                            checked: false,
                        //    group: 'theme',
                            checkHandler: onProduceAreaClick
                        }, {
                            text: '100',
                            checked: false,
                       //     group: 'theme',
                            checkHandler: onProduceAreaClick
                        }, {
                            text: '101',
                            checked: false,
                       //     group: 'theme',
                            checkHandler: onProduceAreaClick
                        }, {
                            text: '102',
                            checked: false,
                       //     group: 'theme',
                            checkHandler: onProduceAreaClick
                        }, {
                            text: '103',
                            checked: false,
                       //     group: 'theme',
                            checkHandler: onProduceAreaClick
                        }, {
                            text: '出口',
                            checked: false,
                       //     group: 'theme',
                            checkHandler: onProduceAreaClick
                        }
                    ]
                }
            }, {
                text: '产品类型',
          
                menu: {        // <-- submenu by nested config object
                    items: [
                        // stick any markup in a menu
                        '<b class="menu-title">选择数据来源</b>',
                        {
                            text: '零件',
                            checked: false,                       
                            group: 'productType',
                            checkHandler: onProduceTypeClick
                        }, {
                            text: '芯体',
                            checked: false,
                            group: 'productType',
                            checkHandler: onProduceTypeClick
                        },  {
                            text: '总成',
                            checked: false,
                            group: 'productType',
                            checkHandler: onProduceTypeClick
                        }, {
                            text: 'All',
                            checked: true,
                            group: 'productType',
                            checkHandler: onProduceTypeClick
                        }
                    ]
                }
            }
        ]
    });
	
	var produceType='All';
//	var produceArea='false,false,false,false,false,false,false';
	var pas =['false','false','false','false','false','false','false'];
 function onProduceTypeClick(item){
 		produceType=item.text;
    }
    function onProduceAreaClick(item, checked){   	
    	  if(item.text=='104A'){
    	  	pas[1]=checked;
        }else if(item.text=='104B'){
        	pas[0]=checked;
       }else if(item.text=='100'){
        	pas[2]=checked;
        }else if(item.text=='101'){
        	pas[3]=checked;
        }else if(item.text=='102'){
        	pas[4]=checked;
        }else if(item.text=='103'){
        	pas[5]=checked;
       }else if(item.text=='出口'){
        	pas[6]=checked;
        }
    }
	
    var productNoLabel = new Ext.form.Label({
    	 text:"图号:",
    	 id: 'productNoLabel'
    })
    
    var productNo = new Ext.form.Field({
    	 text:"图号",
    	 id: 'proudctNo'
    })
    
	var btn1=new Ext.form.DateField({
	 	id:'date',
	 	format :'Y-m'
	 })
	  var btn2=new Ext.Button({
	 text:"查询",
	  iconCls  :'icon-search',
	   handler:search
	 });
	   var btn3=new Ext.Button({
	 text:"下载",
	  iconCls  :'icon-grid',
	   handler:toExcel
	 });
	 
	var toolbar=new Ext.Toolbar({
	 items:[productNoLabel,productNo,btn1,btn2,btn3
	 ,{
			text:'统计类型',
            iconCls: 'icon-grid',  // <-- icon
            menu: menu  // assign menu by instance
        }
        ]
	
	});


	 var summary = new Ext.grid.GroupSummary(); 
	 gridPanel=new Ext.grid.GridPanel({
		title:'产品绩效',
			cm:cm,
			store:dataStore,
			renderTo:'gridContext',
			tbar:toolbar,
			height:800,
			width:850,
			plugins: summary,
		    view: new Ext.grid.GroupingView({
            autoFill :false,
			forceFit:false,
			 startCollapsed: true,
            groupTextTpl: '{text} ({[values.rs.length]} {[ "个"]})'
        })
	})
	
		
})