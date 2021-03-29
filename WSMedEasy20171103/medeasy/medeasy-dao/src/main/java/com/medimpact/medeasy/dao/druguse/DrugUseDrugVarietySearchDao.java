package com.medimpact.medeasy.dao.druguse;

import java.util.List;

import com.medimpact.medeasy.common.bean.statistic.HospitalDrugUseDrugVarietyBi;
import com.medimpact.medeasy.common.form.SearchForm;

public interface DrugUseDrugVarietySearchDao {
	
	public List<HospitalDrugUseDrugVarietyBi> listBySys(SearchForm form);
	public List<HospitalDrugUseDrugVarietyBi> listByHospital(SearchForm form);
	public List<HospitalDrugUseDrugVarietyBi> listByArea(SearchForm form);

}
