$(function() {

	//宽度高度自适应
	var res = $('#resource');
	var winh = $(window).height();
	var winw = $(window).width();
	res.css('height',winh+'px');
	res.css('width',winw+'px');
	
	$(".resource-addbutton").click(function() {
		var dataOption = parseOption(this);
		var node = $('#tree').tree('getSelected');
		if (node == null) {
			$.messager.alert('提示消息', '请选中父资源进行添加!', 'info');
		} else {
			$("#editDiv").load(dataOption.url+"?id="+node.id);
		}
	});

	$(".resource-editbutton").click(function() {
		var dataOption = parseOption(this);
		var node = $('#tree').tree('getSelected');
		if (node == null) {
			$.messager.alert('提示消息', '请选中资源进行修改!', 'info');
		} else {
			$("#editDiv").load(dataOption.url+"?id="+node.id);
		}
	});

	$(".resource-removebutton").click(function() {
		var dataOption = parseOption(this);
		var node = $('#tree').tree('getSelected');
		if (node == null) {
			$.messager.alert('提示消息', '请选中一条资源进行删除!', 'info');
		} else {
			$.messager.confirm('确认', '确定要执行删除操作？', function(r) {
				if (r) {
					$.post(dataOption.url, {
						id : node.id
					}, function(data, textStatus, jqXHR) {
						$.messager.alert('消息', data.message);
						if(data.statusCode=="200"){
							$('#tree').tree('reload',$('#tree').tree('getParent',node.target).target);
						}
					});
				}
			});
		}
	});
	
	$(".resource-reloadbutton").click(function() {
		var node = $('#tree').tree('getSelected');
		if (node == null) {
			$.messager.alert('提示消息', '请选中一条资源进行操作!', 'info');
		} else {
			$('#tree').tree('reload', node.target);
		}
	});
});