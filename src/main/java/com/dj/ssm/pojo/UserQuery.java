package com.dj.ssm.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserQuery implements Serializable {

    private String userName;

    private String password;

    private Integer pageNo;

    private Integer pageNoSize = 2;
}
