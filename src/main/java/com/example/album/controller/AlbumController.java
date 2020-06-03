package com.example.album.controller;

import com.example.album.convert.AlbumVOConvert;
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
import com.example.album.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Album)表控制层
 *
 * @author makejava
 * @since 2020-06-02 14:03:12
 */
@RestController
@RequestMapping("/album")
public class AlbumController {
    /**
     * 服务对象
     */
    @Resource
    private AlbumService albumService;

    @Resource
    private UserAlbumDao userAlbumDao;

    @Resource
    private ImageDao imageDao;

    @Resource
    private AlbumDao albumDao;

    @Resource
    private AlbumImageDao albumImageDao;

    @Autowired
    private AlbumVOConvert albumVOConvert;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get")
    public AlbumVO get(Integer id, Integer userId) {
        Album album = albumService.queryById(id);
        AlbumVO albumVO = albumVOConvert.toVO(album, userId);
        return albumVO;
    }
    
    @PostMapping("/create")
    public Album create(Album album) {
        albumService.insert(album);
        userAlbumDao.insert(UserAlbum.builder().albumId(album.getId()).userId(album.getUserId()).build());
        return album;
    }
    
    @PostMapping("/update")
    public Album update(Album album) {
        this.albumService.update(album);
        return album;
    }
    
    @GetMapping("/delete")
    public boolean deleteById(Integer id, Integer userId) {
        List<UserAlbum> userAlbums = userAlbumDao.queryAll(UserAlbum.builder().albumId(id).userId(userId).build());
        if (!userAlbums.isEmpty()) {
            UserAlbum userAlbum = userAlbums.stream().findFirst().get();
            userAlbumDao.deleteById(userAlbum.getId());
        }
        return true;
    }
    
    @GetMapping("/queryAll")
    public List<AlbumVO> queryAll(Album album) {
        // TODO userId
        return this.albumService.queryAll(album).stream()
                .map(album1 -> albumVOConvert.toVO(album1, album.getUserId())
                ).collect(Collectors.toList());

    }


    @GetMapping("/listByUserId")
    public List<AlbumVO> listByUserId(Integer userId) {
        UserAlbum userAlbum = new UserAlbum();
        userAlbum.setUserId(userId);
        List<UserAlbum> userAlbums = userAlbumDao.queryAll(userAlbum);
        return userAlbums.stream()
                .map(userAlbum1 -> albumVOConvert.toVO(albumService.queryById(userAlbum1.getAlbumId()), userId))
                .collect(Collectors.toList());
    }

    @GetMapping("/getRecentUpload")
    public List<AlbumVO> getRecentUpload(Integer userId) {
        return this.albumDao.getRecentUpdateAlbum(userId).stream()
                .distinct()
                .map(album1 -> albumVOConvert.toVO(album1, userId))
                .filter(albumVO -> albumVO.getAlbumCount() != 0)
                .filter(albumVO -> !albumVO.getPermiss())
                .collect(Collectors.toList());
    }


    
    @GetMapping("/updateInfo")
    public AlbumUpdateInfo updateInfo(Integer userId) {
        return albumDao.getRecentUpdateInfo(userId);
    }
    

}