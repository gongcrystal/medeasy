package com.medimpact.medeasy.service.log;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.RsBi;

public interface RsService {
	public DataModel<RsBi> getRsByMethod(String method);

}
