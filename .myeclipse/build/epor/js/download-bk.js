function download(obj, id, fileName) {
	// fileName = fileName.replace(/#/g,'//#')
var url = 'uploadFolder/' + id + '/' + fileName;
	
	// obj.href=url

	Ext.Ajax.request({
				url : 'epor.do?actionType=doGetAttach',
				params : {
					path : url
				}

			})

}