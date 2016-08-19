Ext.onReady(function(){
	var newsGrid=new Ext.Panel({
		region : 'center',
		title:'公司新闻'
	})
	var contentPanel1=new Ext.Panel({
		region : 'west',
		width:'300',
		title:'公司新闻'
	})
	

	var viewport = new Ext.Viewport({

				layout : 'border',
				width:'1000',
				items : [contentPanel1, newsGrid]
			});
})