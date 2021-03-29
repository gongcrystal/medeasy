package com.medimpact.medeasy.service.irritate;

import java.util.List;

import com.medimpact.medeasy.common.bean.IrritationItemBi;
import com.medimpact.medeasy.common.exception.BizException;

public interface IrritateItemService {
	List<IrritationItemBi> list() throws BizException;
}
