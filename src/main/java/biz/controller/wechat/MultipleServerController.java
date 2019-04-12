package biz.controller.wechat;

import biz.service.music.MusicSearchService;
import biz.service.wechat.MultipleServerService;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@Lazy
@Scope("singleton")
public class MultipleServerController {


    @Resource(name = "multipleServerService")
    private MultipleServerService multipleServerService;

    /**
     * 获取微信服务器IP地址api
     * @return
     */
    @RequestMapping("/getWeChatServerIPAddress")
    public @ResponseBody
    Map<String,Object> getWeChatServerIPAddress(){
        Map<String,Object> map = new HashMap<>();
        map.put("weChatServerIPList",multipleServerService.getWeChatServerIPAddress());
        return map;
    }
}
