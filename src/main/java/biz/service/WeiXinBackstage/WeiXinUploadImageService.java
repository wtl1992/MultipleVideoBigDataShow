package biz.service.WeiXinBackstage;

import biz.filter.SysContext;
import biz.mappers.MediaMapper;
import model.Media;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import utils.HttpRequestPostUtil;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeiXinUploadImageService {

    @Autowired
    private MediaMapper mediaMapper;

    public Object uploadImage(MultipartFile multipartFile) throws Exception{
        Map<String, byte[]> files = new HashMap<String, byte[]>();

        BufferedInputStream bufferedInputStream = new BufferedInputStream(multipartFile.getInputStream());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byte buffer [] = new byte[1024];
        int length = -1;

        while((length = bufferedInputStream.read(buffer))!=-1){
            byteArrayOutputStream.write(buffer,0,length);
        }
        files.put("media", byteArrayOutputStream.toByteArray());
        InputStream inputStream = HttpRequestPostUtil.uploadMultipartFile("https://api.weixin.qq.com/cgi-bin/media/upload?access_token=" + SysContext.ACCESS_TOKEN + "&type=image", null, files, "media","jpg");
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
}
