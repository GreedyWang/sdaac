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
					title : '填写订单号',
					layout : 'form',
					autoHeight : true,
					width : 340,
					items : [{
								xtype : 'textfield',
								id : 'orderId',
								x : 10,
								y : 10,
								fieldLabel : '订单号',
								allowBlank : false,
								name : 'orderId'
							}, {
								xtype : 'textfield',
								id : 'poDate',
								x : 20,
								y : 20,
								fieldLabel : '预计入厂/入库日期(格式yyyy-MM-DD)',
								allowBlank : false,
								name : 'poDate'
								
							}, {
								xtype : 'button',
								x : 30,
								y : 10,
								text : '确认',
								iconCls : '',
								handler : function() {
									var orderId = Ext.get('orderId').dom.value;
									var poDate = Ext.get('poDate').dom.value;
									
	
 

        var flag = poDate.match(/((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/);
        if(flag==null)
        {
            alert("请输入正确的日期格式！,例如：2009-01-01。");
            document.getElementById("txtStrDate").value = "2008-01-01";
        }
					
									
									
									var param = {
										orderNo : orderId,
										PODate: poDate,
										id : index
									}
									prFormListManager.doUpdatePRContext(param,
											function(data) {
												Ext.Msg.alert('成功!')
												document.getElementById('b'
														+ index).innerText = orderId+'  /  '+poDate;
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