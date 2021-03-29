package com.medimpact.medeasy.web.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginCtrl {

	// Login form with error
	@RequestMapping("/login-error")
	public String loginError(Model model) {
		
		model.addAttribute("loginError", true);
		return "/security/login-error";
	}
	
	
}
