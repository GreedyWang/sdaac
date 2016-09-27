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

	var win = false, win2 = false, win3 = false, win4 = false;
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
							html : '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="vave/util/ShowEmpUid.jsp"></iframe>',
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
		}, {
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
							html : '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="epor/baseInfo/sprojectManager.jsp"></iframe>',
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
		}, {
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
							html : '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="epor/baseInfo/scostCentre.jsp"></iframe>',
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
		}, {
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
							html : '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="epor/baseInfo/sbuyerManager.jsp"></iframe>',
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
		}]
	});

	/**
	 * 主菜单
	 */
	function asd() {
		var btn1 = new Ext.Button({
					text : "文件",
					iconCls : 'ddd',
					menu : [{
								icon : 'images/menu-parent.gif',
								text : "打开",
								handler : action
							}, '-', {
								icon : "save.jpg",
								text : "保存"
							}, {
								text : "关闭"
							}]
				});
		var btn2 = new Ext.Button({
					text : "编辑",
					menu : [{
								text : "复制"
							}, {
								text : "拷贝"
							}, "-", {
								text : "查找"
							}, {
								text : "替换"
							}]
				})
		this.toolbar = new Ext.Toolbar({
					items : [btn1, btn2]
				});

	}

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
		handler : function() {
			Ext.Msg
					.alert(
							'帮助信息/help info:',
							'联系电话/phone : 3046; Mail：wang.yongmin@sdaac.com ;使用说明/readMe:P:\\Public\\IT\\Training\\PR')
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
				activeTab : 0,
				height : '100%'

			})
	centre.add({
		'id' : 0,
		'title' : '版本变更',
		closable : true,
		html : '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="epor/readme.html"></iframe>'
	});

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
				height : '100%',
				layoutConfig : {

					animate : true

				},

				items : [{
							title : "个人/personal",
							html : '<div id="tree-div"></div>'
						}, {
							title : "审批/Approval",
							html : '<div id="tree-div3"></div>'
						}, {
							title : "基础信息/Basic Information",
							html : '<div id="tree-div2"></div>'
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

	var root2 = new Ext.tree.TreeNode({
				id : "root2",// 根节点id
				text : "信息管理"
			});

	var root3 = new Ext.tree.TreeNode({
				id : "root3",// 根节点id
				text : "xxx"
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

	var tree2 = new Ext.tree.TreePanel({

				renderTo : 'tree-div2',
				autoScroll : true,
				animate : true,
				enableDD : true,
				containerScroll : true,
				rootVisible : false,
				root : root2
			});

	var tree3 = new Ext.tree.TreePanel({

				renderTo : 'tree-div3',
				autoScroll : true,
				animate : true,
				enableDD : true,
				containerScroll : true,
				rootVisible : false,
				root : root3
			});

	var applyForm = new Ext.tree.TreeNode({
				id : "applyForm",// 根节点id
				leaf : true,
				hrefTarget : 'Lab/Experiment_Apply/outsideForm.jsp',
				text : "外协检测委托合同单"
			});
	var applyForm2 = new Ext.tree.TreeNode({
				id : "applyForm2",// 根节点id
				leaf : true,
				hrefTarget : 'Lab/Experiment_Apply/winnerForm.jsp',
				text : "检测委托合同单"
			});
	var seeFormSelf = new Ext.tree.TreeNode({
				id : "seeFormSelf",// 根节点id
				leaf : true,
				hrefTarget : 'Lab/Experiment_Apply/showList.jsp?uid='
						+ document.getElementById('uid').value,
				text : "查看我的请购单"
			});

	var seeForm = new Ext.tree.TreeNode({
				id : "seeForm",// 根节点id
				leaf : true,
				hrefTarget : 'epor/showList.jsp?types=1',
				text : "待审批的请购单/wait for approval"
			});

	var seeHistoryForm = new Ext.tree.TreeNode({
				id : "seeHistoryForm",// 根节点id
				leaf : true,
				hrefTarget : 'epor/showList.jsp',
				text : "所有进行中的请购单/all"
			});

	var seeHistoryFinishForm = new Ext.tree.TreeNode({
				id : "seeHistoryFinishForm",// 根节点id
				leaf : true,
				hrefTarget : 'epor/showList.jsp?isFin=1',
				text : "所有完成的请购单/all"
			});

	var backer = new Ext.tree.TreeNode({
				id : "backer",// 根节点id
				leaf : true,
				hrefTarget : 'epor/baseInfo/backerManager.jsp',
				text : "设置代理人员/set backup"
			});

	var flowManager = new Ext.tree.TreeNode({
				id : "flowManager",// 根节点id
				leaf : true,
				text : "流程管理"
			});

	var rightsManager = new Ext.tree.TreeNode({
				id : "rightsManager",// 根节点id
				leaf : true,
				hrefTarget : 'admin/role/index.jsp',
				text : "权限管理"
			});

	var doaManager = new Ext.tree.TreeNode({
				id : "doaManager",// 根节点id
				leaf : true,
				hrefTarget : 'epor/baseInfo/doaManager.jsp',
				text : "DOA管理"
			});

	var baseInfo = new Ext.tree.TreeNode({
				id : "baseInfo",// 根节点id
				leaf : true,
				hrefTarget : 'epor/baseInfo/baseInfoManager.jsp',
				text : "基础数据维护"
			});

	if (roles.indexOf('PRAdmin') > 0) {
		root2.appendChild(flowManager);
		root2.appendChild(rightsManager);
		root2.appendChild(baseInfo);
		root2.appendChild(doaManager);
	}
	root.appendChild(applyForm);
	root.appendChild(applyForm2);
	root.appendChild(seeFormSelf);
	if (roles.indexOf('PR') > 0) {
		root3.appendChild(seeForm);
		root3.appendChild(seeHistoryForm);
		root3.appendChild(seeHistoryFinishForm);
		root3.appendChild(backer);
	}

	// root.appendChild(approveForm);

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

	tree2.on('click', function(node) {
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

	tree3.on('click', function(node) {
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
