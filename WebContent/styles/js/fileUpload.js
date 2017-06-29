function uploadFiles_framework(select, upload, preview) {
	if (select == null) {
		$.messager.alert('警告', '请至少传入一个文件选择标签ID!');
		return;
	}
	if ($("#" + select).val() == null || $("#" + select).val().trim() == "") {
		$.messager.alert('警告', '未选定任何文件!');
		return;
	}
	$.messager.progress({
	     title:'请等待',
	     msg:'正在上传,请不要离开此页面...',
	     text:'正在上传...'
    });
         
     
	$.ajaxFileUpload({
		url : WEB_APP + "/frame/file/upload",// 需要链接到服务器地址
		secureuri : true,
		data : {
			fileName : select
		},
		fileElementId : select,// 文件选择框的id属性
		success : function(data) {
			//console.log(data);
			data = eval('(' + data + ')');
			$.messager.progress('close');
			if (data.error == 'false') {
				$.messager.alert('提示', "上传成功");
				if (upload != null) {
					$("#" + upload).val(data.msg);
				}

				if (preview != null) {
					$("#" + preview).attr("src", WEB_APP + data.msg);
				}else{
					$("#" + preview).attr("href", WEB_APP + data.msg);
				}
			} else {
				$.messager.alert('错误', "上传失败:" + data.msg);
			}
		},
		error : function(data, status, e) {
			$.messager.alert('提示', '文件错误！');
			$.messager.progress('close');
		}
	});

}
