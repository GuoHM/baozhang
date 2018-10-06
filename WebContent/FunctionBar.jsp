<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="container">
	<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
		<div class="navbar-header ">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#" name="date"></a>
		</div>
		<div class="collapse navbar-collapse menubar-left">
			<ul class="nav navbar-nav">
				<li>
					<a href="<%=basePath%>/index.jsp">
						<span class="glyphicon glyphicon glyphicon-lock">查看数据</span>
					</a>
				</li>
				<li>
					<a href="<%=basePath%>/Calculate.jsp">
						<span class="glyphicon glyphicon-book">统计报账</span>
					</a>
				</li>
				<li>
					<a href="<%=basePath%>/Import.jsp">
						<span class="glyphicon glyphicon-book">导入数据</span>
					</a>
				</li>
				<li>
					<a href="<%=basePath%>/Analysis.jsp">
						<span class="glyphicon glyphicon-book">分析预测</span>
					</a>
				</li>
			</ul>
		</div>
		
	</div>
</div>