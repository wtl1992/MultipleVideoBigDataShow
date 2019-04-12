package biz.service.baiduyunwangpan;

import model.baiduyunwangpan.BaiDuYunWangPan;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import utils.HttpRequestUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
@Lazy
@Scope("singleton")
public class BaiduYunSearchService {

    public List<BaiDuYunWangPan> getSearchPagingBaiduYunSources(String keyword,
                                                                String pageIndex){
        List<BaiDuYunWangPan> baiDuYunWangPans = new ArrayList<BaiDuYunWangPan>();

        try {
            String url = "http://106.15.195.249:8011/search_new?q="+ URLEncoder.encode(keyword,"utf-8") +"&p="+pageIndex+"&_=1551490963059";

            String json = HttpRequestUtil.requestHttp(url,"utf-8","GET");

            JSONObject jsonObject = JSONObject.fromObject(json).getJSONObject("list");

            int count = jsonObject.getInt("count");

            JSONArray jsonArray = jsonObject.getJSONArray("data");

            for (int i=0;i<jsonArray.size();i++){
                JSONObject tmpJsonObject = jsonArray.getJSONObject(i);
                baiDuYunWangPans.add(new BaiDuYunWangPan(tmpJsonObject.getString("blink")
                ,tmpJsonObject.getString("des")
                ,tmpJsonObject.getString("host")
                ,tmpJsonObject.getString("link")
                ,tmpJsonObject.getString("more")
                ,tmpJsonObject.getString("title").replaceAll(keyword,"<span style='color:red;'>"+keyword+"</span>")
                ,(int) Math.ceil(count * 1.0 / 10.0)));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return baiDuYunWangPans;
    }
}
