package biz.controller.tv;

import biz.mappers.TvAndStarsMapper;
import biz.mappers.TvMapper;
import biz.mappers.TvStarMapper;
import biz.service.tv.TVSearchFromMySQLService;
import model.TVAndStars;
import model.Tv;
import model.TvStar;
import model.TvStarExample;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@Controller
@Lazy
@Scope("singleton")
public class TVSearchFromMySQLController {

    @Resource(name = "tvAndStarsMapper")
    private TvAndStarsMapper tvAndStarsMapper;

    @Resource(name = "tvMapper")
    private TvMapper tvMapper;

    @Resource(name = "tvStarMapper")
    private TvStarMapper tvStarMapper;

    @Resource(name = "tvSearchFromMySQLService")
    private TVSearchFromMySQLService tvSearchFromMySQLService;


    @RequestMapping("/selectByTvLikeTitle")
    public @ResponseBody
    List<TVAndStars> selectByTvLikeTitle(String keyword){
        return tvSearchFromMySQLService.selectByTvLikeTitle(keyword);
    }


    @RequestMapping("/tvSearchResult")
    public String tvSearchResult(String keyword, Model model){
        model.addAttribute("keyword",keyword);
        return "tvSearchResult";
    }

    @RequestMapping("/m/tvSearchResult")
    public String m_tvSearchResult(String keyword, Model model){
        model.addAttribute("keyword",keyword);
        return "m/tvSearchResult";
    }

}
