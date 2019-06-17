package com.yongqiang.wms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongqiang.wms.model.base.ReturnJson;
import com.yongqiang.wms.model.stock.TechDto;
import com.yongqiang.wms.model.stock.Technology;
import com.yongqiang.wms.service.TechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Created by yantao.chen on 2019-05-21
 */
@RestController
@RequestMapping("api/dict")
public class DicController {
    @Autowired
    private TechService techService;

    @RequestMapping(value = "/getall", method = {RequestMethod.POST})
    public ReturnJson getAll() {
        HashMap<String, Object> baseData = new HashMap<>();
        TechDto techDto = new TechDto();
        Page page = new Page();
        page.setCurrent(1);
        page.setSize(Integer.MAX_VALUE);
        techDto.setPage(page);
        IPage<Technology> techPage =  techService.getTechsByPage(techDto);
        baseData.put("techId",techPage.getRecords());
        return new ReturnJson(baseData);
    }

    @RequestMapping(value = "/getBaseData", method = {RequestMethod.POST})
    public ReturnJson getBaseData() {
        HashMap<String, Object> baseData = new HashMap<>();
        TechDto techDto = new TechDto();
        Page page = new Page();
        page.setCurrent(1);
        page.setSize(Integer.MAX_VALUE);
        techDto.setPage(page);
        IPage<Technology> techPage =  techService.getTechsByPage(techDto);
        baseData.put("techId",techPage.getRecords());
        return new ReturnJson(baseData);
    }
}
