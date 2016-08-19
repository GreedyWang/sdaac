
function createForm()
{
	var activeForm= document.createElement('FORM');
	document.body.appendChild(activeForm);
	activeForm.method="POST";
	return activeForm;
}
function Tadd()
{ 
  //	var flag= checkBlank(index);
  //	if(flag){
  //	var submitForm = createForm();
 	var  table1=document.getElementById('table1');
 
  	var newTr = table1.insertRow();
	var newTd0 = newTr.insertCell();	
	var newTd1 = newTr.insertCell();
	var newTd2 = newTr.insertCell();
	var newTd3 = newTr.insertCell();
	var newTd4 = newTr.insertCell();
	var newTd5 = newTr.insertCell();
	var newTd6 = newTr.insertCell();
	var newTd7 = newTr.insertCell();
	
	var index=document.getElementById('table1').rows.length;
	index=index+1;	
	newTd0.innerHTML= '<input class="required" type="text" size="10" name="themes['+index+'].partsName" />'; 
	newTd1.innerHTML= '<input class="required" type="text" size="10" id="themes['+index+'].partsId" name="themes['+index+'].partsId" />';
	
	newTd2.innerHTML= '<select class="required" id="themes['+index+'].customer" name="themes['+index+'].customer" onblur="showOthers('+index+')" onfocus="getInfo('+index+')"></select>';
	
	newTd3.innerHTML= '<select class="required" id="select['+index+']"  name="themes['+index+'].applicableModels" onblur="show2('+index+')"></select>';
	
	newTd4.innerHTML= '<input class="required" type="text" style="text-align: right" size="10" id="themes['+index+'].systemAnnualOutputs" name="themes['+index+'].systemAnnualOutputs" />'; 
	newTd5.innerHTML= '<input class="required" type="text" style="text-align: right" size="10" id="themes['+index+'].partsAnnualOutputs" name="themes['+index+'].partsAnnualOutputs" />';

	newTd6.innerHTML= '<select class="required"  name="themes['+index+'].company" /><option value="件/台/套">'+'件/台/套'+'</option><option value="kg">'+'kg'+'</option></select>';
						
	newTd7.innerHTML= '<img src="images/delete.gif" id="image"  onclick="del(this)"> ';
}
function showOthers(indexID)
{
	//alert('themes['+indexID+'].partsId');
	var target='themes['+indexID+'].customer';
	var partsId=document.getElementById(target).value;
	//alert(partsId);
	vaveInfoManager.doSelectVehicleByCustomCode(partsId,function(data){
		addslt(data,indexID);
	});
}

function show2(indexID)
{	
	var target='themes['+indexID+'].customer';
	var customerID=document.getElementById(target).value;
	var target2='select['+indexID+']';
	var vehicle=document.getElementById(target2).value;
//	alert(customerID);
//	alert(vehicle);
	vaveInfoManager.doSelectVolumeByPK(customerID,vehicle,function(data){
		var target3='themes['+indexID+'].systemAnnualOutputs';
			var target4='themes['+indexID+'].partsAnnualOutputs';		//themes['+index+'].systemAnnualOutputs
		document.getElementById(target3).value=data;
		document.getElementById(target4).value=data;
		var Annual_quantity=document.getElementById('Annual_quantity').value;
		var sum=parseFloat(data)+parseFloat(Annual_quantity);
		//alert(sum)
		document.getElementById('Annual_quantity').value=sum;
	})
}

function getInfo(indexID)
{
	//var target='customID'
	//var costomID=document.getElementById(target).value
	//alert(costomID)
	var target2='themes['+indexID+'].customer';
	var select=document.getElementById(target2);
	select.length=0
	//document.getElementById(target2).value=costomID;
	vaveInfoManager.doSelectCustomCode(function(data){
		for(var i=0;i<data.length;i++)
		{
			var opt=document.createElement('Option');
	 		opt.innerText = data[i]
			opt.value = data[i]    
			select.appendChild(opt);
		}
	});
	
}


  function   del(o){   
  var t=document.getElementById('table1')   
  t.deleteRow(o.parentNode.parentNode.rowIndex)   
  } 
  
  function   addslt(array,indexID)
  {  
	  var select=document.getElementById('select['+indexID+']');  
	 	select.length=0
	 	for(var i=0;i<array.length;i++)
	 	{
	 		var opt=document.createElement('Option');
	 		opt.innerText = array[i]
			opt.value = array[i]    
			select.appendChild(opt);
 		} 
 }
  
	function ccc()
	{
		var index=document.getElementById('table1').rows.length;
		var sum=0;
		for(var i=0;i<index;i++)
		{ 
			var target='themes['+i+'].systemAnnualOutputs'
			sum+=document.getElementById(target).value			
		}
		alert(sum)
	}	
	
	  
