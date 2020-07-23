package com.dj.ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhw 车位管理
 */
@Data
@TableName("truck_space")
public class TruckSpace {

    /*** id*/
    @TableId(type = IdType.AUTO)
    private Integer id;

    /*** 车位编号*/
    private String carNumber;

    /*** 车位价格*/
    private BigDecimal price;

    /*** 车位状态0 无车 1有车*/
    private Integer carStatus;

    /*** 车位等级0:普通车位 1:会员车位*/
    private Integer carLevel;
}
