var index = 0
function checkUid(elem, indexNum) {
	// alert(indexNum)
	var uid = elem.value;

	EmpManager.doSelectById(uid, function(data) {

				document.getElementById('uname[' + indexNum + ']').value = data;
			});
}

function checkPid(elem, indexNum) {

	var pid = elem.value;

	if (pid != null && pid != "") {
		PostManager.doSelectById(pid, function(data) {
			if (data != null) {
				document.getElementById('productName[' + indexNum + ']').value = data.productName;
			}else{
				document.getElementById('productName[' + indexNum + ']').value ='还未添加名称';
			}
		});
	}
}

function checkBlank(index) {
	var uid = document.getElementById('items[' + index + '].tempolyee.uid').value;
	var uname = document.getElementById('uname[' + index + ']').value;
	var postid = document.getElementById('items[' + index + '].tpost.id').value;
	var productName = document.getElementById('productName[' + index + ']').value;
	var worktime = document.getElementById('items[' + index + '].worktime').value;
	var output = document.getElementById('items[' + index + '].output').value;
	var masterid = document.getElementById('masterid').value;
	var masterName = document.getElementById('masterName').value;

	if (uid == "") {
		alert('员工号不能为空');
		return false;
	}
	if (uname == "[]") {
		alert('没有此员工');
		return false;
	}
	if (postid == "") {
		alert('岗位号不能为空');
		return false;
	}
	if (productName == "") {
		alert('没有此岗位');
		return false;
	}
	if (worktime == "") {
		alert('工作时间不能为空');
		return false;
	}
	if (output == "") {
		alert('产出不能为空');
		return false;
	}
	if (masterid == "") {
		alert('组长不能为空');
		return false;
	}
	if (masterName == "[]") {
		alert('没有此组长');
		return false;
	}
	return true;
}

function Tadd() {
	var table3 = document.getElementById('table3');
	// index=table3.rows.length-4;
	// alert(index)
	var flag = checkBlank(index);
	if (flag) {
		var newTr = table3.insertRow();
		var newTd0 = newTr.insertCell();
		var newTd1 = newTr.insertCell();
		var newTd2 = newTr.insertCell();
		var newTd3 = newTr.insertCell();
		var newTd4 = newTr.insertCell();
		var newTd5 = newTr.insertCell();
		var newTd6 = newTr.insertCell();

		index = index + 1;

		newTd0.className = 'altbg1';
		newTd1.className = 'altbg1';
		newTd2.className = 'altbg1';
		newTd3.className = 'altbg1';
		newTd4.className = 'altbg1';
		newTd5.className = 'altbg1';
		newTd0.align = "center";
		newTd1.align = "center";
		newTd2.align = "center";
		newTd3.align = "center";
		newTd4.align = "center";
		newTd5.align = "center";
		newTd6.innerHTML = 'altbg1';
		newTd0.innerHTML = '<input name="items[' + index
				+ '].tempolyee.uid" id="items[' + index
				+ '].tempolyee.uid" onblur="checkUid(this,' + index
				+ ')" size="5" /> ';
		newTd1.innerHTML = '<input  id="uname[' + index
				+ ']" disabled="disabled" size="5"/>';
		newTd2.innerHTML = '<input name="items['
				+ index
				+ '].tpost.id" id="items['
				+ index
				+ '].tpost.id"  onfocus="showToolBar(this)"   onblur="checkPid(this,'
				+ index + ')" size="25" />';
		newTd3.innerHTML = '<input id="productName[' + index
				+ ']" disabled="disabled" size="25" />';
		newTd4.innerHTML = '<input name="items[' + index
				+ '].worktime" id="items[' + index + '].worktime" size="5" />';
		newTd5.innerHTML = '<input name="items[' + index
				+ '].output" id="items[' + index + '].output" size="5"/>';
		newTd6.innerHTML = '<img src="images/delete.gif" id="image"  onclick="Tdelete(this)">';
	}
}
function Tdelete(o) {
	var t = document.getElementById('table3')
	t.deleteRow(o.parentNode.parentNode.rowIndex)
	// index=index-1;
}

function checkMasterUid() {
	var value = document.getElementById('masterid').value;
	EmpManager.doSelectById(value, function(data) {
				document.getElementById('masterName').value = data;
			});
	EmpManager.selectTeamByLead(value,function(data){
			    var sel=document.getElementById('teamName');
			    for(var i=0;i<data.length;i++){
			    	var opt=document.createElement('Option');
	 				opt.innerText = data[i].teamName
					opt.value = data[i].id   	
  					sel.appendChild(opt)
			    }
		})
}

function copyData(elem) {
	var fristName = document.getElementById('firstName').value;
	var midName = document.getElementById('midName').value;
	var lastName = document.getElementById('lastName').value;
	var postID = fristName + '_' + midName + '_' + lastName;

	elem.value = postID;
	// elem.focus();
}
/**
 * 清空
 * 
 * @param {}
 *            zone
 */
function clearData() {
	document.getElementById('firstName').length = 0;
	document.getElementById('lastName').length = 0;
}
/**
 * 载入select1,2数据
 */
function loadData(zone) {
	var sel1 = document.getElementById('firstName')
	var sel2 = document.getElementById('lastName')

	bfManager.selectBounceFigure(zone, function(data) {
				// alert(data[0].figureId)
				for (var i = 0; i < data.length; i++) {
					var option = document.createElement('Option')
					option.value = data[i].figureId
					option.innerText = data[i].figureId
					sel1.appendChild(option)
				}
			})
	bpManager.selectAll(zone, function(data) {
				// alert(data[0].postId)
				for (var i = 0; i < data.length; i++) {
					var option = document.createElement('Option')
					option.value = data[i].postId
					option.innerText = data[i].postId
					sel2.appendChild(option)
				}
			})
}
function init() {
	var zone = document.getElementById('midName').value
	var type = document.getElementById('type')
	if(zone=='100')
	{
		type.value=3;
	}else if(zone=='104A'){
		type.value=2;
	}else if(zone=='103'){
		type.value=4;
	}else if(zone=='chukou'){
		type.value=5;
	}else if(zone=='101'){
		type.value=6;
	}
	clearData();
	loadData(zone)
}
function showToolBar(elem) {
	// document.getElementById('toolbar').style='block'
	copyData(elem)
	// document.getElementById('toolbar').style='none'
}

