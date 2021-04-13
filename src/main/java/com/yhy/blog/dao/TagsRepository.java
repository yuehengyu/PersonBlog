package com.yhy.blog.dao;

import com.yhy.blog.bean.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagsRepository extends JpaRepository<Tag, Long> {


    Tag findByName(String name);

    /**
     * 根据每个标签中的文章数量，返回前6个
     * @param pageable 分页变量
     * @return 前n个标签
     */
    @Query("select t from Tag t")
    List<Tag> findTopTags(Pageable pageable);
}
