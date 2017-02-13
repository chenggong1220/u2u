function registWebSocket(topic,callBackMethod){
	if(!callBackMethod){
		callBackMethod = topic;
		topic="";
	}	
	
	//alert(window.parent.WSocket.socket);
//	for(i in window.parent.WSocket.socket){
//	    alert(i); //属性名  
//	    alert(window.parent.WSocket.socket[i]); //属性值  
//	} 
	window.parent.WSocket.registWebSocket(topic,"$(window.parent.document).contents().find('#ibms_main_content_02')[0].contentWindow."+callBackMethod);
}