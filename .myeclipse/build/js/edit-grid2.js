/*
 * Ext JS Library 2.0.1
 * Copyright(c) 2006-2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */


Ext.onReady(function(){
    Ext.QuickTips.init();
    Ext.BLANK_IMAGE_URL='';
    
    var id= document.getElementById('interviewid').value;
  
    
  
    function showUrl1(value)
	{
		var hrefel="javascript:showRow('"+value+"');";
	 return '<a id="rowcell'+value+'" class="x-btn" href="'+hrefel+'">详细信息</a>';
	// return '<input id="rowcell'+value+'" class="x-btn" type="button" value="详细信息" onclick="'+hrefel+'"/>';
	}



    
	var title='职位名称 面试序号，时间，地点，考官';
	//var title=Ext.get('2').dom.value;
	
	// 格式化日期显示
    function formatDate(value){
        return value ? value.dateFormat('Y, M d') : '';
    }
	
	// 格式化性别显示,这是个 renderer, 渲染器
	function formatSex(value) {
		return value ? "男" : "女";
	}
	
    // 弄个缩写的别名
    var fm = Ext.form;


    // 列模型定义了表格所有列的信息, 
    //姓名 身份证 性别 年龄 学历 电话 Email
	// dataIndex 将特定的列映射到数据源(Data Store)中的数据列(在后面创建)
    var cm = new Ext.grid.ColumnModel([
         
		{
           id:'name',
           header: "用户名",
           dataIndex: 'realname',
           width: 120
         
        },
      		{
           id:'idcard',
           header: "身份证",
           dataIndex: 'card',
           width: 200
        },
//				{
//           id:'education',
//           header: "学历",
//           dataIndex: 'education',
//           width: 100
//        },	
//		{
//           header: "年龄",
//           dataIndex: 'age',
//           width: 40,
//           align: 'right'
//        },
//		{
//           id:'phone',
//           header: "电话",
//           dataIndex: 'phone',
//           width: 95
//		
//        },
//		{
//           header: "电子邮件",
//           dataIndex: 'email',
//           width: 220,
//           align: 'left'
//           
//        },
//		{
//           header: "性别",
//           dataIndex: 'sex',
//           width: 40		  
//        },
//        {
//           id:'resume',
//           header: "简历详细信息话",
//           dataIndex: 'resume',
//           width: 95,
//		   buttons: new Ext.Button,
//		   renderer:'showUrl1'
//        }
		 {header: "详细信息", width: 100, sortable: true,renderer:showUrl1,dataIndex: 'realname'}
    ]);

    // 默认情况下列是可排序的
    cm.defaultSortable = true;
	
    // 定义一个用户对象,这样便于我们动态的添加记录,虽然也可以设置成匿名内置对象
//    var User = Ext.data.Record.create([
//           // 下面的 "name" 匹配读到的标签名称, 除了 "birthDay",它被映射到标签 "birth"
//        
//           {name: 'name', type: 'string'},
//           {name: 'idcard', type: 'int'},
//		   {name: 'education'},          
//           {name: 'age', type: 'int'},   
//           {name: 'phone', type: 'int'},   
//           {name: 'email', type: 'string'},
//           {name: 'sex', type: 'bool'}
//      ]);
//
//    // 创建 Data Store
    var store = new Ext.data.JsonStore({
    		
    		fields:['realname','card']
    });


    // 创建编辑器表格
    var grid = new Ext.grid.EditorGridPanel({
        store: store,
        cm: cm,
        renderTo: 'editor-grid',
        width:1000,
        height:300,
  
        title:title,// 标题
        frame:true,
        clicksToEdit:1,//设置点击几次才可编辑
        selModel: new Ext.grid.RowSelectionModel({singleSelect:false}),//设置单行选中模式, 否则将无法删除数据

		// 顶部工具栏按钮
        tbar: [
		
		
        {
           text: '总结果查看',
        	iconCls: 'details',
        	handler:function(){
        		var interviewid=Ext.get('interviewid').dom.value;
        		location.href='details.jsp?id='+interviewid;
        	}
        	
        },
			{
            text: '面试结果登记',
			iconCls: 'details',
            handler : function(){
                var recordtoedit = grid.getSelectionModel().getSelected();// 返回值为 Record 类型
                if(recordtoedit) {
				//	Ext.Msg.alert('查看选中', "您现在选中的用户其名字为:" + recordtoedit.get("name"));// 取得用户名
					// alert("您现在选中的用户其名字为:" + recordtoedit.get("name"));
					location.href='enrollresults.jsp?uname='+recordtoedit.get("card");
				}
            }
        }
		
//		{
//            text: '面试结果查看',
//			iconCls:'details',
//             handler : function(){
//                var recordtoedit = grid.getSelectionModel().getSelected();// 返回值为 Record 类型
//                if(recordtoedit) {
//				//	Ext.Msg.alert('查看选中', "您现在选中的用户其名字为:" + recordtoedit.get("name"));// 取得用户名
//					// alert("您现在选中的用户其名字为:" + recordtoedit.get("name"));
//					location.href='results.jsp?uname='+recordtoedit.get("idcard");
//				}
//            }
//        }
		
//		{
//            text: '删除所有',
//			iconCls: 'user-delete',
//            handler : function(){
//				Ext.MessageBox.confirm('确认删除', 
//		    	    	'你真的要删除所有用户信息吗?', 
//		    	    	function(btn) {
//			    	     if(btn == 'yes') {// 选中了是按钮
//			    	     	 // 调用 DWR, 执行结果成功时方删除所有数据
//				    	     JUserManager.removeAllUser( function(data) {
//				    	     	if(data) {
//				    	     		// 更新界面, 来真正删除数据
//							 		store.removeAll();
//							 	} else {
//							 		Ext.Msg.alert("出错了!","批量数据删除失败!");
//							 	}
//							 });
//						 }
//						}
//					);
//				
//            }
//        }
		
		]
    });
	

	// 单元格编辑后事件处理
	grid.on("afteredit", afterEdit, grid);
	
	// 事件处理函数
	function afterEdit(e) {
		var record = e.record;// 被编辑的记录
		
		// 显示等待对话框
		Ext.Msg.wait("请等候", "修改中", "操作进行中..."); 
		
    	 // 调用 DWR, 执行结果成功时方删除所有数据
   	     JUserManager.editUser( record.get("id"), e.field, e.value, function(data) {
   	     	if(data) {
   	     		// 更新界面, 来真正删除数据
				Ext.Msg.alert('您成功修改了用户信息', "被修改的用户是:" + e.record.get("name") + "\n 修改的字段是:" 
				+ e.field);// 取得用户名
		 	} else {
		 		Ext.Msg.alert("出错了!","修改数据失败,将重置为修改前的值!",function(){
	            	record.set(e.field, e.originalValue);
	            });
		 	}
		 });
		
	};
  JResumeBizManager.getresumeByInterviewid(id,function(result){
    var data=result;
    store.loadData(data);
    });
    // 触发数据加载
  // store.load();
    grid.render('editor-grid');
});
function showRow(value)
{
var window1;
var win=Ext.get("showDiv");
win.load({
 url: '/human_resource/resumeinfo.do',
 params:'actionType=selectById&name='+value,
 callback:function(){
 window1=new Ext.Window({
		title:'title',
	width:400,
	height:400,
	html:Ext.getDom("showDiv").innerHTML
	});
	window1.show();
 }
});
}
