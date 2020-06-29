package com.tommy.service;

import com.tommy.entity.Plan;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tommy on 2020/5/8 20:25
 */
@Service
public interface PlanService {
    int insertPlan(Plan plan);
    List<Plan> getPlanByUid(Integer id);
    Plan getPlanById(Integer id);
    List<Plan> listPlan();
    int updatePlan(Plan plan);
    int deletePlan(Integer id);
}
