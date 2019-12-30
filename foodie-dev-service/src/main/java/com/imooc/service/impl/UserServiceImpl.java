package com.imooc.service.impl;

import com.common.utils.Const;
import com.common.utils.IMOOCJSONResult;
import com.common.utils.MD5Utils;
import com.imooc.bo.UserBO;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private static final String facePicture = "";
    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernamIsExits(String username) {
        //1、创建查询的实例，将用户名作为查询条件
        Example userExample = new Example(Users.class);
        //2、创建条件实例 criteria是条件
        Example.Criteria criteria = userExample.createCriteria();
        //3、添加条件。property 和 value
        criteria.andEqualTo("username",username);
        //4、调用查询的mapper selectOneByExample方法
        Users result = usersMapper.selectOneByExample(userExample);
        //5、如果查询结果不为空，则存在返回false,不存在则为true
        return result == null ? false : true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public IMOOCJSONResult register(UserBO usersBo){
        Users user = new Users();
        //ID需要生成一个全局唯一的ID,因为分布式的表，不能使用自增的ID
        String userId = sid.nextShort();
        user.setId(userId);
        user.setUsername(usersBo.getUsername());
        try {
            user.setPassword(MD5Utils.getMD5Str(usersBo.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setFace(facePicture);//头像采用默认值
        user.setNickname(usersBo.getUsername());//昵称采用和注册名一致
        user.setRealname(usersBo.getUsername());//昵称采用和真实名一致
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());
        user.setSex(Const.Sex.SECRET.getCode());//1代表男，0 代表女,2 代表保密

        int result = usersMapper.insert(user);
        if(result == 0){
            return IMOOCJSONResult.errorMsg("用户注册失败");
        }
        //将返回中的敏感信息设置为null
        user = setUserToNull(user);
        return IMOOCJSONResult.ok(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public IMOOCJSONResult queryUserForLogin(UserBO userBO) throws Exception {
        //1、创建查询的实例，将用户名作为查询条件
        Example userExample = new Example(Users.class);
        //2、创建条件实例 criteria是条件
        Example.Criteria criteria = userExample.createCriteria();
        //3、添加条件。property 和 value
        criteria.andEqualTo("username",userBO.getUsername());
        criteria.andEqualTo("password",MD5Utils.getMD5Str(userBO.getPassword()));
        //4、获取到查询的结果
        Users result = usersMapper.selectOneByExample(userExample);
        if(result == null){
            return IMOOCJSONResult.errorMsg("用户名或者密码错误");
        }
        //将返回中的敏感信息设置为null
        result = setUserToNull(result);
        return IMOOCJSONResult.ok(result);
    }

    public Users setUserToNull(Users user){
        user.setCreatedTime(null);
        user.setUpdatedTime(null);
        user.setSex(null);
        user.setRealname(null);
        user.setPassword(null);
        user.setBirthday(null);
        user.setEmail(null);
        user.setMobile(null);
        return user;
    }
}
