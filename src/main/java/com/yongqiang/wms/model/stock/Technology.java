package com.yongqiang.wms.model.stock;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Technology {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer upperWindTem;

    private Integer lowerWindTem;

    private Integer boxTem;

    private Integer dissolvedTem;

    private Integer hotrollingPressure;

    private Integer hotrollingUpperTem;

    private Integer hotrollingLowerTem;

    private Integer s1UpperSpeed;

    private Integer s1LowerSpeed;

    private Integer s1ConvulsionSpeed;

    private Integer s1SingleSpeed;

    private Integer s1MeterPump;

    private Integer s1MachineSpeed;

    private Integer s2UpperSpeed;

    private Integer s2LowerSpeed;

    private Integer s2ConvulsionSpeed;

    private Integer s2SingleSpeed;

    private Integer s2MeterPunp;

    private Integer s2MachineSpeed;

    private String remark;

    private Integer valid;

    private Long creator;

    private Date createTime;

    private Long modifier;

    private Date modifyTime;
}