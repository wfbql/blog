package com.jiangfx.service.Impl;

import com.jiangfx.entity.Blog;
import com.jiangfx.mapper.BlogMapper;
import com.jiangfx.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 姜飞祥
 * @Description:
 * @Date: Create in 2018/9/22 16:19
 * @param: $params$
 * @return: $returns$
 */
@Service
public class BlogServiceImpl implements BlogService {
    /**
     * 注入BlogMapper
     * @return
     */
    @Autowired
    private BlogMapper blogMapper;

    /**
     * 查询
     * @return
     */
    @Override
    public List<Blog> getAllBlog() {
        List<Blog> allBlog = blogMapper.getAllBlog();
        return allBlog;
    }

    /**
     * 保存
     * @param blog
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveBlog(Blog blog) {

        return  blogMapper.saveBlog(blog);
    }

    /**
     * 删除
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteBlog(Integer id) {

        return blogMapper.deleteBlog(id);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Blog getById(Integer id) {
        return blogMapper.getById(id);
    }
}
