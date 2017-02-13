package com.u2u.framework.webscoket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.u2u.framework.util.LogUtil;

/**
 * @ClassName: WebSocketServer <br>
 * @Description: WebSocket Server.<br>
 * @date 2015-2-10 下午03:11:12 <br>
 * 
 * @author Dean
 */
@ServerEndpoint(value = "/websocket/push/{userId}")
public class WebSocketServer {
	private static final Log LOG = LogFactory.getLog(WebSocketServer.class);

	@OnClose
	public void end(final Session session) {
		LogUtil.debug(LOG, "close a session ,ID=" + session.getId());
		WebSocketUtil.connections.remove(session.getId());
	}

	@OnMessage
	public void incoming(final Session session, final String callBackMethod) {
		// session.getUserProperties().put("callBackMethod", callBackMethod);
		WebSocketUtil.connections.get(session.getId()).getCallBackMethods()
				.add(callBackMethod);

	}

	@OnError
	public void onError(final Throwable t) throws Throwable {
		// log.error("Chat Error: " + t.toString(), t);
	}

	@OnOpen
	public void start(final Session session,
			@PathParam(value = "userId") final String userId) {
		final WebSocketBean socketBean = new WebSocketBean();
		socketBean.setSession(session);
		LogUtil.debug(LOG, "open a session ,ID=" + session.getId());
		socketBean.setUserId(userId);
		// socketBean.setCallBackMethod((String)session.getUserProperties().get("callBackMethod"));
		WebSocketUtil.connections.put(session.getId(), socketBean);
		WebSocketUtil.pushWithSessionId("WSocket.getWebSocketSessionId",
				session.getId());
	}

}
