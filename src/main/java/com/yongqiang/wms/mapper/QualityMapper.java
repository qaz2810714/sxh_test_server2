package com.yongqiang.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongqiang.wms.model.stock.Quality;
import com.yongqiang.wms.model.stock.QualityDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by yantao.chen on 2019-05-20
 */
@Component
public interface QualityMapper extends BaseMapper<Quality> {
    IPage<Quality> selectQualityByPage(Page page, @Param("record")QualityDto qualityDto);
}
