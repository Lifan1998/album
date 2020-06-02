package com.example.album.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (AlbumImage)实体类
 *
 * @author makejava
 * @since 2020-06-02 13:40:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumImage implements Serializable {
    private static final long serialVersionUID = -83591791889592665L;
    
    private Integer id;
    
    private Integer albumId;
    
    private Integer imageId;
    
    private Date updateTime;
    
    private Date addTime;


}