package com.medimpact.medeasy.service.common;

import com.medimpact.medeasy.common.DataModel;
import com.medimpact.medeasy.common.bean.common.SimpleSelectBi;
import com.medimpact.medeasy.common.exception.BizException;

import java.util.List;

/**
 * Created by matrixliu on 2017/11/30.
 */
public interface SimpleSelectService {
    List<SimpleSelectBi> getSimpleSelectData(SimpleSelectBi simpleSelectBi) throws BizException;
}
