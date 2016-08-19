Ext.onReady(function() {
			Ext.QuickTips.init();// 提示link，如果a标签有title的话
			Ext.state.Manager.setProvider(new Ext.state.CookieProvider());// 以cookie的方式记住用户对面板的部署
			Ext.BLANK_IMAGE_URL = 'images/default/blank.gif';

			var cm = new Ext.grid.ColumnModel([{
						header : "功能名称",
						dataIndex : 'description',
						width : 200
					}, {
						header : '添加',
						dataIndex : '',
						checkBox : true,
						width : 100
					}]);

			var roleList = new Ext.form.ComboBox({
						id : 'productNoLabel',
						store : store
					})

			var btn3 = new Ext.Button({
				text : '新建',
				iconCls : 'icon-grid'
					// handler:toExcel
				});

			var lable1 = new Ext.form.Label({
						text : '选择角色',
						iconCls : 'icon-grid'
					});

			var toolbar = new Ext.Toolbar({
						items : [btn3, '-', lable1, roleList]
					})

			var store = new Ext.data.JsonStore({
						url : '',
						fields : []
					})

			var GridPlane = new Ext.grid.GridPanel({
						title : '角色管理',
						cm : cm,
						width : 600,
						tbar : toolbar,
						store : store,
						renderTo : 'gridContext',
						autoHeight : true
					})

		})