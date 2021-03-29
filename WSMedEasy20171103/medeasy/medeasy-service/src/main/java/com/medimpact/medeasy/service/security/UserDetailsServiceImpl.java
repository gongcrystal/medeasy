package com.medimpact.medeasy.service.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.bean.security.SecRole;
import com.medimpact.medeasy.common.bean.security.SecUser;
import com.medimpact.medeasy.dao.security.RoleDao;
import com.medimpact.medeasy.dao.security.UserDao;

/** 
* @author Crystal E-mail: 
* @version 创建时间：2017年10月12日 
* 类说明 
*/

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private MessageSource messageSource;
	

	/*从db中检索出用户的权限*/
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		SecUser secUser = userDao.getUserByname(username);		
		
		if (secUser == null) {
			System.err.println("用户不存在！！！！！！！！");
			throw new UsernameNotFoundException(
					messageSource.getMessage("UserDetails.userNotFound",new Object[]{username},null));
		}
		
		
		
		/*System.out.println("secUser: "+secUser.getUsername()+" userId= "+secUser.getId());
		String password = new BCryptPasswordEncoder().encode("t");
		System.out.println("password="+password);*/
		
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		List<SecRole> roles = roleDao.getRolesByUserId(secUser.getId());
		
		if(roles!=null && roles.size()>0){
			for(SecRole r: roles){
				/*System.out.println("role="+r.getRoleName()+" r.id: "+r.getId()+" "+r.getDescription());*/
				GrantedAuthority auth = new SimpleGrantedAuthority(r.getRoleName());
				auths.add(auth);
			}
			secUser.setAuthorities(auths);
		}
		return secUser;
	}

	
	
	

	/*@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		SecUser secUser = userDao.getUserByname(username);
		
		if (secUser == null) {
			throw new UsernameNotFoundException(
					messageSource.getMessage("UserDetails.userNotFound",new Object[]{username},null));
		}		
		
		List<SecRole> roles = roleDao.getRolesByUserId(secUser.getId());
		if(roles!=null && roles.size()>0){
			for(SecRole r: roles){
				GrantedAuthority auth = new SimpleGrantedAuthority(r.getName());
			}
		}
		
		
		
		GrantedAuthority auth1 = new SimpleGrantedAuthority("ROLE_ADMIN");
		GrantedAuthority auth2 = new SimpleGrantedAuthority("ROLE_USER");
		
		if(username.equals("t")){
			System.out.println("equal");
			auths.add(auth1);
			auths.add(auth2);			
		}
		String password = new BCryptPasswordEncoder().encode(username);	
		System.out.println("password="+password);
		User user1  =  new User(username, password, auths);
		return user1;
	}*/

}
