package com.jiangfx.mapper;

import com.jiangfx.entity.Admin;
import org.apache.ibatis.annotations.Param;

/**
 * @author smfx1314
 */
public interface AdminMapper {
    /**
     * 根据用户名和密码查询，mybatis多个参数要使用@Param
     * @param username
     * @param password
     * @return
     */
    Admin getByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    Admin getByUsername(String username);

}
