package com.medimpact.medeasy.service.bp.herbalmedicineusedstatistics;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.HerbalUseBi;
import com.medimpact.medeasy.common.bean.statistic.HerbalUseStBi;
import com.medimpact.medeasy.common.constant.ERROCONSTANT;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.form.SearchForm;
import com.medimpact.medeasy.common.utils.SearchFormUtil;
import com.medimpact.medeasy.dao.bp.herbalmedicineusedstatistics.HerbalMedicineUsedStatisticsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matrixliu on 2017/12/2.
 */
@Service
public class HerbalMedicineUsedStatisticsServiceImpl implements HerbalMedicineUsedStatisticsService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private HerbalMedicineUsedStatisticsDao hUDao;

	@Override
	public DataModel<List<HerbalUseStBi>> getHerbalMedicineUsedStatisticsData(SearchForm form) {
		// TODO Auto-generated method stub
		List<HerbalUseStBi> list =  new ArrayList<>();
		Long records=new Long(0);
		Long total ;		
		
		int opLevel = form.getOperateLevel(); // user所在角色对数据的操作权限
		SearchFormUtil.checkAreaHospitalCodeNull(form);
		
		switch (opLevel) {

		case SYSCONSTANT.CON_SYS:
			System.out.println(form.getStartYear()+" "+form.getAreaCode()+" "+form.getHospitalCode()+" "+form.getEndYear()+" "+form.getStartMon()+" "+form.getEndDate());
			list = hUDao.listBySys(form);
		/*	records = hUDao.selectCountBySys(form);*/
			/*for(int i=0; i<list.size(); i++){
				System.out.println(list.get(i).getDecoction());
			}*/
			break;

		case SYSCONSTANT.CON_AREA:
			list = hUDao.listByArea(form);
		/*	records = hUDao.selectCountByArea(form);*/
			break;
		case SYSCONSTANT.CON_HOSPITAL:
			list = hUDao.listByHospital(form);
			/*records = hUDao.selectCountByHospital(form);*/
			break;
		case SYSCONSTANT.CON_USER:
			list = hUDao.listByDr(form);
			/*records = hUDao.selectCountByDr(form);*/
			break;

		default:
			return DataModel.getFailDataModalNotException(ERROCONSTANT.HASNO_AUTHORITY);
		}

		/*total = records / form.getRows() + 1;
		return DataModel.getSucDataModal(list, form.getPage(), records, total);*/
		return DataModel.getSucDataModal(list);
	}   
}
