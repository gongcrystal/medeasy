package com.medimpact.medeasy.service.security;

import java.util.List;
import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.RoleMenuBi;
import com.medimpact.medeasy.common.bean.security.SecRole;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.common.form.RoleMenuForm;
import com.medimpact.medeasy.common.form.SearchForm;

public interface RoleService {	
	public DataModel<List<SecRole>> getSecRoles(int opLevel); //role的总数比较少，不用分页	
	public DataModel<List<RoleMenuForm>> getRoles(SearchForm form); //role的总数比较少，不用分页
	public DataModel<List<RoleMenuForm>> getRolesUniqueRoleName(SearchForm form);  // 把重复的roleName对对应的menu合并为一个
	public DataModel delRole(SecRole secRole);
	public DataModel updateRole(SecRole secRole);
	public DataModel addRole(SecRole secRole);	
	public DataModel<SecRole> getSecRoleByName(String roleName) throws BizException;
}
