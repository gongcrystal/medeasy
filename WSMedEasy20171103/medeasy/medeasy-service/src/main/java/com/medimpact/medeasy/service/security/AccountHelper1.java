package com.medimpact.medeasy.service.security;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AccountHelper1 {

	public AccountHelper1() {
		super();
	}

	private Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	private List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext()
			.getAuthentication().getAuthorities();

	public String getUserName() {
		if (principal instanceof UserDetails) {
			System.out.println("1.is instance of UserDetails");
			return ((UserDetails) principal).getUsername();
		}

		if (principal instanceof Principal) {
			System.out.println("1.is instance of Principal");
			return ((Principal) principal).getName();
		}

		return String.valueOf(principal);
	}

	/* user所拥有的权限 */
	public String getRoles() {
		String authStr = "";
		for (GrantedAuthority grantedAuthority : authorities) {
			authStr += grantedAuthority.getAuthority();
		}
		if (!authStr.equals("")) {
			return authStr.trim();
		}
		return authStr;
	}

}
