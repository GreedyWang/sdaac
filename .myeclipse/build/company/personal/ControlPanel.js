Ext.BLANK_IMAGE_URL = 'ext/resources/images/default/s.gif';
Ext.onReady(function() {
	var contentPanel = new Ext.TabPanel({
				region : 'center',
				deferredRender : false,
				activeTab : 0
			});

	var contentPanel1 = new Ext.Panel({

				title : '菜单栏',
				region : 'west',
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
							title : "新闻管理",
							html : '<div id="tree"></div>'
						}, {

							'title' : '菜单管理',
							closable : true,
							html : '<div id="tree2"></div>'
						}, {

							'title' : '应用程序',
							closable : true,
							html : '<div id="tree3"></div>'
						}]
			});

	var viewport = new Ext.Viewport({

				layout : 'border',

				items : [contentPanel1, contentPanel]
			});

	var root = new Ext.tree.TreeNode({
				id : "root",// 根节点id
				text : "root"
			});

	var menu1 = new Ext.tree.TreeNode({
				id : "company/personal/release.jsp",// 根节点id
				text : "发布新闻"
			});

	var menu2 = new Ext.tree.TreeNode({
		id : 'news.do?actionType=showList',
		text : '查看我发布新闻'
			// href:'task.do?actionType=doSelectMyTask'

		});

	// root.appendChild(root1);
	root.appendChild(menu1);
	root.appendChild(menu2);

	var tree = new Ext.tree.TreePanel({

		renderTo : "tree",
		root : root,// 定位到根节点
		animate : true,// 开启动画效果
		enableDD : false,// 不允许子节点拖动
		border : false,// 没有边框
		rootVisible : false,
		// 设为false将隐藏根节点，很多情况下，我们选择隐藏根节点增加美观性
		listeners : {
			'click' : function(node, event) {
				event.stopEvent();
				var n = contentPanel.getComponent(node.id);
				if (n) {
					contentPanel.remove(n);
				}
				n = contentPanel.add({
					'id' : node.id,
					'title' : node.text,
					closable : true,
					html : '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'
							+ node.id + '"></iframe>'
				});
				contentPanel.setActiveTab(n);
			}
		}
	});
	// -------------------------------------------------------------------
	var root2 = new Ext.tree.TreeNode({
				id : "root2",// 根节点id
				text : "root"
			});

	var R2menu1 = new Ext.tree.TreeNode({
				id : "company/personal/AppList.jsp",// 根节点id
				text : "编辑本部门菜单"
			});

	root2.appendChild(R2menu1);

	var tree2 = new Ext.tree.TreePanel({

		renderTo : "tree2",
		root : root2,// 定位到根节点
		animate : true,// 开启动画效果
		enableDD : false,// 不允许子节点拖动
		border : false,// 没有边框
		rootVisible : false,
		// 设为false将隐藏根节点，很多情况下，我们选择隐藏根节点增加美观性
		listeners : {
			'click' : function(node, event) {
				event.stopEvent();
				var n = contentPanel.getComponent(node.id);
				if (n) {
					contentPanel.remove(n);
				}
				n = contentPanel.add({
					'id' : node.id,
					'title' : node.text,
					closable : true,
					html : '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'
							+ node.id + '"></iframe>'
				});
				contentPanel.setActiveTab(n);
			}
		}
	});
	// -------------------------------------------------------------------
	var root3 = new Ext.tree.TreeNode({
				id : "root3",// 根节点id
				text : "root"
			});

	var R3menu1 = new Ext.tree.TreeNode({
				id : "frontIndex/index.jsp",// 根节点id
				text : "所有应用程序"
			});

	root3.appendChild(R3menu1);

	var tree3 = new Ext.tree.TreePanel({

		renderTo : "tree3",
		root : root3,// 定位到根节点
		animate : true,// 开启动画效果
		enableDD : false,// 不允许子节点拖动
		border : false,// 没有边框
		rootVisible : false,
		// 设为false将隐藏根节点，很多情况下，我们选择隐藏根节点增加美观性
		listeners : {
			'click' : function(node, event) {
				event.stopEvent();
				var n = contentPanel.getComponent(node.id);
				if (n) {
					contentPanel.remove(n);
				}
				n = contentPanel.add({
					'id' : node.id,
					'title' : node.text,
					closable : true,
					html : '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'
							+ node.id + '"></iframe>'
				});
				contentPanel.setActiveTab(n);
			}
		}
	});

})