package com.yhy.blog.web.admin;

import com.yhy.blog.bean.Tag;
import com.yhy.blog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * 后台标签控制层
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    TagsService tagsService;


    /**
     * 查询标签列表
     * @param pageable 分页变量
     * @param model 储存错误信息
     * @return 返回到标签列表页
     */
    @GetMapping("/tags")
    public String getAllTags(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", tagsService.listTags(pageable));
        return "admin/tags";
    }

    /**
     * 新增标签
     * @param model 用来进行后台非空验证使用
     * @return 进入新增标签页面
     */
    @GetMapping("/tags/addTag")
    public String addTag(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tags_input";
    }

    /**
     * 保存新增标签
     * @param tag 新增标签的信息
     * @param bindingResult 用来存储验证的错误信息
     * @param redirectAttributes 存储验证的错误信息
     * @return 重定向到标签列表页面
     */
    @PostMapping("/saveTag")
    public String submitNewTag(@Valid Tag tag, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // 查询当前新增标签名称是否存在，如果存在则返回错误信息
        Tag t1 = tagsService.getTagByName(tag.getName());
        if (t1 != null) {
            bindingResult.rejectValue("name", "nameDuplicate", "该标签名称已存在");
        }
        // 这里也可以包含非空验证的错误信息
        if (bindingResult.hasErrors()) {
            return "admin/tags_input";
        }
        Tag t = tagsService.addTag(tag);
        if (t == null) {
            //保存失败
            redirectAttributes.addFlashAttribute("message", "添加标签失败");
        } else {
            //保存成功
            redirectAttributes.addFlashAttribute("message", "添加标签成功");
        }
        return "redirect:/admin/tags";
    }


    /**
     * 根据ID回显标签名称到更改页面 Note:更改和新增使用相同的html
     * @param id 标签ID
     * @return 进入到新增页面，但是会根据ID将查询到的标签名称回显到页面上，然后进行更改
     */
    @GetMapping("/tags/{id}/update")
    public String editCategory(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagsService.getTag(id));
        return "admin/tags_input";
    }


    /**
     * 提交更改后的标签名称
     * @param tag 更改后标签的信息
     * @param bindingResult 用来存储验证的错误信息
     * @param redirectAttributes 存储验证的错误信息
     * @return 重定向到标签列表页面
     */
    @PostMapping("/saveTag/{id}")
    public String submitUpdateCategory(@Valid Tag tag, BindingResult bindingResult, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        // 查询当前新增分类名称是否存在，如果存在则返回错误信息
        Tag t1 = tagsService.getTagByName(tag.getName());
        if (t1 != null) {
            bindingResult.rejectValue("name", "nameDuplicate", "该标签名称已存在");
        }
        // 这里也可以包含非空验证的错误信息
        if (bindingResult.hasErrors()) {
            return "admin/tags_input";
        }
        Tag t = tagsService.updateTag(id, tag);
        if (t == null) {
            //更新失败
            redirectAttributes.addFlashAttribute("message", "更新失败");
        } else {
            //更新成功
            redirectAttributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 根据ID删除标签
     * @param id 标签ID
     * @return 重定向到标签列表页面
     */
    @GetMapping("tags/{id}/delete")
    public String deleteById(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        tagsService.deleteTag(id);
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }

}
