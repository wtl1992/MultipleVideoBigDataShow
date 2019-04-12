package biz.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import utils.HttpRequestUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

//@Service
@Lazy
@Scope("singleton")
public class YouKu_ComSearchBigDataService implements SearchBigDataServiceInterface{
    @Override
    public void analyseBigData(String keyword,
                                              Map<String, Object> map,
                                              List<Map<String, Object>> multiply_list,
                                              List<Map<String, Object>> single_list,
                                              int pageIndex) {
        String html = null;
        try {
            html = HttpRequestUtil.requestHttp("http://so.youku.com/search_video/q_"+ URLEncoder.encode(keyword, "utf-8")+"?pg="+pageIndex,"utf-8","GET");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (html !=  null && !"".equalsIgnoreCase(html)){
            Document document = Jsoup.parse(html);

            Elements oDivs = document.select("div.sk-mod");

            System.out.println(oDivs.size());

            map.put("html",html);
        }
    }
}
