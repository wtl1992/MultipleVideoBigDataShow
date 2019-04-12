package biz.controller.facetoface;

import biz.mappers.HeadImageMapper;
import biz.service.facetoface.GetHeadImageRecService;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;
import utils.HttpRequestPostUtil;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@Lazy(value = true)
@Scope("singleton")
public class UploadHeadImageController {

    @Resource(name = "headImageMapper")
    private HeadImageMapper headImageMapper;

    @Resource(name = "getHeadImageRecService")
    private GetHeadImageRecService getHeadImageRecService;

    @RequestMapping("/getHeadImageToken")
    public @ResponseBody String getHeadImageToken(){
        return getHeadImageRecService.getHeadImageToken();
    }

    @RequestMapping("/uploadHeadImageFile")
    public @ResponseBody
    String uploadHeadImageFile(MultipartFile headImageFile) throws IOException {
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect?access_token=" + getHeadImageRecService.getHeadImageToken();

        BASE64Encoder base64Encoder = new BASE64Encoder();
        
        byte buffer [] = new byte[1024];
        
        int length = -1;
        
        BufferedInputStream bufferedInputStream = new BufferedInputStream(headImageFile.getInputStream());


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        
        while ((length = bufferedInputStream.read(buffer))!=-1){
            byteArrayOutputStream.write(buffer,0,length);
        }
        
        bufferedInputStream.close();

        String base64 = base64Encoder.encode(byteArrayOutputStream.toByteArray());


        Map<String,String> params = new HashMap<String, String>();

        params.put("image",base64);
        params.put("group_id","1");
        params.put("image_type","BASE64");

        String result = HttpRequestPostUtil.requestHttp(url, "utf-8", params, null);

        JSONObject jsonObject = JSONObject.fromObject(result);

        if ("SUCCESS".equalsIgnoreCase(jsonObject.getString("error_msg"))){
            //todo something
        }

        return result;
    }
}
