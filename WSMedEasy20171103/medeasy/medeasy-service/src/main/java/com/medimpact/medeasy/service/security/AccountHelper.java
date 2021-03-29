package com.medimpact.medeasy.service.security;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/** 
* @author Crystal E-mail: 
* @version 创建时间：2017年11月9日 
* 类说明 
*/
public class AccountHelper {
	
	private static final  Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	private static final List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
	
	public static String getUserName(){
		 if (principal instanceof UserDetails) {
	         return ((UserDetails) principal).getUsername();	         
	      }

	      if (principal instanceof Principal) {	 	    	
	         return ((Principal) principal).getName();
	      }    	     

	      return String.valueOf(principal);
	}
	
	/*user所拥有的权限*/
	public static String getRoles(){
		String authStr="";
		for (GrantedAuthority grantedAuthority : authorities) {
			authStr +=grantedAuthority.getAuthority();
		}
		if (!authStr.equals("")) {
			return authStr.trim();
		}
		return authStr;		
	}
	
}
