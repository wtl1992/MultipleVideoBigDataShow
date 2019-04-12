package biz.controller;

import biz.service.V_qq_comSearchService;
import biz.service.YouKuComSearchService;
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
public class You_KucomController {

    @Resource(name = "youKuComSearchService")
    private YouKuComSearchService youKuComSearchService;


    @RequestMapping("/getAllYOUKUCOM_BigData")
    public @ResponseBody
    Map<String,Object> getAllYOUKUCOM_BigData(){
        return youKuComSearchService.analyseYOUKUCOM();
    }
}
