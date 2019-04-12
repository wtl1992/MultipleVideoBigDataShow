package biz.controller;

import biz.service.AllResultItemService;
import biz.service.V_qq_comSearchBigDataService;
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
public class BigDataController {
    @Resource(name = "allResultItemService")
    AllResultItemService allResultItemService;

    @RequestMapping("/getBigData")
    public @ResponseBody
    Map<String,Object> getBigData(String keyword,int pageIndex){
        return allResultItemService.getAllResults(keyword,pageIndex);
    }
}
