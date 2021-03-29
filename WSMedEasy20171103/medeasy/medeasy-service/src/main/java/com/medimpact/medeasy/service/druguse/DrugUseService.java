package com.medimpact.medeasy.service.druguse;

import java.util.List;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.statistic.HospitalDrugUseStBi;
import com.medimpact.medeasy.common.form.SearchForm;

public interface DrugUseService {
	DataModel<List<HospitalDrugUseStBi>> drugUseStByHospital(SearchForm form,boolean is4Dt) ;
	DataModel<HospitalDrugUseStBi> drugUseSt4HomePage(SearchForm form) ;
}
