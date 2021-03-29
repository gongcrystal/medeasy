package com.medimpact.medeasy.service.particularchange;

import com.medimpact.medeasy.common.bean.MenuBi;
import com.medimpact.medeasy.common.bean.bp.DrugUsedBasicInfoBI;
import com.medimpact.medeasy.common.bean.particularchange.ParticularChangeBi;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.dao.common.SimpleSelectDao;
import com.medimpact.medeasy.dao.particularchange.ParticularChangeDao;
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
public class ParticularChangeServiceImpl implements ParticularChangeService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private ParticularChangeDao particularChangeDao;

    @Override
    public List<ParticularChangeBi> getPaticularChangeDataAntiSt(ParticularChangeBi particularChangeBi) throws Exception {
        int opLevel = particularChangeBi.getOperateLevel(); // user所在角色对数据的操作权限

        switch (opLevel) {
            case SYSCONSTANT.CON_AREA:
                particularChangeBi.setAreaKey(particularChangeBi.getSecUser().getAreaCode());
                break;
            case SYSCONSTANT.CON_HOSPITAL:
                particularChangeBi.setHospitalKey(particularChangeBi.getSecUser().getHospitalCode());
                particularChangeBi.setAreaKey(particularChangeBi.getSecUser().getHospitalBi().getAreaCode());
                break;
            case SYSCONSTANT.CON_USER:
                if(!"".equals(particularChangeBi.getSecUser().getHospitalCode())) {
                    particularChangeBi.setHospitalKey(particularChangeBi.getSecUser().getHospitalCode());
                }
                if(particularChangeBi.getSecUser().getHospitalBi()!=null&&!"".equals(particularChangeBi.getSecUser().getHospitalBi().getAreaCode())) {
                    particularChangeBi.setAreaKey(particularChangeBi.getSecUser().getHospitalBi().getAreaCode());
                }
                particularChangeBi.setPhysicianKey(particularChangeBi.getSecUser().getDoctorCode());
                break;
            case SYSCONSTANT.CON_SYS:
            default:
                break;
        }

        return particularChangeDao.getParticularChange(particularChangeBi);
    }
    private List<MenuBi> listSubDrugOrCategoryByParentId(Integer id,String type) throws BizException {
        if("drugCategory".equals(type)) {
            return particularChangeDao.listSubMenuByParentId(id);
        }else if("drug".equals(type)){
            return particularChangeDao.listAllDrugForTree();
        }else{
            return null;
        }
    }
    @Override
    public List<MenuBi> listAllDrugOrCategory(Integer menuId, String type) throws BizException {
        // TODO Auto-generated method stub
        List<MenuBi> mList = this.listSubDrugOrCategoryByParentId(menuId,type);
        for (MenuBi mBi : mList) {
            if (mBi.isHasSubMenu()) {
                List<MenuBi> subMenus = listAllDrugOrCategory(mBi.getMenuId(),type);
                mBi.setSubMenu(subMenus);
            }
        }
        return mList;
    }

    @Override
    public List<DrugUsedBasicInfoBI> getDrugUsedBasicInfo(DrugUsedBasicInfoBI drugUsedBasicInfoBI) throws BizException {
        return particularChangeDao.getDrugUsedBasicInfo(drugUsedBasicInfoBI);
    }
}
