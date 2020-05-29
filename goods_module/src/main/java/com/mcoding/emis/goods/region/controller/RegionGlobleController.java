package com.mcoding.emis.goods.region.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.emis.goods.region.bean.RegionGroble;
import com.mcoding.emis.goods.region.service.RegionGlobleService;

/**
 * 全球地区信息
 * Created by Benson on 2014-08-04  15:02
 */
@Controller
public class RegionGlobleController {
    @Autowired
    private RegionGlobleService regionGlobleService;

    //根据上一级区域Id加载下一级区域
    @RequestMapping("/region/queryRegionByParentId")
    @ResponseBody
    public List<RegionGroble> queryRegionByParentId(Integer parentRegionId){
        return regionGlobleService.queryRegionByParentId(parentRegionId);
    }

}
