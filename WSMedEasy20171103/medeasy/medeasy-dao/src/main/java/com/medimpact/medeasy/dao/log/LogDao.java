package com.medimpact.medeasy.dao.log;

import java.util.List;

import com.medimpact.medeasy.common.bean.LogBi;
import com.medimpact.medeasy.common.form.LogForm;

public interface LogDao {
	public void insert(LogBi logBi);
	public List<LogBi> list(LogForm form);
	public long getCount(LogForm form);
}
