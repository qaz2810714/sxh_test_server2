package com.yongqiang.wms.model.stock;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Quality {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long wmsInId;

    private String wmsInCode;

    private Integer portraitPower;

    private Integer transversePower;

    private BigDecimal ratio;

    private Integer portraitDrafting;

    private Integer transverseDrafting;

    private BigDecimal singleSilkThin;

    private BigDecimal thickness;

    private BigDecimal even;

    private BigDecimal soft;

    private String remark;

    private Long creator;

    private Date createTime;

    private Long modifier;

    private Date modifyTime;
}