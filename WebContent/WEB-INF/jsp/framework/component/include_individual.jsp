<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">

	$(function() {
		
		$(".auto-resetbutton").click(function() {
			$(this).parents("form")[0].reset();
		});
		
		$("#ibms_dialogId :text").addClass("ibms_form_default_input");
		$("#ibms_dialogId select").addClass("ibms_form_default_select");
		//alert($("#ibms_dialogId:first-child"))
		//$("#ibms_dialogId").before("<div id='gggs'>");
		//$("#ibms_dialogId").after("</div>");
		$("#ibms_dialogId .easyui-panel").addClass("dean");
		$("#ibms_dialogId .easyui-datagrid").addClass("dean");
		
		$(window).resize(function(){
			var width = parent.document.body.clientWidth*0.8; 
			if(!$('.sidebar-btn',window.parent.document).hasClass('closed')){ 
				
				setTimeout(function(){ 
					$('#ibms_dialogId .easyui-panel').panel('resize',{ 
						width:function(){return Math.ceil(width-195);}() 
					}); 
					$('#ibms_dialogId .easyui-datagrid').datagrid('resize', { 
						width:function(){return Math.ceil(width-195);}() 
				    }); 
			    }, 300 ); 

			}else{ 
				setTimeout(function(){ 
				$('#ibms_dialogId .easyui-panel').panel('resize',{ 
					width:function(){return Math.ceil(width*0.95);}() 
				}); 
				$('#ibms_dialogId .easyui-datagrid').datagrid('resize', { 
					width:function(){return Math.ceil(width*0.95);}() 
				}); 
				}, 400 ); 
			} 
		});
		
		$('#gggs').mCustomScrollbar(
		{
			scrollInertia:300
		});
		
		
		
		//弹出页面查看大图
		$(".POPUP_A").click(function(){
			window.open($(this).find('img').attr('src'))
		})
	});
	

	
	</script>

	

