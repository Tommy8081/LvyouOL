package com.tommy.mapper;

import com.tommy.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by tommy on 2020/4/14 17:33
 */
@Mapper
public interface UserMapper {

    /**
     * 用户数据新增
     * @return
     */
    @Insert("insert into t_user(avatar,uname,password,tel,email,create_date) values (#{avatar},#{uname},#{password},#{tel},#{email},now())")
    int insert(User user);

    /**
     * 用户数据修改
     * @return
     */
    @Update("update t_user set avatar=#{avatar},uname=#{uname},password=#{password},tel=#{tel},email=#{email},update_date=now() where uid=#{uid}")
    int updateByPrimaryKey(User user);

    /**
     * 用户数据删除
     * @return
     */
    @Delete("delete from t_user where uid=#{uid}")
    int deleteByPrimaryKey(@Param("uid")int uid);

    /**
     * 根据用户名称查询用户信息
     *
     */
    @Select("SELECT * FROM t_user where uname=#{userName}")
    List<User> selectByName(@Param("userName") String userName);

    /**
     * 根据用户id查询用户信息
     */
    @Select("SELECT * FROM t_user where uid=#{uid}")
    User selectByPrimaryKey(@Param("uid")int uid);

    /**
     * 根据用户名密码查询用户
     */
    @Select("SELECT * FROM t_user where uname=#{userName} and password=#{passWord}")
    User selectByUsernameAndPassword(@Param("userName") String username,@Param("passWord") String password);

    /**
     * 根据用户名密码查询用户
     */
    @Select("SELECT * FROM t_user where uname=#{userName} and password=#{passWord} and ifadmin=#{ifadmin}")
    User selectAdmin(@Param("userName") String username,@Param("passWord") String password,@Param("ifadmin") int ifadmin);

    /**
     * 查询所有
     */
    @Select("SELECT * FROM t_user  order by uid desc")
    List<User> selectAllUser();

    /**
     * 查询所有
     */
    @Select("SELECT * FROM t_user WHERE CONCAT(IFNULL(tel,''),IFNULL(email,''),IFNULL(uname,'')) LIKE CONCAT(CONCAT('%',#{value}),'%') order by uid desc")
    List<User> selectUserByMH(String value);
}

