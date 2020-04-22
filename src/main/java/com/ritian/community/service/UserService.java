package com.ritian.community.service;

import com.ritian.community.mapper.UserMapper;
import com.ritian.community.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: wangth_oup
 * @date: 2020-04-22 16:23
 * @description: 修复登录的bug，每次都生成一条记录 --> 更新已有的记录
 **/
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if(dbUser == null){
            //新增
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        } else {
            //更新
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            userMapper.update(dbUser);
        }
    }
}
