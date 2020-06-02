package com.example.album.domain;

import com.example.album.dao.UserAlbumDao;
import com.example.album.entity.Album;
import com.example.album.entity.Image;
import com.example.album.entity.UserAlbum;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fan.li
 * @date 2020-06-02
 * @description
 */
@Data
@Component
public class AlbumVO {
    private Integer id;
    private String name;
    private String coverImg;
    private String albumDesc;
    private Integer albumCount;
    private Integer albumPwd;
    private Boolean isSelf;
    private Boolean permiss = false;
    private List<ImageVO> imageList = new ArrayList<>();
    private Integer category;
    private Integer userNumber;


    public static AlbumVO toVO(Album album, Integer userId, String imageUrl, Integer count) {
        AlbumVO albumVO = new AlbumVO();
        albumVO.setAlbumDesc(album.getName());
        albumVO.setAlbumPwd(album.getPassword());
        albumVO.setCoverImg(imageUrl);
        albumVO.setId(album.getId());
        albumVO.setIsSelf(album.getUserId().equals(userId));
        albumVO.setAlbumCount(count);
        albumVO.setPermiss(album.getPassword() != null && album.getPassword().toString().length() == 6);
        albumVO.setCategory(album.getCategory());
        albumVO.setName(album.getName());

        return albumVO;
    }
}

