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
			text : i18n("q_uid"),
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
									text : i18n("close"),
									handler : function() {
										win.hide();
									}
								}]
					});
				}
				win.show();
			}
		}, {
			text : i18n("q_item"),
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
									text : i18n("close"),
									handler : function() {
										win2.hide();
									}
								}]
					});
				}
				win2.show();
			}
		}, {
			text : i18n("q_costCenter"),
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
									text : i18n("close"),
									handler : function() {
										win4.hide();
									}
								}]
					});
				}
				win4.show();
			}
		}, {
			text : i18n("q_buyer"),
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
									text : i18n("close"),
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
					text : i18n("file"),
					iconCls : 'ddd',
					menu : [{
								icon : 'images/menu-parent.gif',
								text : i18n("open"),
								handler : action
							}, '-', {
								icon : "save.jpg",
								text : i18n("save")
							}, {
								text : i18n("close")
							}]
				});
		var btn2 = new Ext.Button({
					text : i18n("edit"),
					menu : [{
								text : i18n("copy")  //原文为复制
							}, {
								text : i18n("copy")  //原文为拷贝
							}, "-", {
								text : i18n("find")
							}, {
								text : i18n("replace")
							}]
				})
		this.toolbar = new Ext.Toolbar({
					items : [btn1, btn2]
				});

	}

	var tb = new Ext.Toolbar();
	tb.render('header');

	tb.add({
		text : i18n("tool"),
		iconCls : 'bmenu', // <-- icon
		menu : menu
			// assign menu by instance
		});
	tb.add({
		text : i18n("help"),
		iconCls : 'bmenu', // <-- icon
		handler : function() {
			Ext.Msg
					.alert('联系电话 : 3046; Mail：wang.yongmin@sdaac.com ;使用说明:P:\Public\IT\Training\PR')
		}
			// assign menu by instance
	});

	tb.add({
		text : i18n("q_BOMdata"), //'物料主数据查询'
		iconCls : 'bmenu', // <-- icon
		handler : function() {
			window
					.open('http://test2003/Reports/Pages/Report.aspx?ItemPath=%2fIndirect+Material+Master%2fPCL_%e7%89%a9%e6%96%99%e4%b8%bb%e6%95%b0%e6%8d%ae');
		}
			// assign menu by instance
	});
	
	tb.add({
		text : '历史PR数据查询',
		iconCls : 'bmenu', // <-- icon
		handler : function() {
		window.open('file:\\\\10.243.75.10\\Department\\Public\\IT\\PR\\历史PR查询');
				//alert(i18n("error"))
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
		id : 0,
		title : '版本变更',
		closable : true,
		html : '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="epor/readme.html"></iframe>'
	});

	/** 树菜单栏 */
	var west = new Ext.Panel({

				title : i18n("menu"),
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

				items : [ {
							title : i18n("personal"),
							html : '<div id="tree-div"></div>'
						},{
							title : i18n("approve"),
							html : '<div id="tree-div3"></div>'
						},{
							title : i18n("basicinfo"),
							html : '<div id="tree-div2"></div>'
						}]
			});
			
				/** 树菜单栏 */
	var west = new Ext.Panel({

				title : i18n("menu"),
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

				items : [ {
							title : i18n("personal"),
							html : '<div id="tree-div"></div>'
						},{
							title : i18n("approve"),
							html : '<div id="tree-div3"></div>'
						},{
							title : i18n("basicinfo"),
							html : '<div id="tree-div2"></div>'
						}]
			});

	if (roles.indexOf('PR') > 0) {
		west = new Ext.Panel({

				title : i18n("menu"),
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

				items : [ {
							title : i18n("approve"),
							html : '<div id="tree-div3"></div>'
						},{
							title : i18n("personal"),
							html : '<div id="tree-div"></div>'
						},{
							title : i18n("basicinfo"),
							html : '<div id="tree-div2"></div>'
						}]
			});
	}else{
		west = new Ext.Panel({

				title : i18n("menu"),
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

				items : [ {
							title : i18n("personal"),
							html : '<div id="tree-div"></div>'
						},{
							title : i18n("approve"),
							html : '<div id="tree-div3"></div>'
						},{
							title : i18n("basicinfo"),
							html : '<div id="tree-div2"></div>'
						}]
			});
	}
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
				text : i18n("myProposal")
			});

	var root2 = new Ext.tree.TreeNode({
				id : "root2",// 根节点id
				text : i18n("info_m")
			});

	var root3 = new Ext.tree.TreeNode({
				id : "root3",// 根节点id
				text : "xxx"
			});

	var tree = new Ext.tree.TreePanel({

				renderTo : 'tree-div',
				autoScroll : true,
				animate : true,
				
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
	tree3.expand(true);
	var applyForm = new Ext.tree.TreeNode({
				id : "applyForm",// 根节点id
				leaf : true,
				hrefTarget : 'epor/prForm.jsp',
				text : i18n("w_PRform")
			});
	var applyForm2 = new Ext.tree.TreeNode({
				id : "applyForm2",// 根节点id
				leaf : true,
				hrefTarget : 'mainData.do?actionType=doApplyReady',//'PCL/MainData/Form.jsp',
				text : i18n("w_masterData")
			});
			
	var applySingleSupplyForm = new Ext.tree.TreeNode({
			id : "applySingleSupplyForm",// 根节点id
			leaf : true,
			hrefTarget : 'epor/baseInfo/singleForm.jsp',//'PCL/MainData/Form.jsp',
			text : '单一供应商申请单'
		});
		
	var applyUGForm = new Ext.tree.TreeNode({
		id : "applyUGForm",// 根节点id
		leaf : true,
		hrefTarget : 'epor/baseInfo/UGForm.jsp',//'PCL/MainData/Form.jsp',
		text : '紧急采购申请单'
	});

	// var seeFormSelf = new Ext.tree.TreeNode({
	// id : "seeFormSelf",// 根节点id
	// leaf : true,
	// hrefTarget :
	// 'epor/showList.jsp?uid='+document.getElementById('uid').value,
	// text : "查看我的请购单"
	// });

	var seeFormSelf = new Ext.tree.TreeNode({
				id : "seeFormSelf",// 根节点id
				leaf : true,
				hrefTarget : 'epor/showList.jsp?uid='
						+ document.getElementById('uid').value,
				text : i18n("myPR_form")
			});
			
		var au = new Ext.tree.TreeNode({
				id : "au",// 根节点id
				leaf : true,
				hrefTarget : 'epor/showList.jsp?types=2'
						+ document.getElementById('uid').value,
				text : ">500K"
			});		

	var seeIMM = new Ext.tree.TreeNode({
		id : "seeIMM",// 根节点id
		leaf : true,
		hrefTarget : 'http://test2003/Reports/Pages/Report.aspx?ItemPath=%2fIndirect+Material+Master%2fPCL_%e7%89%a9%e6%96%99%e4%b8%bb%e6%95%b0%e6%8d%ae',
		text : i18n("MRO_form")
	});

	var seeForm = new Ext.tree.TreeNode({
				id : "seeForm",// 根节点id
				leaf : true,
				hrefTarget : 'epor/showList.jsp?types=1',
				text : i18n("pr_pendingApprove")
			});

	var seeHistoryForm = new Ext.tree.TreeNode({
				id : "seeHistoryForm",// 根节点id
				leaf : true,
				hrefTarget : 'epor/showList.jsp',
				text : i18n("pr_inProcess")
			});

	var seeHistoryFinishForm = new Ext.tree.TreeNode({
				id : "seeHistoryFinishForm",// 根节点id
				leaf : true,
				hrefTarget : 'epor/showList.jsp?isFin=1',
				text : i18n("pr_completed")
			});
	
	var report = new Ext.tree.TreeNode({
				id : "report",// 根节点id
				leaf : true,
				hrefTarget : 'epor/report.jsp',
				text : i18n("report")
			});		

	var seeYTForm = new Ext.tree.TreeNode({
				id : "seeYTForm",// 根节点id
				leaf : true,
				hrefTarget : 'epor/showList.jsp?area=3',
				text : i18n("YT_prForm")
			});
			
	var seeSYForm = new Ext.tree.TreeNode({
				id : "seeSYForm",// 根节点id
				leaf : true,
				hrefTarget : 'epor/showList.jsp?area=2',
				text :'所有沈阳请购单'
			});		

	var seeSHForm = new Ext.tree.TreeNode({
				id : "seeSHForm",// 根节点id
				leaf : true,
				hrefTarget : 'epor/showList.jsp?area=1',
				text :'所有上海总部请购单'
			});			

	var seeSHPlantForm = new Ext.tree.TreeNode({
				id : "seeSHPlantForm",// 根节点id
				leaf : true,
				hrefTarget : 'epor/showList.jsp?area=4',
				text :'所有上海厂请购单'
			});	
			
	var backer = new Ext.tree.TreeNode({
				id : "backer",// 根节点id
				leaf : true,
				hrefTarget : 'epor/baseInfo/backerManager.jsp',
				text : i18n("setbackup")
			});

	var flowManager = new Ext.tree.TreeNode({
				id : "flowManager",// 根节点id
				leaf : true,
				text : i18n("process_m")
			});

	var rightsManager = new Ext.tree.TreeNode({
				id : "rightsManager",// 根节点id
				leaf : true,
				hrefTarget : 'admin/role/index.jsp',
				text : i18n("permission_m")
			});

	var doaManager = new Ext.tree.TreeNode({
				id : "doaManager",// 根节点id
				leaf : true,
				hrefTarget : 'epor/baseInfo/doaManager.jsp',
				text : i18n("DOA_m")
			});

	var baseInfo = new Ext.tree.TreeNode({
				id : "baseInfo",// 根节点id
				leaf : true,
				hrefTarget : 'epor/baseInfo/baseInfoManager.jsp',
				text : i18n("maintianBaseData")
			});

	if (roles.indexOf('PRAdmin') > 0) {
		root2.appendChild(flowManager);
		root2.appendChild(rightsManager);
		root2.appendChild(baseInfo);
		root2.appendChild(doaManager);
	}
	root.appendChild(applyForm);
	root.appendChild(applyForm2);
	root.appendChild(applySingleSupplyForm);
	root.appendChild(applyUGForm);
	root.appendChild(seeFormSelf);
	//root.appendChild(seeIMM);

	if (roles.indexOf('PR') > 0) {
		root3.appendChild(seeForm);
		root3.appendChild(seeHistoryForm);
		//root3.appendChild(seeHistoryFinishForm);
		root3.appendChild(report);
		root3.appendChild(backer);
	}
	if (roles.indexOf('PRFinanceControlerSH4YT') > 0) {
		root3.appendChild(seeYTForm);
		root3.appendChild(seeSYForm);
		
		
	}
	
	if(document.getElementById('uid').value=='1151'|| document.getElementById('uid').value=='sy733'){
		root3.appendChild(seeSHForm);
		root3.appendChild(seeSYForm);
	}
	if(document.getElementById('uid').value=='1151'){
		root3.appendChild(seeSHForm);
		root3.appendChild(seeSYForm);
	}
	
	if(document.getElementById('uid').value=='1053'){
		root3.appendChild(seeSHForm);
		
	}
	
	if(document.getElementById('uid').value=='1051' || document.getElementById('uid').value=='1326'|| document.getElementById('uid').value=='1485'){
		root3.appendChild(seeSHPlantForm);
		
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
