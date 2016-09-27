Ext.onReady(function() {
	Ext.QuickTips.init();// 提示link，如果a标签有title的话
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());// 以cookie的方式记住用户对面板的部署
	Ext.BLANK_IMAGE_URL = 'images/default/blank.gif'
	var uid, uname;// 被选择采购员工号
	var win = false;
	// load data for buyer
	loadSubjecetData2();
	
	 function showUrl1(value) {
		  return  "<a class='x-btn' target='blank'  href='epor.do?actionType=doSelSubject&id="+value+"'>修改</a>";
	}
	
	var cm = new Ext.grid.ColumnModel([{
				id : 'number',
				header : '编号',
				dataIndex : 'number',
				width : 100
			},{
				id : 'descrption',
				header : '描述',
				dataIndex : 'descrption',
				width : 100
			},{
				id : 'remark',
				header : '备注',
				dataIndex : 'remark',
				width : 300
			},{	
				id : 'update',
				header : '修改',
				dataIndex : 'id',
				  renderer:showUrl1,
				width : 50
			}])

	var toolbar = new Ext.Toolbar({
		items : [{
				text:'excel导入',
				iconCls:'',
				handler: function(){
					Ext.Msg.alert('coding...');
				}
			},{
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				// create the window on the first click and
				// reuse on subsequent clicks

				if (!win) {
					win = new Ext.Window({
						applyTo : 'hello-win',
						layout : 'fit',
						width : 500,
						height : 300,
						closeAction : 'hide',
						plain : true,
						items : new Ext.Panel({
							applyTo : 'hello-tabs',
							layout : 'form',
							autoTabs : true,
							items : [{
										xtype : 'textfield',
										id : 'remark',
										x : 10,
										y : 10,
										fieldLabel : '编号',
										name : 'remark'
									},{
										xtype : 'textfield',
										x : 10,
										y : 35,
										fieldLabel : '描述',
										name : 'englishName',
										id : 'englishName'								
									}, {
										xtype : 'textfield',
										width :200,
										x : 10,
										y : 35,
										fieldLabel : '注意事项',
										name : 'name',
										id : 'name'
									}],
							border : false
						}),

						buttons : [{
							text : '添加',
							iconCls : 'icon-add',
							handler : function() {
								Ext.Ajax.request({					
											url : 'epor.do?actionType=doAddSubject',
											params : {
												'number' : Ext.get('englishName').dom.value,
												'descrption' : Ext.get('name').dom.value,
												'remark' : Ext.get('remark').dom.value
											},
											success : function() {
												Ext.Msg.alert('成功!');
												loadSubjecetData();										
											},
											failure : function() {
												Ext.Msg.alert('失败!')
											}
										})
							}
								// disabled : true
						}, {
							text : '关闭',
							handler : function() {
								win.hide();
							}
						}]
					});
				}
				win.show();
			}

		}, {
			text : '删除',
			iconCls : 'icon-del',
			handler : function() {
				if (uname != null && uname != '') {
					var flag = window.confirm('确认删除' + uname + '?');
					if (flag) {
						Ext.Ajax.request({
									url : 'epor.do?actionType=doDelSubject',
									params : {
										'id' : uid
									},
									success : function() {
										Ext.Msg.alert('成功!');
										loadSubjecetData();
									},
									failure : function() {
										Ext.Msg.alert('失败!')
									}
								})
					} 
				}else {
						Ext.Msg.alert('请选择采购员!');
					}
			}
		}]

	})
	var grid = new Ext.grid.GridPanel({
				id : 'grid',
				title : '总账科目',
				cm : cm,
				tbar : toolbar,
				sm : new Ext.grid.RowSelectionModel({
							singleSelect : true
						}),
				store : subjecetStore,
				renderTo : Ext.getBody(),
				width : 550,
				autoHeight : true
			})

	grid.getSelectionModel().on('rowselect', function(sm, rowIdx, r) {
				var record = this.grid.store.getAt(rowIdx);
				uid = record.get('id');
				uname = record.get('number')
			});
});
