package com.example.album.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-06-02 14:03:42
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -66996745575049237L;
    
    private Integer id;
    /**
    * 用户名
    */
    private String username;
    
    private String password;
    /**
    * 昵称
    */
    private String nickname;
    /**
    * 手机号
    */
    private String moblie;
    /**
    * 邮箱
    */
    private String email;
    
    private Date addTime;
    
    private Date updateTime;
    /**
    * 头像
    */
    private String avatar;
    /**
    * 身份证号
    */
    private String idcard;
    
    private String desc;


}