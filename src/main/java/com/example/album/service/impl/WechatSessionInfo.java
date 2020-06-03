package com.example.album.service.impl;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * 调用接口wx.getUserInfo，从解密数据中获取UnionID。注意本接口需要用户授权，请开发者妥善处理用户拒绝授权后的情况。

 如果开发者帐号下存在同主体的公众号，并且该用户已经关注了该公众号。开发者可以直接通过wx.login获取到该用户UnionID，无须用户再次授权。

 如果开发者帐号下存在同主体的公众号或移动应用，并且该用户已经授权登录过该公众号或移动应用。开发者也可以直接通过wx.login获取到该用户UnionID，无须用户再次授权。
 */
@Data
public class WechatSessionInfo implements Serializable {
    private static final long serialVersionUID = -1L;
    String openid;
    /**
     * 在满足UnionID下发条件的情况下，返回参数,比如
     */
    String unionid;
    String session_key;
}
