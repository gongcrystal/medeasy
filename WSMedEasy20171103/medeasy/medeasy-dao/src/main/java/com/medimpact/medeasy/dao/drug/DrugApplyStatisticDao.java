package com.medimpact.medeasy.dao.drug;

import java.util.List;

import com.medimpact.medeasy.common.bean.drug.DrugApplySt;

public interface DrugApplyStatisticDao {
	List<DrugApplySt> getDrugApplyStatistic();
	List<Long> getDrugApplyStatistic1();
}
