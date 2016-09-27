Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side'
	Ext.BLANK_IMAGE_URL = 'images/default/blank.gif'
	var fm = Ext.form
	
	var roomStore = new Ext.data.JsonStore({
			// data:data,
			fields : ['id', 'name']
		})
	var cycleStore = new Ext.data.JsonStore({
			// data:data,
			fields : ['id', 'name']
		})
	var roomDate = [{id:1,name:'行政楼1#会议室带投影仪'},{id:2,name:'行政楼2#会议室'},{id:3,name:'行政楼3#会议室'},{id:4,name:'行政楼4#电话会议室'},
		{id:5,name:'技术中心VIP贵宾室'},{id:6,name:'技术中心培训室'}]	
	roomStore.loadData(roomDate);
	
	var roomList = new Ext.form.ComboBox({
				id:'roomList',
				fieldLabel : '会议室',
				hiddenName : 'form.roomId.id',
				name : 'form.roomId.id',
				store : roomStore,
				valueField : 'id',
				displayField : 'name',
				typeAhead : true,
				mode : 'local',
				triggerAction : 'all',
				emptyText : 'Select a room...',
				selectOnFocus : true,
				width : 190,		
				editable :false,
				columnWidth:0.5,
				blankText : '必填项',
				allowBlank : false
			})
			
	var title = new Ext.form.Field({
				id:'title',
				fieldLabel : '概述',
				columnWidth:0.5,
				width : 350,
				name : 'form.context'

			});
	
	var contact = new Ext.form.Field({
				id:'contact',
				fieldLabel : '联系方式',
				columnWidth:0.5,
				width : 350,
				name : 'form.contact'

			});
		
	var beginTime = new Ext.form.DateField({
				format:'Y-m-d',
		        value:new Date(),
		        width:150,
		        name: 'begintime',
		        id:'beginTime',
		        fieldLabel :'开始时间',	  
		        autoWidth :true,
		        allowBlank : false,
		        anchor:'80%' 		
	})
	
	var beginTime2 = new Ext.form.TimeField({
				format:'H:i:s',
		        value:new Date(),
		        columnWidth:0.33,
		        name: 'beginTime2',
		        id:'beginTime2',
		        width:150,
		         maxValue : '20:00:00',
		        minValue : '07:00:00',
		         autoWidth :true,
				 allowBlank : false,
		        anchor:'80%' 		
	})
	
	var endTime = new Ext.form.DateField({
				format:'Y-m-d ',
				columnWidth:0.33,
		        name: 'endTime',
		        id:'endTime',
		         autoWidth :true,
		          allowBlank : false,
		         fieldLabel :'结束时间',
		        anchor:'80%' 
	})
	
	var endTime2 = new Ext.form.TimeField({
				format:'H:i:s',
				columnWidth:0.33,
		        name: 'endTime2',
		        id:'endTime2',
		        autoWidth :true,
		     	allowBlank : false,
		     	 maxValue : '20:00:00',
		        minValue : '07:00:00',
		        anchor:'80%' 
	})
	
	var remark = new Ext.form.TextArea({
		id:'remark',
		name:'form.remark',
		width:300,
		height:160,
		fieldLabel:'备注'
		
	})
	var phone = new Ext.form.Checkbox({
		id:'phone',
		name:'form.phoneid',
		fieldLabel :'需要电话',
		columnWidth:0.125
	})
	
	var projector  = new Ext.form.Checkbox({
		id:'projector',
		name:'form.projector',
		fieldLabel :'需要投影仪',
		inputValue:1,
		columnWidth:0.125
	})
	
	
	var roomStore = new Ext.data.JsonStore({
			// data:data,
			fields : ['id', 'name']
		})
	var cycleDate = [{id:0,name:'单次'},{id:1,name:'周循环'},{id:2,name:'月循环'}]	
	cycleStore.loadData(cycleDate);
	var cycleList = new Ext.form.ComboBox({
				fieldLabel : '重复会议',
				hiddenName : 'form.cycleType',
				name : 'form.cycleType',
				store : cycleStore,
				valueField : 'id',
				displayField : 'name',
				typeAhead : true,
				mode : 'local',
				triggerAction : 'all',
				emptyText : 'Select a option...',
				selectOnFocus : true,
				width : 190,
				blankText : 'empty',
				editable :false,
				columnWidth:0.25,
				allowBlank : false
			})
	var pr = new Ext.FormPanel({
				monitorValid : true,
				fileUpload : true,
				width:800,
				height:600,
				layout : 'form',
				title : '会议室登记单',
				autoHeight : true,
				renderTo : document.body,				   													
				items : [								
						roomList,
						title,	
						contact,											
						beginTime,
						beginTime2,					 
						endTime,
						endTime2,					
						cycleList,
   						phone,
      					projector,
   						{
							xtype : 'fileuploadfield',
							id : 'form-file',
							width : 300,
							emptyText : 'Select a file',
							fieldLabel : '重要客户登记单',
							name : 'formFile',
							columnWidth: 0.4,
							buttonCfg : {
								text : '上传',
								iconCls : 'upload-icon'
							}
						},						 
						remark											
				],
				buttons : [{
					text : '提交',
					handler : function() {
						if (window.confirm('确认提交?')) {
							
							if (!pr.getForm().isValid() ) {
								alert('上传指定文件!');
								return;
							}														
							pr.form.submit({
										waitTitle : '提示',
										url : 'roomSystem.do?actionType=doCheckIn',
										method : 'post',
										// waitMsg:'正在登录验证,请稍候...',
										timeout : 10000,
										// progress:true,
										params : {
											state : 2
										},
										success : function(form,action) {// 如果post成功执行这里
											  
											 Ext.MessageBox.alert('',action.result.msg);  
										},
										failure : function(form, action) {
											 Ext.MessageBox.alert('',action.result.msg); 
											
										}
									})

						}

					}

				}
				
				]
			});



	

});
