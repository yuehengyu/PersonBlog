package com.yhy.blog.service.impl;


import com.yhy.blog.bean.Tag;
import com.yhy.blog.dao.TagsRepository;
import com.yhy.blog.handler.NoResourceFoundException;
import com.yhy.blog.service.TagsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    TagsRepository tagsRepository;

    @Override
    public Tag addTag(Tag tag) {
        return tagsRepository.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagsRepository.findById(id).get();
    }

    @Override
    public Page<Tag> listTags(Pageable pageable) {
        return tagsRepository.findAll(pageable);
    }

    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagsRepository.findById(id).get();
        if (t == null) {
            throw new NoResourceFoundException("不存在该标签");
        }
        BeanUtils.copyProperties(tag, t);
        return tagsRepository.save(t);
    }

    @Override
    public void deleteTag(Long id) {
        tagsRepository.deleteById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagsRepository.findByName(name);
    }
}
