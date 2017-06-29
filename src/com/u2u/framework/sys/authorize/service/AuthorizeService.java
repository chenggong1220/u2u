package com.u2u.framework.sys.authorize.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.u2u.framework.base.BaseService;
import com.u2u.framework.exception.ServiceAuthorizeException;
import com.u2u.framework.exception.ServiceBusinessException;
import com.u2u.framework.sys.authorize.beans.Resource;
import com.u2u.framework.sys.authorize.beans.Role;
import com.u2u.framework.sys.authorize.beans.RoleResource;
import com.u2u.framework.sys.authorize.beans.User;
import com.u2u.framework.sys.authorize.beans.UserRole;
import com.u2u.framework.sys.authorize.condition.UserCondition;
import com.u2u.framework.sys.authorize.mapper.AuthorizeMapper;
import com.u2u.framework.sys.authorize.mapper.BuMapper;
import com.u2u.framework.sys.authorize.model.ResourceRequest;
import com.u2u.framework.sys.authorize.security.SecurityContextUtil;
import com.u2u.framework.util.DateUtil;
import com.u2u.framework.util.LogUtil;
import com.u2u.framework.util.MD5Util;
import com.u2u.framework.util.StringUtils;
import com.u2u.ibms.common.beans.Asset;
import com.u2u.ibms.common.mapper.RentCompanyMapper;
import com.u2u.ibms.web.shop.mapper.ShopMapper;

/**
 * @ClassName: AuthorizeService <br>
 * @Description: 权限信息管理服务实现类 <br>
 * @date 2015年1月14日 上午11:24:09 <br>
 * 
 * @author Freud
 */
@Service("authorizeService")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AuthorizeService extends BaseService {

	public static final String UNIQUE_PASSWORD = "9535B91966CFA19E013F6A0BECF37328001";

	private final static Log log = LogFactory.getLog(AuthorizeService.class);

	private static final int LEVEL_ROOT_RESOURCE = 0;

	private static final int LEVEL_FIRST_NAVIGATION_RESOURCE = 1;

	private AuthorizeMapper authorizeMapper;
	
	@Autowired
	private ShopMapper shopMapper;
	
	@Autowired
	private BuMapper buMapper;	

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param authorizeMapper
	 *            The authorizeMapper to set.
	 */
	@Autowired
	public void setAuthorizeMapper(AuthorizeMapper authorizeMapper) {
		this.authorizeMapper = authorizeMapper;
	}

	public User getUser(int id) throws ServiceAuthorizeException {
		User user = authorizeMapper.getUserById(id);
		List<UserRole> userRoles = authorizeMapper
				.getUserRoleRelationsByUserId(user.getId());
		List<Integer> roleIds = new ArrayList<Integer>();

		for (UserRole userRole : userRoles) {
			roleIds.add(userRole.getRoleId());
		}

		if (!roleIds.isEmpty()) {
			user.getRoles().addAll(authorizeMapper.getRoleByIds(roleIds));
		}

		return user;
	}

	public User getUser(String username) throws ServiceAuthorizeException {
		User user = authorizeMapper.getUserByName(username);

		if (user == null) {
			throw new ServiceAuthorizeException("没有用户名为[" + username
					+ "]的用户存在。");
		}
		List<UserRole> userRoles = authorizeMapper
				.getUserRoleRelationsByUserId(user.getId());
		List<Integer> roleIds = new ArrayList<Integer>();

		for (UserRole userRole : userRoles) {
			roleIds.add(userRole.getRoleId());
		}

		if (!roleIds.isEmpty()) {
			user.getRoles().addAll(authorizeMapper.getRoleByIds(roleIds));
		}

		return user;
	}

	/*
	public List<User> getAllUsers(RowBounds rb) {
		List<User> users = authorizeMapper.getAllUsers(rb);
		List<Role> roles = authorizeMapper.getAllRole(new RowBounds());
		List<UserRole> userRoles = authorizeMapper.getUserRoleAllRelations();

		for (User user : users) {
			for (UserRole userRole : userRoles) {
				if (user.getId() == userRole.getUserId()) {
					for (Role role : roles) {
						if (userRole.getRoleId() == role.getId()) {
							user.getRoles().add(role);
						}
					}
				}
			}
		}

		return users;
	}
	*/
	
	
	//Start: Add this function for query with multiple conditions, SUNZHE, 2017-06-20
	public List<User> getAllUsers(UserCondition condition, RowBounds rb){
		try{
			String userName = getStringCondition(condition.getUserName());
			userName = new String(userName.getBytes("ISO-8859-1"),"utf-8");
			condition.setUserName(userName);
		}catch(Exception e){
			
		}
		
		List<Role> roles = authorizeMapper.getAllRole(new RowBounds());
		List<UserRole> userRoles = authorizeMapper.getUserRoleAllRelations();		
		
		List<User> users = authorizeMapper.getAllUsers(
				getStringCondition(condition.getUserName()),
				getIntegerCondition(condition.getUserShopId()),
				getIntegerCondition(condition.getUserBUId()),
				getIntegerCondition(condition.getUserRoleId()), rb);

		for (User user : users) {
			user.setShop(shopMapper.getById(user.getShopId()));
			user.setBu(buMapper.getById(user.getBuId()));
			
			for (UserRole userRole : userRoles) {
				if (user.getId() == userRole.getUserId()) {
					for (Role role : roles) {
						if (userRole.getRoleId() == role.getId()) {
							user.getRoles().add(role);
						}
					}
				}
			}			
		}
		
		return users;
	}	
	//End: Add this function for query with multiple conditions, SUNZHE, 2017-06-20

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param roleId
	 * @return
	 * @author:[Freud]
	 * @throws ServiceBusinessException
	 * @update:[2015-3-19] [更改人姓名][变更描述]
	 */
	public Role getRoleById(int roleId) throws ServiceBusinessException {
		List<Role> roles = authorizeMapper.getRoleById(roleId);

		if (roles == null || roles.isEmpty()) {
			handleBusinessException("未找到指定的的Role信息");
		}

		Role role = roles.get(0);// 通过ID查到的角色只会有0个或者1个

		List<RoleResource> roleResources = authorizeMapper
				.getRoleResourceRelations(role.getId());

		List<Integer> resourceIds = new ArrayList<Integer>();

		for (RoleResource roleResource : roleResources) {
			resourceIds.add(roleResource.getResourceId());
		}

		if (!resourceIds.isEmpty()) {
			role.getResources().addAll(
					authorizeMapper.getResourcesByIds(resourceIds));
		}

		return role;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param rb
	 * @return
	 * @author:[Freud]
	 * @update:[2015-3-19] [更改人姓名][变更描述]
	 */
	public List<Role> getAllRoles(RowBounds rb) {
		List<Role> roles = authorizeMapper.getAllRole(rb);
		List<Resource> resources = authorizeMapper
				.getAllResources(new RowBounds());
		List<RoleResource> roleResources = authorizeMapper
				.getAllRoleResourceRelations();

		for (Role role : roles) {
			for (RoleResource roleResource : roleResources) {
				if (role.getId() == roleResource.getRoleId()) {
					for (Resource resource : resources) {
						if (resource.getId() == roleResource.getResourceId()) {
							role.getResources().add(resource);
						}
					}
				}
			}
		}
		return roles;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param user
	 * @author:[Freud]
	 * @throws ServiceBusinessException
	 * @update:[2015-3-19] [更改人姓名][变更描述]
	 */
	public void insertUser(User user) throws ServiceBusinessException {
		User exist = authorizeMapper.getUserByName(user.getUsername());

		if (exist != null) {
			handleBusinessException(MessageFormat.format("已经存在用户名为[{0}]的用户",
					user.getUsername()));
		}

		user.setPassword(MD5Util.MD5(user.getPassword()).toLowerCase());
		user.setCreateDate(DateUtil.currentTimestamp());
		user.setOperateDate(DateUtil.currentTimestamp());
		authorizeMapper.insertUser(user);
		exist = authorizeMapper.getUserByName(user.getUsername());

		UserRole userRole = null;

		for (Role role : user.getRoles()) {
			userRole = new UserRole();
			userRole.setUserId(exist.getId());
			userRole.setRoleId(role.getId());
			authorizeMapper.insertUserRole(userRole);
		}
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param role
	 * @author:[Freud]
	 * @throws ServiceBusinessException
	 * @update:[2015-3-19] [更改人姓名][变更描述]
	 */
	public void insertRole(Role role) throws ServiceBusinessException {
		Role exist = authorizeMapper.getRoleByName(role.getRoleName());
		if (exist != null) {
			handleBusinessException(MessageFormat.format("已经存在名为[{0}]的权限",
					role.getRoleName()));
		}

		authorizeMapper.insertRole(role);

		exist = authorizeMapper.getRoleByName(role.getRoleName());

		RoleResource roleResource = null;
		for (Resource resource : role.getResources()) {
			roleResource = new RoleResource();
			roleResource.setRoleId(exist.getId());
			roleResource.setResourceId(resource.getId());

			authorizeMapper.insertRoleResource(roleResource);
		}

	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param user
	 * @author:[Freud]
	 * @update:[2015-3-19] [更改人姓名][变更描述]
	 */
	public void updateUser(User user) throws ServiceBusinessException {
		User exist = authorizeMapper.getUserByName(user.getUsername());
		if (exist == null) {
			handleBusinessException(MessageFormat.format("不存在用户名为[{0}]的用户",
					user.getUsername()));
		}
		if (user.getPassword().equals(UNIQUE_PASSWORD)) {
			// exist.setPassword(exist.getPassword());
		} else {
			exist.setPassword(MD5Util.MD5(user.getPassword()).toLowerCase());
		}
		exist.setShopId(user.getShopId());
		exist.setRealname(user.getRealname());
		exist.setMobile(user.getMobile());
		exist.setDingding(user.getDingding());
		exist.setBuId(user.getBuId());
		exist.setProvinceId(user.getProvinceId());
		exist.setCityId(user.getCityId());
		exist.setEmail(user.getEmail());
		exist.setCities(user.getCities());		
		exist.setOperateDate(DateUtil.currentTimestamp());
		authorizeMapper.updateUser(exist);

		if (user.getRoles() != null && !user.getRoles().isEmpty()) {
			authorizeMapper.removeUserRoleByUserId(user.getId());

			UserRole userRole = null;

			for (Role role : user.getRoles()) {
				userRole = new UserRole();
				userRole.setUserId(exist.getId());
				userRole.setRoleId(role.getId());
				authorizeMapper.insertUserRole(userRole);
			}
		}
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param role
	 * @author:[Freud]
	 * @update:[2015-3-19] [更改人姓名][变更描述]
	 */
	public void udpateRole(Role role) throws ServiceBusinessException {
		Role exist = authorizeMapper.getRoleByName(role.getRoleName());
		if (exist == null) {
			handleBusinessException(MessageFormat.format("不存在名为[{0}]的权限",
					role.getRoleName()));
		}

		authorizeMapper.updateRole(role);
		authorizeMapper.removeRoleResourceByRoleId(role.getId());

		RoleResource roleResource = null;
		for (Resource resource : role.getResources()) {
			roleResource = new RoleResource();
			roleResource.setRoleId(exist.getId());
			roleResource.setResourceId(resource.getId());

			authorizeMapper.insertRoleResource(roleResource);
		}
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param userId
	 * @author:[Freud]
	 * @update:[2015-3-19] [更改人姓名][变更描述]
	 */
	public void deleteUser(int userId) {
		authorizeMapper.removeUser(userId);
		authorizeMapper.removeUserRoleByUserId(userId);
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param roleId
	 * @author:[Freud]
	 * @throws ServiceBusinessException
	 * @update:[2015-3-19] [更改人姓名][变更描述]
	 */
	public void deleteRole(int roleId) throws ServiceBusinessException {
		List<Integer> roleIds = new ArrayList<Integer>();
		roleIds.add(roleId);

		List<UserRole> userRoles = authorizeMapper
				.getUserRoleRelationsByRoleIds(roleIds);
		if (CollectionUtils.isNotEmpty(userRoles)) {
			handleBusinessException("由于角色绑定了用户，暂时不能删除。");
		}

		authorizeMapper.removeRole(roleId);
		authorizeMapper.removeUserRoleByRoleId(roleId);
		authorizeMapper.removeRoleResourceByRoleId(roleId);
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param resourceId
	 * @return
	 * @author:[Freud]
	 * @throws ServiceAuthorizeException
	 * @update:[2015-3-19] [更改人姓名][变更描述]
	 */
	public List<Resource> getNavigationResources(String resourceId)
			throws ServiceAuthorizeException {
		String username = SecurityContextUtil.getUserName();
		List<Role> currentroles = new ArrayList<>();

		currentroles = SecurityContextUtil.getRoleByUser(username);

		List<Integer> currentroleIds = new ArrayList<>();
		for (Role role : currentroles) {
			currentroleIds.add(role.getId());
		}
		if (CollectionUtils.isEmpty(currentroleIds))
			currentroleIds = null;
		List<Resource> resources = authorizeMapper.getNavigationResources(
				resourceId, currentroleIds);

		Set<Integer> resourceIds = new HashSet<Integer>();

		for (Resource resource : resources) {
			resourceIds.add(resource.getId());
		}

		List<RoleResource> roleResources = new ArrayList<RoleResource>();

		if (!resourceIds.isEmpty()) {
			roleResources
					.addAll(authorizeMapper
							.getRoleResourceRelationsByResourceIds(new ArrayList<Integer>(
									resourceIds)));
		}

		Set<Integer> roleIds = new HashSet<Integer>();

		for (RoleResource roleResource : roleResources) {
			roleIds.add(roleResource.getRoleId());
		}

		List<Role> roles = new ArrayList<Role>();

		if (!roleIds.isEmpty()) {
			roles.addAll(authorizeMapper.getRoleByIds(new ArrayList<Integer>(
					roleIds)));
		}

		for (final Resource resource : resources) {
			for (RoleResource roleResource : roleResources) {
				if (resource.getId() == roleResource.getResourceId()) {
					for (Role role : roles) {
						if (roleResource.getRoleId() == role.getId()) {
							resource.getRoles().add(role);
						}
					}
				}
			}
		}

		return resources;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return
	 * @author:[Freud]
	 * @throws ServiceAuthorizeException
	 * @update:[2015-3-19] [更改人姓名][变更描述]
	 */
	public List<Resource> getMainNavigationResources()
			throws ServiceAuthorizeException {
		String username = SecurityContextUtil.getUserName();
		List<Role> currentroles = new ArrayList<>();

		currentroles = SecurityContextUtil.getRoleByUser(username);

		List<Integer> currentroleIds = new ArrayList<>();
		for (Role role : currentroles) {
			currentroleIds.add(role.getId());
		}
		if (CollectionUtils.isEmpty(currentroleIds))
			currentroleIds = null;
		List<Resource> resources = authorizeMapper
				.getMainNavigationResources(currentroleIds);

		Set<Integer> resourceIds = new HashSet<Integer>();

		for (Resource resource : resources) {
			resourceIds.add(resource.getId());
		}

		List<RoleResource> roleResources = new ArrayList<RoleResource>();

		if (CollectionUtils.isNotEmpty(resourceIds)) {
			roleResources
					.addAll(authorizeMapper
							.getRoleResourceRelationsByResourceIds(new ArrayList<Integer>(
									resourceIds)));
		}

		Set<Integer> roleIds = new HashSet<Integer>();

		for (RoleResource roleResource : roleResources) {
			roleIds.add(roleResource.getRoleId());
		}

		List<Role> roles = new ArrayList<Role>();

		if (!roleIds.isEmpty()) {
			roles.addAll(authorizeMapper.getRoleByIds(new ArrayList<Integer>(
					roleIds)));
		}

		for (final Resource resource : resources) {
			for (RoleResource roleResource : roleResources) {
				if (resource.getId() == roleResource.getResourceId()) {
					for (Role role : roles) {
						if (roleResource.getRoleId() == role.getId()) {
							resource.getRoles().add(role);
						}
					}
				}
			}
		}

		return resources;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param rb
	 * @return
	 * @author:[Freud]
	 * @update:[2015-3-19] [更改人姓名][变更描述]
	 */
	public List<Resource> getAllResourcesAsTree(RowBounds rb) {
		List<Resource> resources = this.getAllResourcesAsList(rb);

		List<Resource> ret = new ArrayList<Resource>();
		for (Resource resource : resources) {
			if (resource.getLevel() == LEVEL_FIRST_NAVIGATION_RESOURCE) {
				resource.getResources()
						.addAll(this.findNextLevelResources(resource.getId(),
								resources));
				ret.add(resource);
			}
		}

		return ret;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @return
	 * @author:[Freud]
	 * @update:[2015-4-8] [更改人姓名][变更描述]
	 */
	public List<Resource> getAllResourcesAsTreeAndNotValid() {
		List<Resource> resources = authorizeMapper
				.getResourcesByLevel(LEVEL_ROOT_RESOURCE);

		List<Resource> ret = new ArrayList<Resource>();
		resources
				.get(0)
				.getResources()
				.addAll(getResourcesAsTreeAndNotValidByParentId(resources
						.get(0).getId()));
		ret.addAll(resources);

		return ret;
	}

	public List<Resource> getResourcesAsTreeAndNotValidByParentId(int parentId) {
		List<Resource> resources = this
				.getResourcesAsListWithNotValidByParentId();

		List<Resource> ret = new ArrayList<Resource>();
		for (Resource resource : resources) {
			if (resource.getParentId() == parentId) {
				resource.getResources()
						.addAll(this.findNextLevelResources(resource.getId(),
								resources));
				ret.add(resource);
			}
		}

		return ret;
	}

	public List<Resource> getResourcesAsListWithNotValidByParentId() {
		List<Resource> resources = authorizeMapper
				.getAllResourcesWithNotValid();
		List<RoleResource> roleResources = authorizeMapper
				.getAllRoleResourceRelations();
		List<Role> roles = authorizeMapper.getAllRole(new RowBounds());

		for (final Resource resource : resources) {
			for (RoleResource roleResource : roleResources) {
				if (resource.getId() == roleResource.getResourceId()) {
					for (Role role : roles) {
						if (roleResource.getRoleId() == role.getId()) {
							resource.getRoles().add(role);
						}
					}
				}
			}
		}

		return resources;
	}

	public List<Resource> getAllResourcesAsList(RowBounds rb) {
		List<Resource> resources = authorizeMapper.getAllResources(rb);
		List<RoleResource> roleResources = authorizeMapper
				.getAllRoleResourceRelations();
		List<Role> roles = authorizeMapper.getAllRole(new RowBounds());

		for (final Resource resource : resources) {
			for (RoleResource roleResource : roleResources) {
				if (resource.getId() == roleResource.getResourceId()) {
					for (Role role : roles) {
						if (roleResource.getRoleId() == role.getId()) {
							resource.getRoles().add(role);
						}
					}
				}
			}
		}

		return resources;
	}

	private List<Resource> findNextLevelResources(int parentId,
			List<Resource> resources) {
		List<Resource> list = new ArrayList<Resource>();
		for (Resource resource : resources) {
			if (resource.getParentId() == parentId) {
				List<Resource> childResources = this.findNextLevelResources(
						resource.getId(), resources);
				if (childResources != null && !childResources.isEmpty()) {
					resource.getResources().addAll(childResources);
				}
				list.add(resource);
			}
		}
		return list;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param resourceId
	 * @return
	 * @author:[Freud]
	 * @throws ServiceBusinessException
	 * @update:[2015-3-20] [更改人姓名][变更描述]
	 */
	public Resource getResourceById(int resourceId)
			throws ServiceBusinessException {

		Resource resource = authorizeMapper.getResourcesById(resourceId);
		if (resource == null) {
			handleBusinessException("未找到指定的的Resource信息");
		}

		List<RoleResource> roleResources = authorizeMapper
				.getRoleResourceRelationsByResourceId(resourceId);

		List<Integer> roleIds = new ArrayList<Integer>();

		for (RoleResource roleResource : roleResources) {
			roleIds.add(roleResource.getRoleId());
		}

		if (!roleIds.isEmpty()) {
			resource.getRoles().addAll(authorizeMapper.getRoleByIds(roleIds));
		}

		return resource;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param request
	 * @return
	 * @author:[Freud]
	 * @throws ServiceAuthorizeException
	 * @update:[2015-3-26] [更改人姓名][变更描述]
	 */
	public void insertNewResource(ResourceRequest request)
			throws ServiceAuthorizeException {
		Resource resource = new Resource();

		resource.setParentId(request.getParentsResource());
		resource.setLevel(request.getParentsLevel() + 1);
		resource.setPriority(request.getPriority());
		resource.setImageUrl(request.getImageUrl());
		resource.setDescription(request.getResourceName());

		resource.setUpdateUser(SecurityContextUtil.getUserName());
		resource.setUpdateTime(DateUtil
				.currentTimestamp2String(DateUtil.PATTERN_STANDARD));
		resource.setIfValid(request.isValidate());
		resource.setLink(request.getLink());

		authorizeMapper.insertResource(resource);

	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param request
	 * @return
	 * @author:[Freud]
	 * @throws ServiceBusinessException
	 * @update:[2015-3-26] [更改人姓名][变更描述]
	 */
	public void updateResource(ResourceRequest request)
			throws ServiceBusinessException {

		Resource resource = authorizeMapper.getResourcesById(request.getId());
		if (resource == null) {
			handleBusinessException("未找到指定的的Resource信息");
		}

		resource.setPriority(request.getPriority());
		resource.setImageUrl(request.getImageUrl());
		resource.setLink(request.getLink());
		resource.setDescription(request.getResourceName());
		resource.setUpdateUser(SecurityContextUtil.getUserName());
		resource.setUpdateTime(DateUtil
				.currentTimestamp2String(DateUtil.PATTERN_STANDARD));
		resource.setIfValid(request.isValidate());

		authorizeMapper.updateResource(resource);

	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @author:[Freud]
	 * @throws ServiceBusinessException
	 * @update:[2015-3-26] [更改人姓名][变更描述]
	 */
	public void removeResource(int id) throws ServiceBusinessException {
		Resource resource = authorizeMapper.getResourcesById(id);
		if (resource == null) {
			handleBusinessException("未找到指定的的Resource信息");
		}

		List<Resource> childResources = authorizeMapper
				.getResourceByParentId(resource.getId());

		if (childResources != null && !childResources.isEmpty()) {
			handleBusinessException("要删除的资源绑定了子资源，请先确认所有关联子资源已被删除。");
		}

		authorizeMapper.removeResourceById(id);
	}

	private void handleBusinessException(String error)
			throws ServiceBusinessException {
		LogUtil.error(log, "出现错误：[" + error + "]");
		throw new ServiceBusinessException(error);
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param parentId
	 * @return
	 * @author:[Freud]
	 * @update:[2015-4-8] [更改人姓名][变更描述]
	 */
	public List<Resource> getAllResourceWithCurrentRole(int parentId) {
		List<Role> roles = SecurityContextUtil.getCurrentRoles();

		List<RoleResource> roleResources = new ArrayList<RoleResource>();
		for (Role role : roles) {
			List<RoleResource> roleResource = authorizeMapper
					.getRoleResourceRelations(role.getId());
			if (roleResource != null) {
				roleResources.addAll(roleResource);
			}
		}

		List<Integer> validatedResourceIds = new ArrayList<Integer>();

		for (RoleResource roleResource : roleResources) {
			validatedResourceIds.add(roleResource.getResourceId());
		}

		List<Resource> resources = authorizeMapper
				.getResourceByParentId(parentId);
		List<Resource> ret = new ArrayList<Resource>();

		for (Resource resource : resources) {
			if (validatedResourceIds.contains(resource.getId())) {
				resource.getResources().addAll(
						getAllResourceWithCurrentRole(resource.getId()));
				ret.add(resource);
			}
		}

		return ret;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @author:[Freud]
	 * @update:[2015-7-29] [更改人姓名][变更描述]
	 */
	public ModelAndView getResourceEditPath(ModelAndView mav, int id) {
		Resource resource = authorizeMapper.getResourcesById(id);
		if (resource != null) {
			mav.addObject("parentsLevel", resource.getLevel());
			mav.addObject("parentsResourcePath", getResourceEditPath(resource));
		}
		return mav;
	}

	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @author:[Freud]
	 * @update:[2015-4-8] [更改人姓名][变更描述]
	 */
	public String getResourceEditPath(Resource resource) {
		String ret = "/ ";

		if (resource != null) {
			ret = resource.getDescription() + " / ";

			String parent = getResourceEditPath(authorizeMapper
					.getResourcesById(resource.getParentId()));
			if (parent != null) {
				ret = parent + ret;
			}

		}

		return ret;
	}
}
