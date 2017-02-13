package com.u2u.framework.sys.authorize.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.u2u.framework.base.BaseController;
import com.u2u.framework.beans.AjaxDone;
import com.u2u.framework.exception.ServiceAuthorizeException;
import com.u2u.framework.exception.ServiceBusinessException;
import com.u2u.framework.sys.authorize.beans.Bu;
import com.u2u.framework.sys.authorize.beans.Resource;
import com.u2u.framework.sys.authorize.beans.Role;
import com.u2u.framework.sys.authorize.beans.User;
import com.u2u.framework.sys.authorize.model.ResourceRequest;
import com.u2u.framework.sys.authorize.model.RoleRequest;
import com.u2u.framework.sys.authorize.model.TreeVo;
import com.u2u.framework.sys.authorize.model.UserRequest;
import com.u2u.framework.sys.authorize.model.UserResponse;
import com.u2u.framework.sys.authorize.security.SecurityContextUtil;
import com.u2u.framework.sys.authorize.service.AuthorizeService;
import com.u2u.framework.sys.authorize.service.BuService;
import com.u2u.framework.sys.log.beans.LogType;
import com.u2u.framework.sys.log.service.LoggerService;
import com.u2u.framework.util.DateUtil;
import com.u2u.framework.util.LogUtil;
import com.u2u.framework.util.MD5Util;
import com.u2u.framework.util.ObjectUtils;
import com.u2u.framework.util.StringUtils;
import com.u2u.ibms.common.beans.location.City;
import com.u2u.ibms.common.beans.location.Province;
import com.u2u.ibms.common.mapper.LocationMapper;

/**
 * @ClassName: AuthorizeController <br>
 * @Description: 登录认证模块Controller <br>
 * @date 2015年1月14日 上午11:21:53 <br>
 * 
 * @author Freud
 */
@Controller("AuthorizeController")
@RequestMapping("/auth")
public class AuthorizeController extends BaseController {

	/**
	 * The LOGGER.
	 */
	private static final Log LOG = LogFactory.getLog(AuthorizeController.class);

	private LoggerService loggerService;

	private HttpServletRequest request;

	@Autowired
	private AuthorizeService authorizeService;

	@Autowired
	private LocationMapper locationMapper;
	@Autowired
	private BuService buService;

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return HttpRequest request.
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param request
	 *            The request to set.
	 */
	@Autowired
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return LoggerService loggerService.
	 */
	public LoggerService getLoggerService() {
		return loggerService;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param loggerService
	 *            The loggerService to set.
	 */
	@Autowired
	public void setLoggerService(LoggerService loggerService) {
		this.loggerService = loggerService;
	}

	/**
	 * 
	 * <p>
	 * Discription:[Login页面]
	 * </p>
	 * 
	 * @return
	 * @author:[Freud]
	 * @update:[2015年1月14日] [更改人姓名][变更描述]
	 */
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login() {
		LogUtil.debug(LOG, "登录展示页面被访问。");
		return "framework/auth/login";
	}

	@RequestMapping(value = { "/main" }, method = RequestMethod.GET)
	public String main(HttpServletRequest request) {
		request.setAttribute("login_session_user",
				SecurityContextUtil.getUserName());
		LogUtil.debug(LOG, "架构主页面被访问");
		return "index";
	}

	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public String logout() {
		logIntoDB("登出");
		return "redirect:/auth/logout/success";
	}

	@RequestMapping(value = { "/loginSuccess" }, method = RequestMethod.GET)
	public @ResponseBody AjaxDone loginSuccess() {
		logIntoDB("登录");
		return ajaxDoneSuccess("登录成功！", "auth/main");
	}

	@RequestMapping(value = { "/loginFailure" }, method = RequestMethod.GET)
	public @ResponseBody AjaxDone loginFailure() {
		LogUtil.debug(LOG, "登录失败");
		return ajaxDoneError("登录失败！用户名密码错误！", "");
	}

	@RequestMapping(value = { "/accessdenied" }, method = RequestMethod.GET)
	public AjaxDone accessDeniedAjax() {
		LogUtil.debug(LOG, "权限验证失败，访问被拒绝");
		return ajaxDoneError("权限验证失败，访问被拒绝", "/auth/access_denied");
	}

	@RequestMapping(value = "/access_denied", method = RequestMethod.GET)
	public String accessDeniedPage() {
		return "framework/auth/access_denied";
	}

	@RequestMapping(value = "/user/index", method = RequestMethod.GET)
	public String userIndex() {
		return "framework/auth/user/userList";
	}

	@RequestMapping(value = "/user/self", method = RequestMethod.GET)
	public ModelAndView userSelf() throws ServiceAuthorizeException {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("framework/auth/user/userSelf");
		User user = authorizeService.getUser(SecurityContextUtil.getUserName());
		user.setPassword(AuthorizeService.UNIQUE_PASSWORD);
		mav.addObject("user", convertToUserResponse(user));

		return mav;
	}

	@RequestMapping(value = "/user/self/update", method = RequestMethod.GET)
	public ModelAndView userSelfUpdate(String username)
			throws ServiceAuthorizeException {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("framework/auth/user/userSelfEdit");

		User user = authorizeService.getUser(username);
		user.setPassword(AuthorizeService.UNIQUE_PASSWORD);
		mav.addObject("user", convertToUserResponse(user));

		return mav;
	}

	@RequestMapping(value = "/user/self/save", method = RequestMethod.POST)
	@ResponseBody
	public AjaxDone userSelfSave(String username, String oldPassword,
			String newPassword) throws Exception {
		User user = authorizeService.getUser(username);
		if (user.getPassword().toLowerCase()
				.equals(MD5Util.MD5(oldPassword).toLowerCase())) {
			user.setPassword(newPassword);
			authorizeService.updateUser(user);
			return ajaxDoneSuccess("修改成功", "/auth/user/self");
		} else {
			return ajaxDoneError("密码错误", null);
		}
	}

	/**
	 * <p>
	 * Discription:[查询用户列表]
	 * </p>
	 * 
	 * @param userReq
	 * @return
	 * @author:[xuqinghan]
	 * @update:[2015-3-24] [更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> userList(UserRequest userReq) {
		List<UserResponse> ret = new ArrayList<>();
		List<User> all = new ArrayList<>();
		if (StringUtils.isNotEmpty(userReq.getUsername())) {
			User user = null;
			try {
				user = authorizeService.getUser(userReq.getUsername());
				List<User> a = new ArrayList<>();
				a.add(user);
				ret = convertToUserResponseList(a);
				all = a;
			} catch (ServiceAuthorizeException e) {
				// TODO Auto-generated catch block
				LogUtil.error(LOG, e.getMessage());
			}
		} else {
			ret = convertToUserResponseList(authorizeService
					.getAllUsers(buildRowBounds(userReq)));
			all = authorizeService.getAllUsers(new RowBounds());
		}
		Map<String, Object> result = new HashMap<String, Object>();

		result.put("total", all.size());
		result.put("rows", ret);

		return result;
	}

	@RequestMapping(value = "/user/json")
	@ResponseBody
	public List<User> userJson() {
		return authorizeService.getAllUsers(buildRowBounds());
	}

	/**
	 * <p>
	 * Discription:[封装页面对象，用来显示]
	 * </p>
	 * 
	 * @param users
	 * @return
	 * @author:[xuqinghan]
	 * @update:[2015-3-24] [更改人姓名][变更描述]
	 */
	private List<UserResponse> convertToUserResponseList(List<User> users) {
		List<UserResponse> ret = new ArrayList<UserResponse>();
		for (User user : users) {
			ret.add(convertToUserResponse(user));
		}

		return ret;
	}

	private UserResponse convertToUserResponse(User user) {
		UserResponse response = new UserResponse();
		response.setId(user.getId());
		response.setUsername(user.getUsername());
		response.setRealname(user.getRealname());
		response.setMobile(user.getMobile());
		response.setDingding(user.getDingding());
		Bu bu = buService.getById(user.getBuId());
		if (bu == null) {
			response.setBu("");
		} else {
			response.setBu(bu.getName());
		}

		Province province = locationMapper
				.getProvinceById(user.getProvinceId());
		if (province == null) {
			response.setProvince("");
		} else {
			response.setProvince(province.getName());
		}

		City city = locationMapper.getCityById(user.getCityId());
		if (city == null) {
			response.setCity("");
		} else {
			response.setCity(city.getName());
		}

		List<Role> roles = user.getRoles();

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < roles.size(); i++) {
			if (i == 0) {
				sb.append("[").append(roles.get(i).getRoleName()).append("]");
			} else {
				sb.append(", ").append("[").append(roles.get(i)).append("]");
			}
		}
		response.setRoles(sb.toString());
		return response;
	}

	/**
	 * <p>
	 * Discription:[跳转到添加页面]
	 * </p>
	 * 
	 * @return
	 * @author:[xuqinghan]
	 * @update:[2015-3-24] [更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/user/add")
	public ModelAndView addUser() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("framework/auth/user/userAdd");
		mav.addObject("roleId", authorizeService.getAllRoles(new RowBounds())
				.get(0).getId());

		return mav;
	}

	/**
	 * <p>
	 * Discription:[对页面请求封装]
	 * </p>
	 * 
	 * @param userReq
	 * @return
	 * @author:[xuqinghan]
	 * @update:[2015-3-24] [更改人姓名][变更描述]
	 */
	private User convertUserReq(UserRequest userReq) {
		User user = new User();
		user.setId(userReq.getId());
		user.setUsername(userReq.getUsername());
		user.setPassword(userReq.getPassword());
		user.setRealname(userReq.getRealname());
		user.setMobile(userReq.getMobile());
		user.setDingding(userReq.getDingding());
		user.setBuId(userReq.getBuId());
		user.setProvinceId(userReq.getProvinceId());
		user.setCityId(userReq.getCityId());
		user.setShopId(userReq.getShopId());

		if (!StringUtils.isEmpty(userReq.getRoles())) {
			String[] roleIds = StringUtils.split(userReq.getRoles(), ",");
			for (String roleId : roleIds) {
				user.getRoles().add(new Role(Integer.parseInt(roleId)));
			}
		}

		return user;
	}

	/**
	 * <p>
	 * Discription:[新增用户]
	 * </p>
	 * 
	 * @param userReq
	 * @return
	 * @throws ServiceBusinessException
	 * @author:[xuqinghan]
	 * @update:[2015-3-24] [更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/user/save")
	@ResponseBody
	public AjaxDone saveUser(UserRequest userReq)
			throws ServiceBusinessException {
		authorizeService.insertUser(convertUserReq(userReq));
		return ajaxDoneSuccess("保存成功！", null);
	}

	/**
	 * <p>
	 * Discription:[修改用户]
	 * </p>
	 * 
	 * @param userReq
	 * @return
	 * @throws ServiceBusinessException
	 * @author:[Freud]
	 * @update:[2015-3-24] [更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/user/update")
	@ResponseBody
	public AjaxDone updateUser(UserRequest userReq)
			throws ServiceBusinessException {
		authorizeService.updateUser(convertUserReq(userReq));
		return ajaxDoneSuccess("修改成功！", null);
	}

	/**
	 * <p>
	 * Discription:[跳转到用户编辑页面]
	 * </p>
	 * 
	 * @param userReq
	 * @param model
	 * @return
	 * @throws ServiceAuthorizeException
	 * @author:[Freud]
	 * @update:[2015-3-24] [更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/user/edit")
	public ModelAndView editUser(UserRequest userReq, boolean self)
			throws ServiceAuthorizeException {
		ModelAndView mav = new ModelAndView();

		User user = authorizeService.getUser(userReq.getUsername());
		List<Role> roles = user.getRoles();
		user.setPassword(AuthorizeService.UNIQUE_PASSWORD);
		mav.addObject("self", self);
		mav.addObject("user", user);

		if (roles.size() > 0) {
			mav.addObject("roleid", roles.get(0).getId());
		}

		mav.setViewName("framework/auth/user/userEdit");

		return mav;
	}

	/**
	 * <p>
	 * Discription:[获取所有权限]
	 * </p>
	 * 
	 * @return
	 * @author:[xuqinghan]
	 * @update:[2015-3-24] [更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/role/getRoles")
	@ResponseBody
	public List<Role> getRoles() {
		List<Role> roles = authorizeService.getAllRoles(new RowBounds());
		return roles;
	}

	/**
	 * <p>
	 * Discription:[用户删除]
	 * </p>
	 * 
	 * @param req
	 *            页面请求对象
	 * @param id
	 *            要删除的资源id
	 * @return
	 * @throws NumberFormatException
	 * @throws ServiceBusinessException
	 * @author:[xuqinghan]
	 * @update:[2015-3-24] [更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/user/delete")
	@ResponseBody
	public AjaxDone deleteUser(HttpServletRequest req, String[] id)
			throws NumberFormatException, ServiceBusinessException {
		for (String userid : id) {
			authorizeService.deleteUser(Integer.parseInt(userid));
		}
		return ajaxDoneSuccess("修改成功！", null);
	}

	// /角色管理
	/**
	 * <p>
	 * Discription:[跳转到角色编辑页面]
	 * </p>
	 * 
	 * @return
	 * @author:[Freud]
	 * @update:[2015-3-24] [更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/role/index", method = RequestMethod.GET)
	public String roleIndex() {
		return "framework/auth/role/roleList";
	}

	/**
	 * <p>
	 * Discription:[获取权限列表]
	 * </p>
	 * 
	 * @param roleReq
	 * @return
	 * @author:[Freud]
	 * @update:[2015-3-24] [更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/role/list")
	public @ResponseBody Map<String, Object> roleList(RoleRequest roleReq) {
		List<Role> ret = authorizeService.getAllRoles(buildRowBounds(roleReq));
		List<Role> all = authorizeService.getAllRoles(new RowBounds());
		int size = all.size();
		if (!SecurityContextUtil.isAdmin()) {

			int adminposition = 0;
			for (int i = 0; i < ret.size(); i++) {
				if (ret.get(i).getRoleName().equals("ROLE_ADMIN")) {
					adminposition = i;
					break;
				}
			}
			ret.remove(adminposition);
			size = size - 1;
		}
		Map<String, Object> result = new HashMap<String, Object>();

		result.put("total", all.size());
		result.put("rows", ret);

		return result;
	}

	/**
	 * <p>
	 * Discription:[获取资源树]
	 * </p>
	 * 
	 * @param roleReq
	 * @return
	 * @author:[Freud]
	 * @update:[2015-3-24] [更改人姓名][变更描述]
	 */
	@RequestMapping(value = "/role/getResources")
	public @ResponseBody List<TreeVo> getResources() {
		List<Resource> resources = authorizeService
				.getAllResourcesAsTree(new RowBounds());
		return convert(resources);
	}

	private List<TreeVo> convert(List<Resource> resources) {
		List<TreeVo> tvs = new ArrayList<>();
		for (Resource resource : resources) {
			TreeVo tv = new TreeVo();
			tv.setId(resource.getId() + "");
			tv.setText(resource.getDescription());
			if (resource.getResources().size() == 0) {
				tv.setState("open");
			} else {
				tv.setChildren(convert(resource.getResources()));
			}
			tvs.add(tv);
		}
		return tvs;
	}

	@RequestMapping(value = "/role/add")
	public String addRole() {
		return "framework/auth/role/roleAdd";
	}

	@RequestMapping(value = "/role/save")
	@ResponseBody
	public AjaxDone saveRole(RoleRequest userReq)
			throws ServiceBusinessException {
		authorizeService.insertRole(convertRoleReq(userReq));
		return ajaxDoneSuccess("保存成功！", null);
	}

	@RequestMapping(value = "/role/delete")
	@ResponseBody
	public AjaxDone deleteRole(HttpServletRequest req, String[] id)
			throws NumberFormatException, ServiceBusinessException {
		for (String roleId : id) {
			System.out.println(roleId);
			authorizeService.deleteRole(Integer.parseInt(roleId));
		}
		return ajaxDoneSuccess("删除成功！", null);
	}

	@RequestMapping(value = "/role/update")
	@ResponseBody
	public AjaxDone updateRole(RoleRequest roleReq)
			throws ServiceBusinessException {
		authorizeService.udpateRole(convertRoleReq(roleReq));
		return ajaxDoneSuccess("修改成功！", null);
	}

	private Role convertRoleReq(RoleRequest roleReq) {
		// TODO Auto-generated method stub
		Role role = new Role();
		role.setId(roleReq.getId());
		role.setRoleName(roleReq.getRolename());
		String[] resourceIds = org.apache.commons.lang.StringUtils.split(
				roleReq.getResources(), ",");
		if (ObjectUtils.isNotEmpty(resourceIds)) {
			for (String resourceId : resourceIds) {
				role.getResources().add(
						new Resource(Integer.parseInt(resourceId)));
			}
		}
		return role;
	}

	@RequestMapping(value = "/role/edit")
	public String editRole(RoleRequest roleReq, Model model)
			throws ServiceAuthorizeException, ServiceBusinessException {
		Role role = authorizeService.getRoleById(roleReq.getId());
		List<Resource> resources = role.getResources();
		String str = "";
		for (Resource resource : resources) {
			str += resource.getId() + ",";
		}
		str = StringUtils.removeEnd(str, ",");
		roleReq.setResources(str);
		roleReq.setRolename(role.getRoleName());
		model.addAttribute("role", roleReq);
		return "framework/auth/role/roleEdit";
	}

	// 记录日志
	private void logIntoDB(String targetObjet) {

		String username = SecurityContextUtil.getUserName();
		String currentDate = DateUtil.date2String(new Date(),
				DateUtil.PATTERN_STANDARD);
		String ip = request.getRemoteAddr();
		String description = MessageFormat.format("[{0}]成功", targetObjet);

		LogType logType = new LogType();

		logType.setUsername(username);
		logType.setOperateObject(targetObjet);
		logType.setOperateDesc(description);
		logType.setOperateTime(currentDate);
		logType.setOperateIp(ip);

		loggerService.insertLog(logType);

		/**
		 * 0-用户名 1-时间 2-操作 3-IP
		 */
		String operateLogDescription = "用户[{0}]在[{1}]时间[{2}],用户IP[{3}]";
		LogUtil.info(LOG, operateLogDescription, username, currentDate,
				description, ip);
	}

	/**
	 * 
	 * <p>
	 * Discription:[资源配置首页]
	 * </p>
	 * 
	 * @return
	 * @author:[Freud]
	 * @update:[2015-3-26] [更改人姓名][变更描述]
	 */
	@RequestMapping(value = { "/resource/list" }, method = RequestMethod.GET)
	public String resourceList() {
		LogUtil.debug(LOG, "登录展示页面被访问。");
		return "framework/auth/resource/resourceList";
	}

	/**
	 * 
	 * <p>
	 * Discription:[添加资源展示页]
	 * </p>
	 * 
	 * @return
	 * @author:[Freud]
	 * @update:[2015-3-26] [更改人姓名][变更描述]
	 */
	@RequestMapping(value = { "/resource/add" }, method = RequestMethod.GET)
	public ModelAndView resourceAdd(int id) {
		LogUtil.debug(LOG, "添加Resource");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("framework/auth/resource/resourceAdd");
		mav.addObject("parentsResource", id);

		mav = authorizeService.getResourceEditPath(mav, id);

		return mav;
	}

	/**
	 * 
	 * <p>
	 * Discription:[修改资源首页]
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws ServiceBusinessException
	 * @author:[Freud]
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 * @update:[2015-3-26] [更改人姓名][变更描述]
	 */
	@RequestMapping(value = { "/resource/edit" }, method = RequestMethod.GET)
	public ModelAndView resourceEdit(int id) throws Exception {
		ResourceRequest res = convertToResourceRequest(authorizeService
				.getResourceById(id));
		ModelAndView mav = new ModelAndView();
		mav.setViewName("framework/auth/resource/resourceEdit");
		mav.addObject("resource", res);

		mav = authorizeService.getResourceEditPath(mav, id);

		return mav;
	}

	/**
	 * 
	 * <p>
	 * Discription:[添加的时候保存资源信息]
	 * </p>
	 * 
	 * @param request
	 * @return
	 * @author:[Freud]
	 * @throws ServiceAuthorizeException
	 * @update:[2015-3-26] [更改人姓名][变更描述]
	 */
	@RequestMapping(value = { "/resource/save" }, method = RequestMethod.POST)
	@ResponseBody
	public AjaxDone resourceSave(ResourceRequest request,
			HttpServletRequest servletRequest) throws ServiceAuthorizeException {
		try {
			authorizeService.insertNewResource(request);
			return ajaxDoneSuccess(null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/resource/getResources")
	public @ResponseBody List<TreeVo> getAllResources(Integer id) {
		if (id == null) {
			return convertResources(authorizeService
					.getAllResourcesAsTreeAndNotValid());
		} else {
			return convertResources(authorizeService
					.getResourcesAsTreeAndNotValidByParentId(id));
		}
	}

	@RequestMapping(value = "/resource/getResourcesById")
	public @ResponseBody List<TreeVo> getResourcesById(Integer nodeId,
			Integer id) {
		if (id == null || id == 0)
			id = nodeId;
		List<Resource> resources = authorizeService
				.getAllResourceWithCurrentRole(id);

		return convertResources(resources, true);
	}

	private List<TreeVo> convertResources(List<Resource> resources) {

		List<TreeVo> tvs = new ArrayList<TreeVo>();
		for (Resource resource : resources) {
			TreeVo tv = new TreeVo();
			tv.setId(resource.getId() + "");
			tv.setText(resource.getDescription());
			if (resource.getResources().size() == 0) {
				tv.setState("open");
			} else {
				tv.setState("closed");
				// tv.setChildren(convertResources(resource.getResources()));
			}
			tvs.add(tv);
		}

		return tvs;
	}

	private List<TreeVo> convertResources(List<Resource> resources,
			boolean async) {

		List<TreeVo> tvs = new ArrayList<TreeVo>();
		for (Resource resource : resources) {
			TreeVo tv = new TreeVo();
			tv.setId(resource.getId() + "");
			tv.setText(resource.getDescription());
			tv.getAttributes().put("url", resource.getLink());
			tv.setState("closed");
			if (resource.getResources().size() != 0) {
				tv.setChildren(convertResources(resource.getResources(), true));

			} else {

			}
			tvs.add(tv);
		}

		return tvs;
	}

	/**
	 * 
	 * <p>
	 * Discription:[将资源从DB的Bean类型转换为前台需要的Model类型]
	 * </p>
	 * 
	 * @param resource
	 * @return
	 * @author:[Freud]
	 * @update:[2015-3-26] [更改人姓名][变更描述]
	 */
	private ResourceRequest convertToResourceRequest(Resource resource) {

		ResourceRequest res = new ResourceRequest();

		res.setId(resource.getId());
		res.setPriority(resource.getPriority());
		res.setParentsResource(resource.getParentId());
		res.setResourceName(resource.getDescription());
		res.setLink(resource.getLink());
		res.setImageUrl(resource.getImageUrl());
		res.setValidate(resource.isIfValid());
		res.setParentsLevel(resource.getLevel() - 1);

		return res;
	}

	/**
	 * 
	 * <p>
	 * Discription:[修改保存资源信息]
	 * </p>
	 * 
	 * @param request
	 * @return
	 * @author:[Freud]
	 * @throws Exception
	 * @update:[2015-3-26] [更改人姓名][变更描述]
	 */
	@RequestMapping(value = { "/resource/update" }, method = RequestMethod.POST)
	@ResponseBody
	public AjaxDone resourceUpdate(ResourceRequest request) throws Exception {
		try {
			authorizeService.updateResource(request);
			return ajaxDoneSuccess(null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * <p>
	 * Discription:[删除资源]
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @author:[Freud]
	 * @throws Exception
	 * @update:[2015-3-26] [更改人姓名][变更描述]
	 */
	@RequestMapping(value = { "/resource/delete" }, method = RequestMethod.POST)
	@ResponseBody
	public AjaxDone resourceRemove(int id) throws Exception {
		authorizeService.removeResource(id);
		return ajaxDoneSuccess(null);
	}

}
