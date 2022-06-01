package twcha.h2project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

//    1. Controller에서 GetMapping("/") 으로 맵핑된 html 파일
//    2. index.html순으로 우선순위
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
