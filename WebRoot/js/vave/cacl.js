
function cacl()
{
	var costs=document.getElementsByName('oneTimeCosts');
	var sum=0;
	for(var i=0; i<costs.length;i++)
	{
		//alert(costs[i].value)
		if(costs[i].value=="")
		{
			
		}
		else
		{
			sum+=parseFloat(costs[i].value);
		}
		
	}
	
	document.getElementById('total_onceTimeCosts').value=sum;        //一次性花费
	
	var cur_cost=document.getElementById('cur_cost').value;
		
	var ins_cost=document.getElementById('ins_cost').value;
		
	var Annual_quantity=document.getElementById('Annual_quantity').value;
	
	var distribution_Programs=document.getElementById('distribution_Program').value;
	//alert(distribution_Programs)
	var distribution_Program=distribution_Programs.split('/')[1]
	//alert(distribution_Program)
	var result=(cur_cost-ins_cost)*Annual_quantity;
	document.getElementById('total_saving').value=result;         //总节约成本
	document.getElementById('net_SavingCost').value=result-sum;  //净节约成本
	document.getElementById('sdaacnet_SavingCost').value=(result-sum)*distribution_Program/100;//sdaac净节约成本
	document.getElementById('estimatedSaving').value=(result-sum)*distribution_Program/100;//估计节约成本
}
function doInsert(proposalID)
{
	//得到也面元素值
	var items=document.getElementsByName('item');
	var cycles=document.getElementsByName('cycle');
	var progresses=document.getElementsByName('progress');
	var oneTimeCosts=document.getElementsByName('oneTimeCosts');
	var projects=document.getElementsByName('project');
	var quantity=document.getElementsByName('quantity');
	var unit=document.getElementsByName('unit');
	//把上面的值 转换成数组
	var Vitems=new Array(6);
	var Vcycles=new Array(6);
	var Vprogresses=new Array(6);
	var VoneTimeCosts=new Array(6);
	var Vprojects=new Array(6);
	var Vquantity=new Array(6);
	var Vunit=new Array(6);
	for(var i=0;i<items.length;i++)
	{
		Vitems[i]=items[i].value;
		Vcycles[i]=cycles.value;
		 Vprogresses[i]=progresses[i].value;
		VoneTimeCosts[i]=oneTimeCosts[i].value;
		Vprojects[i]=projects[i].value;
		 Vquantity[i]=quantity[i].value;
		 Vunit[i]=unit[i].value;
	}

	
	//projectSchedule
	//public void doInsert(String[] items,Float[] cycles,Float[] progresses,Float[] oneTimeCosts,String proposalID)
	statisticsSchedule.doInsert(Vitems,Vcycles,Vprogresses,VoneTimeCosts,proposalID,function(date){});	
	
}

