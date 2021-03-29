package com.medimpact.medeasy.service.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.access.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.bean.security.SecAuth;
import com.medimpact.medeasy.dao.menu.RoleMenuDao;
import com.medimpact.medeasy.dao.security.AuthDao;

/**
 * @author Crystal E-mail:
 * @version 创建时间：2017年10月12日 类说明
 */
@Service
public class FilterMetadataSource implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private AuthDao authDao;

	@Autowired
	private RoleMenuDao rmDao;

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object obj) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		FilterInvocation fi = (FilterInvocation) obj;
		String url = fi.getRequestUrl();
		
		/* List<String> roles = authDao.getRoleNamesByUrl(url); */
		List<String> roles = rmDao.getRoleNamesByUrl(url);
		System.out.println(url + " requires role:");

		for (int i = 0; i < roles.size(); i++) {
			System.out.println(roles.get(i));
		}

		String[] rolesStr = roles.stream().toArray(String[]::new);
		return SecurityConfig.createList(rolesStr);

	}

	/*
	 * @Override public Collection<ConfigAttribute> getAttributes(Object obj)
	 * throws IllegalArgumentException { // TODO Auto-generated method stub
	 * FilterInvocation fi = (FilterInvocation)obj; String url =
	 * fi.getRequestUrl();
	 * 
	 * if(url.equals("/login")){ String[] roles = {"ROLE_ANONYMOUS"}; return
	 * SecurityConfig.createList(roles); }else{
	 * System.out.println("Crystal! not login url="+url); String[] roles =
	 * {"ROLE_ADMIN"}; return SecurityConfig.createList(roles); } }
	 */

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return FilterInvocation.class.isAssignableFrom(arg0);
	}

}
