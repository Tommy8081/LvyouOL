package com.tommy.service.impl;

import com.tommy.entity.Plan;
import com.tommy.mapper.PlanMapper;
import com.tommy.service.PlanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tommy on 2020/5/8 20:27
 */
@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    private PlanMapper planMapper;

    @Override
    public Plan getPlanById(Integer id) {
        return planMapper.selectByid(id);
    }

    @Override
    public int insertPlan(Plan plan) {
        return planMapper.insert(plan);
    }

    @Override
    public List<Plan> getPlanByUid(Integer id) {
        return planMapper.selectByUid(id);
    }

    @Override
    public List<Plan> listPlan() {
        return planMapper.selectAllPlan();
    }

    @Override
    public int updatePlan(Plan plan) {
        return planMapper.updateByPrimaryKey(plan);
    }

    @Override
    public int deletePlan(Integer id) {
        return planMapper.deleteByPrimaryKey(id);
    }
}
