package com.yongqiang.wms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongqiang.wms.common.utils.BarCodeUtil;
import com.yongqiang.wms.mapper.UserMapper;
import com.yongqiang.wms.mapper.WmsInInfoMapper;
import com.yongqiang.wms.model.base.RequestJson;
import com.yongqiang.wms.model.base.ReturnJson;
import com.yongqiang.wms.model.stock.WmsInDetail;
import com.yongqiang.wms.model.stock.WmsInInfo;
import com.yongqiang.wms.model.stock.WmsInInfoDto;
import com.yongqiang.wms.model.user.User;
import com.yongqiang.wms.service.WmsInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 *
 *
 * Created by yantao.chen on 2019-05-20
 */
@Controller
@RequestMapping("api/wmsin")
public class WmsInController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WmsInService wmsInService;

    @RequestMapping("testProject")
    @ResponseBody
    public List<User> testProject(){
        return userMapper.selectList(null);
    }

    @RequestMapping("/viewPageList")
    @ResponseBody
    public ReturnJson searchList(@RequestBody RequestJson<WmsInInfoDto> query){
        return new ReturnJson(wmsInService.selectInfoByPage(query.getData()));
    }

    /**
     *
     * @param query 通过主单ID查询detailList
     * @return
     */
    @RequestMapping(value = "/getDetailListByMainId" , method = {RequestMethod.POST})
    @ResponseBody
    public ReturnJson getDetailInfoByMainId(@RequestBody RequestJson<WmsInDetail> query){
        return new ReturnJson(wmsInService.selectDetailInfoByMainId(query.getData().getMainId()));
    }

    /**
     *
     * @param query 通过主单ID查询detailList
     * @return
     */
    @RequestMapping(value = "/getInfoById" , method = {RequestMethod.POST})
    @ResponseBody
    public ReturnJson getInfoByMId(@RequestBody RequestJson<WmsInInfo> query){
        return new ReturnJson(wmsInService.getInfoById(query.getData().getId()));
    }

    /**
     * 创建入库单
     * @param createDto 创建的Dto
     * @return
     */
    @RequestMapping(value = "/createWmsInfo" , method = {RequestMethod.POST})
    @ResponseBody
    public ReturnJson addInfo(@RequestBody RequestJson<WmsInInfoDto> createDto){
        return new ReturnJson(wmsInService.addInfo(createDto.getData()));
    }

    /**
     * 更新入库单
     * @param createDto 创建的Dto
     * @return
     */
    @RequestMapping(value = "/updateWmsInfo" , method = {RequestMethod.POST})
    @ResponseBody
    public ReturnJson updateInfo(@RequestBody RequestJson<WmsInInfoDto> createDto){
        return new ReturnJson(wmsInService.updateInfo(createDto.getData()));
    }

    /**
     *
     */
    /**
     * 更新入库单
     * @param createDto 创建的Dto
     * @return
     */
    @RequestMapping(value = "/getBarCode")
    public void getBarCode(String barCode, HttpServletResponse response) throws IOException {
        OutputStream out = response.getOutputStream();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=" +new String(("demo.jpg").getBytes(), "iso-8859-1"));
        BarCodeUtil.generateBarCode128(barCode,200D,100D,true,false,out);
    }
}
