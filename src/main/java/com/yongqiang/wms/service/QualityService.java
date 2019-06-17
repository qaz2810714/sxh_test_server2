package com.yongqiang.wms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.yongqiang.wms.mapper.QualityMapper;
import com.yongqiang.wms.mapper.WmsInInfoMapper;
import com.yongqiang.wms.model.base.BizException;
import com.yongqiang.wms.model.stock.Quality;
import com.yongqiang.wms.model.stock.QualityDto;
import com.yongqiang.wms.model.stock.WmsInInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yantao.chen on 2019-05-20
 */
@Service
public class QualityService {
    @Autowired
    QualityMapper qualityMapper;
    @Autowired
    WmsInInfoMapper wmsInInfoMapper;

    /**
     * 分页查询工艺信息
     * @param qualityDto
     * @return
     */
    public IPage<Quality> getQualitiesByPage(QualityDto qualityDto){
        return qualityMapper.selectQualityByPage(qualityDto.getPage(),qualityDto);
    }

    /**
     * 根据主键id查询工艺信息
     * @return
     */
    public Quality getQualityInfoById(Long qualityId){
        return qualityMapper.selectById(qualityId);
    }

    /**
     * 新增工艺信息
     * @return
     */
    public int addQualityInfo(Quality quality){
        List<WmsInInfo> wmsInInfoList = wmsInInfoMapper.selectList(new QueryWrapper<WmsInInfo>().eq("whs_in_code",quality.getWmsInCode()));
        if(CollectionUtils.isEmpty(wmsInInfoList)){
            throw new BizException("该入库单号没有相关的入库信息!");
        }
        List<Quality> qualities = qualityMapper.selectList(new QueryWrapper<Quality>().eq("wms_in_code", quality.getWmsInCode()));
        if(CollectionUtils.isNotEmpty(qualities)){
            throw new BizException("同一批货物只能有一份质检信息!");
        }
        return qualityMapper.insert(quality);
    }

    /**
     * 修改工艺信息
     * @return
     */
    public int updateQualityInfo(Quality quality){
        List<Quality> qualities = qualityMapper.selectList(new QueryWrapper<Quality>().eq("wms_in_code", quality.getWmsInCode()).ne("wms_in_id", quality.getWmsInId()));
        if(CollectionUtils.isNotEmpty(qualities)){
            throw new BizException("同一批货物只能有一份质检信息");
        }
        return qualityMapper.updateById(quality);
    }

    /**
     * 删除工艺信息
     * @return
     */
    public int deleteQualityInfo(Quality quality){
        return qualityMapper.deleteById(quality.getId());
    }
}
