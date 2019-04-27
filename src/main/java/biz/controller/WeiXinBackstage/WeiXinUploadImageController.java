package biz.controller.WeiXinBackstage;


import biz.service.WeiXinBackstage.WeiXinUploadImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/weiXinUploadImage")
public class WeiXinUploadImageController {

    @Autowired
    private WeiXinUploadImageService weiXinUploadImageService;

    @RequestMapping("/uploadImage")
    public Object uploadImage(MultipartFile multipartFile) throws Exception{
        return weiXinUploadImageService.uploadImage(multipartFile);
    }
}
