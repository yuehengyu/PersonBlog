package com.yhy.blog.dao;

import com.yhy.blog.bean.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsRepository extends JpaRepository<Tag, Long> {


    Tag findByName(String name);
}
