package com.medimpact.medeasy.service.security;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

/** 
* @author Crystal E-mail: 
* @version 创建时间：2017年10月13日 
* 类说明 
*/
@Service
public class MyAccessDecisionManager extends AbstractAccessDecisionManager{

	public MyAccessDecisionManager(List<AccessDecisionVoter<? extends Object>> decisionVoters) {
		super(decisionVoters);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		// TODO Auto-generated method stub
		for(GrantedAuthority au: authentication.getAuthorities())
			System.out.println("decide! "+au.getAuthority());
		return;
	}
}
