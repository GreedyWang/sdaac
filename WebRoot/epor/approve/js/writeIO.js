Ext.onReady(function() {
	Ext.QuickTips.init();// 提示link，如果a标签有title的话
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());// 以cookie的方式记住用户对面板的部署
	Ext.BLANK_IMAGE_URL = 'images/default/blank.gif'
	var win = false,win2 = false;
	var number, index;
	

//-------------------------------------------
	PNTip = function(pindex) {
		index = pindex;
		if (!win2) {
			win2 = new Ext.Window({
				applyTo : 'hello-win',
				layout : 'fit',
				width : 360,
				autoHeight : true,
				modal : true,
				closeAction : 'hide',
				plain : true,
				items : new Ext.Panel({
					applyTo : 'hello-tabs',
					title : '填写IO号',
					layout : 'form',
					autoHeight : true,
					width : 340,
					items : [{
								xtype : 'textfield',
								id : 'orderId',
								x : 10,
								y : 10,
								fieldLabel : 'IO号',
								allowBlank : false,
								name : 'orderId'
							}, {
								xtype : 'button',
								x : 30,
								y : 10,
								text : '确认',
								iconCls : '',
								handler : function() {
									var orderId = Ext.get('orderId').dom.value;
									var param = {io:orderId,id:index}
									prFormListManager.doUpdatePRContext(param,function(data){
										Ext.Msg.alert('成功!')
										document.getElementById('a' + index).innerText = orderId;
									})		
								}
							}]
				})

			});

		}
		win2.center();
		win2.show();
	}
//-------------------------------
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
						document.getElementById('totalCategroy').innerText = number;
						document.getElementById('totalCategroy3').value = number;
						win.hide();
						
					})

				} else {
					Ext.Msg.alert('请选择科目号!')
				}

			}
		},{
			text : '关闭',
			iconCls : '',
			handler : function() {win.hide();}
		}]

	})

	loadSubjecetData2()

	changeCategoryJ = function() {

		if (!win) {
			win = new Ext.Window({
						applyTo : 'hello-win3',
						layout : 'fit',
						width : 360,
						autoHeight : true,
						modal : true,
						closeAction : 'hide',
						plain : true,
						items : new Ext.grid.GridPanel({
									applyTo : 'hello-tabs3',
									cm : cm,
									tbar : tbar,
									title : '总账科目列表2',
									//2013-06-03
									//增加滚动条，调整长宽
									sm : sm,
									autoHeight : false,
									height:600,
									width : 400,
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
	
	//-------------------------------------------

})