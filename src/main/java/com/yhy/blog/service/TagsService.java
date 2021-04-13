package com.yhy.blog.service;

import com.yhy.blog.bean.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 后台标签业务层接口
 */
public interface TagsService {

    /**
     * 新增标签
     * @param tag 包含了要添加的标签内容的实体对象
     * @return 添加后的实体对象
     */
    Tag addTag(Tag tag);

    /**
     * 根据ID获得标签
     * @param id 标签ID
     * @return 查询到的标签对象
     */
    Tag getTag(Long id);

    /**
     * 标签的分页查询
     * @param pageable 分页变量
     * @return 包含了标签数据的分页对象
     */
    Page<Tag> listTags(Pageable pageable);

    /**
     * 查询所有标签
     * @return 包含了标签数据的分页对象
     */
    List<Tag> listTags();

    /**
     * 修改标签
     * @param id 分类ID
     * @param tag 包含了要修改的标签内容
     * @return 修改够的标签实体
     */
    Tag updateTag(Long id, Tag tag);

    /**
     * 删除标签
     * @param id 标签ID
     */
    void deleteTag(Long id);

    /**
     * 通过名称获取标签
     * @param name 标签名称
     * @return 获取到的tag对象
     */
    Tag getTagByName(String name);

    /**
     * 根据一组ID查询所有相关的标签
     * @param ids 标签集合 eg: 1,2,3,4
     * @return ID所对应的标签集合
     */
    List<Tag> listTag(String ids);

    /**
     * 根据标签内包含的博客数量 返回前n个标签
     * @param size n的大小
     * @return 前n个标签
     */
    List<Tag> listTopType(Integer size);

}
