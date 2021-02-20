package com.yhy.blog.dao;

import com.yhy.blog.bean.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Type, Long> {

    Type findByName(String name);

}
