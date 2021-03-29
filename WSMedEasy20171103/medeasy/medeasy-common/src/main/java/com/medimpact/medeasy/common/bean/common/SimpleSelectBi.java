package com.medimpact.medeasy.common.bean.common;

import com.medimpact.medeasy.common.RequestParameter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by matrixliu on 2017/11/30.
 */
public class SimpleSelectBi extends RequestParameter {
    private String selectKey;
    private String areaId;
    private String areaCode;
    private String hospitalCode;
    private String physicianCode;
    private String text;
    private String value;

    public String getSelectKey() {
        return selectKey;
    }

    public void setSelectKey(String selectKey) {
        this.selectKey = selectKey;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getPhysicianCode() {
        return physicianCode;
    }

    public void setPhysicianCode(String physicianCode) {
        this.physicianCode = physicianCode;
    }
}
