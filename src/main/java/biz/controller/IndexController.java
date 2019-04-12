package biz.controller;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Lazy
@Scope("singleton")
public class IndexController {


    @RequestMapping("/index")
    public String index(){
        return "index";
    }


}
