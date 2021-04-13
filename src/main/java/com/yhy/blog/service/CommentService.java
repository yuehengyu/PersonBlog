package com.yhy.blog.service;

import com.yhy.blog.bean.Comment;

import java.util.List;

/**
 * 评论类业务层接口
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blodId);

    Comment saveComment(Comment comment);
}
