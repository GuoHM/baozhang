$(document).ready(function() {

	var actionHeader = getServerAddress();

	// 2.初始化Button的点击事件
	var oButtonInit = new ButtonInit(actionHeader);
	oButtonInit.Init();

});

var ButtonInit = function(actionHeader) {
	var oInit = new Object();

	oInit.Init = function() {
		$('#FixError').click(function() {
			$.ajax({
				async : false,
				cache : true,
				type : 'get',
				dataType : "json",
				url : actionHeader + 'fixData', // 请求的路径
				data : {
					"offset" : $("#offset").val(),
					"trendNumber" : $("#trendNumber").val(),
				},
				success : function(data) { // controller层传过来的map
					echarts.init(document.getElementById('FixErrorChart')).setOption({
						title : {
							text : '修正后数据'
						},
						tooltip : {
							trigger : 'axis',
							axisPointer : {
								type : 'cross'
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
							data : [ '用电量' ]
						},
						xAxis : {
							data : data.categories
						},
						yAxis : {},
						series : [ {
							name : '用电量',
							type : 'line',
							data : data.amount
						} ],
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

		$('#ARIMAPrediction').click(function() {
			$.ajax({
				async : false,
				cache : true,
				type : 'get',
				dataType : "json",
				url : actionHeader + 'setPredictionChart', // 请求的路径
				data : {
					"predictNumber" : $("#predictNumber").val(),
					"modelName" : getParam("model")
				},
				success : function(data) { // controller层传过来的map
					echarts.init(document.getElementById('ARIMAChart')).setOption({
						title : {
							text : getParam("model") + '模型预测'
						},
						tooltip : {
							trigger : 'axis',
							axisPointer : {
								type : 'cross'
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
							data : [ '用电量', '预测量' ]
						},
						xAxis : {
							data : data.categories
						},
						yAxis : {},
						series : [ {
							name : '用电量',
							type : 'line',
							data : data.amount
						}, {
							name : '预测量',
							type : 'line',
							data : data.predict
						} ],
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
					});
					$("#modelName").text("模型名:" + data.modelname);
					$("#aic").text("AIC值：" + data.aic);
					$("#RMSE").text("RMSE(均方误差)：" + data.RMSE.toFixed(2));
					$("#MAPE").text("MAPE(平均绝对百分比误差)：" + data.MAPE.toFixed(2) + "%");

				}

			})
		})

	}
	return oInit;
};