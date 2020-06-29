package com.tommy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tommy on 2020/4/17 16:05
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/index")
    public String blogs(){
        return "admin/index";
    }
}
