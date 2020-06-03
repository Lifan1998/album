package com.example.album.controller;

import com.example.album.controller.req.CreateImageRequest;
import com.example.album.dao.AlbumImageDao;
import com.example.album.dao.ImageDao;
import com.example.album.domain.ImageVO;
import com.example.album.entity.AlbumImage;
import com.example.album.entity.Image;
import com.example.album.service.ImageService;
import com.example.album.service.ImageUploadService;
import com.example.album.util.Resp;
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

    @Resource
    private AlbumImageDao albumImageDao;

    @Resource
    private ImageUploadService imageUploadService;

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
    public boolean create(CreateImageRequest request) {

        List<String> base64StringList = request.getBase64StringList();

        base64StringList.stream().forEach(base64String -> {
            Image image = new Image();
            image.setStatus(1);
            image.setUserId(request.getUserId());
            Resp<String> resp = imageUploadService.upload(base64String);
            if (resp.isSuccess()) {
                image.setImageUrl(resp.getData());
                imageService.insert(image);
                AlbumImage albumImage = new AlbumImage();
                albumImage.setAlbumId(request.getAlbumId());
                albumImage.setImageId(image.getId());
                albumImageDao.insert(albumImage);
            } else {
                throw new RuntimeException("上传图片失败");
            }

        });
        return true;
    }
    
    @PostMapping("/update")
    public Image update(Image image) {
        this.imageService.update(image);
        return image;
    }
    
    @GetMapping("/delete")
    public boolean deleteById(Integer id) {
        Image image = imageService.queryById(id);
        // 放到回收站
        image.setStatus(-1);
        return imageService.update(image) != null;
    }

    @GetMapping("/reducing")
    public boolean reducing(Integer id) {
        Image image = imageService.queryById(id);
        // 移出回收站
        image.setStatus(1);
        return imageService.update(image) != null;
    }

    @GetMapping("/deleteFromDustbin")
    public boolean deleteFromDustbin(Integer id) {
        albumImageDao.deleteByImageId(id);
        return true;
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