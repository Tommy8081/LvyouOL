package com.tommy.mapper;

import com.tommy.entity.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by tommy on 2020/4/18 22:23
 */
@Mapper
public interface PostMapper {

    @Insert("insert into t_post(title,type_tid,user_uid,content,index_img,createDate,updateDate,tag,published) values (#{title},#{type.tid},#{user.uid},#{content},#{indexImg},now(),now(),#{tag},#{published})")
    int insert(Post post);
    /**
     * 文章修改
     * @return
     */
    @Update("update t_post set index_img=#{indexImg},title=#{title},published=#{published},type_tid=#{type.tid},user_uid=#{user.uid},content=#{content},tag=#{tag},updateDate=now() where id=#{id}")
    int updateByPrimaryKey(Post post);

    /**
     * 文章删除
     * @return
     */
    @Delete("delete from t_post where id=#{id}")
    int deleteByPrimaryKey(@Param("id")int id);

    /**
     * 使用id查询
     */
    @Select("SELECT * FROM t_post where id=#{id}")
    @Results({
            @Result(property = "type",column = "type_tid", one = @One(select =
                    "com.tommy.mapper.TypeMapper.selectByPrimaryKey")),
            @Result(property = "user",column = "user_uid", one = @One(select =
                    "com.tommy.mapper.UserMapper.selectByPrimaryKey"))
    })
    List<Post> selectByPrimaryKey(@Param("id")int id);

    /**
     * 使用uid查询
     */
    @Select("SELECT * FROM t_post where user_uid=#{user_uid} order by id desc")
    @Results({
            @Result(property = "type",column = "type_tid", one = @One(select =
                    "com.tommy.mapper.TypeMapper.selectByPrimaryKey")),
            @Result(property = "user",column = "user_uid", one = @One(select =
                    "com.tommy.mapper.UserMapper.selectByPrimaryKey"))
    })
    List<Post> selectByuserId(@Param("user_uid")int user_uid);

    /**
     * 查询所有
     */
    @Select("SELECT * FROM t_post order by id desc")
    @Results({
            @Result(property = "type",column = "type_tid", one = @One(select =
            "com.tommy.mapper.TypeMapper.selectByPrimaryKey")),
            @Result(property = "user",column = "user_uid", one = @One(select =
                    "com.tommy.mapper.UserMapper.selectByPrimaryKey"))
    })
    List<Post> selectAllPost();

    /**
     * 查询精品贴
     */
    @Select("SELECT * FROM t_post where tag = 1 order by id desc")
    @Results({
            @Result(property = "type",column = "type_tid", one = @One(select =
                    "com.tommy.mapper.TypeMapper.selectByPrimaryKey")),
            @Result(property = "user",column = "user_uid", one = @One(select =
                    "com.tommy.mapper.UserMapper.selectByPrimaryKey"))
    })
    List<Post> selectJingPost();

    /**
     * 点赞数修改
     */
    @Update("update t_post set zanN=#{zanN} where id=#{id}")
    int updatePostZanN(@Param("id")int id, @Param("zanN")int zanN);

    /**
     * 根据title模糊查询
     */
    @Select("select * from t_post where title like CONCAT(CONCAT('%',#{title}),'%') order by id desc")
    @Results({
            @Result(property = "type",column = "type_tid", one = @One(select =
                    "com.tommy.mapper.TypeMapper.selectByPrimaryKey")),
            @Result(property = "user",column = "user_uid", one = @One(select =
                    "com.tommy.mapper.UserMapper.selectByPrimaryKey"))
    })
    List<Post> selectPostByTitle(@Param("title")String title);
}
