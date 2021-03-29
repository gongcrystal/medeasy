package com.medimpact.medeasy.service.particularchange;

import com.medimpact.medeasy.common.bean.MenuBi;
import com.medimpact.medeasy.common.bean.bp.DrugUsedBasicInfoBI;
import com.medimpact.medeasy.common.bean.common.SimpleSelectBi;
import com.medimpact.medeasy.common.bean.particularchange.ParticularChangeBi;
import com.medimpact.medeasy.common.exception.BizException;

import java.util.List;

/**
 * Created by matrixliu on 2017/12/2.
 */
public interface ParticularChangeService {
    List<ParticularChangeBi> getPaticularChangeDataAntiSt(ParticularChangeBi particularChangeBi) throws Exception;
    public List<MenuBi> listAllDrugOrCategory(Integer menuId, String type)  throws BizException;
    List<DrugUsedBasicInfoBI> getDrugUsedBasicInfo(DrugUsedBasicInfoBI drugUsedBasicInfoBI) throws BizException;
}
