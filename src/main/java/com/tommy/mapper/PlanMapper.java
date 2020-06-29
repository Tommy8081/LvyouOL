package com.tommy.mapper;

import com.tommy.entity.Plan;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by tommy on 2020/5/8 20:28
 */
@Mapper
public interface PlanMapper {
    /**
     * 数据新增
     *
     * @return
     */
    @Insert("insert into t_plan(plan_title,startDate,endDate,createDate,user_uid) values (#{plan_title},#{startDate},#{endDate},now(),#{user.uid})")
    int insert(Plan plan);

    /**
     * 修改
     * @return
     */
    @Update("update t_plan set plan_title=#{plan_title},startDate=#{startDate},endDate=#{endDate},updateDate=now() where id=#{id}")
    int updateByPrimaryKey(Plan plan);

    /**
     * 类型删除
     * @return
     */
    @Delete("delete from t_plan where id=#{id}")
    int deleteByPrimaryKey(@Param("id")int id);

    /**
     * 使用uid查询
     */
    @Select("SELECT * FROM t_plan where user_uid=#{user_uid} order by startDate")
    @Results({
            @Result(property = "user",column = "user_uid", one = @One(select =
                    "com.tommy.mapper.UserMapper.selectByPrimaryKey"))
    })
    List<Plan> selectByUid(@Param("user_uid")int user_tid);

    /**
     * 使用id查询
     */
    @Select("SELECT * FROM t_plan where id=#{id} order by startDate")
    @Results({
            @Result(property = "user",column = "user_uid", one = @One(select =
                    "com.tommy.mapper.UserMapper.selectByPrimaryKey"))
    })
    Plan selectByid(@Param("id")int id);

    /**
     * 查询所有
     */
    @Select("SELECT * FROM t_plan")
    @Results({
            @Result(property = "user",column = "user_uid", one = @One(select =
                    "com.tommy.mapper.UserMapper.selectByPrimaryKey"))
    })
    List<Plan> selectAllPlan();
}
