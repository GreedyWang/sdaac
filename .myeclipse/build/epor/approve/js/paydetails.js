 
 PNTip = function(pindex) {

       var html = '<table border=1><tr>' +
       		'<td>物品名称</td><td>预计付款金额</td><td>预计付款日期</td><td>实际付款金额</td><td>实际付款日期</td><td>确认付款</td>' +
       		'</tr><table>'
       		alert(document.getElementById('paydetail').innerHTML)
       document.getElementById('paydetail').innerHTML = html;
   }