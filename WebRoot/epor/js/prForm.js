Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side'
	Ext.BLANK_IMAGE_URL = 'images/default/blank.gif'
	var comboxSize = 110;
	var fm = Ext.form
	var area = document.getElementById('area').value;
	if (area == null || area == '' || area == 'DT05'){
		area = 'DT02';
	}
	var departId = document.getElementById('departId').value;
	loadAllDprt(area);

	loadProjectData();

	//loadCostcentreData2(area);

	loadBuyerData2(area);

	area = document.getElementById('area').value;
	if (area == null || area == ''){
		document.getElementById('area').value = 'DT02';
		alert('用户信息不全,请维护')
	}
	//alert(area);
	// ---------------------------------var ardetial = initARdetail(1);
	function formatDate(value) {
		return value ? value.dateFormat('Y-m-d') : '';
	};

	// grid下拉列表
	var flagStroe = new Ext.data.SimpleStore({
				fields : ['id', 'flag'],
				data : [[0, '美元'], [1, '人民币']]
			});

	var cm = new Ext.grid.ColumnModel([{
				header : "Description名称(length<21)",
				dataIndex : 'description',
				width : 185,
				editor : new fm.TextField({
							allowBlank : false,
							maxLength : 20
						})

			}, {
				header : "Brand品牌(length<50)",
				dataIndex : 'band',
				width : 200,
				editor : new fm.TextField({
							allowBlank : true,
							maxLength : 49
						})
			}, {
				header : "Type型号",
				dataIndex : 'type',
				width : 100,
				editor : new fm.TextField({
							allowBlank : true
						})
			}, {
				header : 'Qty.数量',
				dataIndex : 'quantity',
				width : 60,
				editor : new fm.NumberField({
							allowBlank : false
						})
			}, {
				header : 'UOM单位',
				dataIndex : 'unit',
				width : 60,
				editor : new fm.TextField({
							allowBlank : false
						})
			}, {
				header : 'Req. Deliever date',
				dataIndex : 'expectDeliveryDate',
				width : 90,
				format : 'Y-m-d',
				renderer : formatDate,
				editor : new fm.DateField({
							format : 'Y-m-d',
							allowBlank : false
						})
			}, {
				header : 'Unit Price(no tax/RMB)',
				dataIndex : 'unitPriceE',
				width : 90,
				
				editor : new fm.NumberField({
							decimalPrecision : 2,
							allowBlank : false
						})
			}, {
				header : "Remark备注",
				dataIndex : 'remark',
				width : 260,
				editor : new fm.TextField({
							allowBlank : false
						})

			}]);
			
	var gridData = [{
				description : '',
				remark : '',
				quantity : '',
				unit : '',
				expectDeliveryDate : '',
				unitPrice : '',
				unitPriceE : '需与采购确认!',
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
							type : "string",
							allowBlank : false
						}, 'quantity', 'unit', {
							name : 'expectDeliveryDate',
							type : 'date',
							dateFormat : 'Y-m-d'
						}, 'unitPrice', 'unitPriceE', 'amount', 'currency',
						'glAccount', 'orderNo', 'buyId', 'remark','type']

			})
	var sm = new Ext.grid.CheckboxSelectionModel();
	var record;
	var context = new Ext.grid.EditorGridPanel({

				id : 'goods',
				title : 'PR Detail',
				autoHeight : true,
				allowBlank : false,
				width:1060,
				sm : new Ext.grid.RowSelectionModel({
							singleSelect : true
						}),
				xtype : 'editorgrid',
				tbar : [{
							text : 'New',
							iconCls : 'icon-add',
							handler : function() {
								gridstore.insert(gridstore.getCount(), new cc({
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
							}
						}, {
							text : 'Delete',
							iconCls : 'icon-del',
							handler : function() {
								if (record == null) {
									Ext.Msg.alert('请选择行');
								} else {
									gridstore.remove(record)
								}
							}
						},{
							text : 'Copy',
							iconCls : 'icon-add',
							handler : function() {
								if(gridstore.getCount()>0){
									var dd = gridstore.getAt(gridstore.getCount()-1);
									gridstore.insert(gridstore.getCount(), new cc({
														description : dd.data['description'],
														band:dd.data['band'],
														remark : '请填写其它成本的供应商',
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
												}
							}
						}],
				cm : cm,
				width : 1060,
				store : gridstore,
				clicksToEdit : 1

			})

	context.getSelectionModel().on('rowselect', function(sm, rowIdx, r) {
				record = this.grid.store.getAt(rowIdx);
			});
	
	var projectList = new Ext.form.ComboBox({
			id:'projectList',
			fieldLabel : 'Project',
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
			editable : false,
			width : 200,
			blankText : 'empty',
			listeners:{
				select:function(ProjectCombox, record,index){						
					//Ext.get('arno').dom.value = record.get('arno');
					//Ext.get('pm').dom.value = record.get('managerUid');
				}
			}
		});
		
		var arnolist = new Ext.form.ComboBox({
			id:'arnolist',
			fieldLabel : 'AR NO.',
			hiddenName : 'prForm.arno',
			name : 'prForm.arno',
			store : projectStore,
			valueField : 'arno',
			displayField : 'arno',
			typeAhead : true,
			mode : 'local',
			triggerAction : 'all',
			emptyText : 'Select a option...',
			selectOnFocus : true,
			allowBlank : false,
			editable : false,
			width : comboxSize,
			blankText : 'empty',
			listeners:{
				select:function(ProjectCombox, record,index){	
					Ext.getCmp('projectList').setValue(record.get('id'));					
					//Ext.get('projectList').dom.value = record.get('id');
					//alert(record.get('managerUid'))
					Ext.get('pm').dom.value = record.get('managerUid');
				}
			}
		});
		
	var buyerShowList = new Ext.form.ComboBox({
							id:'buyerShowList',
							fieldLabel : 'Buyer',
							name : 'prForm.tempolyeeByBuyerId',
							hiddenName : 'prForm.tempolyeeByBuyerId',
							store : buyerList,
							valueField : 'uuid',
							displayField : 'buyId',
							typeAhead : true,
							mode : 'local',
							triggerAction : 'all',
							emptyText : 'Select one Buyer',
							selectOnFocus : true,
							width : 150,
							blankText : 'empty',
							listeners:{
									select:function(ProjectCombox, record,index){													
										Ext.get('relateuid').dom.value = record.get('buyId');
									}
							},
							allowBlank : false
						})
						
	var costList = new Ext.form.ComboBox({
				fieldLabel : 'Cost Center',
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
				width : comboxSize,
				blankText : 'empty',
				listeners:{
						select:function(ProjectCombox, record,index){													
							Ext.get('ccowner').dom.value = record.get('owner');
							//alert("record.get('io')"+record.get('io'));
							
							if(record.get('departId') != null){
								var departids = record.get('departId').split(',');
								for(var i = 0; i< departids.length;i++){
									if(departId == departids[i]){
										Ext.get('io').dom.value = record.get('io').split(',')[i];//record.get('owner');
										return;
									}else{
										Ext.get('io').dom.value = '';
									}
								}
							}else{
								Ext.get('io').dom.value = '';
							}
						}
				},
				width:200,
				allowBlank : false,
				editable : false
			})
			
	//new item
	var isCapital = new Ext.form.ComboBox({
			fieldLabel :'Capital or Expense',
			id : 'isCapital',
			hiddenName : 'prForm.isCapital',
			name : 'prForm.isCapital',
			store : isCapitalStore,
			valueField : 'value',
			displayField : 'name',
			typeAhead : true,
			mode : 'local',
			triggerAction : 'all',
			emptyText : 'Select a state...',
			selectOnFocus : true,
			width : comboxSize,
			blankText : 'empty',
			allowBlank : false,
			 listeners:{
                'select': function(){
                        if(isCapital.getRawValue() == 'capital'){
                    		Ext.getCmp('arnolist').enable();
                    		Ext.getCmp('capitalItem').enable();                 		
                    		Ext.getCmp('dpt').clearValue();
                    		Ext.getCmp('costList').clearValue();
                    		Ext.getCmp('dpt').disable();
                    		Ext.getCmp('costList').disable();
                        }else{
                        	Ext.getCmp('dpt').enable();
							Ext.getCmp('costList').enable();
							Ext.getCmp('capitalItem').clearValue();
							Ext.getCmp('arnolist').clearValue();
							Ext.getCmp('capitalItem').disable();	
							Ext.getCmp('arnolist').disable();
							Ext.getCmp('projectList').clearValue();						
                        }
                    }
                },
			editable : false
		});
	
	
	var dpt = new Ext.form.ComboBox({
			fieldLabel :'Cost Center Dept.',
			id : 'dpt',
			hiddenName : 'name',
			name : 'name',
			store : dptStore,
			valueField : 'dprt',
			displayField : 'dprt',
			typeAhead : true,
			mode : 'local',
			triggerAction : 'all',
			emptyText : 'Select a state...',
			selectOnFocus : true,
			width : comboxSize,
			blankText : 'empty',
			allowBlank : false,
			listeners:{
                'select': function(){
                			var d = document.getElementById('area').value;
                			if(d == 'DT05'){d = 'DT02'}
                        	prFormListManager.doSelectByDprt(d,dpt.getRawValue(),function(data) {
										costList.clearValue()
										costcentreStore.loadData(data);
										if(data.length==1){
											costList.setValue(data[0].id);
											Ext.get('ccowner').dom.value = data[0].owner;
											
											
											if(data[0].departId != null){
												var departids = data[0].departId.split(',');
												for(var i = 0; i< departids.length;i++){
													if(departId == departids[i]){
														Ext.get('io').dom.value = data[0].io.split(',')[i];//record.get('owner');
														return;
													}else{
														Ext.get('io').dom.value = '';
													}
												}	
											} else{
												Ext.get('io').dom.value = '';
											}								
										}
							});
                    }
                },
			editable : false
		});
	
	var buyer1 = new Ext.form.ComboBox({
			fieldLabel :'Purchase Category',
			id : 'buyer1',
			store : kindStore,
			hiddenName : 'prForm.buyer1',
			name : 'prForm.buyer1',
			valueField : 'kind',
			displayField : 'kind',
			typeAhead : true,
			mode : 'local',
			triggerAction : 'all',
			emptyText : 'Select a state...',
			selectOnFocus : true,
			width : comboxSize,
			blankText : 'empty',
			//allowBlank : false,
			listeners:{
                'select': function(kind){
						PuyerListBizManager.doSelectAllCategroy(buyer1.getRawValue(),function(data){
							buyer2.clearValue();
							categoryStore.loadData(data);
						});	
				}
            },
            allowBlank : false,
			editable : false
		});
		
		var buyer2 = new Ext.form.ComboBox({
			fieldLabel :'Category Detail',
			id : 'buyer2',
			store : categoryStore,
			hiddenName : 'prForm.buyer2',
			name : 'prForm.buyer2',
			valueField : 'categroy',
			displayField : 'categroy',
			typeAhead : true,
			mode : 'local',
			triggerAction : 'all',
			emptyText : 'Select a state...',
			selectOnFocus : true,
			width : comboxSize + 100,
			blankText : 'empty',
			//allowBlank : false,
			listeners:{
                'select':function (){
						PuyerListBizManager.doSelectBuyerByCondition(Ext.getCmp('buyer1').value,buyer2.getRawValue(),function(data) {
						//alert();
						
						Ext.getCmp('buyerShowList').setValue(data[0].buyerNetid);
						Ext.get('relateuid').dom.value = data[0].buyerName;
									
						//Ext.get('buyerNetid').dom.value = data[0].buyerName;
			});	
		}		
                },
			allowBlank : false,
			editable : false
		});

		
	var capitalItem = new Ext.form.ComboBox({
			fieldLabel :'Capital Category',
			id : 'capitalItem',
			hiddenName : 'prForm.capitalcategroy',
			name : 'prForm.capitalcategroy',
			store : capitalCategroyStore,
			valueField : 'value',
			displayField : 'name',
			typeAhead : true,
			mode : 'local',
			triggerAction : 'all',
			emptyText : 'Select a state...',
			selectOnFocus : true,
			width : comboxSize,
			blankText : 'empty',
			allowBlank : false,
			editable : false
		});	


	var issueFor = new Ext.form.ComboBox({
		fieldLabel :'Issue For',
		id : 'issueFor',
		hiddenName : 'prForm.issueFor',
		name : 'prForm.issueFor',
		store : issueforStore,
		valueField : 'value',
		displayField : 'name',
		typeAhead : true,
		mode : 'local',
		triggerAction : 'all',
		emptyText : 'Select a Option...',
		selectOnFocus : true,
		width : 190,
		blankText : 'empty',
		allowBlank : false,
				listeners:{
                'select': function(){
                        	var myarea = issueFor.getValue();
                        		if (myarea == 1) { // SH
									area = 'DT02'
								} else if (myarea == 2) {
									area = 'DT03'
								} else if (myarea == 3) {
									area = 'DT04'
								} else if(myarea == 4){
									area = 'DT05'
								}
								

								if(document.getElementById('area').value != 'DT02'){
									if(area != document.getElementById('area').value ){
										//just capital
										Ext.getCmp('isCapital').disable();
										Ext.getCmp('isCapital').setValue(0);
									}else{
										Ext.getCmp('isCapital').enable();
									}
								}else{
								//alert(myarea)
									if((myarea == 2||myarea == 3)){
										//just capital
										Ext.getCmp('isCapital').disable();
										Ext.getCmp('isCapital').setValue(0);									
										Ext.getCmp('arnolist').enable();  
										Ext.getCmp('capitalItem').enable(); 
										//
										Ext.getCmp('dpt').disable();
                    					Ext.getCmp('costList').disable();										 
									}else{										
										Ext.getCmp('isCapital').enable();
									}
								}
																
								costList.clearValue();
								//loadAllDprt(document.getElementById('area').value);
								//alert(document.getElementById('area').value)
								//loadCostcentreData2(document.getElementById('area').value);
								//loadBuyerData2(area);
                    }
                },
		editable : false
	});	
			
	if (area == 'DT03') { // 沈阳
		var issuefordata = [{'name':'SY Plant','value':2}];
		issueforStore.loadData(issuefordata);
		Ext.getCmp('issueFor').setValue(2);
	} else if (area == 'DT04') {
		var issuefordata = [{'name':'YT Plant','value':3}];
		issueforStore.loadData(issuefordata);
		Ext.getCmp('issueFor').setValue(3);
	} else if (area == 'DT05') {
		var issuefordata = [{'name':'SH Plant','value':4}];
		issueforStore.loadData(issuefordata);
		Ext.getCmp('issueFor').setValue(4);
	} else {
		var issuefordata = [{'name':'SH Plant','value':4},{'name':'SY Plant','value':2},{'name':'YT Plant','value':3},{'name':'SH Company','value':1}];
		issueforStore.loadData(issuefordata);
		Ext.getCmp('issueFor').setValue(1);
	}
	
	var isPlan = new Ext.form.ComboBox({
		fieldLabel :' In Budget or Not',
		id : 'isPlan',
		hiddenName : 'prForm.isPlan',
		name : 'prForm.isPlan',
		store : isPlanStore,
		valueField : 'value',
		displayField : 'name',
		typeAhead : true,
		mode : 'local',
		triggerAction : 'all',
		emptyText : 'Select a Option...',
		selectOnFocus : true,
		width : comboxSize,
		blankText : 'empty',
		allowBlank : false,
		editable : false
	});	
	
	var supplier = new Ext.form.Field({
		fieldLabel :'Deliever to',
		id : 'supplier',
		width:110,
		name : 'prForm.receivingPlacePerpoleTel'
	});		
	
	Ext.getCmp('arnolist').disable();  
	Ext.getCmp('costList').disable();  
	Ext.getCmp('capitalItem').disable();  
	Ext.getCmp('dpt').disable(); 


	var label;

	var buyerButton = new Ext.Button({
				text : '选择采购员',
				handler : function() {
					label = 'aaaaa';
				}
			})

	var remark = new Ext.form.TextArea({
				id : 'remark',
				name : 'prForm.remark',
				width : 340,
				height : 120,
				fieldLabel : 'Purpose',
				allowBlank : false
			})

	var workshopToUse = new Ext.form.TextArea({
			id : 'workshopToUse',
			name : 'prForm.workshopToUse',
			width : 340,
			height : 120,
			fieldLabel : 'For Which Department/Workshop/Equipment?',
			allowBlank : true
		});
			
	function valueReplace(v) {
		v = v.toString().replace(new RegExp('(["\"])', 'g'), "\\\"");
		return v;
	}

	var AR_NO = new Ext.form.Field({
				fieldLabel : 'AR No.',
				name : 'prForm.arno',
				id:'arno',
				width:comboxSize,
				allowBlank : false
			});
				
	var pr = new Ext.FormPanel({
		monitorValid : true,
		fileUpload : true,		                     		  
		width: 1060,  
		renderTo: Ext.getBody(),  
        layout:'table',  
        layoutConfig: {columns:4},  		 
		title : 'Delphi Purchase Requisition',
		autoHeight : true,
		defaults: { frame: true,height: 50 },		
		items : [
			{
				 layout:'form',
				 width: 1060,
				 height:45,
				 colspan:4,
				 //rowspan:2,
				 items:[
				 {
                    id:'pm',
                    name: "prForm.pm",
                    xtype: "hidden"
                },{
                    id:'relateuid',
                    name: "prForm.relateuid",
                    xtype: "hidden"
                },{
                    id:'ccowner',
                    name: "prForm.ccowner",
                    xtype: "hidden"
                },{
                    id:'buyerNetid',
                    name: "prForm.buyerNetid",
                    xtype: "hidden"
                },
               
                          
             
				{
				 layout:'form',
				 id : 'telephone1',
				 width: 240,
				 //frame:true,
				 colspan:4,
				 items:[{id : 'telephone1',
					fieldLabel : 'Telephone',
					xtype : 'textfield',
					name : 'prForm.telephone'
					//allowBlank : false
				}]
			}
				]},
			//=====	
			{
				 layout:'form',
				 width: 480,
				 //frame:true,
				 colspan:2,
				 items:[issueFor]
			},{
				 layout:'form',
				 width: 585,
				 //frame:true,
				 colspan:2,
				 readOnly:true,
				 editable:false,
				 items:[{			
					id : 'io',						
					fieldLabel : 'IO',
					xtype : 'textfield',
					name : 'prForm.io',
					readOnly:true		
				}]
			},
				
				
			//row1	
			{
				 layout:'form',
				 width: 240,
				 //frame:true,
				 //colspan:2,
				 items:[{
					xtype : 'checkbox',
					id:'isUrgency',
					fieldLabel : 'Urgent',
					name : 'prForm.isUrgency',
					inputValue : 1
				}]
			},{
				 layout:'form',
				 width: 240,
				 items:[{
					xtype : 'fileuploadfield',
					id : 'urgencyFile',
					emptyText : 'zip Format',
					fieldLabel : 'Urgent Purchase Form',
					width:120,
					name : 'urgencyFile',
					buttonCfg : {
						text : 'Upload',
						iconCls : 'upload-icon'
					}
				}]
			},{
				 layout:'form',
				 width: 240,
				 //frame:true,
				 colspan:1,
				 items:[isCapital]
			},{
				 layout:'form',
				 width: 340,
				 //frame:true,
				 colspan:1,
				 items:[isPlan]
			},
			//row2
			{
				layout:'form',
				width: 240,
				colspan:1,
				items:[{xtype : 'checkbox',
					fieldLabel : 'Single Source',
					name : 'prForm.isAssignation',
					id:'isAssignation',
					inputValue : 1
				}]
					
			},{
				layout:'form',
				width: 240,
				colspan:1,
				items:[{
					xtype : 'fileuploadfield',
					id : 'singlesourceFile',
					emptyText : 'zip Format',
					fieldLabel : 'single source Form',
					width:120,
					name : 'singlesourceFile',
					buttonCfg : {
						text : 'Upload',
						iconCls : 'upload-icon'
					}
				}]
					
			},{
				layout:'form',
				width: 240,
				colspan:1,
				items:[capitalItem]
					
			},{
				layout:'form',
				width: 340,
				colspan:1,
				items:[arnolist]
					
			},
			//row3
			{
				layout:'form',
				width: 240,
				colspan:1,
				items:[supplier]				
			},{
				layout:'form',
				width: 240,
				colspan:1,
				items:[{
					id : 'comSupply',					
					xtype : 'textfield',
					width:comboxSize,
					fieldLabel : 'Supplier (Recommended)',
					emptyText : '',
					name : 'prForm.recommendedSupplier'
				}]				
			},{
				layout:'form',
				width: 240,
				colspan:1,
				items:[dpt]
					
			},{
				layout:'form',
				width: 340,
				colspan:1,
				items:[costList]
					
			},
			//row4
			{
				layout:'form',
				width: 480,
				colspan:2,
				items:[buyer1]
			}				
			,{
				layout:'form',
				width: 240,
				colspan:1,
				items:[{
					xtype : 'fileuploadfield',
					id : 'bigcountFile',
					emptyText : 'zip Format',
					fieldLabel : '100K Approval Form',
					width:120,
					name : 'bigcountFile',
					buttonCfg : {
						text : 'Upload',
						iconCls : 'upload-icon'
					}
				}]
			},{
				layout:'form',
				width: 340,
				colspan:1,
				items:[projectList]
					
			}
			,{
				layout:'form',
				width: 480,
				colspan:2,
				items:[buyer2]
					
			},{
				layout:'form',
				width: 560,
				colspan:2,
				items:[buyerShowList]
			},
			//row6
			{
				layout:'form',
				width: 480,
				colspan:2,
				height:120,
				items:[remark]
			},{
				layout:'form',
				width: 580,
				height:120,
				colspan:2,
				items:[workshopToUse]
			},{
				layout:'form',
				width: 1060,
				autoHeight : true,
				colspan:4,
				rowspan:3,
				items:[{
					xtype : 'fileuploadfield',
					id : 'form-file',
					emptyText : 'zip Format',
					fieldLabel : 'Other Attachment',
					width:200,
					name : 'formFile',
					buttonCfg : {
						text : 'Upload',
						iconCls : 'upload-icon'
					}
				},context]					
			}],
		
		buttons : [{
			text : 'Submit',
			handler : function() {
			if(window.confirm('确认提交?')){
					if (Ext.get('form-file').dom.value != 'zip Format'
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
						if(m[i].data['unitPriceE']== '需与采购确认!'){
							alert("请填写单价");
							return false;
						}
						if (m[i].data['expectDeliveryDate'] == ''
								|| m[i].data['description'] == ''
								|| m[i].data['unitPriceE'] == ''						
								|| m[i].data['quantity'] == ''
								|| m[i].data['unit'] == '') {
							alert("购买内容信息每列不能为空，不能有空行");
							return false;
						}
						var description = m[i].data['description'].replace("'","\\'");
						var band = '';
						if(m[i].data['band'] !=null && m[i].data['band'] != 'undefined'){
							band = m[i].data['band'].replace("'","\\'");
						}
						sSend = sSend
								+ "{description:'"
								+ description
								+ "',band:'"
								+ band
								+ "', remark:'"
								+ m[i].data['remark'].replace('\'', '/')
								+ "', quantity:'"
								+ m[i].data['quantity']
								+ "', unit:'"
								+ m[i].data['unit']
								+ "', expectDeliveryDate:'"
								+ Ext.util.Format.date(
										m[i].data['expectDeliveryDate'], 'Y-m-d')
								+ "', unitPrice:'" + m[i].data['unitPrice']
								+ "', amount:'" + m[i].data['amount']
								+ "', currency:'" + m[i].data['currency']
								+ "', glAccount:'" + m[i].data['glAccount']
								+ "', orderNo:'" + m[i].data['orderNo']
								+ "', type:'" + m[i].data['type']
								+ "', unitPriceE:'" + m[i].data['unitPriceE']
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
		}]//end
	});

});
