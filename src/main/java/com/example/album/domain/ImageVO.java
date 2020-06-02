package com.example.album.domain;

import com.example.album.entity.Image;
import lombok.Data;

/**
 * @author fan.li
 * @date 2020-06-02
 * @description
 */
@Data
public class ImageVO {
    Integer id;
    String coverImg;

    public static ImageVO toVO(Image image) {
        ImageVO imageVO = new ImageVO();
        imageVO.setCoverImg(image.getImageUrl());
        imageVO.setId(image.getId());
        return imageVO;
    }
}

