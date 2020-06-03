package com.example.album.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.album.service.ImageUploadService;
import com.example.album.util.Resp;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author fan.li
 * @date 2020-06-03
 * @description
 */
@Service
@Slf4j
public class ImageUploadServiceImpl implements ImageUploadService {
    public static  String url = "http://localhost:8084/";
    public static final MediaType MediaType_JSON
            = MediaType.parse("application/json; charset=utf-8");
    @Override
    public Resp<String> upload(String base64String) {

        OkHttpClient okHttpClient = new OkHttpClient();

        ImageUploadRequest imageUploadRequest = new ImageUploadRequest();
        imageUploadRequest.setBase64Image(base64String);
        RequestBody body = RequestBody.create(com.alibaba.fastjson.JSON.toJSONString(imageUploadRequest), MediaType_JSON);

        Request request = new Request.Builder()
                .url(url + "image/upload")
                .post(body)
                .build();
        Response response;
        String responseString;
        try {
            response = okHttpClient.newCall(request).execute();
            responseString = response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
            log.error("error {}", "e: " + e);
            return Resp.fail("");
        }

        JSONObject jsonObject = JSON.parseObject(responseString);
        log.info("Resp<String> resp: {} response {}", jsonObject, responseString);
        return Resp.success(jsonObject.getString("data"));
    }

    @Override
    public Resp<String> reSave(String imageUrl) {

        OkHttpClient okHttpClient = new OkHttpClient();

        ImageUploadRequest imageUploadRequest = new ImageUploadRequest();
        imageUploadRequest.setOriginUrl(imageUrl);
        RequestBody body = RequestBody.create(com.alibaba.fastjson.JSON.toJSONString(imageUploadRequest), MediaType_JSON);

        Request request = new Request.Builder()
                .url(url + "image/reSave")
                .post(body)
                .build();
        Response response;
        String responseString;
        try {
            response = okHttpClient.newCall(request).execute();
            responseString = response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
            log.error("error {}", "e: " + e);
            return Resp.fail("");
        }

        JSONObject jsonObject = JSON.parseObject(responseString);
        return Resp.success(jsonObject.getString("data"));

    }
}

