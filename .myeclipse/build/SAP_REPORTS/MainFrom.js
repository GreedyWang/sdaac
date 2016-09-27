Ext.onReady(function() {
	Ext.QuickTips.init();// 提示link，如果a标签有title的话
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());// 以cookie的方式记住用户对面板的部署
	Ext.BLANK_IMAGE_URL = 'images/default/blank.gif'
	var roles = document.getElementById('aaa').value;
	/**
	 * 子菜单
	 */
	var dateMenu = new Ext.menu.DateMenu({
				handler : function(dp, date) {
					Ext.example.msg('Date Selected', 'You chose {0}.', date
									.format('M j, Y'));
				}
			});

	var colorMenu = new Ext.menu.ColorMenu({
				handler : function(cm, color) {
					Ext.example.msg('Color Selected', 'You chose {0}.', color);
				}
			});

	var win = false,win2 = false,win3 = false,win4 = false;
	
	var menu = new Ext.menu.Menu({
				id : 'mainMenu',
				items : [{
							text : '工号查询',
							handler : function() {
								if (!win) {
									win = new Ext.Window({
												applyTo : 'hello-win',
												layout : 'fit',
												width : 700,
												height : 300,
												closeAction : 'hide',
												plain : true,
												items : new Ext.Panel({
															applyTo : 'hello-tabs',
															layout : 'form',
															autoTabs : true,
															html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="vave/util/ShowEmpUid.jsp"></iframe>' ,
															border : false
														}),

												buttons : [{
													text : '关闭',
													handler : function() {
														win.hide();
													}
												}]
											});
								}
								win.show();
							}
						},{
							text : '项目查询',
							handler : function() {
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
															html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="epor/baseInfo/sprojectManager.jsp"></iframe>' ,
															border : false
														}),

												buttons : [{
													text : '关闭',
													handler : function() {
														win2.hide();
													}
												}]
											});
								}
								win2.show();
							}
						},{
							text : '成本中心查询',
							handler : function() {
								if (!win4) {
									win4 = new Ext.Window({
												applyTo : 'hello-win4',
												layout : 'fit',
												width : 500,
												height : 300,
												closeAction : 'hide',
												plain : true,
												items : new Ext.Panel({
															applyTo : 'hello-tabs4',
															layout : 'form',
															autoTabs : true,
															html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="epor/baseInfo/scostCentre.jsp"></iframe>' ,
															border : false
														}),

												buttons : [{
													text : '关闭',
													handler : function() {
														win4.hide();
													}
												}]
											});
								}
								win4.show();
							}
						},{
							text : '采购员查询',
							handler : function() {
								if (!win3) {
									win3 = new Ext.Window({
												applyTo : 'hello-win3',
												layout : 'fit',
												width : 500,
												height : 300,
												closeAction : 'hide',
												plain : true,
												items : new Ext.Panel({
															applyTo : 'hello-tabs3',
															layout : 'form',
															autoTabs : true,
															html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="epor/baseInfo/sbuyerManager.jsp"></iframe>' ,
															border : false
														}),

												buttons : [{
													text : '关闭',
													handler : function() {
														win3.hide();
													}
												}]
											});
								}
								win3.show();
							}
						}
				]
			});



	var tb = new Ext.Toolbar();
	tb.render('header');

	tb.add({
		text : '工具/Tools',
		iconCls : 'bmenu', // <-- icon
		menu : menu
			// assign menu by instance
		});
	tb.add({
		text : '帮助/HELP',
		iconCls : 'bmenu', // <-- icon
		handler:function(){
			Ext.Msg.alert('','联系电话 : 3046; Mail：wang.yongmin@sdaac.com ;使用说明:P:\Public\IT\Training\PR')
		}
			// assign menu by instance
		});

	function action(btn) {
		Ext.Msg.alert(btn.text)

	}

	/** 主面板 */
	var centre = new Ext.TabPanel({
				id : 'centre',
				region : 'center',
				width : 800,
				title : 'centre_test',
				activeTab:0,
				height:'100%'

			})
 

	/** 树菜单栏 */
	var west = new Ext.Panel({

				title : ' 菜单',
				region : 'west',
				contentEl : 'west',
				split : true,
				border : true,
				collapsible : true,
				width : 180,
				minSize : 150,
				maxSize : 300,
				layout : "accordion",
					height:'100%',
				layoutConfig : {

					animate : true

				},

				items : [{
							title : "菜单/menu",
							html : '<div id="tree-div"></div>'
						}]
			});
			


	/**
	 * 主界面
	 */
	new Ext.Viewport({
		layout : 'border',
		height : '100%',
		items : [{
			id : 'north',
			region : 'north',

			applyto : 'header',
			bodyStyle : 'background: #dfe8f6 url("images/a_01.jpg") no-repeat;',
			tbar : tb,
			height : 0
		}, west, centre]

	})
	/** 创建树型面板 */
	var root = new Ext.tree.TreeNode({
				id : "root",// 根节点id
				text : "我的提案"
			});



	var tree = new Ext.tree.TreePanel({

				renderTo : 'tree-div',
				autoScroll : true,
				animate : true,
				enableDD : true,
				containerScroll : true,
				rootVisible : false,
				root : root
			});

	var root1 = new Ext.tree.TreeNode({
			id : "item1",// 根节点id
			leaf : false,
			text : "基础数据导入"
		});
			
	var item1 = new Ext.tree.TreeNode({
				id : "item1",// 根节点id
				leaf : true,
				hrefTarget : 'SAP_REPORTS/update.jsp?aa=sp',
				text : "sales_price"
			});
			
	var item2 = new Ext.tree.TreeNode({
			id : "item2",// 根节点id
			leaf : true,
			hrefTarget : 'SAP_REPORTS/update.jsp?aa=mp',
			text : "model_pn"
		});
			
	var item3 = new Ext.tree.TreeNode({
		id : "item3",// 根节点id
		leaf : true,
		hrefTarget : 'SAP_REPORTS/update.jsp?aa=c',
		text : "customer"
	});	

	root1.appendChild(item1);
	root1.appendChild(item2);
	root1.appendChild(item3);
	root.appendChild(root1);


	
	
	tree.on('click', function(node) {
		if (node.isLeaf()) {
			if (node.attributes.hrefTarget != undefined) {
				var target = node.attributes.hrefTarget;

				var n = centre.getComponent(node.id);
				if (n) {
					centre.remove(n);
				}
				n = centre.add({
					'id' : node.id,
					'title' : node.text,
					closable : true,
					html : "<iframe scrolling='auto' frameborder='0' width='100%' height='100%' src='"
							+ target + "'></iframe>"
				});
				centre.setActiveTab(n);

			} else {
				Ext.MessageBox.alert("系統提示", "對不起,該節點沒有鏈接頁面!");
			}
		} else {
			alert('!')
		}

	}, this, {
		stopEvent : true
	});

	
	


});
