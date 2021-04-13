package com.yhy.blog.dao;

import com.yhy.blog.bean.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Type, Long> {

    Type findByName(String name);

    /**
     * 根据每个分类中的文章数量，返回前6个
     * @param pageable
     * @return
     */
    @Query("select t from Type t")
    List<Type> findTopTypes(Pageable pageable);

}
