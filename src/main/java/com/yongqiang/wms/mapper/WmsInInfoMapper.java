package com.yongqiang.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongqiang.wms.model.stock.WmsInInfo;
import com.yongqiang.wms.model.stock.WmsInInfoDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yantao.chen on 2019-05-20
 */
@Component
public interface WmsInInfoMapper extends BaseMapper<WmsInInfo> {

    IPage<WmsInInfo> selectInfoByPage(Page page, @Param("record")WmsInInfoDto query);
    WmsInInfo getLastRecordByDate(@Param("record")WmsInInfoDto query);


}
