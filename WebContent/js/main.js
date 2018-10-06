/**
 * Created by Guo Haoming on 2017/4/24.
 */
var actionHeader = getServerAddress();
function getQueryCrashLogAll() {
	var optionsYear = "<option value=''>全部</option>";
	var optionsMonth = "<option value=''>全部</option>";
	var optionsDistrictCompany = "<option value=''>全部</option>";
	var optionsBusinessType = "<option value=''>全部</option><option value='C网'>C网</option><option value='非C网'>非C网</option>";
	var optionsAccountMethod = "<option value=''>全部</option>";
	var optionsElectricType = "<option value=''>全部</option>";

	$.ajax({
		async : false,
		cache : true,
		type : 'POST',
		dataType : "json",
		url : actionHeader+'/initData', // 请求的路径
		success : function(data) { // controller层传过来的map
			for (var i = 0; i < data.length; i++) {
				var districtCompanyList = data[i].districtCompanyList;
				var yearList = data[i].yearList;
				var accountMethodList = data[i].accountMethodList;
				var electricTypeList = data[i].electricTypeList;
				if (data[i].districtCompanyList == districtCompanyList) {
					for (var j = 0; j < districtCompanyList.length; j++) {
						optionsDistrictCompany += "<option value=\'"
								+ districtCompanyList[j] + "'\>"
								+ districtCompanyList[j] + "</option>";
					}
				}
				if (data[i].yearList == yearList) {
					for (var j = 0; j < yearList.length; j++) {
						optionsYear += "<option value=\'" + yearList[j] + "'\>"
								+ yearList[j] + "</option>";
					}
				}

				if (data[i].accountMethodList == accountMethodList) {
					for (var j = 0; j < accountMethodList.length; j++) {
						optionsAccountMethod += "<option value=\'"
								+ accountMethodList[j] + "'\>"
								+ accountMethodList[j] + "</option>";
					}
				}
				if (data[i].electricTypeList == electricTypeList) {
					for (var j = 0; j < electricTypeList.length; j++) {
						optionsElectricType += "<option value=\'"
								+ electricTypeList[j] + "'\>"
								+ electricTypeList[j] + "</option>";
					}
				}
				for (var i = 1; i <= 12; i++) {
					optionsMonth += "<option value=\'" + i + "'\>" + i
							+ "</option>";
				}
			}
		}
	});
	$("#year").html(optionsYear);
	$("#month").html(optionsMonth);
	$("#districtCompany").html(optionsDistrictCompany);
	$("#businessType").html(optionsBusinessType);
	$("#accountMethod").html(optionsAccountMethod);
	$("#electricType").html(optionsElectricType);
}

function getQueryCrashLogYear() {
	var optionsYear = "<option value=''>全部</option>";
	var optionsMonth = "<option value=''>全部</option>";
	$.ajax({
		async : false,
		cache : true,
		type : 'POST',
		dataType : "json",
		url : actionHeader+'/initYear', // 请求的路径
		success : function(data) { // controller层传过来的map
			for (var i = 0; i < data.length; i++) {
				var yearList = data[i].yearList;
				if (data[i].yearList == yearList) {
					for (var j = 0; j < yearList.length; j++) {
						optionsYear += "<option value=\'" + yearList[j] + "'\>"
								+ yearList[j] + "</option>";
					}
				}
			}
			for (var i = 1; i <= 12; i++) {
				optionsMonth += "<option value=\'" + i + "'\>" + i
						+ "</option>";
			}
		}
	});
	$("#year").html(optionsYear);
	$("#month").html(optionsMonth);

}
