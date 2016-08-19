function update(id, index) {

	var a = id;
	var b = document.getElementById('bfs[' + index + '].carType').value;
	var c = document.getElementById('bfs[' + index + '].standWorkTime').value;
	var d = document.getElementById('bfs[' + index + '].id').value;
	var e = document.getElementById('bfs[' + index + '].need').value;
	var f = document.getElementById('bfs[' + index + '].picesNum').value;
	var g = document.getElementById('bfs[' + index + '].remark').value;
	var t = document.getElementById('bfs[' + index + '].type').value;
	var h = document.getElementById('bfs[' + index + '].productArea').value;
	var p = {
		id : d,
		figureId : a,
		carType : b,
		productArea : h,
		standWorkTime : c,
		picesNum : f,
		need : e,
		remark : g,
		type : t
	}
	bfManager.updateByRemark(p, function(data) {
				alert('ok')
			})

}

function del(id, newProductArea,elem) {

	var bConfirmed = window.confirm("are you sure ");
	if (bConfirmed) {
		bfManager.del(id,newProductArea+'D', function(data) {
					var t = document.getElementById('table1')
					t.deleteRow(elem.parentNode.parentNode.rowIndex)
				})
	}

}