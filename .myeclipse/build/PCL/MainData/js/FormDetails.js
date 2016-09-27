function init() {
	//反应速度类型
	var hourType = '${rs.feedbackType }' 
	if(hourType == 1) {
		document.getElementById('24').selected = true;
	}else if(hourType == 2) {
		document.getElementById('48').selected = true;
	}else if(hourType == 3) {
		document.getElementById('49').selected = true;
	}
	//物料号类型
	var isCreate = '${rs.isCreate }' 
	if(isCreate == 1) {
		document.getElementById('new').selected = true;
	}else if(isCreate == 2) {
		document.getElementById('update').selected = true;
	}
	//selfMade
	var selfMade = '${rs.selfMade }' 
	if(selfMade == 1) {
		document.getElementById('selfMade').checked = true;
	}
}