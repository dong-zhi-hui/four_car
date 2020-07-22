package com.dj.ssm.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhw
 */
@Data
public class TruckSpaceQuery implements Serializable {

    private String carNumber;

    private Integer pageNo;

    private Integer pageSize = 2;
}
