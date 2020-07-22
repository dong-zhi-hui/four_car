package com.dj.ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zhw
 */
@Data
@TableName("order_car")
public class Order {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 订单号 */
    private String orderNumber;

    /** 用户名 */
    private String userName;

    /** 手机号 */
    private String phone;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /** 车牌号 */
    private String plateNumber;

    /** 车位编号 */
    private String carNumber;

    /**  支付金额*/
    private BigDecimal price;

    /**  支付状态 0:待支付 1已支付*/
    private Integer orderStatus;

}
