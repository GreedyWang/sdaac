Ext.onReady(function (){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget='side'
		Ext.BLANK_IMAGE_URL='images/default/blank.gif'
	var fm= Ext.form
	var cm=new Ext.grid.ColumnModel([
	{
		   header: "Common Name",
           dataIndex: 'simple',
           width: 220,
           editor: new fm.TextField({
               allowBlank: false
           })

	},
	{
		header:'名称',
		dataIndex:'orgin',
		width:200,
		editor: new fm.TextField({
			allowBlank: false
		})
	}
	
	])
		
	var comSupply=new Ext.form.Field({
		fieldLabel:'推荐供应商',
		name:'prForm.recommendedSupplier'
	});

		var mustSupply=new Ext.form.Field({
		//fieldLabel:'名称，标准/样本参考质料',
		fieldLabel:'指定供应商',
		name:''

	});
	
		var ppt=new Ext.form.Field({
		//fieldLabel:'名称，标准/样本参考质料',
		fieldLabel:'交货地点/收获人/电话',
		name:'prForm.receivingPlacePerpoleTel'

	});
	
	var approvedCk=new Ext.form.Radio({
		boxLabel:'计划内',
		name:'prForm.isPlan',
		inputValue:0
	
	})
		var approvedCk2=new Ext.form.Radio({
		boxLabel:'计划外',
		name:'prForm.isPlan',
		inputValue:1

	})
	var approvedGroup=new Ext.form.RadioGroup({
	items:[approvedCk,approvedCk2]
	}) 
	
	var data=[{simple:'aaa',orgin:'AAA'},
				{simple:'bbb',orgin:'BBB'},
				{simple:'ccc',orgin:'CCC'}];
				
//	var data=[{simple:'aaa',orgin:'AAA'},
//			{simple:'bbb',orgin:'BBB'},
//			{simple:'ccc',orgin:'CCC'}];
	
	var store=new Ext.data.JsonStore({
		data:data,
		
			fields:['simple','orgin']
			
	
	})
	
		var context=new Ext.grid.EditorGridPanel({
		cm:cm,
		width:300,
		store:store,
		height:300,
		applyTo:'test'
	})
	
	var buyList=    new Ext.form.ComboBox({
                        fieldLabel: '采购员',
                     //   hiddenName:'state',
                        name:'prForm.tempolyeeByApplicantId.uid',
                        store:store,
                        valueField:'simple',
                        displayField:'orgin',
                        typeAhead: true,
                        mode: 'local',
                        triggerAction: 'all',
                        emptyText:'Select a state...',
                        selectOnFocus:true,
                        width:190,
                        blankText:'empty',
                        allowBlank:false
                    })
                    
    	var costList=    new Ext.form.ComboBox({
                        fieldLabel: '成本中心/AR NO.',
                     //   hiddenName:'state',
                        name:'prForm.prCostCenter.costCenterName',
                        store:store,
                        valueField:'simple',
                        displayField:'orgin',
                        typeAhead: true,
                        mode: 'local',
                        triggerAction: 'all',
                        emptyText:'Select a state...',
                        selectOnFocus:true,
                        width:190,
                        blankText:'empty',
                        allowBlank:false
                    })
	
		var typeList=    new Ext.form.ComboBox({
                        fieldLabel: '材料种类',
                     //   hiddenName:'state',
                        name:'prForm.type',
                        store:store,
                        valueField:'simple',
                        displayField:'orgin',
                        typeAhead: true,
                        mode: 'local',
                        triggerAction: 'all',
                        emptyText:'Select a state...',
                        selectOnFocus:true,
                        width:190,
                        blankText:'empty',
                        allowBlank:false
                    })
	
//	var pr=new Ext.FormPanel({
//		
//		monitorValid:true,
//		layout:'form',
//		title:'上海德尔福汽车空调系统有限公司_请购单',
//		width:'800',
//		height:'1200',
//		applyTo:'test',
//		columns: 3,
//		items:[
//		buyList,  //采购员名单
//		costList,	//成本中心列表
//		typeList,  //类型种类
//		approvedGroup,  //计划内外
//		comSupply,
//		mustSupply,
//		ppt,
//		context		
//			],
//		buttons:[{
//			text:'save',
//			handler:function(){
//			//	pr.getForm().isValid()
//				if(true){
//
//                        
//                      
//                    var m = store.getModifiedRecords();
//			        if (m.length == 0)
//		        	{
//			            alert("没有数据");
//			            return false;
//			        }
//			        var sSend = "["
//			      
//			        for (var i = 0; i < m.length; i++)
//		        	{
//		        		if(i+1==m.length)
//		        		{
//		        			sSend = sSend +  "{'description':" + m[i].data["simple"] + "'', 'unit':" + m[i].data["orgin"] + "''}";
//		        		}
//		        		else
//		        		{
//		        			sSend = sSend +  "{'description':" + m[i].data["simple"] + "'', 'unit':" + m[i].data["orgin"] + "''},";
//		        		}
//		        	}
//			          //  sSend = sSend + '{description:' + m[i].data['simple'] + ', unit:' + m[i].data['orgin'] + '}';
//			        
//			        sSend = sSend + "]";
//			       
//			        sSend = Ext.util.JSON.encode(sSend);
//                   //    Ext.Msg.alert(objArray)
//                        
//                        
//                     
//                    pr.form.submit({
//                    	
//                    	waitTitle:'提示',
//                		url:'epor.do?actionType=doInsert',
//                        method: 'post',
//                        waitMsg:'正在登录验证,请稍候...',
//                        progress:true,
//                        params: {data: sSend},
//                        success:function(form,action){//如果post成功执行这里         
//                        Ext.Msg.alert('成功', '插入成功!!',function(){location.href=action.result.data.url;} );
//                                                     
//               			},
//                    	
//              failure: function(form,action) {
//                      alert('no');               
//                       }                            
//                    })   
//                     
//                }
//
//			}
//		
//		},
//		{
//			text:'reset',
//			handler:function(){
//				//pr.getForm().reset();
//				var len=pr.items.length
//			//	alert(len)
//				pr.items[len]=filed1
//				pr.render()
//			}
//		}]
//		});
		
})