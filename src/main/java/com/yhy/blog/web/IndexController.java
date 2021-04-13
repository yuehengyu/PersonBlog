package com.yhy.blog.web;

import com.yhy.blog.service.BlogService;
import com.yhy.blog.service.CategoryService;
import com.yhy.blog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagsService tagsService;

    /**
     * 前端显示index页面分页查询所有文章并列出
     * @param pageable 分页变量
     * @param model 存储返回的对象信息
     * @return 返回前台index首页
     */
    @GetMapping("/")
    public String index(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", blogService.listAllBlogs(pageable));
        model.addAttribute("topTypes", categoryService.listTopType(6));
        model.addAttribute("topTags", tagsService.listTopType(10));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogs(8));
        return "index";
    }

    /**
     * 根据前端输入的内容查询博客列表
     * @param pageable 分页变量
     * @param query 查询条件
     * @param model 存储返回的对象信息
     * @return 返回结果到新的搜索页面
     */
    @PostMapping("/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String query, Model model) {
        model.addAttribute("page", blogService.listQueryBlog("%" + query + "%", pageable));
        model.addAttribute("query", query);
        return "search";
    }

    /**
     * 点击文章标题或者图片跳转到对应文章
     * @param id 文章ID
     * @param model 存储返回的对象信息
     * @return 跳转到对应文章内容页面
     */
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getBlogAndConvertMarkdown(id));
        return "blog";
    }
}
