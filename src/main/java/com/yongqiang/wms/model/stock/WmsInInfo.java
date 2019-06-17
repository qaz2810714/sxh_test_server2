package com.yongqiang.wms.model.stock;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class WmsInInfo {
    /**
     * 设置主键自增
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    private String whsInCode;

    private String clientName;

    private Long techId;

    private String brand;

    private Integer gramWeight;

    private Integer clothWidth;

    private String color;

    private Integer num;

    private String classes;

    private String remark;

    private Long creator;

    private Date createTime;

    @TableField(exist = false)
    private String creatorName;

    private Long modifier;

    private Date modifyTime;

    @TableField(exist = false)
    private String modifierName;

}