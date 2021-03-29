package com.medimpact.medeasy.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
* @author Crystal E-mail: 
* @version 创建时间：2017年10月13日 
* 类说明 
*/
@Controller

public class SecurityCtrl {
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPage() {
		
		System.out.println("Crystal!  loginPage!!!!!!!!!!");
		return "login";
	}
	
}
