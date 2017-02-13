$(function(){ 
	
	/* for collapsible content */
	$('.warning-wrapper a.warning-triger').click(function(){
		var trigBtn = $(this),
			collapsibleCont = trigBtn.siblings('.cont');
		
		if(trigBtn.hasClass('open')){
			trigBtn.removeClass('open');
			collapsibleCont.hide();
		}else{
			trigBtn.addClass('open');
			collapsibleCont.show();
		}
	});
	
	$("#sys_time_span").html(showLocale(new Date()));
	$('.main-nav > li:first').addClass('active'); 
	
	$.post(WEB_APP+"/navigation/main-nav", function(data,status){
		var mainNavigation = "";	
		$.each(data,function(k,v){
				mainNavigation = mainNavigation + "<li>";			  
				mainNavigation = mainNavigation + "<a href=\"#\" id=\""+v.id+"\"><h1><img src=\"" + WEB_APP + v.imageUrl + "\"/></h1><h2>"+v.description+"</h2></a>";
		});

		$(".main-nav").html(mainNavigation);

		$('.main-nav > li').click(function(){
			$(".warning-wrapper").css("left","184px");
				for(var i=0;i<$('.main-nav > li').size();i++){ 
				    if(this==$('.main-nav > li').get(i)){ 
				       $('.main-nav > li').eq(i).addClass('active'); 
				       $("#outermost").load(WEB_APP+"/navigation/leftmenu?navigationId=" + $(this).children("a").attr("id"));
					}else{ 
					   $('.main-nav > li').eq(i).removeClass('active'); 
					} 
			    } 
		});

		$('.main-nav > li:first').click();

	});
	
		
	//load system alarm list.
	$("#bottom_alarm_list").load(WEB_APP+"/navigation/alarm",function(){
	    $(".ibms_alarm_table tr").mouseover(function(){
		$(this).addClass("over");}).mouseout(function(){
		$(this).removeClass("over");}) ;
		$(".ibms_alarm_table tr:even").addClass("alt");
	});
});  

function showLocale(objD)
{
	var str;
	var yy = objD.getYear();
	if(yy<1900) yy = yy+1900;
	var MM = objD.getMonth()+1;
	if(MM<10) MM = '0' + MM;
	var dd = objD.getDate();
	if(dd<10) dd = '0' + dd;
	var hh = objD.getHours();
	if(hh<10) hh = '0' + hh;
	var mm = objD.getMinutes();
	if(mm<10) mm = '0' + mm;
	var ss = objD.getSeconds();
	if(ss<10) ss = '0' + ss;
	var ww = objD.getDay();
	if  ( ww==0 )  colorhead="<font color=\"#FF0000\">";
	if  ( ww > 0 && ww < 6 )  colorhead="<font color=\"#373737\">";
	if  ( ww==6 )  colorhead="<font color=\"#008000\">";
	if  (ww==0)  ww="星期日";
	if  (ww==1)  ww="星期一";
	if  (ww==2)  ww="星期二";
	if  (ww==3)  ww="星期三";
	if  (ww==4)  ww="星期四";
	if  (ww==5)  ww="星期五";
	if  (ww==6)  ww="星期六";
	str =  yy + "-" + MM + "-" + dd + " " + hh + ":" + mm + ":" + ss + "  " + ww ;
	return(str);
}
function tick()
{
	var today;
	today = new Date();
	$("#sys_time_span").html(showLocale(today));
	window.setTimeout("tick()", 1000);
}
tick();


function loadContent(a_element){
	$.post(WEB_APP+"/navigation/handle", "target_desc="+a_element.innerHTML+"&sessionId="+WEB_SOCKET_SESSION_ID, function(data) {
		$("#ibms_main_content_02").attr("src",WEB_APP+"/navigation/userContent?rte="+$(a_element).attr("tlink")+"&resourceId="+$(a_element).attr("id"));
	});
}