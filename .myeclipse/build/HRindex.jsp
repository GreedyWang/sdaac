<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>HRpage</title>
<link rel="stylesheet" type="text/css"
	href="ext/resources/css/ext-all.css" />

<!-- GC -->
<!-- LIBS -->
<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
<!-- ENDLIBS -->
<link rel="stylesheet" type="text/css" href="menus.css" />
<script type="text/javascript" src="ext/ext-all.js"></script>
<script type="text/javascript">Ext.BLANK_IMAGE_URL = 'ext/resources/images/default/s.gif';</script>
<!--<script language="javascript" src="../grid/PropsGrid.js"></script>-->
<style type="text/css">
html,body {
	font: normal 12px verdana;
	margin: 0;
	padding: 0;
	border: 0 none;
	overflow: hidden;
	height: 100%;
}

p {
	margin: 5px;
}

.nav {
	background-image: url(../shared/icons/fam/folder_go.png);
}
</style>
<script type="text/javascript">
     Ext.onReady(function(){
    var contentPanel =  new Ext.TabPanel({

        region:'center',
        deferredRender:false,
        activeTab:0,
        items:[{
                  contentEl:'center-div',
				  title: '标题一',
				  closable:false,
				  autoScroll:true }]

               });
      var contentPanel1 = new Ext.Panel({

            title: '后台管理',
                region: 'west',
                contentEl: 'west-div',
                split: true,
                border: true,
                collapsible: true,
                width: 180,
                minSize: 150,
                maxSize: 300,
                layout: "accordion",
                
                layoutConfig: {
                
                    animate: true
                
                },
                
                items: [{
                    title: "系统管理",
                    html: '<div id="tree"></div>'
                
                
                
                }, {
                    title: "人力资源档案管理",
                    html: '人力资源档案管理'
                
                },{
		    title:"薪酬管理",
		    html:'薪酬管理'
		},{
		    title:"调动管理",
		    html:'调动管理'
		},{
		    title:"招聘管理",
		    html:'<div id="tree1"></div>'
		}]
      });

         var tb=new Ext.Toolbar('toolbar-div');//创建一个工具条

         tb.add(new Ext.Toolbar.Button({

           text: 'button1',
		
	   iconCls:'blist',

           handler: onItemClick

           }),'-',

        new Ext.Toolbar.Button({
			text: 'button2',
	        iconCls:'blist',
		    handler: onItemClick
			 }),'-'
		);

 

   
 

var viewport = new Ext.Viewport({

  layout:'border',

  items:[

      new Ext.BoxComponent({

             region:'north',

             el:'north-div',

             tbar:tb,

             height:26

      }),
		contentPanel1
    ,
		contentPanel
 	,

      new Ext.BoxComponent({

             region:'south',

             el:'south-div',

             height:24

      })

  ]

});


         
      var root1 = new Ext.tree.TreeNode({
                id: "root",
                text: "招聘管理"
            });
            
              
                var testManager = new Ext.tree.TreeNode({
                id: 'testManager',
                text: '试卷管理'
            
            });
        
         
            var c1 = new Ext.tree.TreeNode({
                id: 'c1',
                text: '职位发布',
                listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                if(!n){n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                 closable:true,
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="joblist.jsp"></iframe>'
                });}
                contentPanel.setActiveTab(n);
                }}
            });
            
             var c2 = new Ext.tree.TreeNode({
                id: 'c2',
                text:'招聘管理',
                listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                if(!n){n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="postinfo.jsp"></iframe>'
                //autoLoad:{url:'joblist.jsp', scripts:true}
                });}
                contentPanel.setActiveTab(n);
                }}
            });
            
             var c3 = new Ext.tree.TreeNode({
                id: 'c3',
                text:'简历筛选',
                listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                if(!n){n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
                
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="postitem.jsp"></iframe>'
                });}
                contentPanel.setActiveTab(n);
                }}
            });
            
             var c4 = new Ext.tree.TreeNode({
                id: 'c4',
                text:'创建面试',
                listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                if(!n){n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="createInterview.jsp"></iframe>'
                });}
                contentPanel.setActiveTab(n);
                }}
            });
            
            var c5 = new Ext.tree.TreeNode({
                id: 'c5',
                text:'面试筛选',
                listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                if(!n){n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="interviewResults.jsp"></iframe>'
                });}
                contentPanel.setActiveTab(n);
                }}
            });
            
            c1.appendChild(c2);
            root1.appendChild(c1);
            root1.appendChild(c3);
            root1.appendChild(c4);
            root1.appendChild(c5);

 
            
            
               var questionManager = new Ext.tree.TreeNode({
                id: 'questionManager',
                text:'管理题库',//select,update,delete
                listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                if(!n){n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="questionAction.do?actionType=QuestionAllType"></iframe>'
                });}
                contentPanel.setActiveTab(n);
                }}
            });
                 
            
            
         

           
      
            var tree1 = new Ext.tree.TreePanel({
                renderTo: "tree1",
                root: root1,
                animate: true,
                enableDD: false,
                border: false,
                rootVisible: false
            });       

      function onItemClick(item){

             alert(item.text);

      }

 })

</script>
</head>
<body>
<script type="text/javascript" src="../shared/examples.js"></script>
<!-- EXAMPLES -->
<div id="north-div">
<div id='toolbar-div'></div>
</div>

<div id="west-div"></div>

<div id='center-div'></div>

<div id="south-div">状态栏</div>

</body>
</html>
