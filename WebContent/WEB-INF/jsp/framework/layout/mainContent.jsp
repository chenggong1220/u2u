<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<script> var menuSize=0;</script>
<div class="main">
	<div class="to-hide">
		<div class="side-container">
			<ul id="accordion-effect" class="side-menu">
				<c:forEach var="item" items="${menuList}" varStatus="s">
					<c:if test="${item.level == 2}">
						<script>
							menuSize = menuSize + 1;
						</script>
						<li><a class="tit" href="javascript: void(0);">${item.description}</a>
							<div class="cont">
								<div class="inner" style="width: 164px;">
									<ul class="side-sub">
										<c:forEach var="item1" items="${menuList}" varStatus="s1">
											<c:if test="${item1.level == 3 && item1.parentId ==item.id}">
												<li><a tlink="${item1.link}"
													onclick="loadContent(this)" href="#" id="${item1.id }">${item1.description}</a></li>
											</c:if>
										</c:forEach>
									</ul>
								</div>
							</div></li>
					</c:if>
				</c:forEach>
			</ul>
			<a class="sidebar-btn" href="javascript: void(0);"></a>
		</div>
		<!-- .side-container ends -->
	</div>
	<!-- to-hide ends -->
	<div class="content">
		<div class="pagination-wrapper" id="ibms_floor">
			<script type="text/javascript"
				src="<c:url value='/styles/js/lib/floor.js'/>"></script>
			<div class="ibms_wrapper">
				<div class="ibms_slide">
					<div class="ibms_slide_left"></div>
					<div class="ibms_slide_right"></div>
					<div class="ibms_slide_center"></div>
				</div>
				<div class="ibms_clear"></div>
			</div>
		</div>
		<iframe width="100%" height="100%" src="" frameborder="0"
			name="ibms_main_content_02" id="ibms_main_content_02" scrolling="no"></iframe>
	</div>
	<!-- .content ends -->
</div>
<!-- .main ends -->

<script>
	
	$(function(){	
	    //操作集二级菜单选中样式切换
		$(".side-sub a").click(function(){
			$(".side-sub a.active").removeClass("active");
			$(this).addClass("active");
		});	
	});
	
	$(document).ready(function(){
		$('#accordion-effect li').click(function(){
			
			var curObj = $(this),
				curtain = curObj.children('.cont');
			
			curObj.siblings('li').removeClass('expanded');
			curObj.siblings('li').children('.cont').hide();
			
			if(!(curObj.hasClass('expanded'))){
				curObj.addClass('expanded');
				curtain.show();
			}
			
			resetHeight();
			if(curtain.children('.inner').width() > 119){
				curtain.mCustomScrollbar({axis:"yx"});
			}else{
				curtain.mCustomScrollbar();
			}

		});
		
		function resetHeight() {
			var outHeight = $('#accordion-effect').parent('div').height();
			var curtainHeight = outHeight - menuSize * 30 - 15;	
			
			$('#accordion-effect li.expanded').children('.cont').css('height', curtainHeight);
		}
		
		$(window).resize(function(){
			setTimeout(function(){
				resetHeight();
			}, 200);
		});
		
		$('.sidebar-btn').click(function(){
			var btn = $(this),
			    cont = btn.siblings('ul').parent(),
			    _left =$(".warning-wrapper");
			if(btn.hasClass('closed')){
				setTimeout(function(){ 
					_left.css("left","184px");
			    }, 50 ); 
				
				cont.removeClass('closed');
				btn.removeClass('closed');
				cont.parent().parent().removeClass('whole');
			}else{
				//setTimeout(function(){ 
					_left.css("left","10px");
			   // }, 50 ); 
				
				cont.addClass('closed');
				btn.addClass('closed');
				cont.parent().parent().addClass('whole');
			}
		});
		$('.tit:first').click();
		$('.side-sub a:first').click();
	});
</script>