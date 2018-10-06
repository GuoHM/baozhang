<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>数据修正</title>
<jsp:include page="IncludedFile.jsp"></jsp:include>
<script src="<%=basePath%>/lib/echarts.min.js"></script>
</head>
<body>
	<jsp:include page="FunctionBar.jsp" />
	<div class="maincontent">
		<div class="container">
			<div class="row clearfix">
				<div class="col-xs-14 column">

					<div class="alert alert-success">
						<h4>说明</h4>
						<p>由于原始数据存在一些人为原因的造成的数据异常，现首先对原始数据进行修正以便模型正常运行</p>
					</div>

					<div class="panel-body" style="padding-bottom: 0px;">
						<div class="panel panel-default">
							<div class="panel-heading">设置修正参数</div>
							<div class="panel-body" style="padding-bottom: 5px;">
								<form class="form-horizontal" role="form" action="">
									<div class="form-group">
										<div class="form-group">
											<label class="col-sm-2 control-label">数据偏差值:</label>
											<div class="col-sm-2">
												<select class="form-control" name="ErrorNum" id="offset">
													<option value="0">无</option>
													<option value="0.15">15%</option>
													<option value="0.20">20%</option>
													<option value="0.25">25%</option>
													<option value="0.30">30%</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">数据趋势个数:</label>
											<div class="col-sm-2">
												<select class="form-control" name="ErrorNum" id="trendNumber">
													<option value="0">无</option>
													<option value="1">1</option>
													<option value="2">2</option>
													<option value="3">3</option>
												</select>
											</div>
										</div>
									</div>
								</form>
								<div class="col-sm-offset-4 col-sm-10">
									<input type="submit" value="确定" class="btn btn-default" id="FixError">
								</div>

							</div>
						</div>
					</div>

					<div class="panel-body" style="padding-bottom: 0px;">
						<div class="panel panel-default">
							<div class="panel-heading">修正后数据</div>
							<div id="FixErrorChart" class="center" style="width: 100%; height: 600px;"></div>
						</div>
					</div>

					<div class="panel-body" style="padding-bottom: 0px;">
						<div class="panel panel-default">
							<div class="panel-heading">预测算法</div>
							<div class="panel-body">
								<div class="col-sm-offset-4 col-sm-10">
									<input type="button" value="ARIMA模型" class="btn btn-default"
										onclick="location.href='<%=basePath%>Model.jsp?model=ARIMA'"> <input type="button"
										value="Holt-Winters模型" class="btn btn-default"
										onclick="location.href='<%=basePath%>Model.jsp?model=HoltWinters'">
								</div>
							</div>
						</div>
					</div>



				</div>


			</div>
		</div>
	</div>

	<script src="<%=basePath%>/js/initPredictChart.js" type="text/javascript"></script>
</body>
</html>