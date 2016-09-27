Ext.onReady(function(){
	  var tabs2 = new Ext.TabPanel({
        renderTo: document.body,
        activeTab: 0,
        width:'100%',
        height:'800',
        plain:true,
        defaults:{autoScroll: true},
        items:[{
                title: 'VAVE管理',
                autoLoad:'vave/info/vaveManager.html'
            },{
                title: 'VAVE工作室',
                autoLoad: {url: 'vave/info/vaveworkroom.html'}
            },{
                title: 'VAVE基本知识',
                autoLoad:'vave/info/vavebaseknowledge.html'
            },{
                title: 'VAVE大事记',
                autoLoad: {url: 'vave/info/bigevent.html'}
            }
        ]
    });

})