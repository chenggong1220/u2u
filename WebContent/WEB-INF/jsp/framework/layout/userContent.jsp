<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.sys2.jsp"%>

<script type="application/javascript">
function reloadTarget(){
	var rte = "${rte}";
	if(rte==null||rte==""){
		$("#ibms_main_content_01").html("未配置相关URL，请检查资源配置！");
	}else{
		if(rte.indexOf("?")==-1){
			rte += "?resourceId=${resourceId}&r="+Math.random();
		}else{
			rte += "&resourceId=${resourceId}&r="+Math.random();
		}
		$("#ibms_main_content_01").load(WEB_APP + rte);
	}
}

$(function(){
	reloadTarget();
});
</script>
<div  id="ibms_main_content_01" style="margin:0 8px 0 0;">

</div>
<script type="application/javascript">

$('body').mCustomScrollbar(
		{
			scrollInertia:300
		}
);

</script>
