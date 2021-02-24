package com.yhy.blog.vo;

/**
 * 用来封装所有博客查询参数的实体类
 */
public class BlogQuery {

    private String title;//查询的标题
    private Long typeId; //分类ID
    private boolean recommend; //是否推荐

    public BlogQuery() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }
}
