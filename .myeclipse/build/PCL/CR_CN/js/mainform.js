Ext.onReady(function() {
	Ext.QuickTips.init();// 提示link，如果a标签有title的话
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());// 以cookie的方式记住用户对面板的部署
	Ext.BLANK_IMAGE_URL = 'images/default/blank.gif'

	var tb = new Ext.Toolbar();
	tb.render('header');

	tb.add({
		text : '帮助/HELP',
		iconCls : 'bmenu', // <-- icon
		handler : function() {
			Ext.Msg
					.alert('',
							'联系电话 : 3046; Mail：wang.yongmin@sdaac.com ;使用说明:P:\Public\IT\Training')
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

			});

	/** 树菜单栏 */
	var west = new Ext.Panel({

				title : ' CR_CN',
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
							title : "主菜单/Main Menu",
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

	var applyForm = new Ext.tree.TreeNode({
				id : "applyForm",// 根节点id
				leaf : true,
				hrefTarget : 'epor/prForm.jsp',
				text : "填写销售申请"
			});

	var seeFormSelf = new Ext.tree.TreeNode({
				id : "seeFormSelf",// 根节点id
				leaf : true,
				// hrefTarget :
				// 'epor/showList.jsp?uid='+document.getElementById('uid').value,
				text : "查看我的申请"
			});

	var seeForm = new Ext.tree.TreeNode({
				id : "seeForm",// 根节点id
				leaf : true,
				hrefTarget : 'epor/showList.jsp?types=1',
				text : "审批申请/wait for approval"
			});

	var seeHistoryForm = new Ext.tree.TreeNode({
				id : "seeHistoryForm",// 根节点id
				leaf : true,
				hrefTarget : 'epor/showList.jsp',
				text : "查看历史审批"
			});

	root.appendChild(applyForm);
	root.appendChild(seeFormSelf);
	root.appendChild(seeForm);
	root.appendChild(seeHistoryForm);

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
