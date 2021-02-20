package com.yhy.blog.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台博客类控制器
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    /**
     * 查看所有的blogs
     * @return
     */
    @GetMapping("/blogs")
    public String blogs() {
        return "admin/blog";
    }



}
