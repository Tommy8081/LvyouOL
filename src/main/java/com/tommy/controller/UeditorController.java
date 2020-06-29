package com.tommy.controller;

import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by tommy on 2020/4/24 16:13
 */
@RestController
public class UeditorController {
//    @RequestMapping("/ueditorConfig")
//    public void getUEditorConfig(HttpServletResponse response, HttpServletRequest request){
//        String rootPath = "此处配置项目中config.json文件路径";
//        try {
//            String exec = new ActionEnter(request, rootPath).exec();
//            PrintWriter writer = response.getWriter();
//            writer.write(exec);
//            writer.flush();
//            writer.close();
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//        }
//    }
}
