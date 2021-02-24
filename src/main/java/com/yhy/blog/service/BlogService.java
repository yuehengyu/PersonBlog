package com.yhy.blog.service;

import com.yhy.blog.bean.Blog;
import com.yhy.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 博客service 业务层代码
 */
public interface BlogService {

    Blog getBlogById(Long id);

    Page<Blog> getAllBlogs(Pageable pageable, BlogQuery blogQuery);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);
}
