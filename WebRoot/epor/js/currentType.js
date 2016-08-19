/**
 * 文本框输入转换为货币格式
 * @param {} s
 * @return {}
 */
function formatNum(s) {
	s += "";
	if (s=='.' && !/^(\+|-)?\d+(\.\d+)?$/.test(s)) {
		alert("It isn 't  Number！");
	}
	var a = s.match(/^(\+|-)?(\d[^\.]+)(\.\d+)?$/);
	// alert(a);
	if (s != null && s.length > 3) {
		var b = a[2];
		var c = "";
		for (var i = b.length - 3; i > -3; i = i - 3) {
			c = "," + b.substring(i, i + 3) + c;
		}
		return (a[1] + c.substr(1) + a[3]);
	} else {
		return s;
	}

}

function replaceStr(str, sFnd, sRep) {
	var sTmp = "";
	var endIndex = 0;
	var beginIndex = 0;
	var len = sFnd.length;
	while (endIndex >= 0) {
		endIndex = str.indexOf(sFnd, beginIndex);
		if (endIndex >= 0) {
			sTmp += str.substring(beginIndex, endIndex) + sRep;
			beginIndex = endIndex + len;
		} else if (beginIndex >= 0) {
			sTmp += str.substring(beginIndex);
			break;
		}
	}
	return sTmp;
}

function replaceCommas(str) {
	if (str == "") {
		return str;
	}
	str = replaceStr(str, ",", "");
	return str;
}

function checkNumber(obj) {
	var str = replaceCommas(obj.value);
	obj.value = formatNum(str);
}