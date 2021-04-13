package com.yhy.blog.service.impl;

import com.yhy.blog.bean.Type;
import com.yhy.blog.dao.CategoryRepository;
import com.yhy.blog.handler.NoResourceFoundException;
import com.yhy.blog.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Type addType(Type type) {
        return categoryRepository.save(type);
    }

    @Override
    @Transactional
    public Type getType(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Page<Type> listType(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Type updateType(Long id, Type type) {
        Type t = categoryRepository.findById(id).get();
        if (t == null) {
            throw new NoResourceFoundException("不存在该分类");
        }
        BeanUtils.copyProperties(type, t);
        return categoryRepository.save(t);
    }

    @Override
    @Transactional
    public void deleteType(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Type> listTopType(Integer size) {
        Pageable pageable = PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "blogs.size"));
        return categoryRepository.findTopTypes(pageable);
    }
}
