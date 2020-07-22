package com.dj.ssm.pojo;

import lombok.Data;

@Data
public class UserQuery {

    private Integer pageNo = 1;

    private Integer pageNoSize = 2;
}
