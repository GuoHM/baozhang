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
<title>导入数据</title>
<jsp:include page="IncludedFile.jsp"></jsp:include>
<script src="<%=basePath%>/lib/jquery.uploadify.min.js"
	type="text/javascript"></script>
<link href="<%=basePath%>/css/uploadify.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<jsp:include page="FunctionBar.jsp" />
	<div class="maincontent">
		<div class="container">
			<div class="row clearfix">
				<div class="col-xs-14 column">
					<h3>导入向导</h3>
					<div class="alert alert-success">
						<h4>提醒</h4>
						<p>
							导入前最好先备份，备份还原教程<a
								href='https://www.cnblogs.com/SJP666/p/4656714.html'>https://www.cnblogs.com/SJP666/p/4656714.html</a>
						</p>
						<p>
							请仔细观察要导入的数据在原数据库中是否已存在，数据重复导入系统不会报错但会导致统计结果有误
						</p>
					</div>
					<ul style="font-size:23px">
						<li>1. 下载excel文件</li>
						<li>2. 对比“数据列名.xls”，将除Is10kv外多余的列删去，删去后的列数应为30列（到AD列）</li>
						<li>3. 选中所有列在加上隔壁一列（到AE列，确保有31列） <img alt=""
							src="./img/1.png" height="75%" width="75%"></br> Ctrl+h 替换</br> <img
							alt="" src="./img/2.png" height="75%" width="75%"></br>
							查找内容不写任何东西,替换为里面输入一个空格，点击全部替换（消除数据库null值）</br>
							此时该表有31列，且所有单元格的空值替换为一个空格，以及第31列全部为空格				
						</li>
						<li>4.删去首行列名</li>
						<li>5.	将Q列至X列选中，ctrl+H替换，查找内容写一个空格，替换内容为0，也就是把所有数字列默认空值改成0，确认
						<img alt="" src="./img/3.png" height="75%" width="75%"><br>
						<img alt="" src="./img/4.png" ><br>
						</li>
						<li>6.	打开Sqlserver management studio，在需要导入的数据库右键-任务-导入数据，数据源选excel，选择文件，版本，首行包含列名不打钩，下一步
						<img alt="" src="./img/5.png" ><br>
						</li>
						<li>7.	输入服务器地址，点击sqlserver身份验证，输入账户密码，选择要导入的数据库名称
						<img alt="" src="./img/6.png" ><br>
						</li>
						<li>8.	选择复制一个或多个表，下一步<br>
						<img alt="" src="./img/7.png" ><br>
						</li>
						<li>9.	在源一列勾选一个表，目标一列选择你要导入到哪个表，此处为tbl_baozhang，若不选择则是默认新建一个表导入
						<img alt="" src="./img/8.png" ><br>
						点击预览，确认是31列，如图，则下一步<br>
						<img alt="" src="./img/9.png" ><br>
						</li>
						<li>10.	一直点击下一步，直到完成</li>
						<li>11.	如果导入过程出现错误，请检查以下步骤：<br>
						（1）	确认excel文件有31列，且最后一列全部为空格<br>
（2）	确认excel文件没有空值，即所有空值用空格代替（检查方法：ctrl+A全选，ctrl+H替换，第一行不输入任何字符，第二行输入一个空格）<br>
（3）	确认excel文件数字列值全部默认为0，即数字列不存在空值或者空格<br>
（4）若错误出现在传输过程前，则重新导入，若错误出现在传输过程中，则注意要删掉已经传输成功的部分数据再重新导入(返回步骤6)<br>
</li>
<li>12.导入成功后，进入sqlserver management studio，选中数据库右键点击新建查询，并输入“exec update_10kvData”，执行存储过程<br>
<img alt="" src="./img/10.PNG" ><br>
导入过程完成
						
					</ul>
				</div>
			</div>
		</div>
	</div>
	<script src="<%=basePath%>/js/upload.js" type="text/javascript"></script>
</body>