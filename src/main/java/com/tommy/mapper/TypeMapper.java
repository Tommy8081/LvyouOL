package com.tommy.mapper;

import com.tommy.entity.Type;

import org.apache.ibatis.annotations.*;


import java.util.List;

/**
 * Created by tommy on 2020/4/18 12:11
 */
@Mapper
public interface TypeMapper {
    /**
     * 类型数据新增
     *
     * @return
     */
    @Insert("insert into t_type(tname) values (#{tname})")
    int insert(Type type);

    /**
     * 类型修改
     * @return
     */
    @Update("update t_type set tname=#{tname} where tid=#{tid}")
    int updateByPrimaryKey(Type type);

    /**
     * 类型删除
     * @return
     */
    @Delete("delete from t_type where tid=#{tid}")
    int deleteByPrimaryKey(@Param("tid")int tid);

    /**
     * 使用id查询
     */
    @Select("SELECT * FROM t_type where tid=#{tid}")
    Type selectByPrimaryKey(@Param("tid")int tid);

    /**
     * 使用name查询
     */
    @Select("SELECT * FROM t_type where tname=#{tname}")
    Type selectByName(@Param("tname")String tname);

    /**
     * 查询所有
     */
    @Select("SELECT * FROM t_type")
    List<Type> selectAllType();

}
