$(function() {
	
	//var datetimeboxclass = "aaaa";
	//自动加载datatimebox
	//$('.' + datetimeboxclass).datetimebox({
		// value: '3/4/2010 2:3',   
	//	required : true,
	//	showSeconds : false
	//});

	//验证用户名
	$('.vali_username').validatebox({
		required : true,
		missingMessage : '请填写用户名!'
	});

	$('.vali_password').validatebox({
		required : true,
		missingMessage : '请填写密码!'
	});

	//执行一下本地化?？？？
	//		执行初始化方法
	//		IBMS.ready();
	var height = $(window).height();
	var width = $(window).width();

	$(".login").width(width);
	$(".login").height(height);

	$(window).resize(
			function() {
				var w = window.innerWidth
						|| document.documentElement.clientWidth
						|| document.body.clientWidth;
				var h = window.innerHeight
						|| document.documentElement.clientHeight
						|| document.body.clientHeight;

				$("body").width(w + "px");
				$("body").height(h + "px");
			}
	);
	
	if(top.location.href.indexOf('/auth/login') < 0){
		top.location.href = WEB_APP +"/auth/login";	
	}
	
	$(".button").click( function() {
		IBMS.post(WEB_APP +'/j_spring_security_check', "", function(data) {
			$(".loginmsg").text(data.message);
			if(data.returnUrl){
				location.href = "../" + data.returnUrl;
			}
		});
	});
		
	document.onkeydown = function(e) {
		var ev = document.all ? window.event : e;
		if (ev.keyCode == 13) {
			$(".button").click();
		}
	};
});
