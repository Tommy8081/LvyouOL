package com.tommy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tommy on 2020/4/26 21:38
 */
@CrossOrigin
@RestController
public class FileController {
    //上传图片
    @RequestMapping(value = "/uploadimg",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Map<String,Object> uploadImg(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        Map<String,Object> map  = new HashMap<>();
        request.setCharacterEncoding("UTF-8");
        if(!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String path = null;
            String type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {
                if ("PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())||"JPEG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    // 自定义的文件名称
                    String trueFileName = fileName;

                    // 设置存放图片文件的路径
//                    path = "..//..//..//..//..//..//data//IMG//" + trueFileName;
                    path = "D://IMG//" + trueFileName;
                    File dest = new File(path);
                    //判断文件父目录是否存在
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdir();
                    }

                    file.transferTo(dest);

                    map.put("code",0);
                    map.put("msg","");
                    map.put("data",trueFileName);
                    return map;
                }else {
                    map.put("code",500);
                    map.put("msg","上传失败");
                    map.put("data",null);
                    return map;
                }
            }else {
                map.put("code",500);
                map.put("msg","上传失败");
                map.put("data",null);
                return map;
            }
        }else {
            map.put("code",500);
            map.put("msg","上传失败");
            map.put("data",null);
            return map;
        }
    }
    //上传文章图片
    @ResponseBody
    @RequestMapping(value = "/uploadpimg",method = RequestMethod.POST)
    public String uploadPImg(HttpServletRequest request, HttpServletResponse response, HttpSession session,MultipartFile file, Map map) throws IOException {
        request.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        if(!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            System.out.println(fileName);
            String path = null;//文件路径
            String type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {
                if ("PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())||"JPEG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    // 自定义的文件名称
                    String trueFileName = fileName;

                    // 设置存放图片文件的路径
//                    String pid = map.get("pid").toString(); pid +"/"+
                    path = "..//..//..//..//..//..//data//IMG//"+  trueFileName;
//                    path = "D://IMG//" + trueFileName;
                    File dest = new File(path);
                    //判断文件父目录是否存在
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdir();
                    }

                    file.transferTo(dest);

                    map.put("error",0);
                    map.put("data",trueFileName);
                    String jsonString = mapper.writerWithDefaultPrettyPrinter()
                            .writeValueAsString(map);
                    return jsonString;
                }else {

                    return null;
                }
            }else {

                return null;
            }
        }else {

            return null;
        }
    }
}
