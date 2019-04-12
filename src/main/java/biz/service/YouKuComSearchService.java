package biz.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import utils.HttpRequestUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Lazy
@Scope("singleton")
public class YouKuComSearchService {


    public Map<String, Object> analyseYOUKUCOM(){
        Map<String,Object> map = new HashMap<String, Object>();

        String html = HttpRequestUtil.requestHttp("https://www.youku.com/","utf-8","GET");
        html=html.replaceAll("&gt;",">");
        html=html.replaceAll("&lt;","<");
        html=html.replaceAll("&quot;","\"");
        html=html.replaceAll("&#39;","\'");

        Document document = Jsoup.parse(html);

        //右侧小标题
        Element oneLine_div = document.selectFirst("div#m_2540");

        try {
            Elements rightData_lis = oneLine_div.selectFirst("ul.focus-nav").select("li");
            List<Map<String,Object>> rightData_list = new ArrayList<Map<String, Object>>();
            map.put("rightData",rightData_list);

            for (int i=0;i<rightData_lis.size();i++){
                Map<String,Object> rightDatas = new HashMap<String, Object>();

                rightDatas.put("href",rightData_lis.get(i).selectFirst("a").attr("href"));
                rightDatas.put("text",rightData_lis.get(i).selectFirst("span.focus-nav-title").text());
                rightDatas.put("sub_text",rightData_lis.get(i).selectFirst("span.focus-nav-desc").text());
                rightData_list.add(rightDatas);
            }
        }
        catch (Exception e){
            //
        }

        //横幅
        Elements oneLine_lis = oneLine_div.selectFirst("ul.focus-list").select("li");
        List<Map<String,Object>> oneLine_list = new ArrayList<Map<String, Object>>();
        map.put("oneLine",oneLine_list);

        for (int i=0;i<oneLine_lis.size();i++){
            try {
                Map<String,Object> oneLines = new HashMap<String, Object>();

                oneLines.put("href",oneLine_lis.get(i).selectFirst("a").attr("href"));
                oneLines.put("text",oneLine_lis.get(i).selectFirst("a").attr("alt"));
                if (oneLine_lis.get(i).attr("_lazy").split("'").length > 1){
                    oneLines.put("imgSrc",oneLine_lis.get(i).attr("_lazy").split("'")[1]);
                }
                else{
                    oneLines.put("imgSrc",oneLine_lis.get(i).attr("_lazy"));
                }

                if ("".equalsIgnoreCase(String.valueOf(oneLines.get("imgSrc")))){
                    throw new Exception("空异常");
                }
                oneLine_list.add(oneLines);
            }
            catch (Exception e){

            }
        }

        //正在热播
        try{
            Elements hoting_divs = document.selectFirst("div#m_4846").select("div.yk-col4");
            List<Map<String,Object>> hoting_list = new ArrayList<Map<String, Object>>();
            map.put("hoting",hoting_list);

            for (int i=0;i<hoting_divs.size();i++){
                try {
                    Map<String,Object> hotings = new HashMap<String, Object>();

                    Element innerDiv = hoting_divs.get(i).selectFirst("div.p-thumb");
                    hotings.put("href",innerDiv.selectFirst("a").attr("href"));
                    hotings.put("imgSrc",innerDiv.selectFirst("img").attr("alt"));
                    hotings.put("updateTo",hoting_divs.get(i).selectFirst("ul.p-info.pos-bottom").selectFirst("span.p-time").selectFirst("span").text());
                    Element innerUl = hoting_divs.get(i).selectFirst("ul.info-list");
                    hotings.put("text",innerUl.selectFirst("li.title").selectFirst("a").text());
                    hotings.put("sub_text",innerUl.selectFirst("li.subtitle").selectFirst("span").text());
                    hoting_list.add(hotings);
                }
                catch (Exception e){
                    //
                }
            }
        }
        catch (Exception e){
            //
        }

        //剧集
        Elements episode_divs = document.selectFirst("div#m_2556").select("div.yk-col4");
        List<Map<String,Object>> episode_list = new ArrayList<Map<String, Object>>();
        map.put("episode",episode_list);

        for (int i=0;i<episode_divs.size();i++){
            try {
                Map<String,Object> episodes = new HashMap<String, Object>();

                Element innerDiv = episode_divs.get(i).selectFirst("div.p-thumb");
                episodes.put("href",innerDiv.selectFirst("a").attr("href"));
                episodes.put("imgSrc",innerDiv.selectFirst("img").attr("alt"));
                episodes.put("updateTo",episode_divs.get(i).selectFirst("ul.p-info.pos-bottom").selectFirst("span.p-time").selectFirst("span").text());
                Element innerUl = episode_divs.get(i).selectFirst("ul.info-list");
                episodes.put("text",innerUl.selectFirst("li.title").selectFirst("a").text());
                episodes.put("sub_text",innerUl.selectFirst("li.subtitle").selectFirst("span").text());
                episode_list.add(episodes);
            }
            catch (Exception e){
                //
            }
        }

        //超级网剧
        Elements superNet_divs = document.selectFirst("div#m_5296").select("div.yk-col4");
        List<Map<String,Object>> superNet_list = new ArrayList<Map<String, Object>>();
        map.put("superNet",superNet_list);

        for (int i=0;i<superNet_divs.size();i++){
            try {
                Map<String,Object> superNets = new HashMap<String, Object>();

                Element innerDiv = superNet_divs.get(i).selectFirst("div.p-thumb");
                superNets.put("href",innerDiv.selectFirst("a").attr("href"));
                superNets.put("imgSrc",innerDiv.selectFirst("img").attr("alt"));
                superNets.put("updateTo",superNet_divs.get(i).selectFirst("ul.p-info.pos-bottom").selectFirst("span.p-time").selectFirst("span").text());
                Element innerUl = superNet_divs.get(i).selectFirst("ul.info-list");
                superNets.put("text",innerUl.selectFirst("li.title").selectFirst("a").text());
                superNets.put("sub_text",innerUl.selectFirst("li.subtitle").selectFirst("span").text());
                superNet_list.add(superNets);
            }
            catch (Exception e){
                //
            }
        }

        //综艺
        try {
            Elements variety_divs = document.selectFirst("div#m_2559_c_9285").select("div.yk-col4");
            List<Map<String,Object>> variety_list = new ArrayList<Map<String, Object>>();
            map.put("variety",variety_list);

//        System.out.println(document.selectFirst("div#m_2559_c_9285"));

            for (int i=0;i<variety_divs.size();i++){
                try {
                    Map<String,Object> varietys = new HashMap<String, Object>();

                    Element innerDiv = variety_divs.get(i).selectFirst("div.p-thumb");
                    varietys.put("href",innerDiv.selectFirst("a").attr("href"));
                    varietys.put("imgSrc",innerDiv.selectFirst("img").attr("alt"));
                    varietys.put("updateTo",variety_divs.get(i).selectFirst("ul.p-info.pos-bottom").selectFirst("span.p-time").selectFirst("span").text());
                    Element innerUl = variety_divs.get(i).selectFirst("ul.info-list");
                    varietys.put("text",innerUl.selectFirst("li.title").selectFirst("a").text());
                    varietys.put("sub_text",innerUl.selectFirst("li.subtitle").selectFirst("span").text());
                    variety_list.add(varietys);
                }
                catch (Exception e){
                    //
                }
            }
        }
        catch (Exception e){
            //
        }

        //电影
        Elements movie_divs = document.selectFirst("div#m_2561_c_8264").select("div.yk-col4");
        List<Map<String,Object>> movie_list = new ArrayList<Map<String, Object>>();
        map.put("movie",movie_list);

        for (int i=0;i<movie_divs.size();i++){
            try {
                Map<String,Object> movies = new HashMap<String, Object>();

                Element innerDiv = movie_divs.get(i).selectFirst("div.p-thumb");
                movies.put("href",innerDiv.selectFirst("a").attr("href"));
                movies.put("imgSrc",innerDiv.selectFirst("img").attr("alt"));
                Element innerUl = movie_divs.get(i).selectFirst("ul.info-list");
                movies.put("text",innerUl.selectFirst("li.title").selectFirst("a").text());
                movies.put("sub_text",innerUl.selectFirst("li.subtitle").selectFirst("span").text());
                movie_list.add(movies);
            }
            catch (Exception e){
                //
            }
        }


        //会员
        try {
            Elements member_divs = document.selectFirst("div#m_2562_c_9291").select("div.yk-col4");
            List<Map<String,Object>> member_list = new ArrayList<Map<String, Object>>();
            map.put("member",member_list);

            for (int i=0;i<member_divs.size();i++){
                try {
                    Map<String,Object> members = new HashMap<String, Object>();

                    Element innerDiv = member_divs.get(i).selectFirst("div.p-thumb");
                    members.put("href",innerDiv.selectFirst("a").attr("href"));
                    members.put("imgSrc",innerDiv.selectFirst("img").attr("alt"));
                    members.put("updateTo",member_divs.get(i).selectFirst("ul.p-info.pos-bottom").selectFirst("span.p-time").selectFirst("span").text());
                    Element innerUl = member_divs.get(i).selectFirst("ul.info-list");
                    members.put("text",innerUl.selectFirst("li.title").selectFirst("a").text());
                    members.put("sub_text",innerUl.selectFirst("li.subtitle").selectFirst("span").text());
                    member_list.add(members);
                }
                catch (Exception e){
                    //
                }
            }
        }
        catch (Exception e){
            //
        }

        //动漫
        try {
            Elements comic_divs = document.selectFirst("div#m_2563").select("div.yk-col4");
            List<Map<String,Object>> comic_list = new ArrayList<Map<String, Object>>();
            map.put("comic",comic_list);

            for (int i=0;i<comic_divs.size();i++){
                try {
                    Map<String,Object> comics = new HashMap<String, Object>();

                    Element innerDiv = comic_divs.get(i).selectFirst("div.p-thumb");
                    comics.put("href",innerDiv.selectFirst("a").attr("href"));
                    comics.put("imgSrc",innerDiv.selectFirst("img").attr("alt"));
                    comics.put("updateTo",comic_divs.get(i).selectFirst("ul.p-info.pos-bottom").selectFirst("span.p-time").selectFirst("span").text());
                    Element innerUl = comic_divs.get(i).selectFirst("ul.info-list");
                    comics.put("text",innerUl.selectFirst("li.title").selectFirst("a").text());
                    comics.put("sub_text",innerUl.selectFirst("li.subtitle").selectFirst("span").text());
                    comic_list.add(comics);
                }
                catch (Exception e){
                    //
                }
            }
        }
        catch (Exception e){
            //
        }

        //大鱼号精选
        Elements bigFish_divs = document.selectFirst("div#m_2566").select("div.yk-col4");
        List<Map<String,Object>> bigFish_list = new ArrayList<Map<String, Object>>();
        map.put("bigFish",bigFish_list);

        for (int i=0;i<bigFish_divs.size();i++){
            try {
                Map<String,Object> bigFishs = new HashMap<String, Object>();

                Element innerDiv = bigFish_divs.get(i).selectFirst("div.p-thumb");
                bigFishs.put("href",innerDiv.selectFirst("a").attr("data-href"));
                bigFishs.put("imgSrc",innerDiv.selectFirst("img").attr("alt"));
                bigFishs.put("timeLength",bigFish_divs.get(i).selectFirst("ul.p-info.pos-bottom").selectFirst("span.p-time").selectFirst("span").text());
                Element innerUl = bigFish_divs.get(i).selectFirst("ul.info-list");
                bigFishs.put("text",innerUl.selectFirst("li.title").selectFirst("a").text());
                bigFishs.put("sub_text",innerUl.selectFirst("li.subtitle").selectFirst("span").text());
                bigFish_list.add(bigFishs);
            }
            catch (Exception e){
                //
            }
        }

        //娱乐
        Elements entertainment_divs = document.selectFirst("div#m_2569_c_8269").select("div.yk-col4");
        List<Map<String,Object>> entertainment_list = new ArrayList<Map<String, Object>>();
        map.put("entertainment",entertainment_list);

        for (int i=0;i<entertainment_divs.size();i++){
            try {
                Map<String,Object> entertainments = new HashMap<String, Object>();

                Element innerDiv = entertainment_divs.get(i).selectFirst("div.p-thumb");
                entertainments.put("href",innerDiv.selectFirst("a").attr("data-href"));
                entertainments.put("imgSrc",innerDiv.selectFirst("img").attr("alt"));
                entertainments.put("timeLength",entertainment_divs.get(i).selectFirst("ul.p-info.pos-bottom").selectFirst("span.p-time").selectFirst("span").text());
                Element innerUl = entertainment_divs.get(i).selectFirst("ul.info-list");
                entertainments.put("text",innerUl.selectFirst("li.title").selectFirst("a").text());
                entertainments.put("sub_text",innerUl.selectFirst("li.subtitle").selectFirst("span").text());
                entertainment_list.add(entertainments);
            }
            catch (Exception e){
                //
            }
        }
        return map;

    }
}
