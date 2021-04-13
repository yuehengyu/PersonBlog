package com.yhy.blog.service;

import com.yhy.blog.bean.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 后台分类业务层接口
 */
public interface CategoryService {

    /**
     * 新增分类
     * @param type 包含了要添加的分类内容的实体对象
     * @return 添加后的实体对象
     */
    Type addType(Type type);

    /**
     * 根据ID获得分类
     * @param id 分类ID
     * @return 查询到的分类对象
     */
    Type getType(Long id);

    /**
     * 分类的分页查询
     * @param pageable 分页变量
     * @return 包含了数据的分页对象
     */
    Page<Type> listType(Pageable pageable);

    /**
     * 获取所有分类，在博客列表页初始化时使用
     * @return 所有的分类
     */
    List<Type> listType();

    /**
     * 修改分类
     * @param id 分类ID
     * @param type 包含了要修改的分类内容
     * @return 修改够的分类实体
     */
    Type updateType(Long id, Type type);

    /**
     * 删除分类
     * @param id 分类ID
     */
    void deleteType(Long id);

    /**
     * 通过名称获取分类
     * @param name 分类名称
     * @return 获取到的type对象
     */
    Type getTypeByName(String name);

    /**
     * 返回前n个文章分类
     * @param size n的大小
     * @return 前n个文章分类名称
     */
    List<Type> listTopType(Integer size);

}
