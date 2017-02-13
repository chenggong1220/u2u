package com.u2u.framework.sys.authorize.security;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class AdminRoleVoter implements AccessDecisionVoter<Object> {
	@Override
	public boolean supports(ConfigAttribute arg0) {
		return true;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class arg0) {
		System.out.println(this.getClass().getName() + ".supports(Class arg0)");
		return true;
	}

	@Override
	public int vote(Authentication authentication, Object object,
			Collection<ConfigAttribute> attributes) {
		int result = ACCESS_ABSTAIN;

		for (GrantedAuthority ga : authentication.getAuthorities()) {
			if ("ROLE_ADMIN".equals(ga.getAuthority())) { // ga is user's role.
				result = ACCESS_GRANTED;
			}
		}
		return result;
	}
}