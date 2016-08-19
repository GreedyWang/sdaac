/*
 * Ext JS Library 2.2
 * Copyright(c) 2006-2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */

Ext.onReady(function(){
    Ext.QuickTips.init();


    function selectFirstName()
{
	PostIDManager.selectFirstName(function(data){
		storedm.loadData({dms:data});
		
	});
	
}
    
    
    
    //**测试下拉框级联**
 var storedm = new Ext.data.SimpleStore({   //队名称下拉框
       
     
     root: 'dms'   
    
    }); 
    

    
    
     //单位字段
    var dwField = new Ext.form.ComboBox({
		fieldLabel:'所属队',
		name:'DW',
		//hiddenName:'DW',//hiddenName才是提交到后台的input的name（既select的value值）   
		allowBlank:false,
	    mode: 'local',
	    readOnly:true,
	    triggerAction:'all',
	    anchor:'90%',
	    emptyText:'请选择...',//默认值   
	    store:storedm,
	
        listClass: 'x-combo-list-small', //测试的属性
        lastQuery:'', //测试的属性
		valueField: 'id',
	    displayField: 'mc'
	});
	
	dwField.render('firstName');
	
	
});