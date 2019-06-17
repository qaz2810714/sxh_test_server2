package com.yongqiang.wms.model.stock;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class WmsInDetail {
    /**
     * 设置主键自增
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long mainId;

    private Integer coilLength;

    private BigDecimal netWeight;

    private BigDecimal grossWeight;

    private String batchNo;

    private Integer packNo;

    private String barCode;

    private Date productTime;

    private Long creator;

    private Date createTime;

    private Long modifier;

    private Date modifyTime;


}