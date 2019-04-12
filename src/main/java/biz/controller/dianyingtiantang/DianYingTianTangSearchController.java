package biz.controller.dianyingtiantang;

import biz.service.dianyingtiantang.DianYingTianTangSearchService;
import model.dianyingtiantang.DownloadSource;
import model.dianyingtiantang.Information;
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
public class DianYingTianTangSearchController {

    @Resource(name = "dianYingTianTangSearchService")
    private DianYingTianTangSearchService dianYingTianTangSearchService;

    /**
     * 获得最新影片api
     *
     * @param pageIndex
     * @return
     */
    @RequestMapping("/getPagingNewMovies")
    public @ResponseBody
    List<Information> getPagingNewMovies(String pageIndex) {
        return dianYingTianTangSearchService.getPagingNewMovies(pageIndex);
    }


    /**
     * 获得国内电影api
     *
     * @param pageIndex
     * @return
     */
    @RequestMapping("/getPagingChinaMovies")
    public @ResponseBody
    List<Information> getPagingChinaMovies(String pageIndex) {
        return dianYingTianTangSearchService.getPagingChinaMovies(pageIndex);
    }

    /**
     * 获得欧美电影api
     *
     * @param pageIndex
     * @return
     */
    @RequestMapping("/getPagingEuropeansMovies")
    public @ResponseBody
    List<Information> getPagingEuropeansMovies(String pageIndex) {
        return dianYingTianTangSearchService.getPagingEuropeansMovies(pageIndex);
    }

    /**
     * 获得华语电视api
     *
     * @param pageIndex
     * @return
     */
    @RequestMapping("/getPaginghuaYuTvs")
    public @ResponseBody
    List<Information> getPaginghuaYuTvs(String pageIndex) {
        return dianYingTianTangSearchService.getPaginghuaYuTvs(pageIndex);
    }

    /**
     * 获得日韩电视api
     *
     * @param pageIndex
     * @return
     */
    @RequestMapping("/getPagingRiHanTvs")
    public @ResponseBody
    List<Information> getPagingRiHanTvs(String pageIndex) {
        return dianYingTianTangSearchService.getPagingRiHanTvs(pageIndex);
    }


    /**
     * 获得欧美电视api
     *
     * @param pageIndex
     * @return
     */
    @RequestMapping("/getPagingOuMeiTvs")
    public @ResponseBody
    List<Information> getPagingOuMeiTvs(String pageIndex) {
        return dianYingTianTangSearchService.getPagingOuMeiTvs(pageIndex);
    }


    /**
     * 获得最新综艺api
     *
     * @param pageIndex
     * @return
     */
    @RequestMapping("/getPagingNewZongYis")
    public @ResponseBody
    List<Information> getPagingNewZongYis(String pageIndex) {
        return dianYingTianTangSearchService.getPagingNewZongYis(pageIndex);
    }

    /**
     * 获得旧版综艺api
     *
     * @param pageIndex
     * @return
     */
    @RequestMapping("/getPagingOldZongYis")
    public @ResponseBody
    List<Information> getPagingOldZongYis(String pageIndex) {
        return dianYingTianTangSearchService.getPagingOldZongYis(pageIndex);
    }


    /**
     * 获得动漫api
     *
     * @param pageIndex
     * @return
     */
    @RequestMapping("/getPagingDongMans")
    public @ResponseBody
    List<Information> getPagingDongMans(String pageIndex) {
        return dianYingTianTangSearchService.getPagingDongMans(pageIndex);
    }


    /**
     * 获得游戏api
     *
     * @param pageIndex
     * @return
     */
    @RequestMapping("/getPagingGames")
    public @ResponseBody
    List<Information> getPagingGames(String pageIndex) {
        return dianYingTianTangSearchService.getPagingGames(pageIndex);
    }


    /**
     * 获得详情下载页api
     *
     * @param downloadPageUrl
     * @return
     */
    @RequestMapping("/getDownloads")
    public @ResponseBody
    List<DownloadSource> getDownloads(String downloadPageUrl) {
        return dianYingTianTangSearchService.getDownloads(downloadPageUrl);
    }


    @RequestMapping("/getThunderPagingResult")
    public String getThunderPagingResult(String classify,
                                         String pageIndex,
                                         Model model) {
        model.addAttribute("classify", classify);
        model.addAttribute("pageIndex", pageIndex);
        return "thunderPagingResult";
    }

    @RequestMapping("m/getThunderPagingResult")
    public String m_getThunderPagingResult(String classify,
                                         String pageIndex,
                                         Model model) {
        model.addAttribute("classify", classify);
        model.addAttribute("pageIndex", pageIndex);
        return "m/thunderPagingResult";
    }

    @RequestMapping("/getThunderDownloadResult")
    public String getThunderDownloadResult(String url,
                                           Model model) {
        model.addAttribute("url", url);
        return "thunderDownloadResult";
    }

    @RequestMapping("m/getThunderDownloadResult")
    public String m_getThunderDownloadResult(String url,
                                           Model model) {
        model.addAttribute("url", url);
        return "m/thunderDownloadResult";
    }


    /**
     * 搜索分页结果api
     *
     * @param keyword
     * @param typeid
     * @param pageIndex
     * @return
     */
    @RequestMapping("/getThunderSearchPagingResult")
    public @ResponseBody
    List<Information> getThunderSearchPagingResult(String keyword,
                                                   int typeid,
                                                   int pageIndex) {
        return dianYingTianTangSearchService.getSearchPagingResult(keyword, typeid, pageIndex);
    }

    /**
     * 搜索分页结果
     *
     * @param keyword
     * @param typeid
     * @param pageIndex
     * @return
     */
    @RequestMapping("/getThunderResult")
    public String getThunderResult(String keyword,
                                   int typeid,
                                   int pageIndex,
                                   Model model) {
        model.addAttribute("keyword",keyword);
        model.addAttribute("typeid",typeid);
        model.addAttribute("pageIndex",pageIndex);
        return "thunderSearchResult";
    }


    @RequestMapping("m/getThunderResult")
    public String m_getThunderResult(String keyword,
                                   int typeid,
                                   int pageIndex,
                                   Model model) {
        model.addAttribute("keyword",keyword);
        model.addAttribute("typeid",typeid);
        model.addAttribute("pageIndex",pageIndex);
        return "m/thunderSearchResult";
    }

}
