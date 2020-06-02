package com.example.album.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (UserAlbum)实体类
 *
 * @author makejava
 * @since 2020-06-02 13:42:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAlbum implements Serializable {
    private static final long serialVersionUID = 863126458262077896L;
    
    private Integer id;
    
    private Integer userId;
    
    private Integer albumId;
    
    private Date updateTime;
    
    private Date addTime;


}