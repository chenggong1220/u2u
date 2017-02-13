$(function() {
	
    Slider();
    
    $(window).resize(function(){
    	Slider();
    });
});

function Slider(){
	
	var fullWidth =$(window).width();//定义屏幕的宽度
	
	var slide_width=fullWidth-597;//计算出滑动的宽度

    var _focus=$(".ibms_slide_center");
    
    _focus.css("width",slide_width);
    
    var sWidth = _focus.width();//获取滑动焦点的宽度

    _focus.find("ul li").css("width",sWidth);
    
	var len = $(".ibms_slide_center ul li").length; //获取滑动焦点的个数
	var index = 0;
	var nowLeft = -index*sWidth;
	$(".ibms_slide_center ul").css("left",nowLeft);
	$(".ibms_slide_center ul").css("width",sWidth * (len));
	//显示滑动函数，根据接收的index值显示相应的内容 
	function showFloor(index) {
	var nowLeft = -index*sWidth;//根据index值计算ul元素的left值 
	$(".ibms_slide_center ul").stop(true,false).animate({"left":nowLeft},300); //通过animate()调整ul元素滚动到计算出的position 
	}
}