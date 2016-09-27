  var index=0;

  
  function checkUid(elem,index)
  {
 
  var uid=elem.value;
  	// =document.getElementById('item.tempolyee.uid');?why can't get the value???!!
  
  	EmpManager.doSelectById(uid,function(data)
  	{
  
  		document.getElementById('uname['+index+']').value=data;
  	});
  }

 
  function checkBlank(index)
  {
	var uid=document.getElementById('items['+index+'].tempolyee.uid').value;
	var uname=document.getElementById('uname['+index+']').value;
	var postid=document.getElementById('items['+index+'].tpost.id').value;
	var productName=document.getElementById('productName['+index+']').value;
	var worktime=document.getElementById('items['+index+'].worktime').value;
	var output=document.getElementById('items['+index+'].output').value;
	var masterid=document.getElementById('masterid').value;

var masterName=document.getElementById('masterName').value;
  if(uid=="")
  	{
  		alert('员工号不能为空');
  		return false;
	}
		if(uname=="[]")
  	{
  		alert('没有此员工');
  		return false;
	}	if(postid=="")
  	{
  		alert('岗位号不能为空');
  		return false;
	}	if(productName=="")
  	{
  		alert('没有此岗位');
  		return false;
	}	if(worktime=="")
  	{
  		alert('工作时间不能为空');
  		return false;
	}	if(output=="")
  	{
  		alert('产出不能为空');
  		return false;
	}
	if(masterid==""){
		alert('组长不能为空');
		return false;
	}
	if(masterName=="[]"){
		alert('没有此组长');
		return false;
	}
	return true;
  }
  
  function changeColor(color,title)
  {
  
  	var tb=document.getElementById('table3'); 	
  	tb.style.background=color;
  	var head=document.getElementById('head'); 	
  	head.style.background=color;
  	head.innerHTML=title;	
  }
  




  function createForm()
  {

	var activeForm= document.createElement('FORM');
	document.body.appendChild(activeForm);
	activeForm.method="POST";
	return activeForm;
  }
  
  
  function Tadd()
  {
  	 
  	var flag= checkBlank(index);
  	if(flag){
  	// var submitForm = createForm();
  
  	var newTr = document.getElementById('table3').insertRow();

	var newTd0 = newTr.insertCell();	
	var newTd1 = newTr.insertCell();
	var newTd2 = newTr.insertCell();
	var newTd3 = newTr.insertCell();
	var newTd4 = newTr.insertCell();
	var newTd5 = newTr.insertCell();
	var newTd6 = newTr.insertCell();
	index=index+1;

//设置列内容和属性
//newTr.='<form action="personal.do?actionType=doInsert" method="post" onsubmit="return checkBlank()">';
newTd0.className='altbg1';
newTd1.className='altbg1';
newTd2.className='altbg1';
newTd3.className='altbg1';
newTd4.className='altbg1';
newTd5.className='altbg1';
newTd6.innerHTML='altbg1';
newTd0.innerHTML= '<input name="items['+index+'].tempolyee.uid" id="items['+index+'].tempolyee.uid" onblur="checkUid(this,'+index+')"/> '; 
newTd1.innerHTML= '<input  id="uname['+index+']" disabled="disabled" />';
newTd2.innerHTML= '<input name="items['+index+'].tpost.id" id="items['+index+'].tpost.id" size="25" onblur="checkPid(this,'+ index+')" onclick="copyData(this)"/>';
newTd3.innerHTML= '<input id="productName['+index+']" disabled="disabled" />';
newTd4.innerHTML= '<input name="items['+index+'].worktime" id="items['+index+'].worktime" class="worktime" />';
newTd5.innerHTML= '<input name="items['+index+'].output" id="items['+index+'].output" class="output"  />';
newTd6.innerHTML= '<img src="images/delete.gif" id="image"  onclick="Tdelete(this)"> ';
}
  }

 function checkMasterUid()
  {
 	var value=document.getElementById('masterid').value; 	
  		EmpManager.doSelectById(value,function(data)
  	{
  		document.getElementById('masterName').value=data;
  	});
  }



	function selectMidName()
	{
		var firstName=document.getElementById('firstName').value;
		PostIDManager.selectMidName(firstName,function(data){
			DWRUtil.removeAllOptions('midName');
			DWRUtil.addOptions("midName", data); 
		});
	}
	
	

	function selectLastName()
	{
		PostIDManager.selectLastName(DWRUtil.getValue('firstName'),DWRUtil.getValue('midName'),function(data){
			DWRUtil.removeAllOptions('lastName');
			DWRUtil.addOptions("lastName", data); 
		});
	}
	
	
	function selectFitstNameByTwo()
	{
		
		PostIDManager.selectFitstNameByTwo(DWRUtil.getValue('firstName_two'),function(data){
			DWRUtil.removeAllOptions('firstName');
			DWRUtil.addOptions("firstName", data); 		
		});
	}


function cacl()
{
	
	
	document.getElementById('caclPostID').style.display='block';
	
}
  
  function copyData(elem)
  {
  	var fristName=document.getElementById('firstName').value;
  	var midName=document.getElementById('midName').value;
  	var lastName=document.getElementById('lastName').value;
  	var postID=fristName+'_'+midName+'_'+lastName; 	
  	
  	elem.value=postID;
  		//document.getElementById('items['+index+'].tpost.id').focus();
  }
  
  
  
/**
 * 添加一行并加入信息
 */
function TaddByInfo(uid,name,postid,postName,workTime,workOutput) {

	//var flag = checkBlank(index);
	//if (flag) {
		//var submitForm = createForm();

		var newTr = document.getElementById('table3').insertRow();

		var newTd0 = newTr.insertCell();
		var newTd1 = newTr.insertCell();
		var newTd2 = newTr.insertCell();
		var newTd3 = newTr.insertCell();
		var newTd4 = newTr.insertCell();
		var newTd5 = newTr.insertCell();
		var newTd6 = newTr.insertCell();
		index = index + 1;

		// 设置列内容和属性
		// newTr.='<form action="personal.do?actionType=doInsert" method="post"
		// onsubmit="return checkBlank()">';
		newTd0.className = 'altbg1';
		newTd1.className = 'altbg1';
		newTd2.className = 'altbg1';
		newTd3.className = 'altbg1';
		newTd4.className = 'altbg1';
		newTd5.className = 'altbg1';
		newTd6.innerHTML = 'altbg1';
		newTd0.innerHTML = '<input name="items[' + index
				+ '].tempolyee.uid" id="items[' + index
				+ '].tempolyee.uid" onblur="checkUid(this, '+ index+')" value="'+uid+'"   /> ';
				
		newTd1.innerHTML = '<input  id="uname[' + index
				+ ']" disabled="disabled"value="'+name+'" />';

		newTd2.innerHTML = '<input name="items[' + index
				+ '].tpost.id" id="items[' + index
				+ '].tpost.id" size="25" onblur="checkPid(this, '+ index+')" onclick="copyData(this)"  value="'+postid+'"/>';
				
		newTd3.innerHTML = '<input id="productName[' + index
				+ ']" disabled="disabled" value="'+postName+'"/>';
				
		newTd4.innerHTML = '<input name="items[' + index
				+ '].worktime" id="items[' + index + '].worktime" class="worktime" value="'+workTime+'"/>';
				
		newTd5.innerHTML = '<input name="items[' + index
				+ '].output" id="items[' + index + '].output" class="output" value="'+workOutput+'" />';
				
		newTd6.innerHTML = '<img src="images/delete.gif" id="image"  onclick="Tdelete(this)"> ';
	//}
}

 //导入上次这个组长的日产量信息
  function lastInfo(){
  	var teamLeader = document.getElementById('masterid')
  	if( teamLeader!=null)
  	{
	  	PPManager.getLast(teamLeader.value,function(data){
	  		if(data!=null){
	  			for(var i=0;i<data.length;i++){			
	  			//	alert(data[i].tempolyee.uid+data[i].tempolyee.name+data[i].tpost.id+data[i].tpost.productName+data[i].worktime+data[i].output);
	  				TaddByInfo(data[i].tempolyee.uid,data[i].tempolyee.name,data[i].tpost.id,data[i].tpost.productName,data[i].worktime,data[i].output);
	  			}
	  		}
	  	})
  	}else{
  		alert('请输入组长编号!')
  	}
  }

 //删除table3行
  function Tdelete(o) {
	var t = document.getElementById('table3')
	t.deleteRow(o.parentNode.parentNode.rowIndex)
	// index=index-1;
}

//檢查崗位名稱
    function checkPid(elem,pIndex)
  {
  	var uid=elem.value;	 	
  	if(uid!=null&&uid!=""){
  	PostManager.doSelectById(uid,function(data)
  	{
  		if(data!=null){
  		document.getElementById('productName['+pIndex+']').value=data.productName;
  		}
  	});}
  }
