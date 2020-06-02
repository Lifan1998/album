package com.example.album.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;

/**
 * (Image)实体类
 *
 * @author makejava
 * @since 2020-06-02 14:03:24
 */
@Data
public class Image implements Serializable {
    private static final long serialVersionUID = -25516265865351676L;
    
    private Integer id;
    
    private String imageUrl;
    
    private Integer userId;
    
    private Date updateTime;
    
    private Date addTime;
    /**
    * 状态
    */
    private Integer status;


}