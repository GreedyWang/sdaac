// use to updatePrForm.jsp
var delIds = new Array();
function sub(flag,prId) {
	var m = context.store.getRange(0,context.store.getCount());

	var objArray = new Array();
	var objArray2 = new Array();
	var bb = true;
	var buyer='';
	var total = 0;
	//document.getElementById('costCenter').value==''
	if(!document.getElementById('k').checked && document.getElementById('costCenter').value==''){
		alert("成本中心不能为空");
		return false;
	}
	if(document.getElementById('telephone').value==''){
						alert("电话分机内容不能为空");
						return false;
					}
	
					
					
	for (var i = 0; i < m.length; i++) {
		buyer+=m[i].data['buyerId']+','
		if ( m[i].data['expectDeliveryDate']==''||m[i].data['description']==''|| m[i].data['quantity']==''||m[i].data['unit']==''
				|| m[i].data['buyerId']==''|| m[i].data['buyerId']==null) {
				bb = false;
					alert("采购内容不能为空");
						return false;
			}
	}
	for (var i = 0; i < m.length; i++) {
			var	sSend = {
						'description' : m[i].data['description'],
						'remark' :  m[i].data['remark'],
						'quantity': m[i].data['quantity'] ,
						'unit': m[i].data['unit'],
						'expectDeliveryDate':m[i].data['expectDeliveryDate'],
						'unitPrice':m[i].data['unitPrice'],
						'remark':m[i].data['remark'],
						'amount':m[i].data['amount'],
						'currency': m[i].data['currency'],
						'glAccount':m[i].data['glAccount'],
						'orderNo':m[i].data['orderNo'],
						'unitPriceE':m[i].data['unitPriceE'],
						'buyerId':m[i].data['buyerId'],
						'prPrForm':{id:prId},
						'amount':0,
						'currency':'',
						'glAccount':'',
						'orderNo':''
						};
		objArray[i] = sSend;	
		delIds[delIds.length+i]=m[i].data['id'];
		total += m[i].data['quantity'] * m[i].data['unitPrice'];
			
	
	}
	if(document.getElementById('total').value < total -1 ){
		alert('金额不能增加!')
		return false;
	}else {
		document.getElementById('total').value = total
	}
	
	if(bb){
			formService.dodeleteBuyContext(delIds, function(data) {
			});
	}
	if(bb){
		
	prFormListManager.doAddBuyContext(objArray,document.getElementById('uidd').value,function(data) {
		
				var form = document.forms[0];
					document.getElementById('buyer').value=buyer;
				if (flag == 1) {
					document.getElementById('info').value=4;
				} else if(flag == 3){
					document.getElementById('info').value=5;
				} else if (flag == 2) {
					document.getElementById('state').value=1;
					document.getElementById('info').value=0;
				}else if(flag == 6){
					document.getElementById('info').value=1;
				} 
				form.submit();
			})
	}
}

/**
 * 增加行
 */
function Tadd() {
	var table1 = document.getElementById('table1');
	var newTr = table1.insertRow();
	var newTd7 = newTr.insertCell();
	var newTd0 = newTr.insertCell();
	var newTd1 = newTr.insertCell();
	var newTd2 = newTr.insertCell();
	var newTd3 = newTr.insertCell();

	// var index = document.getElementById('table1').rows.length;
	// index = index + 1;
	newTd7.innerHTML = '<img src="images/delete.gif" id="image"  onclick="del(this)"> ';
	newTd0.innerHTML = '<input class="required" type="text"  name="a" />';
	newTd1.innerHTML = '<input class="required" type="text"  name="b" />';
	newTd2.innerHTML = '<input class="required" type="text" name="c" />';
	newTd3.innerHTML = '<input class="required" type="text" name="d"  />';

}

/**
 * 刪除行
 * 
 * @param {}
 *            o
 */
function del(o) {
	var t = document.getElementById('table1')
	t.deleteRow(o.parentNode.parentNode.rowIndex)
}

/**
 * 载入成本中心数据
 */
function loadCostcentreData(area,ccRemark) {
	prFormListManager.doSelectAllCostcentre2(area,function(data) {
				var roles = document.getElementById('costCenter')
				roles.options.length = 0;
				if (data != null) {
					for (var i = 0; i < data.length; i++) {
						var opt = document.createElement('Option');
						opt.text = data[i].costCenterName
						opt.value = data[i].id
						if (ccRemark == data[i].id) {
							opt.selected = 'selected'
						}
						roles.options.add(opt);
					}
				}
			});
}

/**
 * 载入PRproject数据
 */
function loadProjectData(projectId) {
	prFormListManager.doSelectAllProject(function(data) {
				var roles = document.getElementById('projectId')
				roles.options.length = 0;
				if (data != null) {
					for (var i = 0; i < data.length; i++) {
						var opt = document.createElement('Option');
						opt.text = data[i].context
						opt.value = data[i].id
						if (projectId == data[i].id) {
							 //alert(projectId+':'+data[i].id)
							opt.selected = 'selected'
						}
						roles.options.add(opt);
					}
				}
			});
}
var context
/**
 * 创建采购表格
 * 
 * @param {}
 *            prId
 */
function newGrid(area,prId) {
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side'
	Ext.BLANK_IMAGE_URL = 'images/default/blank.gif'
	var fm = Ext.form
	loadBuyerData2(area);
	function formatDate(value) {
		return value ? value.dateFormat('Y-m-d') : '';
	};
	
		var cc = new Ext.data.Record.create([{
							name : 'description',
							type : "string"
						}, 'quantity', 'unit', {
							name : 'expectDeliveryDate',
							type : 'date',
							dateFormat : 'Y-m-d'
						}, 'unitPrice',  'unitPriceE', 'amount', 'currency', 'glAccount',
						'orderNo','buyId','remark'
			])
	
		var aa =	new fm.NumberField({
//			listeners:{'change':function(obj,newValue,oldValue){
//				alert(newValue)
//					alert(oldValue)
//			}}
			
		})
			aa.on('change',function(){
							alert(newValue)
							alert(oldValue)
						})
var cm = new Ext.grid.ColumnModel([{
				header : "名称(长度<21)",
				dataIndex : 'description',
				width : 160,
				editor : new fm.TextField({
							allowBlank : false,
							maxLength :20
						})

			}, {
				header : "备注/说明",
				dataIndex : 'remark',
				width : 200,
				editor : new fm.TextField({
							allowBlank : false
						})

			},{
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
				renderer : formatDate,
				editor : new fm.DateField({
							format : 'Y-m-d'
						})
			}, {
				header : '预计单价(2位小数、RMB)',
				dataIndex : 'unitPriceE',
				width : 60,
				editor : new fm.NumberField({
							decimalPrecision:2
						})
			}, {
				header : '采购员',
				dataIndex : 'buyerId',
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

	var gridData, gridstore;

	formService.doSelectByPRId(prId, function(data) {
				// gridData=data
				gridstore.loadData(data)
			})

	gridstore = new Ext.data.JsonStore({
				// data : gridData,
				fields : [{
							name : 'description',
							type : "string"
						}, 'quantity', 'unit', {
							name : 'expectDeliveryDate',
							type : 'date',
							dateFormat : 'Y-m-d'
						}, 'unitPrice', 'unitPriceE', 'amount', 'currency',
						'glAccount', 'orderNo', 'buyerId','id','remark']
			})

	context = new Ext.grid.EditorGridPanel({

				id : 'goods',
				x : 10,
				y : 400,
				sm : new Ext.grid.RowSelectionModel({
							singleSelect : true
						}),
				autoHeight : true,
				xtype : 'editorgrid',
				applyTo : 'aa',
				tbar : [{
							text : '添加行',
							iconCls : 'icon-add',
							handler : function() {
								// var p = [];

								// Ext.get('goods').stopEditing();
							
								gridstore.insert(gridstore.getTotalCount(), new cc({
												description : '',
												remark :'',
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
								// Ext.get('goods').startEditing(0,0);
							}
						}, {
							text : '删除行',
							iconCls : 'icon-del',
							handler : function() {
								if (record == null) {
									Ext.Msg.alert('请选择行');
								} else {
									context.store.remove(record);
									delIds.push(index);
								}
							}
						}],
				cm : cm,
				width : 1050,
				store : gridstore,
				clicksToEdit : 1

			})
	var index
	context.getSelectionModel().on('rowselect', function(sm, rowIdx, r) {
				record = this.grid.store.getAt(rowIdx);
				index = record.get('id');
			});
};
