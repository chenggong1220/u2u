<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/component/include.jsp"%>

<form id="queryForm">
	<div class="easyui-panel ibms_form_panel"
		data-options="iconCls:'icon-search',collapsible:true" title="报表条件">
		<!-- ---------------------------		 -->
				<div class="ibms_form_default">
					<ul>
						<li id="reportDTType"><font>统计类型：</font>
							<h1>
								<!-- 							
									<b><input type="radio" name="dtType" value="0" id="Radio1"/>按周</b>
								-->
								<b><input type="radio" name="dtType" value="1" id="Radio2" checked="checked" />按月</b>
								<b><input type="radio" name="dtType" value="3" id="Radio3" />任意时段</b>
							</h1>
						</li>					
					</ul>
					<script type="text/javascript">
			        	$("#reportDTType input:radio[name='dtType']").change(function(){
			        		var val = $("#reportDTType input:radio[name='dtType']:checked").val();
			        		console.log(val);
			        		if(val=='0'){
			        			$("#monthlyRptDiv").hide();
			        			$("#anyTimeRptDiv").hide();
			        			$("#weeklyRptDiv").show();
			        		}else if(val=='1'){
			        			$("#weeklyRptDiv").hide();
			        			$("#anyTimeRptDiv").hide();
			        			$("#monthlyRptDiv").show();
			        			$("#startDate").datebox("setValue", "");
			        			$("#endDate").datebox("setValue", "");
			        		}else{
			        			$("#weeklyRptDiv").hide();
			        			$("#anyTimeRptDiv").show();
			        			$("#monthlyRptDiv").hide();	
			        			$("#billingMonth").datebox("setValue", "");
			        		}
			        	});
			        	$("#weeklyRptDiv").hide();
	        			$("#monthlyRptDiv").show();
	        			$("#anyTimeRptDiv").hide();
			        </script>
				</div>
			
				<div class="easyui-panel ibms_form_panel">
					<div class="ibms_form_default">
						<div class="ibms_clear"></div>
						<div id="weeklyRptDiv">
							<ul>
								<li><font>申请人：</font>
								<h1>
										<input id="name" name="name" type="text">
									</h1></li>
							</ul>
						</div>
						<div id="monthlyRptDiv">
							<ul>
								<li><font>入账月份：</font>
								<h1>
									<input id="billingMonth" editable="false" name="billingMonth" class="easyui-datebox" >
								</h1></li>
							</ul>
						</div>
						<div id="anyTimeRptDiv">
							<ul>
								<li><font>入账时间(始)：</font>
									<h1>
										<input id="startDate" name="startDate" type="text"
											class="easyui-datebox">
									</h1></li>
								<li><font>入账时间(终)：</font>
									<h1>
										<input id="endDate" name="endDate" type="text"
											class="easyui-datebox">
									</h1></li>
							</ul>
						</div>						
						<div class="ibms_clear"></div>
						<div>
							<ul>
								<li><font>入账类型：</font>
									<h1>
										<select id="billType" name="billType" class="easyui-combobox"
											data-options="editable:false">
											<option value="">全部</option>
											<option value="0">保证金</option>
											<option value="1">服务费</option>
											<option value="2">租金</option>
										</select>
								</h1></li>							
								<li><font>合同编号：</font>
									<h1>
										<input id="contractNo" name="contractNo" type="text">
								</h1></li>
								<li><font>承租人类型：</font>
									<h1>
										<select id="custType" name="custType" class="easyui-combobox"
											data-options="editable:false">
											<option value="">全部</option>
											<option value="0">企业</option>
											<option value="1">个人</option>
										</select>
								</h1></li>									
								<li><font>承租人名称：</font>
									<h1>
										<input id="cusName" name="cusName" type="text">
								</h1></li>	
								
							</ul>
						</div>					
					</div>
				</div>			
		<!-- ---------------------------		 -->					

		
		<div class="ibms_clear"></div>
		<div class="ibms_form_btn">
			<a href="#" class="auto-querybutton query_list_button">查 询</a> <a
				href="#" class="auto-resetbutton query_list_button">重 置</a>
		</div>
	</div>
</form>

<table id="querygrid" class="easyui-datagrid" title="还款统计报表"
	data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				pagination:true,
				rownumbers:true,
				 fitColumns: true,
				singleSelect:true,
				url: '${pageContext.request.contextPath}/web/report/billingDetails',
				pageSize:10,
				pageList: [1,2,5,10],
				collapsible:true,
				method: 'post'
			">

	<thead>
		<tr>
			<th data-options="field:'contractNo',width:'15%'">合同编号</th>
			<th data-options="field:'cusName',width:'20%'">承租人</th>
			<th data-options="field:'dealDate',width:'10%'">入账时间</th>
			<th data-options="field:'deposit',width:'10%',formatter:
				function(value,rec){
					if(rec.status==true){
						if(value==0){
							return '保证金';
						}else if(value == 1){
							return '服务费';
						}else if(value == 2){
							return '租金';
						}else{
							return '';
						}
					}
				}">入账类型</th>
			<th data-options="field:'amount',width:'10%'">汇款金额</th>
			
<!-- 			
合同编号、承租人、入账时间、入账类型、汇款金额	
-->		
		</tr>
	</thead>
</table>
<!-- 
	<div id="tb" style="height: auto">
		<a href="javascript:void(0)"
			class="easyui-linkbutton auto-exportbutton"
			data-options="iconCls:'icon-excel',plain:true">导出</a>
	</div>
-->

<script type="text/javascript">
$(function() {

	   $('#billingMonth').datebox({
	       //显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
	       onShowPanel: function () {
	          //触发click事件弹出月份层
	          span.trigger('click'); 
	          if (!tds)
	            //延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
	            setTimeout(function() { 
	                tds = p.find('div.calendar-menu-month-inner td');
	                tds.click(function(e) {
	                   //禁止冒泡执行easyui给月份绑定的事件
	                   e.stopPropagation(); 
	                   //得到年份
	                   var year = /\d{4}/.exec(span.html())[0] ,
	                   //月份
	                   //之前是这样的month = parseInt($(this).attr('abbr'), 10) + 1; 
	                   month = parseInt($(this).attr('abbr'), 10);  

	         //隐藏日期对象                     
	         $('#billingMonth').datebox('hidePanel') 
	           //设置日期的值
	           .datebox('setValue', year + '-' + month); 
	                        });
	                    }, 0);
	            },
	            //配置parser，返回选择的日期
	            parser: function (s) {
	                if (!s) return new Date();
	                var arr = s.split('-');
	                return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
	            },
	            //配置formatter，只返回年月 之前是这样的d.getFullYear() + '-' +(d.getMonth()); 
	            formatter: function (d) { 
	                var currentMonth = (d.getMonth()+1);
	                var currentMonthStr = currentMonth < 10 ? ('0' + currentMonth) : (currentMonth + '');
	                return d.getFullYear() + '-' + currentMonthStr; 
	            }
	        });

	        //日期选择对象
	        var p = $('#billingMonth').datebox('panel'), 
	        //日期选择对象中月份
	        tds = false, 
	        //显示月份层的触发控件
	        span = p.find('span.calendar-text'); 
	        var curr_time = new Date();

	        //设置前当月
	        $("#billingMonth").datebox("setValue", myformatter(curr_time));
	});
	
	//格式化日期
	function myformatter(date) {
	    //获取年份
	    var y = date.getFullYear();
	    //获取月份
	    var m = date.getMonth() + 1;
	    return y + '-' + m;
	}	
</script>