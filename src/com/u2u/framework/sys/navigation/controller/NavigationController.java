package com.u2u.framework.sys.navigation.controller;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanPropertyValueEqualsPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u2u.framework.base.BaseController;
import com.u2u.framework.config.SysConstants;
import com.u2u.framework.exception.ServiceAuthorizeException;
import com.u2u.framework.exception.ServiceBusinessException;
import com.u2u.framework.sys.authorize.beans.Resource;
import com.u2u.framework.sys.authorize.security.SecurityContextUtil;
import com.u2u.framework.sys.authorize.service.AuthorizeService;
import com.u2u.framework.util.ObjectUtils;
import com.u2u.framework.util.StringUtils;
import com.u2u.framework.webscoket.TopicVO;
import com.u2u.framework.webscoket.WebSocketUtil;

/**
 * @ClassName: NavigationController <br>
 * @Description: 导航,菜单调度类. <br>
 * @date 2015-2-5 下午04:03:41 <br>
 * 
 * @author Dean
 */
@Controller("NavigationController")
@RequestMapping("/navigation")
public class NavigationController extends BaseController {
	private AuthorizeService authorizeService;

	@RequestMapping(value = { "/main-nav" }, method = RequestMethod.POST)
	public @ResponseBody List<Resource> getMainNavigation()
			throws ServiceAuthorizeException {
		return authorizeService.getMainNavigationResources();
	}

	@RequestMapping(value = { "/leftmenu" }, method = RequestMethod.GET)
	public String getLeftMenu(final Model model,
			@RequestParam final String navigationId)
			throws ServiceAuthorizeException, NumberFormatException,
			ServiceBusinessException {
		List<Resource> menuList = authorizeService
				.getNavigationResources(navigationId);
		model.addAttribute("menuList", menuList);
		return "framework/layout/mainContent";
	}

	@RequestMapping(value = { "/handle" }, method = RequestMethod.POST)
	public void handle(@RequestParam final String target_desc,
			@RequestParam final String sessionId, final HttpSession session) {
		if (!ObjectUtils.isNullOrEmptyString(target_desc)) {
			if (null != session.getAttribute(SysConstants.SUB_SYSTEM_KEY)) {
				session.removeAttribute(SysConstants.SUB_SYSTEM_KEY);
			}
			session.setAttribute(SysConstants.SUB_SYSTEM_KEY, target_desc);
		}

		if (null != WebSocketUtil.connections.get(sessionId)) {
			WebSocketUtil.connections.get(sessionId).cleanBackMethods();
			WebSocketUtil.connections.get(sessionId).getTopics().clear();

		}
	}

	@RequestMapping(value = { "/unRegist" }, method = RequestMethod.POST)
	public void unRegist(@RequestParam final String topic,
			@RequestParam final String sessionId, final HttpSession session) {
		if (null != WebSocketUtil.connections.get(sessionId)) {
			WebSocketUtil.connections.get(sessionId).cleanBackMethods();
			Set<TopicVO> topicVO = WebSocketUtil.connections.get(sessionId)
					.getTopics();
			@SuppressWarnings("unchecked")
			Collection<TopicVO> topicVOf = CollectionUtils.select(topicVO,
					new BeanPropertyValueEqualsPredicate("name", topic));
			topicVO.removeAll(topicVOf);
		}
	}

	@RequestMapping(value = { "/registwebsocket" }, method = RequestMethod.POST)
	public @ResponseBody String registWebsocket(
			@RequestParam final String sessionId,
			@RequestParam final String topic,
			@RequestParam final String callBackMethod) {
		if (StringUtils.isEmpty(topic)) {
			WebSocketUtil.connections.get(sessionId).getCallBackMethods()
					.add(callBackMethod);
		} else {
			WebSocketUtil.connections.get(sessionId).getTopics()
					.add(new TopicVO(topic, callBackMethod));
		}

		return "";
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param authorizeService
	 *            The authorizeService to set.
	 */
	@Autowired
	public void setAuthorizeService(AuthorizeService authorizeService) {
		this.authorizeService = authorizeService;
	}

	@RequestMapping("/userContent")
	public String userContent(final HttpServletRequest request) {
		request.setAttribute("login_session_user",
				SecurityContextUtil.getUserName());
		request.setAttribute("rte", request.getParameter("rte"));
		request.setAttribute("resourceId", request.getParameter("resourceId"));
		return "framework/layout/userContent";
	}

}
