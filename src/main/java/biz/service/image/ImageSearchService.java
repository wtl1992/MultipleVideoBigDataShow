package biz.service.image;

import model.image.Image;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import utils.HttpRequestUtil;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
@Lazy
@Scope("singleton")
public class ImageSearchService {

    public List<Image> getAllMatchingImages(String keyword,
                                             int pageIndex,
                                             int pageSize){
        List<Image> images = new ArrayList<Image>();
        try {
            String _keyword = URLEncoder.encode(keyword,"utf-8");
            String url = "https://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&queryWord="+ _keyword +"&ie=utf-8&oe=utf-8&word="+_keyword+"&pn="+pageIndex+"&rn="+pageSize;

            String json = HttpRequestUtil.requestHttp(url,"utf-8","GET");
            if (json != null && !"".equalsIgnoreCase(json)){
                JSONArray jsonArray = JSONObject.fromObject(json).getJSONArray("data");
                for (int i=0;i<jsonArray.size()-1;i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    images.add(new Image(jsonObject.getString("bdImgnewsDate")
                    ,jsonObject.getString("fromPageTitle")
                    ,jsonObject.getString("fromPageTitleEnc")
                    ,jsonObject.getString("fromURL")
                    ,jsonObject.getString("fromURLHost")
                    ,jsonObject.getString("middleURL")
                    ,jsonObject.getString("thumbURL")
                    ,jsonObject.getString("type")
                    ,jsonObject.getInt("width")));
                }
            }

        }
        catch (Exception e){
            //
        }

        return images;
    }
}
