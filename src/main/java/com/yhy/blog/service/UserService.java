package com.yhy.blog.service;

import com.yhy.blog.bean.User;

/**
 * 用户业务层接口
 */
public interface UserService {


    User checkUser(String username, String password);
}
