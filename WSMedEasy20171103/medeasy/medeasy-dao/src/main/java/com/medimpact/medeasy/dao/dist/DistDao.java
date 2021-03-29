package com.medimpact.medeasy.dao.dist;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.medimpact.medeasy.common.bean.DistBi;
import com.medimpact.medeasy.dao.CommonDao;

@Mapper
public interface DistDao extends CommonDao<DistBi>{
	public List<DistBi> getDistsByOpLevel(DistBi distBi);
	public DistBi getDistByCode(@Param("code") String code);
}
