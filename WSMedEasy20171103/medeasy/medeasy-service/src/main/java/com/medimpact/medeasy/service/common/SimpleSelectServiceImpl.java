package com.medimpact.medeasy.service.common;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.common.SimpleSelectBi;
import com.medimpact.medeasy.common.constant.ERROCONSTANT;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.dao.common.SimpleSelectDao;
import com.medimpact.medeasy.dao.security.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by matrixliu on 2017/11/30.
 */
@Service
public class SimpleSelectServiceImpl implements SimpleSelectService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private SimpleSelectDao simpleSelectDao;
    @Override
	public List<SimpleSelectBi> getSimpleSelectData(SimpleSelectBi simpleSelectBi){
		int opLevel = simpleSelectBi.getOperateLevel(); // user所在角色对数据的操作权限

		switch (opLevel) {
			case SYSCONSTANT.CON_SYS:
				break;
			case SYSCONSTANT.CON_AREA:
				simpleSelectBi.setAreaCode(simpleSelectBi.getSecUser().getAreaCode());
				break;
			case SYSCONSTANT.CON_HOSPITAL:
				simpleSelectBi.setHospitalCode(simpleSelectBi.getSecUser().getHospitalCode());
				simpleSelectBi.setAreaCode(simpleSelectBi.getSecUser().getAreaCode());
				break;
			case SYSCONSTANT.CON_USER:
				if(!"".equals(simpleSelectBi.getSecUser().getHospitalCode())) {
					simpleSelectBi.setHospitalCode(simpleSelectBi.getSecUser().getHospitalCode());
				}
				if(simpleSelectBi.getSecUser().getHospitalBi()!=null&&!"".equals(simpleSelectBi.getSecUser().getHospitalBi().getAreaCode())) {
					simpleSelectBi.setAreaCode(simpleSelectBi.getSecUser().getHospitalBi().getAreaCode());
				}
				simpleSelectBi.setPhysicianCode(simpleSelectBi.getSecUser().getDoctorCode());
				break;
			default:

		}
    	String ssKey=simpleSelectBi.getSelectKey();
    	List<SimpleSelectBi> ret=null;
    	switch (ssKey){
    		case "getAreaSelect":
    			ret=simpleSelectDao.getAreaSelect(simpleSelectBi);
    			break;
    		case "getDeptSelect":
    			ret=simpleSelectDao.getDeptSelect(simpleSelectBi);
    			break;
    		case "getDoctorSelect":
    			ret= simpleSelectDao.getDoctorSelect(simpleSelectBi);
    			break;
    		case "getDurEventSelect":
    			ret= simpleSelectDao.getDurEventSelect(simpleSelectBi);
    			break;
    		case "getHospitalSelect":
    			ret= simpleSelectDao.getHospitalSelect(simpleSelectBi);
    			break;
    		case "getPatientTypeSelect":
    			ret= simpleSelectDao.getPatientTypeSelect(simpleSelectBi);
    			break;
    		case "getIrrCodeSelect":
    			ret= simpleSelectDao.getIrrCodeSelect(simpleSelectBi);
    			break;
    		case "getAlertTypeSelect":
    			ret= simpleSelectDao.getAlertTypeSelect(simpleSelectBi);
    			break;
    		case "getAbxLevelSelect":
    			ret = simpleSelectDao.getAbxLevelSelect(simpleSelectBi);
    			break;
    		default:
    			throw new BizException("simpleSelectService","不存在该数据加载项"+ssKey);
    	}
    	return ret;
    }
}
