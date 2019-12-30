package com.imooc.controller;

import com.common.utils.CookieUtils;
import com.common.utils.IMOOCJSONResult;
import com.common.utils.JsonUtils;
import com.imooc.bo.UserBO;
import com.imooc.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "注册登录接口", tags = "用户注册登录的相关接口")
@RestController
@RequestMapping("/passport")
public class PassportController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
    @GetMapping("/usernameIsExits")
    @ResponseBody
    public IMOOCJSONResult usernameIsExits(@RequestParam String username) {
        //1、检测用户名是否为空
        if (StringUtils.isBlank(username)) {
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        }
        boolean isExits = userService.queryUsernamIsExits(username);
        //2、如果存在，则返回500，说明已经存在
        if (isExits) {
            return IMOOCJSONResult.errorMsg("用户名已经存在");
        }
        //3、不存在，则返回200说明用户名不存在可以添加
        return IMOOCJSONResult.ok();

    }

    @ApiOperation(value = "用户注册接口", notes = "用户注册接口", httpMethod = "POST")
    @PostMapping("/regist")
    @ResponseBody
    public IMOOCJSONResult register(@RequestBody UserBO usersBo,
                                    HttpServletRequest request, HttpServletResponse response) {
        //1、检测用户名是否为空
        if (StringUtils.isBlank(usersBo.getUsername()) &&
                StringUtils.isBlank(usersBo.getPassword()) &&
                StringUtils.isBlank(usersBo.getConfirmPassword())) {
            return IMOOCJSONResult.errorMsg("用户名或者密码不能为空");
        }
        //2、判断用户名是否已经存在
        boolean isExits = userService.queryUsernamIsExits(usersBo.getUsername());
        if (isExits) {
            return IMOOCJSONResult.errorMsg("用户名已经存在");
        }
        //3、判断密码长度是否大于6位
        if (usersBo.getPassword().length() < 6) {
            return IMOOCJSONResult.errorMsg("密码的长度小于6");
        }
        //4、判断两次密码是否一致
        if (!usersBo.getPassword().equals(usersBo.getConfirmPassword())) {
            return IMOOCJSONResult.errorMsg("两次密码不一致");
        }
        //5、实现注册
        IMOOCJSONResult result = userService.register(usersBo);
        if (result.isOK()) {
            CookieUtils.setCookie(request, response, "user",
                    JsonUtils.objectToJson(result.getData()), true);
        }
        return result;
    }

    @ApiOperation(value = "用户登录接口", notes = "用户登录接口", httpMethod = "POST")
    @PostMapping("/login")
    @ResponseBody
    public IMOOCJSONResult login(@RequestBody UserBO usersBo,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1、检测用户名是否为空
        if (StringUtils.isBlank(usersBo.getUsername()) &&
                StringUtils.isBlank(usersBo.getPassword())) {
            return IMOOCJSONResult.errorMsg("用户名或者密码不能为空");
        }
        //2、获取查询登录的结果
        IMOOCJSONResult result = userService.queryUserForLogin(usersBo);
        if (result.isOK()) {
            CookieUtils.setCookie(request, response, "user",
                    JsonUtils.objectToJson(result.getData()), true);
        }
        //返回结果
        return result;
    }

    @ApiOperation(value = "用户登出接口", notes = "用户登出接口", httpMethod = "POST")
    @PostMapping("/logout")
    @ResponseBody
    public IMOOCJSONResult logout(@RequestBody String userId,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        CookieUtils.deleteCookie(request, response, "user");
        // TODO 用户退出登录，需要清空购物车
        // TODO 分布式会话中需要清除用户数据
        return IMOOCJSONResult.ok();
    }
}
