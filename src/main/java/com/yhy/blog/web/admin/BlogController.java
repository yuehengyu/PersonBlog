package com.yhy.blog.web.admin;

import com.yhy.blog.bean.Blog;
import com.yhy.blog.bean.User;
import com.yhy.blog.service.BlogService;
import com.yhy.blog.service.CategoryService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * 后台博客类控制器
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagsService tagsService;

    /**
     *  获取博客列表
     * @param pageable 分页变量
     * @param blogQuery 博客实体，包含了前端传入的查询条件
     * @param model 设置页面上用来回显的属性
     * @return 返回到博客列表页面
     */
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blogQuery, Model model) {
        model.addAttribute("types", categoryService.listType());
        model.addAttribute("page", blogService.getAllBlogs(pageable, blogQuery));
        return "admin/blog";
    }

    /**
     *
     * 根据条件查询博客列表，只刷新博客列表区域而不刷新整个页面
     * @param pageable 分页变量
     * @param blogQuery 博客实体，包含了前端传入的查询条件
     * @param model 设置页面上用来回显的属性
     * @return 返回到博客列表页面
     */
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blogQuery, Model model) {
        model.addAttribute("page", blogService.getAllBlogs(pageable, blogQuery));
        //返回admin下的blog页面中的blogList片段
        return "admin/blog :: blogList";
    }


    /**
     * 显示博客新增页面
     * @param model 存储了一个新的Blog对象，和修改共有同一个方法时进行区分
     * @return 返回博客新增页面
     */
    @GetMapping("/blogs/createBlog")
    public String createBlog(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("types", categoryService.listType());
        model.addAttribute("tags", tagsService.listTags());
        return "admin/blog_release";
    }

    /**
     * 提交保存新增的博客文章,修改时也用这个方法，根据blog对象是否包含ID判断是新增还是修改
     * @param blog 博客内容实体对象
     * @param redirectAttributes 封装错误信息的对象
     * @param session session对象，用户相关
     * @return 重定向到博客列表页面
     */
    @PostMapping("/blogs/blogSubmit")
    public String submitBlog(Blog blog, RedirectAttributes redirectAttributes, HttpSession session) {
        //获取当前user对象并设置到blog实体中
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(categoryService.getType(blog.getType().getId()));
        blog.setTags(tagsService.listTag(blog.getTagIds()));
        if ("".equals(blog.getFlag()) || blog.getFlag() == null) {
            blog.setFlag("原创");
        }
        Blog b;
        if (blog.getId() > 0) {
            b = blogService.updateBlog(blog.getId(), blog);
        } else {
            b = blogService.saveBlog(blog);
        }
        if (b == null) {
            //新增失败
            redirectAttributes.addFlashAttribute("message", "新增/修改 失败");
        } else {
            //新增成功
            redirectAttributes.addFlashAttribute("message", "新增/修改 成功");
        }
        return "redirect:/admin/blogs";
    }

    /**
     * 打开修改页面，并回显博客相关数据
     * @param model 存储了一个新的Blog对象，和修改共有同一个方法时进行区分
     * @return 返回博客新增页面
     */
    @GetMapping("/blogs/{id}/updateBlog")
    public String updateBlog(@PathVariable Long id, Model model) {
        Blog blog = blogService.getBlogById(id);
        blog.initTagIds();//将tagIDS数组转换为String
        model.addAttribute("blog", blog);
        model.addAttribute("types", categoryService.listType());
        model.addAttribute("tags", tagsService.listTags());
        return "admin/blog_release";
    }

    /**
     * 根据ID删除文章
     * @param id
     * @return
     */
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        blogService.deleteBlog(id);
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }


}
