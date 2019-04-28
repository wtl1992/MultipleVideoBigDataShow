package biz.controller.WeiXinBackstage;


import biz.service.WeiXinBackstage.WeiXinGetMediaService;
import model.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 获取微信公众号资源列表
 */
@RestController
@RequestMapping("/weiXinGetMedia")
public class WeiXinGetMediaController {

    @Autowired
    private WeiXinGetMediaService weiXinGetMediaService;

    @RequestMapping("/getAllMediaByLimit")
    public List<Media> getAllMediaByLimit(int count,
                                   int pageIndex){
        return weiXinGetMediaService.getAllMedia(count, pageIndex);
    }
}
