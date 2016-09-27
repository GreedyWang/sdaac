Ext.onReady(function() {
	Ext.QuickTips.init();// 提示link，如果a标签有title的话
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());// 以cookie的方式记住用户对面板的部署
	Ext.BLANK_IMAGE_URL = 'image/blank.gif'

	var contentPanel1 = new Ext.Panel({

				title : ' 统计面板',
				region : 'center',
				// contentEl: 'west-div',
				split : true,
				border : true,
				collapsible : true,
				width : 180,
				minSize : 150,
				maxSize : 300,
				layout : "accordion",
				layoutConfig : {

					animate : true

				},
				items : [{
							title : "提案统计",
							html : '<iframe width="100%" height="100%" src="vave/Statistics/VaveCount.jsp"></iframe>'
						}]
			});

	contentPanel1.add({
		'id' : 0,
		'title' : '各部门绩效统计(参与率、批准率)---点击展开',
		closable : true,
		html : '<iframe width="100%" height="100%" src="vave/Statistics/rate.jsp"></iframe>'
	});

	contentPanel1.add({
		'id' : 0,
		'title' : '提案批准管理与统计',
		closable : true,
		html : '<iframe width="100%" height="100%" src="statistics.do?actionType=approveRateAcount"></iframe>'
	});

	var viewport = new Ext.Viewport({

				layout : 'border',
				renderTo : Ext.getBody(),
				items : [contentPanel1]
			});

});