Ext.onReady(function() {
		var btn2 = new Ext.Button({
				text : "确认完成所有指标定义",
				iconCls : 'icon-tick',
				handler : function(){
					Ext.Ajax.request({
					   url: 'empIndex.do?actionType=checkDepartmentIndexState',
					   success: function(){Ext.Msg.alert("","完成")}
					});
				}
			});
	mainPanel(btn2,'/bpp/index.do?actionType=doSelectEmpIndex&empUid=');
})
//	Ext.QuickTips.init();// 提示link，如果a标签有title的话
//	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());// 以cookie的方式记住用户对面板的部署
//	Ext.BLANK_IMAGE_URL = 'img/blank.gif'
//	var theUrl = 'employee.do?actionType=doSelectOrganization'
//	/** 搜索栏 */
//	var tab = new Ext.FormPanel({
//
//				labelAlign : 'top',
//				applyTo : 'search',
//				bodyStyle : 'padding:5px,5px,0',
//				frame : true,
//				width : 200,
//				items : [{
//							layout : 'column',
//							items : [{
//										columnWidth : .2,
//										layout : 'form',
//										items : [{
//													fieldLabel : '姓名',
//													xtype : 'textfield',
//													name : 'uname',
//													id : 'uname',
//													tabIndex : 0,
//													anchor : '80%'
//												}]
//									}]
//						}, {
//							columnWidth : .2,
//							layout : 'form',
//							buttons : [{
//								text : '查询',
//								handler : function(event) {
//									var uname = Ext.get('uname').dom.value
//									treePanel.loader.dataUrl += "&name="+encodeURI(uname,'utf-8') 
//									treePanel.loader.load(root, function(r,
//													options, success) {
//												
//												treePanel.loader.dataUrl = theUrl					
//											})
//									
//
//									// projectGridStore.load({
//									// params : {
//									// uname : uname
//									// },
//									// callback : function(r, options, success)
//									// {
//									// if (success) {
//									// Ext.Msg.alert('操作', '成功！');
//									// } else {
//									// Ext.Msg.alert('操作', '失败！');
//									// }
//									// }// callback function end
//									// });
//									// projectGridStore.load(params:{})
//
//								}
//							}]
//						}]
//			});
//
//	/**
//	 * 子菜单
//	 */
//	var win = false;
//	function search() {
//		// alert('!')
//		// var p = new Ext.Panel({
//		// title: 'My Panel',//标题
//		// collapsible:true,//右上角上的那个收缩按钮，设为false则不显示
//		//		       
//		// floating: true,
//		// renderTo: 'search',//这个panel显示在html中id为container的层中
//		// width:400,
//		// height:200,
//		// html: "<p>我是内容，我包含的html可以被执行！</p>"//panel主体中的内容，可以执行html代码
//		// });
//		/**
//		 * var p=new Ext.Panel({ title: 'Drag me', x: 100, y: 100, renderTo:
//		 * 'search', floating: true, frame: true, width: 400, height: 200,
//		 * draggable: true, closable:true, plugins: new Ext.ux.TabCloseMenu() })
//		 */
//
//		if (!win) {
//			win = new Ext.Window({
//						applyTo : 'body',
//						layout : 'fit',
//						width : 500,
//						height : 200,
//						closeAction : 'hide',
//						plain : true,
//						items : tab,
//						buttons : [{
//									text : '关闭',
//									handler : function() {
//										// alert(document.getElementById('search').innerHTML)
//										win.hide();
//									}
//
//								}]
//					});
//		}
//		win.show();
//
//	}
//
//	/**
//	 * 主菜单
//	 */
//	var btn2 = new Ext.Button({
//				text : "确认完成所有打分",
//				iconCls : 'icon-tick',
//				handler : function(){
//					Ext.Ajax.request({
//					   url: 'dindex.do?actionType=selectDepEmpsIndexs',
//					   success: function(){Ext.Msg.alert("","完成")}
//					});
//				}
//			});
//	
//	var btn1 = new Ext.Button({
//				text : "搜索",
//				iconCls : 'icon-search',
//				handler : search
//			});
//
//	this.toolbar = new Ext.Toolbar({
//				items : [btn1,btn2]
//			});
//
//	function action(btn) {
//		Ext.Msg.alert(btn.text)
//
//	}
//
//	// projectGridStore.load();
//	var rightP = new Ext.Panel({
//		id : 'centre2',
//		region : 'east',
//		title : '指标内容',
//		width : '75%',
//		collapsible : true,
//		autoScroll : true,
//		split : true,
//		html : '<iframe frameBorder="0" id="context" name="context" scrolling="auto" height="100%" width="100%"></iframe>'
//			// html:'<iframe scrolling="auto" frameborder="0" width="100%"
//			// height="100%" src="left.jsp"></iframe>'
//	})
//
//	var contentPanel1 = new Ext.Panel({
//				title : '菜单栏',
//				region : 'center',
//				// contentEl: 'west-div',
//				split : true,
//				border : true,
//				collapsible : true,
//				width : '25%',
//				minSize : 150,
//				maxSize : 300,
//				layout : "accordion",
//			
//				layoutConfig : {
//					animate : true
//				},
//				items : [{
//							title : " ",
//							html : '<div id="tree2"></div>'
//						}]
//			});
//
//	var rightPanel = new Ext.Panel({
//				id : 'centre',
//				region : 'center',
//				tbar : toolbar,
//				width : '100%',
//				title : '指标管理',
//				layout : 'border',
//				items : [contentPanel1, rightP]
//			})
//
//	new Ext.Viewport({
//				layout : 'border',
//				width : '100%',
//				items : [rightPanel]
//			})
//
//	// set the root node
//	var root = new Ext.tree.AsyncTreeNode({
//				text : '人员列表',
//				draggable : false,
//				iconCls : 'icon-group',
//				id : '-1'
//			
//			});
//	var treePanel = new Ext.tree.TreePanel({
//
//				id : 'center',
//				renderTo : 'tree2',
//				root : root,// 定位到根节点
//				// title : '人员列表',
//				rootVisible : true,
//				width : '100%',
//				autoScroll : true,
//				enableDD : true,
//				containerScroll : true,
//				loader : new Ext.tree.TreeLoader({
//							dataUrl : theUrl
//						})
//			});
//	root.expand(2);
//	treePanel.on('click', function(node, event) {
//		event.preventDefault(); // 这行是必须的
//		context.location.href = '/bpp/index.do?actionType=doSelectEmpIndex&empUid='
//				+ node.id
//
//	});


