package com.tommy.service;

import com.tommy.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tommy on 2020/4/18 22:15
 */
@Service
public interface PostService {
    List<Post> getPost(Integer id);
    List<Post> listPost();
    int insertPost(Post post);
    int updatePostZanN(Integer id, Integer zanN);
    int updatePost(Post post);
    int deletePost(Integer id);
    List<Post> listJingPost();
    List<Post> listSelectPost(String title);
    List<Post> getPostByUserId(Integer id);
}
