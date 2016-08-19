Ext.onReady(function() {
			Ext.QuickTips.init();// 提示link，如果a标签有title的话
			Ext.state.Manager.setProvider(new Ext.state.CookieProvider());// 以cookie的方式记住用户对面板的部署
			Ext.BLANK_IMAGE_URL = 'images/default/blank.gif'
			var accordion = new Ext.Panel({
						title : '基础数据维护',
						layout : 'accordion',
						renderTo: Ext.getBody(),
						height:'100%',
						defaults : {
							// applied to each contained panel
							bodyStyle : 'padding:15px'
						},
						layoutConfig : {
							// layout-specific configs go here
							titleCollapse : false,
							animate : true,
							activeOnTop : true
						},
						items : [{
									title : '配件管理',
									html : '<iframe width="100%" height="800" src="HumanResource/assembly_room/accessoryManager.jsp"></iframe>'
								}, {
									title : '会议室管理',
									html : '<iframe width="100%" height="800" src=""></iframe>'
								}
								]
					});
		});