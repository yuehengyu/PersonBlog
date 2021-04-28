package com.yhy.blog.service;

import com.yhy.blog.bean.Blog;
import com.yhy.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 博客service 业务层代码
 */
public interface BlogService {

    /**
     * 根据ID获取文章信息
     * @param id 文章ID
     * @return 返回相对应的文章
     */
    Blog getBlogById(Long id);

    /**
     * 将文章中的markdown元素转换为HTML元素
     * @param id 文章ID
     * @return 对应文章内容
     */
    Blog getBlogAndConvertMarkdown(Long id);

    /**
     * 有条件的文章列表查询
     * @param pageable 分页变量
     * @param blogQuery 文章过滤条件
     * @return 返回符合条件的所有文章
     */
    Page<Blog> getAllBlogs(Pageable pageable, BlogQuery blogQuery);


    /**
     * 查询该tagID下面所有的文章，返回文章列表
     * @param pageable 分页变量
     * @param tagId 标签ID
     * @return 返回符合条件的所有文章
     */
    Page<Blog> getAllBlogsByTagId(Pageable pageable, Long tagId);

    /**
     * 新增博客
     * @param blog 博客信息实体
     * @return 新增博客
     */
    Blog saveBlog(Blog blog);

    /**
     * 更改博客
     * @param id 所更改的博客ID
     * @param blog 更新的博客信息实体
     * @return 被更改后的博客实体
     */
    Blog updateBlog(Long id, Blog blog);

    /**
     * 无条件的查询博客列表
     * @param pageable 分页变量
     * @return 博客列表
     */
    Page<Blog> listAllBlogs(Pageable pageable);

    /**
     * 删除博客
     * @param id 博客ID
     */
    void deleteBlog(Long id);

    /**
     * 推荐文章列表显示
     * @param  size 显示几个文章
     * @return 返回最新推荐文章
     */
    List<Blog> listRecommendBlogs(Integer size);

    /**
     * 根据查询条件返回所有相关博客
     * @param query 查询条件
     * @param pageable 分页变量
     * @return 符合条件的博客列表
     */
    Page<Blog> listQueryBlog(String query, Pageable pageable);


    /**
     * 返回所有的博客数据， 并存入到map中进行归档操作
     * @return key为博客的年份， value为该年份包含的所有博客数据
     */
    Map<String, List<Blog>> archives();

    /**
     * 计算共有多少条博客
     * @return 博客总数
     */
    Long countBlog();


}
