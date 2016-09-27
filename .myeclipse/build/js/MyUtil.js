
function createForm()
{
	var activeForm= document.createElement('FORM');
	document.body.appendChild(activeForm);
	activeForm.method="POST";
	return activeForm;
}
var index=0;
function Tadd(tableID)
{ 
  	var newTr = tableID.insertRow();
	var newTd0 = newTr.insertCell();	
	var newTd1 = newTr.insertCell();
	var newTd2 = newTr.insertCell();
	var newTd3 = newTr.insertCell();
	var newTd4 = newTr.insertCell();
	index=index+1;
	//newTr.='<form action="personal.do?actionType=doInsert" method="post" onsubmit="return checkBlank()">';
	newTd0.className='altbg1';
	newTd1.className='altbg1';
	newTd2.className='altbg1';
	newTd3.className='altbg1';
	
	newTd0.innerHTML= '<input name="teamWorkProject.jobContent['+index+']" size="40" /> '; 
	newTd1.innerHTML= '<input size="10" name="teamWorkProject.responsiblePerson['+index+']" />';
	newTd2.innerHTML= '<input size="10" name="planTiem" id="planTiem" onfocus="calShow('+'planTiem'+')"/>';
	newTd3.innerHTML= '<input size="10" name="finishTiem" id="finishTiem" onfocus="calShow(finishTiem)"/>';
	newTd4.innerHTML= '<img src="images/delete.gif" id="image"  onclick="del(this)"> ';
}

  function   del(o){   
  var t=document.getElementById('table1')   
  t.deleteRow(o.parentNode.parentNode.rowIndex)   
  } 
