package com.tommy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tommy.entity.Type;
import com.tommy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by tommy on 2020/4/18 16:07
 */
@RestController
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping(value = "/getType", method = RequestMethod.GET)
    public String getTypes() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(typeService.listType());
        System.out.println("获取Type成功");
        return jsonString;
    }

    @RequestMapping(value = "/getTypeById", method = RequestMethod.POST)
    public String getTypeById(@RequestBody Map map) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        int id = Integer.parseInt(map.get("tid").toString());
        String jsonString = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(typeService.getType(id));
        System.out.println("根据ID获取Type成功");
        return jsonString;
    }

    @RequestMapping(value = "/getTypeByNM", method = RequestMethod.POST)
    public String getTypeByNM(@RequestBody Map map) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String tname = map.get("tname").toString();
        String jsonString = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(typeService.getTypeByName(tname));
        System.out.println("根据NM获取Type成功");
        return jsonString;
    }
    @RequestMapping(value = "/updateType", method = RequestMethod.POST)
    public  String updateTypes(@RequestBody Map map){
        Type type = new Type();
        type.setTid(Integer.parseInt(map.get("tid").toString()));
        type.setTname(map.get("tname").toString());

        int result = typeService.updateType(type);
        if(result>0){
            return "update success!";
        }else{
            return "update failed!";
        }
    }

    @RequestMapping(value = "/deleteType", method = RequestMethod.POST)
    public String deleteType(@RequestBody Map map){
        int id = Integer.parseInt(map.get("tid").toString());
        int result = typeService.deleteType(id);
        if(result>0){
            return "delete success!";
        }else{
            return "delete failed!";
        }
    }
}
