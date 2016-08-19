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
	



	var tb = new Ext.Toolbar();
	tb.render('header');


	tb.add({
		text : '帮助/HELP',
		iconCls : 'bmenu', // <-- icon
		handler:function(){
			Ext.Msg.alert('','联系电话 : 3046; Mail：yongmin.wang@delphi.com')
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
			text : "基础数据/BaseInfo"
		});
			
	var item1 = new Ext.tree.TreeNode({
				id : "item1",// 根节点id
				leaf : false,
				hrefTarget : 'SAP_REPORTS/update.jsp?aa=sp',
				text : "人员&组织/People&Org."
			});
			
		var item1A = new Ext.tree.TreeNode({
				id : "item1A",// 根节点id
				leaf : true,
				hrefTarget : 'dMS.do?actionType=doGetMyTeam',
				text : "查看我的组员"
			});		
		var item1B = new Ext.tree.TreeNode({
				id : "item1B",// 根节点id
				leaf : true,
				hrefTarget : 'DMS/emp/EmpManager.jsp',
				text : "查看员工信息"
			});
		var item1C = new Ext.tree.TreeNode({
				id : "item1C",// 根节点id
				leaf : true,
				hrefTarget : 'employee.do?actionType=beforeAddDMSEmp',
				text : "添加新员工"
			});
			
			item1.appendChild(item1A);	
			item1.appendChild(item1B);	
			item1.appendChild(item1C);
	//--------------------------------------------------------------------
	var item2 = new Ext.tree.TreeNode({
			id : "item2",// 根节点id
			leaf : true,
			hrefTarget : 'dMS.do?actionType=doShowIndicate',
			text : "指标/Indicate"
		});
			
	var item3 = new Ext.tree.TreeNode({
		id : "item3",// 根节点id
		leaf : true,
		hrefTarget : 'DMS/BaseInfo/index.jsp',
		text : "角色/Role"
	});	
	
//---------------BONUS----------------
	var item4 = new Ext.tree.TreeNode({
		id : "item4",// 根节点id
		leaf : false,
		hrefTarget : 'DMS/bouns.jsp',
		text : "奖金/Bonus"
	});	

	var item11 = new Ext.tree.TreeNode({
		id : "item11",// 根节点id
		leaf : true,
		hrefTarget : 'DMS/BaseInfo/bonus/settingbouns.jsp',
		text : "设置奖金/Bonus"
	});	
	var item12 = new Ext.tree.TreeNode({
		id : "item12",// 根节点id
		leaf : true,
		hrefTarget : 'DMS/BaseInfo/bonus/bouns.jsp',
		text : "查询奖金/Bonus"
	});	
	

//---------------END----------------
			

	//---------------------------------
		var root3 = new Ext.tree.TreeNode({
			id : "root3",// 根节点id
			leaf : false,
			text : "打分/Scoring"
		});
			
	var item31 = new Ext.tree.TreeNode({
				id : "item31",// 根节点id
				leaf : true,
				hrefTarget : 'dMS.do?actionType=doGetUserInfo',
				text : "打分"
			});
	
		var item34 = new Ext.tree.TreeNode({
				id : "item34",// 根节点id
				leaf : true,
				hrefTarget : 'dMS.do?actionType=doScoreReady2',
				text : "繼續打分"
			});
			
	var item32 = new Ext.tree.TreeNode({
			id : "item32",// 根节点id
			leaf : true,
			hrefTarget : 'dMS.do?actionType=doGetUserInfo&d=4score',
			text : "查看得分"
		});
			
	var item33 = new Ext.tree.TreeNode({
		id : "item33",// 根节点id
		leaf : true,
		hrefTarget : 'DMS/Map.html',
		text : "查看历史记录"
	});	
	
		//---------------------------------
	
		var root4 = new Ext.tree.TreeNode({
			id : "root4",// 根节点id
			leaf : false,
			text : "报表/Reports"
		});
	
		var report1 = new Ext.tree.TreeNode({
			id : "report1",// 根节点id
			leaf : true,
			hrefTarget : 'http://test2003/Reports/Pages/Report.aspx?ItemPath=%2fSDAAC%2fZ4',
			text : "报表1"
		});
		
		var report2 = new Ext.tree.TreeNode({
			id : "report2",// 根节点id
			leaf : true,
			hrefTarget : 'http://test2003/Reports/Pages/Report.aspx?ItemPath=%2fSDAAC%2fZ4',
			text : "报表2"
		});
	

		
	root1.appendChild(item1);
	root1.appendChild(item2);
	
	if (roles.indexOf('DMS_admin') > 0) {
		root1.appendChild(item3);
		item4.appendChild(item11);
		item4.appendChild(item12);
		root1.appendChild(item4);
	}
	
	
//	root2.appendChild(item21);
	
	root3.appendChild(item31);
	root3.appendChild(item32);
//	root3.appendChild(item33);
//	root3.appendChild(item34);
	
	root4.appendChild(report1);
	
	root.appendChild(root1);
//	root.appendChild(root2);
	root.appendChild(root3);
	root.appendChild(root4);

	
	
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
