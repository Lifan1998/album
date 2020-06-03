package com.example.album.service;

import com.example.album.util.Resp;

/**
 * @author fan.li
 * @date 2020-06-03
 * @description
 */

public interface ImageUploadService {
    Resp<String> upload(String base64String);
    Resp<String> reSave(String imageUrl);
}

