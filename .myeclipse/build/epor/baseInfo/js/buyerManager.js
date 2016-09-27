Ext.onReady(function() {
	Ext.QuickTips.init();// 提示link，如果a标签有title的话
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());// 以cookie的方式记住用户对面板的部署
	Ext.BLANK_IMAGE_URL = 'images/default/blank.gif'
	var uid, uname;// 被选择采购员工号
	var win = false;
	// load data for buyer
	function loadBuyerData() {
		prFormListManager.doSelectBuyers(function(data) {
					girdStore.loadData(data);
				});
	}

	var girdStore = new Ext.data.JsonStore({
				// url : '',
				fields : ['buyId', 'buyName']
			})
	// girdStore.reload()

	loadBuyerData();

	var cm = new Ext.grid.ColumnModel([{
				id : 'name',
				header : '姓名',
				dataIndex : 'buyId',
				width : 100
			},{
				id : 'name',
				header : '描述',
				dataIndex : 'buyName',
				width : 500
			}])

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
										id : 'empUid',
										x : 10,
										y : 10,
										fieldLabel : '员工工号',
										name : 'empUid'
									}, {
										xtype : 'button',
										x : 30,
										y : 10,
										text : '监测',
										iconCls : '',
										handler : function() {
											var empID = Ext.get('empUid').dom.value;
											if (empID != null && empID != '') {
												EmpManager
														.doSelectGetEmpDetails(
																empID,
																function(data) {
																	if (data != null) {
																		Ext
																				.get('empName').dom.value = data.name;
																	} else {
																		Ext.Msg
																				.alert('无此员工!')
																		Ext
																				.get('empName').dom.value = '';
																	}
																})
											} else {
												Ext.Msg.alert('请填写员工工号!')
												Ext.get('empName').dom.value = '';
											}

										}
									}, {
										xtype : 'textfield',
										x : 10,
										y : 35,
										fieldLabel : '员工姓名',
										name : 'empName',
										id : 'empName',
										disabled : true
									}],
							border : false
						}),

						buttons : [{
							text : '添加',
							iconCls : 'icon-add',
							handler : function() {
								if (Ext.get('empName').dom.value != '') {
									Ext.Ajax.request({
										url : 'epor.do?actionType=doAddBuyer',
										params : {
											'uid' : Ext.get('empUid').dom.value,
											'name' : Ext.get('empName').dom.value
										},
										success : function() {
											Ext.Msg.alert('成功!');
											loadBuyerData();
										},
										failure : function() {
											Ext.Msg.alert('失败!')
										}
									})
								} else {
									Ext.Msg.alert('无此员工!');
								}
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
				} else {
					Ext.Msg.alert('请选择采购员!');
				}
			}
		}]

	})
	var grid = new Ext.grid.GridPanel({
				id : 'grid',
				title : '采购员列表',
				cm : cm,
				tbar : toolbar,
				sm : new Ext.grid.RowSelectionModel({
							singleSelect : true
						}),
				store : girdStore,
				renderTo : Ext.getBody(),
				width : 600,
				autoHeight : true
			})

	grid.getSelectionModel().on('rowselect', function(sm, rowIdx, r) {
				var record = this.grid.store.getAt(rowIdx);
				uid = record.get('buyId');
				uname = record.get('buyName')
			});
});
