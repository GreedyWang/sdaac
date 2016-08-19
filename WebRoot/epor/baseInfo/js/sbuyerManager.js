Ext.onReady(function() {
	Ext.QuickTips.init();// 提示link，如果a标签有title的话
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());// 以cookie的方式记住用户对面板的部署
	Ext.BLANK_IMAGE_URL = 'images/default/blank.gif'
	var uid, uname;// 被选择采购员工号
	var win = false;
	// load data for buyer
	
	function loadBuyerData() {
		prFormListManager.doSelectBuyers(function(data) {
					girdStore.loadData(data);
				});
	}

	var girdStore = new Ext.data.JsonStore({
				// url : '',
				fields : ['buyId', 'buyName']
			})

	loadBuyerData();

	var cm = new Ext.grid.ColumnModel([{
				id : 'name',
				header : '姓名',
				dataIndex : 'buyId',
				width : 100
			},{
				id : 'name',
				header : '描述',
				dataIndex : 'buyName',
				width : 500
			}])

	var grid = new Ext.grid.GridPanel({
				id : 'grid',
				title : '采购员列表',
				cm : cm,
				store : girdStore,
				renderTo : Ext.getBody(),
				width : 600,
				autoHeight : 400
			})


});
