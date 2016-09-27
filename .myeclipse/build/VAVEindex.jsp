<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
  <title>HRpage</title>
	<link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css" />
    
    <!-- GC -->
 	<!-- LIBS -->
 	<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
 	<!-- ENDLIBS -->
    <link rel="stylesheet" type="text/css" href="menus.css"/>
    <script type="text/javascript" src="ext/ext-all.js"></script>
  <script type="text/javascript">Ext.BLANK_IMAGE_URL = 'ext/resources/images/default/s.gif';</script>
	<style type="text/css">
	html, body {
        font:normal 12px verdana;
        margin:0;
        padding:0;
        border:0 none;
        overflow:hidden;
        height:100%;
    }
	p {
	    margin:5px;
	}
    .nav {
        background-image:url(../shared/icons/fam/folder_go.png);
    }
    </style>
	<script type="text/javascript">
	
     Ext.onReady(function(){   
    var contentPanel =  new Ext.TabPanel({

        region:'center',
        deferredRender:false,
        activeTab:0
               });
               
     contentPanel.add({
                'id':0,
                'title':'提案统计',
                closable:true,
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="vave/Statistics/statisticsIndex.jsp"></iframe>'              
                });          
      var contentPanel1 = new Ext.Panel({

                title: ' VAVE',
                region: 'west',
              //  contentEl: 'west-div',
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
                    title: "提案管理",
                    html: '<div id="tree"></div>'               
                }]
      });
      	   contentPanel1.add({
                'id':0,
                'title':'工号查询',
                closable:true,
                html:'<iframe width="100%" height="100%" src="vave/util/ShowEmpUid.jsp"></iframe>'              
                }); 

var viewport = new Ext.Viewport({

  layout:'border',

  items:[   
		contentPanel1
    ,
		contentPanel
  ]
});

 var root = new Ext.tree.TreeNode({
                id: "root",//根节点id
                text: "我的提案"
            });
            
 var root1 = new Ext.tree.TreeNode({
                id: "root1",//根节点id
                text: "我的提案"
            });
            
  var business = new Ext.tree.TreeNode({
                id: "business",//根节点id
                text: "经营课题"
            });
  
  var rTopic = new Ext.tree.TreeNode({
                id: 'rTopic',
                text:'发布经营课题',
                listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                 if(n)
                {                
                	contentPanel.remove(n);             
                }
               	n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="vave/kpi/business.jsp"></iframe>'              
                });
                contentPanel.setActiveTab(n);
                }}
            });
  
  var managerTopic = new Ext.tree.TreeNode({
                id: 'mTopic',
                text:'查看已发布的经营课题',
                listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                 if(n)
                {                
                	contentPanel.remove(n);             
                }
               	n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="businessTopic.do?actionType=doSelectMyself"></iframe>'              
                });
                contentPanel.setActiveTab(n);
                }}
            });
  
 var myTask = new Ext.tree.TreeNode({
			    id: "myTask",//根节点id
			    text: "任务管理"
			});
			
			 var myTask_node1 = new Ext.tree.TreeNode({
                id: 'myTask_node1',
                text:'查看我的任务',
                listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                 if(n)
                {                
                	contentPanel.remove(n);             
                }
               	n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="task.do?actionType=doSelectMyTask"></iframe>'              
                });
                contentPanel.setActiveTab(n);
                }}
            });
            
             var myTask_node2 = new Ext.tree.TreeNode({
                id: 'myTask_node2',
                text:'查询我管理的项目',
                listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                 if(n)
                {                
                	contentPanel.remove(n);             
                }
               	n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,//task.do?actionType=doSelectMyTeamTask
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="cc/MyJsp2.jsp"></iframe>'              
                 });
                contentPanel.setActiveTab(n);
                }}
            });
			
            
 var company_root = new Ext.tree.TreeNode({
                id: "company_root",//根节点id
                text: "公司审批"
            });
            
           //定义树节点
              var c1 = new Ext.tree.TreeNode({
                id: 'c1',
                text:'查看我的提案',
                listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
             	if(n)
                {                
                	contentPanel.remove(n);             
                }
               	n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
               html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="proposal.do?actionType=doSearch&uid=${logineduser.uid }"></iframe>'              
                  });            
                contentPanel.setActiveTab(n);
                }}
            });
          
            var c2 = new Ext.tree.TreeNode({
                id: 'c2',
                text:'发布我的提案',
                listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
               if(n)
                {                
                	contentPanel.remove(n);             
                }
               	n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
              html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="proposal.do?actionType=doSelectVaveInfo"></iframe>'              
                 });
                contentPanel.setActiveTab(n);
                }}
            });
            
  var root2=new Ext.tree.TreeNode({
  				id:'root2',
  				text:'部门审批'
  			}); 
            var Rc1 = new Ext.tree.TreeNode({
                id: 'Rc1',
                text:'本部门审批',
                listeners:{
                'click':function(node,event){
                event.stopEvent();
                 var n=contentPanel.getComponent(node.id);
                if(n)
                {                
                	contentPanel.remove(n);             
                }
               	n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
              html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="proposal.do?actionType=doSearchOthers"></iframe>'              
                  });
                contentPanel.setActiveTab(n);
                }}
            });
                var Rc2 = new Ext.tree.TreeNode({
                id: 'Rc2',
                text:'外部门审批',
                listeners:{
                'click':function(node,event){
                event.stopEvent();
                 var n=contentPanel.getComponent(node.id);
                if(n)
                {                
                	contentPanel.remove(n);             
                }
               	n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
               html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="proposal.do?actionType=doSearchOutside"></iframe>'              
                 });
                contentPanel.setActiveTab(n);
                }}
            });
            	
            
   var root3=new Ext.tree.TreeNode({
  				id:'root3',
  				text:'vave任务管理'
  			}); 
        
                var Rc3 = new Ext.tree.TreeNode({
                id: 'Rc3',
                text:'公司可行性评审',
                listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
               if(n)
                {                
                	contentPanel.remove(n);             
                }
               	n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
             html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="proposal.do?actionType=doSearchAllCompany"></iframe>'              
                   });
                contentPanel.setActiveTab(n);
                }}
            });
           
           var count = new Ext.tree.TreeNode({
                id: "count",//根节点id
                text: "数据统计"
            }); 
           
        var menu1 = new Ext.tree.TreeNode({
                id: "menu1",//根节点id
                text: "提案统计",
                  listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                 if(n)
                {                
                	contentPanel.remove(n);             
                }
               	n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="vave/Statistics/VaveCount.jsp"></iframe>'              
                });
                contentPanel.setActiveTab(n);
                }} 
            });
            
             var f = new Ext.tree.TreeNode({
                id: "f",//根节点id
                text: "车型统计",
                  listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                 if(n)
                {                
                	contentPanel.remove(n);             
                }
               	n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="vave/Statistics/fc.jsp"></iframe>'              
                });
                contentPanel.setActiveTab(n);
                }} 
            });   
            
        	var vTopics = new Ext.tree.TreeNode({
                id: "vTopics",//根节点id
                text: "查看经营课题",
                        listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                  if(n)
                {                
                	contentPanel.remove(n);             
                }
               	n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="businessTopic.do?actionType=doSelect"></iframe>'              
                });
                contentPanel.setActiveTab(n);
                }}
            });
            
            var allThemes = new Ext.tree.TreeNode({
                id: "allThemes",//根节点id
                text: "查看所有发布的提案",
                        listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                  if(n)
                {                
                	contentPanel.remove(n);             
                }
               	n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="vave/Statistics/AllTopic.jsp"></iframe>'              
                });
                contentPanel.setActiveTab(n);
                }}
            });
          
           var menu2 = new Ext.tree.TreeNode({
                id: "menu2",//根节点id
                text: "VAVE管理",
                        listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                  if(n)
                {                
                	contentPanel.remove(n);             
                }
               	n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="vave/info/vaveManager.html"></iframe>'              
                });
                contentPanel.setActiveTab(n);
                }}
            });
               var menu3 = new Ext.tree.TreeNode({
                id: "menu3",//根节点id
                text: "VAVE工作室",
                        listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                 if(n)
                {                
                	contentPanel.remove(n);             
                }
               	n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="vave/info/vaveworkroom.html"></iframe>'              
                });
                contentPanel.setActiveTab(n);
                }}
            });
               var menu4 = new Ext.tree.TreeNode({
                id: "menu4",//根节点id
                text: "VAVE基本知识",
                        listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                if(n)
                {                
                	contentPanel.remove(n);             
                }
               	n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="vave/info/vavebaseknowledge.html"></iframe>'              
                });
                contentPanel.setActiveTab(n);
                }}
            });
               var menu5 = new Ext.tree.TreeNode({
                id: "menu5",//根节点id
                text: "VAVE大事记",
                listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                  if(n)
                {                
                	contentPanel.remove(n);             
                }
               	n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="vave/info/bigevent.html"></iframe>'              
                });
                contentPanel.setActiveTab(n);
                }}
            });
            
            var menu6 = new Ext.tree.TreeNode({
                id: "menu6",//根节点id
                text: "VAVE纪念品超市",
                listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                  if(n)
                {                
                	contentPanel.remove(n);             
                }
               	n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="goods.do?actionType=showAll"></iframe>'              
                });
                contentPanel.setActiveTab(n);
                }}
            });
            
            var rate = new Ext.tree.TreeNode({
                id: "rate",//根节点id
                text: "参与率",
                listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                  if(n)
                {                
                	contentPanel.remove(n);             
                }
               	n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="statistics.do?actionType=rateAcount"></iframe>'              
                });
                contentPanel.setActiveTab(n);
                }}
            });
            
             
            var approveRate = new Ext.tree.TreeNode({
                id: "approveRate",//根节点id
                text: "提案批准率",
                listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                  if(n)
                {                
                	contentPanel.remove(n);             
                }
               	n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="statistics.do?actionType=approveRateAcount"></iframe>'              
                });
                contentPanel.setActiveTab(n);
                }}
            });
            
            var projectList = new Ext.tree.TreeNode({
                id: "projectList",//根节点id
                text: "所有项目列表",
                listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                  if(n)
                {                
                	contentPanel.remove(n);             
                }
               	n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="task.do?actionType=doSelectProjectList"></iframe>'              
                });
                contentPanel.setActiveTab(n);
                }}
            });
            
    	  var utilEmpUid = new Ext.tree.TreeNode({
                id: "utilEmpUid",//根节点id
                text: "员工工号查询",
                listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                  if(n)
                {                
                	contentPanel.remove(n);             
                }
               	n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="vave/util/ShowEmpUid.jsp"></iframe>'              
                });
                contentPanel.setActiveTab(n);
                }}
            });
    	
    	
    var baseInfo=new Ext.tree.TreeNode({
    			id:'baseInfo',
    			text:'基础数据维护'
    	})
            //生成树形面板
   var annlSale=new Ext.tree.TreeNode({
   			id:'as',
   			text:'年度销售预测',
   			 listeners:{
                'click':function(node,event){
                event.stopEvent();
                var n=contentPanel.getComponent(node.id);
                  if(n)
                {                
                	contentPanel.remove(n);             
                }
               	n=contentPanel.add({
                'id':node.id,
                'title':node.text,
                closable:true,
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="vaveInfo.do?actionType=doSelectAll"></iframe>'              
                });
                contentPanel.setActiveTab(n);
                }}
   	
   })         
            baseInfo.appendChild(annlSale);   
            root1.appendChild(c1);//我的提案
            root1.appendChild(c2);   
          	root.appendChild(root1);
          	business.appendChild(rTopic);
          	business.appendChild(managerTopic);
        	root.appendChild(business);
          	
          	
        	var flag='${logineduser.emp.roleids}';
            if(flag=='yes')
            {
           	var roleids='${logineduser.emp.roleids_b}';    
           var roles=roleids.substring(1,roleids.length-1).split(',')            
	            for(var j=0;j<roles.length;j++)
	            {
	            	
	         		
	            	if(roles[j]=='vaveDepartEmp'||roles[j]=='vavePM'||roles[j]==' vaveDepartEmp'||roles[j]==' vavePM')//部门提案
		            {
		            	 root2.appendChild(Rc2);
		                 root2.appendChild(Rc1);
		                 root.appendChild(root2);
		                 break;
		            }
					 if(roles[j]=='vaveCompany'||roles[j]==' vaveCompany')
		            {
		            	
		            	company_root.appendChild(Rc3);  //公司提案
		            	root.appendChild(company_root);	             	 
		            }
		          
	            }
	            
            }
       
            myTask.appendChild(myTask_node1);
            myTask.appendChild(myTask_node2);
          	root.appendChild(myTask);              //任务管理
          
           
          	root.appendChild(baseInfo);
             count.appendChild(menu1);
              count.appendChild(f);
          root.appendChild(count);
               root.appendChild(vTopics);
               
               root.appendChild(allThemes);
            root.appendChild(menu2);
           root.appendChild(menu3);
           root.appendChild(menu4);
           root.appendChild(menu5);
           root.appendChild(menu6);        
            root.appendChild(rate);
            root.appendChild(approveRate);       
            root.appendChild(projectList);
           root.appendChild(utilEmpUid);
            
            
            
            var tree = new Ext.tree.TreePanel({
                renderTo: "tree",
                root: root,//定位到根节点
                animate: true,//开启动画效果
                enableDD: false,//不允许子节点拖动
                border: false,//没有边框
                rootVisible: false//设为false将隐藏根节点，很多情况下，我们选择隐藏根节点增加美观性
            });
            
              
                                   
function onItemClick(item){

       alert(item.text);

}

 })

</script>
</head>
<body>
<script type="text/javascript" src="../shared/examples.js"></script><!-- EXAMPLES -->
  <div id="north-div"><div id='toolbar-div'></div></div>

  <div id="west-div"></div>

      <div id='center-div'></div>

  

 </body>
</html>
