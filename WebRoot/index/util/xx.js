Ext.BLANK_IMAGE_URL='image/blank.gif'
//改变部门指标状态
  	function doSubmit(id,index){
  		var context= document.getElementById('context['+index+']').value
  		var form1 = new Ext.form.FormPanel({
				baseCls : 'x-plain',
				layout : 'absolute',
				url : 'empIndex.do?actionType=updateDepartmentIndexState',
				items : [{
							x : 0,
							y : 5,
							xtype : 'label',
							text : '是否通过:',
							allowBlank:false

						},{
							x : 60,
							y : 5,		
							boxLabel  :'未批准',
							xtype : 'radio',
							name : 'state',
							inputValue :'0',
							checked :true
							
							
						},{
							x : 120,
							y : 5,	
							boxLabel  :'完成指标定义',
							xtype : 'radio',
							name : 'state',
							inputValue :'1'
							
						},{
							x : 220,
							y : 5,	
							boxLabel  :'完成指标评定',
							xtype : 'radio',
							name : 'state',
							inputValue :'2'
		
						}, {
							x : 0,
							y : 35,
							xtype : 'label',
							text : '说明:'
						}, {
							x : 60,
							y : 35,
							xtype : 'textfield',
							name : 'context',
							value:context

						} ]
			});

	var wind = new Ext.Window({
				id : 'panel1',
				// title:'修改',
				// applyTo:'dialog',
				width : 500,
				height : 300,
				layout : 'fit',
				plain : true,
				 buttonAlign:'center',
				// html : '<iframe scrolling="auto" frameborder="0" width="600"
				// height="800" src="welcome/Login.jsp"></iframe>',
				items : form1,
				buttons : [{
							text : '关闭',
							handler : function() {
								// alert(document.getElementById('search').innerHTML)
								// wind.hide();
								// loader.load(root, null);															
								wind.close();
								aaa();
								
							}

						},{
            text: '提交',
            handler : function() {
            	//form.Submit();
            	form1.form.submit({
            		url:'empIndex.do?actionType=updateDepartmentIndexState&id='+id
            	});
    
            }
        }]
			})
	wind.show();
  		
  	}
 
  	
  	function aaa(){
  		//alert('!')	
  		window.location.href='empIndex.do?actionType=showDepartmentIndexState';
  	}
	
