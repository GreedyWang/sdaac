Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side'
	Ext.BLANK_IMAGE_URL = 'images/default/blank.gif'

	var fm = Ext.form

	loadProjectData();
	loadCostcentreData();
	loadBuyerData();

	// var buyList = new Ext.form.ComboBox({
	// fieldLabel : '采购员',
	// hiddenName : 'approvedPR.prForm.tempolyeeByBuyerId.uid',
	// name : 'approvedPR.prForm.tempolyeeByBuyerId.uid',
	// store : buyerList,
	// valueField : 'buyId',
	// displayField : 'buyName',
	// typeAhead : true,
	// mode : 'local',
	// triggerAction : 'all',
	// emptyText : '选择一名采购员',
	// width : 150,
	// blankText : 'empty',
	// allowBlank : false
	// })

	function formatDate(value) {
		return value ? value.dateFormat('Y-m-d') : '';
	};

	// grid下拉列表
	var flagStroe = new Ext.data.SimpleStore({
				fields : ['id', 'flag'],
				data : [[0, '美元'], [1, '人民币']]
			});

	var cm = new Ext.grid.ColumnModel([{
				header : "名称(长度<21)",
				dataIndex : 'description',
				width : 160,
				editor : new fm.TextField({
							allowBlank : false,
							maxLength : 20
						})

			}, {
				header : "备注/说明",
				dataIndex : 'remark',
				width : 200,
				editor : new fm.TextField({
							allowBlank : false
						})

			}, {
				header : '数量',
				dataIndex : 'quantity',
				width : 50,
				editor : new fm.NumberField({
							allowBlank : false
						})
			}, {
				header : '单位',
				dataIndex : 'unit',
				width : 50,
				editor : new fm.TextField({
							allowBlank : false
						})
			}, {
				header : '要求交货日期',
				dataIndex : 'expectDeliveryDate',
				width : 90,
				format : 'Y-m-d',
				renderer : formatDate,
				editor : new fm.DateField({
							format : 'Y-m-d'
						})
			}, {
				header : '预计单价(2位小数、RMB)',
				dataIndex : 'unitPriceE',
				width : 160,
				editor : new fm.NumberField({
							decimalPrecision : 2
						})
			}, {
				header : '采购员',
				dataIndex : 'buyId',
				width : 60,
				editor : new Ext.form.ComboBox({
							fieldLabel : '采购员',
							name : 'buyerName',
							store : buyerList,
							valueField : 'buyId',
							displayField : 'buyId',
							typeAhead : true,
							mode : 'local',
							triggerAction : 'all',
							emptyText : '选择一名采购员',
							selectOnFocus : true,
							width : 150,
							blankText : 'empty',
							allowBlank : false
						})

			}]);

	var comSupply = new Ext.form.Field({
				fieldLabel : '供应商',
				name : 'prForm.recommendedSupplier'

			});

	var mustSupply = new Ext.form.Field({
				fieldLabel : '指定供应商',
				name : ''

			});

	var AR_NO = new Ext.form.Field({
				fieldLabel : 'AR NO(如果PR为资本化，此项必需填写)',
				name : 'prForm.arno'

			});

	var bigCountNo = new Ext.form.Field({
				fieldLabel : '大额费用审批号',
				name : 'prForm.bigCountNo'

			});

	var approvedCk = new Ext.form.Radio({
				boxLabel : '计划内',
				name : 'prForm.isPlan',
				inputValue : 0

			})
	var approvedCk2 = new Ext.form.Radio({
				boxLabel : '计划外',
				name : 'prForm.isPlan',
				inputValue : 1

			})
	var approvedGroup = new Ext.form.RadioGroup({
				items : [approvedCk, approvedCk2]
			})

	var gridData = [{
				description : '',
				remark : '',
				quantity : '',
				unit : '',
				expectDeliveryDate : '',
				unitPrice : '',
				unitPriceE : '',
				buyId : '',
				amount : '',
				currency : '',
				glAccount : '',
				orderNo : ''
			}];

	var cc = new Ext.data.Record.create([{
				name : 'description',
				type : "string"
			}, 'quantity', 'unit', {
				name : 'expectDeliveryDate',
				type : 'date',
				dateFormat : 'Y-m-d'
			}, 'unitPrice', 'unitPriceE', 'amount', 'currency', 'glAccount',
			'orderNo', 'buyId', 'remark'])

	var gridstore = new Ext.data.JsonStore({
				data : gridData,
				fields : [{
							name : 'description',
							type : "string"
						}, 'quantity', 'unit', {
							name : 'expectDeliveryDate',
							type : 'date',
							dateFormat : 'Y-m-d'
						}, 'unitPrice', 'unitPriceE', 'amount', 'currency',
						'glAccount', 'orderNo', 'buyId', 'remark']

			})
	var sm = new Ext.grid.CheckboxSelectionModel();
	var record;
	var context = new Ext.grid.EditorGridPanel({

				id : 'goods',
				x : 10,
				y : 400,
				autoHeight : true,
				sm : new Ext.grid.RowSelectionModel({
							singleSelect : true
						}),
				xtype : 'editorgrid',
				tbar : [{
							text : '添加行',
							iconCls : 'icon-add',
							handler : function() {
								// var p = [];

								// Ext.get('goods').stopEditing();
								// var cc = new Ext.data.Record([{
								// description : '1',
								// quantity : '2',
								// unit : '3',
								// expectDeliveryDate : '',
								// unitPrice : '',
								// unitPriceE : '',
								// buyId : '',
								// amount : '',
								// currency : '',
								// glAccount : '',
								// orderNo : ''
								// }])
								gridstore.insert(gridstore.getCount(),
										new cc({
													description : '',
													remark : '',
													quantity : '',
													unit : '',
													expectDeliveryDate : '',
													unitPrice : '',
													unitPriceE : '',
													buyId : '',
													amount : '',
													currency : '',
													glAccount : '',
													orderNo : ''
												}));
								// gridstore.getAt(gridstore.getCount())
								// Ext.get('goods').startEditing(0,0);
							}
						}, {
							text : '删除行',
							iconCls : 'icon-del',
							handler : function() {
								if (record == null) {
									Ext.Msg.alert('请选择行');
								} else {
									gridstore.remove(record)
								}
							}
						}],
				cm : cm,
				width : 1050,
				store : gridstore,
				clicksToEdit : 1

			})

	context.getSelectionModel().on('rowselect', function(sm, rowIdx, r) {
				record = this.grid.store.getAt(rowIdx);
			});

	var costList = new Ext.form.ComboBox({
				fieldLabel : '成本中心',
				hiddenName : 'prForm.prCostCenter.id',
				name : 'prForm.prCostCenter.id',
				store : costcentreStore,
				valueField : 'id',
				displayField : 'costCenterNameEnglish',
				typeAhead : true,
				mode : 'local',
				triggerAction : 'all',
				emptyText : 'Select a state...',
				selectOnFocus : true,
				width : 190,
				blankText : 'empty',
				allowBlank : false
			})

	var projectList = new Ext.form.ComboBox({
				fieldLabel : '项目编号',
				hiddenName : 'prForm.prProject.id',
				name : 'prForm.prProject.id',
				store : projectStore,
				valueField : 'id',
				displayField : 'context',
				typeAhead : true,
				mode : 'local',
				triggerAction : 'all',
				emptyText : 'Select a option...',
				selectOnFocus : true,
				width : 190,
				blankText : 'empty'
			})

	var label;

	var buyerButton = new Ext.Button({
				text : '选择采购员',
				handler : function() {
					label = 'aaaaa';
				}
			})

	var attach = new Ext.form.FileUploadField({
				xtype : 'fileuploadfield',
				id : 'form-file',
				width : 300,
				emptyText : 'Select an file',
				fieldLabel : '附件(多个文件请打包)',
				name : 'formFile',
				allowBlank : false,
				buttonCfg : {
					text : '上传',
					iconCls : 'upload-icon'
				}
			});

	var pr = new Ext.FormPanel({
				monitorValid : true,
				fileUpload : true,
				layout : 'form',
				title : '上海德尔福汽车空调系统有限公司_请购单',
				autoHeight : true,
				renderTo : document.body,
				items : [
						costList, // 成本中心列表
						AR_NO,// ARNO
						projectList,// 项目标号
						bigCountNo, {
							id : 'telephone',
							x : 10,
							y : 10,
							width : 100,
							fieldLabel : '电话分机',
							emptyText : '电话分机:',
							xtype : 'textfield',
							name : 'prForm.telephone',
							allowBlank : false
						}, {
							id : 'comSupply',
							columnWidth : .15,
							xtype : 'textfield',
							fieldLabel : '推荐供应商',
							emptyText : '',
							name : 'prForm.recommendedSupplier'
							// allowBlank : false

					}	, {
							xtype : 'checkbox',
							columnWidth : .15,
							fieldLabel : '供应商是否为指定',
							boxLabel : '是',
							name : 'isAssignation',
							inputValue : 1
						},

						{
							xtype : 'fileuploadfield',
							id : 'form-file',
							width : 300,
							emptyText : 'Select an file',
							fieldLabel : '附件(多个文件请打包,并用标准格式)',
							name : 'formFile',
							allowBlank : false,
							buttonCfg : {
								text : '上传',
								iconCls : 'upload-icon'
							}
						}, {
							xtype : 'button',
							text : '标准格式查询',
							handler : function() {
								alert('ZIP:压缩包,'

								+ 'PDF:' + '一般文档,'

								+ 'TIF:' + ' 二维设计图,'

								+ 'JPG:' + '一般图片,'

								+ 'DOC:' + 'MS Word 2003,'

								+ 'XLS:' + ' MS Excel 2003,'

								+ 'PPT:' + 'MS Powerpoint 2003,'

								+ 'DWG:' + 'AutoCAD 2007,'

								+ 'PART:' + 'UG NX 2， 3， 4， 5,'

								+ 'TXT:一般文本文件');

							}

						}, {
							id : 'type',
							x : 10,
							y : 90,
							xtype : 'checkboxgroup',
							fieldLabel : '类型(IT类请钩选IT)',
							// allowBlank : false,
							columns : 3,
							items : [{
										boxLabel : '固定资产',
										name : 'a',
										inputValue : 1
									}, {
										boxLabel : '日常需求',
										name : 'a',
										inputValue : 2
									}, {
										boxLabel : '工具',
										name : 'a',
										inputValue : 3
									}, {
										boxLabel : '能    源',
										name : 'a',
										inputValue : 4
									}, {
										boxLabel : '租赁',
										name : 'a',
										inputValue : 5
									}, {
										boxLabel : '外包服务',
										name : 'a',
										inputValue : 6
									}, {
										boxLabel : '间接材料',
										name : 'a',
										inputValue : 7
									}, {
										boxLabel : '机器设备',
										name : 'a',
										inputValue : 8
									}, {
										boxLabel : '维修件',
										name : 'a',
										inputValue : 9
									}, {
										boxLabel : 'IT',
										name : 'a',
										inputValue : 10
									}]
						}, context

				],
				buttons : [{
					text : '提交',
					handler : function() {
						// pr.getForm().isValid()

						if (window.confirm('确认提交?')) {
							
							if (Ext.get('form-file').dom.value!='' && Ext.get('form-file').dom.value.split('.')[Ext
									.get('form-file').dom.value.split('.').length
									- 1] != 'zip') {
								alert('请压缩成.zip文件!');
								return;
							}
							
							var m = context.store.getRange(0, context.store
											.getCount());
							if (m.length == 0) {
								alert("没有数据");
								return false;
							}
							var sSend = "["
							// fields:['description','quantity','unit','expectDeliveryDate','unitPrice','amount','currency','glAccount','orderNo']
							var objArray = new Array();
							for (var i = 0; i < m.length; i++) {
								if (m[i].data['expectDeliveryDate'] == ''
										|| m[i].data['description'] == ''
										|| m[i].data['unitPriceE'] == ''
										|| m[i].data['quantity'] == ''
										|| m[i].data['unit'] == ''
										|| m[i].data['buyId'] == ''
										|| m[i].data['buyId'] == null) {
									alert("采购内容不能为空");
									return false;
								}
								sSend = sSend
										+ "{description:'"
										+ m[i].data['description']
										+ "', remark:'"
										+ m[i].data['remark']
										+ "', quantity:'"
										+ m[i].data['quantity']
										+ "', unit:'"
										+ m[i].data['unit']
										+ "', expectDeliveryDate:'"
										+ Ext.util.Format
												.date(
														m[i].data['expectDeliveryDate'],
														'Y-m-d')
										+ "', unitPrice:'"
										+ m[i].data['unitPrice']
										+ "', amount:'" + m[i].data['amount']
										+ "', currency:'"
										+ m[i].data['currency']
										+ "', glAccount:'"
										+ m[i].data['glAccount']
										+ "', orderNo:'" + m[i].data['orderNo']
										+ "', unitPriceE:'"
										+ m[i].data['unitPriceE']
										+ "', buyerId:'" + m[i].data['buyId']
										+ "'}";

								if (i + 1 < m.length) {
									sSend += ',';
								}

							}

							sSend = sSend + "]";

							pr.form.submit({
										waitTitle : '提示',
										url : 'epor.do?actionType=doInsert&isInsert=y',
										method : 'post',
										// waitMsg:'正在登录验证,请稍候...',
										timeout : 10000,
										// progress:true,
										params : {
											data : sSend
										},
										success : function(result, request) {// 如果post成功执行这里
											Ext.Msg.alert('提交成功!');
										},
										failure : function(form, action) {
											alert('failure');
										}
									})

						}

					}

				}, {
					text : '保存',
					handler : function() {
						// pr.getForm().isValid()
							
						if (true) {
							
							var sSend = "["
							alert(sSend)
							if (Ext.get('form-file').dom.value!='' && Ext.get('form-file').dom.value.split('.')[Ext
									.get('form-file').dom.value.split('.').length
									- 1] != 'zip') {
								alert('请压缩成.zip文件!');
								return;
							}

							var m = context.store.getRange(0, context.store
											.getCount());
							if (m.length == 0) {

								return false;
							}

							
							// fields:['description','quantity','unit','expectDeliveryDate','unitPrice','amount','currency','glAccount','orderNo']
							var objArray = new Array();

							for (var i = 0; i < m.length; i++) {
								if (m[i].data['expectDeliveryDate'] == ''
										|| m[i].data['description'] == ''
										|| m[i].data['unitPriceE'] == ''
										|| m[i].data['quantity'] == ''
										|| m[i].data['unit'] == ''
										|| m[i].data['buyId'] == ''
										|| m[i].data['buyId'] == null) {
									alert("采购内容不能为空");
									return false;
								}
								sSend = sSend
										+ "{description:'"
										+ m[i].data['description']
										+ "', remark:'"
										+ m[i].data['remark']
										+ "', quantity:'"
										+ m[i].data['quantity']
										+ "', unit:'"
										+ m[i].data['unit']
										+ "', expectDeliveryDate:'"
										+ Ext.util.Format
												.date(
														m[i].data['expectDeliveryDate'],
														'Y-m-d')
										+ "', unitPrice:'"
										+ m[i].data['unitPrice']
										+ "', amount:'" + m[i].data['amount']
										+ "', currency:'"
										+ m[i].data['currency']
										+ "', glAccount:'"
										+ m[i].data['glAccount']
										+ "', orderNo:'" + m[i].data['orderNo']
										+ "', unitPriceE:'"
										+ m[i].data['unitPriceE']
										+ "', buyerId:'" + m[i].data['buyId']
										+ "'}";

								if (i + 1 < m.length) {
									sSend += ',';
								}

							}

							sSend = sSend + "]";
						
							pr.form.submit({
										waitTitle : '提示',
										url : 'epor.do?actionType=doInsert',
										method : 'post',
										// waitMsg:'正在登录验证,请稍候...',
										timeout : 10000,
										// progress:true,
										params : {
											data : sSend
										},
										success : function(result, request) {// 如果post成功执行这里
											Ext.Msg.alert('保存成功!');
										},
										failure : function(form, action) {
											alert('failure');
										}
									})
						}
					}
				}]
			});

});
