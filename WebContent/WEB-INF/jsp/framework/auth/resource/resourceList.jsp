<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<script type="text/javascript"
	src="<c:url value='/auth/resource/js/resourceList.js'/>"></script>

<div id="resource" class="easyui-layout" style="height: 100%;">
	<div data-options="region:'north'"
		style="height: 57px; overflow: hidden;">
		<div class="easyui-panel"
			data-options="iconCls: 'icon-edit',border:false" title="资源列表"></div>
		<div class="easyui-toolbar">
			<a href="javascript:void(0)"
				class="easyui-linkbutton resource-addbutton"
				data-options="iconCls:'icon-add',plain:true,url:'${pageContext.request.contextPath}/auth/resource/add'">添加子资源</a>
			<a href="javascript:void(0)"
				class="easyui-linkbutton resource-editbutton"
				data-options="iconCls:'icon-edit',plain:true,url:'${pageContext.request.contextPath}/auth/resource/edit'">编辑</a>
			<a href="javascript:void(0)"
				class="easyui-linkbutton resource-removebutton"
				data-options="iconCls:'icon-remove',plain:true,url:'${pageContext.request.contextPath}/auth/resource/delete'">删除</a>
			<a href="javascript:void(0)"
				class="easyui-linkbutton resource-reloadbutton"
				data-options="iconCls:'icon-reload',plain:true">刷新</a>
		</div>
	</div>
	<div id="treeDiv" data-options="region:'west'"
		style="width: 270px; padding: 5px;">
		<ul id="tree"></ul>
	</div>
	<div id="editDiv" data-options="region:'center'" style="padding: 10px;">
	</div>
</div>

<script>
$('#tree').tree({
	url : WEB_APP + '/auth/resource/getResources',
	lines : true,
	onDblClick:function(node){
		$("#editDiv").load(parseOption($(".resource-editbutton")).url+"?id="+$('#tree').tree('getSelected').id);
	},
	onLoadSuccess:function(node){
		var rootNode = $("#tree").tree('getRoot');
		$("#tree").tree('expand', rootNode.target);
	}
});
</script>
