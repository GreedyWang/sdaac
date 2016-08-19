
function loadRejectSelContext() {
	var rjSel = document.getElementById("rejectType");
	if (rjSel.options.length <= 0) {
		rejectTypeManager.showRes(function (aaa) {
			var varItem = new Option("slecet one", -2);
			rjSel.options.add(varItem);
			for (var i = 0; i < aaa.length; i++) {
				var varItem = new Option(aaa[i].des, aaa[i].des);
				rjSel.options.add(varItem);
			}
		});
	}
}
function showRejectDlg() {
	loadRejectSelContext();
	document.getElementById("rejectType").disabled = false;
}
function hideRejectDlg() {
	document.getElementById("rejectType").value = -3;
	document.getElementById("rejectType").disabled = true;
}
function rejectDlg(elem) {
	if (elem.value == 0) {
		showRejectDlg();
	} else {
		hideRejectDlg();
	}
}
var isSaved = false;
function valify() {
	if (window.confirm("确认提交?")) {
		var suggId = document.getElementById("suggId");
		if (!isSaved) {
			if (suggId.value == 0) {
				var rjSel = document.getElementById("rejectType");
				if (rjSel.value == -2) {
					alert("Select one rejecet reason");
					return false;
				} else {
					isSaved = true;
					document.getElementById("aasx").submit();
					return true;
				}
			} else {
				isSaved = true;
				document.getElementById("aasx").submit();
			}
		}
		//document.getElementById('aasx').submit();
		return true;
	}
}
function valify2() {
	if (window.confirm("确认提交?")) {
		var suggId = document.getElementById("suggId");
		if (suggId.value == 0) {
			var rjSel = document.getElementById("rejectType");
			if (rjSel.value == -2) {
				alert("Select one rejecet reason");
				return false;
			} else {
				document.getElementById("aasx").submit();
				return true;
			}
		}
		document.getElementById("aasx").submit();
		return true;
	}
}

function valify3() {
		if(document.getElementById("totalCategroy3").value==""){
			alert('请选择GL');
			return false;
		}
	if (window.confirm("确认提交?")) {

		var suggId = document.getElementById("suggId");
		if (suggId.value == 0) {
			var rjSel = document.getElementById("rejectType");
			if (rjSel.value == -2) {
				alert("Select one rejecet reason");
				return false;
			} else {
				document.getElementById("aasx").submit();
				return true;
			}
		}
		document.getElementById("aasx").submit();
		return true;
	}
}

function valify4() {
		if(document.getElementById("prsn").value==""){
			alert('请填写SAP_SN');
			return false;
		}
	if (window.confirm("确认提交?")) {

		var suggId = document.getElementById("suggId");
		if (suggId.value == 0) {
			var rjSel = document.getElementById("rejectType");
			if (rjSel.value == -2) {
				alert("Select one rejecet reason");
				return false;
			} else {
				document.getElementById("aasx").submit();
				return true;
			}
		}
		document.getElementById("aasx").submit();
		return true;
	}
}

