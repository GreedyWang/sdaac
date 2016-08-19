Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side'
	Ext.BLANK_IMAGE_URL = 'images/default/blank.gif'
	var fm = Ext.form

	loadProjectData();
	loadCostcentreData();
	loadBuyerData();

//	var buyList = new Ext.form.ComboBox({
//				fieldLabel : '采购员',
//				hiddenName : 'approvedPR.prForm.tempolyeeByBuyerId.uid',
//				name : 'approvedPR.prForm.tempolyeeByBuyerId.uid',
//				store : buyerList,
//				valueField : 'buyId',
//				displayField : 'buyName',
//				typeAhead : true,
//				mode : 'local',
//				triggerAction : 'all',
//				emptyText : '选择一名采购员',
//				selectOnFocus : true,
//				width : 150,
//				blankText : 'empty',
//				allowBlank : false
//			})

	function formatDate(value) {
		return value ? value.dateFormat('Y-m-d') : '';
	};

	// grid下拉列表
	var flagStroe = new Ext.data.SimpleStore({
				fields : ['id', 'flag'],
				data : [[0, '美元'], [1, '人民币']]
			});



	var cm = new Ext.grid.ColumnModel([{
				header : "名称,标准/样本参考资料",
				dataIndex : 'description',
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
				width : 130,
				renderer : formatDate,
				editor : new fm.DateField({
							format : 'Y-m-d'
						})
			}, {
				header : '预计单价',
				dataIndex : 'unitPriceE',
				width : 100,
				editor : new fm.NumberField({
							allowBlank : false
						})
			}, {
				header : '采购员',
				dataIndex : 'buyId',
				width : 100,
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

			}, {
				header : '单价(不含税)',
				dataIndex : 'unitPrice',
				width : 100
			}, {
				header : '总价',
				dataIndex : 'amount',
				width : 50,
				renderer : function(v, params, record) {
					var result = record.data.unitPrice * record.data.quantity;
					value = result;
					record.data.amount = value;
					return result;
				}
			}, {
				header : '币种',
				dataIndex : 'currency',
				width : 50
			}, {
				header : '总帐科目',
				dataIndex : 'glAccount',
				width : 100
			}, {
				header : '定单NO.',
				dataIndex : 'orderNo',
				width : 100
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
				fieldLabel : 'AR NO',
				name : 'prForm.receivingPlacePerpoleTel'

			});

	var bigCountNo = new Ext.form.Field({
				fieldLabel : '大额金额号',
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
			}, {
				description : '',
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
			}, {
				description : '',
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
			}, {
				description : '',
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
			}, {
				description : '',
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
			}, {
				description : '',
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
			}, {
				description : '',
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
	
			var gridstore = new Ext.data.JsonStore({
				data : gridData,
				fields : [{
							name : 'description',
							type : "string"
						}, 'quantity', 'unit', {
							name : 'expectDeliveryDate',
							type : 'date',
							dateFormat : 'Y-m-d'
						}, 'unitPrice',  'unitPriceE', 'amount', 'currency', 'glAccount',
						'orderNo','buyId']
			})
			
	var context = new Ext.grid.EditorGridPanel({
			
							id : 'goods',
							x : 10,
							y : 400,
							height : 300,
							xtype : 'editorgrid',
//							tbar : [{
//										text : 'Add ROW',
//										handler : function() {
//											var p = {
//												description : '',
//												quantity : '',
//												unit : '',
//												expectDeliveryDate : '',
//												unitPrice : '',
//												amount : '',
//												currency : '',
//												glAccount : '',
//												orderNo : ''
//											};
//
//											// Ext.get('goods').stopEditing();
//											gridstore.add(p);
//											// Ext.get('goods').startEditing(0,
//											// 0);
//										}
//									}],
							cm : cm,
							width : 1050,
							store : gridstore,
							clicksToEdit : 1
						
			})
			
	

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
				id : 'attach',
				x : 10,
				y : 50,
				width : 300,
				emptyText : 'Select an image',
				fieldLabel : 'Photo',
				name : 'formFile',
				buttonCfg : {
					text : '上传'
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
							layout : 'column',
							border : 0,
							items : [
									// {
									// title: '推荐供应商',
									// height:0,
									// columnWidth: .15
									// },
									{
								id : 'comSupply',
								columnWidth : .15,
								xtype : 'textfield',
								fieldLabel : '推荐供应商',
								emptyText : '推荐供应商:',
								name : 'prForm.recommendedSupplier',
								allowBlank : false

							}, {
								xtype : 'checkbox',
								columnWidth : .15,
								boxLabel : '是否为指定',
								name : 'isAssignation',
								inputValue : 1
							}]

						},

						{
							xtype : 'fileuploadfield',
							id : 'form-file',
							width : 300,
							emptyText : 'Select an file',
							fieldLabel : '附件',
							name : 'formFile',
							buttonCfg : {
								text : '上传',
								iconCls : 'upload-icon'
							}
						}, {
							id : 'type',
							x : 10,
							y : 90,
							xtype : 'checkboxgroup',
							fieldLabel : '类型(IT设备请勾上IT)',
						//	allowBlank : false,
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
						if (true) {
							var m = context.store.getModifiedRecords();
							if (m.length == 0) {
								alert("没有数据");
								return false;
							}
							var sSend = "["
							// fields:['description','quantity','unit','expectDeliveryDate','unitPrice','amount','currency','glAccount','orderNo']
							var objArray = new Array();
							for (var i = 0; i < m.length; i++) {
								if (m[i].data['description']==''|| m[i].data['quantity']==''||m[i].data['unit']==''|| m[i].data['buyId']=='') {
								alert("采购内容不能为空");
								return false;
							}
								sSend = sSend
										+ "{description:'"
										+ m[i].data['description']
										+ "', quantity:'"
										+ m[i].data['quantity']
										+ "', unit:'"
										+ m[i].data['unit']
										+ "', expectDeliveryDate:'"
										+ Ext.util.Format
												.date(m[i].data['expectDeliveryDate'])
										+ "', unitPrice:'"
										+ m[i].data['unitPrice']
										+ "', amount:'" + m[i].data['amount']
										+ "', currency:'"
										+ m[i].data['currency']
										+ "', glAccount:'"
										+ m[i].data['glAccount']
										+ "', orderNo:'" + m[i].data['orderNo']
										+"', unitPriceE:'" + m[i].data['unitPriceE']
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
											Ext.Msg.alert('提交成功!');
										},
										failure : function(form, action) {
											alert('failure');
										}
									})

						}

					}

				}, {
					text : 'reset',
					handler : function() {
						// pr.getForm().reset();
						var len = pr.items.length
						// alert(len)
						pr.items[len] = filed1
						pr.render()
					}
				}, {
					text : '保存',
					handler : function() {
						// pr.getForm().isValid()
						if (true) {
							var m = context.store.getModifiedRecords();
							if (m.length == 0) {
								alert("没有数据");
								return false;
							}
							var sSend = "["
							// fields:['description','quantity','unit','expectDeliveryDate','unitPrice','amount','currency','glAccount','orderNo']
							var objArray = new Array();
							for (var i = 0; i < m.length; i++) {

							sSend = sSend
										+ "{description:'"
										+ m[i].data['description']
										+ "', quantity:'"
										+ m[i].data['quantity']
										+ "', unit:'"
										+ m[i].data['unit']
										+ "', expectDeliveryDate:'"
										+ Ext.util.Format
												.date(m[i].data['expectDeliveryDate'])
										+ "', unitPrice:'"
										+ m[i].data['unitPrice']
										+ "', amount:'" + m[i].data['amount']
										+ "', currency:'"
										+ m[i].data['currency']
										+ "', glAccount:'"
										+ m[i].data['glAccount']
										+ "', orderNo:'" + m[i].data['orderNo']
										+"', unitPriceE:'" + m[i].data['unitPriceE']
										+ "', buyId:'" + m[i].data['buyId']
										+ "'}";

								if (i + 1 < m.length) {
									sSend += ',';
								}

							}

							sSend = sSend + "]";
							pr.form.submit({
										waitTitle : '提示',
										url : 'epor.do?actionType=doSave',
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
