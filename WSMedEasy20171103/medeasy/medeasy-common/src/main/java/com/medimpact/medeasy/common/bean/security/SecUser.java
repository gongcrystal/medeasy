package com.medimpact.medeasy.common.bean.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.medimpact.medeasy.common.RequestParameter;
import com.medimpact.medeasy.common.bean.HospitalBi;

	
public class SecUser extends RequestParameter  implements UserDetails,Serializable {
	private static final long serialVersionUID = 1L;	
	private int id;	
	private String username;  //登陆名
	private String name; //姓名
	private String deptName;
	private String deptId;
	private String hospitalName;
	private String hospitalId;
	private String areaCode;
	private String areaName;
	
	private String mobile;
	private String hospitalCode; 
	private String doctorCode;
	private String doctorName;
	private String deptCode;
	
	private String password;
    private boolean enabled;  
    private boolean accountNonExpired;  
    private boolean accountNonLocked;  
    private boolean credentialsNonExpired;  
    private Collection<GrantedAuthority>  authorities;
    private SecRole role;
    private Date created;
    private Date lasted;
    private HospitalBi hospitalBi;
    
    //用于检索
    private String searchDoctorName=null;
    private String searchHospitalCode=null;
    private String searchDeptCode=null;
    
    
    
   /* private String roleName;
    private String roleDescription;*/
	

	public HospitalBi getHospitalBi() {
		return hospitalBi;
	}

	public void setHospitalBi(HospitalBi hospitalBi) {
		this.hospitalBi = hospitalBi;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLasted() {
		return lasted;
	}

	public void setLasted(Date lasted) {
		this.lasted = lasted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public SecRole getRole() {
		return role;
	}

	public void setRole(SecRole role) {
		this.role = role;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getDoctorCode() {
		return doctorCode;
	}

	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getSearchDoctorName() {
		return searchDoctorName;
	}

	public void setSearchDoctorName(String searchDoctorName) {
		this.searchDoctorName = searchDoctorName;
	}

	public String getSearchHospitalCode() {
		return searchHospitalCode;
	}

	public void setSearchHospitalCode(String searchHospitalCode) {
		this.searchHospitalCode = searchHospitalCode;
	}

	public String getSearchDeptCode() {
		return searchDeptCode;
	}

	public void setSearchDeptCode(String searchDeptCode) {
		this.searchDeptCode = searchDeptCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	

	/*public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	*/
	
	
	
}
