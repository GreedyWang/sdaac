Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side'
	Ext.BLANK_IMAGE_URL = 'images/default/blank.gif'
	var fm = Ext.form

	// loadProjectData();
	// loadCostcentreData2(area);
	// loadBuyerData2(area);

	function formatDate(value) {
		return value ? value.dateFormat('Y-m-d') : '';
	};

	var comSupply = new Ext.form.Field({
				fieldLabel : '制造商物料号',
				name : 'prForm.recommendedSupplier'

			});

	var mustSupply = new Ext.form.Field({
				fieldLabel : '制造商名字',
				name : ''

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

	var costList = new Ext.form.ComboBox({
				fieldLabel : '成本中心',
				id : 'costList',
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
				editable : false

			})

	// 库存地点
	var storage_address = new Ext.data.SimpleStore({
				fields : ['id', 'context'],
				data : [[1, 'IN01'], [2, 'IN02'], [3, 'IN03']]
			});

	var storageList = new Ext.form.ComboBox({
				fieldLabel : '库存地点',
				hiddenName : 'prForm.prProject.id',
				name : 'prForm.prProject.id',
				store : storage_address,
				valueField : 'id',
				displayField : 'context',
				typeAhead : true,
				mode : 'local',
				triggerAction : 'all',
				emptyText : 'Select a option...',
				selectOnFocus : true,
				editable : false,
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
				emptyText : 'Select a file',
				fieldLabel : '附件(多个文件请打包)',
				name : 'formFile',
				buttonCfg : {
					text : '上传',
					iconCls : 'upload-icon'
				}
			});

	var remark = new Ext.form.TextArea({
				id : 'remark',
				name : 'prForm.remark',
				width : 300,
				height : 160,
				fieldLabel : '物料描述（最多英文字符40，中文20）',
				allowBlank : false

			})

	var radiogroup = new Ext.form.RadioGroup({
				fieldLabel : '优先级',
				items : [{
							boxLabel : '24小时内响应',
							inputValue : "1",
							name : "prForm.source"
						}, {
							boxLabel : '48小时内响应',
							inputValue : "2",
							name : "prForm.source"
						}, {
							boxLabel : '响应时间超过48小时',
							inputValue : "3",
							name : "prForm.source"
						}]
			});
	
	var radiogroup2 = new Ext.form.RadioGroup({
				fieldLabel : '3. ',
				items : [{
							boxLabel : '设备维修项 (ERSA)',
							inputValue : "1",
							name : "prForm.source"
						}, {
							boxLabel : '运营支持(HIBE)',
							inputValue : "2",
							name : "prForm.source"
						}]
			});
	
	// new or update
	var nu = new Ext.form.RadioGroup({
				fieldLabel : '优先级',
				items : [{
							boxLabel : '新建',
							inputValue : "1",
							name : "prForm.source"
						}, {
							boxLabel : '修改',
							inputValue : "2",
							name : "prForm.source"
						}]
			});
	// old material SN
	var old_material_sn = new Ext.form.Field({
				fieldLabel : '如果是修改或修正请在右边填写旧物料号',
				name : 'prForm.arno'

			});

	function valueReplace(v) {
		v = v.toString().replace(new RegExp('(["\"])', 'g'), "\\\"");
		return v;
	}

	var pr = new Ext.FormPanel({
		monitorValid : true,
		fileUpload : true,
		layout : 'form',
		title : '上海德尔福汽车空调系统有限公司_请购单',
		autoHeight : true,
		renderTo : document.body,
		items : [radiogroup,
				costList, // 成本中心列表

				storageList,// 项目标号
				{
					id : 'telephone',
					x : 10,
					y : 10,
					width : 100,
					fieldLabel : '电话分机',
					emptyText : '电话分机:',
					xtype : 'textfield',
					name : 'prForm.telephone',
					allowBlank : false
				}, nu, old_material_sn, remark, {
					id : 'materialSN',
					columnWidth : .15,
					xtype : 'textfield',
					fieldLabel : '制造商物料号',
					emptyText : '',
					name : 'prForm.recommendedSupplier'
					// allowBlank : false

			}	, {
					id : 'name',
					columnWidth : .15,
					xtype : 'textfield',
					fieldLabel : '制造商名字',
					emptyText : '',
					name : 'prForm.recommendedSupplier'
					// allowBlank : false

			}	, {
					id : 'unit',
					columnWidth : .15,
					xtype : 'textfield',
					fieldLabel : '基本单位',
					emptyText : '',
					name : 'prForm.recommendedSupplier'
					// allowBlank : false

			}	, {
					id : 'label',
					columnWidth : .15,
					xtype : 'textfield',
					fieldLabel : '资产标签号（仅用于ERSA（备件））',
					emptyText : '',
					name : 'prForm.recommendedSupplier'
					// allowBlank : false

				},{
					id : 'If yes, FID #:',
					columnWidth : .15,
					xtype : 'textfield',
					fieldLabel : '单价',
					emptyText : '',
					name : 'prForm.recommendedSupplier'
					// allowBlank : false

				},{
					xtype : 'checkbox',
					columnWidth : .15,
					fieldLabel : '自制',
					boxLabel : '是',
					name : 'isAssignation',
					inputValue : 1
				}, {
					xtype : 'fileuploadfield',
					id : 'form-file',
					width : 300,
					emptyText : 'Select a file',
					fieldLabel : '附件(多个文件请打包,并用标准格式)',
					name : 'formFile',
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
					width : 680,
					layout : 'column',
					items : [{
								html : "危险物料",
								columnWidth : .2
							}, {
								title : 'aaaa',
								xtype : 'checkbox',
								fieldLabel : '危险物料',
								boxLabel : '是',
								name : 'isAssignation',
								columnWidth : .2,
								inputValue : 1
							}, {
								html : "资产标签号（仅用于ERSA（备件））",
								columnWidth : .4
							}, {
								id : 'If yes, FID #:',
								columnWidth : .2,
								xtype : 'textfield',
								fieldLabel : '资产标签号（仅用于ERSA（备件））',
								emptyText : '',
								name : 'prForm.recommendedSupplier'
								// allowBlank : false

						}]
				}, {
					id : 'bb',
					width : 680,
					layout : 'column',
					border :0,
					items : [{
								html : "最低库存数量",
								columnWidth : .16
							}, {
								xtype : 'textfield',
								boxLabel : '是',
								name : 'isAssignation',
								columnWidth : .16,
								emptyText : '0',
								inputValue : 1
							}, {
								html : "最高库存数量",
								columnWidth : .16
							}, {
								id : 'If yes, FID #:',
								columnWidth : .16,
								xtype : 'textfield',
								emptyText : '0',
								name : 'prForm.recommendedSupplier'
								// allowBlank : false
						}	, {
								html : "年度用量",
								columnWidth : .16
							}, {
								id : 'annual_ammounts:',
								columnWidth : .10,
								xtype : 'textfield',
								emptyText : '0',
								name : 'prForm.recommendedSupplier'
								// allowBlank : false
						}]
				},{
				
					id : 'cc',
					width : 680,
					layout : 'column',
					items : [{
								html : "长度",
								columnWidth : .16
							}, {
								xtype : 'textfield',
								boxLabel : '是',
								name : 'isAssignation',
								columnWidth : .16,
								emptyText : '0',
								inputValue : 1
							}, {
								html : "宽度",
								columnWidth : .16
							}, {
								id : 'If yes, FID #:',
								columnWidth : .16,
								xtype : 'textfield',
								emptyText : '0',
								name : 'prForm.recommendedSupplier'
								// allowBlank : false
						}	, {
								html : "高度",
								columnWidth : .16
							}, {
								id : 'annual_ammounts:',
								columnWidth : .10,
								xtype : 'textfield',
								emptyText : '0',
								name : 'prForm.recommendedSupplier'
								// allowBlank : false
						}]
				
				},{
					xtype : 'checkbox',
					width:400,
					fieldLabel : '1.是否做库存管理',
					boxLabel : '是',
					name : 'isAssignation',
					inputValue : 1
				},{
					xtype : 'checkbox',
					width:400,
					fieldLabel : '2. 收货就费用化',
					boxLabel : '是',
					name : 'isAssignation',
					inputValue : 1
				},radiogroup2
		],
		buttons : [{
			text : '提交',
			handler : function() {
				// pr.getForm().isValid()

				if (window.confirm('确认提交?')) {
					if (!Ext.get('ttt').dom.checked
							&& Ext.get('costList').dom.value == 'Select a state...') {
						alert('请选择成本中心!');
						return;
					}

					if (Ext.get('form-file').dom.value != 'Select a file'
							&& Ext.get('form-file').dom.value.split('.')[Ext
									.get('form-file').dom.value.split('.').length
									- 1] != 'zip') {
						alert('请压缩成.zip文件!');
						return;
					}

					var m = context.store.getRange(0, context.store.getCount());
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
								+ m[i].data['remark'].replace('\'', '/')
								+ "', quantity:'"
								+ m[i].data['quantity']
								+ "', unit:'"
								+ m[i].data['unit']
								+ "', expectDeliveryDate:'"
								+ Ext.util.Format.date(
										m[i].data['expectDeliveryDate'],
										'Y-m-d') + "', unitPrice:'"
								+ m[i].data['unitPrice'] + "', amount:'"
								+ m[i].data['amount'] + "', currency:'"
								+ m[i].data['currency'] + "', glAccount:'"
								+ m[i].data['glAccount'] + "', orderNo:'"
								+ m[i].data['orderNo'] + "', unitPriceE:'"
								+ m[i].data['unitPriceE'] + "', buyerId:'"
								+ m[i].data['buyId'] + "'}";

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
					if (!Ext.get('ttt').dom.checked
							&& Ext.get('costList').dom.value == 'Select a state...') {
						alert('请选择成本中心!');
						return;
					}
					if (Ext.get('form-file').dom.value != 'Select a file'
							&& Ext.get('form-file').dom.value.split('.')[Ext
									.get('form-file').dom.value.split('.').length
									- 1] != 'zip') {
						alert('请压缩成.zip文件!');
						return;
					}

					var m = context.store.getRange(0, context.store.getCount());
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
								+ Ext.util.Format.date(
										m[i].data['expectDeliveryDate'],
										'Y-m-d') + "', unitPrice:'"
								+ m[i].data['unitPrice'] + "', amount:'"
								+ m[i].data['amount'] + "', currency:'"
								+ m[i].data['currency'] + "', glAccount:'"
								+ m[i].data['glAccount'] + "', orderNo:'"
								+ m[i].data['orderNo'] + "', unitPriceE:'"
								+ m[i].data['unitPriceE'] + "', buyerId:'"
								+ m[i].data['buyId'] + "'}";

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
