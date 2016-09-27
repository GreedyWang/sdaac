Ext.onReady(function() {
		
	Ext.QuickTips.init();// 提示link，如果a标签有title的话
	// Ext.state.Manager.setProvider(new Ext.state.CookieProvider());//
	// 以cookie的方式记住用户对面板的部署
	Ext.BLANK_IMAGE_URL = 'images/default/blank.gif'

	var url1 = "<iframe scrolling='auto' frameborder='0' width='100%' height='100%' src='";
	var url2 = "'></iframe>";
	var detailsText = "";
	var sm = new Ext.grid.CheckboxSelectionModel();

	/** 列表grid */
	var url = 'epor.do?actionType=doSelectPrFormList';
	var gridToolBar = ''
	function initToolbar() {
		gridToolBar = new Ext.Toolbar({
			items : [{
				text : '如何打开/How to Open',
				iconCls : 'icon-upload',
				handler : function() {
					Ext.Msg.alert("",'双击打开/double click to open')
				}
			}]
		})
	}

	function getUrl() {
		
		if (document.getElementById('types').value == 2) {
			url = 'epor.do?actionType=doSelect4Anuo'
			initToolbar();
		}
		else if (document.getElementById('types').value == 1) {
			url = 'epor.do?actionType=doSelectApprovingPrForms'
			initToolbar();
		}
		else if (document.getElementById('paramUid').value != '') {
			url += '&self=y&uid=' + document.getElementById('paramUid').value;
		}
		if (document.getElementById('isFin').value == 1) {
			url += '&state=11';
		} else {
			url += '&isFin=n';
		}
		if(document.getElementById('area').value == 1){
			url += '&area=1';
		}
		if(document.getElementById('area').value == 3){
			url += '&area=3';
		}
		if(document.getElementById('area').value == 2){
			url += '&area=2';
		}
		if(document.getElementById('area').value == 4){
			url += '&area=4';
		}
		return url
	}

//-------------------------------
	var store = new Ext.data.JsonStore({
				url : getUrl(),
				autoLoad : true,																
				start : 0,  
  				limit : page_size ,
  				totalProperty : 'totalCount' ,
  				root : 'root', 
  				
  				baseParams:{uid : '' , state : '',depart : '' , benginTime : '',endTime : '' , prNo : '' },
  				
				fields : [{
							name : 'state',
							mapping : 'stateName'
						}, {
							name : "jsonData",
							mapping : "jsonData"
						}, {
							name : 'uname',
							mapping : 'tempolyeeByApplicantId.uname'
						}, {
							name : "departName",
							mapping : "tempolyeeByApplicantId.departId"
						}
						 ,  {
						 name : 'remark2',
						 mapping : 'remark2'
						 }
						, {
						 name : 'typeName',
						 mapping : 'typeName'
						 }
						 , {
						 name : 'ssidTip',
						 mapping : 'ssidTip'
						 }
						 , {
						 name : 'remark',
						 mapping : 'remark'
						 }
						, {
							name : 'uuid',
							mapping : 'uuid'
						}, {
							name : 'ssid',
							mapping : 'ssid'
						}, 'type', 'key', 'needExplain']
			})			
	
	var varstate='sa';
			
    function showUrl1(value)
	{
		alert(varstate);
//	  return  "<a class='x-btn' target='main' href='proposal.do?actionType=doSelecetMyTaskTeam&proposalID="+value+"'>详细</a>";
	
	}

	var cm = new Ext.grid.ColumnModel([ sm,
			{
				dataIndex : 'type',
				header : '流程类型/processName',
				sortable : true,
				width : 100
			}, {
				dataIndex : 'needExplain',
				header : '提案状态/status',
				// sortable : true,
				width : 100,
				renderer : function(val) {

					if (val == '1') {
						return '<span style="color:purple;">等待解释</span>';
					} else if (val == '0') {
						return '<span style="color:green;">正在审批</span>';
					} else if (val == '-1') {
						return '<span style="color:green;">被退回</span>';
					} else if (val == '4') {
						return '<span style="color:green;">编辑状态</span>';
					} else if (val == '5') {
						return '<span style="color:green;">已解释</span>';
					} else if (val == '6') {
						return '<span style="color:green;">完成</span>';
					}
					return '';

				}
			}, {
				dataIndex : 'state',
				header : '流程状态/status',
				sortable : true,
				width : 100
			}, {
				dataIndex : 'jsonData',
				header : '日期/applicationDate',
				sortable : true,
				 renderer: Ext.util.Format.dateRenderer('Y-m-d'),
				width : 80
			}, {
				dataIndex : 'uname',
				header : '申请人/applicant',
				sortable : true,
				width : 100
			}, {
				dataIndex : 'departName',
				header : '申请人部门/department',
				sortable : true,
				width : 80
			},{
				dataIndex : 'typeName',
				header : '类型/type',	
				sortable : true,
				width : 100
			},{
			dataIndex : 'ssidTip',
				header : '单号/SSID',	
				sortable : true,
				width : 100
			},{
			dataIndex : 'remark',
				header : '备注/Remark',			
				width : 300
			},{
			dataIndex : 'remark2',
				header : '附加信息/Remark',			
				width : 300
			}

	])
	
	var page_size = 30;
	var grid = new Ext.grid.GridPanel({
				store : store,
				sm : sm,
				cm : cm,
				tbar : gridToolBar,
				height : 300,
				width : 600,
				region : 'center',
				title : 'PR列表-完成状态的PR单,请在搜索栏中查询',
//				listeners:
//						{
//							"rowdblclick" : function(grid, rowIndex, e)
//							{
//								//alert(grid.getStore().getAt(rowIndex).data.state);
//								var data = grid.getStore().getAt(rowIndex).data;
//								if(data.type=='ePR') {
//									window.open('/bpp/epor.do?actionType=doSelectPrForm&prFormID='+data.key)
//								}else if(data.type=='物料主数据') {
////									alert(data.key)
//									window.open('/bpp/mainData.do?actionType=doShowDetails&id='+data.uuid)
//								}
//							}
//						},
				bbar : new Ext.PagingToolbar({
						    pageSize : page_size,
						    store : store,
						    displayInfo : true

				   }) ,
				id : 'center'
			})

	sm.on('rowselect', function(a, rowIndex, r) {
				var record = this.grid.store.getAt(rowIndex);
				varstate = record.get('state');
				var el = Ext.getCmp('bottom').body;
												var data = grid.getStore().getAt(rowIndex).data;
								if(data.type=='ePR') {
									el.update(url1+'epor.do?actionType=doSelectPrForm&admin=1&isTip=1&prFormID='+data.key+ url2);
								
								}else if(data.type=='物料主数据') {
									
									el.update(url1+'/bpp/mainData.do?actionType=doShowDetails&isTip=1&id='+data.uuid+ url2)
								
								}
				
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
				id : '',
				name : "取消"
			}, {
				id : 10025,
				name : "暂停"
			}, {
				id : 0,
				name : "个人"
			}, {
				id : 1,
				name : "部门经理审批"
			}, {
				id : 2,
				name : "财务预算员审批"
			}, {
				id : 10036,
				name : "相关部门审批"
			}, {
				id : 4,
				name : "采购员确认"
			}, {
				id : 5,
				name : "财务经理审批"
			}, {
				id : 6,
				name : "副总经理审批"
			}, {
				id : 7,
				name : "总经理审批"
			}, {
				id : 8,
				name : "回填信息"
			}, {
				id : 9,
				name : "PCLSAP录入"
			}, {
				id : 10,
				name : "等待采购员填写订单号"
			}, {
				id : 11,
				name : "完成"
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
									fieldLabel : 'PR流水号(前两位+后四位如:110222)',
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
								var prNo = Ext.get('ssid').dom.value
								store.baseParams['state'] = state,
									store.baseParams['uid'] = uid,
										store.baseParams['depart'] = depart,
											store.baseParams['benginTime'] = benginTime,
												store.baseParams['endTime'] = endTime,
												store.baseParams['prNo'] = prNo,
								store.load({
											params : {
												start : 0,  
  												limit : page_size
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

		var bottom = new Ext.Panel({
				region : 'south',
				title : '详细说明',
				id : 'bottom',
				autoScroll : true,
				collapsible : true,
				split : true,
				margins : '0 2 2 2',
				cmargins : '2 2 2 2',
				height : 180,
				html : detailsText
			})
	
	/**
	 * 主界面
	 */
	new Ext.Viewport({
				layout : 'border',
				height : '100%',
				width : 800,
				anchor : '80%',
				items : [tab, grid, bottom]

			})

	grid.render('centre')
});