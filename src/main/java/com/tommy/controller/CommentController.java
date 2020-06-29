package com.tommy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tommy.entity.Comment;
import com.tommy.entity.Post;
import com.tommy.entity.User;
import com.tommy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * Created by tommy on 2020/5/9 19:18
 */
@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/getCommentByPidPC",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getCommentByPidPC(@RequestBody Map map) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        int id = Integer.parseInt(map.get("pid").toString());
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(commentService.getCommentByPidPC(id));
        return jsonString;
    }

    @RequestMapping(value = "/getCommentByPid",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getCommentByPid(@RequestBody Map map) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        int id = Integer.parseInt(map.get("pid").toString());
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(commentService.getCommentByPid(id));
        return jsonString;
    }

    @RequestMapping(value = "/getCommentByUid",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getCommentByUid(@RequestBody Map map) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        int id = Integer.parseInt(map.get("uid").toString());
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(commentService.getCommentByUid(id));
        return jsonString;
    }

    @RequestMapping(value = "/insertComment",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String insertComment(@RequestBody Map map){
        Comment comment = new Comment();
        comment.setConcent(map.get("concent").toString());
        if(map.get("parentId")!=null){
            comment.setParent_comment_cid(Integer.parseInt(map.get("parentId").toString()));
        }
        Object user = new User();
        Object post = new Post();

        user = map.get("user");
        post = map.get("post");

        comment.setUser(user);
        comment.setPost(post);

        int result = commentService.insertComment(comment);

        if(result>0){
            return "insertComment success!";
        }else {
            return "insertComment faild!";
        }

    }
    @RequestMapping(value = "/updatePCZanN",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String updatePCZanN(@RequestBody Map map) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        int id = Integer.parseInt(map.get("id").toString());
        int zanN = Integer.parseInt(map.get("zanN").toString());
        int result = commentService.updateZanN(id,zanN);

        if(result > 0){
            return "zanN update success!";
        }else{
            return null;
        }
    }
}
