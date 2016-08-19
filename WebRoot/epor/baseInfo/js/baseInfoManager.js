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
									title : '成本中心数据管理',
									html : '<iframe width="100%" height="800" src="epor/baseInfo/costCentre.jsp"></iframe>'
								}, {
									title : '采购员数据管理',
									html : '<iframe width="100%" height="800" src="epor/baseInfo/buyerManager.jsp"></iframe>'
								}, {
									title : '项目数据管理',
									html : '<iframe width="100%" height="800" src="epor/baseInfo/projectManager.jsp"></iframe>'
								}
								, {
									title : '总账科目管理',
									html : '<iframe width="100%" height="800" src="epor/baseInfo/subjectManager.jsp"></iframe>'
								}
								]
					});
		});