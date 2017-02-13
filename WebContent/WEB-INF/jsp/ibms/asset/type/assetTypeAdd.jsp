<%@ include file="/WEB-INF/jsp/framework/component/include_individual.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<form id="addForm" >
	<div class="easyui-panel ibms_form_panel">
	    <div class="ibms_form_default">
	        <ul>
	           <li><font>品牌：</font><h1><input id="brandId" name="brandId"  type="text" class="easyui-combobox"></h1></li>
	           <li><font>型号：</font><h1><input name="model"  type="text" ></h1></li>
	           <li><font>主电机功率：</font><h1><input name="machinePower"  type="text" ></h1></li>
	           <li><font>运动方式：</font><h1><input name="moveMethod"  type="text" ></h1></li>
	           <li><font>加工尺寸：</font><h1><input id="finishSize" name="finishSize"  type="text"></h1></li>
	           <li><font>主轴转速：</font><h1><input id="mainShaftSpeed" name="mainShaftSpeed"  type="text"></h1></li>
	           <li><font>刀具数量：</font><h1><input id="cutterCount" name="cutterCount"  type="text"></h1></li>
	           <li><font>控制方式：</font><h1><input id="controlMethod" name="controlMethod"  type="text"></h1></li>
	           <li><font>控制系统：</font><h1><input id="controlSystem" name="controlSystem"  type="text"></h1></li>
	           <li><font>布局形式：</font><h1><input id="layout" name="layout"  type="text"></h1></li>
	           <li><font>动力类型：</font><h1><input id="driving" name="driving"  type="text"></h1></li>
	           <li><font>保证金：</font><h1><input id="deposit" name="deposit"  type="text"></h1></li>
	           <li><font>净值：</font><h1><input id="amount" name="amount"  type="text"></h1></li>
	           <li><font>保险金额：</font><h1><input id="insuranceAmount" name="insuranceAmount"  type="text" ></h1></li>
	        </ul>
        </div>
        <div class="ibms_clear"></div>
        <div class="ibms_form_default">
	        <ul>
		     	<li><font>设备图片：</font>
           		<h1>
					<b>
						<input type="file" name="pictureSelected" id="pictureSelected" accept="image/gif, image/jpeg, image/bmp, image/png"/>
						<input type="hidden" name="picture" id="picture" value=""/>
					</b>
					<b>
						<input id="upload" type="button" class="query_list_button" value="上传" onclick="javascript:uploadFiles_framework('pictureSelected','picture','picturePreview');">
					</b>
				</h1>
				</li>
		    </ul>
        </div>
        <div class="ibms_clear"></div>
 	      <div class="ibms_form_default_textarea">
    	    <font>图片预览：</font>
        		<h1 style="width:90%;">
					<b>
						<img width="300" height="200" src="" id="picturePreview" name="picturePreview"/>
					</b>
				</h1>
		</div>
        <div class="ibms_clear"></div>
	    <div class="ibms_form_btn">
	        <a href="#" class="query_list_button auto-savebutton" data-options="iconCls:'icon-save',plain:true,url:'${pageContext.request.contextPath}/web/asset/type/save',autoclose:true">保存</a>
	        <a href="#" class="query_list_button auto-resetbutton">重 置</a>
        </div>
	</div>
</form>

<script type="text/javascript">
$('#brandId').combobox({
	editable:false,
    url: WEB_APP + '/web/asset/brand/json',
    valueField:'id',
    textField:'name'
});
</script>
