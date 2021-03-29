package com.medimpact.medeasy.service.security;

import java.util.List;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.RoleAuthorityBi;
import com.medimpact.medeasy.common.form.SearchForm;

public interface RoleService1 {
	DataModel<List<RoleAuthorityBi>> getAuthorities(SearchForm form);

}
