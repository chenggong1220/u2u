//define(function() {

//常量定义
var maskId = "body";
//var maskId = "#mask";
var formId = "#formid";

var IBMS = function(selector, context) {
	return new IBMS.prototype.init(selector, context);
};

IBMS.prototype = {
	init : function(selector, context) {
		this.selector = selector;
		return this;
	},
	load : function(url, maskId, params, callback) {
		maskId = maskId || "body";
		$(maskId).showLoading();
		$(this.selector).load(url, params, callback);
		$(maskId).hideLoading();
	},
	post : function(url, data, callback, type) {
		
		if($.isFunction(data)){
			callback = data;
			data = "";
		}
		options = {
				url : url,
				type : "post",
				dataType : type,
				data : data|| $(this.selector).serialize(),
				success : callback|| defaultsuccess,
				complete:function(){$(maskId).hideLoading();}
			};
		// 表单验证
//		if (!$(this.selector).form('validate')) {
//			return;
//		}
		$(maskId).showLoading();
		$.ajax(url, options);
	}
};
// 引入jQuery
//IBMS.prototype = $.extend({}, $.prototype, IBMS.prototype);
// 原型对象覆盖init构造器的原型对象
IBMS.prototype.init.prototype = IBMS.prototype;

// IBMS常量定义
IBMS.statusCode = {
	ok : 200,
	error : 300,
	timeout : 301
};
IBMS.resource  = {
		jianzhu : 255,
		peidian : 256,
		caiji : 257
	};
IBMS.messager = $.messager;
var defaultsuccess = function(data) {
	if (data.message) {
		IBMS.messager.alert("提示消息", data.message, "info", function() {
			if (data.returnUrl) {
				location.href = "../" + data.returnUrl;
			}
		});
	}
};
var defaulterror = function(data) {
	IBMS.messager.alert("错误提示", "网络错误", "error");
};

IBMS.ajax = function(url, options) {

	if (typeof url === "object") {
		options = url;
		url = undefined;
	}
	options = options || {};
	options.success = options.success || defaultsuccess;
	options.error = options.error || defaulterror;
	options.data = options.data || $(formId).serialize();
	// 在回调函数执行前关闭遮罩层
	options.complete = function() {
		$(maskId).hideLoading();
	};

	// 表单验证
	if (!$(formId).form('validate')) {
		return;
	}
	$(maskId).showLoading();
	$.ajax(url, options);
};

IBMS.validate = function() {
	return $(formId).form('validate');
};
$.each([ "get", "post" ], function(i, method) {
	IBMS[method] = function(url, data, callback, type) {
		// shift arguments if data argument was omitted
		if ($.isFunction(data)) {
			type = type || callback;
			callback = data;
			data = undefined;
		}
		return IBMS.ajax({
			url : url,
			type : method,
			dataType : type,
			data : data,
			success : callback
		});
	};
});

//验证方法扩展
$.extend($.fn.validatebox.defaults.rules, {   
	fixLength: {   
        validator: function(value, param){
            return value.length == param[0];
        },   
        message: '请输入{0}个字符.'
    },
	minLength: {   
        validator: function(value, param){   
            return value.length >= param[0];   
        },   
        message: '请至少输入{0}个字符.'
    },   
    maxLength: {
    	validator: function(value, param){   
            return value.length <= param[0];   
        },
        message: '请最多输入{0}个字符.'
    },
    telephone: {   
    	validator: function(value, param){   
    		var r = /^0?1[3|4|5|8][0-9]\d{8}$/;
    		return r.test(value);   
    	},   
    	message: '请输入正确的电话号码.'  
    },   
    number: {   
    	validator: function(value, param){
    		var r = /^\+?[1-9][0-9]*$/;
    		return r.test(value);   
    	},   
    	message: '请输入正整数.'  
    },
    float: {
    	validator: function(value, param){
    		var r = /^\+?\-?(([1-9]\d*)|([1-9]\d*\.[0-9]+)|([0-9]\.[0-9]+)|0)$/;
    		
    		if(param==null){
    			return r.test(value);
    		}else{
    			if(r.test(value)){
        			var splits = value.split('.'); 
        			if(splits.length == 1){
        				return value.length <= param[0] - param[1];
        			}else{
        				return (splits[0].length + splits[1].length) <= param[0] 
        							&& splits[0].length <= param[0] - param[1]
        							&& splits[1].length <= param[1];
        			}
        		}
        		return false;
    		}
    	},
    	message: '请输入浮点数.精度[{0},{1}]'
    }
});

//添加提示
$.extend(true, $.fn.datagrid.defaults.view, {
	onAfterRender:function(target){
		var grid = $(target);
		var data = $(target).data('datagrid');
		if (data) {
			var panel = grid.datagrid('getPanel').panel('panel');
			panel.find('.datagrid-body').each(function() {
				// 获得表格对象
				var delegateEle = $(this).find('> div.datagrid-body-inner').length ? 
					$(this).find('> div.datagrid-body-inner')[0] : this;
				// 单元格添加提示框
				$(delegateEle).find('td').each(function(){
					if($(this).text()){//判断单元格内容是否为空
						// easyui提示框定义
						$(this).tooltip({
							position : 'bottom',
							content : $(this).text(),
							showDelay : 1000
						});
					}
				});
			});
		}
	}
});

//公共方法
function parseOption(button){
	var dataOption=$.trim($(button).attr("data-options"));
	if(dataOption){
    	if(dataOption.substring(0,1)!="{"){
    		dataOption="{"+dataOption+"}";
    	}
    	dataOption = eval("(" + dataOption + ")");
    	}
	return dataOption;
}

function openDialog(button){
	var dataOption=parseOption(button);
	$(button).append("<div id='ibms_tmp_dialogId'></div>");
	$("#ibms_tmp_dialogId").dialog({   
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
	    	$("#ibms_tmp_dialogId").html("");
	    },
	    modal: true  
	});
}

function queryGrid(form,datagrid){
	var param = {};
	var a = $(form).serializeArray();
	$.each(a, function(i,val){   
		param[val.name] = val.value;
	  }); 
	$(datagrid).datagrid('load', param); 
}

function exportExcel(queryGrid,exportUrl){
	queryGrid = queryGrid||".easyui-datagrid";
	var options = $(queryGrid).datagrid('options');
	var columns = options.columns[0];	
	var param = jQuery.extend(true, {}, options.queryParams);
	var title = options.title;
	var url = exportUrl || options.url;
	
//	var data = $(queryForm).serializeArray();
//	var param = {};
//	
//	$.each(data, function(i,val){   
//		param[val.name] = val.value;
//	  }); 
	
	var paramCols = [];
	$.each(columns, function(i,val){ 
		if(val.hidden&&!val.export){return;}
		var paramCol = {};
		paramCol["field"] = val.field;
		paramCol["title"] = val.title;
		paramCols.push(paramCol);
	  }); 
	param["rowNames"] = paramCols;
	param["export"] = 1;
	param["title"] = title;
	//定义一个form表单
	var form=$("<form>");
	form.attr("style","display:none");
	form.attr("method","post");
	form.attr("action",url);
		
	$.each(param,function(i,val){
		var input=$("<input>");
		input.attr("type","hidden");
		input.attr("name",i);
		if(i=="rowNames"){
			input.attr("value",JSON.stringify(val));
		}else{
			input.attr("value",val);
		}
		form.append(input);
	});
	
		//将表单放置在web中
		$("body").append(form);
		form.submit();
}
