package com.medimpact.medeasy.service.bp.abxcomprehensiveindex;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.bean.bp.AbxComprehensiveIndexBi;
import com.medimpact.medeasy.dao.bp.abxcomprehensiveindex.AbxComprehensiveIndexDao;

/**
 * Created by matrixliu on 2017/12/2.
 */
@Service
public class AbxComprehensiveIndexServiceImpl implements AbxComprehensiveIndexService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private AbxComprehensiveIndexDao abxComprehensiveIndexDao;
    @Override
    public AbxComprehensiveIndexBi getAbxComprehensiveIndexData(AbxComprehensiveIndexBi abxComprehensiveIndexBi) throws Exception {
    	return abxComprehensiveIndexDao.getAbxComprehensiveIndex(abxComprehensiveIndexBi);
    }
}
