Ext.onReady(function() {
	Ext.QuickTips.init();// 提示link，如果a标签有title的话
	//Ext.state.Manager.setProvider(new Ext.state.CookieProvider());// 以cookie的方式记住用户对面板的部署
	Ext.BLANK_IMAGE_URL = 'images/default/blank.gif'
	var roles = document.getElementById('aaa').value;
	/**
	 * 子菜单
	 */
	var win = false;
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
			Ext.Msg.alert('帮助','联系电话 : 3046; Mail：wang.yongmin@sdaac.com ;使用说明:')
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
				activeTab:0,
				title : 'centre_test',
				height:'100%'

			})
	
	 centre.add({
                'id':'no1',
                'title':'查看会议室',
                closable:true,
                html:'<iframe width="100%" height="100%" src="HumanResource/assembly_room/dashbar.jsp"></iframe>'              
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
					height:'100%',
				layoutConfig : {

					animate : true

				},

				items : [{
							title : "个人/personal",
							html : '<div id="tree-div"></div>'
						},{
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
				hrefTarget : 'HumanResource/assembly_room/form.jsp',
				text : "预定会议室"
			});
			
	var seeFormSelf = new Ext.tree.TreeNode({
				id : "seeFormSelf",// 根节点id
				leaf : true,
					hrefTarget : 'HumanResource/assembly_room/showList.jsp',
				text : "查看记录"
			});
			
	var showDashBar = new Ext.tree.TreeNode({
				id : "showDashBar",// 根节点id
				leaf : true,
					hrefTarget : 'roomSystem.do?actionType=doTest',
				text : "查看会议室"
			});
	
	var fileDownLoad = new Ext.tree.TreeNode({
				id : "fileDownLoad",// 根节点id
				leaf : true,
					hrefTarget : 'HumanResource/assembly_room/a.html',
				text : "附件1：重要来访接待申请表 下载"
			}); 
	
	var seeHistoryForm = new Ext.tree.TreeNode({
				id : "seeHistoryForm",// 根节点id
				leaf : true,
				hrefTarget : 'HumanResource/assembly_room/approveList.jsp',
				text : "重要来访接待申请批准"
			});
	
		
			
	var backer = new Ext.tree.TreeNode({
			id : "backer",// 根节点id
			leaf : true,
			hrefTarget : 'epor/baseInfo/backerManager.jsp',
			text : "设置代理人员/set backup"
		});

	var accessoryManager = new Ext.tree.TreeNode({
				id : "accessoryManager",// 根节点id
				leaf : true,
				hrefTarget : 'HumanResource/assembly_room/accessoryManager.jsp',
				text : "配件管理"
			});
			
	var roomManager = new Ext.tree.TreeNode({
				id : "roomManager",// 根节点id
				leaf : true,
				//hrefTarget : 'HumanResource/assembly_room/baseInfoManager.jsp',
				text : "会议室管理"
			});
			
	var root4 = new Ext.tree.TreeNode({
				id : "root4",// 根节点id
				text : "基础数据维护"
			});
	//if(roles.indexOf('PRAdmin')>0){

		root2.appendChild(root4);
		root4.appendChild(accessoryManager)
		root4.appendChild(roomManager)
//	}
	root.appendChild(applyForm);
	root.appendChild(seeFormSelf);
	root.appendChild(showDashBar);
	root.appendChild(fileDownLoad);
	
//	if(roles.indexOf('PR')>0){
	
		root3.appendChild(seeHistoryForm);	
		root3.appendChild(backer);		
//	}
	
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
				Ext.MessageBox.alert("系統提示", "改功能尚未实现!");
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
				Ext.MessageBox.alert("系統提示", "改功能尚未实现!");
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
