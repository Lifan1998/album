package com.example.album.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;

/**
 * (Album)实体类
 *
 * @author makejava
 * @since 2020-06-02 14:03:07
 */
@Data
public class Album implements Serializable {
    private static final long serialVersionUID = 159887172328473349L;
    
    private Integer id;
    
    private Integer userId;
    
    private Integer password;
    
    private String name;
    
    private Date updateTime;
    
    private Date addTime;
    /**
    * 类型
    */
    private Integer category;
    /**
    * 相册类型1. 私人 2.共享
    */
    private Integer type;


}