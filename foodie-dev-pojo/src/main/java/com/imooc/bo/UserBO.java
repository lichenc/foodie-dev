package com.imooc.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户实体BO",description = "前端用户输入参数将存在此entity中")
public class UserBO {
    /**
     * 用户名 用户名
     */
    @ApiModelProperty(value = "用户名",name = "username",example = "张三",required = true)
    private String username;

    /**
     * 密码 密码
     */
    @ApiModelProperty(value = "密码",name = "password",example = "123123",required = true)
    private String password;

    /**
     * 确认 密码
     */
    @ApiModelProperty(value = "确认密码",name = "confirmPassword",example = "123123",required = false)
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
