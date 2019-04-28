package biz.service.WeiXinBackstage;

import biz.mappers.MediaMapper;
import model.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeiXinGetMediaService {

    @Autowired
    private MediaMapper mediaMapper;

    public List<Media> getAllMedia(int count,
                                   int pageIndex){
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("start",pageIndex);
        map.put("count",count);
        List<Media> allMediaByLimit = mediaMapper.getAllMediaByLimit(map);
        return allMediaByLimit;
    }

}
