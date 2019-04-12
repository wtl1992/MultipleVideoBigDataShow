package biz.controller.facetoface;


import biz.service.facetoface.IdCardService;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@Lazy(value = true)
@Scope("singleton")
public class IDCardController {

    @Resource(name = "idCardService")
    private IdCardService idCardService;

    @RequestMapping("/getIdCardInfo")
    public @ResponseBody
    Map<String,String> getIdCardInfo(@RequestParam("access_token") String access_token,
                         @RequestParam("id_card_side") String id_card_side,
                         @RequestParam("image") String image){
        Map<String,String> map = idCardService.getIdCardInfor(access_token, id_card_side, image);
        return map;
    }
}
