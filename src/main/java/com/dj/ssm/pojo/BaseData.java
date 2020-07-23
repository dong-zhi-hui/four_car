package com.dj.ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dj_base_data")
public class BaseData {

    /**
     * id
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * base_name
     *
     */
    private String baseName;

    /**
     * 	parent_Id
     */
    private Integer parentId;

    /**
     * is_del是否删除
     */
    private Integer isDel;
}
