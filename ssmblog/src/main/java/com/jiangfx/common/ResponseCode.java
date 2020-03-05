package com.jiangfx.common;

/**
 * @Author: 姜飞祥
 * @Description:
 * @Date: Create in 2018/10/11 22:13
 * @param: $params$
 * @return: $returns$
 */
public enum ResponseCode {
    /**
     * 返回成功状态码为0
     */
    SUCCESS(0,"SUCCESS"),
    /**
     * 返回失败状态码为1
     */
    ERROR(1,"ERROR"),
    /**
     * 没有登录
     */
    NEED_LOGIN(10,"NEED_LOGIN");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc){
        this.code=code;
        this.desc=desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
