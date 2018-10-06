$(document).ready(function() {

	var actionHeader = getServerAddress();

	// 2.初始化Button的点击事件
	var oButtonInit = new ButtonInit(actionHeader);
	oButtonInit.Init();

});

var ButtonInit = function(actionHeader) {
	var oInit = new Object();

	oInit.Init = function() {
		$('#searchAll').click(function() {
			$.ajax({
				async : false,
				cache : true,
				type : 'get',
				dataType : "json",
				url : actionHeader + 'setChart', // 请求的路径
				data : {
					"accountDate" : getYear(),
				},
				success : function(data) { // controller层传过来的map
					echarts.init(document.getElementById('Electricity')).setOption({
						title : {
							text : getChartName()+'用电量图'
						},
						tooltip: {
							trigger: 'axis',
							axisPointer: {
								type: 'cross'
							}
						},
						dataZoom : [ { // 这个dataZoom组件，默认控制x轴。
							type : 'slider', // 这个 dataZoom 组件是 slider 型
							start : 0, 
							end : 100
						}, { // 这个dataZoom组件，也控制x轴。
							type : 'inside', // 这个 dataZoom 组件是 inside 型
							start : 0, 
							end : 100
						} ],
						legend : {
							data : [ '用电量','金额' ]
						},
						xAxis : {
							data : data.categories
						},
						yAxis : {},
						series : [ {
							name : '用电量',
							type : 'line',
							data : data.amount
						} ,
						{
							name : '金额',
							type : 'line',
							data : data.money
						}],
						toolbox : {
							show : true,
							feature : {
								dataView : {
									readOnly : false
								},
								magicType : {
									type : [ 'line', 'bar' ]
								},
								saveAsImage : {}
							},
						}
					})

				}

			})
		})

		$('#searchEntity').click(function() {
			$.ajax({
				async : false,
				cache : true,
				type : 'get',
				dataType : "json",
				url : actionHeader + 'setEntityChart', // 请求的路径
				data : {
					"accountDate" : getYear(),
					"entityName" : $("#EntityName").val()
				},
				success : function(data) { // controller层传过来的map
					echarts.init(document.getElementById('Entity')).setOption({
						title : {
							text : getChartName()+$("#EntityName").val()+'用电量及金额图'
						},
						tooltip: {
							trigger: 'axis',
							axisPointer: {
								type: 'cross'
							}
						},
						dataZoom : [ { // 这个dataZoom组件，默认控制x轴。
							type : 'slider', // 这个 dataZoom 组件是 slider 型
							start : 0, 
							end : 100
						}, { // 这个dataZoom组件，也控制x轴。
							type : 'inside', // 这个 dataZoom 组件是 inside 型
							start : 0, 
							end : 100
						} ],
						legend : {
							data : [ '用电量','金额' ]
						},
						xAxis : {
							data : data.categories
						},
						yAxis : {},
						series : [ {
							name : '用电量',
							type : 'line',
							data : data.amount
						} ,
						{
							name : '金额',
							type : 'line',
							data : data.money
						}],
						toolbox : {
							show : true,
							feature : {
								dataView : {
									readOnly : false
								},
								magicType : {
									type : [ 'line', 'bar' ]
								},
								saveAsImage : {}
							},
						}
					})

				}

			})
		})


	}
	return oInit;
};