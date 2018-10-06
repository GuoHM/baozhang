<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script src="<%=basePath%>/js/main.js"></script>
<link rel="stylesheet" href="<%=basePath%>/css/style.css">
<link rel="stylesheet" href="<%=basePath%>/css/bootstrap.css">
<link rel="stylesheet" href="<%=basePath%>/css/bootstrap-table.css">
<script src="<%=basePath%>/lib/jquery.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap.min.js"></script>
<script src="<%=basePath%>/lib/bootstrap-table.js"></script>
<script src="<%=basePath%>/lib/bootstrap-table-zh-CN.js"></script>
<script src="<%=basePath%>/lib/bootstrap-table-export.js"></script>
<script src="<%=basePath%>/lib/tableExport.js"></script>
<script src="<%=basePath%>/lib/jquery.i18n.properties.min.js"></script>
<script src="<%=basePath%>/js/util.js"></script>