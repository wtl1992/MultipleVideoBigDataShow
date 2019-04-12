package biz.service;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import utils.HttpRequestUtil;

import java.util.*;

@Service("iQiYi_COMSearchService")
@Lazy
@Scope("singleton")
public class IQiYi_COMSearchService {


    public Map<String, Object> analyseIQIYICOM() {
        Map<String, Object> map = new HashMap<String, Object>();

        String html = HttpRequestUtil.requestHttp("https://www.iqiyi.com/", "utf-8", "GET");

        Document document = Jsoup.parse(html);

        //右侧边数据
        Elements rightData_lis = document.selectFirst("ul#txtlist").select("li.focus-side-item");

        List<Map<String, Object>> rightData_list = new ArrayList<Map<String, Object>>();
        map.put("rightData", rightData_list);

        for (int i = 0; i < rightData_lis.size(); i++) {
            Map<String, Object> rightDatas = new HashMap<String, Object>();
            Element rightData_A = rightData_lis.get(i).selectFirst("a");

            rightDatas.put("href", rightData_A.attr("href"));
            rightDatas.put("text", rightData_A.text());
            rightData_list.add(rightDatas);
        }

        //横幅列表
        Elements banner_lis = document.selectFirst("div#piclist").select("li");

        List<Map<String, Object>> banner_list = new ArrayList<Map<String, Object>>();
        map.put("banner", banner_list);

        for (int i = 0; i < banner_lis.size(); i++) {
            Map<String, Object> banners = new HashMap<String, Object>();
            if (!"".equalsIgnoreCase(banner_lis.get(i).attr("data-jpg-img"))) {
                banners.put("imgSrc", banner_lis.get(i).attr("data-jpg-img"));
            } else {
                banners.put("imgSrc", banner_lis.get(i).attr(":style").split("\\(")[1].split("\\)")[0]);
            }

            banners.put("href", banner_lis.get(i).selectFirst("a").attr("href"));

            banners.put("text", banner_lis.get(i).selectFirst("a").attr("alt"));

            banner_list.add(banners);
        }

        //今日焦点
        Element todayHot_div = document.selectFirst("div#block-D");

        List<Map<String, Object>> todayHot_list = new ArrayList<Map<String, Object>>();
        map.put("todayHot", todayHot_list);

        Element todayHot_A = todayHot_div.selectFirst("h2#D_bigTitle0").selectFirst("a");
        Map<String, Object> todayHots = new HashMap<String, Object>();

        todayHots.put("href", todayHot_A.attr("href"));
        todayHots.put("text", todayHot_A.text());

        todayHot_list.add(todayHots);

        Elements todayHot_lis = todayHot_div.selectFirst("ul#txtList0").select("li");

        for (int i = 0; i < todayHot_lis.size(); i++) {
            Map<String, Object> todayHots1 = new HashMap<String, Object>();

            Element todayhot_A1 = todayHot_lis.get(i).selectFirst("a");
            todayHots1.put("href", todayhot_A1.attr("href"));
            todayHots1.put("text", todayhot_A1.text());
            todayHot_list.add(todayHots1);
        }
        Element todayHot_A2 = todayHot_div.selectFirst("h2#D_bigTitle1").selectFirst("a");
        Map<String, Object> todayHots2 = new HashMap<String, Object>();

        todayHots2.put("href", todayHot_A.attr("href"));
        todayHots2.put("text", todayHot_A.text());

        todayHot_list.add(todayHots2);

        Elements todayHot_lis2 = todayHot_div.selectFirst("ul#txtList1").select("li");

        for (int i = 0; i < todayHot_lis2.size(); i++) {
            Map<String, Object> todayHots3 = new HashMap<String, Object>();

            Element todayhot_A3 = todayHot_lis2.get(i).selectFirst("a");
            todayHots3.put("href", todayhot_A3.attr("href"));
            todayHots3.put("text", todayhot_A3.text());
            todayHot_list.add(todayHots3);
        }

        //中部内容
        Element middle_div = document.selectFirst("div#picList1");

        List<Map<String, Object>> middle_list = new ArrayList<Map<String, Object>>();
        map.put("middle", middle_list);

        Elements middle_divs = middle_div.select("div.qy-mod-link-wrap");


        //System.out.println(middle_divs.size());
        if (middle_divs != null) {
            for (int i = 0; i < middle_divs.size(); i++) {
                try {
                    Map<String, Object> middles = new HashMap<String, Object>();
                    Element eA = middle_divs.get(i).selectFirst("a");
                    middles.put("href", eA.attr("href"));
                    middles.put("imgSrc", eA.selectFirst("img").attr("src"));
                    middle_list.add(middles);
                } catch (Exception e) {
                    //
                }
            }
        }

        //第二行、右侧图片
        Element secondRight_div = document.selectFirst("div#block-FA");

        List<Map<String, Object>> secondRight_list = new ArrayList<Map<String, Object>>();
        map.put("secondRight", secondRight_list);

        Elements secondRight_lis = secondRight_div.select("li.qy-mod-li");


        if (secondRight_lis != null) {
            for (int i = 0; i < secondRight_lis.size(); i++) {
                try {
                    Map<String, Object> secondRights = new HashMap<String, Object>();
                    Element eA = secondRight_lis.get(i).selectFirst("a");
                    secondRights.put("href", eA.attr("href"));
                    secondRights.put("imgSrc", eA.selectFirst("img").attr("src"));
                    Element innerDiv = secondRight_lis.get(i).selectFirst("div.title-wrap");
                    secondRights.put("text", innerDiv.selectFirst("a").text());
                    secondRight_list.add(secondRights);
                } catch (Exception e) {
                    //
                }
            }
        }

        //综艺
        Element variety_div = document.selectFirst("div.qy-mod-wrap-side.mod-horizon-two div.right-col-1");

        List<Map<String, Object>> variety_list = new ArrayList<Map<String, Object>>();
        map.put("variety", variety_list);


        JSONObject zongyi = JSONObject.fromObject(variety_div.attr(":source-data"));

        JSONArray zongyiArray = (JSONArray) zongyi.get("videos");

        for (int i = 0; i < zongyiArray.size(); i++) {
            try {
                Map<String, Object> varietys = new HashMap<String, Object>();
                JSONObject jsonObject = zongyiArray.getJSONObject(i);
                varietys.put("href", jsonObject.get("pageUrl"));
                varietys.put("imgSrc", jsonObject.get("imageUrl"));
                varietys.put("text", jsonObject.get("name"));
                varietys.put("sub_text", jsonObject.get("shortDisplayName"));
                variety_list.add(varietys);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }


        //自制综艺
        Element homemade_div = document.select("div.qy-mod-wrap.mod-horizon-one")
                .get(0);

        List<Map<String, Object>> homemade_list = new ArrayList<Map<String, Object>>();
        map.put("homemade", homemade_list);

        JSONObject homemadeZongyi = JSONObject.fromObject(homemade_div.attr(":source-data"));
        JSONArray homemadeZongyiArray = (JSONArray) homemadeZongyi.get("videos");

        for (int i = 0; i < homemadeZongyiArray.size(); i++) {
            Map<String, Object> homemades = new HashMap<String, Object>();
            JSONObject jsonObject = (JSONObject) homemadeZongyiArray.get(i);
            homemades.put("href", jsonObject.get("pageUrl"));
            homemades.put("imgSrc", jsonObject.get("imageUrl"));
            homemades.put("text", jsonObject.get("name"));
            homemades.put("sub_text", jsonObject.get("prompt"));
            homemades.put("sub_text2", jsonObject.get("prompt"));
            homemade_list.add(homemades);
        }

        //脱口秀
        Element talkShow_div = document.select("div.qy-mod-wrap.mod-horizon-one")
                .get(1);

        List<Map<String, Object>> talkShow_list = new ArrayList<Map<String, Object>>();
        map.put("talkShow", talkShow_list);

        JSONObject talkShowZongyi = JSONObject.fromObject(talkShow_div.attr(":source-data"));

        JSONArray talkShowZongyiArray = (JSONArray) talkShowZongyi.get("videos");

        for (int i = 0; i < talkShowZongyiArray.size(); i++) {
            try {
                Map<String, Object> talkShows = new HashMap<String, Object>();
                JSONObject jsonObject = talkShowZongyiArray.getJSONObject(i).getJSONObject("mixinVideo");
                talkShows.put("href", jsonObject.get("url"));
                talkShows.put("imgSrc", jsonObject.get("imageUrl"));
                talkShows.put("text", jsonObject.get("name"));
                talkShows.put("sub_text", jsonObject.get("subtitle"));
                talkShows.put("sub_text2", jsonObject.get("description"));
                talkShow_list.add(talkShows);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }

        //娱乐
        Element entertainment_div = document.select("div.qy-mod-wrap.mod-horizon-two")
                .get(0);

        List<Map<String, Object>> entertainment_list = new ArrayList<Map<String, Object>>();
        map.put("entertainment", entertainment_list);

        JSONObject entertainmentZongyi = JSONObject.fromObject(entertainment_div.attr(":source-data"));

        JSONArray entertainmentZongyiArray = entertainmentZongyi.getJSONArray("videos");


        for (int i = 0; i < entertainmentZongyiArray.size(); i++) {
            try {
                Map<String, Object> entertainments = new HashMap<String, Object>();
                JSONObject jsonObject = entertainmentZongyiArray.getJSONObject(i).getJSONObject("mixinVideo");
                entertainments.put("href", jsonObject.get("url"));
                entertainments.put("imgSrc", jsonObject.get("imageUrl"));
                entertainments.put("text", jsonObject.get("name"));
                entertainments.put("sub_text", jsonObject.get("shortTitle"));
                entertainments.put("sub_text2", jsonObject.get("description"));
                entertainment_list.add(entertainments);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }

        //电影
        Element movie_div = document.select("div.qy-mod-wrap-side.mod-vertical-two div.right-col-1")
                .get(0);

        List<Map<String, Object>> movie_list = new ArrayList<Map<String, Object>>();
        map.put("movie", movie_list);

        JSONObject movieZongyi = JSONObject.fromObject(movie_div.attr(":source-data"));

        JSONArray movieZongyiArray = (JSONArray) movieZongyi.get("videos");

        for (int i = 0; i < movieZongyiArray.size(); i++) {
            try {
                Map<String, Object> movies = new HashMap<String, Object>();
                JSONObject jsonObject = movieZongyiArray.getJSONObject(i).getJSONObject("list");
                movies.put("href", jsonObject.get("playUrl"));
                movies.put("imgSrc", jsonObject.get("imageUrl"));
                movies.put("text", jsonObject.get("name"));
                movies.put("sub_text", jsonObject.get("albumName"));
                movie_list.add(movies);
            }
            catch (Exception e){
                //e.printStackTrace();
            }
        }

        //电视剧
        Element tv_div = document.select("div.qy-mod-wrap-side.mod-vertical-two div.right-col-1")
                .get(1);

        List<Map<String, Object>> tv_list = new ArrayList<Map<String, Object>>();
        map.put("tv", tv_list);

        JSONObject tvZongyi = JSONObject.fromObject(tv_div.attr(":source-data"));

        JSONArray tvZongyiArray = tvZongyi.getJSONArray("videos");

        for (
                int i = 0; i < tvZongyiArray.size(); i++) {
            try {
                Map<String, Object> tvs = new HashMap<String, Object>();
                JSONObject jsonObject = tvZongyiArray.getJSONObject(i).getJSONObject("album");
                tvs.put("href", jsonObject.get("url"));
                tvs.put("imgSrc", jsonObject.get("imageUrl"));
                tvs.put("text", jsonObject.get("shortTitle"));
                tvs.put("sub_text", jsonObject.get("description"));
                tvs.put("sns_score", jsonObject.get("score"));
                tv_list.add(tvs);
            } catch (Exception e) {
                //
            }
        }

        //动漫
        Element comic_div = document.select("div.qy-mod-wrap-side.mod-horizon-two div.right-col-1")
                .get(0);

        List<Map<String, Object>> comic_list = new ArrayList<Map<String, Object>>();
        map.put("comic", comic_list);

        JSONObject comicZongyi = JSONObject.fromObject(comic_div.attr(":source-data"));

        JSONArray comicZongyiArray = (JSONArray) comicZongyi.get("videos");

        for (int i = 0; i < comicZongyiArray.size(); i++) {
            try {
                Map<String, Object> comics = new HashMap<String, Object>();
                JSONObject jsonObject = comicZongyiArray.getJSONObject(i);
                comics.put("href", jsonObject.get("albumUrl"));
                comics.put("imgSrc", jsonObject.get("imageUrl"));
                comics.put("text", jsonObject.get("shortTitle"));
                comics.put("sub_text", jsonObject.get("focus"));
                comics.put("score", jsonObject.get("score"));
                comic_list.add(comics);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        //儿童
        Element children_div = document.select("div.qy-mod-wrap.mod-horizon-two")
                .get(2);

        List<Map<String, Object>> children_list = new ArrayList<Map<String, Object>>();
        map.put("children", children_list);

        JSONObject childrenZongyi = JSONObject.fromObject(children_div.attr(":source-data"));

        JSONArray childrenZongyiArray = childrenZongyi.getJSONArray("videos");

        for (int i = 0; i < childrenZongyiArray.size(); i++) {
            try {
                Map<String, Object> childrens = new HashMap<String, Object>();
                JSONObject jsonObject = childrenZongyiArray.getJSONObject(i).getJSONObject("album");
                childrens.put("href", jsonObject.get("url"));
                childrens.put("imgSrc", jsonObject.get("imageUrl"));
                childrens.put("text", jsonObject.get("name"));
                childrens.put("sub_text", jsonObject.get("description"));
                childrens.put("score", jsonObject.get("score"));
                children_list.add(childrens);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        //游戏视频
        Element game_div = document.select("div.qy-mod-wrap")
                .get(11);


        List<Map<String, Object>> game_list = new ArrayList<Map<String, Object>>();
        map.put("game", game_list);

        JSONObject gameZongyi = JSONObject.fromObject(game_div.attr(":source-data"));

        JSONArray gameZongyiArray = gameZongyi.getJSONArray("videos");

        for (int i = 0; i < gameZongyiArray.size(); i++) {
            try {
                Map<String, Object> games = new HashMap<String, Object>();
                JSONObject jsonObject = gameZongyiArray.getJSONObject(i).getJSONObject("mixinVideo");
                games.put("href", jsonObject.get("pageUrl"));
                games.put("imgSrc", jsonObject.get("imageUrl"));
                games.put("text", jsonObject.get("name"));
                games.put("sub_text", jsonObject.get("shortTitle"));
                games.put("score", 0);
                game_list.add(games);
            }
            catch (Exception e){
                //e.printStackTrace();
            }
        }

        //体育
        Element sports_div = document.select("div.qy-mod-wrap")
                .get(7);


        List<Map<String, Object>> sports_list = new ArrayList<Map<String, Object>>();
        map.put("sports", sports_list);

        JSONObject sportsZongyi = JSONObject.fromObject(sports_div.attr(":source-data"));

        JSONArray sportsZongyiArray = (JSONArray) sportsZongyi.get("videos");

        for (int i = 0; i < sportsZongyiArray.size(); i++) {
            try {
                Map<String, Object> sports = new HashMap<String, Object>();
                JSONObject jsonObject = sportsZongyiArray.getJSONObject(i).getJSONObject("mixinVideo");
                sports.put("href", jsonObject.get("url"));
                sports.put("imgSrc", jsonObject.get("imageUrl"));
                sports.put("text", jsonObject.get("name"));
                sports.put("sub_text", jsonObject.get("shortTitle"));
                sports.put("score", jsonObject.get("period"));
                sports_list.add(sports);
            }
            catch (Exception e){
               // e.printStackTrace();
            }
        }

        //原创
        Element original_div = document.select("div.qy-mod-wrap.mod-horizon-one")
                .get(3);


        List<Map<String, Object>> original_list = new ArrayList<Map<String, Object>>();
        map.put("original", original_list);

        JSONObject originalZongyi = JSONObject.fromObject(original_div.attr(":source-data"));

        JSONArray originalZongyiArray = (JSONArray) originalZongyi.get("videos");

        for (int i = 0; i < sportsZongyiArray.size(); i++) {
            try {
                Map<String, Object> originals = new HashMap<String, Object>();
                JSONObject jsonObject = originalZongyiArray.getJSONObject(i).getJSONObject("mixinVideo");
                originals.put("href", jsonObject.get("url"));
                originals.put("imgSrc", jsonObject.get("imageUrl"));
                originals.put("text", jsonObject.get("name"));
                originals.put("sub_text", jsonObject.get("shortTitle"));
                originals.put("score", jsonObject.get("albumId"));
                original_list.add(originals);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        //纪录片
        Element documentary_div = document.select("div.qy-mod-wrap.mod-vertical-one")
                .get(0);


        List<Map<String, Object>> documentary_list = new ArrayList<Map<String, Object>>();
        map.put("documentary", documentary_list);

        JSONObject documentaryZongyi = JSONObject.fromObject(documentary_div.attr(":source-data"));

        JSONArray documentaryZongyiArray = (JSONArray) originalZongyi.get("videos");

        for (int i = 0; i < documentaryZongyiArray.size(); i++) {
            try {
                Map<String, Object> documentarys = new HashMap<String, Object>();
                JSONObject jsonObject = documentaryZongyiArray.getJSONObject(i).getJSONObject("album");
                documentarys.put("href", jsonObject.get("url"));
                documentarys.put("imgSrc", jsonObject.get("imageUrl"));
                documentarys.put("text", jsonObject.get("name"));
                documentarys.put("sub_text", jsonObject.get("shortTitle"));
                documentarys.put("score", jsonObject.get("latestTvId"));
                documentary_list.add(documentarys);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return map;
    }
}
