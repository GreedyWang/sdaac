	// var costList = new Ext.form.ComboBox({
	// fieldLabel : '成本中心',
	// id : 'costList',
	// hiddenName : 'prForm.prCostCenter.id',
	// name : 'prForm.prCostCenter.id',
	// store : costcentreStore,
	// valueField : 'id',
	// displayField : 'costCenterNameEnglish',
	// typeAhead : true,
	// mode : 'local',
	// triggerAction : 'all',
	// emptyText : 'Select a state...',
	// selectOnFocus : true,
	// width : 190,
	// blankText : 'empty',
	// editable : false
	//
	// })
	
		// 查询科目费用使用情况
		
	//var flag;
//	alert('!')
//	if(typeof(flag)!='undefined'){
		//ardetial.applyTo = 'ardetail'
	//	alert(flag)
//	}

function initARdetail(target){
		var xxx = function(ele) {
		// alert(Ext.get('arno').dom.value)
		Ext.getCmp('AR_NO_Tip').getEl()
				.update('<font color="red">剩余费用：1000,在审批费用3000</front>');
		// subjectDetailService.show('', function(data) {
		// Ext.getCmp('AR_NO_Tip').getEl().update('剩余费用：1000,在审批费用3000');
		// })

	}
	
	var AR_NO_lable = new Ext.form.Label({
			id : 'AR_NO_lable',
			 columnWidth:.35,
			 height:20,
			 style : 'font-size: 12',
			text : 'AR NO(如果PR为资本化，此项必需填写)'
		});
		
		var blank1 = new Ext.form.Label({			
			 columnWidth:.02						
		});
		var blank2 = new Ext.form.Label({			
			 columnWidth:.02						
		});
	var AR_NO = new Ext.form.Field({
				id : 'arno',
				columnWidth:.20,
				height:20,
				fieldLabel : 'AR NO(如果PR为资本化，此项必需填写)',
				name : 'prForm.arno'

			});
	var AR_NO2 = new Ext.form.ComboBox({
				id : 'arno2',
				fieldLabel : 'AR NO(如果PR为资本化，此项必需填写)',
				columnWidth:.1,
				height:20,
				hiddenName : 'prForm.arno2',
				name : 'prForm.arno2',
				emptyText : 'Select a state...',
				selectOnFocus : true,
					 triggerAction : 'all',
				editable : false,
				store : ['a', 'b','c']
			});
			AR_NO2.setValue('c')
	var AR_NO3 = new Ext.form.Field({
				id : 'arno3',
				columnWidth:.1,
				fieldLabel : 'AR NO(如果PR为资本化，此项必需填写)',
				name : 'prForm.arno',
				listeners : {
					blur : xxx
				}
			});

	var AR_NO_Tip = new Ext.form.Label({
				id : 'AR_NO_Tip',
				columnWidth:1,
				text : ''
			});
			var aa = document.getElementById('bb')

	
	if(target == 'update'){
			var ardetial = new Ext.Panel({
				layout : 'column',
				height : 50,
				width:800,			
				border:false,
				applyTo:'bb',
				items : [AR_NO_lable,AR_NO,blank1,AR_NO2,blank2,AR_NO3, 
				AR_NO_Tip]
			})
	}else{
			var ardetial = new Ext.Panel({
				layout : 'column',
				height : 50,
				width:800,			
				border:false,
				//applyTo:'bb',
				items : [AR_NO_lable,AR_NO,blank1,AR_NO2,blank2,AR_NO3, 
				AR_NO_Tip]
			})
	}
	
	return ardetial
}

	
			