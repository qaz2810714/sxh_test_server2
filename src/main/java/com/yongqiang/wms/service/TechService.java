package com.yongqiang.wms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.yongqiang.wms.mapper.TechnologyMapper;
import com.yongqiang.wms.model.base.BizException;
import com.yongqiang.wms.model.stock.TechDto;
import com.yongqiang.wms.model.stock.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yantao.chen on 2019-05-20
 */
@Service
public class TechService {
    @Autowired
    private TechnologyMapper technologyMapper;

    /**
     * 分页查询工艺信息
     * @param techDto
     * @return
     */
    public IPage<Technology> getTechsByPage(TechDto techDto){
        return technologyMapper.selectTechByPage(techDto.getPage(),techDto);
    }

    /**
     * 根据主键id查询工艺信息
     * @return
     */
    public Technology getTechInfoById(Long techId){
        return technologyMapper.selectById(techId);
    }

    /**
     * 新增工艺信息
     * @return
     */
    public int addTechInfo(Technology technology){
        List<Technology> techList = technologyMapper.selectList(new QueryWrapper<Technology>().eq("name", technology.getName()));
        if(CollectionUtils.isNotEmpty(techList)){
            throw new BizException("工艺名不能重复");
        }
        return technologyMapper.insert(technology);
    }

    /**
     * 修改工艺信息
     * @return
     */
    public int updateTechInfo(Technology technology){
        List<Technology> techList = technologyMapper.selectList(new QueryWrapper<Technology>().eq("name", technology.getName()).ne("id",technology.getId()));
        if(CollectionUtils.isNotEmpty(techList)){
            throw new BizException("工艺名不能重复");
        }
        return technologyMapper.updateById(technology);
    }

    /**
     * 删除工艺信息
     * @return
     */
    public int deleteTechInfo(Technology technology){
        return technologyMapper.deleteById(technology.getId());
    }
}
