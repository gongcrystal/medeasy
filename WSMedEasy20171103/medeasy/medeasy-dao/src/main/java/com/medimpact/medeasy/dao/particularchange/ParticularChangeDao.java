package com.medimpact.medeasy.dao.particularchange;

import com.medimpact.medeasy.common.bean.MenuBi;
import com.medimpact.medeasy.common.bean.bp.DrugUsedBasicInfoBI;
import com.medimpact.medeasy.common.bean.common.SimpleSelectBi;
import com.medimpact.medeasy.common.bean.particularchange.ParticularChangeBi;
import com.medimpact.medeasy.dao.CommonDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by matrixliu on 2017/12/2.
 */
public interface ParticularChangeDao extends CommonDao<ParticularChangeBi> {
    List<ParticularChangeBi> getParticularChange(ParticularChangeBi particularChangeBi);

    public List<MenuBi> listSubMenuByParentId(@Param("parentCode") Integer id);

    List<MenuBi> listAllDrugForTree();

    public List<DrugUsedBasicInfoBI> getDrugUsedBasicInfo(DrugUsedBasicInfoBI drugUsedBasicInfoBI);
}
