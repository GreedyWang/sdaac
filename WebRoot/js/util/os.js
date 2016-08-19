  $(document).ready(function(){
	
  		beginShow();
  		osManager.getName(function(data){
  			var select=document.getElementById('under')
  			for(var i=0;i<data.length;i++)
  			{
	  			var option=document.createElement('Option')
	  			option.value=data[i][0]
	  			option.text=data[i][1]
	  			select.options.add(option)
  			}
  		})
  		

  })
  
  function beginShow()
  {
  			var context;
  		osManager.getAll(function(data){
  			context=data;
  			$.each(context,function(i,n){
  				
  						 createDiv(n);	
  			})
  		})
  }
  	function createDiv(n)
  	{ 		
  		var aaa="<div onclick='show(this)'  id="+n.id+" style='position: absolute; left:"+n.postionAx+';top:'+n.postionAy+"; width:100px;height: 50px; border: 1px solid; border-color: black;'"  +'>'+ n.context +'<br>'+n.uname+ '</div> '
  				
  					if(n.fatherid!=0)
  					{
 
  					var fx=  document.getElementById(n.fatherid).style.left
  					var fy= document.getElementById(n.fatherid).style.top

  				
  					fx=parseInt(fx.substring(0,fx.length-2))+50
  						
  					fy=parseInt(fy.substring(0,fy.length-2))
  			
  					var liney=50+fy;
  					var lineHeight=n.postionAy-50-fy
  					var rowWidth=n.postionAx-fx
  					var row
  					var line="<div style='position: absolute; left:"+fx+'px;top:'+liney+'px; width:1px;height:'+lineHeight+"px; border: 1px ;background-color: black;'"  +'></div> '
  					if(rowWidth<0)
  					{
  						 rowWidth=Math.abs( n.postionAx-fx)-100
  						 fx=fx-rowWidth
  						 row="<div style='position: absolute; left:"+fx+'px;top:'+n.postionAy+'px; width:'+rowWidth+'px;height:'+1+"px; background-color: black; overflow: hidden  '"  +'></div> '
  						 }else{
  						 row="<div style='position: absolute; left:"+fx+'px;top:'+n.postionAy+'px; width:'+rowWidth+"px;height:1px; background-color: black;overflow: hidden'"  +'></div> '
  					}
  					
  					$('body').append(line);
  					$('body').append(row);
  					
  					}
  					$('body').append(aaa);
  	}
  	function getDetails()
  	{
  		var x=document.getElementById('x').value
  		var y=document.getElementById('y').value
  		var context=document.getElementById('context').value
  		var uname=document.getElementById('uname').value
  		var fatherid=document.getElementById('under').value
  		var divID=document.getElementById('divId').value
  		var n={ 	
  			   id:divID,
  		       context:context,
		   	   uname:uname,
		       type:'',
		       postionAx:x,
		       postionAy:y,
		       fatherid:fatherid
  		}
  		return n;
  	}
  	function create()
  	{
  		var n=getDetails();
  		
  		osManager.add(n,function(data){
  				n.id=data;
  				createDiv(n); 		
  		});
  	}
  	function update()
  	{
  		var n=getDetails();
  		//更新当前对象的位置
  		
  		osManager.update(n,function(data){
  			if(data){alert('success!')}
  			else{alert('fail!')}
  		});
  		window.location.reload();
  	}
  	
 	function show(elem)
 	{
 		var contexts=elem.innerHTML.split('<BR>')
 		var xx=elem.style.left
 		var yy=elem.style.top
 		document.getElementById('divId').value=elem.id;
 		var x=document.getElementById('x').value=xx.substring(0,xx.length-2)
  		var y=document.getElementById('y').value=yy.substring(0,yy.length-2)
  		var context=document.getElementById('context').value=contexts[0]
  		var uname=document.getElementById('uname').value=contexts[1]
  		//var fatherid=document.getElementById('under').value=
 	}
 	
