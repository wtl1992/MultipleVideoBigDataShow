package biz.controller.WeiXinBackstage;

import biz.service.WeiXinBackstage.WeiXinUploadMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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
                              String type,
                              String content)throws Exception{
        return weiXinUploadMediaService.uploadMedia(multipartFile, type,content);
    }


    /**
     * 根据文字获取voice
     * @param content 文字内容
     * @param httpServletResponse
     * @throws Exception
     */
    @RequestMapping("/getVoice")
    public void getVoice(String content,
                           HttpServletResponse httpServletResponse)throws Exception{
        weiXinUploadMediaService.getVoice(content,httpServletResponse);
    }
}
