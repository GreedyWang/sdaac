Ext.onReady(function() {
	Ext.QuickTips.init();// 提示link，如果a标签有title的话
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());// 以cookie的方式记住用户对面板的部署
	Ext.BLANK_IMAGE_URL = 'images/default/blank.gif'
	var win, win2 = false;
	var number = '', index;

	/**
	 * 载入PRproject数据
	 */
	loadProjectData = function() {
		prFormListManager.doSelectAllProject(function(data) {
					var roles = document.getElementById('projectId')
					roles.options.length = 0;
					if (data != null) {
						for (var i = 0; i < data.length; i++) {
							var opt = document.createElement('Option');
							opt.text = data[i].context + '#' + data[i].number
									+ '#' + data[i].managerUid
							opt.value = data[i].id

							roles.options.add(opt);
						}
					}
				});
	}
	// -------------------------
	showUid = function() {
		if (!win2) {
			win2 = new Ext.Window({
				applyTo : 'hello-win2',
				layout : 'fit',
				width : 500,
				height : 300,
				closeAction : 'hide',
				plain : true,
				items : new Ext.Panel({
					applyTo : 'hello-tabs2',
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
										EmpManager.doSelectGetEmpDetails(empID,
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
							document.getElementById('relate').value += Ext
									.get('empUid').dom.value
									+ ';';
							document.getElementById('relateTip').innerText += Ext
									.get('empName').dom.value
									+ ';';
						} else {
							Ext.Msg.alert('无此员工!');
						}
					}
						// disabled : true
				}, {
					text : '清空',
					handler : function() {
						document.getElementById('relate').value = ''
						document.getElementById('relateTip').innerText = ''
					}
				}]
			});
		}
		win2.show();
	}

	var sm = new Ext.grid.CheckboxSelectionModel({
				singleSelect : true
			})

	var cm = new Ext.grid.ColumnModel([sm, {
				id : 'number',
				header : '科目号',
				dataIndex : 'number',
				width : 80
			}, {
				id : 'descrption',
				header : '描述',
				dataIndex : 'descrption',
				width : 80
			}, {
				id : 'remark',
				header : '注意事项',
				dataIndex : 'remark',
				width : 180
			}])

	var tbar = new Ext.Toolbar({
		items : [{
			text : '确认',
			iconCls : '',
			handler : function() {
				if (number != null && number != '') {
					var param = {
						glAccount : number,
						id : index
					}
					prFormListManager.doUpdatePRContext(param, function(data) {
						Ext.Msg.alert('成功!')
						document.getElementById('vv').value = number;
						document.getElementById('totalCategroyTip').innerText = number;

					})

				} else {
					Ext.Msg.alert('请选择科目号!')
				}

			}
		}]

	})

	var area = document.getElementById('area');
	if (area == null) {
		loadSubjecetData()
	} else {
		if (area.value == null || area.value == '') {
			area = 'DT02';
		}
		loadSubjecetData2(area.value)
	}

	subjectTip = function() {

		if (!win) {
			win = new Ext.Window({
						applyTo : 'hello-win',
						layout : 'fit',
						width : 360,
						//autoHeight : true,
						height:380,
						collapsible:true,
						modal : true,
						closeAction : 'hide',
						plain : true,
						items : new Ext.grid.GridPanel({
									applyTo : 'hello-tabs',
									cm : cm,
									tbar : tbar,
									title : '总账科目列表',
									sm : sm,
									collapsible:true,
									width : 340,
									store : subjecetStore
								})

					});

		}
		win.center();
		win.show();
	}

	sm.on('rowselect', function(a, rowIndex, r) {
		var record = this.grid.store.getAt(rowIndex);
		number = record.get('number')
			// var el = Ext.getCmp('bottom').body;
			// el.update(url1 + record.get('id') + url2);
		})

})