//检查提案人，预计节约金额等字段是否合法
function checkFormValidate()
{
	if(document.getElementById('proposalPersonName').innerHTML=='无此人')
	{
		alert('无此提案人！，请检查')
		return false;	
	}
	var str=document.getElementById('estimatedSaving').value
	var cost1=document.getElementById('cur_cost').value
	var cost2=document.getElementById('ins_cost').value
	var quantity=document.getElementById('Annual_quantity').value
	 var  re=/^(-|\+)?\d+(\.\d+)?$/;   
        if(!re.test(str)){ 
	        alert("预计节约成本只能是浮点数！") 
	        return false; 
        }
         else if(!re.test(cost1)){ 
	        alert("现在方案成本只能是浮点数！") 
	        return false; 
        }  else if(!re.test(quantity)){ 
	        alert("年用量合计只能是浮点数！") 
	        return false; 
        } 
       var r1 = document.getElementById('r1')
       var r2 = document.getElementById('r2')
       if(r1.checked==false && r2.checked==false) {
       	 	alert("请选择类型！") 
	        return false;
       }
        return true;  
}
//保存
function tmpSave()
{
	var title=document.getElementById('proposaltitle').value
	if(title=='')
	{
		alert('主题不能为空') 
	}else{
		document.getElementById('item.state').value=0;
		if(checkFormValidate())
		{
			formSubmit();
		}
		
	}
}