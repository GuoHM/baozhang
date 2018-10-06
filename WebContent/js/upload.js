$(function() {
	// 添加界面的附件管理
	$('#inputfile').uploadify({
		'swf' : '../lib/uploadify.swf', // FLash文件路径
		'buttonText' : '选择数据源文件', // 按钮文本
		'uploader' : '../import.jsp', // 处理上传的页面
		'queueID' : 'fileQueue', // 队列的ID
		'queueSizeLimit' : 1, // 队列最多可上传文件数量，默认为999
		'auto' : false, // 选择文件后是否自动上传，默认为true
		'multi' : false, // 是否为多选，默认为true
		'removeCompleted' : true, // 是否完成后移除序列，默认为true
		'fileSizeLimit' : '100MB', // 单个文件大小，0为无限制，可接受KB,MB,GB等单位的字符串值
		'fileTypeDesc' : 'Excel Files', // 文件描述
		'fileTypeExts' : '*.xls;*.xlsx', // 上传的文件后缀过滤器
		'onQueueComplete' : function(event, data) { // 所有队列完成后事件
			// 业务处理代码
			// 提示用户Excel格式是否正常，如果正常加载数据
		},
		'onUploadStart' : function(file) {
			InitUpFile();// 上传文件前 ，重置GUID，每次不同
			$("#inputfile").uploadify("settings", 'formData', {
				'folder' : '数据导入文件',
				'guid' : $("#AttachGUID").val()
			}); // 动态传参数
		},
		'onUploadError' : function(event, queueId, fileObj, errorObj) {
			// alert(errorObj.type + "：" + errorObj.info);
		}
	});
});