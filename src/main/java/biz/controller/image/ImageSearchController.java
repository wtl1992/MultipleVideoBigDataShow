package biz.controller.image;


import biz.service.image.ImageSearchService;
import model.image.Image;
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
public class ImageSearchController {

    @Resource(name = "imageSearchService")
    private ImageSearchService imageSearchService;

    /**
     * 获得所有匹配关键字的分页图片api
     * @param keyword
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping("/getAllMatchingImages")
    public @ResponseBody
    List<Image> getAllMatchingImages(String keyword,
                                      int pageIndex,
                                      int pageSize){
        return imageSearchService.getAllMatchingImages(keyword, pageIndex, pageSize);
    }

    @RequestMapping("/imageResult")
    public String imageResult(String keyword,
                                    int pageIndex,
                                    int pageSize,
                                    Model model){
        model.addAttribute("keyword",keyword);
        model.addAttribute("pageIndex",pageIndex);
        model.addAttribute("pageSize",pageSize);
        return "imageResult";
    }

    @RequestMapping("m/imageResult")
    public String m_imageResult(String keyword,
                              int pageIndex,
                              int pageSize,
                              Model model){
        model.addAttribute("keyword",keyword);
        model.addAttribute("pageIndex",pageIndex);
        model.addAttribute("pageSize",pageSize);
        return "m/imageResult";
    }
}
