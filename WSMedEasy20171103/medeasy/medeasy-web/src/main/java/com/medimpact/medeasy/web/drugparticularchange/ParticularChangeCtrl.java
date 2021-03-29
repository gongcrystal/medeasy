package com.medimpact.medeasy.web.drugparticularchange;

import com.medimpact.medeasy.common.bean.MenuBi;
import com.medimpact.medeasy.common.bean.bp.DrugUsedBasicInfoBI;
import com.medimpact.medeasy.common.bean.particularchange.ParticularChangeBi;
import com.medimpact.medeasy.common.vo.ZTreeNodeVo;
import com.medimpact.medeasy.service.hospital.HospitalService;
import com.medimpact.medeasy.service.particularchange.ParticularChangeService;
import com.medimpact.medeasy.service.security.TRoleService;
import com.medimpact.medeasy.service.security.UserService;
import com.medimpact.medeasy.web.UserInfoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by matrixliu on 2017/12/2.
 */
@Controller
@RequestMapping("/bp")
public class ParticularChangeCtrl {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private ParticularChangeService particularChangeService;
    @Resource
    private UserService us;
    @Resource
    private TRoleService ts;
    @Resource
    private HospitalService hService;

    @RequestMapping(value = "/particularchange", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ParticularChangeBi> getParticularChange(ParticularChangeBi form) {
        try {
            UserInfoUtil.setUserInfo(us, ts, hService,form);
            return particularChangeService.getPaticularChangeDataAntiSt(form);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{

        }
    }

    @RequestMapping(value = "/listDrugCategoryNodes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ZTreeNodeVo> listNodes() {
        Integer parentId = 0;
        List<MenuBi> list = particularChangeService.listAllDrugOrCategory(parentId,"drugCategory");
        List<ZTreeNodeVo> nodes = new ArrayList<>();

        for (MenuBi mBi : list) {
            this.recuisiveCpy(mBi, nodes);
        }
        return nodes;
    }

    @RequestMapping(value = "/getDrugUsedBasicInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<DrugUsedBasicInfoBI> getDrugUsedBasicInfo(DrugUsedBasicInfoBI form) {
        UserInfoUtil.setUserInfo(us, ts, hService,form);
        return particularChangeService.getDrugUsedBasicInfo(form);
    }

    @RequestMapping(value = "/listDrugNodes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ZTreeNodeVo> listDrugNodes() {
        Integer parentId = 0;
        List<MenuBi> list = particularChangeService.listAllDrugOrCategory(parentId,"drug");
        List<ZTreeNodeVo> nodes = new ArrayList<>();

        for (MenuBi mBi : list) {
            this.recuisiveCpy(mBi, nodes);
        }
        return nodes;
    }


    public void recuisiveCpy(MenuBi mBi, List<ZTreeNodeVo> nodes) {

        if (mBi != null && nodes != null) {
            ZTreeNodeVo node = new ZTreeNodeVo();
            this.singleCpy(mBi, node);
            nodes.add(node);

            if (mBi.isHasSubMenu()) {
                List<MenuBi> subMenuBis = mBi.getSubMenu();
                for (MenuBi mBi2 : subMenuBis) {
                    recuisiveCpy(mBi2, nodes);
                }
            }
        }
    }

    public void singleCpy(MenuBi mBi, ZTreeNodeVo node) {
        if (mBi != null && node != null) {
            node.setId(mBi.getMenuId());
            node.setName(mBi.getMenuName());
            node.setpId(mBi.getParentId());
            node.setIsParent(mBi.isHasSubMenu());
        }
    }
}
