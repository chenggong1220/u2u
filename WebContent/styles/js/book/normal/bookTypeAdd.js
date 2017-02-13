$(function() {

	$(".resource_save_button").click(function(){
		var form = $(this).parents("form");
    	if(!form.form('validate')){
			return;
		}
		var dataOption = parseOption(this);
		if (dataOption) {
			IBMS(form).post(dataOption.url, function(data) {
				if (data.message) {
					IBMS.messager.alert("提示消息", data.message, "info",
					function() {
						$("#editDiv").html("");
						var node = $('#tree').tree('getSelected');
						if (node) {
		                    if($("#tree").tree("isLeaf",node.target)){
		                    	$('#tree').tree('reload', $('#tree').tree("getParent",node.target).target);
		                    }else{
		                    	$('#tree').tree('reload', node.target);
		                    }
		                }
		                else {  
		                    $('#tree').tree('reload');  
		                }
					});
				}
			});
		}
	});
	
});