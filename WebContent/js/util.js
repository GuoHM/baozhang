function getServerAddress() {
	return 'http://localhost:8080/baozhang/';
}

function getDate() {
	var date;
	if (($("#year").val() == '' && $("#month").val() == '')) {
		date = "%";
	} else if ($("#year").val() == '' && $("#month").val() != '') {
		date = "%" + $("#month").val();
	} else if ($("#year").val() != '' && $("#month").val() == '') {
		date = $("#year").val() + "%";
	} else if ($("#year").val() != '' && $("#month").val() != '') {
		date = $("#year").val() + "-" + $("#month").val();
	}
	return date;
}

function getYear() {
	if ($("#year").val() == '') {
		return '%';
	}
	return $("#year").val()+'%';
}

function getChartName() {
	if ($("#year").val() == '') {
		return '所有历史数据';
	}
	return $("#year").val()+'年';
}

function getDateConvert() {
	// url不能识别%，在直接用url传参数的时候用这个函数
	var date;
	if (($("#year").val() == '' && $("#month").val() == '')) {
		date = "%25";
	} else if ($("#year").val() == '' && $("#month").val() != '') {
		date = "%25" + $("#month").val();
	} else if ($("#year").val() != '' && $("#month").val() == '') {
		date = $("#year").val() + "%25";
	} else if ($("#year").val() != '' && $("#month").val() != '') {
		date = $("#year").val() + "-" + $("#month").val() ;
	}
	return date;
}

function getText() {
	if ($("#month").val() != '') {
		return $("#year").val() + "年" + $("#month").val() + "月报账结果";
	} else {
		return $("#year").val() + "年报账结果";
	}
}

function getParam(paramName) { 
    paramValue = "", isFound = !1; 
    if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) { 
        arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&"), i = 0; 
        while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++ 
    } 
    return paramValue == "" && (paramValue = null), paramValue 
} 