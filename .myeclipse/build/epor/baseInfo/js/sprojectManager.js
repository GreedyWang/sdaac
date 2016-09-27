Ext.onReady(function() {
	Ext.QuickTips.init();// 提示link，如果a标签有title的话
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());// 以cookie的方式记住用户对面板的部署
	Ext.BLANK_IMAGE_URL = 'images/default/blank.gif'
	var uid, uname;// 被选择采购员工号
	var win = false;
	// load data for buyer
	loadProjectData();

	var cm = new Ext.grid.ColumnModel([{
				id : 'number',
				header : '项目经理',
				dataIndex : 'number',
				width : 100
			},{
				id : 'sapNo',
				header : 'SAP统计型内部订单号',
				dataIndex : 'sapNo',
				width : 100
			},{
				id : 'context',
				header : '内容',
				dataIndex : 'context',
				width : 100
			}])

	var toolbar = new Ext.Toolbar({
		items : [{
				text:'excel导入',
				iconCls:'',
				handler: function(){
					Ext.Msg.alert('coding...');
				}
			},{
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
										id : 'empUid',
										x : 10,
										y : 10,
										fieldLabel : '项目编号',
										name : 'empUid'
									},{
										xtype : 'textfield',
										x : 10,
										y : 35,
										fieldLabel : 'SAP统计型内部订单号',
										name : 'empName',
										id : 'empName'								
									}, {
										xtype : 'textarea',
										width :200,
										x : 10,
										y : 35,
										fieldLabel : '内容',
										name : 'empName',
										id : 'context'
									}],
							border : false
						}),

						buttons : [{
							text : '添加',
							iconCls : 'icon-add',
							handler : function() {
								Ext.Ajax.request({
											url : 'epor.do?actionType=doAddBuyer',
											params : {
											//	'uid' : Ext.get('empUid').dom.value,
											//	'name' : Ext.get('empName').dom.value
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
					alert(uid)
					var flag = window.confirm('确认删除' + uname + '?');
					if (flag) {
						Ext.Ajax.request({
									url : 'epor.do?actionType=doDelBuyer',
									params : {
										'uid' : uid
									},
									success : function() {
										Ext.Msg.alert('成功!');
										loadBuyerData();
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
				title : '项目列表',
				cm : cm,
				sm : new Ext.grid.RowSelectionModel({
							singleSelect : true
						}),
				store : projectStore,
				renderTo : Ext.getBody(),
				width : 450,
				autoHeight : true
			})

	grid.getSelectionModel().on('rowselect', function(sm, rowIdx, r) {
				var record = this.grid.store.getAt(rowIdx);
//				uid = record.get('buyId');
//				uname = record.get('buyName')
			});
});
