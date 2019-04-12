package biz.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import utils.HttpRequestUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Lazy
@Scope("singleton")
public class AiQiYi_ComSearchBigDataService implements SearchBigDataServiceInterface {
    @Override
    public void analyseBigData(String keyword,
                                              Map<String, Object> map,
                                              List<Map<String, Object>> multiply_list,
                                              List<Map<String, Object>> single_list,
                                              int pageIndex) {
        String html = null;

        try {
//            System.out.println("https://so.iqiyi.com/so/q_" + URLEncoder.encode(keyword, "utf-8") + "_page_"+pageIndex);
            html = HttpRequestUtil.requestHttp("https://so.iqiyi.com/so/q_" + URLEncoder.encode(keyword, "utf-8") + "_page_"+pageIndex,"utf-8","GET");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (html != null && !"".equalsIgnoreCase(html)){
            Document document = Jsoup.parse(html);

            Elements oLis = document.select("li.list_item");

            for (int i=0;i<oLis.size();i++){
                if (oLis.get(i).selectFirst("a.figure.figure-180101") != null){
                    //处理单条数据
                    Map<String,Object> singles = new HashMap<String, Object>();
                    Element infoDiv = oLis.get(i).selectFirst("div.result_info.result_info-180101");
                    singles.put("title",infoDiv.selectFirst("h3.result_title > a").text());
                    singles.put("time",infoDiv.selectFirst("div.info_item div.result_info_cont.result_info_cont-half").text());
                    if (infoDiv.selectFirst("div.info_item div.result_info_cont.result_info_cont-row1 span.result_info_txt") != null){
                        singles.put("synopsis",infoDiv.selectFirst("div.info_item div.result_info_cont.result_info_cont-row1 span.result_info_txt").text());
                    }
                    Element linkA = oLis.get(i).selectFirst("a.figure.figure-180101");
                    singles.put("imgSrc",linkA.selectFirst("img").attr("src"));
                    singles.put("href",linkA.attr("href"));
                    singles.put("source","爱奇艺");
                    synchronized (AllResultItemService.class){
                        single_list.add(singles);
                    }
                }
                else{
                    //处理大批数据
                    Map<String,Object> multiplys = new HashMap<String, Object>();
                    multiplys.put("title",oLis.get(i).selectFirst("h3.result_title").text());
                    multiplys.put("href",oLis.get(i).selectFirst("a.figure.figure-180236").attr("href"));
                    multiplys.put("imgSrc",oLis.get(i).selectFirst("a.figure.figure-180236 img").attr("src"));
                    if (oLis.get(i).selectFirst("div.info_item div.result_info_txt") != null){
                        multiplys.put("synopsis",oLis.get(i).selectFirst("div.info_item div.result_info_txt").text());
                    }
                    Elements playLists = oLis.get(i).select("div.info_item.mt15 li.album_item");
                    List<Map<String,Object>> playList = new ArrayList<Map<String, Object>>();
                    multiplys.put("playList",playList);
                    for (int j=0;j<playLists.size();j++){
                        Map<String,Object> playListMap = new HashMap<String, Object>();
                        playListMap.put("href",playLists.get(j).selectFirst("a").attr("href"));
                        playListMap.put("title",playLists.get(j).selectFirst("a").text());
                        playList.add(playListMap);
                    }
                    multiplys.put("source","爱奇艺");
                    synchronized (AllResultItemService.class){
                        multiply_list.add(multiplys);
                    }
                }
            }
        }
    }
}
