package com.tommy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tommy.entity.Plan;
import com.tommy.entity.User;
import com.tommy.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by tommy on 2020/5/8 20:46
 */
@RestController
public class PlanController {
    @Autowired
    private PlanService planService;

    @RequestMapping(value = "/getPlan",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String getPlan() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(planService.listPlan());
        return jsonString;
    }

    @RequestMapping(value = "/insertPlan",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String insertPlan(@RequestBody Map map){
        ObjectMapper objectMapper = new ObjectMapper();
        Plan plan = new Plan();
        Object user = new User();
        plan.setPlan_title(map.get("title").toString());
        plan.setStartDate(map.get("startDate").toString());
        plan.setEndDate( map.get("endDate").toString());
        user = map.get("user");
        plan.setUser(user);
        System.out.println(plan);
        int result = planService.insertPlan(plan);
        if(result > 0){
            return "insert success";
        }else {
            return "insert failed";
        }
    }

    @RequestMapping(value = "/getPlanByUid",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getPlanByUid(@RequestBody Map map) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        int uid = Integer.parseInt(map.get("uid").toString());
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(planService.getPlanByUid(uid));

        return jsonString;
    }

    @RequestMapping(value = "/getPlanById",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String getPlanByid(@RequestBody Map map) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        int id = Integer.parseInt(map.get("id").toString());
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(planService.getPlanById(id));

        return jsonString;
    }

    @RequestMapping(value = "/deletePlan",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String deletePlan(@RequestBody Map map) throws JsonProcessingException {
        int id = Integer.parseInt(map.get("id").toString());
        int result = planService.deletePlan(id);
        if(result>0){
            return this.getPlan();
        }else{
            return null;
        }
    }

    @RequestMapping(value = "/updatePlan",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String updatePlan(@RequestBody Map map) throws JsonProcessingException, ParseException {
        Plan plan = new Plan();
        plan.setId(Integer.parseInt(map.get("id").toString()));
        plan.setPlan_title(map.get("title").toString());
        plan.setStartDate(map.get("startDate").toString());
        plan.setEndDate(map.get("endDate").toString());
        System.out.println(plan);
        int result = planService.updatePlan(plan);
//        int result =1;
        if(result > 0){
            return this.getPlan();
        }else{
            return null;
        }
    }
}
