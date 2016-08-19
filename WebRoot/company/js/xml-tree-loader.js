Ext.BLANK_IMAGE_URL = 'ext/resources/images/default/s.gif';
Ext.app.BookLoader = Ext.extend(Ext.ux.XmlTreeLoader, {
			processAttributes : function(attr) {
				if (attr.menu) { // is it an author node?

					// Set the node text that will show in the tree since our
					// raw data does not include a text attribute:
					attr.text = attr.name;

					// Author icon, using the gender flag to choose a specific
					// icon:
					// attr.iconCls = 'author-' + attr.gender;

					// Override these values for our folder nodes because we are
					// loading all data at once. If we were
					// loading each node asynchronously (the default) we would
					// not want to do this:
					attr.loaded = true;
					// attr.expanded = true;
				} else { // is it a book node?

					// Set the node text that will show in the tree since our
					// raw data does not include a text attribute:
					attr.text = attr.name;

					// Book icon:
					// attr.iconCls = 'book';

					// Tell the tree this is a leaf node. This could also be
					// passed as an attribute in the original XML,
					// but this example demonstrates that you can control this
					// even when you cannot dictate the format of
					// the incoming source XML:
					attr.leaf = true;
				}
			}
		});
/**
 * 得到xmlName参数
 * 
 * @param {}
 *            name
 * @return {String}
 */
function getParams(name) {
	if (location.href.indexOf("?") == -1
			|| location.href.indexOf(name + '=') == -1) {
		return '';
	} else {
		return location.href.substring(location.href.indexOf("=") + 1);
	}

}

Ext.onReady(function() {

	var detailsText = '<i>Select a category to see more information...</i>';
	// var url = 'company/data/' + getParams('xml')
	var url = 'company/data/IT.xml'
	var tpl = new Ext.Template('<h2 class="title">{title}</h2>',

			'<p><b>说明</b>: {innerText}</p>',
			'<p><a href="{url}" target="_blank">check this</a></p>');
	tpl.compile();
	// 工具版面
	var appTreePanel = new Ext.tree.TreePanel({
				// xtype : 'treepanel',
				id : 'tree-panel',
				region : 'center',
				margins : '2 2 0 2',
				autoScroll : true,
				rootVisible : false,
				root : new Ext.tree.AsyncTreeNode(),

				// Our custom TreeLoader:
				loader : new Ext.app.BookLoader({
							// dataUrl:'company/data/IT.xml'
							dataUrl : url
						}),

				listeners : {
					'render' : function(tp) {
						tp.getSelectionModel().on('selectionchange',
								function(tree, node) {
									var el = Ext.getCmp('details-panel').body;
									if (node.leaf) {
										tpl.overwrite(el, node.attributes);
									} else {
										el.update(detailsText);
									}
								})
					}

				}
			})
	var appPanel = new Ext.Panel({
				region : 'west',
				title : '文件列表',
				layout : 'border',
				collapsible : true,
				autoScroll : true,
				split : true,
				width : 260,
				height : 600,
				items : [appTreePanel, {
							region : 'south',
							title : '详细说明',
							id : 'details-panel',
							autoScroll : true,
							collapsible : true,
							split : true,
							margins : '0 2 2 2',
							cmargins : '2 2 2 2',
							height : 180,
							html : detailsText
						}]
			});

	var newsGridStore = new Ext.data.JsonStore({
		fields : ['id', 'title', {
					name : 'releaseTime',
					mapping : 'releaseTime',
					type : 'date',
					dateFormat : 'n/j/Y'
				}, {
					name : 'departName',
					mapping : 'releaseEmployeeId.tdepartment.name'
				}]
			// ,
			// proxy : new Ext.data.ScriptTagProxy({
			// url : ''
			// })

		})

	newsManager.searchHotNewsExt(function(data) {
				newsGridStore.loadData(data)
			});

	/** 加载部门信息 */
	var dplistdata = [{
				id : '',
				name : "sdaac"
			}, {
				id : 10025,
				name : "物流部PC&L"
			}, {
				id : 10026,
				name : "运营支持与技术服务部"
			}, {
				id : 10030,
				name : "人事行政部HR&Admin"
			}, {
				id : 10031,
				name : "党工办"
			}, {
				id : 10036,
				name : "项目管理部"
			}, {
				id : 10038,
				name : "管理组(HVAC)"
			}, {
				id : 10039,
				name : "管理组(PTC)"
			}, {
				id : 10040,
				name : "产品工程部PE"
			}, {
				id : 10041,
				name : "客户满意部CS"
			}, {
				id : 10044,
				name : "制造工程部ME"
			}, {
				id : 10046,
				name : "IT"
			}, {
				id : 10047,
				name : "Finance"
			}, {
				id : 10048,
				name : "采购部"
			}, {
				id : 10049,
				name : "Sales"
			}, {
				id : 10052,
				name : "SAP"
			}, {
				id : 10057,
				name : "环保EHS"
			}, {
				id : 10058,
				name : "实验室"
			}]

	var dplistStore = new Ext.data.JsonStore({
				data : dplistdata,
				fields : ['id', 'name']
			})
	var departList = new Ext.form.ComboBox({
				x : 60,
				y : 35,
				id : 'department',
				// fieldLabel: '部门',
				// fieldLabel
				// name:'department',
				// hiddenName:'department',
				store : dplistStore,
				valueField : 'id',
				displayField : 'name',
				typeAhead : true,
				mode : 'local',
				triggerAction : 'all',
				emptyText : '选择部门',
				selectOnFocus : true,
				width : 190,
				blankText : 'empty',
				allowBlank : false
			})

	// grid 的 toolbar
	var newsGridBar = new Ext.Toolbar({
		items : [{
			xtype : 'button',
			text : "搜索",
			iconCls : 'icon-search',
			handler : function() {
				var wind = new Ext.Window({
					id : 'search',
					title : '搜索',
					width : 500,
					height : 300,
					layout : 'fit',
					plain : true,
					buttonAlign : 'center',
					items : [{ // 搜索面板
						xtype : 'form',
						baseCls : 'x-plain',
						layout : 'absolute',
						items : [{
									x : 0,
									y : 5,
									xtype : 'label',
									text : '主题:'
								}, {
									x : 60,
									y : 5,
									id : 'title',
									xtype : 'textfield',
									name : 'title'
								}, {
									x : 0,
									y : 35,
									xtype : 'label',
									text : '部门:'
								}, departList, {
									x : 0,
									y : 65,
									xtype : 'label',
									text : '开始日期:'
								}, {
									x : 60,
									y : 65,
									id : 'startTime',
									xtype : 'datefield',
									name : 'startTime'
								}, {
									x : 200,
									y : 65,
									xtype : 'label',
									text : '结束日期:'
								}, {
									x : 260,
									y : 65,
									id : 'endTime',
									xtype : 'datefield',
									name : 'endTime'
								}]
					}],
					buttons : [{
								text : '关闭',
								handler : function() {
									wind.close();
								}

							}, {
								text : '搜索',
								handler : function() {
									var news = {
										title : Ext.get('title').dom.value,
										startTime : Ext.get('startTime').dom.value,
										endTime : Ext.get('endTime').dom.value,
										releaseEmployeeId : {
											tdepartment : {
												id : departList.getValue()
											}
										}
									}
									newsManager.searchHotNews(news, function(
													data) {
												newsGridStore.loadData(data)
											});
								}
							}]
				});
				wind.show()
			}
		}]
	})
	// 分页条
	var pagingBar = new Ext.PagingToolbar({
				pageSize : 100,
				store : newsGridStore,
				displayInfo : true,
				displayMsg : 'Displaying topics {0} - {1} of {2}',
				emptyMsg : "No topics to display"

			});

	var newsGrid = new Ext.grid.GridPanel({
				title : '公司新闻',
				region : 'center',
				store : newsGridStore,
				tbar : newsGridBar,
				autoScroll : true,
				columns : [{
							id : 'title',
							header : "题目",
							dataIndex : 'title',
							width : 300
						}, {
							id : 'releaseTime',
							header : "发布日期",
							dataIndex : 'releaseTime',
							// renderer:showUrl1,
							sortable : true,
							width : 70
						}, {
							id : 'departName',
							header : "部门部门",
							dataIndex : 'departName',
							// renderer:showUrl1,
							sortable : true,
							width : 80
						}],
				sm : new Ext.grid.RowSelectionModel({
							singleSelect : true
						}),
				viewConfig : {
					forceFit : true
				},
				bbar : pagingBar,
				height : 350,
				width : 600

			})
	// newsGridStore.load({
	// params : {
	// start : 0,
	// limit : 2
	// }
	// });
	newsGrid.getSelectionModel().on('rowselect', function(sm, rowIndex, r) {

				newsManager.show(r.data.id, function(data) {
							var theHtml = '<html>' + data.context + '</html>'
							var wind = new Ext.Window({
										id : 'newsDetails',
										title : data.title,
										width : 800,
										height : 600,
										autoScroll : true,
										footer : true,
										layout : 'fit',
										plain : true,
										buttonAlign : 'center',
										html : theHtml
									})
							wind.show();
						})

			})
	// 最右边的panel
	var rightPanel = new Ext.Panel({
				region : 'east',
				title : 'xxxx',
				collapsible : true,
				autoScroll : true,
				split : true,
				width : 240
			})

	var bottomPanelBar = new Ext.Toolbar({
				items : [{
							xtype : 'button',
							text : "IT",
							handler : function() {
								url = 'company/data/10046.xml'
								appTreePanel.loader.dataUrl = url
								appTreePanel.root.reload();
							}
						}, {
							xtype : 'button',
							text : "HR",
							handler : function() {
								url = 'company/data/10030.xml'
								appTreePanel.loader.dataUrl = url
								appTreePanel.root.reload();
							}
						}, {
							xtype : 'button',
							text : "PE",
							handler : function() {
								url = 'company/data/10040.xml'
								appTreePanel.loader.dataUrl = url
								appTreePanel.root.reload();
							}
						}, {
							xtype : 'button',
							text : "PM",
							handler : function() {
								url = 'company/data/10036.xml'
								appTreePanel.loader.dataUrl = url
								appTreePanel.root.reload();
							}

						}, {
							xtype : 'button',
							text : "LU",
							handler : function() {
								url = 'company/data/10058.xml'
								appTreePanel.loader.dataUrl = url
								appTreePanel.root.reload();
							}

						}, {
							xtype : 'button',
							text : "ME",
							handler : function() {
								url = 'company/data/10044.xml'
								appTreePanel.loader.dataUrl = url
								appTreePanel.root.reload();
							}

						}, {
							xtype : 'button',
							text : "PTC",
							handler : function() {
								url = 'company/data/10039.xml'
								appTreePanel.loader.dataUrl = url
								appTreePanel.root.reload();
							}

						}, {
							xtype : 'button',
							text : "SAP",
							handler : function() {
								url = 'company/data/10052.xml'
								appTreePanel.loader.dataUrl = url
								appTreePanel.root.reload();
							}

						}, {
							xtype : 'button',
							text : "EHS",
							handler : function() {
								url = 'company/data/10057.xml'
								appTreePanel.loader.dataUrl = url
								appTreePanel.root.reload();
							}

						}, {
							xtype : 'button',
							text : "HAVC",
							handler : function() {
								url = 'company/data/10038.xml'
								appTreePanel.loader.dataUrl = url
								appTreePanel.root.reload();
							}

						}, {
							xtype : 'button',
							text : "PC&L",
							handler : function() {
								url = 'company/data/10025.xml'
								appTreePanel.loader.dataUrl = url
								appTreePanel.root.reload();
							}

						}, {
							xtype : 'button',
							text : "Market",
							handler : function() {
								url = 'company/data/10049.xml'
								appTreePanel.loader.dataUrl = url
								appTreePanel.root.reload();
							}

						}, {
							xtype : 'button',
							text : "Finance",
							handler : function() {
								url = 'company/data/10047.xml'
								appTreePanel.loader.dataUrl = url
								appTreePanel.root.reload();
							}

						}, {
							xtype : 'button',
							text : "登录",
							iconCls : 'login',
							handler : function() {
								window.open('../welcome/Login2.jsp')
							}
						}]

			})
	// 最上边的panel
	var topPanel = new Ext.Panel({
		region : 'north',
		title : 'SDAAC内部网站',	
		height :105,
		tbar : bottomPanelBar,
		items : [{		
					x:5,
					y:0,
					xtype : 'button',
					text : 'SDAAC ID Change Password SDAAC帐号修改密码',
					iconCls : 'wrench',
					handler : function() {
						window.open('http://10.243.75.13/iisadmpwd/aexp2b.asp')
					}
				}
				, {			
					x:300,
					y:0,
					xtype : 'button',
					text : 'Address List ',
					iconCls : 'addressbook',
					handler : function() {
						window
								.open(' http://10.243.75.13/info/address list.htm')
					}
				}
				]
	})
	// 最下边的panel
	var bottomPanel = new Ext.Panel({
		region : 'south'

			// height:30
		})
	var viewport = new Ext.Viewport({

				layout : 'border',
				width : '1000',

				items : [newsGrid, appPanel, rightPanel, topPanel, bottomPanel]
			});
});