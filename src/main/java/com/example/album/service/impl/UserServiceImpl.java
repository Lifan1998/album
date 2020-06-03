package com.example.album.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.album.controller.req.WeChatLoginRequest;
import com.example.album.dao.TpuserDao;
import com.example.album.entity.Tpuser;
import com.example.album.entity.User;
import com.example.album.dao.UserDao;
import com.example.album.service.ImageUploadService;
import com.example.album.service.UserService;
import com.example.album.util.Resp;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-06-02 14:03:45
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private static String sessionKeyFormat =
            "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    @Resource
    private UserDao userDao;

    @Resource
    private TpuserDao tpuserDao;

    @Resource
    private ImageUploadService imageUploadService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }
    
    @Override
    public List<User> queryAll(User user) {
        return this.userDao.queryAll(user);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }

    @Override
    public Resp<User> tpLogin(WeChatLoginRequest weChatLoginRequest) {
        //
        String url  = getWechatAPPSessionUrl(weChatLoginRequest.getCode(), weChatLoginRequest.getAppId(), weChatLoginRequest.getAppSecret());

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response;
        String responseString;
        try {
            response = okHttpClient.newCall(request).execute();
            responseString = response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
            log.error("error {}", "e: " + e);
            return Resp.fail("fail");
        }

        WechatSessionInfo sessionInfo = JSON.parseObject(responseString, WechatSessionInfo.class);


        Tpuser tpuser = tpuserDao.queryByOpenId(sessionInfo.openid);

        User user;
        if (tpuser == null) {
            // 注册用户
            user = new User();
            user.setUsername(weChatLoginRequest.getName());
            user.setPassword("123456");
            Resp<String> resp = imageUploadService.reSave(weChatLoginRequest.getAvatar());
            user.setAvatar(resp.getData());
            user.setDesc(sessionInfo.openid);
            userDao.insert(user);
            tpuser = new Tpuser();
            tpuser.setOpenId(sessionInfo.openid);
            tpuser.setUnionId(sessionInfo.unionid);
            tpuser.setTpType(1);
            tpuser.setUserId(user.getId());
            tpuserDao.insert(tpuser);
            return Resp.success(user);
        } else {
            User user1 = userDao.queryById(tpuser.getUserId());
            if (user1 != null) {
                return Resp.success(user1);
            }
        }

        return Resp.fail(null);
    }

    private String getWechatAPPSessionUrl(String code, String appId, String appSecret) {
        return String.format(sessionKeyFormat, appId,
                appSecret, code);
    }
}