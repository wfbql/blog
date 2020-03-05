package com.jiangfx.entity;

import java.io.Serializable;

/**
 * @Author: 姜飞祥
 * @Description:
 * @Date: Create in 2018/9/22 16:10
 * @param: $params$
 * @return: $returns$
 */
public class Blog implements Serializable {
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 简介
     */
    private String des;
    /**
     * 内容
     */
    private String context;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
