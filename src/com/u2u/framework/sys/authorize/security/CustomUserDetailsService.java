package com.u2u.framework.sys.authorize.security;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.u2u.framework.sys.authorize.beans.Role;
import com.u2u.framework.sys.authorize.beans.User;
import com.u2u.framework.sys.authorize.service.AuthorizeService;

/**
 * @ClassName: CustomUserDetailsService <br>
 * @Description: 给Spring提供的用户信息的获取类 <br>
 * @date 2015年1月13日 下午2:02:44 <br>
 * 
 * @author xuqinghan
 */
public class CustomUserDetailsService implements UserDetailsService
{
    
    /**
     * LOGGER
     */
    private static final Logger LOGGER = Logger.getLogger(CustomUserDetailsService.class);
    
    /**
     * The authorizeService.
     */
    private AuthorizeService authorizeService;
    
    /**
     * 
     * <p>
     * Discription:[通过UserName加载整个User对象]
     * </p>
     * 
     * @param username
     * @return
     * @throws UsernameNotFoundException
     * @throws DataAccessException
     * @author:[xuqinghan]
     * @update:[2015年1月14日] [更改人姓名][变更描述]
     */
    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException, DataAccessException
    {
        
        UserDetails userDetails = null;
        
        try
        {
            
            User user = authorizeService.getUser(username);
            
            userDetails =
                new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    getAuthorities(user.getRoles()));
            
            LOGGER.info(MessageFormat.format("User [{0}] login the page at {1}", username, new Date()));
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new UsernameNotFoundException("Error in retrieving user");
        }
        
        return userDetails;
    }
    
    /**
     * 
     * <p>
     * Discription:[匹配Role]
     * </p>
     * 
     * @param authorizes
     * @return
     * @author:[xuqinghan]
     * @update:[2015年1月14日] [更改人姓名][变更描述]
     */
    public Collection<GrantedAuthority> getAuthorities(List<Role> authorizes)
    {
        
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(authorizes.size());
        for (Role authorize : authorizes)
        {
            if (null != authorize)
                authList.add(new SimpleGrantedAuthority(authorize.getRoleName()));
        }
        return authList;
    }
    
    /**
     * <p>
     * Discription:[方法功能中文描述]
     * </p>
     * 
     * @return AuthorizeService authorizeService.
     */
    public AuthorizeService getAuthorizeService()
    {
        return authorizeService;
    }
    
    /**
     * <p>
     * Discription:[方法功能中文描述]
     * </p>
     * 
     * @param authorizeService The authorizeService to set.
     */
    public void setAuthorizeService(AuthorizeService authorizeService)
    {
        this.authorizeService = authorizeService;
    }
    
}
