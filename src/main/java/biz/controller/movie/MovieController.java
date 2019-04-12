package biz.controller.movie;


import biz.service.movie.GetAllMovieService;
import biz.service.tv.GetAllTVPlayService;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@Lazy
@Scope("singleton")
public class MovieController {

    @Resource(name = "getAllMovieService")
    private GetAllMovieService getAllMovieService;

    /**
     * 获得电影分页列表api
     * @param pageIndex
     * @param model
     * @return
     */
    @RequestMapping("moviePlayResult")
    public @ResponseBody
    Map<String,Object> moviePlayResult(int pageIndex, Model model){
        return getAllMovieService.getAllResults(pageIndex);
    }

    @RequestMapping("/movieResult")
    public String movieResult(int pageIndex,Model model){
        model.addAttribute("pageIndex",pageIndex);
        return "movieResult";
    }


    @RequestMapping("/m/movieResult")
    public String m_movieResult(int pageIndex,Model model){
        model.addAttribute("pageIndex",pageIndex);
        return "m/movieResult";
    }


}
