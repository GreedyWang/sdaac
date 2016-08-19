Ext.onReady(function (){
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());//以cookie的方式记住用户对面板的部署
	var panelTop=new Ext.Panel({
		id:'a',
		title:'SDAAC PLM System',
		region:'center',
		
		height:'600',
		collapsible:true,
 		autoScroll:true,
 			split:true,
		html:'<iframe frameBorder="0" id="main" name="main" scrolling="auto" height="100%" width="100%" src="plm/plm.jsp" ></iframe>'
	})
	

	
//	var panelBottomLTopBar=new Ext.Toolbar({
//		items:[
//		{
//			xtype:'button',
//			 text:"下载",
//			 handler:download
//		}
//		]
//		
//	})
	
	
	var panelBottomL=new Ext.Panel({
		id:'b',
		title:'tree',
		region:'east',
		width:'400',
		height:'100',
	//	tbar:panelBottomLTopBar,
		collapsible:true,
 		autoScroll:true,
		split:true,
		html:'<iframe frameBorder="0" id="tree" name="tree" scrolling="auto" height="100%" width="100%" src="	plm/TreePanel.jsp" ></iframe>'
	})
//	var panelBottomR=new Ext.Panel({
//		title:'details',
//		width:'400',
//		height:'100',
//		split:true,
//		region:'east',
//		html:'<iframe frameBorder="0" id="detail" name="detail" scrolling="auto" height="100%" width="100%" src="	plm/showDetails.jsp" ></iframe>'
//	})
	
//	var p=new Ext.Panel({
//			region:'east',
//		layout:'border',
//		items:[panelBottomL]	
//	})
		
	new Ext.Viewport({
	
			layout:'border',
		
		items:[panelTop,panelBottomL]
	})
})