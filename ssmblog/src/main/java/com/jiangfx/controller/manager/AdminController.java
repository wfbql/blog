package com.jiangfx.controller.manager;

import com.jiangfx.common.ResponseData;
import com.jiangfx.entity.Admin;
import com.jiangfx.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by jiangfeixiang on 2018/5/3
 * @author smfx1314
 * 后台管理页面
 */
@Controller
@RequestMapping(value = "/admin",method = RequestMethod.GET)
public class AdminController {
    /**
     * 注入adminService
     */
    @Autowired
    private AdminService adminService;

    /**
     * 登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public ResponseData login(@RequestParam("username") String username,@RequestParam("password") String password, HttpSession session){
        Admin admin = adminService.getByUsernameAndPassword(username,password);
        System.out.println(admin);
        if (admin != null){
            return ResponseData.success(admin);
        }
        return ResponseData.fail("用户名或密码不正确");
    }

}
