package com.example.album.controller.req;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:     {"nickName":"51v","gender":1,"language":"zh_TW","city":"","province":"Dublin","country":"Ireland","avatarUrl":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIFO6F8hZjXDucA62Zq2u9cSDmJlITRnOPW1K1GfjKdhZcBPXPNPnjfPEEKyy8QP5XmcX4jpxm25A/132"}
 *
 * @author: daniel
 * @creed: keep it simple and stupid !
 * @Time: 2020/1/17 11:29 AM
 */
@Data
public class WeChatUserInfo implements Serializable {
    String nickName;
    int gender;
    String city;
    String province;
    String country;
    String avatarUrl;
}
