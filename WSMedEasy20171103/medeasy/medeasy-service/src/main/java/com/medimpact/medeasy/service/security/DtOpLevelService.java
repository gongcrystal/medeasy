package com.medimpact.medeasy.service.security;

import java.util.List;

import com.medimpact.medeasy.common.bean.DtOpLevelBi;
import com.medimpact.medeasy.common.exception.BizException;

public interface DtOpLevelService {
	public List<DtOpLevelBi> getDtOpLevels() throws BizException;

}
