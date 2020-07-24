package com.dj.ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("user")
public class User {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 用户名 */
    private String userName;

    /** 密码 */
    private String userPwd;

    /** 手机号 */
    private String phone;

    /** 车牌号 */
    private String plateNumber;


    /** 用户状态 0无效 1有效*/
    private Integer userStatus;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /** 等级 0:普通用户 1:普通会员 2:高级会员 3:管理员 */
    private Integer level;

    /** 验证码 */
    private String code;

    /** 有效时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finiteTime;

}
