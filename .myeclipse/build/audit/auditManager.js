Ext.onReady(function() {
			new Ext.form.DateField({
						id : 'start',
						format : 'Y-m-d',
						applyTo : 'start'
					})

			new Ext.form.DateField({
						id : 'end',
						format : 'Y-m-d',
						applyTo : 'end'
					})

		})

function init(computerId) {
	auditDailyManager.selectAllUser(computerId, function(data) {

				DWRUtil.removeAllOptions('computer');
					DWRUtil.addOptions("computer", ["All"] );
				DWRUtil.addOptions("computer", data);

			})
}

function showItem(category) {

	auditDailyManager.selectItem(	document.getElementById('computer').value,category, function(data) {
				DWRUtil.removeAllOptions('item');
			
				DWRUtil.addOptions("item", ["All"] );
				DWRUtil.addOptions("item", data);
			})
}

function showResult(item){
		auditDailyManager.selectResult(	document.getElementById('computer').value,	document.getElementById('category').value,item, function(data) {
				DWRUtil.removeAllOptions('rs');
				document.getElementById('rs').size=data.length
			
				DWRUtil.addOptions("rs", data);
					
			})
}

function newWindow(url)
{
	     window.open(url,null,
"height=300,width=1000,status=yes,toolbar=no,menubar=no,location=no");
                      
}