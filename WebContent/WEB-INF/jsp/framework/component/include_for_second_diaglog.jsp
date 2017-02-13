<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript">

$(function() {
	//添加弹出层所需位置
	
	function bindSave(button){
// 		if($("#ck_editor")!=null&&CKEDITOR.instances.ck_editor!=null){
// 			$("#ck_editor").val(CKEDITOR.instances.ck_editor.getData());
// 		}
		var form = $(button).parents("form");
		if(!$(this).parents("form").form('validate')){
			return;
		}
		var dataOption = parseOption(button);
		if(!form.form('validate')){
			return;
		}
		if(dataOption){
	    	IBMS(form).post(dataOption.url,function(data){
	    		if (data.message) {
	    			IBMS.messager.alert("提示消息", data.message, "info", function() {
	    				if (dataOption.autoclose && data.statusCode =="200") {
	    					$("#ibms_dialogId_2").dialog("close");
	    				}
	    			});
	    		}
	    	});
	    	
		}
	}
	
	$(document.body).append("<div id='ibms_dialogId_2'></div>");
	
	$(".auto-addbutton_2").click(function(){
		var dataOption = parseOption(this);
		var title = dataOption.title || "添加";
		var width = dataOption.d_width || '90%';
		var height = dataOption.d_height || '80%';
		$("#ibms_dialogId_2").dialog({   
		    title: title,   
		    width: width,   
		    height: height,
		    closed: false,
		    cache: false,
		    href: dataOption.url,
		    onLoad:function(){
		    	$(".auto-savebutton").click(function(){bindSave(this);});
				$(".auto-resetbutton").click(function() {
					$(this).parents("form").form("reset");
				});
		    }, 
		    onClose:function(){
		    	$("#ibms_dialogId_2").html("");
		    	$(".easyui-datagrid_2").datagrid('load',{}); 
		    },
		    modal: true  
		});
	});
	
	$(".auto-editbutton_2").click(function(){
		var dataOption = parseOption(this);
		var index = dataOption.index || "id";
		var checkedList = $(".easyui-datagrid_2").datagrid('getChecked');
		var title = dataOption.title || "编辑";
		var width = dataOption.d_width || '90%';
		var height = dataOption.d_height || '80%';
		var alarmOne = dataOption.alarmOne || "请选择要编辑的数据";
		var alarmMany = dataOption.alarmMany || "只能选择一行进行编辑!";
		if(!checkedList.length){
			IBMS.messager.alert('提示消息',alarmOne,"info");
		}else if(checkedList.length!=1){
			IBMS.messager.alert('提示消息',alarmMany,"info");
		}else{
			var param = {};
			param[index] = checkedList[0][index];
			$("#ibms_dialogId_2").dialog({   
			    title: title,   
			    width: width,   
			    height: height,   
			    closed: false,   
			    cache: false,   
			    href:dataOption.url,
			    method:"post",
			    queryParams:param,
			    onLoad:function(){  
			    	$(".auto-savebutton").click(function(){bindSave(this);});
					$(".auto-resetbutton").click(function() {
						$(this).parents("form").form("reset");
					});
			    	//为保存按钮绑定事件
			    }, 
			    onDestroy:function(){alert("destroy");},
			    onClose:function(){
			    	$("#ibms_dialogId_2").html("");
// 			    	$(".auto-querybutton").click();
			    	$(".easyui-datagrid_2").datagrid('load',{}); 
			    },
			    modal: true  
			});
		}
	});
	
	$(".auto-delbutton_2").click(function(){
		var dataOption = parseOption(this);
		var index = dataOption.index || "id";
		var checkedList = $(".easyui-datagrid_2").datagrid('getChecked');
		if(!checkedList.length){
			$.messager.alert('提示消息','请选择要删除的数据!',"info");
		}else{
			var alarmInfo = dataOption.alarmInfo || "确定要删除么?";
			IBMS.messager.confirm('确认', alarmInfo, function(r){
				if (r){
						var param = {};
						var indexArray=[];
						$.each(checkedList,function(k,v){
							indexArray.push(v[index]);					
						});
						param[index] = indexArray.join(",");
						IBMS.post(dataOption.url, param,function(data) {
							if (data.message) {
								IBMS.messager.alert("提示消息", data.message, "info", function() {
									$(".auto-querybutton").click();
								});
							}
					});
				}
			});
		}
	});
});

</script>