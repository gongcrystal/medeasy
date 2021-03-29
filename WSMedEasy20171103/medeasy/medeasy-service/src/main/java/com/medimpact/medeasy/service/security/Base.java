package com.medimpact.medeasy.service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/** 
* @author Crystal E-mail: 
* @version 创建时间：2017年10月12日 
* 类说明 
*/
@Configuration
public class Base {
	
	@Bean
	public FilterMetadataSource filterMetadataSource() {
		return new FilterMetadataSource();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
