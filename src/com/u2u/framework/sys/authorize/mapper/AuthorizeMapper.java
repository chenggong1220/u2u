package com.u2u.framework.sys.authorize.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.u2u.framework.sys.authorize.beans.Resource;
import com.u2u.framework.sys.authorize.beans.Role;
import com.u2u.framework.sys.authorize.beans.RoleResource;
import com.u2u.framework.sys.authorize.beans.User;
import com.u2u.framework.sys.authorize.beans.UserRole;
import com.u2u.ibms.common.beans.Asset;

/**
 * @ClassName: AuthorizeMapper <br>
 * @Description: 登陆认证管理的数据库操作接口 <br>
 * @date 2015年1月14日 上午11:30:57 <br>
 * 
 * @author Freud
 */
public interface AuthorizeMapper {
	/**
	 * ----------------USER----------------------
	 */
	User getUserByName(@Param("username") String username);

	User getUserById(@Param("userId") Integer userId);

	List<User> getAllUsers(RowBounds rb);
	
	List<User> getAllUsers(@Param("userName") String userName,
			@Param("userShopId") Integer userShopId,
			@Param("userBUId") Integer userBUId,
			@Param("userRoleId") Integer userRoleId,RowBounds rb);	
	
	List<Asset> getAll(@Param("assetTypeIds") List<Integer> assetTypeIds,
			@Param("rent") Boolean rent,
			@Param("provinceId") Integer provinceId,
			@Param("cityId") Integer cityId, RowBounds rb);	

	void insertUser(User user);

	void updateUser(User user);

	void removeUser(@Param("userId") int userId);

	/**
	 * ----------------ROLE----------------------
	 */
	List<Role> getRoleById(@Param("roleId") int roleId);

	List<Role> getRoleByIds(@Param("roleIds") List<Integer> roleIds);

	Role getRoleByName(@Param("roleName") String roleName);

	List<Role> getAllRole(RowBounds rb);

	void insertRole(Role role);

	void updateRole(Role role);

	void removeRole(@Param("roleId") int roleId);

	/**
	 * ----------------USER_ROLE----------------------
	 */
	List<UserRole> getUserRoleRelationsByUserId(@Param("userId") int userId);

	List<UserRole> getUserRoleRelationsByRoleIds(
			@Param("roleIds") List<Integer> roleIds);

	List<UserRole> getUserRoleAllRelations();

	void insertUserRole(UserRole userRole);

	void removeUserRoleByUserId(@Param("userId") int userId);

	void removeUserRoleByRoleId(@Param("roleId") int roleId);

	/**
	 * ----------------ROLE_RESOURCE----------------------
	 */
	List<RoleResource> getRoleResourceRelations(@Param("roleId") int roleId);

	List<RoleResource> getRoleResourceRelationsByResourceId(
			@Param("resourceId") int resourceId);

	List<RoleResource> getRoleResourceRelationsByResourceIds(
			@Param("resourceIds") List<Integer> resourceIds);

	List<RoleResource> getAllRoleResourceRelations();

	void insertRoleResource(RoleResource roleResource);

	void removeRoleResourceByRoleId(@Param("roleId") int roleId);

	/**
	 * ----------------RESOURCE----------------------
	 */

	/**
	 * 
	 * <p>
	 * Discription:[通过一级导航ID获得左侧导航信息]
	 * </p>
	 * 
	 * @return
	 * @author:[Freud]
	 * @update:[2015-2-9] [更改人姓名][变更描述]
	 */
	public List<Resource> getNavigationResources(
			@Param("resourceId") String resourceId,
			@Param("roleIds") List<Integer> roleIds);

	/**
	 * 
	 * <p>
	 * Discription:[获得所有的一级导航]
	 * </p>
	 * 
	 * @return
	 * @author:[Freud]
	 * @update:[2015-3-19] [更改人姓名][变更描述]
	 */
	public List<Resource> getMainNavigationResources(
			@Param("roleIds") List<Integer> roleIds);

	/**
	 * 
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param rb
	 * @return
	 * @author:[Freud]
	 * @update:[2015-3-20] [更改人姓名][变更描述]
	 */
	public List<Resource> getAllResources(RowBounds rb);

	public List<Resource> getAllResourcesWithNotValid();

	public Resource getResourcesById(@Param("resourceId") int resourceId);

	public List<Resource> getResourcesByLevel(@Param("level") int level);

	public List<Resource> getResourcesByIds(
			@Param("resourceIds") List<Integer> resourceIds);

	public void insertResource(Resource resource);

	public void updateResource(Resource resource);

	public void removeResourceById(@Param("resourceId") int resourceId);

	public List<Resource> getResourceByParentId(@Param("parentId") int parentId);

}
