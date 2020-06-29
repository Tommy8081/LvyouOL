package com.tommy.service.impl;

import com.tommy.entity.Post;
import com.tommy.mapper.PostMapper;
import com.tommy.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tommy on 2020/4/18 22:21
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Override
    public List<Post> listJingPost() {
        return postMapper.selectJingPost();
    }

    @Override
    public List<Post> getPost(Integer id) { return postMapper.selectByPrimaryKey(id);}

    @Override
    public List<Post> listPost() {
        return postMapper.selectAllPost();
    }

    @Override
    public int insertPost(Post post) {
        return postMapper.insert(post);
    }

    @Override
    public int updatePost(Post post) {
        return postMapper.updateByPrimaryKey(post);
    }

    @Override
    public int deletePost(Integer id) {
        return postMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updatePostZanN(Integer id , Integer zanN) {
        return postMapper.updatePostZanN(id,zanN);
    }

    @Override
    public List<Post> listSelectPost(String title) {
        return postMapper.selectPostByTitle(title);
    }

    @Override
    public List<Post> getPostByUserId(Integer id) {
        return postMapper.selectByuserId(id);
    }
}
