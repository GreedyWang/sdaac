var HKEY_Root, HKEY_Path, HKEY_Key;
HKEY_Root = "HKEY_CURRENT_USER";
HKEY_Path = "\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
// 设置网页打印的页眉页脚为空
function PageSetup_Null() {
	try {
		var Wsh = new ActiveXObject("WScript.Shell");
		HKEY_Key = "header";
		Wsh.RegWrite(HKEY_Root + HKEY_Path + HKEY_Key, "");
		HKEY_Key = "footer";
		Wsh.RegWrite(HKEY_Root + HKEY_Path + HKEY_Key, "");
	} catch (e) {
	}
}
// 恢复网页打印的页眉页脚
function PageSetup_default() {
	try {
		var Wsh = new ActiveXObject("WScript.Shell");
		HKEY_Key = "header";
		Wsh.RegWrite(HKEY_Root + HKEY_Path + HKEY_Key, "&w&b页码,&p/&P");
		HKEY_Key = "footer";
		Wsh.RegWrite(HKEY_Root + HKEY_Path + HKEY_Key, "&u&b&d");
	} catch (e) {
	}
}
function printpreview() {
	// 打印页面预览
	PageSetup_Null();
	document.all.WebBrowser.execwb(6, 1)

}

function printit() {
	if (confirm('确定打印吗？')) {
		PageSetup_Null();
		document.all.WebBrowser.execwb(7, 1)
	}
}