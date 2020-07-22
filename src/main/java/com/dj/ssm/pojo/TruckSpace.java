package com.dj.ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("truck_space")
public class TruckSpace {

    /*** id*/
    @TableId(type = IdType.AUTO)
    private Integer id;

    /*** 车位编号*/
    private String carNumber;

    /*** 车位编号*/
    private BigDecimal price;

    /*** 车位编号*/
    private Integer carStatus;

    /*** 车位编号*/
    private Integer carLevel;
}
