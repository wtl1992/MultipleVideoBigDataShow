package biz.controller;

import biz.service.V_qq_comSearchService;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@Lazy
@Scope("singleton")
public class V_qq_comController {

    @Resource(name = "v_qq_comSearchService")
    private V_qq_comSearchService v_qq_comSearchService;


    @RequestMapping("/getAllVQQCOM_BigData")
    public @ResponseBody
    Map<String,Object> getAllVQQCOM_BigData(){
        return v_qq_comSearchService.analyseVQQCOM();
    }

}
