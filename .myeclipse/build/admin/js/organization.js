/**
 * sdaac employee organization
 */
Ext.BLANK_IMAGE_URL='img/blank.gif'
Ext.onReady( function(){
	 var tree = new Ext.tree.TreePanel({
        el:'tree',
        width:'500',
        height:'800',
        autoScroll:true,
     //   animate:true,
        enableDD:true,
        containerScroll: true,
        loader: new Ext.tree.TreeLoader({
      //      dataUrl:'treeTest/a.jsp'
                     dataUrl:'employee.do?actionType=doSelectOrganization'
        })
    });
    
    tree.on('click',function(node,event){      
         // alert("node.id=");    
          event.preventDefault(); //这行是必须的    
          showUrl1(node.id)
          
    });    
      // set the root node
    var  root = new Ext.tree.AsyncTreeNode({
        text: 'root',
        draggable:false,
        id:'-1'
    });
    tree.setRootNode(root);

    // render the tree
    tree.render();
    root.expand();
})