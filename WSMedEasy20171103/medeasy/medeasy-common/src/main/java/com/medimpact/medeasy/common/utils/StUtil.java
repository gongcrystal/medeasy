package com.medimpact.medeasy.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import com.medimpact.medeasy.common.StatisticParameter;
import com.medimpact.medeasy.common.bean.statistic.HospitalDrugUseStBi;

public class StUtil {

	public static DecimalFormat decimalFormat = new DecimalFormat("#.####");
	public static DecimalFormat decimalFormat1 = new DecimalFormat("#.##");
	public static BigDecimal ONEHANDRUD = new BigDecimal(100);

	public static <T extends StatisticParameter> List<T> calRank(List<T> list) {		
		
		for (int i = list.size() - 1; i >= 0; i--) {	
			/*System.out.println("bump: i="+i);*/
			list.get(i).setRankId(new Long(i + 1));			
		}

		return list;
	}

	// 金额总占比%： “药品使用金额”/同期区域“药品使用金额”×100%
	public static <T extends StatisticParameter> List<T> totalSumOfAmount(List<T> list) {
		for (T t : list) {

			if (t.getAreaAmount().compareTo(BigDecimal.ZERO) != 0) {
				System.out.println("totalSumOfAmount: " + t.getAreaAmount());
				t.setAmountPerAreaTotal(Double.valueOf(decimalFormat
						.format(t.getMamount().divide(t.getAreaAmount(), 2).multiply(ONEHANDRUD).doubleValue())));
			}
		}
		return list;
	}

	// 药占比% “药品使用金额”/“总费用（元）” ×100%
	public static <T extends StatisticParameter> List<T> calAmountPerTotal(List<T> list) {
		BigDecimal total = new BigDecimal("0");

		for (T t : list) {
			total = total.add(t.getMamount());
		}

		for (T t : list) {
			if (total.compareTo(BigDecimal.ZERO) != 0)
				/*
				 * t.setAmountPerTotal(t.getMamount().divide(total,2,
				 * RoundingMode.HALF_UP).setScale(2).doubleValue()*100);
				 */
				t.setAmountPerTotal(Double.valueOf(
						decimalFormat.format(t.getMamount().divide(total, 2).multiply(ONEHANDRUD).doubleValue())));

		}

		return list;
	}

	// 人均药品使用金额 : 就诊用药“药品使用金额”/同期使用该药“就诊人次” Q:本医院的就诊人次
	public static <T extends StatisticParameter> List<T> amountPerPt(List<T> list) {

		for (T t : list) {
			if (t.getRegAmount() != 0)
				/*
				 * t.setAmountAvgPt(t.getMamount().divide(new
				 * BigDecimal(t.getRegAmount()), 2).doubleValue());
				 */
				t.setAmountAvgPt(Double.valueOf(decimalFormat1.format(t.getMamount()
						.divide(new BigDecimal(t.getRegAmount()), 2,BigDecimal.ROUND_HALF_UP).doubleValue())));
		}

		return list;
	}

	// 人均药品品种数:就诊用药“处方品种数”合计/同期使用该药“就诊人次”  drugSpecAmount:已经对应于对drug_variety_account的计算
	public static <T extends StatisticParameter> List<T> drugSpecAvgPt(List<T> list) {

		for (T t : list) {
			if (t.getRegAmount() != 0 && t.getDrugSpecAmount()!=null )
				t.setDrugSpecAvgPt(Double.valueOf(decimalFormat1.format(new Double(t.getDrugSpecAmount()) / new Double(t.getRegAmount()))));
		}
		return list;
	}

	// 大处方百分率%:高于某金额的“处方数”/同期“处方数”×100%
	public static <T extends StatisticParameter> List<T> bidRxPer(List<T> list) {

		for (T t : list) {
			if (t.getRxAmount()!= 0 && t.getBigRx() !=null){
				t.setPerbigRx( Double.valueOf(decimalFormat1.format( (new Double(t.getBigRx()) / new Double(t.getRxAmount()))*100    )));
/*System.err.println("bidRxPer:"+decimalFormat1.format( (new Double(t.getBigRx()) / new Double(t.getRxAmount()))*100    ));*/
			}
				
		}
		return list;
	}

	// 平均处方金额（元）：就诊用药“药品使用金额”/同期使用该药“处方数”
	public static <T extends StatisticParameter> List<T> amountAvgRx(List<T> list) {

		for (T t : list) {
			if (t.getRxAmount() != null && t.getMamount()!=null && t.getRxAmount() != 0 ) {				
				t.setAmountAvgRx(Double.valueOf(decimalFormat1.format( t.getMamount().divide(new BigDecimal(t.getRxAmount()), 2).doubleValue())));
			}
		}
		return list;
	}

	// 人均用药天数
	public static <T extends StatisticParameter> List<T> drugDayAvgPt(List<T> list) {

		for (T t : list) {
			if (t.getRegAmount() != 0 && t.getDrugDayPerHp()!=null)
				t.setDrugDayAvgPt(Double.valueOf(decimalFormat1.format(new Double(t.getDrugDayPerHp()) / new Double(t.getRegAmount()))));
		}
		return list;
	}

	// 药品周转率%:期末药品库存金额/本期药品销售金额×100%
	// 药品库存金额 药品的销售金额？

	// 合理处方占比%
	public static <T extends StatisticParameter> List<T> okPerRXAmount(List<T> list) {
		for (T t : list) {
			if (t.getRxAmount() != 0 && t.getOkAmount()!=null) {
				double d = new Double(t.getOkAmount()) / new Double(t.getRxAmount()) * 100;
				t.setPerOkAmount(Double.valueOf(decimalFormat1.format(d)));
			}
		}
		return list;
	}

	// 不合理处方占比%
	public static <T extends StatisticParameter> List<T> nonOkPerRXAmount(List<T> list) {
		for (T t : list) {		

			if (t.getRxAmount()!=null && t.getRxAmount() != 0 && t.getNonOkamount()!=null) {
				double d =new Double(t.getNonOkamount()) / new Double(t.getRxAmount()) * 100;
				t.setPerNonOkAmount(Double.valueOf(decimalFormat1.format(d)));
			}		
		}
		return list;
	}
	

	// 抗菌药处方占比%
	public static <T extends StatisticParameter> List<T> abxPerRXAmount(List<T> list) {
		for (T t : list) {
			if (t.getRxAmount() != 0 && t.getAbxCount()!=null) {
				double d =new Double(t.getAbxCount()) / new Double(t.getRxAmount())* 100;
				t.setPerAbxAmount(Double.valueOf(decimalFormat1.format(d)));
			}
		}
		return list;
	}

	// 不合理抗菌药处方占比%
	public static <T extends StatisticParameter> List<T> nonOKAbxPerRXAmount(List<T> list) {
		for (T t : list) {
			if (t.getRxAmount() != 0 && t.getNonOkAbxAmount()!=null) {
				double d = 
						new Double(t.getNonOkAbxAmount()) / new Double(t.getRxAmount())* 100;
				t.setPerNonOkAbxAmount(Double.valueOf(decimalFormat1.format(d)));
			}
		}
		return list;
	}
}
