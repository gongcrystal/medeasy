package com.medimpact.medeasy.service.bp.basedrugusedstatistics;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.bp.BaseDrugUsedStatisticsBi;
import com.medimpact.medeasy.common.bean.statistic.RxEvaStBi;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.utils.StUtil;
import com.medimpact.medeasy.dao.bp.BaseDrugUsedStatistics.BaseDrugUsedStatisticsDao;

/**
 * Created by matrixliu on 2017/12/2.
 */
@Service
public class BaseDrugUsedStatisticsServiceImpl implements BaseDrugUsedStatisticsService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	public static DecimalFormat decimalFormat = new DecimalFormat("#.####");
	public static DecimalFormat decimalFormat1 = new DecimalFormat("#.##");

	@Resource
	private BaseDrugUsedStatisticsDao baseDrugUsedStatisticsDao;

	@Override
	public DataModel<List<BaseDrugUsedStatisticsBi>> getBaseDrugUsedStatisticsData(BaseDrugUsedStatisticsBi form)
			throws Exception {
/*System.err.println("enter getBaseDrugUsedStatisticsData"+new Date());*/
		if (null != form.getBasicDrugCategory() && !"".equals(form.getBasicDrugCategory())) {
			form.setDrugClassBtndrugcategoryid(null);
			form.setDrugClassBtndrugid(null);
			/*System.err.println("快速检测");*/
		}

		if ((form.getBasicDrugCategory() == null || form.getBasicDrugCategory().isEmpty())
				&& (form.getDrugClassBtndrugid() != null && !form.getDrugClassBtndrugid().isEmpty())) {
			form.setDrugClassBtndrugcategoryid(null);
			/*System.err.println("药品名称");*/
		}

		if (form.getPatientTypeKey() != null && form.getPatientTypeKey().equals(SYSCONSTANT.OUTPATIENT_URGENT)) {
			form.setPatientTypeKey(null); // 检索时， 当时门急诊的时候， 表示检索所有的，此处不含住院
		}
/*System.err.println("baseDrugUsed 1:"+new Date());*/
		List<BaseDrugUsedStatisticsBi> li = baseDrugUsedStatisticsDao.getBaseDrugUsedStatistics(form);
		
		/*System.err.println("baseDrugUsed 2:"+new Date());*/
		List<BaseDrugUsedStatisticsBi> li1 = baseDrugUsedStatisticsDao.getBaseDrugUsed1Statistics(form);
		/*System.err.println("baseDrugUsed 3:"+new Date());*/
		List<BaseDrugUsedStatisticsBi> li2 = baseDrugUsedStatisticsDao.getBaseDrugUsedAmount(form); // 药品使用金额之和,分母不受“药品/药品分类/快速选择药品”条件影响
		System.err.println("crystal "+li.size()+ " "+li1.size()+" "+li2.size());
		/*System.err.println("baseDrugUsed 4:"+new Date());*/
		/*for(BaseDrugUsedStatisticsBi bi: li){
			System.err.println("11:"+bi.getHospitalCode());
		}
		
		for(BaseDrugUsedStatisticsBi bi: li1){
			System.err.println("12:"+bi.getHospitalCode()+" "+bi.getBaseDrugAmount());
		}
		
		for(BaseDrugUsedStatisticsBi bi: li2){
			System.err.println("13:"+bi.getHospitalCode()+" "+bi.getAmount());
		}*/
		
		bDrugAmountCP(li, li1);
		bDrugAmountCP1(li, li2);

		li.stream().filter(v -> v.getAmount() != null && v.getAmount().compareTo(BigDecimal.ZERO) != 0)
				.forEach(v -> v.setAmountPercentage(Double.valueOf(decimalFormat1
						.format(v.getBaseDrugAmount().divide(v.getAmount(), 4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100))))));

		// 按照nonOkamount进行排序
		Collections.sort(li, (o1, o2) -> o2.getBaseDrugAmount().compareTo(o1.getBaseDrugAmount()));

		StUtil.calRank(li); // 排名

		/*
		 * Long records = baseDrugUsedStatisticsDao.getBaseDrugUsedCount(form);
		 * Long total = records/form.getRows() +1;
		 */
		return DataModel.getSucDataModal(li);
		/*
		 * return DataModel.getSucDataModal(li, form.getPage(), records, total);
		 */
	}

	/* 药品使用金额 */
	public void bDrugAmountCP(List<BaseDrugUsedStatisticsBi> list, List<BaseDrugUsedStatisticsBi> list1) {
		Map<String, BaseDrugUsedStatisticsBi> map = list.stream()
				.filter((a) -> a != null && a.getHospitalCode() != null)
				.collect(Collectors.toMap(BaseDrugUsedStatisticsBi::getHospitalCode, bi -> bi));

		Map<String, BaseDrugUsedStatisticsBi> map1 = list1.stream()
				.filter((a) -> a != null && a.getHospitalCode() != null)
				.collect(Collectors.toMap(BaseDrugUsedStatisticsBi::getHospitalCode, bi -> bi));

		map.forEach((k, v) -> {
			if (map1.get(k) != null) {
				v.setBaseDrugAmount(map1.get(k).getBaseDrugAmount());
			}
		});
	}

	/* 药品使用金额之和 */
	public void bDrugAmountCP1(List<BaseDrugUsedStatisticsBi> list, List<BaseDrugUsedStatisticsBi> list1) {
		Map<String, BaseDrugUsedStatisticsBi> map = list.stream()
				.filter((a) -> a != null && a.getHospitalCode() != null)
				.collect(Collectors.toMap(BaseDrugUsedStatisticsBi::getHospitalCode, bi -> bi));

		Map<String, BaseDrugUsedStatisticsBi> map1 = list1.stream()
				.filter((a) -> a != null && a.getHospitalCode() != null)
				.collect(Collectors.toMap(BaseDrugUsedStatisticsBi::getHospitalCode, bi -> bi));

		map.forEach((k, v) -> {
			if (map1.get(k) != null) {
				v.setAmount(map1.get(k).getAmount());
			}
		});
	}
}
