package biz.service;

import org.jsoup.Jsoup;
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
public class V_qq_comSearchBigDataService implements SearchBigDataServiceInterface {

//    private Map<String,Object> map = new HashMap<String, Object>();
//    //存储多样化的数据列表
//    private List<Map<String,Object>> multiply_list = new ArrayList<Map<String, Object>>();

    public void analyseBigData(String keyword,
                                                   Map<String,Object> map,
                                                   List<Map<String,Object>> multiply_list,
                                                   List<Map<String,Object>> single_list,
                                                   int pageIndex){
        String html = null ;

        try {
            html = HttpRequestUtil.requestHttp("https://v.qq.com/x/search/?q="+ URLEncoder.encode(keyword, "utf-8")+"&cur="+pageIndex,"utf-8","GET");;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (html != null && !"".equalsIgnoreCase(html)){
            Element document = Jsoup.parse(html);

            Element allDiv = document.selectFirst("div.wrapper_main");

            Elements item_v_Divs = allDiv.select("div.result_item.result_item_v");

            Elements item_h_Divs = allDiv.select("div.result_item.result_item_h._quickopen");

            for (int i=0;i<item_v_Divs.size();i++){
                try {
                    Map<String,Object> multiplys = new HashMap<String, Object>();
                    Element infoDiv = item_v_Divs.get(i).selectFirst("div._infos").selectFirst("div");
                    multiplys.put("title",infoDiv.selectFirst("h2.result_title").selectFirst("a").text());
                    multiplys.put("href",infoDiv.selectFirst("h2.result_title").selectFirst("a").attr("href"));
                    if (infoDiv.selectFirst("div.info_item.info_item_desc") != null){
                        multiplys.put("synopsis",infoDiv.selectFirst("div.info_item.info_item_desc").selectFirst("span.desc_text").text());
                    }
//                    for (Map<String,Object> tmp_map : multiply_list){
//                        if (tmp_map.get("synopsis").toString().equalsIgnoreCase(multiplys.get("synopsis").toString())){
//                            throw new Exception("重复数据异常!!!");
//                        }
//                    }
                    multiplys.put("imgSrc",infoDiv.selectFirst("a > img").attr("src"));
                    try {
                        Elements playListDivs = item_v_Divs.get(i).selectFirst("div._playlist").select("div.item");
                        List<Map<String,Object>> playList = new ArrayList<Map<String, Object>>();
                        multiplys.put("playList",playList);
                        for (int j=0;j<playListDivs.size();j++){
                            Map<String,Object> playListMap = new HashMap<String, Object>();
                            Element innerA = playListDivs.get(j).selectFirst("a");
                            playListMap.put("href",innerA.attr("href"));
                            playListMap.put("title",innerA.text());
                            playList.add(playListMap);
                        }
                    }
                    catch (Exception e){
                        //
                    }
                    multiplys.put("source","腾讯视频");
                    synchronized (AllResultItemService.class){
                        multiply_list.add(multiplys);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            //处理单条数据，即item_h_Divs里的内容
            for (int i=0;i<item_h_Divs.size();i++){
                try {
                    Map<String,Object> singles = new HashMap<String, Object>();
                    Element infoA = item_h_Divs.get(i).selectFirst("h2.result_title > a");
                    singles.put("title",infoA.text());
                    singles.put("href",infoA.attr("href"));
                    singles.put("imgSrc",item_h_Divs.get(i).selectFirst("a > img").attr("src"));
                    Element infoDiv = item_h_Divs.get(i).selectFirst("div.result_info");
                    if (infoDiv.selectFirst("div.info_item.info_item_odd span.content") != null){
                        singles.put("time",infoDiv.selectFirst("div.info_item.info_item_odd span.content").text());
                    }
                    if (infoDiv.selectFirst("div.info_item.info_item_even span.content") != null){
                        singles.put("author",infoDiv.selectFirst("div.info_item.info_item_even span.content").text());
                    }
                    if (infoDiv.selectFirst("div.info_item.info_item_desc span.desc_text") != null){
                        singles.put("synopsis",infoDiv.selectFirst("div.info_item.info_item_desc span.desc_text").text());
                    }
                    singles.put("source","腾讯视频");
                    synchronized (AllResultItemService.class){
                        single_list.add(singles);
                    }
                }
                catch (Exception e){
                    //e.printStackTrace();
                }
            }
        }

    }
}
