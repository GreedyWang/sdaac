Ext.onReady(function() {
	Ext.QuickTips.init();// 提示link，如果a标签有title的话
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());// 以cookie的方式记住用户对面板的部署
	Ext.BLANK_IMAGE_URL = 'image/blank.gif'
	
	var projectGridStore;
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel([
			sm,
//				{
//				id : 'indexId',
//				header : "indexId",
//				dataIndex : 'indexId',
//				
//				width : 30
//				
//			}, 
				{
				id : 'name',
				header : "名称",
				dataIndex : 'name',
				width : 150
			}, {
			//	id : 'formula',
				header : "计算公式",
				dataIndex : 'formula',
				sortable : true,
				width : 150
			}, {
		//		id : 'type',
				header : '类型',
				dataIndex : 'type',
				sortable : true,
				width : 30
			}, {
		//		id : 'a',
				header : '70%',
				dataIndex : 'a',
				sortable : true,
				width : 50
			}, {
		//		id : 'b',
				header : '100%',
				dataIndex : 'b',
				sortable : true,
				width : 50
			}, {
		//		id : 'c',
				header : '150%',
				dataIndex : 'c',
				sortable : true,
				width : 50
			}])

	/**
	 * 子菜单
	 */
	var win = false;
	function search() {

		var uid = Ext.get('search_uid').dom.value
		projectGridStore.load({
					params : {
						empUid : uid
					},
					callback : function(r, options, success) {
						if (success) {
							Ext.Msg.alert('操作', '成功！');
						} else {
							Ext.Msg.alert('操作', '失败！');
						}
					}// callback function end
				});
	}

	/**
	 * 主菜单
	 */

	var btn1 = new Ext.form.DateField({
				id : 'year',
				format : 'Y'
			})
	// var label3 = new Ext.form.Label('员工姓名');
	var btn3 = new Ext.form.TextField({
				id : 'search_uid',
				fieldLabel : '员工姓名'
			})

	var btn4 = new Ext.form.TextField({
				id : 'btn4',
				fieldLabel : '指标名称'
			})

	function toExcel() {
		window.location.href = '/bpp/index.do?actionType=selectDepEmpsIndexs ';
	}

	var btn2 = new Ext.Button({
				text : "查询",
				iconCls : 'icon-grid',
				handler : search
			});
			
	var add = new Ext.Button({
				text : "Add",
			//	iconCls : 'icon-grid',
				handler : function(){
					var uid=document.getElementById('uid').value
					var items=sm.getSelections();
					if(items!=null){
						
						for(var i=0;i<items.length;i++){
							//alert(uid)
							//alert(items[i].get('indexId'))
							var temp={index:{id:items[i].get('indexId')},
									tempolyee:{uid:uid}
										}
							EmpIndexManager.doInsert(temp,function(data){})
						}	
						
							Ext.Msg.alert('操作', '成功！');
						
					}					
				}
			});

	this.toolbar = new Ext.Toolbar({
				items : ['员工工号', btn3, '指标名称', btn4, btn1, btn2]
			});

	function action(btn) {
		Ext.Msg.alert(btn.text)

	}

	function showUrl1(value) {
		return "<a class='x-btn' target='context' href='index.do?actionType=doSelectEmpIndex&empUid="
				+ value + "'>详细</a>";
	}

	

	projectGridStore = new Ext.data.JsonStore({
		autoLoad : true,
		// url:'employee.do?actionType=doSelectAllSalary',
		url : 'index.do?actionType=doSelectDepartMentIndexDatabase',
		fields : [{
					name : 'name',
					mapping : 'name'
				}, {
					name : 'formula',
					mapping : 'formula'
				},
				 {name: 'a', mapping : 'a'},
				  {name: 'b', mapping : 'b'},
				   {name: 'c', mapping : 'c'},
				    {name: 'type', mapping : 'type'},
				    {name: 'indexId', mapping : 'id'}]
			// sortInfo:{field: 'proposalNum', direction: "DESC"}
			// groupField:'departmentName'
		})


	

	var projectGrid = new Ext.grid.GridPanel({
		title : '统计列表',
		id : 'west2',
		region : 'center',
		tbar : toolbar,
		width : 600,
		height : 600,
		sm : sm,
		cm : cm,
		frame:true,
		 applyTo:'search',
		 buttons: [add],
        buttonAlign:'center',
		store : projectGridStore

			// plugins: summary,
			// view: new Ext.grid.GroupingView({
			// autoFill :false,
			// forceFit:false,
			// startCollapsed: true,
			// groupTextTpl: '{text} ({[values.rs.length]} {[ "个"]})'
			// })

			// plugins: expander

		})

})
