package com.yhy.blog.web.admin;

import com.yhy.blog.bean.Type;
import com.yhy.blog.service.CategoryService;
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
 * 文章分类控制器
 */

@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * 获取分类列表
     * @param pageable 分页变量
     * @param model 存储分类数据，前端使用model来接受信息
     * @return 分类列表页面
     */
    @GetMapping("/category")
    public String getAllCategories(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", categoryService.listType(pageable));
        return "admin/category";
    }

    /**
     * 新增分类
     * @param model 用来进行后台非空验证使用
     * @return 进入新增分类页面
     */
    @GetMapping("/category/addCategory")
    public String addCategory(Model model) {
        model.addAttribute("type", new Type());
        return "admin/category_input";
    }

    /**
     * 保存新增分类
     * @param type 新增分类的信息
     * @param bindingResult 用来存储验证的错误信息
     * @param redirectAttributes 存储验证的错误信息
     * @return 重定向到分类列表页面
     */
    @PostMapping("/saveCategory")
    public String submitNewCategory(@Valid Type type, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // 查询当前新增分类名称是否存在，如果存在则返回错误信息
        Type t1 = categoryService.getTypeByName(type.getName());
        if (t1 != null) {
            bindingResult.rejectValue("name", "nameDuplicate", "该分类名称已存在");
        }
        // 这里也可以包含非空验证的错误信息
        if (bindingResult.hasErrors()) {
            return "admin/category_input";
        }
        Type t = categoryService.addType(type);
        if (t == null) {
            //保存失败
            redirectAttributes.addFlashAttribute("message", "操作失败");
        } else {
            //保存成功
            redirectAttributes.addFlashAttribute("message", "添加成功");
        }
        return "redirect:/admin/category";
    }

    /**
     * 根据ID回显分类名称到更改页面 Note:更改和新增使用相同的html
     * @param id
     * @return 进入到新增页面，但是会根据ID将查询到的分类名称回显到页面上，然后进行更改
     */
    @GetMapping("/category/{id}/update")
    public String editCategory(@PathVariable Long id, Model model) {
        model.addAttribute("type", categoryService.getType(id));
        return "admin/category_input";
    }

    /**
     * 提交更改后的分类名称
     * @param type 更改后分类的信息
     * @param bindingResult 用来存储验证的错误信息
     * @param redirectAttributes 存储验证的错误信息
     * @return 重定向到分类列表页面
     */
    @PostMapping("/saveCategory/{id}")
    public String submitUpdateCategory(@Valid Type type, BindingResult bindingResult, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        // 查询当前新增分类名称是否存在，如果存在则返回错误信息
        Type t1 = categoryService.getTypeByName(type.getName());
        if (t1 != null) {
            bindingResult.rejectValue("name", "nameDuplicate", "该分类名称已存在");
        }
        // 这里也可以包含非空验证的错误信息
        if (bindingResult.hasErrors()) {
            return "admin/category_input";
        }
        Type t = categoryService.updateType(id, type);
        if (t == null) {
            //更新失败
            redirectAttributes.addFlashAttribute("message", "更新失败");
        } else {
            //更新成功
            redirectAttributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/category";
    }

    /**
     * 根据分类ID删除分类
     * @param id 分类ID
     * @return 重定向到分类列表页面
     */
    @GetMapping("category/{id}/delete")
    public String deleteById(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        categoryService.deleteType(id);
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/category";
    }

}
