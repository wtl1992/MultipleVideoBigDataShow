package biz.controller.baiduyunwangpan;

import biz.service.baiduyunwangpan.BaiduYunSearchService;
import model.baiduyunwangpan.BaiDuYunWangPan;
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
public class BaiduYunSearchController {

    @Resource(name = "baiduYunSearchService")
    private BaiduYunSearchService baiduYunSearchService;

    @RequestMapping("/getSearchPagingBaiduYunSources")
    public @ResponseBody
    List<BaiDuYunWangPan> getSearchPagingBaiduYunSources(String keyword,
                                                         String pageIndex){
        return baiduYunSearchService.getSearchPagingBaiduYunSources(keyword, pageIndex);
    }


    @RequestMapping("/baiduyunwangpanSearchResult")
    public String baiduyunwangpanSearchResult(String keyword,
                                              String pageIndex,
                                              Model model){
        model.addAttribute("keyword",keyword);
        model.addAttribute("pageIndex",pageIndex);
        return "baiduYunSearchResult";
    }

    @RequestMapping("m/baiduyunwangpanSearchResult")
    public String m_baiduyunwangpanSearchResult(String keyword,
                                              String pageIndex,
                                              Model model){
        model.addAttribute("keyword",keyword);
        model.addAttribute("pageIndex",pageIndex);
        return "m/baiduYunSearchResult";
    }
}
