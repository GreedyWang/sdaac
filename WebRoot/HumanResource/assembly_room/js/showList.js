Ext.onReady(function() {
	Ext.QuickTips.init();// 提示link，如果a标签有title的话
	//Ext.state.Manager.setProvider(new Ext.state.CookieProvider());// 以cookie的方式记住用户对面板的部署
	Ext.BLANK_IMAGE_URL = 'images/default/blank.gif'
	var url1 = "<iframe scrolling='auto' frameborder='0' width='100%' height='100%' src='epor.do?actionType=doSelectPrForm&isTip=1&prFormID=";
	var url2 = "'></iframe>";
	var detailsText = "";
	/** 列表grid */
	var url = 'roomSystem.do?actionType=doShow';
	var gridToolBar = ''

	
	function getUrl(){
		if(document.getElementById('types').value==1){
			url = 'roomSystem.do?actionType=doShow&state=1';		
		}
		
		return url
	}
	/**测试数据*/
	var testDate = [{'id':'1','title':'测试会议1','emp':{'name':'AAA','depName':'IT'},'isCycle':'a',
		'phone':'true','projector':'a','state2':'1','begin':'2011-2-23 10:25','end':'2011-2-23 11:25','room':'2#'},
		{'id':'1','title':'测试会议1','emp':{'name':'AAA','depName':'IT'},'isCycle':'a',
		'phone':'true','projector':'a','state2':'1','begin':'2011-2-23 10:25','end':'2011-2-23 11:25','room':'1#'}]
		
	var store = new Ext.data.JsonStore({
				url : getUrl(),
				autoLoad:true,
				fields : ['isCycle','projector',						
						{name :'isCycle' ,mapping:'cycleType'},
						{name :'id' ,mapping:'id'},
						{name :'title' ,mapping:'context'},
						{name :'phone' ,mapping:'phoneid'},
						{name :'room' ,mapping:'roomId.name'},
						{name :'begin' ,mapping:'begintime'},
						{name :'end' ,mapping:'endtime'},
						{
							name : 'state',
							mapping : 'state'
						},{
							name : 'name',
							mapping : 'emp.uname'
						},{
							name : 'departName',
							mapping : 'emp.departId'
						}]
			})
	//store.loadData(testDate);
	//-------------------------------end test date

			
	var cm = new Ext.grid.ColumnModel([
//		{
//				dataIndex : 'id',
//				header : '详细',
//				sortable : true,
//				width : 100,
//				renderer : function(val,rowIndex){
//					if(document.getElementById('types').value==1){
//					 return  "<a class='x-btn' target='main' href='roomSystem.do?actionType=doShowDetail&begin="+begin+"&end="+end+"&flag=1&id="+val+"'>详细</a>";
//					}else {
//					 return  "<a class='x-btn' target='main' href='roomSystem.do?actionType=doShowDetail&begin="+begin+"&end="+end+"&id="+val+"'>详细</a>";
//					}
//				}
//
//			},
		{
				dataIndex : 'state',
				header : '状态',
				//sortable : true,
				width : 70,
				renderer : function(val){
					
					        if(val == '2'){
					            return '<span style="color:green;">正常</span>';
					        }else if(val == '1'){
					        	return '<span style="color:green;">待审批</span>';
					        }else if(val == '0'){
					        	return '<span style="color:green;">个人状态</span>';
					        }else if(val == '-1'){
					        	return '<span style="color:green;">不批准</span>';
					        }
					        return '';
					
				}
			},{
				dataIndex : 'name',
				header : '姓名',
				sortable : true,
				width : 100
			}, {
				dataIndex : 'departName',
				header : '部门',
				sortable : true,
				width : 100
			}, {
				dataIndex : 'begin',
				header : '开始时间',
				sortable : true,
				// renderer: Ext.util.Format.dateRenderer('Y-m-d'),
				width : 100
			}, {
				dataIndex : 'end',
				header : '结束时间',
				sortable : true,
				// renderer: Ext.util.Format.dateRenderer('Y-m-d'),
				width : 100
			}, {
				dataIndex : 'room',
				header : '会议室',
				sortable : true,
				width : 100

			}
			, {
				dataIndex : 'phone',
				header : '电话机',
				sortable : true,
				renderer : function(val){ 
					if(val == 0) { return '不需求' } 
						else if (val == 1) {return '需要'} 
						},
				width : 100

			}, {
				dataIndex : 'projector',
				header : '投影仪',
				sortable : true,
				renderer : function(val){ 
					if(val == 0) { return '不需求' } 
						else if (val == 1) {return '需要'} 
						},
				width : 100

			}, {
				dataIndex : 'isCycle',
				header : '是否周期会议',
				sortable : true,
				renderer : function(val){
					        if(val == '0'){
					            return '单次';
					        }else if(val == '1'){
					        	return '周循环';
					        }else if(val == '2'){
					        	return '月循环';
					        }
					},
					width : 100
			}, {
				dataIndex : 'id',
				header : '编号',
				sortable : true,
				width : 100

			}])

	var grid = new Ext.grid.GridPanel({
				store : store,
				cm : cm,	
				height:300,
				width : 500,
				region : 'center',
				title : '会议预定列表(默认为14天内的记录)-双击显示详细信息',
				id : 'center'
			})
	function rowdblclickFn(grid, rowIndex, e){//双击事件  
              var row = grid.store.getById(grid.store.data.items[rowIndex].id);  
              var begin = row.get("begin");  
              var end = row.get("end"); 
              var val = row.get("id");
              if(document.getElementById('types').value==1){
           
							window.location.target = 'blank'
							window.location.href = '/bpp/roomSystem.do?actionType=doShowDetail&flag=1&begin='+begin+'&end='+end+'&id='+val;
					}else {
						//	window.location.target = '_blank'
							//alert('/bpp/roomSystem.do?actionType=doShowDetail&begin='+begin+'&end='+end+'&id='+val)
						//	window.location.href = '/bpp/roomSystem.do?actionType=doShowDetail&begin='+begin+'&end='+end+'&id='+val;
							window.open( '/bpp/roomSystem.do?actionType=doShowDetail&begin='+begin+'&end='+end+'&id='+val,'_blank');
//					 return  "<a class='x-btn' target='main' href='roomSystem.do?actionType=doShowDetail&begin="+begin+"&end="+end+"&id="+val+"'>详细</a>";
					}
         }
	grid.addListener('rowdblclick', rowdblclickFn); 


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
										if(document.getElementById('paramUid').value!=''){
											uid = document.getElementById('paramUid').value;
										}
										var depart = departList.getValue();
									//	var state = stateList.getValue();
										var benginTime = Ext.get('beginTime').dom.value
										var endTime = Ext.get('endTime').dom.value
										//var prNo = Ext.get('ssid').dom.value
										alert(url)
										store.reload({
													params : {
														uid : uid,
//														state : state,
														depart : depart,
														begin : benginTime,
														end : endTime
														
													},
													callback : function(r,
															options, success) {
//														if (success) {
//															Ext.Msg.alert('操作',
//																	'成功！');
//														} else {
//															Ext.Msg.alert('操作',
//																	'失败！');
//														}
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
				height : '100%',
				width : 800,
				anchor : '80%',
				items : [tab, grid]

			})

	grid.render('centre')
});