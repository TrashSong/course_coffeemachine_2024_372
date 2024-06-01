package ua.com.kisit2024.course_coffeemachine_2024_372.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/admin")
    public String getPageAdmin(){
        return "admin";
    }

}
