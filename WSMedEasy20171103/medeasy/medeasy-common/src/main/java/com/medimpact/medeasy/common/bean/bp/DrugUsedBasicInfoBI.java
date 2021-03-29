package com.medimpact.medeasy.common.bean.bp;

import com.medimpact.medeasy.common.RequestParameter;

/**
 * Created by matrixliu on 2017/12/25.
 */
public class DrugUsedBasicInfoBI extends RequestParameter {
    private String areaKey;
    private String hospitalKey;
    private String startDate;
    private String endDate;

    private String rxNum;
    private String regNum;
    private String avgDrugVariety;
    private String avgRxPriceAmount;
    private String drugPriceAmount;
    private String avgDrugPriceAmount;

    public String getAreaKey() {
        return areaKey;
    }

    public void setAreaKey(String areaKey) {
        this.areaKey = areaKey;
    }

    public String getHospitalKey() {
        return hospitalKey;
    }

    public void setHospitalKey(String hospitalKey) {
        this.hospitalKey = hospitalKey;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRxNum() {
        return rxNum;
    }

    public void setRxNum(String rxNum) {
        this.rxNum = rxNum;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public String getAvgDrugVariety() {
        return avgDrugVariety;
    }

    public void setAvgDrugVariety(String avgDrugVariety) {
        this.avgDrugVariety = avgDrugVariety;
    }

    public String getAvgRxPriceAmount() {
        return avgRxPriceAmount;
    }

    public void setAvgRxPriceAmount(String avgRxPriceAmount) {
        this.avgRxPriceAmount = avgRxPriceAmount;
    }

    public String getDrugPriceAmount() {
        return drugPriceAmount;
    }

    public void setDrugPriceAmount(String drugPriceAmount) {
        this.drugPriceAmount = drugPriceAmount;
    }

    public String getAvgDrugPriceAmount() {
        return avgDrugPriceAmount;
    }

    public void setAvgDrugPriceAmount(String avgDrugPriceAmount) {
        this.avgDrugPriceAmount = avgDrugPriceAmount;
    }
}
