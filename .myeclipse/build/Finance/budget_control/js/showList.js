Ext.onReady(function() {
	Ext.QuickTips.init();// 提示link，如果a标签有title的话
	// Ext.state.Manager.setProvider(new Ext.state.CookieProvider());//
	// 以cookie的方式记住用户对面板的部署
	Ext.BLANK_IMAGE_URL = 'images/default/blank.gif'
	var url1 = "<iframe scrolling='auto' frameborder='0' width='100%' height='100%' src='epor.do?actionType=doSelectPrForm&isTip=1&prFormID=";
	var url2 = "'></iframe>";
	var detailsText = "";
	/** 列表grid */
	var url = 'roomSystem.do?actionType=doShow';
	var gridToolBar = ''
	var year = 2011
	var nextYear = year + 1
	var afnYear = nextYear + 1
	var fm = Ext.form;



	function getUrl() {
		if (document.getElementById('types').value == 1) {
			url = 'roomSystem.do?actionType=doShow&state=1';
		}

		return url
	}

	/** 测试数据 */
	var testDate = [{
				'ccid' : 'DT02Y15000',
				'id' : 'co',
				'state' : '2011.01-2013.12.001',
				'name' : 'S009656070',
				'remark' : '废品损失（含料工费）',
				'departName' : '差旅市内',
				'title' : '测试会议1',
				'emp' : {
					'name' : 'AAA',
					'depName' : 'IT'
				},
				'isCycle' : 'a',
				'phone' : 'true',
				'projector' : 'a',
				'state2' : '1',
				'begin' : '2011-2-23 10:25',
				'end' : '2011-2-23 11:25',
				'room' : '2#'
			}, {
				'id' : '1',
				'title' : '测试会议1',
				'emp' : {
					'name' : 'AAA',
					'depName' : 'IT'
				},
				'isCycle' : 'a',
				'phone' : 'true',
				'projector' : 'a',
				'state2' : '1',
				'begin' : '2011-2-23 10:25',
				'end' : '2011-2-23 11:25',
				'room' : '1#'
			}]

	var store = new Ext.data.JsonStore({
				// url : getUrl(),
				// autoLoad:true,
				fields : ['isCycle', 'projector', 'remark', 'ccid', {
							name : 'isCycle',
							mapping : 'cycleType'
						}, {
							name : 'id',
							mapping : 'id'
						}, {
							name : 'title',
							mapping : 'context'
						}, {
							name : 'phone',
							mapping : 'phoneid'
						}, {
							name : 'room',
							mapping : 'roomId'
						}, {
							name : 'begin',
							mapping : 'begintime'
						}, {
							name : 'end',
							mapping : 'endtime'
						}, {
							name : 'state',
							mapping : 'state'
						}, {
							name : 'name',
							mapping : 'name'
						}, {
							name : 'departName',
							mapping : 'emp.departId'
						}]
			})
	store.loadData(testDate);
	// -------------------------------end test date
	function changeColor(val) {
		return '<span style="color:green;">' + val + '</span>';
	}

	var cm = new Ext.grid.ColumnModel([{
				dataIndex : 'id',
				header : 'fun',
				sortable : true,
				width : 50,
				renderer : changeColor
			}, {
				dataIndex : 'state',
				header : 'Budget Version',
				sortable : true,
				width : 120,
				renderer : changeColor
			}, {
				dataIndex : 'name',
				header : 'Account',
				sortable : true,
				width : 100,
				renderer : changeColor
			}, {
				dataIndex : 'remark',
				header : 'Remark',
				sortable : true,
				width : 150,
				renderer : changeColor
			}, {
				dataIndex : 'ccid',
				header : 'ccid',
				sortable : true,
				renderer : changeColor,
				width : 100
			}, {
				dataIndex : 'end',
				header : 'Budget Assumption/Remark',
				sortable : true,
				renderer : changeColor,
				width : 130
			},
			// first year
			{
				id : 'a1',
				dataIndex : 'room',
				header : year + ' Jan',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			}, {
				id : 'a2',
				dataIndex : 'room',
				header : year + ' Feb',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			}, {
				id : 'a3',
				dataIndex : 'room',
				header : year + ' Mar',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			}, {
				id : 'a4',
				dataIndex : 'room',
				header : year + ' Apr',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			}, {
				id : 'a5',
				dataIndex : 'room',
				header : year + ' May',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			}, {
				id : 'a6',
				dataIndex : 'room',
				header : year + ' Jun',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			}, {
				id : 'a7',
				dataIndex : 'room',
				header : year + ' Jul',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			}, {
				id : 'a8',
				dataIndex : 'room',
				header : year + ' Aug',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			}, {
				id : 'a9',
				dataIndex : 'room',
				header : year + ' Sep',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			}, {
				id : 'a10',
				dataIndex : 'room',
				header : year + ' Oct',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			}, {
				id : 'a11',
				dataIndex : 'room',
				header : year + ' Nov',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			}, {
				id : 'a12',
				dataIndex : 'room',
				header : year + ' Dec',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			},// nextYear
			{
				dataIndex : 'room',
				header : nextYear + ' Jan',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			}, {
				dataIndex : 'room',
				header : nextYear + ' Feb',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			}, {
				dataIndex : 'room',
				header : nextYear + ' Mar',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			}, {
				dataIndex : 'room',
				header : nextYear + ' Apr',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			}, {
				dataIndex : 'room',
				header : nextYear + ' May',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			}, {
				dataIndex : 'room',
				header : nextYear + ' Jun',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			}, {
				dataIndex : 'room',
				header : nextYear + ' Jul',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			}, {
				dataIndex : 'room',
				header : nextYear + ' Aug',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			}, {
				dataIndex : 'room',
				header : nextYear + ' Sep',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			}, {
				dataIndex : 'room',
				header : nextYear + ' Oct',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			}, {
				dataIndex : 'room',
				header : nextYear + ' Nov',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			}, {
				dataIndex : 'room',
				header : nextYear + ' Dec',
				sortable : true,
				editor : new fm.NumberField({

				}),
				width : 60

			}])
	
	// ----init ----
	for (var i = 19; i < 30; i++) {
		cm.setHidden(i, true)
	}
	// ----end init

	/**
	 * menu
	 */

	var tbar = new Ext.Toolbar({
				items : [ {
							text : '添加行',
							iconCls : 'icon-add',
							handler : function() {
							}
						}, {
							text : '删除行',
							iconCls : 'icon-add',
							handler : function() {
							}
						},{
							text : '保存',
							iconCls : 'icon-add',
							handler : function() {
							}
						}, {
							text : '',
							iconCls : 'icon-add',
							handler : function() {
							}
						}]

			});

	var tbar2 = new Ext.Toolbar();
	tbar2.render('header');
	tbar2.add({
				text : '拷贝/Tools',
				iconCls : 'bmenu', // <-- icon
				menu : new Ext.menu.Menu({
							id : 'mainMenu',
							items : [{
										text : '拷贝' + year,
										handler : function() {
//											for( var i = 1 ;i<13 ;i++) {
												alert(cm.getColumnById('a'+1))
											//	cm.getColumnById('a'+i).value = 1000;
//											}
										}
									}, {
										text : '拷贝' + year + 1,
										handler : function() {

										}
									}, {
										text : '拷贝' + year + 2,
										handler : function() {

										}
									}]
						})
			})

	tbar2.add({
				text : '收缩/Tools',
				iconCls : 'bmenu', // <-- icon
				menu : new Ext.menu.Menu({
							id : 'mainMenu',
							items : [{
										text : '收缩列头信息',
										handler : function() {
											if (cm.isHidden(0)) {
												cm.setHidden(0, false)
												cm.setHidden(1, false)
												cm.setHidden(2, false)
												cm.setHidden(4, false)
												cm.setHidden(5, false)
											} else {
												cm.setHidden(0, true)
												cm.setHidden(1, true)
												cm.setHidden(2, true)
												cm.setHidden(4, true)
												cm.setHidden(5, true)
											}

										}
									}, {
										text : '收缩' + year + '数据',
										handler : function() {
											if (cm.isHidden(7)) {
												// cm.setHidden(6, false)
												cm.setHidden(7, false)
												cm.setHidden(8, false)
												cm.setHidden(9, false)
												cm.setHidden(10, false)
												cm.setHidden(11, false)
												cm.setHidden(12, false)
												cm.setHidden(13, false)
												cm.setHidden(14, false)
												cm.setHidden(15, false)
												cm.setHidden(16, false)
												cm.setHidden(17, false)
											} else {
												// cm.setHidden(6, true)
												cm.setHidden(7, true)
												cm.setHidden(8, true)
												cm.setHidden(9, true)
												cm.setHidden(10, true)
												cm.setHidden(11, true)
												cm.setHidden(12, true)
												cm.setHidden(13, true)
												cm.setHidden(14, true)
												cm.setHidden(15, true)
												cm.setHidden(16, true)
												cm.setHidden(17, true)
											}

										}
									}, {
										text : '收缩' + nextYear,
										handler : function() {
											if (cm.isHidden(19)) {
												for (var i = 19; i < 30; i++) {
													cm.setHidden(i, false)
												}
											} else {

												for (var i = 19; i < 30; i++) {
													cm.setHidden(i, true)
												}

											}
										}
									}, {
										text : '收缩' + afnYear,
										handler : function() {

										}
									}]
						})
			})
	
		tbar2.add({
				text : '导入数据/Tools',
				iconCls : 'bmenu', // <-- icon
				menu : new Ext.menu.Menu({
							id : 'mainMenu',
							items : [{
										text : '导入数据',
										handler : function() {
											var url ='lookup.jsp'
											window.open(url,null,
											"height=800,width=1000,status=yes,toolbar=no,menubar=no,location=no");
										}
									}]
						})
			})		

			tbar2.add({
				text : '确认完成，提交财务部门',
				iconCls : 'bmenu', // <-- icon
				handler : function() {}
				
			})		

	var grid = new Ext.grid.EditorGridPanel({
				store : store,
				cm : cm,
				tbar : tbar,
				height : 300,
				width : 500,
				region : 'center',
				title : '会议预定列表',
				id : 'center'
			})

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
				id : 'depart',
				fieldLabel : '部门',
				name : 'depart',
				// hiddenName:'depart',
				store : dplistStore,
				valueField : 'id',
				displayField : 'name',
				typeAhead : true,
				mode : 'local',
				triggerAction : 'all',
				emptyText : '选择部门',
				selectOnFocus : true,
				// width:190,
				blankText : 'empty',
				// allowBlank : false,
				tabIndex : 1,
				anchor : '80%'
			})

	/** 加载部门信息 */

	var stateData = [{
				id : '',
				name : '所有'
			}, {
				id : 0,
				name : "个人"
			}, {
				id : 1,
				name : "待审批"
			}, {
				id : 2,
				name : "正常"
			}, {
				id : -1,
				name : "不批准"
			}]

	var stateStore = new Ext.data.JsonStore({
				data : stateData,
				fields : ['id', 'name']
			})

	var stateList = new Ext.form.ComboBox({
				id : 'state',
				fieldLabel : '状态',
				name : 'state',
				// hiddenName:'depart',
				store : stateStore,
				valueField : 'id',
				displayField : 'name',
				typeAhead : true,
				mode : 'local',
				triggerAction : 'all',
				emptyText : '选择状态',
				selectOnFocus : true,
				// width:190,
				blankText : 'empty',
				// allowBlank : false,
				tabIndex : 1,
				anchor : '80%'
			})

	/** 搜索栏 */

	var tab = new Ext.FormPanel({

		labelAlign : 'top',
		title : '搜索栏/search bar',
		region : 'north',
		split : true,
		bodyStyle : 'padding:5px,5px,0',
		frame : true,
		width : '100%',
		height : 90,

		// autoHeight: true,
		items : [{
			layout : 'column',
			items : [{
						columnWidth : .14,
						layout : 'form',
						items : [stateList]
					}, {
						columnWidth : .14,
						layout : 'form',
						items : [{
									fieldLabel : '编号',
									xtype : 'textfield',
									name : 'ssid',
									id : 'ssid',
									tabIndex : 0,
									anchor : '80%'
								}]
					}, {
						columnWidth : .14,
						layout : 'form',
						items : [{
									fieldLabel : '员工姓名',
									xtype : 'textfield',
									name : 'uid',
									id : 'uid',
									tabIndex : 0,
									anchor : '80%'
								}]
					}, {
						columnWidth : .14,
						layout : 'form',
						items : [departList]
					}, {
						columnWidth : .14,
						layout : 'form',
						items : [{
									fieldLabel : '开始日期',
									xtype : 'datefield',
									format : 'Y-m-d',
									// value : new Date(),
									name : 'beginTime',
									id : 'beginTime',
									anchor : '80%'
								}]
					}, {
						columnWidth : .14,
						layout : 'form',
						items : [{
									fieldLabel : '结束日期',
									xtype : 'datefield',
									format : 'Y-m-d',
									// value : new Date(),
									name : 'endTime',
									id : 'endTime',
									anchor : '80%'
								}]
					}, {
						columnWidth : .14,
						layout : 'form',
						buttons : [{
							text : '查询',
							handler : function(event) {
								var uid = Ext.get('uid').dom.value
								if (document.getElementById('paramUid').value != '') {
									uid = document.getElementById('paramUid').value;
								}
								var depart = departList.getValue();
								var state = stateList.getValue();
								var benginTime = Ext.get('beginTime').dom.value
								var endTime = Ext.get('endTime').dom.value
								// var prNo = Ext.get('ssid').dom.value
								store.reload({
											params : {
												uid : uid,
												state : state,
												depart : depart,
												benginTime : benginTime,
												endTime : endTime,
												prNo : prNo
											},
											callback : function(r, options,
													success) {
												// if (success) {
												// Ext.Msg.alert('操作',
												// '成功！');
												// } else {
												// Ext.Msg.alert('操作',
												// '失败！');
												// }
											}
										});
							}
						}]
					}]
		}]

	});

	/**
	 * 主界面
	 */
	new Ext.Viewport({
				layout : 'border',
				tbar : tbar2,
				height : '100%',
				width : 800,
				anchor : '80%',
				items : [tab, grid]

			})

	grid.render('centre')
});