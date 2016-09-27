Ext.onReady(function(){
	Ext.QuickTips.init();//提示link，如果a标签有title的话
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());//以cookie的方式记住用户对面板的部署
	Ext.BLANK_IMAGE_URL='image/blank.gif'
	
	var myData = [
        ['3m Co',71.72,0.02,0.03,'9/1 12:00am'],
        ['Alcoa Inc',29.01,0.42,1.47,'9/1 12:00am'],
        ['Altria Group Inc',83.81,0.28,0.34,'9/1 12:00am'],
        ['American Express Company',52.55,0.01,0.02,'9/1 12:00am'],
        ['American International Group, Inc.',64.13,0.31,0.49,'9/1 12:00am'],
        ['AT&T Inc.',31.61,-0.48,-1.54,'9/1 12:00am'],
        ['Boeing Co.',75.43,0.53,0.71,'9/1 12:00am'],
        ['Caterpillar Inc.',67.27,0.92,1.39,'9/1 12:00am'],
        ['Citigroup, Inc.',49.37,0.02,0.04,'9/1 12:00am'],
        ['E.I. du Pont de Nemours and Company',40.48,0.51,1.28,'9/1 12:00am'],
        ['Exxon Mobil Corp',68.1,-0.43,-0.64,'9/1 12:00am'],
        ['General Electric Company',34.14,-0.08,-0.23,'9/1 12:00am'],
        ['General Motors Corporation',30.27,1.09,3.74,'9/1 12:00am'],
        ['Hewlett-Packard Co.',36.53,-0.03,-0.08,'9/1 12:00am'],
        ['Honeywell Intl Inc',38.77,0.05,0.13,'9/1 12:00am'],
        ['Intel Corporation',19.88,0.31,1.58,'9/1 12:00am'],
        ['International Business Machines',81.41,0.44,0.54,'9/1 12:00am'],
        ['Johnson & Johnson',64.72,0.06,0.09,'9/1 12:00am'],
        ['JP Morgan & Chase & Co',45.73,0.07,0.15,'9/1 12:00am'],
        ['McDonald\'s Corporation',36.76,0.86,2.40,'9/1 12:00am'],
        ['Merck & Co., Inc.',40.96,0.41,1.01,'9/1 12:00am'],
        ['Microsoft Corporation',25.84,0.14,0.54,'9/1 12:00am'],
        ['Pfizer Inc',27.96,0.4,1.45,'9/1 12:00am'],
        ['The Coca-Cola Company',45.07,0.26,0.58,'9/1 12:00am'],
        ['The Home Depot, Inc.',34.64,0.35,1.02,'9/1 12:00am'],
        ['The Procter & Gamble Company',61.91,0.01,0.02,'9/1 12:00am'],
        ['United Technologies Corporation',63.26,0.55,0.88,'9/1 12:00am'],
        ['Verizon Communications',35.57,0.39,1.11,'9/1 12:00am'],
        ['Wal-Mart Stores, Inc.',45.45,0.73,1.63,'9/1 12:00am']
    ];
	//1079 用边关替代
    function showUrl1(value)
	{
		
	  return '<a  class="x-btn" target="main" href="admin/UserAdd.jsp">详细信息</a>';
	
	}
	
	var projectGridStore=new Ext.data.JsonStore({
		url:'',		
    	fields:['id']
	})
	
	var store = new Ext.data.SimpleStore({
        fields: [
         'id',
           {name: 'price', type: 'float'},
           {name: 'change', type: 'float'},
           {name: 'pctChange', type: 'float'}
          
        ]
    }); 
    store.loadData(myData);
		  // row expander
    var expander = new Ext.grid.RowExpander({
        tpl : new Ext.Template(
            '<p><b>Company:</b> {id}</p><br>'
          //  '<p><b>Summary:</b> {desc}</p>'
        )
    });
    
	var cm=new Ext.grid.ColumnModel([
		 expander,
		 {
		 id:'id',
         header: "项目名称",
         dataIndex: 'id',
         renderer:showUrl1,
         width: 50
		},
		{
		 id:'price',
         header: "price",
         dataIndex: 'price',
         width: 20
		}
	])	
    
	var projectGrid=new Ext.grid.GridPanel({
		title:'项目列表',
		id:'west2',
		region:'center',
		width:'250',
		cm:cm,
		store:store,	
		plugins: expander
	})
		
	var rightP=new Ext.Panel({
		id:'centre2',
		region:'east',
		title:'项目内容',
		width:'800',
		collapsible:true,
 		autoScroll:true,
		split:true,
		html:'<iframe frameBorder="0" id="main" name="main" scrolling="auto" height="100%" width="100%"></iframe>'
		//html:'<iframe  scrolling="auto" frameborder="0" width="100%" height="100%" src="left.jsp"></iframe>'
	})
		
	var rightPanel=new Ext.Panel({
			id:'centre',
			region:'center',
			
			width:'300',
			title:'项目管理',
			layout:'border',
			items:[projectGrid,rightP]
			
		
	})
	
	new Ext.Viewport({
		layout:'border',
		width:'1000',
		
		items:[{
			id:'west',
			collapsible:true,
	 		autoScroll:true,
			split:true,
			region:'west',
			title:'west,',
			width:'200'
		
		},rightPanel]
	})
})

  