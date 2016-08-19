var index=0;
function delTask(id,elem)
  {
  	var key=window.confirm("确定删除");
  	if(key)
  	{  
	  	//var url='task.do?actionType=doDeleteMyTeamTask&teamWorkprojectID='+id;  
	  	//window.location.href=url;
	  	TaskManager.doDelete(id,function(data){});
	  	del(elem)
  	}
  }
  //add row----------------------------------
  //	var index=0
	function Tadd()
	{ 
	 //	alert(rowNum)
	  	var tableID=document.getElementById('table1');
	  	var newTr = tableID.insertRow();
		var newTd0 = newTr.insertCell();	
		var newTd1 = newTr.insertCell();
		var newTd2 = newTr.insertCell();
		var newTd3 = newTr.insertCell();
		var newTd4 = newTr.insertCell();
		var newTd5 = newTr.insertCell();
		var newTd6 = newTr.insertCell();
		var newTd7 = newTr.insertCell();
		var newTd8 = newTr.insertCell();

		//index=rowNum;
		newTd0.className='altbg1';
		newTd1.className='altbg1';
		newTd2.className='altbg1';
		newTd3.className='altbg1';
		newTd4.className='altbg1';
		newTd5.className='altbg1';
		newTd6.className='altbg1';
		newTd7.className='altbg1';
		newTd8.className='altbg1';

		
		newTd0.innerHTML= '';
		newTd1.innerHTML= '<select class="required" name="type" >'+
							'<option value="1.设计开发">1.设计开发</option>'+
							'<option value="2.供应商定点">2.供应商定点</option>'+
							'<option value="3.工装制造">3.工装制造</option>'+
							'<option value="4.样件制造">4.样件制造</option>'+
							'<option value="5.试验验证">5.试验验证</option>'+
							'<option value="6.顾客确认">6.顾客确认</option>'+
							'<option value="7.批量验证">7.批量验证</option>'+
							'<option value="8.产品/成本切换">8.产品/成本切换</option>'+
							'</select> ';						  				
		newTd2.innerHTML= '<input class="required" name="jobContent" size="40" /> '; 
		newTd3.innerHTML= '<input class="required" size="10" name="responsiblePerson" onblur="showName('+index+',this)"/>';
		newTd4.innerHTML= '<label size="10" id="name['+index+']" ></label>';
		newTd5.innerHTML= '<input class="required" size="10" name="myTime" id="planTiem" onfocus="calShow('+'planTiem'+')"/>';
		newTd6.innerHTML= '<input  size="10" name="finishTiem" id="finishTiem" onfocus="calShow(finishTiem)"/>';
		newTd7.innerHTML= '<input size="10" name="remarks" id="teamWorkProject['+index+'].remarks" />';
		newTd8.innerHTML= '<img src="images/del.gif" id="image"  onclick="del(this)"> ';
			index+=1;
	}
	function showName(index,resouce)
	{
			
		var target='name['+index+']';
		var elem=document.getElementById(target);
		
		EmpManager.doSelectById(resouce.value,function(data)
	  	{
	  		if(data!='[]')
	  		{
	  			elem.innerText=data;
	  		
	  		}else
	  		{
	  			elem.innerText='无此员工'
	  			resouce.value=''
	  		}
	  		
	  	});
		 
	}
	//row del-----------------------
	function   del(o)
	{   
	  var t=document.getElementById('table1')   
	  t.deleteRow(o.parentNode.parentNode.rowIndex)   
	} 
	function  updateMang (){
		   //刷新项目成员名单
			var tmp=new Object()		
			tmp.update=function(){
                    	var projectId2=document.getElementById('workTeamId').value
						var param={workTeamId:projectId2}	
						projectManager.doSelectByCond(param,function(data){	
						if(data[0].meneberNames!=null)
						{
							
							document.getElementById('projectManagerName').innerText=data[0].projectManager.name;
			 			}
						})
			}
			return tmp
	}
	
	
	//更变项目负责人
	function updateManager(projectId)
	{
		var html1="<iframe id='f1' frameBorder='0'  id='context' src='vave/newTask/changManager.jsp?projectId="+projectId +"'  scrolling='auto' height='100%' width='100%'></iframe>"
		openWind('更变项目负责人',html1,updateMang())
		
		
		
		
	}
	
	
		function  updateMenbs (){
		   //刷新项目成员名单
			var tmp=new Object()

			
			tmp.update=function(){
                    	var projectId2=document.getElementById('workTeamId').value
						var param={workTeamId:projectId2}	
						projectManager.doSelectByCond(param,function(data){	
						if(data[0].projectManager!=null)
						{
						
							var ss='项目成员： ';	
							for(var i=0;i<data[0].meneberNames.length;i++)
									{
										ss+=data[0].meneberNames[i]+','
									}
								document.getElementById('meneberNames').innerText=ss;
								
			 			}
						})
			}
			return tmp
	}
	//添加成员
	function addEmps(projectId)
	{
		var html2="<iframe id='f2' frameBorder='0'  id='context' src='vave/newTask/AddMembers.jsp?projectId="+projectId +"'  scrolling='auto' height='100%' width='100%'></iframe>"
		openWind('添加成员',html2,updateMenbs())
	}

	
	var  win=false;
	function openWind(title, html ,target  )
	{
		/** 添加成员*/
		if(!win){	
	 	  win = new Ext.Window({
                applyTo     : 'emps',
              
                width       : 600,
                height      : 400,
                closeAction :'hide',
                plain       : true,                            
                items       : new Ext.Panel({
									title:title,
							        width       : 600,
						            height      : 400,
						            bodyStyle:'padding:50px,5px,0',
									html:html
		}),
                buttons: [{
                    text     : '关闭',
                    handler  : function(){
             		   target.update()      
                        win.hide();
                    }

                }
                ]
            }); 
		}		
		win.show();
	}
	
	
	var  win2=false;
	function openWind2(title, html   )
	{
		/** 添加成员*/
		if(!win2){	
	 	  win2 = new Ext.Window({
                applyTo     : 'emps2',
              
                width       : 300,
                height      : 260,
            	closeAction :'hide',
                plain       : true,                            
                items       : new Ext.Panel({
									title:title,
							        width       : 300,
						            height      : 260,
						            bodyStyle:'padding:5px,5px,0',
									html:html
		})
             
            }); 
		}		
		win2.show();
	}
	
	