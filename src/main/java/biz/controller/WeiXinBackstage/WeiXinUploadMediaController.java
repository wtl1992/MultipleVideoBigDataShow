package biz.controller.WeiXinBackstage;

import biz.service.WeiXinBackstage.WeiXinUploadMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/weiXinUploadMedia")
public class WeiXinUploadMediaController {


    @Autowired
    private WeiXinUploadMediaService weiXinUploadMediaService;

    /**
     * 上传媒体文件
     * @param multipartFile
     * @param type 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploadMedia")
    public Object uploadMedia(MultipartFile multipartFile,
                              String type)throws Exception{
        return weiXinUploadMediaService.uploadMedia(multipartFile, type);
    }
}
