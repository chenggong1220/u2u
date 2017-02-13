<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>
<%@ include file="/WEB-INF/jsp/framework/component/include_chart.jsp"%>

<script type="text/javascript"
	src="<c:url value='/styles/js/location.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/styles/js/assetType.js'/>"></script>

<form id="queryForm">
	<div class="easyui-panel ibms_form_panel"
		data-options="iconCls:'icon-search',collapsible:true" title="查询">
		<div class="ibms_form_default">
			<ul>
				<li><font>创建时间(始)：</font>
					<h1>
						<input id="startDate" name="startDate" type="text" class="easyui-datebox">
					</h1></li>
				<li><font>创建时间(终)：</font>
					<h1>
						<input id="endDate" name="endDate" type="text" class="easyui-datebox">
					</h1></li>
			</ul>
		</div>
		<div class="ibms_clear"></div>
		<div class="ibms_form_btn">
			<a href="#" class="query_list_button" onclick="queryAll();">查询</a> <a
				href="#" class="auto-resetbutton query_list_button">重 置</a>
		</div>
	</div>
</form>
<div id="hasRent" style="height:400px;width:50%;float:left;"></div>
<div id="hasPay" style="height:400px;width:50%;float:left;"></div>
  <script type="text/javascript">
        // 基于准备好的dom，初始化echarts图表
        var hasRent = echarts.init(document.getElementById('hasRent')); 
        var hasPay = echarts.init(document.getElementById('hasPay')); 

        // 为echarts对象加载数据 
        function getHasRent(){
        	var sd = $("#startDate").datebox('getValue')||"1990-01-01";
        	var ed = $("#endDate").datebox('getValue')||"2990-01-01";
        	$.ajax({
    			url : '${pageContext.request.contextPath}/web/report/getHasRent',
    			type : 'post',
    			dataType : 'json',
    			data : {"startDate":sd,"endDate":ed},
    			success : function(data){
    				legendarray = []
    				$.each(data,function(i,v){
    					legendarray.push(v.name);
    				});
    				var option = {
    	             	    title : {
    	             	        text: '已租设备',
    	             	        x:'center'
    	             	    },
    	             	    tooltip : {
    	             	        trigger: 'item',
    	             	        formatter: "{a} <br/>{b} : {c} ({d}%)"
    	             	    },
    	             	    legend: {
    	             	        orient : 'horizontal',
    	             	        x : 'center',
    	             	        y:'bottom',
    	             	        data:legendarray
    	             	    },
    	             	    calculable : true,
    	             	    series : [
    	             	        {
    	             	            name:'设备数量',
    	             	            type:'pie',
    	             	            radius : '55%',
    	             	            center: ['50%', '60%'],
    	             	            data:data
    	             	        }
    	             	    ]
    	             	};
    	         
    				if(data.length==0){
    					console.info("clear")
    					hasRent.clear();
    				}else{	 hasRent.setOption(option);}
    			}
        	});
        	 
        }
        function getHasPay(){
        	var sd = $("#startDate").datebox('getValue')||"1990-01-01";
        	var ed = $("#endDate").datebox('getValue')||"2990-01-01";
        	$.ajax({
    			url : '${pageContext.request.contextPath}/web/report/getHasPay',
    			type : 'post',
    			dataType : 'json',
    			data : {"startDate":sd,"endDate":ed},
    			success : function(data){
    				legendarray = []
    				$.each(data,function(i,v){
    					legendarray.push(v.name);
    				});
    				var payOption = {
    	             	    title : {
    	             	        text: '收入汇总',
    	             	        x:'center'
    	             	    },
    	             	    tooltip : {
    	             	        trigger: 'item',
    	             	        formatter: "{a} <br/>{b} : {c} ({d}%)"
    	             	    },
    	             	    legend: {
    	             	        orient : 'horizontal',
    	             	        x : 'center',
    	             	        y:'bottom',
    	             	        data:legendarray
    	             	    },
    	             	    calculable : true,
    	             	    series : [
    	             	        {
    	             	            name:'收入',
    	             	            type:'pie',
    	             	            radius : '55%',
    	             	            center: ['50%', '60%'],
    	             	            data:data
    	             	        }
    	             	    ]
    	             	};
    	         
    				if(data.length==0){
    					console.info("clear")
    					hasPay.clear();
    				}else{	 hasPay.setOption(payOption);}
    			}
        	});
        	 
        }
       function queryAll(){
    	   getHasRent();
    	   getHasPay();
       }
       queryAll();
    </script>