Ext.onReady(function(){
	Ext.QuickTips.init();//提示link，如果a标签有title的话
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());//以cookie的方式记住用户对面板的部署
	Ext.BLANK_IMAGE_URL='images/default/blank.gif';
	
	var tabs = new Ext.TabPanel({
    renderTo: Ext.getBody(),
    activeTab: 0,
    items: [{
        title: '用户角色管理',
        html: '<iframe width="100%" height="1000px" src="admin/role/UserRoleManager.jsp"></iframe>'
    },{
        title: '角色管理',
        html: '<iframe width="100%" height="1000px" src="admin/role/RoleManager.jsp"></iframe>'
    }]
});
	
})