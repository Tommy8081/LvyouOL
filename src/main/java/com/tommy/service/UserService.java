package com.tommy.service;

import com.tommy.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tommy on 2020/4/14 15:51
 */
@Service
public interface UserService {
    //增加一个User
    int insertUser(User user);
    //删除一个User
    int deleteByUid(Integer id);
    //更改一个User
    int updateByUid(User user);
    //根据用户id查询
    User selectByUid(Integer id);
    //genuine用户昵称查询
    List<User> selectByName(String userName);
    //根据用户名和密码查询
    User checkUser(String username,String password);
    //根据用户名和密码及ifadmin查询
    User checkAdmin(String username,String password,int ifadmin);
    //查询所有的User
    List<User> selectAllUser();
    //模糊查询user
    List<User> selectUserByMH(String value);
}
