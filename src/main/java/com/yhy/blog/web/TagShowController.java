package com.yhy.blog.web;

import com.yhy.blog.bean.Tag;
import com.yhy.blog.service.BlogService;
import com.yhy.blog.service.TagsService;
import com.yhy.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


/**
 * 标签类控制层
 */
@Controller
public class TagShowController {

    @Autowired
    private TagsService tagsService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{tagId}")
    public String tags(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                       @PathVariable Long tagId, Model model) {

        List<Tag> tags = tagsService.listTopType(10000);
        if (tagId == -1) {
            tagId = tags.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(tagId);
        model.addAttribute("tags", tags);
        model.addAttribute("page", blogService.getAllBlogsByTagId(pageable, tagId));
        model.addAttribute("activeTagId", tagId);
        return "tags";
    }
}
