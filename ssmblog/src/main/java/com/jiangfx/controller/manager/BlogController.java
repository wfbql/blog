package com.jiangfx.controller.manager;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiangfx.common.ResponseData;
import com.jiangfx.entity.Blog;
import com.jiangfx.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @Author: 姜飞祥
 * @Description:
 * @Date: Create in 2018/9/22 16:08
 * @param: $params$
 * @return: $returns$
 */
@Controller
@RequestMapping(value = "/admin")
public class BlogController {
    /**
     * 注入BlogService
     */
    @Autowired
    private BlogService blogService;


    /**
     * 查询所有博客
     */
    @RequestMapping(value = "/getAllBlog",method = RequestMethod.GET)
    @ResponseBody
    public ResponseData getAllBlog(@RequestParam(defaultValue="1",required=true,value="pn") Integer pn){

        /**
         * 每页显示记录数
         *
         */
        Integer pageSize=5;
        /**
         * 分页查询，注意顺序，startPage()方法放前面
         */
        PageHelper.startPage(pn, pageSize);
        /**
         * 获取所用用户信息
         */
        List<Blog> list = blogService.getAllBlog();
        /**
         * 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
         * 封装了详细的分页信息,传入连续显示的页数
         */
        PageInfo<Blog> pageInfo=new PageInfo(list);

        return ResponseData.success(pageInfo);

    }

    /**
     * 根据id查询
     */
    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    @ResponseBody
    public ResponseData getById(@RequestParam("id") Integer id){

        Blog blog = blogService.getById(id);
        if (blog !=null){
            return ResponseData.success(blog);
        }

        return ResponseData.fail("查询失败");
    }

    /**
     * 写博客
     */
    @RequestMapping(value = "/saveBlog",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData saveBlog(Blog blog){
        int i = blogService.saveBlog(blog);
        if (i==1){
            return ResponseData.success();
        }

        return ResponseData.fail("保存失败");
    }

    /**
     * 删除博客
     */
    @RequestMapping(value = "/deleteBlog",method = RequestMethod.GET)
    @ResponseBody
    public ResponseData deleteBlog(@RequestParam("id") Integer id){

        int i = blogService.deleteBlog(id);

        if (i == 1){
            return ResponseData.success();
        }
        return ResponseData.fail("删除失败");
    }

}
