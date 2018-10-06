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
<title>ARIMA分析</title>
<jsp:include page="IncludedFile.jsp"></jsp:include>
<script src="<%=basePath%>/lib/echarts.min.js"></script>
</head>
<body>
	<jsp:include page="FunctionBar.jsp" />
	<div class="maincontent">
		<div class="container">
			<div class="row clearfix">
				<div class="col-xs-14 column">
					<div class="panel-body" style="padding-bottom: 0px;">
						<form class="form-inline" role="form" action="">
							<div class="form-group">
								<div class="form-group">
									<label>预测数据:&nbsp;&nbsp;&nbsp;</label> <input type="text" class="form-control"
										id="predictNumber" required="required"><label for="predictNumber">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								</div>
							</div>
						</form>
						<div class="col-sm-offset-4 col-sm-10">
							<input type="submit" value="提交" class="btn btn-default" id="ARIMAPrediction"> <input
								type="submit" value="上一步" class="btn btn-default"
								onclick="location.href='<%=basePath%>FixData.jsp'">
						</div>
					</div>

					<div class="panel-body" style="padding-bottom: 0px;">
						<div class="panel panel-default">
							<div class="panel-heading">模型信息</div>
							<div class="panel-body">
								<h3 id="modelName"></h3>
								<h3 id="aic"></h3>
							</div>
						</div>
					</div>

					<div class="panel-body" style="padding-bottom: 0px;">
						<div class="panel panel-default">
							<div class="panel-heading">预测数据</div>
							<div id="ARIMAChart" class="center" style="width: 100%; height: 600px;"></div>
						</div>
					</div>



				</div>
			</div>
		</div>
	</div>
	<script src="<%=basePath%>/js/initPredictChart.js" type="text/javascript"></script>
</body>
</html>

