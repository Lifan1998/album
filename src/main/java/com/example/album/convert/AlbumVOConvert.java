package com.example.album.convert;

import com.example.album.dao.AlbumDao;
import com.example.album.dao.AlbumImageDao;
import com.example.album.dao.ImageDao;
import com.example.album.dao.UserAlbumDao;
import com.example.album.domain.AlbumUpdateInfo;
import com.example.album.domain.AlbumVO;
import com.example.album.domain.ImageVO;
import com.example.album.entity.Album;
import com.example.album.entity.AlbumImage;
import com.example.album.entity.Image;
import com.example.album.entity.UserAlbum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fan.li
 * @date 2020-06-02
 * @description
 */
@Component
public class AlbumVOConvert {
    @Resource
    private AlbumDao albumDao;

    @Resource
    private UserAlbumDao userAlbumDao;

    @Resource
    private AlbumImageDao albumImageDao;

    @Resource
    private ImageDao imageDao;

    public AlbumVO toVO(Album album, Integer userId) {
        AlbumVO albumVO = new AlbumVO();
        albumVO.setAlbumDesc(album.getName());
        albumVO.setAlbumPwd(album.getPassword());

        List<AlbumImage> albumImages = albumImageDao.queryAll(AlbumImage.builder().albumId(album.getId()).build());
        List<Image> imageList = albumImages.stream()
                .map(albumImage -> imageDao.queryById(albumImage.getImageId()))
                .filter(image -> image.getStatus() == 1)
                .collect(Collectors.toList());
        List<ImageVO> imageVOS = imageList.stream()
                .map(image -> ImageVO.toVO(image))
                .collect(Collectors.toList());
        albumVO.setImageList(imageVOS);
        albumVO.setAlbumCount(imageVOS.size());

        if (imageList.isEmpty()) {
            albumVO.setCoverImg("http://qiniu.lifan.org.cn/2002020235345533image%401.5x.png");
        } else {
            // TODO
            Image image = imageList.stream().sorted(new Comparator<Image>() {
                @Override
                public int compare(Image o1, Image o2) {
                    if (o1.getUpdateTime().before(o2.getUpdateTime())) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }).findFirst().get();
            albumVO.setCoverImg(image.getImageUrl());
        }
        albumVO.setId(album.getId());
        albumVO.setIsSelf(album.getUserId().equals(userId));
        albumVO.setPermiss(album.getType() == 1);
        albumVO.setCategory(album.getCategory());
        albumVO.setName(album.getName());
        List<UserAlbum> userAlbums = userAlbumDao.queryAll(UserAlbum.builder().albumId(album.getId()).build());
        albumVO.setUserNumber(userAlbums.size());

        return albumVO;
    }
}

