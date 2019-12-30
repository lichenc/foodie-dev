package com.imooc.service;

import com.common.utils.IMOOCJSONResult;
import com.imooc.bo.UserBO;

public interface UserService {

    public boolean queryUsernamIsExits(String username);

    public IMOOCJSONResult register(UserBO userBO);

    public IMOOCJSONResult queryUserForLogin(UserBO userBO) throws Exception;
}
