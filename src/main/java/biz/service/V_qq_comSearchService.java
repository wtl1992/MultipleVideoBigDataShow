package biz.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import utils.HttpRequestUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.*;

@Service
@Lazy
@Scope("singleton")
public class V_qq_comSearchService {

    public Map<String, Object> analyseVQQCOM() {

        Map<String, Object> map = new HashMap<String, Object>();

        String html = HttpRequestUtil.requestHttp("https://v.qq.com/", "utf-8", "GET");

        Document document = Jsoup.parse(html);

        //重磅推荐的列表信息
        Elements rightDatas_Divs = document.select("div.slider_nav.slider_nav_watched");

        Elements rightDatas_as = rightDatas_Divs.get(0).getElementsByTag("a");
        List<Map<String, Object>> rightDatas_list = new ArrayList<Map<String, Object>>();

        map.put("rightDatas", rightDatas_list);
        for (int i = 1; i < rightDatas_as.size(); i++) {
            Map<String, Object> rightDatas = new HashMap<String, Object>();
            rightDatas.put("href", rightDatas_as.get(i).attr("href"));
            String allText = rightDatas_as.get(i).getElementsByClass("text").get(0).text();
            rightDatas.put("text", allText.split(" ")[0]);
            rightDatas.put("imgSrc",rightDatas_as.get(i).attr("data-bgimage"));
            BufferedImage bufferedImage = null;
            try {
                bufferedImage = ImageIO.read(new URL("https:"+rightDatas_as.get(i).attr("data-bgimage")));
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (bufferedImage!= null){
                    int pixel = bufferedImage.getRGB(0,0);
                    int rgb [] = new int [3];
                    rgb[0] = (pixel & 0xff0000) >> 16;
                    rgb[1] = (pixel & 0xff00) >> 8;
                    rgb[2] = (pixel & 0xff);

                    rightDatas.put("rgb","rgb("+rgb[0]+","+rgb[1]+","+rgb[2]+")");
                }
            }
            rightDatas.put("sub_text", allText.split(" ")[1]);
            rightDatas.put("banner_url", rightDatas_as.get(i).attr("data-bgimage"));

            rightDatas_list.add(rightDatas);
        }

        //滚动横幅的列表
        List<Map<String, Object>> banner_list = new ArrayList<Map<String, Object>>();

        map.put("banner", banner_list);

        for (int i = 0; i < rightDatas_list.size(); i++) {
            Map<String, Object> banners = new LinkedHashMap<String, Object>();
            banners.put("text", rightDatas_list.get(i).get("text"));
            banners.put("url", rightDatas_list.get(i).get("banner_url"));
            banner_list.add(banners);
        }

        //大家在看的列表
        Elements multiple_as = document.getElementsByClass("slider_figure_inner")
                .get(0).getElementsByTag("a");

        List<Map<String, Object>> multiple_list = new ArrayList<Map<String, Object>>();

        map.put("multiple_list", multiple_list);

        for (int i = 0; i < multiple_as.size(); i++) {
            Map<String, Object> multiples = new HashMap<String, Object>();
            multiples.put("href", multiple_as.get(i).attr("href"));
            multiples.put("imgSrc", multiple_as.get(i).getElementsByTag("img")
                    .get(0).attr("src"));
            multiples.put("title", multiple_as.get(i).getElementsByClass("slider_figure_title")
                    .get(0).text());
            multiples.put("sub_title", multiple_as.get(i).getElementsByClass("slider_figure_desc")
                    .get(0).text());

            multiple_list.add(multiples);
        }

        //今日热门
        Elements todayHot_divs = document.select("div.mod_figure.mod_figure_h_default.mod_figure_h_default_1row.mod_figure_h_default.mod_figure_scroll")
                .get(0).getElementsByClass("list_item");


        List<Map<String, Object>> todayHot_list = new ArrayList<Map<String, Object>>();

        map.put("todayHot", todayHot_list);

        for (int i = 0; i < todayHot_divs.size(); i++) {
            try {
                Map<String, Object> todayHots = new HashMap<String, Object>();
                Element a = todayHot_divs.get(i).selectFirst("a");
                todayHots.put("href", a.attr("href"));
                Element img = todayHot_divs.get(i).selectFirst("img");
                if (img.attr("src").indexOf("common") > 0){
                    todayHots.put("imgSrc", img.attr("lz_next"));
                }
                else{
                    todayHots.put("imgSrc", img.attr("src"));
                }
                Element a2 = todayHot_divs.get(i).getElementsByClass("figure_detail figure_detail_two_row ")
                        .get(0).selectFirst("a");
                todayHots.put("text", a2.text());
                todayHots.put("timeLength", todayHot_divs.get(i).selectFirst("div.figure_caption")
                        .text().replaceAll(" ", ""));
                todayHot_list.add(todayHots);
            }
            catch (Exception e){
                //
            }
        }

        //原创精选
        Elements original_divs = document.select("div.mod_figure.mod_figure_h_default." +
                "mod_figure_h_default_1row." +
                "mod_figure_h_default." +
                "mod_figure_scroll." +
                "mod_figure_author").get(0).select("div.list_item");

        List<Map<String, Object>> original_list = new ArrayList<Map<String, Object>>();
        map.put("original", original_list);

        for (int i = 0; i < original_divs.size(); i++) {
            try {
                Map<String, Object> originals = new HashMap<String, Object>();

                Element original_A = original_divs.get(i).selectFirst("a");
                originals.put("href", original_A.attr("href"));
                Element original_Img = original_divs.get(i).selectFirst("img");
                if (original_Img.attr("src").indexOf("common") > 0){
                    originals.put("imgSrc", original_Img.attr("lz_next"));
                }
                else{
                    originals.put("imgSrc", original_Img.attr("src"));
                }
                originals.put("timeLength", original_A.selectFirst("div.figure_caption").
                        text().replaceAll(" ", ""));
                originals.put("detailText",original_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row")
                .text());
                original_list.add(originals);
            } catch (Exception e) {
                //未处理的异常
            }
        }

        //强势接档
        Elements mighty_divs = document.selectFirst("div.mod_figure.mod_figure_v_default.mod_figure_v_default_1row.mod_figure_v_default.mod_figure_scroll")
                .select("div.list_item");
        List<Map<String, Object>> mighty_list = new ArrayList<Map<String, Object>>();
        map.put("mighty", mighty_list);

        for (int i=0;i<mighty_divs.size();i++){
            try {
                Map<String, Object> mighties = new HashMap<String, Object>();
                Element mighty_A = mighty_divs.get(i).selectFirst("a");
                mighties.put("href",mighty_A.attr("href"));
                if ("".equalsIgnoreCase(mighty_divs.get(i).selectFirst("img").attr("lz_src"))){
                    mighties.put("imgSrc",mighty_divs.get(i).selectFirst("img").attr("lz_next"));
                }
                else{
                    mighties.put("imgSrc",mighty_divs.get(i).selectFirst("img").attr("lz_src"));
                }
                mighties.put("expectationIndex",mighty_A.selectFirst("span.text.text2").text());
                Element inner_div = mighty_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row.figure_detail_collect");
                mighties.put("text",inner_div.selectFirst("a").text());
                mighties.put("sub_text",inner_div.selectFirst("div").text());
                mighty_list.add(mighties);
            } catch (Exception e) {
                //未处理的异常
            }
        }

        //同步剧场
        Elements synchronous_divs = document.select("div.mod_figure.mod_figure_v_default.mod_figure_v_default_1row.mod_figure_v_default.mod_figure_scroll")
                .get(1).select("div.list_item");

//        System.out.println(synchronous_divs.size());
        List<Map<String, Object>> synchronous_list = new ArrayList<Map<String, Object>>();
        map.put("synchronous", synchronous_list);

        for (int i=0;i<synchronous_divs.size();i++){
            try {
                Map<String, Object> synchronous = new HashMap<String, Object>();
                Element synchronous_A = synchronous_divs.get(i).selectFirst("a");
                synchronous.put("href",synchronous_A.attr("href"));
                if ("".equalsIgnoreCase(synchronous_A.selectFirst("img").attr("lz_src"))){
                    synchronous.put("imgSrc",synchronous_A.selectFirst("img").attr("lz_next"));
                }
                else{
                    synchronous.put("imgSrc",synchronous_A.selectFirst("img").attr("lz_src"));
                }
                synchronous.put("updateTo",synchronous_A.selectFirst("div.figure_caption").
                        text().replaceAll(" ",""));
                Element innerDiv = synchronous_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row");
                synchronous.put("text",innerDiv.selectFirst("a").text());
                synchronous.put("sub_text",innerDiv.selectFirst("div").text());
                synchronous_list.add(synchronous);
            } catch (Exception e) {
                //未处理的异常
            }
        }

        //花絮·剧透·预告片
        Elements trivia_divs = document.select("div.mod_figure.mod_figure_h_default.mod_figure_h_default_1row.mod_figure_h_default.mod_figure_scroll")
                .get(2).select("div.list_item");

//        System.out.println(synchronous_divs.size());
        List<Map<String, Object>> trivia_list = new ArrayList<Map<String, Object>>();
        map.put("trivia", trivia_list);

        for (int i=0;i<trivia_divs.size();i++){
            try {
                Map<String, Object> trivias = new HashMap<String, Object>();
                Element trivia_A = trivia_divs.get(i).selectFirst("a");
                trivias.put("href",trivia_A.attr("href"));
                if ("".equalsIgnoreCase(trivia_A.selectFirst("img").attr("lz_src"))){
                    trivias.put("imgSrc",trivia_A.selectFirst("img").attr("lz_next"));
                }
                else{
                    trivias.put("imgSrc",trivia_A.selectFirst("img").attr("lz_src"));
                }
                trivias.put("timeLength",trivia_A.selectFirst("div").
                        text().replaceAll(" ",""));
                Element innerDiv = trivia_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row");
                trivias.put("text",innerDiv.selectFirst("a").text());
                trivias.put("sub_text",innerDiv.selectFirst("div").text());
                trivia_list.add(trivias);
            } catch (Exception e) {
                //未处理的异常
            }
        }

        //综艺
        Elements variety_divs = document.select("div.mod_figure.mod_figure_h_default.mod_figure_h_default_2row.mod_figure_h_default")
                .get(0).select("div.list_item");

//        System.out.println(synchronous_divs.size());
        List<Map<String, Object>> variety_list = new ArrayList<Map<String, Object>>();
        map.put("variety", variety_list);

        for (int i=0;i<variety_divs.size();i++){
            try {
                Map<String, Object> varietys = new HashMap<String, Object>();
                Element variety_A = variety_divs.get(i).selectFirst("a");
                varietys.put("href",variety_A.attr("href"));
                if ("".equalsIgnoreCase(variety_A.selectFirst("img").attr("lz_src"))){
                    varietys.put("imgSrc",variety_A.selectFirst("img").attr("lz_next"));
                }
                else{
                    varietys.put("imgSrc",variety_A.selectFirst("img").attr("lz_src"));
                }
                varietys.put("qiNumber",variety_A.selectFirst("div").
                        text().replaceAll(" ",""));
                Element innerDiv = variety_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row");
                varietys.put("text",innerDiv.selectFirst("a").text());
                varietys.put("sub_text",innerDiv.selectFirst("div").text());
                variety_list.add(varietys);
            } catch (Exception e) {
                //未处理的异常
            }
        }

        //会员尊享
        Elements membership_divs = document.select("div.mod_figure.mod_figure_h_default.mod_figure_h_default_1row.mod_figure_h_default.mod_figure_scroll")
                .get(3).select("div.list_item");

//        System.out.println(synchronous_divs.size());
        List<Map<String, Object>> membership_list = new ArrayList<Map<String, Object>>();
        map.put("membership", membership_list);

        for (int i=0;i<membership_divs.size();i++){
            try {
                Map<String, Object> memberships = new HashMap<String, Object>();

                Element membership_A = membership_divs.get(i).selectFirst("a");

                memberships.put("href",membership_A.attr("href"));

                if ("".equalsIgnoreCase(membership_A.selectFirst("img").attr("lz_src"))){
                    memberships.put("imgSrc",membership_A.selectFirst("img").attr("lz_next"));
                }
                else{
                    memberships.put("imgSrc",membership_A.selectFirst("img").attr("lz_src"));
                }
                memberships.put("qiNumber",membership_A.selectFirst("div").
                        text().replaceAll(" ",""));
                Element otherDiv = membership_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row");
                memberships.put("text",otherDiv.selectFirst("a").text());
                memberships.put("sub_text",otherDiv.selectFirst("div").text());
                membership_list.add(memberships);
            } catch (Exception e) {

            }
        }

        //电影
        Elements movie_divs = document.select("div.mod_figure.mod_figure_v_default.mod_figure_v_default_2row.mod_figure_v_default")
                .get(0).select("div.list_item");
        List<Map<String, Object>> movie_list = new ArrayList<Map<String, Object>>();
        map.put("movie", movie_list);

        for (int i=0;i<movie_divs.size();i++){
            try {
                Map<String,Object> movies = new HashMap<String, Object>();

                Element movie_A = movie_divs.get(i).selectFirst("a");
                movies.put("href",movie_A.attr("href"));
                if ("".equalsIgnoreCase(movie_A.selectFirst("img").attr("lz_src"))){
                    movies.put("imgSrc",movie_A.selectFirst("img").attr("lz_next"));
                }
                else{
                    movies.put("imgSrc",movie_A.selectFirst("img").attr("lz_src"));
                }
                movies.put("timeLength",movie_A.selectFirst("div.figure_caption").text().replaceAll(" ",""));
                movies.put("score",movie_A.selectFirst("div.figure_score").text());
                Element innerDiv = movie_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row");
                movies.put("text",innerDiv.selectFirst("a").text());
                movies.put("sub_text",innerDiv.selectFirst("div").text());
                movie_list.add(movies);
            }
            catch (Exception e){
                //
            }
        }

        //电影预告
        Elements moviePredict_divs = document.select("div.mod_figure.mod_figure_h_default.mod_figure_h_default_1row.mod_figure_h_default.mod_figure_scroll")
                .get(4).select("div.list_item");
        List<Map<String, Object>> moviePredict_list = new ArrayList<Map<String, Object>>();
        map.put("moviePredict", moviePredict_list);

        for (int i=0;i<moviePredict_divs.size();i++){
            try {
                Map<String,Object> moviePredicts = new HashMap<String, Object>();

                Element moviePredict_A = moviePredict_divs.get(i).selectFirst("a");
                moviePredicts.put("href",moviePredict_A.attr("href"));
                if ("".equalsIgnoreCase(moviePredict_A.selectFirst("img").attr("lz_src"))){
                    moviePredicts.put("imgSrc",moviePredict_A.selectFirst("img").attr("lz_next"));
                }
                else{
                    moviePredicts.put("imgSrc",moviePredict_A.selectFirst("img").attr("lz_src"));
                }
                moviePredicts.put("timeLength",moviePredict_A.selectFirst("div.figure_caption").
                        text().replaceAll(" ",""));
                Element innerDiv = moviePredict_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row");
                moviePredicts.put("text",innerDiv.selectFirst("a").text());
                moviePredicts.put("sub_text",innerDiv.selectFirst("div").text());
                moviePredict_list.add(moviePredicts);
            }
            catch (Exception e){

            }
        }

        //动漫
        Elements comic_divs = document.select("div.mod_figure.mod_figure_v_default.mod_figure_v_default_1row.mod_figure_v_default.mod_figure_scroll")
                .get(2).select("div.list_item");
        List<Map<String, Object>> comic_list = new ArrayList<Map<String, Object>>();
        map.put("comic", comic_list);


        for (int i=0;i<comic_divs.size();i++){
            try {
                Map<String,Object> comics = new HashMap<String, Object>();

                Element comic_A = comic_divs.get(i).selectFirst("a");
                comics.put("href",comic_A.attr("href"));
                if ("".equalsIgnoreCase(comic_A.selectFirst("img").attr("lz_src"))){
                    comics.put("imgSrc",comic_A.selectFirst("img").attr("lz_next"));
                }
                else{
                    comics.put("imgSrc",comic_A.selectFirst("img").attr("lz_src"));
                }
                comics.put("updateTo",comic_A.selectFirst("div.figure_caption").text()
                        .replaceAll(" ",""));
                Element innerDiv = comic_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row");
                comics.put("text",innerDiv.selectFirst("a").text());
                comics.put("sub_text",innerDiv.selectFirst("div").text());
                comic_list.add(comics);
            }
            catch (Exception e){
                //
            }
        }


        //少儿
        Elements children_divs = document.select("div.mod_figure.mod_figure_v_default.mod_figure_v_default_1row.mod_figure_v_default.mod_figure_scroll")
                .get(3).select("div.list_item");
        List<Map<String, Object>> children_list = new ArrayList<Map<String, Object>>();
        map.put("children", children_list);

        for (int i=0;i<children_divs.size();i++){
            try {
                Map<String,Object> childrens = new HashMap<String, Object>();

                Element children_A = children_divs.get(i).selectFirst("a");
                childrens.put("href",children_A.attr("href"));
                if ("".equalsIgnoreCase(children_A.selectFirst("img").attr("lz_src"))){
                    childrens.put("imgSrc",children_A.selectFirst("img").attr("lz_next"));
                }
                else{
                    childrens.put("imgSrc",children_A.selectFirst("img").attr("lz_src"));
                }

                childrens.put("timeLength",children_A.selectFirst("div.figure_caption").text()
                        .replaceAll(" ",""));
                Element innerDiv = children_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row");
                childrens.put("text",innerDiv.selectFirst("a").text());
                childrens.put("sub_text",innerDiv.selectFirst("div").text());
                children_list.add(childrens);
            }
            catch (Exception e){
                //
            }
        }

        //纪录片
        Elements documentary_divs = document.select("div.mod_figure.mod_figure_v_default.mod_figure_v_default_1row.mod_figure_v_default.mod_figure_scroll")
                .get(4).select("div.list_item");
        List<Map<String, Object>> documentary_list = new ArrayList<Map<String, Object>>();
        map.put("documentary", documentary_list);
        for (int i=0;i<documentary_divs.size();i++){
            try {
                Map<String,Object> documentarys = new HashMap<String, Object>();

                Element documentarys_A = documentary_divs.get(i).selectFirst("a");
                documentarys.put("href",documentarys_A.attr("href"));
                if ("".equalsIgnoreCase(documentarys_A.selectFirst("img").attr("lz_src"))){
                    documentarys.put("imgSrc",documentarys_A.selectFirst("img").attr("lz_next"));
                }
                else{
                    documentarys.put("imgSrc",documentarys_A.selectFirst("img").attr("lz_src"));
                }

                documentarys.put("timeLength",documentarys_A.selectFirst("div.figure_caption").text()
                        .replaceAll(" ",""));
                Element innerDiv = documentary_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row");
                documentarys.put("text",innerDiv.selectFirst("a").text());
                documentarys.put("sub_text",innerDiv.selectFirst("div").text());
                documentary_list.add(documentarys);
            }
            catch (Exception e){
                //
            }
        }

        //英美剧
        Elements britishAmericanDrama_divs = document.select("div.mod_figure.mod_figure_v_default.mod_figure_v_default_1row.mod_figure_v_default.mod_figure_scroll")
                .get(5).select("div.list_item");
        List<Map<String, Object>> britishAmericanDrama_list = new ArrayList<Map<String, Object>>();
        map.put("britishAmericanDrama", britishAmericanDrama_list);
        for (int i=0;i<britishAmericanDrama_divs.size();i++){
            try {
                Map<String,Object> britishAmericanDramas = new HashMap<String, Object>();

                Element britishAmericanDrama_A = britishAmericanDrama_divs.get(i).selectFirst("a");
                britishAmericanDramas.put("href",britishAmericanDrama_A.attr("href"));
                if ("".equalsIgnoreCase(britishAmericanDrama_A.selectFirst("img").attr("lz_src"))){
                    britishAmericanDramas.put("imgSrc",britishAmericanDrama_A.selectFirst("img").attr("lz_next"));
                }
                else{
                    britishAmericanDramas.put("imgSrc",britishAmericanDrama_A.selectFirst("img").attr("lz_src"));
                }

                britishAmericanDramas.put("jiNumber",britishAmericanDrama_A.selectFirst("div.figure_caption").text()
                        .replaceAll(" ",""));
                Element innerDiv = britishAmericanDrama_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row");
                britishAmericanDramas.put("text",innerDiv.selectFirst("a").text());
                britishAmericanDramas.put("sub_text",innerDiv.selectFirst("div").text());
                britishAmericanDrama_list.add(britishAmericanDramas);
            }
            catch (Exception e ){
                //
            }
        }

        //泰剧
        Elements thaiDrama_divs = document.select("div.mod_figure.mod_figure_h_default.mod_figure_h_default_1row.mod_figure_h_default.mod_figure_scroll")
                .get(5).select("div.list_item");
        List<Map<String, Object>> thaiDrama_list = new ArrayList<Map<String, Object>>();
        map.put("thaiDrama", thaiDrama_list);

        for (int i=0;i<thaiDrama_divs.size();i++){
            try {
                Map<String,Object> thaiDramas = new HashMap<String, Object>();

                Element thaiDrama_A = thaiDrama_divs.get(i).selectFirst("a");
                thaiDramas.put("href",thaiDrama_A.attr("href"));
                if ("".equalsIgnoreCase(thaiDrama_A.selectFirst("img").attr("lz_src"))){
                    thaiDramas.put("imgSrc",thaiDrama_A.selectFirst("img").attr("lz_next"));
                }
                else{
                    thaiDramas.put("imgSrc",thaiDrama_A.selectFirst("img").attr("lz_src"));
                }

                thaiDramas.put("updateTo",thaiDrama_A.selectFirst("div.figure_caption").text()
                        .replaceAll(" ",""));
                Element innerDiv = thaiDrama_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row");
                thaiDramas.put("text",innerDiv.selectFirst("a").text());
                thaiDramas.put("sub_text",innerDiv.selectFirst("div").text());
                thaiDrama_list.add(thaiDramas);
            }
            catch (Exception e ){
                //
            }
        }

        //体育资讯
        Elements sports_divs = document.select("div.mod_figure.mod_figure_h_default.mod_figure_h_default_1row.mod_figure_h_default.mod_figure_scroll")
                .get(16).select("div.list_item");
        List<Map<String, Object>> sports_list = new ArrayList<Map<String, Object>>();
        map.put("sports", sports_list);

        for (int i=0;i<sports_divs.size();i++){
            try {
                Map<String,Object> sports = new HashMap<String, Object>();

                Element sports_A = sports_divs.get(i).selectFirst("a");
                sports.put("href",sports_A.attr("href"));
                if ("".equalsIgnoreCase(sports_A.selectFirst("img").attr("lz_src"))){
                    sports.put("imgSrc",sports_A.selectFirst("img").attr("lz_next"));
                }
                else{
                    sports.put("imgSrc",sports_A.selectFirst("img").attr("lz_src"));
                }

                Element innerDiv = sports_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row");
                sports.put("text",innerDiv.selectFirst("a").text());
                sports.put("sub_text",innerDiv.selectFirst("div").text());
                sports_list.add(sports);
            }
            catch (Exception e ){
                //
            }
        }

        //音乐·演唱会
        System.out.println(document.select("div.mod_figure.mod_figure_h_default.mod_figure_h_default_1row.mod_figure_h_default.mod_figure_scroll").size());
        Elements music_divs = document.select("div.mod_figure.mod_figure_h_default.mod_figure_h_default_1row.mod_figure_h_default.mod_figure_scroll")
                .get(7).select("div.list_item");
        List<Map<String, Object>> music_list = new ArrayList<Map<String, Object>>();
        map.put("music", music_list);

        for (int i=0;i<music_divs.size();i++){
            try {
                Map<String,Object> musics = new HashMap<String, Object>();

                Element music_A = music_divs.get(i).selectFirst("a");
                musics.put("href",music_A.attr("href"));
                if ("".equalsIgnoreCase(music_A.selectFirst("img").attr("lz_src"))){
                    musics.put("imgSrc",music_A.selectFirst("img").attr("lz_next"));
                }
                else{
                    musics.put("imgSrc",music_A.selectFirst("img").attr("lz_src"));
                }
                Element innerDiv = music_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row");
                musics.put("text",innerDiv.selectFirst("a").text());
                musics.put("sub_text",innerDiv.selectFirst("div").text());
                music_list.add(musics);
            }
            catch (Exception e ){
                //
            }
        }

        //娱乐热点
        Elements entertainment_divs = document.select("div.mod_figure.mod_figure_h_default.mod_figure_h_default_1row.mod_figure_h_default.mod_figure_scroll")
                .get(8).select("div.list_item");
        List<Map<String, Object>> entertainment_list = new ArrayList<Map<String, Object>>();
        map.put("entertainment", entertainment_list);

        for (int i=0;i<entertainment_divs.size();i++){
            try {
                Map<String,Object> entertainments = new HashMap<String, Object>();

                Element entertainment_A = entertainment_divs.get(i).selectFirst("a");
                entertainments.put("href",entertainment_A.attr("href"));
                if ("".equalsIgnoreCase(entertainment_A.selectFirst("img").attr("lz_src"))){
                    entertainments.put("imgSrc",entertainment_A.selectFirst("img").attr("lz_next"));
                }
                else{
                    entertainments.put("imgSrc",entertainment_A.selectFirst("img").attr("lz_src"));
                }
                entertainments.put("timeLength",entertainment_A.selectFirst("div.figure_caption").
                        text().replaceAll(" ",""));
                Element innerDiv = entertainment_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row");
                entertainments.put("text",innerDiv.selectFirst("a").text());
                entertainments.put("sub_text",innerDiv.selectFirst("div").text());
                entertainment_list.add(entertainments);
            }
            catch (Exception e ){
                //
            }
        }

        //旅游精选
        Elements travel_divs = document.select("div.mod_figure.mod_figure_h_default.mod_figure_h_default_1row.mod_figure_h_default.mod_figure_scroll")
                .get(9).select("div.list_item");
        List<Map<String, Object>> travel_list = new ArrayList<Map<String, Object>>();
        map.put("travel", travel_list);

        for (int i=0;i<travel_divs.size();i++){
            try {
                Map<String,Object> travels = new HashMap<String, Object>();

                Element travel_A = travel_divs.get(i).selectFirst("a");
                travels.put("href",travel_A.attr("href"));
                if ("".equalsIgnoreCase(travel_A.selectFirst("img").attr("lz_src"))){
                    travels.put("imgSrc",travel_A.selectFirst("img").attr("lz_next"));
                }
                else{
                    travels.put("imgSrc",travel_A.selectFirst("img").attr("lz_src"));
                }
                travels.put("timeLength",travel_A.selectFirst("div.figure_caption").
                        text().replaceAll(" ",""));
                Element innerDiv = travel_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row");
                travels.put("text",innerDiv.selectFirst("a").text());
                travels.put("sub_text",innerDiv.selectFirst("div").text());
                travel_list.add(travels);
            }
            catch (Exception e ){
                //
            }
        }

        //时尚热度榜
        Elements fashion_divs = document.select("div.mod_figure.mod_figure_h_default.mod_figure_h_default_1row.mod_figure_h_default.mod_figure_scroll")
                .get(10).select("div.list_item");
        List<Map<String, Object>> fashion_list = new ArrayList<Map<String, Object>>();
        map.put("fashion", fashion_list);

        for (int i=0;i<fashion_divs.size();i++){
            try {
                Map<String,Object> fashions = new HashMap<String, Object>();

                Element fashion_A = fashion_divs.get(i).selectFirst("a");
                fashions.put("href",fashion_A.attr("href"));
                if ("".equalsIgnoreCase(fashion_A.selectFirst("img").attr("lz_src"))){
                    fashions.put("imgSrc",fashion_A.selectFirst("img").attr("lz_next"));
                }
                else{
                    fashions.put("imgSrc",fashion_A.selectFirst("img").attr("lz_src"));
                }
                fashions.put("timeLength",fashion_A.selectFirst("div.figure_caption").
                        text().replaceAll(" ",""));
                Element innerDiv = fashion_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row");
                fashions.put("text",innerDiv.selectFirst("a").text());
                fashions.put("sub_text",innerDiv.selectFirst("div").text());
                fashion_list.add(fashions);
            }
            catch (Exception e ){
                //
            }
        }

        //精品游戏
        Elements game_divs = document.select("div.mod_figure.mod_figure_h_default.mod_figure_h_default_1row.mod_figure_h_default.mod_figure_scroll")
                .get(11).select("div.list_item");
        List<Map<String, Object>> game_list = new ArrayList<Map<String, Object>>();
        map.put("game", game_list);

        for (int i=0;i<game_divs.size();i++){
            try {
                Map<String,Object> games = new HashMap<String, Object>();

                Element game_A = game_divs.get(i).selectFirst("a");
                games.put("href",game_A.attr("href"));
                if ("".equalsIgnoreCase(game_A.selectFirst("img").attr("lz_src"))){
                    games.put("imgSrc",game_A.selectFirst("img").attr("lz_next"));
                }
                else{
                    games.put("imgSrc",game_A.selectFirst("img").attr("lz_src"));
                }
                games.put("timeLength",game_A.selectFirst("div.figure_caption").
                        text().replaceAll(" ",""));
                Element innerDiv = game_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row");
                games.put("text",innerDiv.selectFirst("a").text());
                games.put("sub_text",innerDiv.selectFirst("div").text());
                game_list.add(games);
            }
            catch (Exception e ){
                //
            }
        }

        //最强笑点
        Elements laugh_divs = document.select("div.mod_figure.mod_figure_h_default.mod_figure_h_default_1row.mod_figure_h_default.mod_figure_scroll")
                .get(12).select("div.list_item");
        List<Map<String, Object>> laugh_list = new ArrayList<Map<String, Object>>();
        map.put("laugh", laugh_list);

        for (int i=0;i<laugh_divs.size();i++){
            try {
                Map<String,Object> laughs = new HashMap<String, Object>();

                Element laugh_A = laugh_divs.get(i).selectFirst("a");
                laughs.put("href",laugh_A.attr("href"));
                if ("".equalsIgnoreCase(laugh_A.selectFirst("img").attr("lz_src"))){
                    laughs.put("imgSrc",laugh_A.selectFirst("img").attr("lz_next"));
                }
                else{
                    laughs.put("imgSrc",laugh_A.selectFirst("img").attr("lz_src"));
                }
                laughs.put("timeLength",laugh_A.selectFirst("div.figure_caption").
                        text().replaceAll(" ",""));
                Element innerDiv = laugh_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row");
                laughs.put("text",innerDiv.selectFirst("a").text());
                laughs.put("sub_text",innerDiv.selectFirst("div").text());
                laugh_list.add(laughs);
            }
            catch (Exception e ){
                //
            }
        }

        //母婴常识
        Elements motherAndInfant_divs = document.select("div.mod_figure.mod_figure_h_default.mod_figure_h_default_1row.mod_figure_h_default.mod_figure_scroll")
                .get(13).select("div.list_item");
        List<Map<String, Object>> motherAndInfant_list = new ArrayList<Map<String, Object>>();
        map.put("motherAndInfant", motherAndInfant_list);

        for (int i=0;i<motherAndInfant_divs.size();i++){
            try {
                Map<String,Object> motherAndInfants = new HashMap<String, Object>();

                Element motherAndInfant_A = motherAndInfant_divs.get(i).selectFirst("a");
                motherAndInfants.put("href",motherAndInfant_A.attr("href"));
                if ("".equalsIgnoreCase(motherAndInfant_A.selectFirst("img").attr("lz_src"))){
                    motherAndInfants.put("imgSrc",motherAndInfant_A.selectFirst("img").attr("lz_next"));
                }
                else{
                    motherAndInfants.put("imgSrc",motherAndInfant_A.selectFirst("img").attr("lz_src"));
                }
                motherAndInfants.put("timeLength",motherAndInfant_A.selectFirst("div.figure_caption").
                        text().replaceAll(" ",""));
                Element innerDiv = motherAndInfant_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row");
                motherAndInfants.put("text",innerDiv.selectFirst("a").text());
                motherAndInfants.put("sub_text",innerDiv.selectFirst("div").text());
                motherAndInfant_list.add(motherAndInfants);
            }
            catch (Exception e ){
                //
            }
        }

        //生活资讯
        Elements life_divs = document.select("div.mod_figure.mod_figure_h_default.mod_figure_h_default_1row.mod_figure_h_default.mod_figure_scroll")
                .get(14).select("div.list_item");
        List<Map<String, Object>> life_list = new ArrayList<Map<String, Object>>();
        map.put("life", life_list);

        for (int i=0;i<life_divs.size();i++){
            try {
                Map<String,Object>lifes = new HashMap<String, Object>();

                Element life_A = life_divs.get(i).selectFirst("a");
                lifes.put("href",life_A.attr("href"));
                if ("".equalsIgnoreCase(life_A.selectFirst("img").attr("lz_src"))){
                    lifes.put("imgSrc",life_A.selectFirst("img").attr("lz_next"));
                }
                else{
                    lifes.put("imgSrc",life_A.selectFirst("img").attr("lz_src"));
                }
                lifes.put("timeLength",life_A.selectFirst("div.figure_caption").
                        text().replaceAll(" ",""));
                Element innerDiv = life_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row");
                lifes.put("text",innerDiv.selectFirst("a").text());
                lifes.put("sub_text",innerDiv.selectFirst("div").text());
                life_list.add(lifes);
            }
            catch (Exception e ){
                //
            }
        }

        //星座聚焦
        try {
            Elements constellation_divs = document.select("div.mod_figure.mod_figure_h_default.mod_figure_h_default_1row.mod_figure_h_default.mod_figure_scroll")
                    .get(15).select("div.list_item");
            List<Map<String, Object>> constellation_list = new ArrayList<Map<String, Object>>();
            map.put("constellation", constellation_list);

            for (int i=0;i<constellation_divs.size();i++){
                try {
                    Map<String,Object> constellations = new HashMap<String, Object>();

                    Element constellation_A = constellation_divs.get(i).selectFirst("a");
                    constellations.put("href",constellation_A.attr("href"));
                    if ("".equalsIgnoreCase(constellation_A.selectFirst("img").attr("lz_src"))){
                        constellations.put("imgSrc",constellation_A.selectFirst("img").attr("lz_next"));
                    }
                    else{
                        constellations.put("imgSrc",constellation_A.selectFirst("img").attr("lz_src"));
                    }
                    constellations.put("timeLength",constellation_A.selectFirst("div.figure_caption").
                            text().replaceAll(" ",""));
                    Element innerDiv = constellation_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row");
                    constellations.put("text",innerDiv.selectFirst("a").text());
                    constellations.put("sub_text",innerDiv.selectFirst("div").text());
                    constellation_list.add(constellations);
                }
                catch (Exception e ){
                    //
                }
            }
        }
        catch (Exception e){
            //
        }

        //汽车资讯
        try {
            Elements car_divs = document.select("div.mod_figure.mod_figure_h_default.mod_figure_h_default_1row.mod_figure_h_default.mod_figure_scroll")
                    .get(16).select("div.list_item");
            List<Map<String, Object>> car_list = new ArrayList<Map<String, Object>>();
            map.put("car", car_list);

            for (int i=0;i<car_divs.size();i++){
                try {
                    Map<String,Object> cars = new HashMap<String, Object>();

                    Element car_A = car_divs.get(i).selectFirst("a");
                    cars.put("href",car_A.attr("href"));
                    if ("".equalsIgnoreCase(car_A.selectFirst("img").attr("lz_src"))){
                        cars.put("imgSrc",car_A.selectFirst("img").attr("lz_next"));
                    }
                    else{
                        cars.put("imgSrc",car_A.selectFirst("img").attr("lz_src"));
                    }
                    cars.put("timeLength",car_A.selectFirst("div.figure_caption").
                            text().replaceAll(" ",""));
                    Element innerDiv = car_divs.get(i).selectFirst("div.figure_detail.figure_detail_two_row");
                    cars.put("text",innerDiv.selectFirst("a").text());
                    cars.put("sub_text",innerDiv.selectFirst("div").text());
                    car_list.add(cars);
                }
                catch (Exception e ){
                    //
                }
            }
        }
        catch (Exception e){
            //
        }
        return map;

    }

}
