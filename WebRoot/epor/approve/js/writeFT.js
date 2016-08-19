Ext.onReady(function() {
	Ext.QuickTips.init();// 提示link，如果a标签有title的话
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());// 以cookie的方式记住用户对面板的部署
	Ext.BLANK_IMAGE_URL = 'images/default/blank.gif'
	var win = false;
	var number, index;

	PNTip = function(pindex) {
		index = pindex;
		if (!win) {
			win = new Ext.Window({
				applyTo : 'hello-win',
				layout : 'fit',
				width : 360,
				autoHeight : true,
				modal : true,
				closeAction : 'hide',
				plain : true,
				items : new Ext.Panel({
					applyTo : 'hello-tabs',
					title : '填写到货日期',
					layout : 'form',
					autoHeight : true,
					width : 340,
					items : [ {
								xtype : 'textfield',
								id : 'poDate',
								x : 20,
								y : 20,
								fieldLabel : '到货日期(格式yyyy-MM-DD)',
								allowBlank : false,
								name : 'poDate'
								
							}, {
								xtype : 'button',
								x : 30,
								y : 10,
								text : '确认',
								iconCls : '',
								handler : function() {
									
									var poDate = Ext.get('poDate').dom.value;
									
									var param = {
										
										finishDate: poDate,
										id : index
									}
									prFormListManager.doUpdatePRContext(param,
											function(data) {
												Ext.Msg.alert('成功!')
												document.getElementById('c'
														+ index).innerText = poDate;
											})
								}
							}]
				})

			});

		}
		win.center();
		win.show();
	}

})