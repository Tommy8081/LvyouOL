package com.tommy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tommy.entity.User;
import com.tommy.service.UserService;
import com.tommy.units.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by tommy on 2020/4/14 17:16
 */
// 如： /api/user 的get请求将会被 UserQry() 函数处理
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUser",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String getUser(){
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(userService.selectAllUser());
            System.out.println("获取user成功！");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String register(@RequestBody Map map) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        user.setAvatar((String) map.get("avatar"));
        user.setUname((String) map.get("username"));
        user.setPassword(MD5Utils.code((String) map.get("password")));
        user.setEmail((String) map.get("email"));
        user.setTel((String) map.get("tel"));
        int result = userService.insertUser(user);
        if(result>0){
//            List<User> user1 = userService.selectByName("王五");
            String jsonString = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(user);
            System.out.println("插入成功！");
            System.out.println("插入的结果："+jsonString);
            return "Register success";
        }else{
            System.out.println("插入失败!");
            return "Register Failed";
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String login(@RequestBody User user){

        User user1 = userService.checkUser(user.getUname(),user.getPassword());
        if(user1==null){
            System.out.println(user.getUname()+" "+user.getPassword());
            return null;
        }else{
            return "Login success";
        }
    }

    @RequestMapping(value = "/loginByAdmin",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String loginByAdmin(@RequestBody Map map){
        User user1 = userService.checkAdmin(map.get("uname").toString(),map.get("password").toString(),Integer.parseInt(map.get("isIfadmin").toString()));
        if(user1==null){
            System.out.println(map.get("uname")+" "+map.get("password"));
            return "Login Failed";
        }else{
            return "Login success";
        }
    }

    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String deleteUser(@RequestBody Map map){
        ObjectMapper mapper = new ObjectMapper();
        int id = Integer.parseInt(map.get("uid").toString());
        int result = userService.deleteByUid(id);
        if(result>0){
            return this.getUser();
        }else{
            return null;
        }
    }

    @RequestMapping(value = "/selectUserByUid",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String selectUserUid() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        int uid = 19;
        String jsonString = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(userService.selectByUid(uid));
        System.out.println("根据模糊条件查找User成功！");
        return jsonString;
    }

    @RequestMapping(value = "/selectUserMH",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String selectUserMH(@RequestBody Map map) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String str = map.get("value").toString();
        String value = "%"+str+"%";
        String jsonString = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(userService.selectUserByMH(value));
        System.out.println("根据模糊条件查找User成功！");
        return jsonString;
    }

    @RequestMapping(value = "/selectUserByName",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String selectUserByName(@RequestBody Map map) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String value = map.get("value").toString();
        String jsonString = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(userService.selectByName(value));
        return jsonString;
    }
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String updateUser(@RequestBody Map map) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        user.setUid(Integer.parseInt(map.get("uid").toString()));
        user.setAvatar( map.get("avatar").toString());
        user.setUname( map.get("username").toString());
        User oldUser =  userService.selectByUid(Integer.parseInt(map.get("uid").toString()));
        //判断MD5加密的密码是否是之前的密码
        if(oldUser.getPassword().equals(MD5Utils.code( map.get("password").toString()))){
            user.setPassword(map.get("password").toString());
        }else{
            user.setPassword(MD5Utils.code( map.get("password").toString()));
        }

        user.setEmail(map.get("email").toString());
        user.setTel(map.get("tel").toString());
        System.out.println(user);
        int result = userService.updateByUid(user);
        if(result > 0){
            return this.getUser();
        }else{
            return null;
        }
    }

}
