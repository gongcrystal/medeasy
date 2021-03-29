package com.medimpact.medeasy.service.log;


import java.util.List;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.LogBi;
import com.medimpact.medeasy.common.form.LogForm;

public interface LogService {
	public void save(LogBi logBi);
	public DataModel<List<LogBi>>  list(LogForm form);
}
