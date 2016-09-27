function showWindow() {
	var form1 = new Ext.form.FormPanel({
				baseCls : 'x-plain',
				layout : 'absolute',
				url : 'login.do?actionType=doLogin',
				items : [{
							x : 0,
							y : 5,
							xtype : 'label',
							text : '用户名:',
							allowBlank:false

						}, {
							x : 60,
							y : 5,
							
							xtype : 'textfield',
							name : 'uname'
						}, {
							x : 0,
							y : 35,
							xtype : 'label',
							text : '密码:'
						}, {
							x : 60,
							y : 35,
							xtype : 'textfield',
							name : 'upass',
							allowBlank:false

						}]
			});

	var wind = new Ext.Window({
				id : 'panel1',
				// title:'修改',
				// applyTo:'dialog',
				width : 500,
				height : 300,
				layout : 'fit',
				plain : true,
				 buttonAlign:'center',
				// html : '<iframe scrolling="auto" frameborder="0" width="600"
				// height="800" src="welcome/Login.jsp"></iframe>',
				items : form1,
				buttons : [{
							text : '关闭',
							handler : function() {
								// alert(document.getElementById('search').innerHTML)
								// wind.hide();
								// loader.load(root, null);
								wind.close();
							}

						},{
            text: '登录',
            handler : function() {
            	//form.Submit();
            	form1.form.submit({
            		url:'login.do?actionType=doLogin'
            	});
            }
        }]
			})
	wind.show();
}
