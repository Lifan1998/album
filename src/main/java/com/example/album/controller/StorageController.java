package com.example.album.controller;

import com.example.album.domain.UserStorageInfo;
import com.example.album.entity.Image;
import com.example.album.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author fan.li
 * @date 2020-06-02
 * @description
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/info")
    public UserStorageInfo info(Integer userId) {
        UserStorageInfo info = new UserStorageInfo();
        Image query = new Image();
        query.setUserId(userId);
        List<Image> imageList = imageService.queryAll(query);
        info.setSpaceAll(256);
        info.setUserSpace((int) (imageList.size() * 2.5));
        return info;
    }
}

