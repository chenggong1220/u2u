<%@ include
	file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<form id="addForm">
	<input type="hidden" name="id" value="${department.id }">
	<div class="easyui-panel ibms_form_panel">
		<div class="ibms_form_default">
			<ul>
				<li><font>部门等级：</font>
				<h1>
						<input id="level" name="level" type="text" class="easyui-combobox">
					</h1></li>
			</ul>
			<ul>
				<li><font>上级部门：</font>
				<h1>
						<input id="parentId" name="parentId" type="text"
							value="${department.parentId}">
					</h1></li>
			</ul>
			<ul>
				<li><font>部门名称：</font>
				<h1>
						<input id="name" name="name" type="text"
							value="${department.name}">
					</h1></li>
			</ul>
		</div>
		<div class="ibms_clear"></div>
		<div class="ibms_form_btn">
			<a href="#" class="query_list_button auto-savebutton"
				data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/auth/department/update',autoclose:true">提交</a>
			<a href="#" class="query_list_button auto-resetbutton">重 置</a>
		</div>
	</div>
</form>

<script type="text/javascript">
 	var data= [];
  data.push({ "text": "1级", "value": '1'});
  data.push({ "text": "2级", "value": '2'});
  data.push({ "text": "3级", "value": '3'});
  $('#level').combobox({
  	editable : false,
  	required : true,
  	data : data,
      onLoadSuccess:function(){
      	$(this).combobox('setValue', '${department.level}');	
      }
  });
</script>