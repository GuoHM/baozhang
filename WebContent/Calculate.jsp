<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>统计报账</title>
<jsp:include page="IncludedFile.jsp"></jsp:include>
</head>
<body onload="getQueryCrashLogYear()">
	<jsp:include page="FunctionBar.jsp" />
	<div class="maincontent">
		<div class="container">
			<div class="row clearfix">
				<div class="col-xs-14 column">
					<div class="panel-body" style="padding-bottom: 0px;">
						<div class="panel panel-default">
							<div class="panel-heading">查询条件</div>
							<div class="panel-body center">

								<form class="form-inline" role="form">
									<div class="form-group">
										<div class="form-group">
											<label>报账期间:&nbsp;&nbsp;&nbsp;</label> <select class="form-control" name="year" id="year"></select><label
												for="year">&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;</label>
										</div>
										<div class="form-group">
											<select class="form-control" name="month" id="month">
											</select> <label for="month">&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;</label>
										</div>
									</div>
								</form>
								<div class="col-sm-offset-4 col-sm-10">
									<input type="submit" value="提交" class="btn btn-default" id="searchCal">
								</div>
							</div>
						</div>
					</div>
					<div class="panel-body" style="padding-bottom: 0px;">
						<div class="panel panel-default">
							<div class="panel-heading">全市总览</div>
							<div class="panel-body">
								<h3 id="text"></h3>
								<h3 id="result"></h3>
								<h3 id="amount"></h3>
								<table class="table table-hover table-condensed" id="cityTable">
								</table>
							</div>
						</div>
					</div>

					<div class="panel-body" style="padding-bottom: 0px;">
						<div class="panel panel-default">
							<div class="panel-heading">各区情况</div>
							<div class="panel-body">
								<form class="form-inline" role="form">
									<div class="form-group">
										<div class="form-group">
											<label>区公司:&nbsp;&nbsp;&nbsp;</label> <select class="form-control" name="districtCompany"
												id="districtCompany">
												<option value="禅城%">禅城区分公司</option>
												<option value="南海%">南海区分公司</option>
												<option value="顺德%">顺德区分公司</option>
												<option value="高明%">高明区分公司</option>
												<option value="三水%">三水区分公司</option>
												<option value="%办公室%">市办公室</option>
												<option value="%网络运营%">市网络运营部</option>
												<option value="%预算资产%">市资产管理部</option>
											</select>
										</div>
									</div>
								</form>
								<div class="col-sm-offset-4 col-sm-10">
									<input type="submit" value="提交" class="btn btn-default" id="searchDistrict">
								</div>
								<h3 id="companyName"></h3>
								<h3 id="textDistrict"></h3>
								<h3 id="resultDistrict"></h3>
								<h3 id="amountDistrict"></h3>
								<table class="table table-hover table-condensed" id="districtTable">
								</table>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<script src="<%=basePath%>/js/initCalculateTable.js"></script>

</body>