Ext.onReady(function() {
	Ext.QuickTips.init();// 提示link，如果a标签有title的话
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());// 以cookie的方式记住用户对面板的部署
	Ext.BLANK_IMAGE_URL = 'images/default/blank.gif'
	var uid,uid2, uname;// 被选择采购员工号
	var win = false;
	// load data for buyer
	uid = document.getElementById('uid').value
	function loadBuyerData() {
		AuthorizedFormManager.selectAuthorizedForm(uid,function(data) {
					girdStore.loadData(data);
				});
	}

	var girdStore = new Ext.data.JsonStore({
				// url : '',
				fields : ['uid', 'total','type','from','to']
			})
	// girdStore.reload()

	//loadBuyerData();

	var cm = new Ext.grid.ColumnModel([{
				id : 'name',
				header : '姓名',
				dataIndex : 'name',
				width : 100
			},{
				id : 'uid',
				header : '工号',
				dataIndex : 'uid',
				width : 100
			},{
				id : 'a',
				header : '授权金额(起始)',
				dataIndex : 'moneya',
				width : 100
			},{
				id : 'b',
				header : '授权金额(到)',
				dataIndex : 'moneyb',
				width : 100
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
																		Ext.get('empName').dom.value = data.name;
																	} else {
																		Ext.Msg.alert('无此员工!')
																		Ext.get('empName').dom.value = '';
																	}
																})
											} else {
												Ext.Msg.alert('请填写员工工号!')
												Ext.get('empName').dom.value = '';
											}

										}
									},{
										xtype : 'textfield',
										x : 10,
										y : 35,
										fieldLabel : '金额',
										name : 'total',
										id : 'total'
									},{
										xtype : 'textfield',
										x : 10,
										y : 35,
										fieldLabel : 'Type',
										name : 'type',
										id : 'type'										
									},{
										xtype : 'textfield',
										x : 10,
										y : 35,
										fieldLabel : 'From',
										name : 'from',
										id : 'from'
									},{
										xtype : 'textfield',
										x : 10,
										y : 35,
										fieldLabel : 'To',
										name : 'to',
										id : 'to'
									}],
							border : false
						}),

						buttons : [{
							text : '添加',
							iconCls : 'icon-add',
							handler : function() {
								//if (Ext.get('empName').dom.value != '') {
									AuthorizedFormManager.addAuthorized( Ext.get('empUid').dom.value,uid,Ext.get('type').dom.value,
										Ext.get('total').dom.value,Ext.get('from').dom.value,Ext.get('to').dom.value,
										function(data){
												loadBuyerData();
										})
								//}
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
						EmpManager.doADelBacker(uid2,uid,function(data){
							loadBuyerData();
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
				title : '代理人员列表',
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
				uid2 = record.get('uid');
				uname = record.get('name')
			});
});
