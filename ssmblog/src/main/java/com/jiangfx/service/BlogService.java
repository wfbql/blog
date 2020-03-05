package com.jiangfx.service;

import com.jiangfx.entity.Blog;

import java.util.List;

/**
 * Created by jiangfeixiang on 2018/5/3
 */
public interface BlogService {

    /**
     * 查询所有blog
     * @return
     */
    List<Blog> getAllBlog();


    /**
     * 添加blog
     * @param blog
     */
    int saveBlog(Blog blog);

    /**
     * 删除banner
     * @param id
     */
    int deleteBlog(Integer id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Blog getById(Integer id);
}
