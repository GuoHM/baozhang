$(document).ready(function() {

	$("#year option[value='']").attr("selected", "selected");
	$("#month option[value='']").attr("selected", "selected");

	var actionHeader = getServerAddress();

	// 1.初始化Table
	var oTable = new TableInit(actionHeader);
	oTable.Init();

	// 2.初始化Button的点击事件
	var oButtonInit = new ButtonInit(actionHeader);
	oButtonInit.Init();

});

var TableInit = function(actionHeader) {
	var oTableInit = new Object();
	// 初始化Table
	oTableInit.Init = function() {
		$('#cityTable').bootstrapTable({
			method : 'get', // 请求方式（*）
			url : actionHeader + '/getCityData',
			striped : true, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			sortable : true, // 是否启用排序
			sortOrder : "asc", // 排序方式
			queryParams : oTableInit.queryParams,// 传递参数（*）
			sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1, // 初始化加载第一页，默认第一页
			pageSize : 20, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
			// search: true, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			strictSearch : true,
			queryParamsType : "",
			showRefresh : true, // 是否显示刷新按钮
			minimumCountColumns : 2, // 最少允许的列数
			clickToSelect : true, // 是否启用点击选中行
			height : 500, // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			// uniqueId: "ID", //每一行的唯一标识，一般为主键列
			showToggle : true, // 是否显示详细视图和列表视图的切换按钮
			cardView : false, // 是否显示详细视图
			detailView : false, // 是否显示父子表
			showExport : true, // 是否显示导出按钮
			showColumns : true,
			exportOptions : {
				fileName : '报账统计', // 文件名称设置
				worksheetName : 'sheet1', // 表格工作区名称
				tableName : '报账统计'
			},
			columns : [ {
				align : "center",
				title : '用电属性',
				sortable : true,
				field : 'electricType'
			}, {
				align : "center",
				title : '总电量',
				sortable : true,
				field : 'totalElectricity'
			}, {

				align : "center",
				title : '总金额',
				sortable : true,
				field : 'totalAmount'
			} ],

			formatLoadingMessage : function() {
				// 正在加载
				return "请稍等，系统正在计算中...";
			}
		});
		$('#districtTable').bootstrapTable({
			method : 'get', // 请求方式（*）
			url : actionHeader + '/getDistrictConditions',
			striped : true, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			sortable : true, // 是否启用排序
			sortOrder : "asc", // 排序方式
			queryParams : oTableInit.queryParams,// 传递参数（*）
			sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1, // 初始化加载第一页，默认第一页
			pageSize : 20, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
			// search: true, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			strictSearch : true,
			queryParamsType : "",
			showRefresh : true, // 是否显示刷新按钮
			minimumCountColumns : 2, // 最少允许的列数
			clickToSelect : true, // 是否启用点击选中行
			height : 500, // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			// uniqueId: "ID", //每一行的唯一标识，一般为主键列
			showToggle : true, // 是否显示详细视图和列表视图的切换按钮
			cardView : false, // 是否显示详细视图
			detailView : false, // 是否显示父子表
			showExport : true, // 是否显示导出按钮
			showColumns : true,
			exportOptions : {
				fileName : '报账统计', // 文件名称设置
				worksheetName : 'sheet1', // 表格工作区名称
				tableName : '报账统计'
			},
			columns : [ {
				align : "center",
				title : '用电属性',
				sortable : true,
				field : 'electricType'
			}, {
				align : "center",
				title : '总电量',
				sortable : true,
				field : 'totalElectricity'
			}, {

				align : "center",
				title : '总金额',
				sortable : true,
				field : 'totalAmount'
			} ],

			formatLoadingMessage : function() {
				// 正在加载
				return "请稍等，系统正在计算中...";
			}
		});
	};

	// 得到查询的参数
	oTableInit.queryParams = function(params) {
		var date = getDate();
		var temp = {
			pageNumber : params.pageNumber,
			pageSize : params.pageSize,
			accountDate : date,
			districtCompany : $("#districtCompany").val()
		};
		return temp;
	};

	return oTableInit;
};

var ButtonInit = function(actionHeader) {
	var oInit = new Object();

	oInit.Init = function() {
		// 初始化页面上面的按钮事件
		$('#searchCal').click(
				function() {
					var date = getDateConvert();
					$.ajax({
						async : false,
						cache : true,
						type : 'get',
						dataType : "json",
						url : actionHeader + 'calculate', // 请求的路径
						data : {
							"accountDate" : getDate()
						},
						success : function(data) { // controller层传过来的map
							for (var i = 0; i < data.length; i++) {
								var result = data[i].result;
								var amount = data[i].amountResult;
							}
							var text, result, amount;
							if ($("#month").val() != '') {
								text = $("#year").val() + "年"
										+ $("#month").val() + "月报账结果：";
								result = "总金额:" + result.toFixed(2);
								amount = "总数量:" + amount.toFixed(2);
							} else {
								text = $("#year").val() + "年报账结果：";
								result = "总金额:" + result.toFixed(2);
								amount = "总数量:" + amount.toFixed(2);
							}
							$("#text").text(text);
							$("#result").text(result);
							$("#amount").text(amount);
						}
					});
					$("#cityTable").bootstrapTable('destroy');
					var oTable = new TableInit();
					oTable.Init();
				})
		$('#searchDistrict').click(
				function() {
					var date = getDateConvert();
					var companyName = $("#districtCompany").val();
					$.ajax({
						async : false,
						cache : true,
						type : 'get',
						dataType : "json",
						url : actionHeader + 'calculate', // 请求的路径
						data : {
							"accountDate" : getDate(),
							"districtCompany" : $("#districtCompany").val()
						},
						success : function(data) { // controller层传过来的map
							for (var i = 0; i < data.length; i++) {
								var result = data[i].result;
								var amount = data[i].amountResult;
							}
							var text, result, amount;
							if ($("#month").val() != '') {
								text = $("#year").val() + "年"
										+ $("#month").val() + "月报账结果：";
								result = "总金额:" + result.toFixed(2);
								amount = "总数量:" + amount.toFixed(2);
							} else {
								text = $("#year").val() + "年报账结果：";
								result = "总金额:" + result.toFixed(2);
								amount = "总数量:" + amount.toFixed(2);
							}
							$("#textDistrict").text(text);
							$("#resultDistrict").text(result);
							$("#amountDistrict").text(amount);
							$("#companyName").text(
									$("#districtCompany").find(
											"option:selected").text());
						}
					});
					$("#districtTable").bootstrapTable('destroy');
					var oTable = new TableInit();
					oTable.Init();
				})
	};

	return oInit;
};
