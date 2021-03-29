package com.medimpact.medeasy.dao.herbaluse;

import java.util.List;

import com.medimpact.medeasy.common.bean.HerbalUseBi;

public interface HerbalUseDao {
	public List<HerbalUseBi> search(HerbalUseBi hbi);
}
