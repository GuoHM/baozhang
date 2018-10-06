$(document).ready(function() {

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
		$('#showWorksTable').bootstrapTable({
			method : 'get', // 请求方式（*）
			url : actionHeader + 'getData',
			striped : true, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			sortable : true, // 是否启用排序
			sortOrder : "asc", // 排序方式
			queryParams : oTableInit.queryParams,// 传递参数（*）
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1, // 初始化加载第一页，默认第一页
			pageSize : 10, // 每页的记录行数（*）
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
			showColumns : true,
			columns : [ {
				align : "center",
				title : '状态',
				visible : false,
				sortable : true,
				field : 'status'

			}, {

				align : "center",
				title : '分公司名称',
				visible : false,
				sortable : true,
				field : 'branchCompany'
			}, {

				align : "center",
				title : '区县公司名称',
				sortable : true,
				field : 'districtCompany'
			}, {

				align : "center",
				title : '费用对象编码',
				visible : false,
				sortable : true,
				field : 'costObjectId'
			}, {

				align : "center",

				title : '费用对象名称',
				visible : false,
				sortable : true,
				field : 'costObjectName'
			}, {

				align : "center",
				title : '实体编码',
				sortable : true,
				field : 'entityId'
			}, {

				align : "center",
				title : '实体名称',
				sortable : true,
				field : 'entityName'
			}, {

				align : "center",
				title : '费用类型',
				visible : false,
				sortable : true,
				field : 'costType'
			}, {

				align : "center",
				title : '报账期间',
				sortable : true,
				field : 'accountDate'
			}, {

				align : "center",
				title : '报账人',
				visible : false,
				sortable : true,
				field : 'accountPerson'
			}, {

				align : "center",
				title : '开始日期',
				visible : false,
				sortable : true,
				field : 'startedDate'
			}, {

				align : "center",
				title : '结束日期',
				visible : false,
				sortable : true,
				field : 'endedDate'

			}, {

				align : "center",
				title : '报账类型',
				visible : false,
				sortable : true,
				field : 'accountType'
			}, {

				align : "center",
				title : '业务类型',
				sortable : true,
				field : 'businessType'
			}, {

				align : "center",
				title : '报账方式',
				sortable : true,
				field : 'accountMethod'
			}, {

				align : "center",
				title : '用电属性',
				sortable : true,
				field : 'electricType'
			}, {

				align : "center",
				title : '数量',
				sortable : true,
				field : 'electricAmount'
			}, {

				align : "center",
				title : '单价',
				sortable : true,
				field : 'electricPrice'
			}, {

				align : "center",
				title : '报账金额',
				sortable : true,
				field : 'accountAmount'

			}, {

				align : "center",
				title : '税额',
				visible : false,
				sortable : true,
				field : 'tax'

			}, {

				align : "center",
				title : '缴费方式',
				visible : false,
				sortable : true,
				field : 'payMethod'

			}, {
				align : "center",
				title : '含水电费',
				visible : false,
				sortable : true,
				field : 'utilitiesFee'
			}, {
				align : "center",
				title : '含污水费',
				visible : false,
				sortable : true,
				field : 'sewageFee'
			}, {
				align : "center",
				title : '含垃圾费',
				visible : false,
				sortable : true,
				field : 'garbageFee'
			}, {
				align : "center",
				title : '记账成本中心名称',
				visible : false,
				sortable : true,
				field : 'accountCenterName'
			}, {
				align : "center",
				title : '记账成本中心编码',
				visible : false,
				sortable : true,
				field : 'accountCenterId'
			}, {
				align : "center",
				title : '用户编号',
				visible : false,
				sortable : true,
				field : 'userId'
			}, {
				align : "center",
				title : '代垫外单位名称',
				visible : false,
				sortable : true,
				field : 'advancePayment'
			}, {
				align : "center",
				title : '对外结算类型',
				visible : false,
				sortable : true,
				field : 'settlementType'
			}, {
				align : "center",
				title : '票据类型',
				visible : false,
				sortable : true,
				field : 'billType'
			}, {
				align : "center",
				title : '10千伏',
				sortable : true,
				field : 'is10kv'

			} ],
			formatLoadingMessage : function() {
				// 正在加载
				return "请稍等，正在加载中...";
			}
		});
	};

	// 得到查询的参数
	oTableInit.queryParams = function(params) {
		var date = getDate();
		var temp = {
			pageNumber : params.pageNumber,
			pageSize : params.pageSize,
			districtCompany : $("#districtCompany").val(),
			accountDate : date,
			businessType : $("#businessType").val(),
			accountMethod : $("#accountMethod").val(),
			electricType : $("#electricType").val(),
		};
		return temp;
	};
	return oTableInit;
};

var ButtonInit = function() {
	var oInit = new Object();
	var postdata = {};

	oInit.Init = function() {
		// 初始化页面上面的按钮事件
		$('#btSearch').click(function() {
			$("#showWorksTable").bootstrapTable('destroy');
			var actionHeader = getServerAddress();
			var oTable = new TableInit(actionHeader);
			oTable.Init();
		})
	};

	return oInit;
};
