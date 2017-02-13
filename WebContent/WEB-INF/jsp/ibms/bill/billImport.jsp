<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include
	file="/WEB-INF/jsp/framework/component/include_uploadfile.jsp"%>


<script type="text/javascript">
	$(function() {
		pageInit();
	});
	function taskLoad(obj) {
		window.parent.loadContent(obj);
	}
	function pageInit() {
		var uploadurl = '${pageContext.request.contextPath}/web/bill/uploadfile;jsessionid=<%=request.getSession().getId()%>',
			ext = 'Excel表格 (*.xls;*.xlsx;*.xlsm)',
			size = '2 MB',
			count = 5,
			useget = 1,
			params = {taskType:'1'};//默认值 
		uploadurl = getQuery('uploadurl') || uploadurl;
		ext = getQuery('ext') || ext;
		size = getQuery('size') || size;
		count = getQuery('count') || count;
		useget = getQuery('useget') || useget;

		var tmpParams = getQuery('params');
		if (tmpParams) {
			try {
				eval("tmpParams=" + tmpParams);
			} catch (ex) {
			}
			;
			params = $.extend({}, params, tmpParams);
		}
		ext = ext.match(/([^\(]+?)\s*\(\s*([^\)]+?)\s*\)/i);
		setTimeout(fixHeight, 10);
		swfu = new SWFUpload(
				{
					// Flash组件
					flash_url : "<c:url value='/styles/component/multiupload/swfupload/swfupload.swf'/>",
					prevent_swf_caching : false,//是否缓存SWF文件

					// 服务器端
					upload_url : uploadurl,
					file_post_name : "filedata",
					post_params : params,//随文件上传一同向上传接收程序提交的Post数据
					use_query_string : useget == '1' ? true : false,//是否用GET方式发送参数

					// 文件设置
					file_types : ext[2],//文件格式限制
					file_types_description : ext[1],//文件格式描述
					file_size_limit : size, // 文件大小限制
					file_upload_limit : count,//上传文件总数
					file_queue_limit : 0,//上传队列总数
					custom_settings : {
						test : "aaa"
					},

					// 事件处理
					file_queued_handler : fileQueued,//添加成功
					file_queue_error_handler : fileQueueError,//添加失败
					upload_start_handler : uploadStart,//上传开始
					upload_progress_handler : uploadProgress,//上传进度
					upload_error_handler : uploadError,//上传失败
					upload_success_handler : function(file, serverData){
						uploadSuccess(file, serverData);
						if(getFilesQueuedCount()==0){
							taskLoad(document.getElementById("taskUrl"));
							$("#dcim_tmp_dialogId").dialog("close");
						}
					},//上传成功
					upload_complete_handler : uploadComplete,//上传结束

					// 按钮设置
					button_placeholder_id : "divAddFiles",
					button_width : 69,
					button_height : 17,
					button_window_mode : SWFUpload.WINDOW_MODE.TRANSPARENT,
					button_cursor : SWFUpload.CURSOR.HAND,
					button_image_url : "<c:url value='/styles/component/multiupload/img/add.gif'/>",
					button_text : '<span class="theFont">添加文件</span>',
					button_text_style : ".theFont { font-size: 12px; }",
					button_text_left_padding : 20,
					button_text_top_padding : 0,

					// 调试设置
					debug : true
				});
	}
	function fixHeight() {
		$('#listArea').css('height', 'auto');
	}
	function getQuery(item) {
		var svalue = location.search.match(new RegExp('[\?\&]' + item
				+ '=([^\&]*)(\&?)', 'i'));
		return svalue ? decodeURIComponent(svalue[1]) : '';
	}

	//----------------跨域支持代码开始(非跨域环境请删除这段代码)----------------

	var callback = callback || function(v) {
		v = JSON.stringify(v);
		window.name = escape(v);
		//window.location='http://'+location.search.match(/[\?&]parenthost=(.*)(&|$)/i)[1]+'/xheditorproxy.html';//这个文件最好是一个0字节文件，如果无此文件也会正常工作
	};
	//----------------跨域支持代码结束----------------
</script>

<form>
	<input type="hidden" id="taskUrl" tlink="/web/bill/billList" floor="false"/>
	
	<div class="ibms_form_panel">
		<div class="ibms_form_default">
			<ul class="ibms_clear">
				<li>
					<font>步骤1：</font>
					<h1>
						<a id="downAssetModel" href="${pageContext.request.contextPath}/web/bill/download"
							data-options="plain:true,url:'${pageContext.request.contextPath}/exportExcel/assetModel'">点击下载导入模板</a>
					</h1>
				</li>
				<li>
					<font>步骤2：</font>
					<h1>填写导入数据</h1>
				</li>
				<li>
					<font>步骤3：</font>
					<h1>导入文件 </h1>
				</li>
			</ul>
		</div>
		<div id="upload">
			<div id="buttonArea">
				<div id="controlBtns" style="display: none;">
					<a href="javascript:void(0);" id="btnClear" onclick="removeFile();" class="btn" style="display: none;">
					<span><img src="<c:url value='/styles/component/multiupload/img/clear.gif'/>" />删除文件</span> 
					</a>
					<a href="javascript:void(0);" id="btnStart" onclick="startUploadFiles();" class="btn">
					<span><img src="<c:url value='/styles/component/multiupload/img/start.gif'/>" />开始上传</span>
					</a>
				</div>
				<a href="javascript:void(0);" id="addFiles" class="btn"><span><div id="divAddFiles">添加文件</div></span></a>							
			</div>
			<div id="listArea" style="height:auto;">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<thead id="listTitle">
						<tr>
							<td width="53%">文件名</td>
							<td width="25%">大小</td>
							<td width="22%">状态</td>
						</tr>
					</thead>
					<tbody id="listBody">
					</tbody>
				</table>
			</div>
			<div id="progressArea">
			<!-- 
				<div id="progressBar">
					<span>0%</span>
					<div id="progress" style="width: 1px;"></div>
				</div>
				 -->
			</div>
		</div>
	</div>
</form>