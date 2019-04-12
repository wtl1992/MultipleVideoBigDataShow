package biz.controller.facetoface;


import biz.service.facetoface.GetTokenService;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@Lazy(value = true)
@Scope("singleton")
public class GetTokenController {

    @Resource(name = "getTokenService")
    private GetTokenService getTokenService;

    @RequestMapping("/getToken")
    public @ResponseBody
    String getToken(){
        return getTokenService.getToken();
    }
}
