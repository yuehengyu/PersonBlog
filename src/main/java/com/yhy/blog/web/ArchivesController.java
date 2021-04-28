package com.yhy.blog.web;

import com.yhy.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * 归档页面控制层
 */

@Controller
public class ArchivesController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String showArchives(Model model) {
        model.addAttribute("archiveMap", blogService.archives());
        model.addAttribute("countBlog", blogService.countBlog());
        return "archives";
    }
}
