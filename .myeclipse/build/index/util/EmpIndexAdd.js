/**
 * 弹出窗口，显示部门人员
 */
Ext.BLANK_IMAGE_URL='image/blank.gif'


//mycars[0]="Saab"

function copytoOthers(id){
	
	//alert(empData)
	//var empData=[["10", "Ten"]]
	 var isForm = new Ext.form.FormPanel({
       // title: 'ItemSelector Test',
        width:700,
        bodyStyle: 'padding:10px;',
        renderTo: 'itemselector',
        url : 'departIndex.do?actionType=EmployeecopyToEmployees',
        items:[{
            xtype:"itemselector",
            name:"itemselector",
            fieldLabel:"ItemSelector",
            dataFields:["code", "desc"],
            toData:[],
            msWidth:250,
            msHeight:200,
            valueField:"code",
            displayField:"desc",
            imagePath:"images/",
            toLegend:"已选员工",
            fromLegend:"本部门员工",
            fromData:empData,
            toTBar:[{
                text:"Clear",
                handler:function(){
                    var i=isForm.getForm().findField("itemselector");
                    i.reset.call(i);
                }
            }]
        }]
        //,
//        buttons: [{
//            text: 'Save',
//            handler: function(){
//                if(isForm.getForm().isValid()){
//                    Ext.Msg.alert('Submitted Values', 'The following will be sent to the server: <br />'+ 
//                        isForm.getForm().getValues(true));
//                        
//                }
//            }
//        }]
    });

	
	
//	var form1 = new Ext.form.FormPanel({
//				baseCls : 'x-plain',
//				layout : 'absolute',
//				url : 'empIndex.do?actionType=updateDepartmentIndexState',
//				items : [{
//							x : 0,
//							y : 5,
//							xtype : 'label',
//							text : '是否通过:',
//							allowBlank:false
//
//						},{
//							x : 60,
//							y : 5,		
//							boxLabel  :'通过',
//							xtype : 'radio',
//							name : 'state',
//							inputValue :'1'
//							
//							
//						},{
//							x : 120,
//							y : 5,	
//							boxLabel  :'不通过',
//							xtype : 'radio',
//							name : 'state',
//							inputValue :'0',
//							checked :true
//						}, {
//							x : 0,
//							y : 35,
//							xtype : 'label',
//							text : '说明:'
//						}, {
//							x : 60,
//							y : 35,
//							xtype : 'textfield',
//							name : 'context',
//							value:context
//
//						} ]
//			});
    
	var wind = new Ext.Window({
				id : 'panel1',
				// title:'修改',
				// applyTo:'dialog',
				width : 720,
				height : 400,
				layout : 'fit',
				plain : true,
				 buttonAlign:'center',
				// html : '<iframe scrolling="auto" frameborder="0" width="600"
				// height="800" src="welcome/Login.jsp"></iframe>',
				items : isForm,
				buttons : [{
							text : '关闭',
							handler : function() {													
								wind.close();							
							}

						},{
            text: '提交',
            handler : function() {
							alert('success');
            	//form.Submit();
            	isForm.form.submit({
            		url:'departIndex.do?actionType=EmployeecopyToEmployees&empIndexId='+id
            	});
            	//
            }
        }]
			})
			
			
	wind.show();
  		
  	}
