$(function() {
	
	//宽度高度自适应
	var $role = $('#role');
	var diah = $('#ibms_dialogId').height();
	var diaw = $('#ibms_dialogId').width();
	$role.css('height',diah+'px');
	$role.css('width',diaw+'px');
	
	$("#cc").tree({
		url : WEB_APP + '/auth/role/getResources',
		method : 'get',
		cascadeCheck : false,
		checkbox : true,
		onLoadSuccess : function() {
			
			selectBox();
			expendRoot();
			
			$('#cc').tree("options")["onCheck"]=
				function(node, checked) {
				if (checked) {
					var parent = $('#cc').tree('getParent', node.target);
					if (parent != null ) {
						$('#cc').tree('check', parent.target);
					}
				}
			};
		}
	});

	function expendRoot(){
		var rootNode = $("#cc").tree('getRoot');
		$("#cc").tree('expand', rootNode.target);
	}
	function selectBox() {
		
		$('#cc').tree("options")["onCheck"] = function(){};
		
		var resources = $('#resourceValues').attr('tvalue');
		if(resources!=null && resources!='' && !resources.match('$/$.*')){
			var resourcesIds = resources.split(',');
			for(var i=0;i<resourcesIds.length;i++){
				var target = $("#cc").tree("find",resourcesIds[i]);
				$('#cc').tree('check', target.target);	
			}
		}
		
		$('#cc').tree("options")["onCheck"] =function(node, checked) {
													if (checked) {
														var parent = $('#cc').tree('getParent', node.target);
														if (parent != null ) {
															$('#cc').tree('check', parent.target);
														}
													}
												};
	}
	
	function resetResources(){
		var checkedNodes = $('#cc').tree('getChecked');
		for(var i=0;i<checkedNodes.length;i++){
			$("#cc").tree('uncheck',checkedNodes[i].target);	
		}
		selectBox();
	}
	
	$("#resetbutton").click(resetResources);

	$('.auto-savebutton').click(function(){
		var checkedNodes = $('#cc').tree('getChecked', ['checked','indeterminate']);
		var ids = '';
		for(var i=0;i < checkedNodes.length;i++){
			if(i==0){
				ids +=checkedNodes[i].id;
			}else{
				ids += ',' + checkedNodes[i].id;
			}
		}
		
		$('#resources').val(ids);
	});
});