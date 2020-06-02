package com.example.album.controller;

import com.example.album.dao.ImageDao;
import com.example.album.domain.ImageVO;
import com.example.album.entity.Image;
import com.example.album.service.ImageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Image)表控制层
 *
 * @author makejava
 * @since 2020-06-02 14:03:30
 */
@RestController
@RequestMapping("/image")
public class ImageController {
    /**
     * 服务对象
     */
    @Resource
    private ImageService imageService;

    @Resource
    private ImageDao imageDao;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get")
    public ImageVO get(Integer id) {
        return ImageVO.toVO(imageService.queryById(id));
    }
    
    @PostMapping("/create")
    public Image create(Image image) {
        this.imageService.insert(image);
        return image;
    }
    
    @PostMapping("/update")
    public Image update(Image image) {
        this.imageService.update(image);
        return image;
    }
    
    @GetMapping("/delete")
    public boolean deleteById(Integer id) {
        return imageService.deleteById(id);
    }
    
    @GetMapping("/queryAll")
    public List<Image> queryAll(Image image) {
        return this.imageService.queryAll(image);
    }
    
    @GetMapping("/getRecentUploadImageList")
    public List<Image> getRecentUploadImageList(Integer userId) {
        return imageDao.getRecentUploadImageList(userId);
    }

    @GetMapping("/getDeletedImageList")
    public List<ImageVO> getDeletedImageList(Integer userId) {
        Image imageQueryRequest = new Image();
        imageQueryRequest.setUserId(userId);
        imageQueryRequest.setStatus(-1);
        return imageDao.queryAll(imageQueryRequest)
                .stream()
                .map(image -> ImageVO.toVO(image))
                .collect(Collectors.toList());
    }

}