package com.medimpact.medeasy.dao.security;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.medimpact.medeasy.common.bean.StatementTypebi;

@Mapper
public interface StatementTypeDao {
	//将用户具有的opLevel和statement_type表进行比较，检测条件是opLevel必须大于等于表里需要的级别
	public List<StatementTypebi> getStatementTypeByOpLevel(@Param("opLevel") int opLevel);

}
