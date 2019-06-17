package com.yongqiang.wms.controller;

import com.yongqiang.wms.model.base.RequestJson;
import com.yongqiang.wms.model.base.ReturnJson;
import com.yongqiang.wms.model.stock.Quality;
import com.yongqiang.wms.model.stock.QualityDto;
import com.yongqiang.wms.service.QualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yantao.chen on 2019-05-20
 */
@RestController
@RequestMapping("api/quality")
public class QualityController {
    @Autowired
    private QualityService qualityService;

    /**
     * 分页查询质量信息
     * @param qualityDto
     * @return
     */
    @RequestMapping("getQualitiesByPage")
    @ResponseBody
    public ReturnJson getQualitiesByPage(@RequestBody RequestJson<QualityDto> qualityDto){
        return new ReturnJson(qualityService.getQualitiesByPage(qualityDto.getData()));
    }

    /**
     * 根据主键id查询工艺信息
     */
    @RequestMapping("getQualityInfoById")
    @ResponseBody
    public ReturnJson getQualityInfoById(@RequestBody Quality quality){
        return new ReturnJson(qualityService.getQualityInfoById(quality.getId()));
    }

    /**
     * 新增工艺信息
     */
    @RequestMapping("addQualityInfo")
    @ResponseBody
    public ReturnJson addQualityInfo(@RequestBody RequestJson<Quality> quality){
        return new ReturnJson(qualityService.addQualityInfo(quality.getData()));
    }

    /**
     * 修改工艺信息
     */
    @RequestMapping("updateQualityInfo")
    @ResponseBody
    public ReturnJson updateQualityInfo(@RequestBody RequestJson<Quality> quality){
        return new ReturnJson(qualityService.updateQualityInfo(quality.getData()));
    }


    /**
     * 删除工艺信息
     */
    @RequestMapping("deleteQualityInfo")
    @ResponseBody
    public ReturnJson deleteQualityInfo(@RequestBody RequestJson<Quality> quality){
        return new ReturnJson(qualityService.deleteQualityInfo(quality.getData()));
    }
}
