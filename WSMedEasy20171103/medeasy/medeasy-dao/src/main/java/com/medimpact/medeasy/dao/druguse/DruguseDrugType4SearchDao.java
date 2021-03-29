package com.medimpact.medeasy.dao.druguse;

import java.util.List;

import com.medimpact.medeasy.common.bean.statistic.HospitalDrugUseDrugVarietyBi;
import com.medimpact.medeasy.common.form.SearchForm;
//增加此接口， 源于search的时候， 如果是drugrelated, 则人均药品品种数的分母，计算方法不同于nondrugrelated
public interface DruguseDrugType4SearchDao {
	
	public List<HospitalDrugUseDrugVarietyBi> listBySys(SearchForm form);
	public List<HospitalDrugUseDrugVarietyBi> listByHospital(SearchForm form);
	public List<HospitalDrugUseDrugVarietyBi> listByArea(SearchForm form);

}
