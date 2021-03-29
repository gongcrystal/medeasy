package com.medimpact.medeasy.dao.common;

import com.medimpact.medeasy.common.bean.common.SimpleSelectBi;
import com.medimpact.medeasy.dao.CommonDao;

import java.util.List;

/**
 * Created by matrixliu on 2017/11/30.
 */
public interface SimpleSelectDao extends CommonDao<SimpleSelectBi> {
    public List<SimpleSelectBi> getAreaSelect(SimpleSelectBi simpleSelect);
    public List<SimpleSelectBi> getHospitalSelect(SimpleSelectBi simpleSelect);
    public List<SimpleSelectBi> getDeptSelect(SimpleSelectBi simpleSelect);
    public List<SimpleSelectBi> getDoctorSelect(SimpleSelectBi simpleSelect);
    public List<SimpleSelectBi> getDurEventSelect(SimpleSelectBi simpleSelect);
    public List<SimpleSelectBi> getPatientTypeSelect(SimpleSelectBi simpleSelect);
    public List<SimpleSelectBi> getIrrCodeSelect(SimpleSelectBi simpleSelect);

    public List<String> getDrugIdsFromDrugCatetory(String[] cateIds);
    public List<String> getDrugIdsFromBasicDrugCatetory(String drugCate);
	public List<SimpleSelectBi> getAlertTypeSelect(SimpleSelectBi simpleSelectBi);
	public List<SimpleSelectBi> getAbxLevelSelect(SimpleSelectBi simpleSelectBi);
}
