package com.medimpact.medeasy.dao;

import java.util.List;

import com.medimpact.medeasy.common.RequestParameter;
import com.medimpact.medeasy.common.bean.statistic.HerbalUseStBi;
import com.medimpact.medeasy.common.form.SearchForm;

public interface CommonStDao<T>{
	public List<T> listByHospital(RequestParameter form); // hospital admin

	public List<T> listByArea(RequestParameter form); // area admin

	public List<T> listBySys(RequestParameter form); // sys admin
	
	public List<T> listByDr(RequestParameter form); // Dr

	public Long selectCountBySys(RequestParameter form);

	public Long selectCountByArea(RequestParameter form);

	public Long selectCountByHospital(RequestParameter form);
	
	public Long selectCountByDr(RequestParameter form);

}
