package com.tommy.service.impl;


import com.tommy.mapper.UserMapper;
import com.tommy.entity.User;
import com.tommy.service.UserService;
import com.tommy.units.MD5Utils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tommy on 2020/4/14 15:53
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public int insertUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int deleteByUid(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByUid(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public User selectByUid(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectByName(String userName) { return userMapper.selectByName(userName); }

    @Override
    public User checkUser(String username, String password) { return userMapper.selectByUsernameAndPassword(username, MD5Utils.code(password)); }

    @Override
    public User checkAdmin(String username, String password, int ifadmin) { return userMapper.selectAdmin(username,MD5Utils.code(password),ifadmin); }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public List<User> selectUserByMH(String value) {
        return userMapper.selectUserByMH(value);
    }
}
