<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	    					$("#ibms_dialogId").dialog("close");
	    				}
	    			});
	    		}
	    	});
	    	
		}
	}
	
	function bindTableSave(button){
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
		
		var checkedList = $(".easyui-datatablegrid").datagrid('getChecked');
		if(!checkedList.length){
			$.messager.alert('提示消息','请选择要添加的数据!',"info");
		}else{
			if(dataOption){
		    	IBMS(form).post(dataOption.url,function(data){
		    		if (data.message) {
		    			IBMS.messager.alert("提示消息", data.message, "info", function() {
		    				if (dataOption.autoclose && data.statusCode =="200") {
		    					$("#ibms_dialogId").dialog("close");
		    				}
		    			});
		    		}
		    	});
		    	
			}	
		}
	}
	
	$(document.body).append("<div id='ibms_dialogId'></div>");
	
	$(".auto-addbutton").click(function(){
		var dataOption = parseOption(this);
		var title = dataOption.title || "添加";
		var width = dataOption.d_width || '90%';
		var height = dataOption.d_height || '80%';
		$("#ibms_dialogId").dialog({   
		    title: title,   
		    width: width,   
		    height: height,
		    closed: false,
		    cache: false,
		    href: dataOption.url,
		    onLoad:function(){
		    	$(".auto-savebutton").click(function(){bindSave(this);});
		    	$(".auto-tablesavebutton").click(function(){bindTableSave(this);});
				$(".auto-resetbutton").click(function() {
					$(this).parents("form").form("reset");
				});
		    }, 
		    onClose:function(){
		    	$("#ibms_dialogId").html("");
		    	$(".auto-querybutton").click();
		    },
		    modal: true  
		});
	});
	
	$(".auto-editbutton").click(function(){
		var dataOption = parseOption(this);
		var index = dataOption.index || "id";
		var checkedList = $(".easyui-datagrid").datagrid('getChecked');
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
			$("#ibms_dialogId").dialog({   
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
			    	$(".auto-tablesavebutton").click(function(){bindTableSave(this);});
					$(".auto-resetbutton").click(function() {
						$(this).parents("form").form("reset");
					});
			    	//为保存按钮绑定事件
			    }, 
			    onDestroy:function(){alert("destroy");},
			    onClose:function(){
			    	$("#ibms_dialogId").html("");
			    	$(".auto-querybutton").click();
			    },
			    modal: true  
			});
		}
	});
	
	$(".auto-delbutton").click(function(){
		var dataOption = parseOption(this);
		var index = dataOption.index || "id";
		var checkedList = $(".easyui-datagrid").datagrid('getChecked');
		if(!checkedList.length){
			$.messager.alert('提示消息','请选择要操作的数据!',"info");
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
	
	//Start: 增加删除订单操作 ，by SUNZHE, 2017-02-11 -->
	$(".delorderbutton").click(function(){
		var dataOption = parseOption(this);
		var index = dataOption.index || "id";
		var checkedList = $(".easyui-datagrid").datagrid('getChecked');
		if(!checkedList.length){
			$.messager.alert('提示消息','请选择要操作的数据!',"info");
		}else if(checkedList[0].status != "租赁申请"){
			IBMS.messager.alert('提示消息',"只能对【租赁申请】状态的订单操作!","info");
			return;
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
	//End: 增加删除订单操作 ，by SUNZHE, 2017-02-11 -->
	
	//Start: 增加转换核销项操作 ，by SUNZHE, 2017-03-12 -->
	$(".convertbillcheckbutton").click(function(){
		var dataOption = parseOption(this);
		var index = dataOption.index || "id";
		var checkedList = $(".easyui-datagrid").datagrid('getChecked');
		if(!checkedList.length){
			$.messager.alert('提示消息','请选择要操作的数据!',"info");
		}else if(checkedList[0].paySourceStr == "银行汇款"){
			IBMS.messager.alert('提示消息',"不能对【银行汇款】的记录进行转换!","info");
			return;
		}else{
			var alarmInfo = dataOption.alarmInfo || "确定要执行该操作吗?";
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
	//End: 增加转换核销项操作 ，by SUNZHE, 2017-03-12 -->	
	
	$(".auto-verifybutton").click(function(){
		var dataOption = parseOption(this);
		var index = dataOption.index || "id";
		var checkedList = $(".easyui-datagrid").datagrid('getChecked');
		if(checkedList[0].status != "租赁申请"){
			IBMS.messager.alert('提示消息',"只能对【租赁申请】状态的订单操作!","info");
			return;
		}
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
			$("#ibms_dialogId").dialog({   
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
			    	$(".auto-tablesavebutton").click(function(){bindTableSave(this);});
					$(".auto-resetbutton").click(function() {
						$(this).parents("form").form("reset");
					});
			    	//为保存按钮绑定事件
			    }, 
			    onDestroy:function(){alert("destroy");},
			    onClose:function(){
			    	$("#ibms_dialogId").html("");
			    	//$(".auto-querybutton").click();
			    },
			    modal: true  
			});
		}
	});
	
//Start: Order Modifiy, SUNZHE, 2017-06-22
/*
	$(".modifyorderbutton").click(function(){
		var dataOption = parseOption(this);
		var index = dataOption.index || "id";
		var checkedList = $(".easyui-datagrid").datagrid('getChecked');
		if(checkedList[0].status != "租赁申请"){
			IBMS.messager.alert('提示消息',"只能对【租赁申请】状态的订单操作!","info");
			return;
		}
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
			$("#ibms_dialogId").dialog({   
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
			    	$(".auto-tablesavebutton").click(function(){bindTableSave(this);});
					$(".auto-resetbutton").click(function() {
						$(this).parents("form").form("reset");
					});
			    	//为保存按钮绑定事件
			    }, 
			    onDestroy:function(){alert("destroy");},
			    onClose:function(){
			    	$("#ibms_dialogId").html("");
			    	//$(".auto-querybutton").click();
			    },
			    modal: true  
			});
		}
	});
*/

//End: Order Modify, SUNZHE, 2017-06-22
	
// 	$(".auto-downloadbutton").click(function(){
// 		var dataOption = parseOption(this);
// 		var index = dataOption.index || "id";
// 		var checkedList = $(".easyui-datagrid").datagrid('getChecked');
// 		if(!checkedList.length){
// 			$.messager.alert('提示消息','请选择要操作的数据!',"info");
// 		}else{
// 			var param = {};
// 			var indexArray=[];
// 			$.each(checkedList,function(k,v){
// 				indexArray.push(v[index]);				
// 			});
// 			param[index] = indexArray.join(",");
// 			IBMS.post(dataOption.url, param,function(data) {
// 				console.log(data.statusCode);
// 				console.log('${pageContext.request.contextPath}' + data.returnUrl);
// 				if (data.statusCode=='300') {
// 					IBMS.messager.alert("提示消息", data.message, "error");
// 				}else if(data.statusCode=='200'){
// // 					IBMS.get();
// 					console.log('${pageContext.request.contextPath}' + data.returnUrl);
// 					window.open('${pageContext.request.contextPath}' + data.returnUrl);
// 				}
// 			});
// 		}
// 	});
	
	//绑定excel导出
	$(".auto-exportbutton").click(function(){
		exportExcel();
	});
	
});
	$(function() {
		$(window).resize(function(){
			LiResize();//调用获取表单中li的宽度方法
			var width = parent.document.body.clientWidth; 
			if(!$('.sidebar-btn',window.parent.document).hasClass('closed')){ 
				setTimeout(function(){ 
					$('.easyui-panel:not(.dean)').panel('resize',{ 
						width:function(){return Math.ceil(width-213);}() 
					}); 
					$('.easyui-datagrid:not(.dean)').datagrid('resize', { 
						width:function(){return Math.ceil(width-213);}() 
				    });
					$('.easyui-tabs:not(.dean)').tabs('resize',{ 
						width:function(){return Math.ceil(width-213);}() 
					});
					$('.easyui-layout:not(.dean)').layout('resize',{ 
						width:function(){return Math.ceil(width-213);}() 
					});
			    }, 200 ); 

			}else{ 
				$('.easyui-panel:not(.dean)').panel('resize',{ 
					width:function(){return Math.ceil(width-39);}() 
				}); 
				$('.easyui-datagrid:not(.dean)').datagrid('resize', { 
					width:function(){return Math.ceil(width-39);}() 
				});
				$('.easyui-tabs:not(.dean)').tabs('resize',{ 
					width:function(){return Math.ceil(width-39);}() 
				});
				$('.easyui-layout:not(.dean)').layout('resize',{ 
					width:function(){return Math.ceil(width-39);}() 
				});
			} 
		});
		$(window).resize();
		
		$(":text").addClass("ibms_form_default_input");
		$("select").addClass("ibms_form_default_select");
		
		$.parser.parse("#ibms_main_content_01");
		
		//为查询按钮绑定事件
		$(".auto-querybutton").click( function() {
			var param = {};
			var a = $(this).parents("form").serializeArray();
			$.each(a, function(i,val){   
				param[val.name] = val.value;
			  }); 
			if(!$(this).parents("form").form('validate')){
				return;
			}
			$(".easyui-datagrid").datagrid('load', param); 
		});
		
		$(".auto-resetbutton").click(function() {
			$(this).parents("form").form("reset");
		});
	});
	
	function LiResize(){ //获取表单中li的宽度
		var _li = $('.ibms_form_default ul li');
		var win = $(window).outerWidth();//获取表单的宽度
		for(var i = 4; i>0;i--){ 
			if(win/i>275){
				_li.width(Math.round(win/i)-15);
				return true;
			}
		}
	}
	function openDialog(button){
		var dataOption=parseOption(button);
		console.info(dataOption);
		$(button).append("<div id='dcim_tmp_dialogId'></div>");
		$("#dcim_tmp_dialogId").dialog({   
		    title: dataOption.title||'标题',   
		    width: dataOption.width||"80%",   
		    height: dataOption.height||"60%",   
		    closed:dataOption.closed|| false,   
		    cache: dataOption.cache||false,   
		    href: dataOption.url,
		    onOpen:function(){   
		    	if($.isFunction(dataOption.onOpen)){
		    		dataOption.onOpen();
		    	}
		    }, 
		    onClose:function(){
		    	if($.isFunction(dataOption.onClose)){
		    		dataOption.onClose();
		    	}
		    	$("#dcim_tmp_dialogId").html("");
		    },
		    modal: true  
		});
	}
	</script>



