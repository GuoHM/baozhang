<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="bean.TblBaozhang"%>
<%@page import="java.util.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>查看数据</title>
<jsp:include page="IncludedFile.jsp"></jsp:include>
</head>
<body onload="getQueryCrashLogAll()">
	<jsp:include page="FunctionBar.jsp" />
	<div class="maincontent">
		<div class="container">
			<div class="row clearfix">
				<div class="col-xs-14 column">
					<div class="panel-body" style="padding-bottom: 0px;">
						<div class="panel panel-default">
							<div class="panel-heading">查询条件</div>
							<div class="panel-body">
								<form class="form-horizontal" role="form">
									<div class="form-group">
										<label class="col-sm-6 control-label">区公司名称</label>
										<div>
											<label class="checkbox-inline"> <select
												class="form-control" id="districtCompany"
												name="districtCompany">
											</select>
											</label>
										</div>
									</div>
									<div class="from-inline">
										<div class="form-group">

											<label class="col-sm-6 control-label">报账期间</label> <label
												class="checkbox-inline"> <select
												class="form-control" name="year" id="year">
											</select></label> <label for="year">年</label><label class="checkbox-inline">
												<select class="form-control" name="month" id="month">
											</select>
											</label> <label for="month">月</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-6 control-label">业务类型</label>
										<div>
											<label class="checkbox-inline"> <select
												class="form-control" name="businessType" id="businessType">

											</select>
											</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-6 control-label">报账方式</label>
										<div>
											<label class="checkbox-inline"> <select
												class="form-control" name="accountMethod" id="accountMethod">

											</select>
											</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-6 control-label">用电属性</label>
										<div>
											<label class="checkbox-inline"> <select
												class="form-control" name="electricType" id="electricType">

											</select>
											</label>
										</div>
									</div>
								</form>

								<div class="col-sm-offset-4 col-sm-10">
									<label class="checkbox-inline"> <input type="submit"
										value="提交" class="btn btn-default" id="btSearch">
									</label>
								</div>

							</div>
						</div>
					</div>



					<table class="table table-hover table-condensed"
						id="showWorksTable">
					</table>

				</div>
			</div>
		</div>
	</div>
	<script src="<%=basePath%>/js/initShowTable.js"></script>

</body>