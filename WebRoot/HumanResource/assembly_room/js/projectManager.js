Ext.onReady(function() {
	Ext.QuickTips.init();// 提示link，如果a标签有title的话
	//Ext.state.Manager.setProvider(new Ext.state.CookieProvider());// 以cookie的方式记住用户对面板的部署
	Ext.BLANK_IMAGE_URL = 'images/default/blank.gif'
	var uid, uname;// 被选择采购员工号
	var win = false;

	var projectStore = new Ext.data.JsonStore({
			// url : '',
			fields : ['id', 'name', 'remark', 'state','type']
		})
		
	RoomManager.doShow(function(data) {
				projectStore.loadData(data);
			});	
		
	 function showUrl1(value) {
		  return  "<a class='x-btn' target='blank'  href='roomSystem.do?actionType=doSelectAccessory&id="+value+"'>修改</a>";
	}
	
	var cm = new Ext.grid.ColumnModel([{
				id : 'name',
				header : '配件名称',
				dataIndex : 'name',
				width : 100
			},{
				id : 'type',
				header : '类型',
				dataIndex : 'type',
				renderer: function (val) {if(val == 1) return '投影仪'; else {return val} },
				width : 100
			},{
				id : 'state',
				header : '状态',
				dataIndex : 'state',
				renderer: function(val) {
					if(val == 1) return '不正常';
				 else if(val == 0){return '正常';} },
				width : 100
			},{
				id : 'remark',
				header : '备注',
				dataIndex : 'remark',
				width : 200
			},{	
				id : 'update',
				header : '修改',
				dataIndex : 'id',
				  renderer:showUrl1,
				width : 50
			}])
	//---------------------------------
	var roomStore = new Ext.data.JsonStore({
			// data:data,
			fields : ['id', 'name']
		})
	var roomDate = [{id:1,name:'投影仪'},{id:2,name:'电话机'}]	
	roomStore.loadData(roomDate);
	var roomList = new Ext.form.ComboBox({
				id:'roomList',
				fieldLabel : '类型',
				hiddenName : 'form.roomId',
				name : 'form.roomId',
				store : roomStore,
				valueField : 'id',
				displayField : 'name',
				typeAhead : true,
				mode : 'local',
				triggerAction : 'all',
				emptyText : 'Select a type...',
				selectOnFocus : true,
				width : 190,		
				editable :false,
				columnWidth:0.5,
				blankText : '必填项',
				allowBlank : false
			})
	var toolbar = new Ext.Toolbar({
		items : [{
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				// create the window on the first click and
				// reuse on subsequent clicks

				if (!win) {
					win = new Ext.Window({
						applyTo : 'hello-win',
						layout : 'fit',
						width : 500,
						height : 300,
						closeAction : 'hide',
						plain : true,
						items : new Ext.Panel({
							applyTo : 'hello-tabs',
							layout : 'form',
							autoTabs : true,
							items : [{
										xtype : 'textfield',
										id : 'managerUid',
										x : 10,
										y : 10,
										fieldLabel : '配件名称',
										name : 'managerUid'
									},roomList, {
										xtype : 'textarea',
										width :200,
										x : 10,
										y : 35,
										fieldLabel : '备注',
										name : 'context',
										id : 'context'
									}],
							border : false
						}),

						buttons : [{
							text : '添加',
							iconCls : 'icon-add',
							handler : function() {
								Ext.Ajax.request({
											url : 'roomSystem.do?actionType=doAddAccessory',
											params : {
												'name' : Ext.get('managerUid').dom.value,
												'type' : Ext.get('roomList').dom.value,
												'remark' : Ext.get('context').dom.value
											},
											success : function() {
												Ext.Msg.alert('成功!');
												loadProjectData();
											},
											failure : function() {
												Ext.Msg.alert('失败!')
											}
										})
							}
								// disabled : true
						}, {
							text : '关闭',
							handler : function() {
								win.hide();
							}
						}]
					});
				}
				win.show();
			}

		}, {
			text : '删除',
			iconCls : 'icon-del',
			handler : function() {
				if (uname != null && uname != '') {
					var flag = window.confirm('确认删除' + uname + '?');
					if (flag) {
						Ext.Ajax.request({
									url : 'epor.do?actionType=doDelProject',
									params : {
										'id' : uid
									},
									success : function() {
										Ext.Msg.alert('成功!');
											loadProjectData();
									},
									failure : function() {
										Ext.Msg.alert('失败!')
									}
								})
					} 
				}else {
						Ext.Msg.alert('请选择采购员!');
					}
			}
		}]

	})
	var grid = new Ext.grid.GridPanel({
				id : 'grid',
				title : '配件管理',
				cm : cm,
				tbar : toolbar,
				sm : new Ext.grid.RowSelectionModel({
							singleSelect : true
						}),
				store : projectStore,
				renderTo : Ext.getBody(),
				width : 550,
				autoHeight : true
			})

	grid.getSelectionModel().on('rowselect', function(sm, rowIdx, r) {
				var record = this.grid.store.getAt(rowIdx);
				uname = record.get('sapNo');
				uid = record.get('id')
			});
});
