package com.example.album.controller.req;

import lombok.Data;

import java.util.List;

/**
 * @author fan.li
 * @date 2020-06-03
 * @description
 */
@Data
public class CreateImageRequest {
    private List<String> base64StringList;
    private Integer userId;
    private Integer albumId;
}

