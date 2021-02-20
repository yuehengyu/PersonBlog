package com.yhy.blog.service.impl;

import com.yhy.blog.bean.User;
import com.yhy.blog.dao.UserRepository;
import com.yhy.blog.service.UserService;
import com.yhy.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务层接口实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
    }
}
