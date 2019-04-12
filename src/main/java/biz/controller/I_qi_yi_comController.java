package biz.controller;

import biz.service.IQiYi_COMSearchService;
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
public class I_qi_yi_comController {

    @Resource(name = "iQiYi_COMSearchService")
    private IQiYi_COMSearchService iQiYi_COMSearchService;


    @RequestMapping("/getAllIQIYICOM_BigData")
    public @ResponseBody
    Map<String,Object> getAllIQIYICOM_BigData(){
        return iQiYi_COMSearchService.analyseIQIYICOM();
    }
}
