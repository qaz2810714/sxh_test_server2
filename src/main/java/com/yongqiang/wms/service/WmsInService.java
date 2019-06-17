package com.yongqiang.wms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.yongqiang.wms.common.utils.BeanUtils;
import com.yongqiang.wms.common.utils.CodeProductUtil;
import com.yongqiang.wms.mapper.WmsInDetailMapper;
import com.yongqiang.wms.mapper.WmsInInfoMapper;
import com.yongqiang.wms.model.stock.WmsInDetail;
import com.yongqiang.wms.model.stock.WmsInInfo;
import com.yongqiang.wms.model.stock.WmsInInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by yantao.chen on 2019-05-20
 */
@Service
public class WmsInService {

    @Autowired
    private WmsInInfoMapper wmsInInfoMapper;
    @Autowired
    private WmsInDetailMapper wmsInDetailMapper;
    @Autowired
    private CodeProductUtil codeProductUtil;

    public IPage<WmsInInfo> selectInfoByPage(WmsInInfoDto query) {
        return wmsInInfoMapper.selectInfoByPage(query.getPage(),query);
    }

    public List<WmsInDetail> selectDetailInfoByMainId(Long mainId){
        QueryWrapper<WmsInDetail> wrapper = new QueryWrapper<>();
        WmsInDetail queryDto = new WmsInDetail();
        queryDto.setMainId(mainId);
        wrapper.setEntity(queryDto);
        return wmsInDetailMapper.selectList(wrapper);
    }

    public WmsInInfo getInfoById(Long id){
        //TODO 填充创建人及修改人信息
        return wmsInInfoMapper.selectById(id);
    }

    /**
     * 添加入库单信息信息
     * @param createDto
     * @return
     */
    @Transactional
    public WmsInInfoDto addInfo(WmsInInfoDto createDto) {
        List<WmsInDetail> details =  createDto.getDetails();
        createDto.setCreator(1L);
        createDto.setCreateTime(new Date());
        createDto.setModifier(1L);
        createDto.setModifyTime(new Date());
        //生成单据号
        String code = codeProductUtil.getWhsInCode();
        createDto.setWhsInCode(code);
        wmsInInfoMapper.insert(createDto);
        //添加明细单
        details.forEach( item ->{
            item.setMainId(createDto.getId());
            item.setCreator(1L);
            item.setCreateTime(new Date());
            item.setModifier(1L);
            item.setModifyTime(new Date());
            wmsInDetailMapper.insert(item);
        });
        return createDto;
    }


    /**
     * 更新入库单单据信息
     * @param updateDto
     * @return
     */
    @Transactional
    public WmsInInfoDto updateInfo(WmsInInfoDto updateDto) {
        //先更新主单信息
        updateDto.setModifier(1L);
        updateDto.setModifyTime(new Date());
        wmsInInfoMapper.updateById(updateDto);

        // ----------再更新明细级数据 --------

        // ------进行分堆策略 --------
        //修改前数据和修改后数据
        List<WmsInDetail> preList = updateDto.getPreDetails();
        List<WmsInDetail> aftList = updateDto.getAftDetails();
        //新增的List为修改后数据中Id为空的数据集
        List<WmsInDetail> addList = aftList.stream().filter(a-> a.getId() == null).collect(Collectors.toList());
        //删除的List为修改前ID set比修改后ID set多出来的那部分数据
        Set<Long> aftIdSet = aftList.stream().map(WmsInDetail::getId).collect(Collectors.toSet());
        List<WmsInDetail> removeList = preList.stream().filter( a -> !aftIdSet.contains(a.getId())).collect(Collectors.toList());
        //更新的List很简单，修改后的list所有ID不为空的数据直接进行更新
        List<WmsInDetail> updateList = aftList.stream().filter( a -> a.getId() != null).collect(Collectors.toList());
        // ------分堆结束 --------

        //新增操作
        addList.forEach(item ->{
            item.setMainId(updateDto.getId());
            item.setCreator(1L);
            item.setCreateTime(new Date());
            item.setModifier(1L);
            item.setModifyTime(new Date());
            wmsInDetailMapper.insert(item);
        });

        //删除操作
        List<Long> delIdList = removeList.stream().map(WmsInDetail::getId).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(delIdList))
            wmsInDetailMapper.deleteBatchIds(delIdList);

        //更新操作
        updateList.forEach(item ->{
            item.setModifier(1L);
            item.setModifyTime(new Date());
            wmsInDetailMapper.updateById(item);
        });

        // ----------明细级数据更新完毕 --------

        //返回更新完后的最新数据
        updateDto.setDetails(updateDto.getAftDetails());
        updateDto.setPreDetails(updateDto.getDetails());
        updateDto.setAftDetails(null);

        return updateDto;
    }
}
