/**
 * 读取PRproject数据
 */
function loadProjectData() {
	prFormListManager.doSelectAllProject(function(data) {
				projectStore.loadData(data);
			});
}

var projectStore = new Ext.data.JsonStore({
			fields : ['number', 'context', 'sapNo', 'id','arno','managerUid']
		});

// loadProjectData();

/**
 * 读取成本中心数据
 */
function loadCostcentreData() {
	prFormListManager.doSelectAllCostcentre(function(data) {
				costcentreStore.loadData(data);
			});
}

function loadCostcentreData2(area) {
	prFormListManager.doSelectAllCostcentre2(area,function(data) {
		costcentreStore.clearFilter()
				costcentreStore.loadData(data);
			});
}

var costcentreStore = new Ext.data.JsonStore({
			// url : '',
			fields : ['costCenterName', 'costCenterNameEnglish', 'remark', 'id','owner','io','departId']
		})

/**
 * 读取总账科目数据
 */
function loadSubjecetData() {
	prFormListManager.doSelectAllSubjecet(function(data) {
				subjecetStore.loadData(data);
			});
}

function loadSubjecetData2(area) {

	prFormListManager.doSelectAllSubjecet2('DT02',function(data) {
				subjecetStore.loadData(data);
			});
}

var subjecetStore = new Ext.data.JsonStore({
			// url : '',
			fields : ['number', 'descrption', 'remark','id']
		})

/**
 * buyerList
 */
var buyerList = new Ext.data.JsonStore({
			// data:data,
			fields : ['uuid', 'buyId']
		})
		
function loadBuyerData() {
	prFormListManager.doSelectBuyers(function(data) {
				buyerList.loadData(data);
			});
}
function loadBuyerData2(area) {
	prFormListManager.doSelectBuyers2(area,function(data) {
				buyerList.loadData(data);
			
			});

}

//--------------------------------
var isCapitalStore = new Ext.data.JsonStore({
			fields : ['name', 'value']
		});
var capitalData =[{'name':'capital','value':0},{'name':'expense','value':1}];
	isCapitalStore.loadData(capitalData);	
	
var dptStore = new Ext.data.JsonStore({
			fields : ['dprt']
		});	
function loadAllDprt(area){
		prFormListManager.doSelectAllDprt(area,function(data){
		dptStore.loadData(data);
});
}	
		
var kindStore = new Ext.data.JsonStore({
			fields : ['kind']
		});
		
PuyerListBizManager.doSelectAllKind(function(data){
	kindStore.loadData(data);
});
			
var categoryStore = new Ext.data.JsonStore({
			fields : ['categroy']
		});

				
var capitalCategroyStore = new Ext.data.JsonStore({
			fields : ['name', 'value']
		});	
var capitalCategroyData =[{'name':'PTC','value':2},{'name':'HVAC','value':1},{'name':'IT','value':3},{'name':'NP','value':4},{'name':'Engineering','value':5}];
		capitalCategroyStore.loadData(capitalCategroyData);
		
var issueforStore = new Ext.data.JsonStore({
			fields : ['name', 'value']
		});
		var issuefordata = [{'name':'SH Plant','value':4},{'name':'SY Plant','value':2},{'name':'YT Plant','value':3}];
		issueforStore.loadData(issuefordata);	
			
var isPlanStore = new Ext.data.JsonStore({
			fields : ['name', 'value']
		});
var planData =[{'name':'In Budget','value':0},{'name':'out of Budget','value':1}];
		isPlanStore.loadData(planData);
				
var supplierStore = new Ext.data.JsonStore({
			fields : ['supplyName']
		});	
	
//prFormListManager.doSelectAllSuppliesInTheSap(function(data){
//		supplierStore.loadData(data);
//});														