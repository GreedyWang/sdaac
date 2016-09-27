	function checkFrom()
  	{
  		var flag=document.getElementById('productArea').value
  		if(flag==-1)
  		{
  			alert('请选择车间');
  			return false;
  		}else
  		{
  			return true;
  		}
  	}