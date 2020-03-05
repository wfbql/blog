package com.jiangfx.service.Impl;

import com.jiangfx.entity.Admin;
import com.jiangfx.mapper.AdminMapper;
import com.jiangfx.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jiangfeixiang on 2018/5/3
 * @author smfx1314
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {
    /**
     * 注入AdminMapper
     */
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public Admin getByUsernameAndPassword(String username, String password) {
        Admin admin = adminMapper.getByUsernameAndPassword(username, password);
        if (admin !=null){
            return admin;
        }
        return null;
    }


    /**
     * 根据用户名查询
     * @return
     */
    @Override
    public Admin getByUsername(String username) {

        return adminMapper.getByUsername(username);
    }
}
