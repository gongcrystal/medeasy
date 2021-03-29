package com.medimpact.medeasy.web.bp.rxevawroksheet;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medimpact.medeasy.common.PageData;
import com.medimpact.medeasy.common.bean.bp.RxEvaWrokSheetBi;
import com.medimpact.medeasy.service.bp.rxevawroksheet.RxEvaWrokSheetService;
import com.medimpact.medeasy.service.hospital.HospitalService;
import com.medimpact.medeasy.service.security.TRoleService;
import com.medimpact.medeasy.service.security.UserService;
import com.medimpact.medeasy.web.UserInfoUtil;

/**
 * Created by matrixliu on 2017/12/2.
 */
@Controller
@RequestMapping("/bp")
public class RxEvaWrokSheetCtrl {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Resource
	private RxEvaWrokSheetService rxEvaWrokSheetService;
	@Resource
	private UserService us;
	@Resource
	private TRoleService ts;
	@Resource
	private HospitalService hService;

	@RequestMapping(value = "/rxEvaWrokSheetData")
	@ResponseBody
	public PageData<RxEvaWrokSheetBi> getRxEvaWrokSheet(RxEvaWrokSheetBi form) {
		try {
			UserInfoUtil.setUserInfo(us, ts, hService, form);
			return rxEvaWrokSheetService.getRxEvaWrokSheetData(form);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			
		}
	}
}
