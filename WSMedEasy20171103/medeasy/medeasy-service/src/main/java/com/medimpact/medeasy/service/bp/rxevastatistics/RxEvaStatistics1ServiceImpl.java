package com.medimpact.medeasy.service.bp.rxevastatistics;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.statistic.HospitalDrugUseDrugVarietyBi;
import com.medimpact.medeasy.common.bean.statistic.HospitalDrugUseStBi;
import com.medimpact.medeasy.common.bean.statistic.RxEvaStBi;
import com.medimpact.medeasy.common.bean.statistic.RxReasobleBi;
import com.medimpact.medeasy.common.constant.ERROCONSTANT;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.form.SearchForm;
import com.medimpact.medeasy.common.utils.SearchFormUtil;
import com.medimpact.medeasy.common.utils.StUtil;
import com.medimpact.medeasy.dao.bp.rxevastatistics.RxEvaStatistics1Dao;
import com.medimpact.medeasy.dao.bp.rxevastatistics.RxEvaStatistics1DenominatorUnChangeDao;
import com.medimpact.medeasy.dao.bp.rxevastatistics.RxEvaStatistics1RsDao;
import com.medimpact.medeasy.dao.bp.rxevastatistics.RxEvaStatistics2RsDao;
import com.medimpact.medeasy.dao.bp.rxevastatistics.RxEvaStatisticsDao;
import com.medimpact.medeasy.dao.bp.rxevastatistics.RxEvaStatisticsHpDao;

/*不受条件影响：
 合理处方占比
不合理处方占比
不合理抗菌药处方占比
抗菌药处方占比 - */
@Service
public class RxEvaStatistics1ServiceImpl implements RxEvaStatistics1Service {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Resource
	private RxEvaStatistics1Dao dao;

	@Resource
	private RxEvaStatistics1RsDao rxEvaStatistics1RsDao;

	@Resource
	private RxEvaStatistics2RsDao rxEvaStatistics2RsDao; // 药品使用金额

	@Resource
	private RxEvaStatistics1DenominatorUnChangeDao dao1;

	@Override
	public DataModel<List<RxEvaStBi>> getRxEvaStatisticsData(SearchForm form) {

		List<RxEvaStBi> list = new ArrayList<>();
		List<RxReasobleBi> listRs = new ArrayList<>();
		List<RxReasobleBi> listRs2 = new ArrayList<>();
		List<RxEvaStBi> list1 = new ArrayList<>();

		int opLevel = form.getOperateLevel(); // user所在角色对数据的操作权限

		if (form.getPatientType() != null && form.getPatientType() == SYSCONSTANT.OUTPATIENT_URGENT)
			form.setPatientType(null); // 检索时， 当时门急诊的时候， 表示检索所有的，此处不含住院

		if (form.getLocalDrugId() != null && form.getLocalDrugId().isEmpty()) {
			form.setLocalDrugId(null);
		}
		
		boolean isDRel = SearchFormUtil.isDrugRelatedSearch1(form);

		if (isDRel) {

			SearchFormUtil.controlDrugSearchPrio(form);
		}

		switch (opLevel) {

		case SYSCONSTANT.CON_SYS:
			System.err.println("1: Start "+new Date());
			list = dao.listBySys(form);
		
			listRs = rxEvaStatistics1RsDao.listBySys(form);
		
			listRs2 = rxEvaStatistics2RsDao.listBySys(form);
			System.err.println("3: End "+new Date());
		/*	System.err.println("1size= " + listRs.size() + " " + list.size() + " isDRel=" + isDRel);*/

			if (isDRel) {
				// drugrelated seach, 分母不受药品检索的影响
				list1 = dao1.listBySys(form);
			}
			break;

		case SYSCONSTANT.CON_AREA:
			list = dao.listByArea(form);
			listRs = rxEvaStatistics1RsDao.listByArea(form);
			listRs2 = rxEvaStatistics2RsDao.listBySys(form);
			if (isDRel) {
				// drugrelated seach, 分母不受药品检索的影响
				list1 = dao1.listByArea(form);
			}
			break;
		case SYSCONSTANT.CON_HOSPITAL:
			list = dao.listByHospital(form);
			listRs = rxEvaStatistics1RsDao.listByHospital(form);
			listRs2 = rxEvaStatistics2RsDao.listBySys(form);
			if (isDRel) {
				// drugrelated seach, 分母不受药品检索的影响
				list1 = dao1.listByHospital(form);
			}
			break;

		default:
			return DataModel.getFailDataModalNotException(ERROCONSTANT.HASNO_AUTHORITY);
		}
		
		this.evaRsCP(list, listRs);
		
		this.evaRsCP2(list, listRs2);
	
		this.st(list);
		
		if (isDRel == true) {
			this.evaRsCP(list1, listRs);

			this.st1(list1);

			this.evaRsCP1(list, list1);
		}

		// 按照nonOkamount进行排序
		Collections.sort(list, (o1, o2) -> o2.getPerNonOkAmount().compareTo(o1.getPerNonOkAmount()));
	
		StUtil.calRank(list); // 排名
		
		// 对就诊类型的显示
		for (RxEvaStBi bi : list) {
			if (form.getPatientType() == null || form.getPatientType().isEmpty()) {
				bi.setPatientType(SYSCONSTANT.OUTPATIENT_URGENT_CHS);
			} else {
				if (form.getPatientType().equals(SYSCONSTANT.OUTPATIENT))
					bi.setPatientType(SYSCONSTANT.OUTPATIENT_CHS);

				if (form.getPatientType().equals(SYSCONSTANT.URGENT_CARE))
					bi.setPatientType(SYSCONSTANT.URGENT_CARE_CHS);
			}
		}
		
		return DataModel.getSucDataModal(list);
	}

	public void st(List<RxEvaStBi> list) {
		if (!list.isEmpty()) {

			StUtil.okPerRXAmount(list); // 合理处方占比
			StUtil.nonOkPerRXAmount(list); // 不合理处方占比
			StUtil.nonOKAbxPerRXAmount(list); // 不合理抗菌药处方占比
			StUtil.abxPerRXAmount(list); // 抗菌药处方占比
			StUtil.amountAvgRx(list);
		}
	}

	public void st1(List<RxEvaStBi> list) {
		if (!list.isEmpty()) {
			StUtil.okPerRXAmount(list); // 合理处方占比
			StUtil.nonOkPerRXAmount(list); // 不合理处方占比
			StUtil.nonOKAbxPerRXAmount(list); // 不合理抗菌药处方占比
			StUtil.abxPerRXAmount(list); // 抗菌药处方占比

		}
	}

	// 把list2里对应和list医院一样的 赋值进来
	public void evaRsCP(List<RxEvaStBi> list, List<RxReasobleBi> list2) {
		Map<String, RxEvaStBi> map = list.stream().filter((a) -> a != null && a.getHospitalCode() != null)
				.collect(Collectors.toMap(RxEvaStBi::getHospitalCode, bi -> bi));

		Map<String, RxReasobleBi> map1 = list2.stream().filter((a) -> a != null && a.getHospitalCode() != null)
				.collect(Collectors.toMap(RxReasobleBi::getHospitalCode, bi -> bi));

		map.forEach((k, v) -> {
			if (map1.get(k) != null) {
				v.setOkAmount(map1.get(k).getOkAmount());
				v.setUnReasonableAmount1(map1.get(k).getUnReasonableAmount1());
				v.setUnReasonableAmount2(map1.get(k).getUnReasonableAmount2());
				v.setUnReasonableAmount3(map1.get(k).getUnReasonableAmount3());
				v.setAbxCount(map1.get(k).getAbxAmount());
				v.setNonOkAbxAmount(map1.get(k).getNonOkAbxAmount());
				v.setNonOkamount(map1.get(k).getNonOkamount());
			}
		});
	}

	// 不受分母影响的
	public void evaRsCP1(List<RxEvaStBi> list, List<RxEvaStBi> list2) {
		Map<String, RxEvaStBi> map = list.stream().filter((a) -> a != null && a.getHospitalCode() != null)
				.collect(Collectors.toMap(RxEvaStBi::getHospitalCode, bi -> bi));

		Map<String, RxEvaStBi> map1 = list2.stream().filter((a) -> a != null && a.getHospitalCode() != null)
				.collect(Collectors.toMap(RxEvaStBi::getHospitalCode, bi -> bi));

		map.forEach((k, v) -> {
			if (map1.get(k) != null) {
				v.setPerOkAmount(map1.get(k).getPerOkAmount());
				v.setPerNonOkAmount(map1.get(k).getPerNonOkAmount());
				v.setPerNonOkAbxAmount(map1.get(k).getPerNonOkAbxAmount());
				v.setPerAbxAmount(map1.get(k).getPerAbxAmount());
			}
		});
	}

	// 药品使用金额
	public void evaRsCP2(List<RxEvaStBi> list, List<RxReasobleBi> list2) {
		Map<String, RxEvaStBi> map = list.stream().filter((a) -> a != null && a.getHospitalName() != null)
				.collect(Collectors.toMap(RxEvaStBi::getHospitalName, bi -> bi));

		Map<String, RxReasobleBi> map1 = list2.stream().filter((a) -> a != null && a.getHospitalName() != null)
				.collect(Collectors.toMap(RxReasobleBi::getHospitalName, bi -> bi));

		map.forEach((k, v) -> {
			if (map1.get(k) != null) {
				v.setMamount(map1.get(k).getMamount());
			}
		});
	}

	// 4 chart
	@Override
	public DataModel<List<RxEvaStBi>> getRxEvaStatisticsDataHpNew(SearchForm form) {

		List<RxEvaStBi> list = new ArrayList<>();
		List<RxReasobleBi> listRs = new ArrayList<>();
		List<RxEvaStBi> list1 = new ArrayList<>();

		int opLevel = form.getOperateLevel(); // user所在角色对数据的操作权限

		if (form.getPatientType() != null && form.getPatientType() == SYSCONSTANT.OUTPATIENT_URGENT)
			form.setPatientType(null); // 检索时， 当时门急诊的时候， 表示检索所有的，此处不含住院

		if (form.getLocalDrugId() != null && form.getLocalDrugId().isEmpty()) {
			form.setLocalDrugId(null);
		}

		boolean isDRel = SearchFormUtil.isDrugRelatedSearch1(form);

		if (isDRel) {

			SearchFormUtil.controlDrugSearchPrio(form);
		}

		switch (opLevel) {

		case SYSCONSTANT.CON_SYS:
			list = dao.listBySys(form);
			listRs = rxEvaStatistics1RsDao.listBySys(form);

			if (isDRel) {
				// drugrelated seach, 分母不受药品检索的影响
				list1 = dao1.listBySys(form);
			}
			break;

		case SYSCONSTANT.CON_AREA:
			list = dao.listByArea(form);
			listRs = rxEvaStatistics1RsDao.listByArea(form);
			if (isDRel) {
				// drugrelated seach, 分母不受药品检索的影响
				list1 = dao1.listByArea(form);
			}
			break;
		case SYSCONSTANT.CON_HOSPITAL:
			list = dao.listByHospital(form);
			listRs = rxEvaStatistics1RsDao.listByHospital(form);
			if (isDRel) {
				// drugrelated seach, 分母不受药品检索的影响
				list1 = dao1.listByHospital(form);
			}
			break;

		default:
			return DataModel.getFailDataModalNotException(ERROCONSTANT.HASNO_AUTHORITY);
		}

		if (isDRel == true) {
			this.evaRsCP(list1, listRs);
			return DataModel.getSucDataModal(list1);
		} else {
			this.evaRsCP(list, listRs);
			return DataModel.getSucDataModal(list);
		}
	}
}
