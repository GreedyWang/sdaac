 	var wightSum=0;//权重和
  	var index=0;
 
 function check_Weight(tableID)
  {
	
  	var sum=0;
  	var target;

  	if(tableID=='table3')
  	{
  
  		target='Weight'
  	}
  	if(tableID=='table4')
  	{
  		target='Weight2'
  	}
  	var ele=table3.getElementsByTagName('input')
	  for(var i=0;i<ele.length;i++)
	  {
	  		var eleId=ele[i].id
		  	var rr=eleId.substring(0,6)		
		  
	  	if(rr==target)
	  	{
	  		if(ele[i].value!='')
	  		{
		  		sum+=parseInt(ele[i].value);
		  		
		  		if(sum>100)
		  		{
		  			alert('权重大于100！')
		  		
		  			break;
		  		}
	  		}
	  	}
	  }
	 // 	alert('!')
  		document.getElementById('totalWeight').innerText=sum;
  	//alert(   	document.getElementById('totalWeight')  )
  }


  /**
   * 新增行时检测weight》100
   * */
  

  function checkWightInNew(elem,index)
  {
  	if(elem.value!='')
  	{
  		document.getElementById('totalWeight').innerText=
  		parseInt(document.getElementById('totalWeight').innerText)+parseInt(elem.value) ;
  		
  		document.getElementById(index).value=parseInt(elem.value)
  	}
  }
  //当onclick时，减去现在的值
  function Tdelete(elem,cc)
  {
  	var t=document.getElementById('table3')   
  	t.deleteRow(elem.parentNode.parentNode.rowIndex)		
  	minus(document.getElementById(cc))
  }
  
  function minus(elem)
  {
  	
  	if(elem!=null&&elem.value!='')
  	{
	  	document.getElementById('totalWeight').innerText-=elem.value;	
  	}
  }
  function checkSubmit()
  {
 		 if(document.getElementById('totalWeight').innerText>100)
  		{	
  			alert('权重之和不能大于100');
  			return false;
  		}
  		return true;
  }
    /**
   * 检测指标类型，如果为非量化
   * @type Number
   */
  function checkType(attrib,elem)
  {
  		
  		if(attrib!='必选且权重不可变')
  		{
  			elem.readOnly=false;

  		}
  }
  
  function checkType2(attrib,elem)
  {
  	if(attrib=='非量化指标')
  		{
  			elem.readOnly=false;
  			elem.style.backgroundColor='white';
  		}else{
  			elem.readOnly=true;
  			elem.style.backgroundColor='gray';
  		}
  }
  
  //员工指标增加行
   function Tadd()
  {
  	
   // var index=document.getElementById('table3').rows.length-long;
  	var newTr = table3.insertRow();
	var newTd0 = newTr.insertCell();	
	var newTd1 = newTr.insertCell();
	var newTd2 = newTr.insertCell();
	var newTd3 = newTr.insertCell();
	var newTd4 = newTr.insertCell();
	var newTd5 = newTr.insertCell();
	var newTd6 = newTr.insertCell();
	var newTd7 = newTr.insertCell();
	var newTd8 = newTr.insertCell();
	var newTd9 = newTr.insertCell();
	var newTd10 = newTr.insertCell();
	
	newTd0.className='altbg1';
	newTd1.className='altbg1';
	newTd2.className='altbg1';
	newTd3.className='altbg1';
	newTd4.className='altbg1';
	newTd5.className='altbg1';
	newTd6.className='altbg1';
	newTd7.className='altbg1';
	newTd8.className='altbg1';
	newTd9.className='altbg1';
	newTd10.className='altbg1';
	var echkbox=document.createElement("input");   
	echkbox.setAttribute("type","hidden"); 
	echkbox.setAttribute("id",index);   
	document.body.appendChild(echkbox)
	newTd0.innerHTML= '<input class="required" name="items['+index+'].index.name" id="items['+index+'].name" size="20"/> '; 
	newTd1.innerHTML= '<input  name="items['+index+'].index.formula" id="items['+index+'].formula" size="20"/> '; 
	newTd2.innerHTML='<select class="required" name="items['+index+'].index.type"><option value="量化指标">量化指标</option><option value="非量化指标">非量化指标</option></select>';
	newTd3.innerHTML='<select class="required" name="items['+index+'].index.isChoice"><option value="必选">必选</option><option value="可选">可选</option><option value="必选且值不可变">必选且值不可变</option></select>';
	newTd4.innerHTML= '<input class="required" name="items['+index+'].index.a" id="items['+index+'].a" size="5"/>';
	newTd5.innerHTML= '<input class="required" name="items['+index+'].index.b" id="items['+index+'].b" size="5"/>';
	newTd6.innerHTML= '<input class="required" name="items['+index+'].index.c" id="items['+index+'].c" size="5"/>';
	newTd7.innerHTML= '<input name="items['+index+'].percentage" id="items['+index+'].percentage" onfocus="minus(this)" onblur="checkWightInNew(this,'+index+')" size="5"/>';
	newTd8.innerHTML= '<input name="items['+index+'].act_output" id="items['+index+'].act_output" size="5"/>';
	newTd10.innerHTML= '<img src="images/del.gif" id="image"  onclick="Tdelete(this,'+index+')" >';
	index=index+1;
  }
  
  //试运算
function testCal(a,b,c,output,index)
{
	var target = 'score['+index+']'
	var output=parseFloat(output.value) 
	var a=parseFloat(a) 
	var b=parseFloat(b) 
	var c=parseFloat(c) 

	if(a<b)//正向
	{
		
		if(a>output)
		{
			
			document.getElementById(target).innerText=0;
			return 
		}
		if(output>=a&&output<b)
		{
		
			var reslut=(30/(b-a))*output+(70-(30*a/(b-a)))
			document.getElementById(target).innerText=reslut;
			return 
		}
		if(output>=b&&output<c)
		{
			
			var reslut=(50/(c-b))*output+(100-(50*b/(c-b)))
			document.getElementById(target).innerText=reslut;
			
			return 
		}
		if(output>=c)
		{
		
			document.getElementById(target).innerText=150;
			return ;
		}
	}else{
	
		if(a<output)
		{
			
			document.getElementById(target).innerText=0;
			return 
		}
		if(output<=a&&output>b)
		{
		
			var reslut=(30/(b-a))*output+(70-(30*a/(b-a)))
			document.getElementById(target).innerText=reslut;
			return 
		}
		if(output<=b&&output>c)
		{
			
			var reslut=(50/(c-b))*output+(100-(50*b/(c-b)))
			document.getElementById(target).innerText=reslut;
			
			return 
		}
		if(output<=c)
		{
		
			document.getElementById(target).innerText=150;
			return ;
		}
	}
	
} 
  