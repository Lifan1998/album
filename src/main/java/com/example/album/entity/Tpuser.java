package com.example.album.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;

/**
 * (Tpuser)实体类
 *
 * @author makejava
 * @since 2020-06-02 13:41:54
 */
@Data
public class Tpuser implements Serializable {
    private static final long serialVersionUID = 562106154476141694L;
    
    private Integer id;
    /**
    * 用户ID
    */
    private Integer userId;
    /**
    * 第三方类型
    */
    private Integer tpType;
    
    private String openId;
    
    private String unionId;
    
    private String mobile;
    
    private String accessToken;
    
    private Date addTime;
    
    private Date updateTime;


}