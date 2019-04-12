package biz.service.movie;

import biz.service.tv.TVPlaySearchServiceInterface;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import utils.HttpRequestUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Lazy
@Scope("singleton")
public class YouKuMovieSearchService implements MovieSearchServiceInterface {
    @Override
    public void analyseBigData(Map<String, Object> map,
                               List<Map<String, Object>> list,
                               int pageIndex) {
        String json = "";
        try {
             json = HttpRequestUtil.requestHttp("https://so.youku.com/search_video/bigword?keyword="+ URLEncoder.encode("电影","utf-8") +"&pz=12&pg="+pageIndex,"utf-8","GET");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (json != null && !"".equalsIgnoreCase(json)){
            json = (String) ((JSONObject)JSONObject.fromObject(json).get("data")).get("html");
            Document document = Jsoup.parse(json);
            Elements youkuLis = document.select("ul.mod-play-list.play-list-video > li");
            for (int i=0;i<youkuLis.size();i++){
                Map<String,Object> youkuTmpMap = new HashMap<String, Object>();
                youkuTmpMap.put("title",youkuLis.get(i).selectFirst("span.pack-title.txt-single").text());
                Map<String,Object> starMap = new HashMap<String, Object>();
                youkuTmpMap.put("star",starMap);
                starMap.put("starts",youkuLis.get(i).selectFirst("span.cl-lv-4.txt-single").text());
                youkuTmpMap.put("imgSrc",youkuLis.get(i).selectFirst("div.pack-cover img").attr("src"));
                youkuTmpMap.put("href",youkuLis.get(i).selectFirst("a.sk-pack.pack-vertical").attr("href"));
                list.add(youkuTmpMap);
            }
        }
    }
}
