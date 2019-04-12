package biz.controller.facetoface;

import biz.mappers.IdcardMapper;
import model.Idcard;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import utils.UUID;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@Lazy(value = true)
@Scope("singleton")
public class UploladFrontIdCardFileController {

    @Resource(name = "idcardMapper")
    private IdcardMapper idcardMapper;

    @RequestMapping("/uploadFrontIdCardFile")
    public @ResponseBody
    String uploadFrontIdCardFile(MultipartFile frontIdCardFile) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = frontIdCardFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        byte [] buffer = new byte[1024];

        int length = -1;

        Idcard idcard = new Idcard();

        String uuid = UUID.getUUID();

        idcard.setUuid(uuid);

        String frontIdcardImagePath = "D:/upload/" + uuid;

        idcard.setFrontidcardimagepath(frontIdcardImagePath);

        idcardMapper.insert(idcard);

        FileOutputStream fileOutputStream = new FileOutputStream(frontIdcardImagePath);

        while((length = bufferedInputStream.read(buffer))!= -1){
            fileOutputStream.write(buffer,0,length);
        }

        bufferedInputStream.close();
        fileOutputStream.close();

        return uuid;
    }

    /**
     *
     * @param uuid 上一次上传所产生的uuid入库
     * @param backIdCardFile
     * @throws IOException
     */
    @RequestMapping("/uploadBackIdCardFile")
    public @ResponseBody String uploadBackIdCardFile(String uuid,MultipartFile backIdCardFile) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = backIdCardFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        byte [] buffer = new byte[1024];

        int length = -1;

        Idcard idcard = new Idcard();

        idcard.setUuid(uuid);

        String backIdcardImagePath = "D:/upload/" + UUID.getUUID();

        idcard.setBackidcardimagepath(backIdcardImagePath);

        idcardMapper.updateByPrimaryKeySelective(idcard);

        FileOutputStream fileOutputStream = new FileOutputStream(backIdcardImagePath);

        while((length = bufferedInputStream.read(buffer))!= -1){
            fileOutputStream.write(buffer,0,length);
        }

        bufferedInputStream.close();
        fileOutputStream.close();

        return uuid;
    }

    @RequestMapping("/uploadIdCardInfo")
    public @ResponseBody Map<String,Object> uploadIdCardInfo(Idcard idcard){
        int result = idcardMapper.updateByPrimaryKeySelective(idcard);
        Map<String,Object> map = new HashMap<String, Object>();
        if (result > 0){
            map.put("status","success");
        }
        else{
            map.put("status","failed");
        }

        return map;
    }
}
