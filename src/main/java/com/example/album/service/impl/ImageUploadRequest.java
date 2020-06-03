package com.example.album.service.impl;

import lombok.Data;

/**
 * @author fan.li
 * @date 2020-06-03
 * @description
 */
@Data
public class ImageUploadRequest {
    private String base64Image;
    private String filename;
    private byte[] fileByte;
    private String originUrl;
}

