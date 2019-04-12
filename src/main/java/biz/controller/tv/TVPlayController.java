package biz.controller.tv;


import biz.service.tv.GetAllTVPlayService;
import model.TVAndStars;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@Lazy
@Scope("singleton")
public class TVPlayController {

    @Resource(name = "getAllTVPlayService")
    private GetAllTVPlayService getAllTVPlayService;

    @RequestMapping("tvPlayResult")
    public @ResponseBody
    Map<String,Object> tvPlayResult(int pageIndex, Model model){
        return getAllTVPlayService.getAllResults(pageIndex);
    }

    @RequestMapping("/tvResult")
    public String tvResult(int pageIndex,Model model){
        model.addAttribute("pageIndex",pageIndex);
        return "tvResult";
    }


    /**
     * 移动端的显示页面
     * @param pageIndex
     * @param model
     * @return
     */
    @RequestMapping("/m/tvResult")
    public String m_tvResult(int pageIndex,Model model){
        model.addAttribute("pageIndex",pageIndex);
        return "m/tvResult";
    }

}
