package biz.service.tv;

import biz.service.tv.TVPlaySearchServiceInterface;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import utils.HttpRequestPostUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Lazy
@Scope("singleton")
public class AiQiYiTVPlaySearchService implements TVPlaySearchServiceInterface {
    @Override
    public void analyseBigData(Map<String, Object> map,
                               List<Map<String,Object>> list,
                               int pageIndex) {
        String json = "";

        Map<String,String> params = new HashMap<String, String>();
        params.put("p","10");
        params.put("p1","101");
        params.put("mode","1");
        params.put("threeCategory","");
        params.put("platform","web");
        params.put("pageNum",pageIndex+"");
        params.put("intentActionType","0");
        params.put("pageSize","10");
        params.put("ctgName","电视剧");
        params.put("dataType","json");
        params.put("method","POST");
        params.put("firstFilter","");
        params.put("secondFilter","");
        params.put("termParams","%26ctgname%3D%25E7%2594%25B5%25E8%25A7%2586%25E5%2589%25A7%26data_type%3D34%253Bmustnot%26data_type%3D1%26graph_type%3D1_1_0_-1%26real_query%3D%25E7%2594%25B5%25E8%25A7%2586%25E5%2589%25A7%25E5%25A4%25A7%25E5%2585%25A8");
        params.put("pos","1");
        json = HttpRequestPostUtil.requestHttp("https://so.iqiyi.com/intent?if=video&type=list","UTF-8","application/x-www-form-urlencoded",params,null,null);

        json = (String) JSONObject.fromObject(json).get("data");

        Document document = Jsoup.parse(json);

        Elements aiqiyiLis = document.select("li.intent-item-twoline");

        for (int i=0;i<aiqiyiLis.size();i++){
            Map<String,Object> tmpMap = new HashMap<String, Object>();
            Element titleDiv = aiqiyiLis.get(i).selectFirst("div.site-piclist_info.twoLine-info");
            tmpMap.put("title",titleDiv.selectFirst("p.site-piclist_info_title > a").text());
            tmpMap.put("imgSrc",aiqiyiLis.get(i).selectFirst("div.site-piclist_pic > a img").attr("src"));
            tmpMap.put("href",aiqiyiLis.get(i).selectFirst("div.site-piclist_pic > a").attr("href"));
            tmpMap.put("jiNumber",aiqiyiLis.get(i).selectFirst("div.site-piclist_pic p span").text());
            Map<String,Object> starMap = new HashMap<String, Object>();
            tmpMap.put("star",starMap);
            List<Map<String,Object>> starList = new ArrayList<Map<String, Object>>();
            starMap.put("starts",starList);
            Elements starAs = titleDiv.select("p.site-piclist_info_describe > a.ml5");
            for (int j=0;j<starAs.size();j++){
                Map<String,Object> startTmpMap = new HashMap<String, Object>();
                startTmpMap.put("name",starAs.get(j).text());
                startTmpMap.put("href",starAs.get(j).attr("href"));
                starList.add(startTmpMap);
            }

            list.add(tmpMap);
        }
    }
}
