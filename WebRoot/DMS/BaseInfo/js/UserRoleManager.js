Ext.onReady(function() {
			Ext.QuickTips.init();// 提示link，如果a标签有title的话
			Ext.state.Manager.setProvider(new Ext.state.CookieProvider());// 以cookie的方式记住用户对面板的部署
			Ext.BLANK_IMAGE_URL = 'images/default/blank.gif';
			//define checkColum plugin
			Ext.grid.CheckColumn = function(config) {
				Ext.apply(this, config);
				if (!this.id) {
					this.id = Ext.id();
				}
				this.renderer = this.renderer.createDelegate(this);
			};

			Ext.grid.CheckColumn.prototype = {
				init : function(grid) {
					this.grid = grid;
					this.grid.on('render', function() {
								var view = this.grid.getView();
								view.mainBody.on('mousedown', this.onMouseDown,
										this);
							}, this);
				},

				onMouseDown : function(e, t) {
					if (t.className
							&& t.className.indexOf('x-grid3-cc-' + this.id) != -1) {
						e.stopEvent();
						var index = this.grid.getView().findRowIndex(t);
						var record = this.grid.store.getAt(index);
					
						record
								.set(this.dataIndex,
										!record.data[this.dataIndex]);
						//update rights
						Ext.Ajax.request({
						   url: 'rights.do?actionType=doChangeUserRights',
						   success: function(){Ext.Msg.alert('success')},
						   failure: function(){Ext.Msg.alert('failure')},
//						   headers: {
//						       'roleid': 'isOwn'
//						   },
						   params: { roleid: record.get('roleid'),isOwn:record.get('isOwn'),uid:Ext.get('uid').dom.value }
						});					
					}
				},

				renderer : function(v, p, record) {
					p.css += ' x-grid3-check-col-td';
					return '<div class="x-grid3-check-col' + (v ? '-on' : '')
							+ ' x-grid3-cc-' + this.id + '">&#160;</div>';
				}
			};

			var checkColumn = new Ext.grid.CheckColumn({
						header : '添加',
						dataIndex : 'isOwn',
						width : 55
//						handler: function(){
//							alert('!')
//						}
					});


			var cm = new Ext.grid.ColumnModel([{
						header : "角色名称",
						dataIndex : 'roleName',
						width : 200
					}, checkColumn]);

			var uid = new Ext.form.TextField({
						id : 'uid',
						store : store
					})
			
			//角色类别
			var typesdata = [{aa:'DMS',bb:'DMS'}]
			
			var typesStore = new Ext.data.JsonStore({
				data : typesdata,
				fields : ['aa', 'bb']
			})
			
			var types = new Ext.form.ComboBox({
				fieldLabel : '角色组',
				id : 'types',
				// hiddenName:'state',
				name : 'prForm.prCostCenter.costCenterName',
				store : typesStore,
				valueField : 'aa',
				displayField : 'bb',
				typeAhead : true,
				mode : 'local',
				triggerAction : 'all',
				emptyText : 'Select a type...',
				selectOnFocus : true,
				width : 100,
				blankText : 'empty',
				allowBlank : false
			})
			
			//--------------------		
			
			var btn4 = new Ext.Button({
				text : '查询',
				iconCls : 'icon-search',
					 handler:function(){
					 	var uidValue = Ext.get('uid').dom.value;
					 	store.reload({
					 		params:{uid:uidValue,typeName:'DMS'}
					 	})
					 	
					 }
				});

			var lable1 = new Ext.form.Label({
						text : '输入员工工号',
						iconCls : 'icon-grid'
					});

			var toolbar = new Ext.Toolbar({
						items : [types, '-', lable1, uid,btn4]
					})
						
			var store = new Ext.data.JsonStore({
						url : 'rights.do?actionType=doSelectUserAndRights',
						fields : ['roleid', 'roleName', {name: 'isOwn', type: 'bool'}],
						autoLoad : true
					})

			var GridPlane = new Ext.grid.EditorGridPanel({
						title : '角色管理',
						cm : cm,
						width : 600,
						tbar : toolbar,
						store : store,
						renderTo : 'gridContext',
						clicksToEdit:1,
						plugins:checkColumn,

						autoHeight : true
					})

		})