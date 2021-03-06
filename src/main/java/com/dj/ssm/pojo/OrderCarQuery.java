package com.dj.ssm.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhw
 */
@Data
public class OrderCarQuery implements Serializable {

    private String userName;

    private Integer pageNo;

    private Integer pageSize = 2;
}
