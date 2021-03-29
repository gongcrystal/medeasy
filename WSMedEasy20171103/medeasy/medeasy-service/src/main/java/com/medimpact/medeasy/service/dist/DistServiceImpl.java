package com.medimpact.medeasy.service.dist;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.DistBi;
import com.medimpact.medeasy.common.constant.ERROCONSTANT;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.dao.dist.DistDao;

@Service
public class DistServiceImpl implements DistService{
	
	@Resource
	private DistDao dDao;
	@Override
	public List<DistBi> listDists(DistBi distBi) throws BizException {
		// TODO Auto-generated method stub	
		if(distBi.getOperateLevel()==SYSCONSTANT.CON_SYS){
			System.out.println("2: distBi.getOperateLevel()"+distBi.getOperateLevel());
			return dDao.list(null);
		}
		
		List<DistBi> list = dDao.getDistsByOpLevel(distBi);
		/*System.out.println("6:"+distBi.getOpLevel() +" "+distBi.getOperateLevel()+" "+ list.size()+" "+list.get(0).getAreaName());*/
		return list;
	}
	@Override
	public DistBi getDistByCode(String code ) throws BizException {
		// TODO Auto-generated method stub			
		return dDao.getDistByCode(code); 
	}
}
