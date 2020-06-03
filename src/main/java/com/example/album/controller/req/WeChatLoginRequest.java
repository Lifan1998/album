package com.example.album.controller.req;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信授权登录
 *
 * @author daqian
 * @date 2020/4/22 18:30
 */
@Data
public class WeChatLoginRequest implements Serializable {
    /**
     * 授权码
     */
    private String code;
    /**
     * 微信前端手机号加密数据
     */
    private String encryptedData;
    /**
     * 微信前端手机号加密数据
     */
    private String iv;

    /**
     * 第三方小程序的appId
     */
    private String appId;
    /**
     * appSecret
     */
    private String appSecret;

    private String avatar;
    private String name;

    private WeChatUserInfo info;

}
