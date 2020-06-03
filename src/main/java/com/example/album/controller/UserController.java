package com.example.album.controller;

import com.alibaba.fastjson.JSON;
import com.example.album.controller.req.WeChatLoginRequest;
import com.example.album.controller.req.WeChatUserInfo;
import com.example.album.dao.UserDao;
import com.example.album.entity.User;
import com.example.album.service.UserService;
import com.example.album.util.Resp;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-06-02 14:03:45
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @Resource
    private UserDao userDao;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get")
    public User get(Integer id) {
        return this.userService.queryById(id);
    }
    
    @PostMapping("/create")
    public User create(User user) {
        this.userService.insert(user);
        return user;
    }
    
    @PostMapping("/update")
    public User update(User user) {
        this.userService.update(user);
        return user;
    }
    
    @GetMapping("/delete")
    public boolean deleteById(Integer id) {
        return userService.deleteById(id);
    }
    
    @GetMapping("/queryAll")
    public List<User> queryAll(User user) {
        return this.userService.queryAll(user);
    }
    

    @GetMapping("/getRecentUpdateUserList")
    public List<User> getRecentUpdateUserList(Integer userId) {
        return userDao.getRecentUploadUserList(userId);
    }

    @PostMapping("/wechatLogin")
    public Resp<User> wechatLogin(@RequestBody WeChatLoginRequest weChatLoginRequest) {
        System.out.println(weChatLoginRequest);
        weChatLoginRequest.setAppId("wx02ffc0b1acdf4535");
        weChatLoginRequest.setAppSecret("63cc5480462ae1446d7da3937496fca6");
        return userService.tpLogin(weChatLoginRequest);
    }
    

}