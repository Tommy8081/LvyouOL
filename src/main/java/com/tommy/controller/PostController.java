package com.tommy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tommy.entity.Post;
import com.tommy.entity.Type;
import com.tommy.entity.User;
import com.tommy.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * Created by tommy on 2020/4/18 22:45
 */
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/getPost",method = RequestMethod.GET)
    public String getPost(){
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(postService.listPost());
            System.out.println("获取post成功！");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
    @RequestMapping(value = "/getJingPost",method = RequestMethod.GET)
    public String getJingPost() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        jsonString = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(postService.listJingPost());
        return jsonString;
    }

    @RequestMapping(value = "/getPostById",method = RequestMethod.POST,produces = "application/json;charset=UTF-8" )
    public String getPostById(@RequestBody Map map) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Integer id = Integer.parseInt(map.get("id").toString());
               String jsonString = mapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(postService.getPost(id));
            System.out.println("查找成功！");
            return jsonString;
    }

    @RequestMapping(value = "/getPostByuserId",method = RequestMethod.POST,produces = "application/json;charset=UTF-8" )
    public String getPostByuserId(@RequestBody Map map) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Integer uid = Integer.parseInt(map.get("uid").toString());
        String jsonString = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(postService.getPostByUserId(uid));
        System.out.println("根据userid查找成功！");
        return jsonString;
    }

    @RequestMapping(value = "/selectPostByTitle",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getPostByTitle(@RequestBody Map map) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String str =  map.get("title").toString();
        String title = "%"+str+"%";
        String jsonString = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(postService.listSelectPost(str));
        System.out.println("根据title查找成功！");
        return jsonString;
    }

    @RequestMapping(value = "/deletePost",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String deletePost(@RequestBody Map map){
        int id = Integer.parseInt(map.get("tid").toString());
        int result = postService.deletePost(id);
        if(result>0){
            return this.getPost();
        }else{
            return null;
        }
    }

    @RequestMapping(value = "/addPost",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String addPost(@RequestBody Map map) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Post post = new Post();
        Object user = new User();
        Object type = new Type();
        post.setTitle(map.get("title").toString());
        post.setContent(map.get("content").toString());
        post.setIndexImg(map.get("indexImg").toString());
        user = map.get("user");
        type = map.get("type");

        post.setUser(user);
        post.setType(type);

        post.setPublished(Boolean.parseBoolean(map.get("published").toString()));
        post.setTag(Boolean.parseBoolean(map.get("tag").toString()));
        System.out.println(post);
        int result = postService.insertPost(post);

        if(result > 0){
            return "insert success";
        }else {
            return "insert failed";
        }
    }

    @RequestMapping(value = "/updatePost",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String updatePost(@RequestBody Map map) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Post post = new Post();
        Object user = new User();
        Object type = new Type();
        post.setId(Integer.parseInt(map.get("id").toString()));
        post.setIndexImg(map.get("indexImg").toString());
        post.setTitle(map.get("title").toString());
        post.setContent(map.get("content").toString());
        post.setPublished(Boolean.parseBoolean(map.get("published").toString()));
        post.setTag(Boolean.parseBoolean(map.get("tag").toString()));
        //前端传递的字符串反序列化为type,user对象
        user = map.get("user");
        type = map.get("type");
//        System.out.println(type);
        post.setType(type);
        post.setUser(user);
        System.out.println(post);
        System.out.println(post.getUser());
        System.out.println(post.getType());

        int result = postService.updatePost(post);

        if(result > 0){
            return this.getPost();
        }else{
            return null;
        }
    }

    @RequestMapping(value = "/updateZanN",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String updateZanN(@RequestBody Map map) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        int id = Integer.parseInt(map.get("id").toString());
        int zanN = Integer.parseInt(map.get("zanN").toString());
        int result = postService.updatePostZanN(id,zanN);

        if(result > 0){
            return this.getPost();
        }else{
            return null;
        }
    }
}
