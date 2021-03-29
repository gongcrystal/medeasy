package com.medimpact.medeasy.dao.druguse;

import com.medimpact.medeasy.common.bean.statistic.HospitalDrugUseStBi;
import com.medimpact.medeasy.dao.CommonStDao;

//要求点击查询的时候， bigrx 分母不受药品类的影响
public interface DrugUseBigRxSearchDao extends CommonStDao<HospitalDrugUseStBi>{

}
