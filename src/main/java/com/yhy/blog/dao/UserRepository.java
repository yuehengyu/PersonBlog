package com.yhy.blog.dao;

import com.yhy.blog.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    User findByUsernameAndPassword(String username, String password);
}
