package com.tommy.service.impl;

import com.tommy.entity.Comment;
import com.tommy.mapper.CommentMapper;
import com.tommy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tommy on 2020/5/9 17:11
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int insertComment(Comment comment) {
        return commentMapper.insert(comment);
    }

    @Override
    public List<Comment> getCommentByPid(Integer id) {
        return commentMapper.selectByPid(id);
    }

    @Override
    public List<Comment> getCommentByPidPC(Integer id) {
        return commentMapper.selectByPidPC(id);
    }

    @Override
    public List<Comment> getCommentByUid(Integer id) {
        return commentMapper.selectByUid(id);
    }

    @Override
    public int updateZanN(Integer id, Integer zanN) {
        return commentMapper.updatePCZanN(id,zanN);
    }

    @Override
    public Comment getCommentById(Integer id) {
        return null;
    }

    @Override
    public int deleteComment(Integer id) {
        return 0;
    }
}
