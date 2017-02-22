<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/styles/css/timeline.css'/>">
<div id="" class="easyui-layout" style="width: 100%; height: 100%;">
	<div data-options="region:'north'"
		style="height: 57px; overflow: hidden;">
		<div class="easyui-panel"
			data-options="iconCls:'icon-man',border:false" title="流程管理"></div>
		<div class="easyui-toolbar">
			<a href="javascript:void(0)"
				class="easyui-linkbutton resource-reloadbutton"
				data-options="iconCls:'icon-save',plain:true" onclick="savepr();">保存</a>
		</div>
	</div>
	<div data-options="region:'west'" style="width: 50%; padding: 5px;">
		<div class="timeline">
			<div pid="1" class="active">
				<h1></h1>
				<h2>租赁申请</h2>
			</div>
			<div pid="2">
				<h1></h1>
				<h2>订单处理</h2>
			</div>
			<div pid="3">
				<h1></h1>
				<h2>项目处理</h2>
			</div>
			<div pid="4">
				<h1></h1>
				<h2>项目复核</h2>
			</div>
			<div pid="5">
				<h1></h1>
				<h2>信审</h2>
			</div>
			<div pid="6">
				<h1></h1>
				<h2>信审复核</h2>
			</div>
			<div pid="7">
				<h1></h1>
				<h2>合同到司确认</h2>
			</div>
			<div pid="8">
				<h1></h1>
				<h2>合同签约</h2>
			</div>
			<div pid="9">
				<h1></h1>
				<h2>押金核销</h2>
			</div>
			<div pid="10">
				<h1></h1>
				<h2>发货</h2>
			</div>
			<div pid="11">
				<h1></h1>
				<h2>到货确认</h2>
			</div>
		</div>
	</div>
	<div data-options="region:'center'" style="padding: 5px;">
		<div class="timeline-title">
			<span>订单处理</span>权限
		</div>
		<ul id="tt">
		</ul>
	</div>
	<script type="text/javascript">
$(function(){
	
	$('#tt').tree({
		url : WEB_APP + '/web/progress/getTree',
		lines : true,
		checkbox:true,
		onDblClick:function(node){
			$("#editDiv").load(parseOption($(".resource-editbutton")).url+"?id="+$('#tree').tree('getSelected').id);
		},
		onLoadSuccess:function(node){
			   IBMS.post("${pageContext.request.contextPath}/web/progress/getRoleByPid",{"pid":1},
			    		function(data){
			   		var nodes = $('#tt').tree('getChecked');
				   	 $.each(nodes,function(n,value) { 
				   		$('#tt').tree('uncheck', value.target);
					 });
			   	 $.each(data,function(n,value) { 

			   		node = $('#tt').tree('find', value);
			   		$('#tt').tree('check', node.target);
				 });
			    });
		}
	});
	
	
    $('.timeline > div').on('click',function(){
	    $('.timeline > div.active').removeClass('active');
	    pid =  $(this).attr("pid");
	   IBMS.post("${pageContext.request.contextPath}/web/progress/getRoleByPid",{"pid":pid},
	    		function(data){
	   		var nodes = $('#tt').tree('getChecked');
		   	 $.each(nodes,function(n,value) { 
		   		$('#tt').tree('uncheck', value.target);
			 });
	   	 $.each(data,function(n,value) { 

	   		node = $('#tt').tree('find', value);
	   		$('#tt').tree('check', node.target);
		 });
	    });
	    
		$(this).addClass('active');
	});	
});
function savepr(){
	var pid = $('.timeline > div.active').attr('pid');
	var nodes = $('#tt').tree('getChecked');
	var rids = '';
	 $.each(nodes,function(n,value) { 
		 rids = rids+value.id+",";
	 });
	console.info(pid);
	console.info(rids);
	IBMS.post('${pageContext.request.contextPath}/web/progress/saveRolePid',{'pid':pid,'rids':rids});
}
</script>
</div>