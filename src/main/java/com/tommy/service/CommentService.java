package com.tommy.service;

import com.tommy.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tommy on 2020/5/9 17:10
 */
@Service
public interface CommentService {
    int insertComment(Comment comment);
    int updateZanN(Integer id,Integer zanN);
    List<Comment> getCommentByPid(Integer id);
    List<Comment> getCommentByPidPC(Integer id);
    List<Comment> getCommentByUid(Integer id);
    Comment getCommentById(Integer id);
    int deleteComment(Integer id);
}
