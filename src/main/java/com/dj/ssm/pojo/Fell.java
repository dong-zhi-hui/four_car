package com.dj.ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("fell")
public class Fell {

    /** 主键id */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 用户id */
    private Integer userId;

    /** 免费次数 */
    private Integer fellCount;

    /** 已用次数 */
    private Integer failureCount;
}
