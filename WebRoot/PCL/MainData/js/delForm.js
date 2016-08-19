// 删除PR单
function doDel(prFormId, state) {
	if (window.confirm('确认删除?')) {
		var p = {
			id : prFormId,
			state : state
		}
		prFormListManager.doDelete(p, function(data) {
					window.close();
				})
	}

}
