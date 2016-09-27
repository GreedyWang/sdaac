Ext.onReady(function(){
	Ext.QuickTips.init();//提示link，如果a标签有title的话
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());//以cookie的方式记住用户对面板的部署
	Ext.BLANK_IMAGE_URL='image/blank.gif'
	/**
	 * 子菜单
	 */
	  var dateMenu = new Ext.menu.DateMenu({
        handler : function(dp, date){
            Ext.example.msg('Date Selected', 'You chose {0}.', date.format('M j, Y'));
        }
    });

    var colorMenu = new Ext.menu.ColorMenu({
        handler : function(cm, color){
            Ext.example.msg('Color Selected', 'You chose {0}.', color);
        }
    });
	
	  var menu = new Ext.menu.Menu({
        id: 'mainMenu',
        items: [
            {
                text: 'I like Ext',
                checked: true,       // when checked has a boolean value, it is assumed to be a CheckItem
                checkHandler: action
            },
            {
                text: 'Ext for jQuery',
                checked: true,
                checkHandler: action
            },
            {
                text: 'I donated!',
                checked:false,
                checkHandler: action
            }, '-', {
                text: 'Radio Options',
                menu: {        // <-- submenu by nested config object
                    items: [
                        // stick any markup in a menu
                        '<b class="menu-title">Choose a Theme</b>',
                        {
                            text: 'Aero Glass',
                            checked: true,
                            group: 'theme',
                            checkHandler: action
                        }, {
                            text: 'Vista Black',
                            checked: false,
                            group: 'theme',
                            checkHandler: action
                        }, {
                            text: 'Gray Theme',
                            checked: false,
                            group: 'theme',
                            checkHandler: action
                        }, {
                            text: 'Default Theme',
                            checked: false,
                            group: 'theme',
                            checkHandler: action
                        }
                    ]
                }
            },{
                text: 'Choose a Date',
                iconCls: 'calendar',
                menu: dateMenu // <-- submenu by reference
            },{
                text: 'Choose a Color',
                menu: colorMenu // <-- submenu by reference
            }
        ]
    });
	
	/**
	 * 主菜单
	 */
	 function asd(){ 	
	 var btn1=new Ext.Button({
	 text:"文件",
	  iconCls  :'ddd',
	 menu:[
	  {icon:'images/menu-parent.gif',
	  text:"打开",
	   handler:action
	  },'-',
	  {icon:"save.jpg",text:"保存"},
	  {text:"关闭"}]});
	var btn2=new Ext.Button({
	 text:"编辑",
	 menu:[
	  {text:"复制"},
	  {text:"拷贝"},
	  "-",
	  {text:"查找"},
	  {text:"替换"}
	 ] 
	})
	this.toolbar=new Ext.Toolbar({
	 items:[btn1,btn2]
	});

	}
	
	
//	
//var ddd=	new asd()
//	  ddd.toolbar.render('header');
//		ddd.toolbar.add({
//		text:'others',
//		menu:menu
//	});

	
	    var tb = new Ext.Toolbar();
  tb.render('header');

    tb.add({
            text:'Button w/ Menu',
            iconCls: 'bmenu',  // <-- icon
            menu: menu  // assign menu by instance
        });
	
	function action(btn){
		 Ext.Msg.alert(btn.text)
		 
	}

	
	
	
/**
 * 主界面
 */
	new Ext.Viewport({
		layout:'border',
		items:[{
			id:'north',
			region:'north',
		//	xtype : 'box',
			applyto:'header',
			 bodyStyle:'background: #dfe8f6 url("images/a_01.jpg") no-repeat;',
			 tbar:tb,
			height:80
		},{
			id:'south',
			region:'south',
			title:'south_test,'
	//		applyto:'south'
		},{
			id:'west',
			region:'west',
			title:'west,',
			collapsible:true,
			 autoScroll:true,
			  split:true,
			
	//		applyto:'west',
			width:'200'
		},{
			id:'east',
			region:'east',
			title:'east,',
		//	applyto:'east',
			width:'100'
		},{
			id:'centre',
			region:'center',
			title:'centre_test'
		//	applyto:'centre'
		}]
		
	})
});

