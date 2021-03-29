package com.medimpact.medeasy.service.dist;

import java.util.List;

import com.medimpact.medeasy.common.bean.DistBi;
import com.medimpact.medeasy.common.exception.BizException;


public interface DistService {
	public  List<DistBi>   listDists(DistBi distBi) throws BizException;
	public DistBi getDistByCode(String code );

}
