package com.yhy.blog.web;


import com.yhy.blog.bean.Blog;
import com.yhy.blog.bean.Type;
import com.yhy.blog.service.BlogService;
import com.yhy.blog.service.CategoryService;
import com.yhy.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * 前端分类页面控制层
 */

@Controller
public class CategoryShowController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/categories/{categoryId}")
    public String categories(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                             @PathVariable Long categoryId, Model model) {

        List<Type> categories = categoryService.listTopType(10000);
        if (categoryId == -1) {
            categoryId = categories.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(categoryId);
        model.addAttribute("categories", categories);
        model.addAttribute("page", blogService.getAllBlogs(pageable, blogQuery));
        model.addAttribute("activeCategoryId", categoryId);
        return "category";
    }
}
