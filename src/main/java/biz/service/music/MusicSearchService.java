package biz.service.music;

import model.music.Music;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import utils.HttpRequestUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
@Lazy
@Scope("singleton")
public class MusicSearchService {

    public List<Music> getAllMatchingImages(String keyword,
                                            int pageIndex,
                                            int pageSize){
        List<Music> musics = new ArrayList<Music>();

        try {
            String url_search = "https://api.bzqll.com/music/tencent/search?key=579621905&s="+URLEncoder.encode(keyword,"utf-8")+"&limit="+pageSize+"&offset="+pageIndex+"&type=song";

            String json1 = HttpRequestUtil.requestHttp(url_search,"utf-8","GET");

            JSONArray jsonArray = JSONObject.fromObject(json1).
                    getJSONArray("data");

            for (int i=0;i<jsonArray.size();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                musics.add(new Music(jsonObject.getString("id")
                ,jsonObject.getString("lrc")
                ,jsonObject.getString("name")
                ,jsonObject.getString("pic")
                ,jsonObject.getString("singer")
                ,jsonObject.getString("url")));
            }
        }
        catch (Exception e){
            //
        }

        return musics;
    }
}
