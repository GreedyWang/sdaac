
//
// Extend the XmlTreeLoader to set some custom TreeNode attributes specific to
// our application:
//
Ext.BLANK_IMAGE_URL = 'ext/resources/images/default/s.gif';
Ext.app.BookLoader = Ext.extend(Ext.ux.XmlTreeLoader, {
			processAttributes : function(attr) {

				if (attr.menu) { // is it an author node?

					// Set the node text that will show in the tree since our
					// raw data does not include a text attribute:
					attr.text = attr.name;
					attr.pk = attr.id;
					attr.menu = attr.menu;
					// Author icon, using the gender flag to choose a specific
					// icon:
					// attr.iconCls = 'author-' + attr.gender;

					// Override these values for our folder nodes because we are
					// loading all data at once. If we were
					// loading each node asynchronously (the default) we would
					// not want to do this:
					attr.loaded = true;
					// attr.expanded = true;
				} else { // is it a book node?

					// Set the node text that will show in the tree since our
					// raw data does not include a text attribute:
					attr.text = attr.name;
					attr.pk = attr.id;
					attr.url = attr.url;
					// alert(attr.innerText)
					attr.introduce = attr.text;
					attr.leaf = true;
				}
			}
		});

var bb = new Object();
var root = new Ext.tree.AsyncTreeNode();
function getParams(name) {
	if (location.href.indexOf("?") == -1
			|| location.href.indexOf(name + '=') == -1) {
		return '';
	} else {
		return location.href.substring(location.href.indexOf("=") + 1);
	}

}
function showWind(thUrl) {
	var tHtml = '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src='
			+ thUrl + '></iframe>';
	var wind = new Ext.Window({
				id : 'panel1',
				// title:'修改',
				// applyTo:'dialog',
				height : '600',
				width : '800',
				html : tHtml,
				buttons : [{
							text : '关闭',
							handler : function() {
								// alert(document.getElementById('search').innerHTML)
								// wind.hide();
								loader.load(root, null);
								wind.close();
							}

						}]
			})
	wind.show();
}

// 右键菜单
var rightClick = new Ext.menu.Menu({
	id : 'rightClickCont',
	items : [{
		id : 'add',
		text : '添加',
		iconCls : 'icon-add',
		// 增加菜单点击事件
		handler : function(node) {
			var thUrl = encodeURI("news.do?actionType=test&&m=add&id=" + bb.pk);
			showWind(thUrl);
		}
	}, {
		id : 'modify',
		text : '修改',
		iconCls : 'icon-modify',
		handler : function(node) {

			var thUrl = encodeURI("news.do?actionType=test&&m=modify&id="
					+ bb.pk + "&name=" + bb.name + "&url=" + bb.url
					+ "&introduce=" + bb.introduce);
			// var thUrl="company/personal/MenuManager.jsp?item="+bb;
			// alert(thUrl)
			// var tHtml='<iframe scrolling="auto" frameborder="0" width="100%"
			// height="100%" src='+thUrl+'></iframe>';
			showWind(thUrl);
		}
	}, {
		id : 'delete',
		text : '删除',
		iconCls : 'icon-delete',
		handler : function(node) {
			if (bb.pk == 1) {
				Ext.Msg.alert('不能删除根目录')
			} else {
				if (Ext.Msg.alert('确认删除？')) {

					Ext.Ajax.request({
								// 请求地址
								url : 'news.do?actionType=test&id=' + bb.pk
										+ '&&m=delete',
								// 提交参数组
								// params: {
								// LoginName:Ext.get('LoginName').dom.value,
								// LoginPassword:Ext.get('LoginPassword').dom.value
								// },
								// 成功时回调
								success : function(response, options) {
									// 获取响应的json字符串
									// var responseArray =
									// Ext.util.JSON.decode(response.responseText);
									// if(responseArray.success==true){
									// Ext.Msg.alert('success');
									// }
									// else{
									// Ext.Msg.alert('failure');
									// }
									loader.load(root, null);
								}
							});

				}
			}
		}
	}]
});

function init() {

	var detailsText = '<i>Select a category to see more information...</i>';
	// var url = 'company/data/' + getParams('xml')
	var tpl = new Ext.Template('<h2 class="title">{title}</h2>',
			'<p><b>说明</b>: {innerText}</p>',
			'<p><a href="{url}" target="_blank">check this</a></p>');
	tpl.compile();

	new Ext.Panel({
				title : 'Catagory List',
				renderTo : 'tree',
				layout : 'border',
				width : 700,
				height : 600,
				items : [{
					xtype : 'treepanel',
					id : 'tree-panel',
					region : 'center',
					margins : '2 2 0 2',
					autoScroll : true,
					rootVisible : false,
					root : root,

					// Our custom TreeLoader:
					loader : loader,

					listeners : {
						'render' : function(tp) {
							tp.getSelectionModel().on('selectionchange',
									function(tree, node) {
										var el = Ext.getCmp('details-panel').body;
										if (node.leaf) {
											tpl.overwrite(el, node.attributes);
										} else {
											el.update(detailsText);
										}
									})
						},
						'contextmenu' : function(node, event) {
							// alert("node.id="+ node.id);

							event.preventDefault(); // 这行是必须的

							bb = {
								text : node.attributes.text,
								pk : node.attributes.pk,
								url : node.attributes.url,
								name : node.attributes.name,
								flag : node.attributes.menu,
								introduce : node.attributes.innerText
							}
							rightClick.showAt(event.getXY());// 取得鼠标点击坐标，展示菜单
						}

					}
				}, {
					region : 'south',
					title : 'Details',
					id : 'details-panel',
					autoScroll : true,
					collapsible : true,
					split : true,
					margins : '0 2 2 2',
					cmargins : '2 2 2 2',
					height : 220,
					html : detailsText
				}]
			});
};