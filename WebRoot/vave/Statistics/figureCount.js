
Ext.onReady(function(){
var storeF

	
storeF =new Ext.data.JsonStore({
	fields:['figureNo','saving','count_']
})
		proposalManager.countFigure(function(data){
		storeF.loadData(data)
	})
var cmF=new Ext.grid.ColumnModel([
	{
		 id:'figureNo',
         header: "车型",
         dataIndex: 'figureNo',
         width:100
	},	{
		 id:'saving',
         header: "总节约金额",
         dataIndex: 'saving',
         sortable :true,
         width:100
	},	{
		 id:'count_',
         header: "提案涉及数",
         dataIndex: 'count_',
           sortable :true,
         width:100
	}

])

new Ext.grid.GridPanel({
	id:'aa',
	title:'车型统计',
	store:storeF,
	cm:cmF,
	renderTo:'vvv',

	width:600,
    height:670


	
})








    

    
    
});

