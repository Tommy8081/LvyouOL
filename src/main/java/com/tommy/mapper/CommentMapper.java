package com.tommy.mapper;

import com.tommy.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by tommy on 2020/5/9 17:12
 */
public interface CommentMapper {
    /**
     * 新增评论
     *
     * @return
     */
    @Insert("insert into t_comment(concent,createDate,post_id,user_id) values (#{concent},now(),#{post[0].id},#{user.uid})")
    int insert(Comment comment);

    /**
     * 删除
     * @return
     */
    @Delete("delete from t_comment where cid=#{cid}")
    int deleteByPrimaryKey(@Param("cid")int cid);

    /**
     * 使用uid查询
     */
    @Select("SELECT * FROM t_comment where user_id=#{user_id}")
    @Results({
            @Result(property = "post",column = "post_id", one = @One(select =
                    "com.tommy.mapper.PostMapper.selectByPrimaryKey"))
    })
    List<Comment> selectByUid(@Param("user_id")int user_id);

    /**
     * 使用post_id查询
     */
    @Select("SELECT * FROM t_comment where post_id=#{post_id}")
    @Results({
            @Result(property = "user",column = "user_id", one = @One(select =
                    "com.tommy.mapper.UserMapper.selectByPrimaryKey")),
//            @Result(property = "post",column = "post_id", one = @One(select =
//                    "com.tommy.mapper.PostMapper.selectByPrimaryKey"))
    })
    List<Comment> selectByPid(@Param("post_id")int post_id);

    /**
     * 使用post_id查询父评论
     */
    @Select("SELECT * FROM t_comment where post_id=#{post_id} and parent_comment_cid is null order by createDate desc")
    @Results({
            @Result(property = "user",column = "user_id", one = @One(select =
                    "com.tommy.mapper.UserMapper.selectByPrimaryKey")),
    })
    List<Comment> selectByPidPC(@Param("post_id")int post_id);

    /**
     * 使用父评论id查询子评论
     */
    @Select("SELECT * FROM t_comment where post_id=#{post_id} and parent_comment_cid is null")
    @Results({
            @Result(property = "user",column = "user_id", one = @One(select =
                    "com.tommy.mapper.UserMapper.selectByPrimaryKey")),
    })
    List<Comment> selectByPCid(@Param("post_id")int post_id);

    /**
     * 点赞数修改
     */
    @Update("update t_comment set zanN=#{zanN} where cid=#{id}")
    int updatePCZanN(@Param("id")int id, @Param("zanN")int zanN);
}
