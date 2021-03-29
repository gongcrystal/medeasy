package com.medimpact.medeasy.dao.log;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.medimpact.medeasy.common.bean.RsBi;

@Mapper
public interface RsDao {
	//根据method name 检索 rs的记录
	public RsBi getRsByMethod(@Param("method") String method);

}
