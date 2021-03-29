package com.medimpact.medeasy.web.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.medimpact.medeasy.service.security.FilterMetadataSource;
import com.medimpact.medeasy.service.security.MyAccessDecisionManager;
import com.medimpact.medeasy.service.security.UserDetailsServiceImpl;


/**
 * @author Crystal E-mail:
 * @version 创建时间：2017年11月8日 类说明
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stubh
		

		http	
		
				.authorizeRequests()
				/*.antMatchers("/**").hasRole("ADMIN")*/
				.antMatchers("/medeasy-web/").permitAll()	
								
				.and()
				.headers().frameOptions().disable()
				.and()
				.formLogin()
				.loginPage("/login")				
				/*.failureUrl("/login-error")*/
				.and()
                .sessionManagement()
                
               /* .invalidSessionUrl("/login") */
                .invalidSessionUrl("/invalid/") 
                
               
               
               .and()
               .logout()
               .logoutUrl("/ts/logout")
               .invalidateHttpSession(false)
               .logoutSuccessUrl("/login")
               .deleteCookies("JSESSIONID","SESSION")
		
               /* .and()
                .logout()
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(false) 
                
                .deleteCookies("JSESSIONID", "SESSION")*/
                
                
                
               /* .invalidSessionUrl("/invalid")*/
               /* .sessionAuthenticationErrorUrl("/login-error")*/
				/*.and()
				.logout()
				.logoutSuccessUrl("/login")*/	
				
				/*.loginPage("/login1")
				.usernameParameter("username")
				.passwordParameter("password")*/
				
				/*.antMatchers("/login1").permitAll()*/
				/*.and()					
				.exceptionHandling().accessDeniedPage("/denied")*/
	/*			.and()				
				.formLogin()
				.loginPage("/login1")
				.usernameParameter("username")
				.passwordParameter("password")
				.loginProcessingUrl("/login2")
				.defaultSuccessUrl("/index")
				.and()
				.logout()
				.logoutSuccessUrl("/login1")*/
				.and()
				.csrf().disable()
				.addFilter(filterSecurityInterceptor());
		http.sessionManagement().maximumSessions(5);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers("/plugins/**", "/js/**", "/images/**", "/css/**", "/assets/**");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();	
				
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setHideUserNotFoundExceptions(false);
		/*authenticationProvider.setPasswordEncoder(passwordEncoder);*/
		authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		auth.authenticationProvider(authenticationProvider);

	}

	@Bean
	public FilterSecurityInterceptor filterSecurityInterceptor() throws Exception {
		FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
		filterSecurityInterceptor.setAuthenticationManager(authenticationManager);
		filterSecurityInterceptor.setSecurityMetadataSource(securityMetadataSource());
		filterSecurityInterceptor.setAccessDecisionManager(affirmativeBased());
		return filterSecurityInterceptor;

	}

	@Bean
	public MyAccessDecisionManager myAccessDecisionManager(){
		List<AccessDecisionVoter<? extends Object>> accessDecisionVoters = new ArrayList<>();
		accessDecisionVoters.add(new RoleVoter());
		return new MyAccessDecisionManager(accessDecisionVoters);
	}
	@Bean
	public AffirmativeBased affirmativeBased() {
		List<AccessDecisionVoter<? extends Object>> accessDecisionVoters = new ArrayList<>();
		/*accessDecisionVoters.add(new RoleVoter()); */
		RoleVoter roleVoter = new RoleVoter();
		roleVoter.setRolePrefix("ROLE_");
		accessDecisionVoters.add(roleVoter);
		AffirmativeBased affirmativeBased = new AffirmativeBased(accessDecisionVoters);
		return affirmativeBased;
	}

	
	@Bean
	public RoleHierarchyVoter roleVoter() {
		RoleHierarchyVoter roleHierarchyVoter = new RoleHierarchyVoter(roleHierarchy());
		roleHierarchyVoter.setRolePrefix("ROLE_");
		return roleHierarchyVoter;
	}

	// RoleHierarchy
	@Bean
	public RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
		return roleHierarchy;
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public FilterInvocationSecurityMetadataSource securityMetadataSource() {
		return new FilterMetadataSource();
	}
	
	@Bean
	public FilterMetadataSource filterMetadataSource() {
		return new FilterMetadataSource();
	}

}
