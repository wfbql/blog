package com.jiangfx.service;

import com.jiangfx.entity.Admin;

/**
 * Created by jiangfeixiang on 2018/5/3
 * @author jiangfeixiang
 */
public interface AdminService {
    /**
     * 登录：根据用户名和密码查询
     */
    Admin getByUsernameAndPassword(String username, String password);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    Admin getByUsername(String username);
}
