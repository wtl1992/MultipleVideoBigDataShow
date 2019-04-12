package biz.service.dianyingtiantang;

import model.dianyingtiantang.DownloadSource;
import model.dianyingtiantang.Information;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import utils.HttpRequestPostUtil;
import utils.HttpRequestUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Service
@Lazy
@Scope("singleton")
public class DianYingTianTangSearchService {

    /**
     * 获得最新影片
     * @param pageIndex
     * @return
     */
    public List<Information> getPagingNewMovies(String pageIndex){
        List<Information> informations = new ArrayList<Information>();

        String url = "https://www.ygdy8.com/html/gndy/dyzz/list_23_"+pageIndex+".html";

        String html = HttpRequestUtil.requestHttp(url,"gb2312","GET");

        if (html != null && !"".equalsIgnoreCase(html)) {
            Document document = Jsoup.parse(html);
            Elements newMoviesTables = document.select("div.co_content8 ul table");
            for (int i = 0; i < newMoviesTables.size(); i++) {
                Element table = newMoviesTables.get(i);
                informations.add(new Information(table.select("a").size() == 1 ? table.select("a").get(0).text() : table.select("a").get(1).text()
                        , "https://www.ygdy8.com" + (table.select("a").size() == 1  ? table.select("a").get(0).attr("href"): table.select("a").get(1).attr("href"))
                        , table.selectFirst("font").text().split("点击")[0]
                        , table.select("td").get(table.select("td").size() - 1).text()
                        ,Integer.parseInt(document.select("select option").get(document.select("select option").size()-1).text())));
            }
        }
        return informations;
    }

    /**
     * 获得国内电影
     * @param pageIndex
     * @return
     */
    public List<Information> getPagingChinaMovies(String pageIndex){
        List<Information> informations = new ArrayList<Information>();

        String url = "http://www.ygdy8.net/html/gndy/china/list_4_"+pageIndex+".html";

        String html = HttpRequestUtil.requestHttp(url,"gb2312","GET");

        if (html != null && !"".equalsIgnoreCase(html)) {
            Document document = Jsoup.parse(html);
            Elements newMoviesTables = document.select("div.co_content8 ul table");
            for (int i = 0; i < newMoviesTables.size(); i++) {
                Element table = newMoviesTables.get(i);
                informations.add(new Information(table.select("a").size() == 1 ? table.select("a").get(0).text() : table.select("a").get(1).text()
                        , "http://www.ygdy8.net" + (table.select("a").size() == 1  ? table.select("a").get(0).attr("href"): table.select("a").get(1).attr("href"))
                        , table.selectFirst("font").text().split("点击")[0]
                        , table.select("td").get(table.select("td").size() - 1).text()
                        ,Integer.parseInt(document.select("select option").get(document.select("select option").size()-1).text())));
            }
        }
        return informations;
    }


    /**
     * 获得欧美电影
     * @param pageIndex
     * @return
     */
    public List<Information> getPagingEuropeansMovies(String pageIndex){
        List<Information> informations = new ArrayList<Information>();

        String url = "http://www.ygdy8.net/html/gndy/oumei/list_7_"+pageIndex+".html";

        String html = HttpRequestUtil.requestHttp(url,"gb2312","GET");

        if (html != null && !"".equalsIgnoreCase(html)) {
            Document document = Jsoup.parse(html);
            Elements newMoviesTables = document.select("div.co_content8 ul table");
            for (int i = 0; i < newMoviesTables.size(); i++) {
                Element table = newMoviesTables.get(i);
                informations.add(new Information(table.select("a").size() == 1 ? table.select("a").get(0).text() : table.select("a").get(1).text()
                        , "http://www.ygdy8.net" + (table.select("a").size() == 1  ? table.select("a").get(0).attr("href"): table.select("a").get(1).attr("href"))
                        , table.selectFirst("font").text().split("点击")[0]
                        , table.select("td").get(table.select("td").size() - 1).text()
                        ,Integer.parseInt(document.select("select option").get(document.select("select option").size()-1).text())));
            }
        }
        return informations;
    }


    /**
     * 获得华语电视
     * @param pageIndex
     * @return
     */
    public List<Information> getPaginghuaYuTvs(String pageIndex){
        List<Information> informations = new ArrayList<Information>();

        String url = "http://www.ygdy8.net/html/tv/hytv/list_71_"+pageIndex+".html";

        String html = HttpRequestUtil.requestHttp(url,"gb2312","GET");

        if (html != null && !"".equalsIgnoreCase(html)) {
            Document document = Jsoup.parse(html);
            Elements newMoviesTables = document.select("div.co_content8 ul table");
            for (int i = 0; i < newMoviesTables.size(); i++) {
                Element table = newMoviesTables.get(i);
                informations.add(new Information(table.select("a").size() == 1 ? table.select("a").get(0).text() : table.select("a").get(1).text()
                        , "http://www.ygdy8.net" + (table.select("a").size() == 1  ? table.select("a").get(0).attr("href"): table.select("a").get(1).attr("href"))
                        , table.selectFirst("font").text().split("点击")[0]
                        , table.select("td").get(table.select("td").size() - 1).text()
                        ,Integer.parseInt(document.select("select option").get(document.select("select option").size()-1).text())));
            }
        }
        return informations;
    }


    /**
     * 获得日韩电视
     * @param pageIndex
     * @return
     */
    public List<Information> getPagingRiHanTvs(String pageIndex){
        List<Information> informations = new ArrayList<Information>();

        String url = "http://www.ygdy8.net/html/tv/rihantv/list_8_"+pageIndex+".html";

        String html = HttpRequestUtil.requestHttp(url,"gb2312","GET");

        if (html != null && !"".equalsIgnoreCase(html)) {
            Document document = Jsoup.parse(html);
            Elements newMoviesTables = document.select("div.co_content8 ul table");
            for (int i = 0; i < newMoviesTables.size(); i++) {
                Element table = newMoviesTables.get(i);
                informations.add(new Information(table.select("a").size() == 1 ? table.select("a").get(0).text() : table.select("a").get(1).text()
                        , "http://www.ygdy8.net" + (table.select("a").size() == 1  ? table.select("a").get(0).attr("href"): table.select("a").get(1).attr("href"))
                        , table.selectFirst("font").text().split("点击")[0]
                        , table.select("td").get(table.select("td").size() - 1).text()
                        ,Integer.parseInt(document.select("select option").get(document.select("select option").size()-1).text())));
            }
        }
        return informations;
    }

    /**
     * 获得欧美电视
     * @param pageIndex
     * @return
     */
    public List<Information> getPagingOuMeiTvs(String pageIndex){
        List<Information> informations = new ArrayList<Information>();

        String url = "http://www.ygdy8.net/html/tv/oumeitv/list_9_"+pageIndex+".html";

        String html = HttpRequestUtil.requestHttp(url,"gb2312","GET");

        if (html != null && !"".equalsIgnoreCase(html)) {
            Document document = Jsoup.parse(html);
            Elements newMoviesTables = document.select("div.co_content8 ul table");
            for (int i = 0; i < newMoviesTables.size(); i++) {
                Element table = newMoviesTables.get(i);
                informations.add(new Information(table.select("a").size() == 1 ? table.select("a").get(0).text() : table.select("a").get(1).text()
                        , "http://www.ygdy8.net" + (table.select("a").size() == 1  ? table.select("a").get(0).attr("href"): table.select("a").get(1).attr("href"))
                        , table.selectFirst("font").text().split("点击")[0]
                        , table.select("td").get(table.select("td").size() - 1).text()
                        ,Integer.parseInt(document.select("select option").get(document.select("select option").size()-1).text())));
            }
        }
        return informations;
    }


    /**
     * 获得最新综艺
     * @param pageIndex
     * @return
     */
    public List<Information> getPagingNewZongYis(String pageIndex){
        List<Information> informations = new ArrayList<Information>();

        String url = "http://www.ygdy8.net/html/zongyi2013/list_99_"+pageIndex+".html";

        String html = HttpRequestUtil.requestHttp(url,"gb2312","GET");

        if (html != null && !"".equalsIgnoreCase(html)) {
            Document document = Jsoup.parse(html);
            Elements newMoviesTables = document.select("div.co_content8 ul table");
            for (int i = 0; i < newMoviesTables.size(); i++) {
                Element table = newMoviesTables.get(i);
                informations.add(new Information(table.select("a").size() == 1 ? table.select("a").get(0).text() : table.select("a").get(1).text()
                        , "http://www.ygdy8.net" + (table.select("a").size() == 1  ? table.select("a").get(0).attr("href"): table.select("a").get(1).attr("href"))
                        , table.selectFirst("font").text().split("点击")[0]
                        , table.select("td").get(table.select("td").size() - 1).text()
                        ,Integer.parseInt(document.select("select option").get(document.select("select option").size()-1).text())));
            }
        }
        return informations;
    }



    /**
     * 获得旧版综艺
     * @param pageIndex
     * @return
     */
    public List<Information> getPagingOldZongYis(String pageIndex){
        List<Information> informations = new ArrayList<Information>();

        String url = "http://www.ygdy8.net/html/zongyi2013/list_99_"+pageIndex+".html";

        String html = HttpRequestUtil.requestHttp(url,"gb2312","GET");

        if (html != null && !"".equalsIgnoreCase(html)) {
            Document document = Jsoup.parse(html);
            Elements newMoviesTables = document.select("div.co_content8 ul table");
            for (int i = 0; i < newMoviesTables.size(); i++) {
                Element table = newMoviesTables.get(i);
                informations.add(new Information(table.select("a").size() == 1 ? table.select("a").get(0).text() : table.select("a").get(1).text()
                        , "http://www.ygdy8.net" + (table.select("a").size() == 1  ? table.select("a").get(0).attr("href"): table.select("a").get(1).attr("href"))
                        , table.selectFirst("font").text().split("点击")[0]
                        , table.select("td").get(table.select("td").size() - 1).text()
                        ,Integer.parseInt(document.select("select option").get(document.select("select option").size()-1).text())));
            }
        }
        return informations;
    }


    /**
     * 获得动漫
     * @param pageIndex
     * @return
     */
    public List<Information> getPagingDongMans(String pageIndex){
        List<Information> informations = new ArrayList<Information>();

        String url = "http://www.ygdy8.net/html/dongman/list_16_"+pageIndex+".html";

        String html = HttpRequestUtil.requestHttp(url,"gb2312","GET");

        if (html != null && !"".equalsIgnoreCase(html)) {
            Document document = Jsoup.parse(html);
            Elements newMoviesTables = document.select("div.co_content8 ul table");
            for (int i = 0; i < newMoviesTables.size(); i++) {
                Element table = newMoviesTables.get(i);
                informations.add(new Information(table.select("a").size() == 1 ? table.select("a").get(0).text() : table.select("a").get(1).text()
                        , "http://www.ygdy8.net" + (table.select("a").size() == 1  ? table.select("a").get(0).attr("href"): table.select("a").get(1).attr("href"))
                        , table.selectFirst("font").text().split("点击")[0]
                        , table.select("td").get(table.select("td").size() - 1).text()
                        ,Integer.parseInt(document.select("select option").get(document.select("select option").size()-1).text())));
            }
        }
        return informations;
    }

    /**
     * 获得游戏
     * @param pageIndex
     * @return
     */
    public List<Information> getPagingGames(String pageIndex){
        List<Information> informations = new ArrayList<Information>();

        String url = "http://www.ygdy8.net/html/game/list_19_"+pageIndex+".html";

        String html = HttpRequestUtil.requestHttp(url,"gb2312","GET");

        if (html != null && !"".equalsIgnoreCase(html)) {
            Document document = Jsoup.parse(html);
            Elements newMoviesTables = document.select("div.co_content8 ul table");
            for (int i = 0; i < newMoviesTables.size(); i++) {
                Element table = newMoviesTables.get(i);
                informations.add(new Information(table.select("a").size() == 1 ? table.select("a").get(0).text() : table.select("a").get(1).text()
                        , "http://www.ygdy8.net" + (table.select("a").size() == 1  ? table.select("a").get(0).attr("href"): table.select("a").get(1).attr("href"))
                        , table.selectFirst("font").text().split("点击")[0]
                        , table.select("td").get(table.select("td").size() - 1).text()
                        ,Integer.parseInt(document.select("select option").get(document.select("select option").size()-1).text())));
            }
        }
        return informations;
    }


    /**
     * 获得详情下载页
     * @param downloadPageUrl
     * @return
     */
    public List<DownloadSource> getDownloads(String downloadPageUrl){
        List<DownloadSource> downloadSources = new ArrayList<DownloadSource>();

        String html = HttpRequestUtil.requestHttp(downloadPageUrl,"gb2312","GET");

        if (html != null && !"".equalsIgnoreCase(html)) {
            Document document = Jsoup.parse(html);
            Element container = document.selectFirst("div.co_content8 ul");
            DownloadSource downloadSource = new DownloadSource();

            downloadSource.setDescription(container.selectFirst("div#Zoom span > p").html());
            Elements as = document.selectFirst("div#Zoom").select("table a");

            if (as != null && as.size() >= 1){
                List<String> fileNames = new ArrayList<String>();
                List<String> downloadUrls = new ArrayList<String>();

                for (int i=0;i<as.size();i++){
                    fileNames.add(as.get(i).text());
                    Attributes attributes = as.get(i).attributes();

                    Iterator<Attribute> attributeIterator = attributes.iterator();
                    while (attributeIterator.hasNext()){
                        String _url = attributeIterator.next().getValue();
                        downloadUrls.add(_url);
                    }
                }

                downloadSource.setFileNames(fileNames);
                downloadSource.setDownloadUrls(downloadUrls);

                downloadSources.add(downloadSource);
            }
        }
        return downloadSources;
    }

    /**
     * 搜索分页结果
     * @param keyword
     * @param typeid
     * @param pageIndex
     * @return
     */
    public List<Information> getSearchPagingResult(String keyword,
                                             int typeid,
                                             int pageIndex){
        List<Information> informations = new ArrayList<Information>();

        try {
            String url = "http://s.ygdy8.com/plus/so.php?keyword="+URLEncoder.encode(keyword,"gb2312")+"&searchtype=titlekeyword&channeltype=0&orderby=&kwtype=0&pagesize=10&typeid="+typeid+"&PageNo="+pageIndex;

            String html = HttpRequestUtil.requestHttp(url,"gb2312","GET");

            if (html != null && !"".equalsIgnoreCase(html)){
                Document document = Jsoup.parse(html);

                Elements tables = document.select("table[width='100%']");

                if (tables != null && tables.size() > 0){
                    for (int i=0;i<tables.size();i++){
                        try {
                            informations.add(new Information(tables.get(i).selectFirst("a").text()
                                    ,"https://www.ygdy8.com"+tables.get(i).selectFirst("a").attr("href")
                                    ,null
                                    ,tables.get(i).select("td").get(1).text()
                                    ,document.selectFirst("input[name=\"TotalResult\"]") != null ? (int)Math.ceil(Integer.parseInt(document.selectFirst("input[name=\"TotalResult\"]").attr("value")) *1.0 /10.0 ) : 1));
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return informations;
    }
}
