package biz.service.WeiXinBackstage;

import biz.filter.SysContext;
import biz.mappers.MediaMapper;
import model.Media;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import utils.HttpRequestPostUtil;
import utils.HttpRequestUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeiXinUploadMediaService {

    @Autowired
    private MediaMapper mediaMapper;

    public Object uploadMedia(MultipartFile multipartFile,
                              String type,
                              String content)throws Exception{
        Map<String, byte[]> files = new HashMap<String, byte[]>();
        byte buffer [] = new byte[1024];
        int length = -1;
        /**
         * 使用百度翻译中的语音进行上传
         */
        if ("voice".equalsIgnoreCase(type)){
            String url_voice = "https://fanyi.baidu.com/gettts?lan=zh&text="+ URLEncoder.encode(content,"utf-8")+"&spd=5&source=web";
            byte[] httpThroughBytes = HttpRequestUtil.requestHttpThroughBytes(url_voice, "utf-8", "GET");
            files.put("media", httpThroughBytes);
        }
        else{
            BufferedInputStream bufferedInputStream = new BufferedInputStream(multipartFile.getInputStream());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            while((length = bufferedInputStream.read(buffer))!=-1){
                byteArrayOutputStream.write(buffer,0,length);
            }
            files.put("media", byteArrayOutputStream.toByteArray());
        }
        String suffix = "jpg";
        switch (type){
            case "image":
                suffix = "jpg";
                break;
            case "voice":
                suffix = "amr";
                break;
            case "video":
                suffix = "mp4";
            case "thumb":
                suffix = "jpg";
                break;
        }
        InputStream inputStream = HttpRequestPostUtil.uploadMultipartFile("https://api.weixin.qq.com/cgi-bin/material/add_material?access_token="+SysContext.ACCESS_TOKEN+"&type="+type, null, files, "media",suffix);
        BufferedInputStream bufferedInputStream_result = new BufferedInputStream(inputStream);
        ByteArrayOutputStream byteArrayOutputStream_result = new ByteArrayOutputStream();

        while ((length = bufferedInputStream_result.read(buffer)) != -1) {
            byteArrayOutputStream_result.write(buffer, 0, length);
        }

        String result_upload = new String(byteArrayOutputStream_result.toByteArray(), "utf-8");

        JSONObject jsonObject = JSONObject.fromObject(result_upload);
        Media media = new Media();
        media.setMediaId(jsonObject.getString("media_id"));
        int result = mediaMapper.insertSelective(media);

        Map<String,Object> map = new HashMap<String, Object>();

        if (result > 0){
            map.put("status","success");
        }
        else {
            map.put("status","failed");
        }

        return map;
    }


    /**
     * 获取给定文字的语音
     * @param content
     * @param httpServletResponse
     * @throws Exception
     */
    public void getVoice(String content,
                         HttpServletResponse httpServletResponse)throws Exception{
        String url_voice = "https://fanyi.baidu.com/gettts?lan=zh&text="+ URLEncoder.encode(content,"utf-8")+"&spd=5&source=web";
        byte[] httpThroughBytes = HttpRequestUtil.requestHttpThroughBytes(url_voice, "utf-8", "GET");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpServletResponse.getOutputStream());

        bufferedOutputStream.write(httpThroughBytes);

        bufferedOutputStream.close();
    }
}
