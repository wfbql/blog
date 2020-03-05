package com.jiangfx.mapper;

import com.jiangfx.entity.Blog;

import java.util.List;

/**
 * Created by jiangfeixiang on 2018/5/3
 * @author smfx1314
 */
public interface BlogMapper {

    /**
     * 查询所有blog
     * @return
     */
    List<Blog> getAllBlog();


    /**
     * 添加博客
     * @param blog
     * @return
     */
    int saveBlog(Blog blog);

    /**
     * 删除博客
     * @param id
     * @return
     */
    int deleteBlog(Integer id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Blog getById(Integer id);
}
