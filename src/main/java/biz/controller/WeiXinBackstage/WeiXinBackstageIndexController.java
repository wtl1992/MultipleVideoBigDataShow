package biz.controller.WeiXinBackstage;

import biz.service.WeiXinBackstage.WeiXinBackstageIndexService;
import model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/weiXinBackstage")
public class WeiXinBackstageIndexController {

    @Autowired
    private WeiXinBackstageIndexService weiXinBackstageIndexService;

    @RequestMapping("/index")
    public String index(){
        return "WeiXinBackstage/index";
    }

    @RequestMapping("/main")
    public String main(HttpSession session){
        if ("logined".equalsIgnoreCase((String) session.getAttribute("flag"))){
            return "WeiXinBackstage/main";
        }
        else{
            return "WeiXinBackstage/index";
        }
    }

    @RequestMapping("/uploadImage")
    public String uploadImage(){
        return "WeiXinBackstage/uploadImage";
    }


    @RequestMapping("/uploadMedia")
    public String uploadMedia(Model model,String type){
        model.addAttribute("type",type);
        switch (type){
            case "image":
                model.addAttribute("media_type","图片");
                break;
            case "voice":
                model.addAttribute("media_type","语音");
                break;
            case "video":
                model.addAttribute("media_type","video");
                break;
            case "thumb":
                model.addAttribute("media_type","thumb");
                break;
        }
        return "WeiXinBackstage/uploadMedia";
    }

    @RequestMapping("/getMedias")
    public String getMedias(){
        return "WeiXinBackstage/getMedias";
    }

    @RequestMapping("/login")
    public String login(LoginUser loginUser,HttpSession session) throws Exception{
        Map<String, Object> objectMap = weiXinBackstageIndexService.login(loginUser,session);
        if ("success".equalsIgnoreCase((String) objectMap.get("status"))){
            return "redirect:main";
        }

        return "WeiXinBackstage/index";
    }
}