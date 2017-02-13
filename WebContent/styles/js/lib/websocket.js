var WEB_SOCKET_SESSION_ID;
var WSocket = {};

WSocket.socket = null;

WSocket.connect = (function(host) {
	if ('WebSocket' in window) {
		WSocket.socket = new WebSocket(host);
	} else if ('MozWebSocket' in window) {
		WSocket.socket = new MozWebSocket(host);
	} else {
		return;
	}

	WSocket.socket.onopen = function(e) {
		// alert(e.toString());

	};

	WSocket.socket.onclose = function() {
		console.log("socket closed");
	};

	WSocket.socket.onmessage = function(message) {

		try{
			eval(message.data);
		}catch(e){
			console.log(e);
			alert("推送配置错误!"+e);
		}
	};
});

WSocket.getWebSocketSessionId = function(sessionId) {
	WEB_SOCKET_SESSION_ID = sessionId;
};

WSocket.initialize = function(userId) {
	if (window.location.protocol == 'http:') {
		WSocket.connect('ws://' + window.location.host + WEB_APP
				+ '/websocket/push/' + userId);
	} else {
		WSocket.connect('wss://' + window.location.host + WEB_APP
				+ '/websocket/push/' + userId);
	}
};

WSocket.registWebSocket = function(topic, callBackMethod) {
	var param = {};
	callBackMethod = callBackMethod || topic;
	param["callBackMethod"] = callBackMethod;
	if(callBackMethod===topic)topic="";
	param["topic"] = topic;
	param["sessionId"] = WEB_SOCKET_SESSION_ID;
	$.post(WEB_APP + "/navigation/registwebsocket", param);
};

WSocket.unregistWebSocket = function() {
	var param = {};
	param["target_desc"] = "";
	param["sessionId"] = WEB_SOCKET_SESSION_ID;
	$.post(WEB_APP + "/navigation/handle", param);
};

// //Sys web socket
//        
// var WEB_SOCKET_SESSION_ID_SYS;
// var WSocket_Sys = {};
//
// WSocket_Sys.socket = null;
//
// WSocket_Sys.connect = (function(host) {
// if ('WebSocket' in window) {
// WSocket_Sys.socket = new WebSocket(host);
// } else if ('MozWebSocket' in window) {
// WSocket_Sys.socket = new MozWebSocket(host);
// } else {
// return;
// }
//
// WSocket_Sys.socket.onopen = function (e) {
// //alert(e.toString());
//
// };
//
// WSocket_Sys.socket.onclose = function () {
//
// };
//
// WSocket_Sys.socket.onmessage = function (message) {
// eval(message.data);
// };
// });
//        
// WSocket_Sys.getWebSocketSessionId = function(sessionId){
// WEB_SOCKET_SESSION_ID_SYS = sessionId;
// };
//
// WSocket_Sys.initialize = function(userId) {
// if (window.location.protocol == 'http:') {
// WSocket_Sys.connect('ws://' + window.location.host + WEB_APP
// +'/websocket/push/'+userId);
// } else {
// WSocket_Sys.connect('wss://' + window.location.host + WEB_APP +
// '/websocket/push/'+userId);
// }
// };
//
// WSocket_Sys.registWebSocket = function(callBackMethod){
// $.post(WEB_APP+"/navigation/registwebsocket",
// "callBackMethod="+callBackMethod+"&sessionId="+WEB_SOCKET_SESSION_ID);
// };

WSocket.registCallBackMethod = function(callBackMethod) {
	if (WSocket.socket.readyState == 1) {
		// WSocket.socket.send(callBackMethod);
		WSocket.registWebSocket(callBackMethod);
		return;
	}
	setTimeout("WSocket.registCallBackMethod('" + callBackMethod + "')", 5000);
};

WSocket.initialize(LOGIN_SESSION_USER);
// WSocket_Sys.initialize(LOGIN_SESSION_USER);
