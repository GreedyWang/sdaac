/*
 * Ext JS Library 2.0.1
 * Copyright(c) 2006-2008, Ext JS, LLC.
 * licensing@extjs.com
 *
 * http://extjs.com/license
 */
    var  id ;
    var Tree  = Ext.tree;
    var tree;
    var type;
    var rootName;
function bb(href){
    // shorthand
    
   
     tree = new Tree.TreePanel({
        el:'tree-div',
        width:'500',
        height:'800',
        autoScroll:true,
     //   animate:true,
        enableDD:true,
        containerScroll: true,
        loader: new Tree.TreeLoader({
      //      dataUrl:'treeTest/a.jsp'
                     dataUrl:href
        })
    });
    
        //绑定节点右键菜单功能   
      tree.on('contextmenu',function(node,event){      
         // alert("node.id="+ node.id);    
          event.preventDefault(); //这行是必须的    
          rightClick.showAt(event.getXY());//取得鼠标点击坐标，展示菜单    
          //alert(node.id);    
          document.getElementById('rootid').value=node.id;    
                 document.getElementById('bbb').value=node.text;    
         });     
             
             
     //定义右键菜单    
    var rightClick = new Ext.menu.Menu({    
        id :'rightClickCont',    
        items : [{    
            id:'rMenu1',    
            text : '属性',    
            //增加菜单点击事件    
            handler:function (node){    
             
                 var pk=document.getElementById('rootid').value
              window.open('/bpp/plm.do?actionType=showDetail&mpartId='+pk,null,
"height=200,width=600,status=yes,toolbar=no,menubar=no,location=no");
                       
            }    
        }, {    
            id:'re',    
            text : '反查BOM'   ,
             handler:function (node){    
              
                 var pk=document.getElementById('rootid').value
               var name=document.getElementById('bbb').value
                window.open('/bpp/plm/TreePanel.jsp?name='+name+'&id='+pk+'&type=re')                   
            }    
        }]    
     });    
    

    // set the root node
    var  root = new Tree.AsyncTreeNode({
        text: rootName,
        draggable:false,
        id:id
    });
    tree.setRootNode(root);

    // render the tree
    tree.render();
    root.expand();
}

function change()
{
	 id=document.getElementById('aa').value; //得到pk

	 type=document.getElementById('type').value; //得到 顺逆序
	 
	 rootName=document.getElementById('rootName').value+"/1";//得到节点名称 
	 var href
	if(type==''||type==null)
	{
			 href='plm.do?actionType=showTree'
			bb(href);
	}else{
			
				 href='plm.do?actionType=reShowTree'
			bb(href);
	}

}

        
  