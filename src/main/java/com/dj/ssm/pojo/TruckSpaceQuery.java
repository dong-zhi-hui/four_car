package com.dj.ssm.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询类
 */
@Data
public class TruckSpaceQuery implements Serializable {

    /** 留言内容 */
    private String messageContents;

    /** 车位编号 */
    private String carNumber;

    /** 当前页 */
    private Integer pageNo;

    /** 每页条数 */
    private Integer pageSize = 2;
}
