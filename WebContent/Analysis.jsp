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
<title>预测分析</title>
<jsp:include page="IncludedFile.jsp"></jsp:include>
<script src="<%=basePath%>/lib/echarts.min.js"></script>
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
									</div>
								</form>
								<div class="col-sm-offset-4 col-sm-10">
									<input type="submit" value="查询" class="btn btn-default" id="searchAll"> <input
										type="submit" value="开始预测" class="btn btn-default"
										onclick="location.href='<%=basePath%>FixData.jsp'">
								</div>
							</div>
						</div>
					</div>

					<div class="panel-body" style="padding-bottom: 0px;">
						<div class="panel panel-default">
							<div class="panel-heading">总体数据</div>
							<div id="Electricity" class="center" style="width: 100%; height: 600px;"></div>
						</div>
					</div>
					<div class="panel-body" style="padding-bottom: 0px;">
						<div class="panel panel-default">
							<div class="panel-heading">具体10kv机楼情况</div>
							<div class="panel-body">
								<form class="form-inline" role="form">
									<div class="form-group">
										<div class="form-group">
											<label>10kv机楼名称:&nbsp;&nbsp;&nbsp;</label> <select class="form-control" name="EntityName"
												id="EntityName">
												<option value="南海大沥颜峰机楼">南海大沥颜峰机楼</option>
												<option value="南海罗村罗村机楼">南海罗村罗村机楼</option>
												<option value="南海黄岐旧局机房">南海黄岐旧局机房</option>
												<option value="南海黄岐黄岐新局机楼">南海黄岐黄岐新局机楼</option>
												<option value="南海西樵西樵新局机楼">南海西樵西樵新局机楼</option>
												<option value="盐步机楼">盐步机楼</option>
												<option value="南海大沥大沥机楼">南海大沥大沥机楼</option>
												<option value="南海桂城桂城二局机楼">南海桂城桂城二局机楼</option>
												<option value="南海平洲平洲机楼">南海平洲平洲机楼</option>
												<option value="顺峰枢纽目标局房屋">顺峰枢纽目标局房屋</option>
												<option value="南海里水里水机楼">南海里水里水机楼</option>
												<option value="伦教三洲机房">伦教三洲机房</option>
												<option value="顺德区容桂桂新机房">顺德区容桂桂新机房</option>
												<option value="南海丹灶金沙金沙机楼">南海丹灶金沙金沙机楼</option>
												<option value="南海小塘小塘机楼">南海小塘小塘机楼</option>
												<option value="勒流富裕接入网机房">勒流富裕接入网机房</option>
												<option value="南边综合机楼">南边综合机楼</option>
												<option value="大塘综合机楼">大塘综合机楼</option>
												<option value="更楼生产综合楼">更楼生产综合楼</option>
												<option value="容里电信分局">容里电信分局</option>
												<option value="勒流电信分局">勒流电信分局</option>
												<option value="南海和顺和顺机楼">南海和顺和顺机楼</option>
												<option value="白坭综合机楼">白坭综合机楼</option>
												<option value="北窖镇碧江电信支局（主楼）">北窖镇碧江电信支局（主楼）</option>
												<option value="南海西樵民乐机楼">南海西樵民乐机楼</option>
												<option value="明城生产综合楼">明城生产综合楼</option>
												<option value="人和生产综合楼">人和生产综合楼</option>
												<option value="顺德区容桂容山机房">顺德区容桂容山机房</option>
												<option value="合水综合生产楼">合水综合生产楼</option>
												<option value="大福基局房">大福基局房</option>
												<option value="九江机楼">九江机楼</option>
												<option value="南海桂城石肯机房">南海桂城石肯机房</option>
												<option value="乐平综合机楼">乐平综合机楼</option>
												<option value="芦苞综合机楼">芦苞综合机楼</option>
												<option value="城西综合机楼">城西综合机楼</option>
												<option value="松岗机楼">松岗机楼</option>
												<option value="城东综合机楼">城东综合机楼</option>
												<option value="均安新局">均安新局</option>
												<option value="官窑机楼">官窑机楼</option>
												<option value="富湾综合生产楼">富湾综合生产楼</option>
												<option value="荷城旧局综合楼">荷城旧局综合楼</option>
												<option value="杏坛局房">杏坛局房</option>
												<option value="伦教电信分局">伦教电信分局</option>
												<option value="大良综合楼主楼">大良综合楼主楼</option>
												<option value="陈村新局">陈村新局</option>
												<option value="龙江新局房屋">龙江新局房屋</option>
												<option value="振华电信综合楼主楼">振华电信综合楼主楼</option>
												<option value="乐从电信分局综合楼主楼">乐从电信分局综合楼主楼</option>
												<option value="南区综合楼副楼">南区综合楼副楼</option>
												<option value="北窖电信分局管理楼">北窖电信分局管理楼</option>
												<option value="西南综合机楼">西南综合机楼</option>
												<option value="荷城区沧江电信办公新大楼">荷城区沧江电信办公新大楼</option>
												<option value="南区综合楼主楼">南区综合楼主楼</option>
												<option value="勒流电信分局">勒流电信分局</option>
												<option value="信息大厦">信息大厦</option>
												<option value="亲仁机楼">亲仁机楼</option>
												<option value="南海桂城桂城一局机楼">南海桂城桂城一局机楼</option>
												<option value="大富机楼">大富机楼</option>
												<option value="杨梅生产综合楼">杨梅生产综合楼</option>
												<option value="三洲综合生产楼">三洲综合生产楼</option>
												<option value="环湖机楼">环湖机楼</option>
												<option value="城西机楼">城西机楼</option>
												<option value="朝安机楼">朝安机楼</option>
												<option value="张槎机楼">张槎机楼</option>
												<option value="南庄机楼">南庄机楼</option>
												<option value="榴苑机楼">榴苑机楼</option>
												<option value="魁奇机楼">魁奇机楼</option>
												<option value="丹灶中心营业厅">丹灶中心营业厅</option>
												<option value="同济机楼">同济机楼</option>
												<option value="荷城旧局综合楼">荷城旧局综合楼</option>
											</select>
										</div>
									</div>
								</form>
								<div class="col-sm-offset-4 col-sm-10">
									<input type="submit" value="提交" class="btn btn-default" id="searchEntity">
								</div>
							</div>

						</div>
					</div>
					<div class="panel-body" style="padding-bottom: 0px;">
						<div class="panel panel-default">
							<div class="panel-heading">具体机楼数据</div>
							<div id="Entity" class="center" style="width: 100%; height: 600px;"></div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>

	<script src="<%=basePath%>/js/initChart.js" type="text/javascript"></script>
</body>