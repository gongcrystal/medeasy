package com.medimpact.medeasy.service.drug;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.medimpact.medeasy.common.bean.drug.DrugApplySt;
import com.medimpact.medeasy.dao.drug.DrugApplyStatisticDao;

@Service
public class DrugApplyStServiceImpl implements DrugApplyStService{
	@Resource
	private DrugApplyStatisticDao drugApplyStDao;

	@Override
	public List<DrugApplySt> getDrugApplyStatistic() {
		// TODO Auto-generated method stub
		List<DrugApplySt> sts = drugApplyStDao.getDrugApplyStatistic();
		List<Long> sts1 = drugApplyStDao.getDrugApplyStatistic1();
		
		
		for(Long st: sts1){
			System.out.println(st);
		}
		System.out.println("sts="+sts.size());
		for(DrugApplySt st: sts){
			System.out.println(st.getLabel()+": "+st.getData());
		}
		return sts;
	}

}
