package biz.controller.movie;

import biz.mappers.*;
import biz.service.movie.MovieSearchFromMySQLService;
import biz.service.tv.TVSearchFromMySQLService;
import model.MovieAndStars;
import model.TVAndStars;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Lazy
@Scope("singleton")
public class MovieSearchFromMySQLController {

    @Resource(name = "movieAndStarsMapper")
    private MovieAndStarsMapper movieAndStarsMapper;

    @Resource(name = "movieMapper")
    private MovieMapper movieMapper;

    @Resource(name = "movieStarMapper")
    private MovieStarMapper movieStarMapper;

    @Resource(name = "movieSearchFromMySQLService")
    private MovieSearchFromMySQLService movieSearchFromMySQLService;


    /**
     * 获得电影检索列表api
     * @param keyword
     * @return
     */
    @RequestMapping("/selectByMovieLikeTitle")
    public @ResponseBody
    List<MovieAndStars> selectByMovieLikeTitle(String keyword){
        return movieSearchFromMySQLService.selectByMovieLikeTitle(keyword);
    }


    @RequestMapping("/movieSearchResult")
    public String movieSearchResult(String keyword, Model model){
        model.addAttribute("keyword",keyword);
        return "movieSearchResult";
    }

    @RequestMapping("/m/movieSearchResult")
    public String m_movieSearchResult(String keyword, Model model){
        model.addAttribute("keyword",keyword);
        return "m/movieSearchResult";
    }

}
