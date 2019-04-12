package biz.service.wechat;

import biz.filter.SysContext;
import model.wechat.ArticleItem;
import model.wechat.InMsgEntity;
import model.wechat.OutMsgEntity;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import utils.HttpRequestPostUtil;
import utils.HttpRequestUtil;
import utils.UUID;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

@Service
@Lazy
@Scope("singleton")
public class CheckSignatureService {

    static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    public boolean checkToken(String signature,
                              String timestamp,
                              String nonce,
                              String echostr){
        String token = "ljxwtl";
        String strs [] = {token,timestamp,nonce};

        //对字符串数组进行字典排序
        Arrays.sort(strs);


        //拼接排序后的字符串数组
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<strs.length;i++){
            stringBuilder.append(strs[i]);
        }

        //对拼接的字符串进行SHA-1加密
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] digestBytes = messageDigest.digest(stringBuilder.toString().getBytes());

        //将字节数组进行转换成十六进制转换
        char digist[] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        String result = "";
        for (int i=0;i<digestBytes.length;i++){
            char tmpChars [] = new char[2];
            tmpChars[0] = digist[(digestBytes[i]>>> 4) & 0X0F];
            tmpChars[1] = digist[digestBytes[i] & 0X0F];
            result += new String(tmpChars);
        }
        logger.info("#########################################");
        logger.info("传递的signature："+signature);
        logger.info("加密后的signature："+result);
        logger.info("#########################################");

        return result != null ? result.equals(signature.toUpperCase()) : false;
    }

    /**
     * 处理发送过来的post消息
     * @param inMsgEntity
     * @return
     * @throws IOException
     */
    public Object dealMultipleMessage(InMsgEntity inMsgEntity) throws IOException {

        switch (inMsgEntity.getMsgType()){
            case "text":
                String content = inMsgEntity.getContent();

                if ("电视剧".equalsIgnoreCase(content)){
                    OutMsgEntity outMsgEntity = new OutMsgEntity();
                    outMsgEntity.setToUserName(inMsgEntity.getFromUserName());
                    outMsgEntity.setFromUserName(inMsgEntity.getToUserName());
                    outMsgEntity.setArticleCount(8);
                    outMsgEntity.setCreateTime(new Date().getTime());
                    outMsgEntity.setMsgType("news");
                    ArticleItem[] articles = new ArticleItem[8];

                    ArticleItem article_top = new ArticleItem();
                    article_top.setTitle("祥龙检索电视剧，千度电视剧");
                    article_top.setDescription("能够提供很好的电视剧检索平台");
                    article_top.setPicUrl("http://ljxwtl.cn/images/logo/index_logo.png");
                    article_top.setUrl("http://ljxwtl.cn/m/tvResult?pageIndex=1");
                    articles[0] = article_top;

                    String json = HttpRequestUtil.requestHttp("http://www.ljxwtl.cn/tvPlayResult?pageIndex=1","utf-8","GET");
                    for (int i=1;i<8;i++){
                        JSONObject jsonObject = JSONObject.fromObject(json).getJSONArray("tvList").getJSONObject(i);
                        ArticleItem article = new ArticleItem();
                        article.setTitle(jsonObject.getString("title"));
                        article.setDescription("好看的电视剧");
                        article.setPicUrl("http:"+jsonObject.getString("imgSrc"));
                        article.setUrl(jsonObject.getString("href"));
                        articles[i] = article;
                    }
                    outMsgEntity.setItem(articles);

                    return outMsgEntity;
                }
                else if ("电影".equalsIgnoreCase(content)){
                    OutMsgEntity outMsgEntity = new OutMsgEntity();
                    outMsgEntity.setToUserName(inMsgEntity.getFromUserName());
                    outMsgEntity.setFromUserName(inMsgEntity.getToUserName());
                    outMsgEntity.setArticleCount(8);
                    outMsgEntity.setCreateTime(new Date().getTime());
                    outMsgEntity.setMsgType("news");
                    ArticleItem [] articles = new ArticleItem[8];
                    /**
                     * 添加第一个图文头
                     */
                    ArticleItem article_top = new ArticleItem("祥龙检索电影，千度电影",
                            "能够提供很好的电影检索平台",
                            "http://ljxwtl.cn/images/logo/index_logo.png",
                            "http://ljxwtl.cn/m/movieResult?pageIndex=1");
                    articles[0] = article_top;
                    String json = HttpRequestUtil.requestHttp("http://www.ljxwtl.cn/moviePlayResult?pageIndex=1","utf-8","GET");

                    for (int i=1;i<8;i++){
                        JSONObject jsonObject = JSONObject.fromObject(json).getJSONArray("movieList").getJSONObject(i);
                        ArticleItem article = new ArticleItem(jsonObject.getString("title"),
                                "好看的电影",
                                "http:"+jsonObject.getString("imgSrc"),
                                jsonObject.getString("href"));
                        articles[i] = article;
                    }
                    outMsgEntity.setItem(articles);
                    return outMsgEntity;
                }
                else if ("app".equalsIgnoreCase(content)){
                    OutMsgEntity outMsgEntity = new OutMsgEntity();
                    outMsgEntity.setToUserName(inMsgEntity.getFromUserName());
                    outMsgEntity.setFromUserName(inMsgEntity.getToUserName());
                    outMsgEntity.setArticleCount(8);
                    outMsgEntity.setCreateTime(new Date().getTime());
                    outMsgEntity.setMsgType("news");
                    ArticleItem[] articles = new ArticleItem[8];
                    /**
                     * 添加第一个图文头
                     */
                    ArticleItem article_top = new ArticleItem("祥龙检索app，千度app",
                            "能够提供很好的app检索平台",
                            "http://ljxwtl.cn/images/logo/index_logo.png",
                            "http://ljxwtl.cn/m/appResult?categoryId=-10&pageSize=60&pageContext=1");
                    articles[0] = article_top;
                    String json = HttpRequestUtil.requestHttp("http://www.ljxwtl.cn/getClassifyApps?categoryId=-10&pageSize=60&pageContext=1","utf-8","GET");

                    for (int i=1;i<8;i++){
                        JSONObject jsonObject = JSONObject.fromObject(json).getJSONArray("apps").getJSONObject(i);
                        ArticleItem article = new ArticleItem(jsonObject.getString("appName"),
                                "好用的app",
                                jsonObject.getString("iconUrl"),
                                jsonObject.getString("apkUrl"));
                        articles[i] = article;
                    }
                    outMsgEntity.setItem(articles);
                    return outMsgEntity;
                }
                else if ("图片".equalsIgnoreCase(content)){
                    OutMsgEntity outMsgEntity = new OutMsgEntity();
                    outMsgEntity.setToUserName(inMsgEntity.getFromUserName());
                    outMsgEntity.setFromUserName(inMsgEntity.getToUserName());
                    outMsgEntity.setArticleCount(8);
                    outMsgEntity.setCreateTime(new Date().getTime());
                    outMsgEntity.setMsgType("news");
                    ArticleItem[] articles = new ArticleItem[8];
                    /**
                     * 添加第一个图文头
                     */
                    ArticleItem article_top = new ArticleItem("祥龙检索图片，千度图片",
                            "能够提供很好的图片检索平台",
                            "http://ljxwtl.cn/images/logo/index_logo.png",
                            "http://ljxwtl.cn/m/imageResult?keyword=%E7%BE%8E%E5%A5%B3&pageIndex=1&pageSize=60");
                    articles[0] = article_top;
                    String json = HttpRequestUtil.requestHttp("http://www.ljxwtl.cn/getAllMatchingImages?keyword=美女&pageIndex=1&pageSize=60","utf-8","GET");

                    for (int i=1;i<8;i++){
                        JSONObject jsonObject = JSONArray.fromObject(json).getJSONObject(i);
                        ArticleItem article = new ArticleItem(jsonObject.getString("fromPageTitleEnc"),
                                "好看的图片",
                                jsonObject.getString("middleURL"),
                                jsonObject.getString("middleURL"));
                        articles[i] = article;
                    }
                    outMsgEntity.setItem(articles);
                    return outMsgEntity;
                }
                else if ("音乐".equalsIgnoreCase(content)){
                    OutMsgEntity outMsgEntity = new OutMsgEntity();
                    outMsgEntity.setToUserName(inMsgEntity.getFromUserName());
                    outMsgEntity.setFromUserName(inMsgEntity.getToUserName());
                    outMsgEntity.setArticleCount(8);
                    outMsgEntity.setCreateTime(new Date().getTime());
                    outMsgEntity.setMsgType("news");
                    ArticleItem []articles = new ArticleItem[8];
                    /**
                     * 添加第一个图文头
                     */
                    ArticleItem article_top = new ArticleItem("祥龙检索音乐，千度音乐",
                            "能够提供很好的音乐检索平台",
                            "http://ljxwtl.cn/images/logo/index_logo.png",
                            "http://ljxwtl.cn/m/musicResult?keyword=%E9%9F%B3%E4%B9%90&pageIndex=1&pageSize=20");
                    articles[0] = article_top;
                    String json = HttpRequestUtil.requestHttp("http://www.ljxwtl.cn/getAllMatchingMusics?keyword=音乐&pageIndex=1&pageSize=20","utf-8","GET");

                    for (int i=1;i<8;i++){
                        JSONObject jsonObject = JSONArray.fromObject(json).getJSONObject(i);
                        ArticleItem article = new ArticleItem(jsonObject.getString("name"),
                                "好用的音乐",
                                "http:"+jsonObject.getString("pic"),
                                jsonObject.getString("url"));
                        articles[i] = article;
                    }
                    outMsgEntity.setItem(articles);
                    return outMsgEntity;
                }
                else if ("迅雷下载".equalsIgnoreCase(content)){
                    OutMsgEntity outMsgEntity = new OutMsgEntity();
                    outMsgEntity.setToUserName(inMsgEntity.getFromUserName());
                    outMsgEntity.setFromUserName(inMsgEntity.getToUserName());
                    outMsgEntity.setArticleCount(8);
                    outMsgEntity.setCreateTime(new Date().getTime());
                    outMsgEntity.setMsgType("news");
                    ArticleItem[] articles = new ArticleItem[8];
                    /**
                     * 添加第一个图文头
                     */
                    ArticleItem article_top = new ArticleItem("祥龙检索迅雷下载，千度迅雷下载",
                            "能够提供很好的迅雷下载检索平台",
                            "http://ljxwtl.cn/images/logo/index_logo.png",
                            "http://ljxwtl.cn/m/getThunderPagingResult?classify=%E6%9C%80%E6%96%B0%E5%BD%B1%E7%89%87&pageIndex=1");
                    articles[0] = article_top;
                    String json = HttpRequestUtil.requestHttp("http://www.ljxwtl.cn/getPagingNewMovies?pageIndex=1","gb2312","GET");

                    for (int i=1;i<8;i++){
                        JSONObject jsonObject = JSONArray.fromObject(json).getJSONObject(i);
                        ArticleItem article = new ArticleItem(jsonObject.getString("title"),
                                "好用的迅雷下载",
                                "https://www.xunlei.com/v2018/dist/header/xl-logo2x.png?h=35e6a0",
                                jsonObject.getString("downloadPageUrl"));
                        articles[i] = article;
                    }
                    outMsgEntity.setItem(articles);
                    return outMsgEntity;
                }
                else if ("百度云盘".equalsIgnoreCase(content)){
                    OutMsgEntity outMsgEntity = new OutMsgEntity();
                    outMsgEntity.setToUserName(inMsgEntity.getFromUserName());
                    outMsgEntity.setFromUserName(inMsgEntity.getToUserName());
                    outMsgEntity.setArticleCount(8);
                    outMsgEntity.setCreateTime(new Date().getTime());
                    outMsgEntity.setMsgType("news");
                    ArticleItem[] articles = new ArticleItem[8];
                    /**
                     * 添加第一个图文头
                     */
                    ArticleItem article_top = new ArticleItem("祥龙检索百度云盘，千度百度云盘",
                            "能够提供很好的百度云盘检索平台",
                            "http://ljxwtl.cn/images/logo/index_logo.png",
                            "http://ljxwtl.cn/m/baiduyunwangpanSearchResult?keyword=%E6%95%B0%E6%8D%AE%E5%BA%93&pageIndex=1");
                    articles[0] = article_top;
                    String json = HttpRequestUtil.requestHttp("http://www.ljxwtl.cn/getSearchPagingBaiduYunSources?keyword=数据库&pageIndex=1","utf-8","GET");

                    System.out.println(json);
                    for (int i=1;i<8;i++){
                        JSONObject jsonObject = JSONArray.fromObject(json).getJSONObject(i);
                        ArticleItem article = new ArticleItem(jsonObject.getString("title"),
                                "好用的百度云盘",
                                "https://pan.baidu.com/box-static/disk-header/header/img/logo.png?t=1550732425176",
                                jsonObject.getString("link"));
                        articles[i] = article;
                    }
                    outMsgEntity.setItem(articles);
                    return outMsgEntity;
                }
                else if (content.length() > 20){
                    OutMsgEntity outMsgEntity_voice = new OutMsgEntity();
                    outMsgEntity_voice.setToUserName(inMsgEntity.getFromUserName());
                    outMsgEntity_voice.setFromUserName(inMsgEntity.getToUserName());
                    outMsgEntity_voice.setCreateTime(new Date().getTime());
                    outMsgEntity_voice.setMsgType("voice");
                    outMsgEntity_voice.setMedia_id_voice(new String []{
                            JSONObject.fromObject(uploadResource("https://fanyi.baidu.com/gettts?lan=zh&text="+ URLEncoder.encode(inMsgEntity.getContent(),"utf-8") +"&spd=5&source=web","voice")).getString("media_id")
                    });
                    return outMsgEntity_voice;
                }
                String params = "{\n" +
                        "\t\"reqType\":0,\n" +
                        "    \"perception\": {\n" +
                        "        \"inputText\": {\n" +
                        "            \"text\": \""+inMsgEntity.getContent()+"\"\n" +
                        "        }\n" +
                        "    },\n" +
                        "    \"userInfo\": {\n" +
                        "        \"apiKey\": \"90e06f2fdde149c4b0fceef0ad255af7\",\n" +
                        "        \"userId\": \"1234567890\"\n" +
                        "    }\n" +
                        "}";

                String url = "http://openapi.tuling123.com/openapi/api/v2";

                String json = HttpRequestPostUtil.requestHttpByJSONObject(url,"utf-8",JSONObject.fromObject(params),null);

                String result = JSONObject.fromObject(json).getJSONArray("results").
                        getJSONObject(0).
                        getJSONObject("values").getString("text");


                if (result != null){
                    OutMsgEntity outMsgEntity = new OutMsgEntity();
                    outMsgEntity.setFromUserName(inMsgEntity.getToUserName());
                    outMsgEntity.setToUserName(inMsgEntity.getFromUserName());
                    outMsgEntity.setCreateTime(new Date().getTime());
                    outMsgEntity.setMsgType("text");
                    outMsgEntity.setContent(result);
                    return outMsgEntity;
                }
                break;
            case "image":
                OutMsgEntity outMsgEntity = new OutMsgEntity();
                outMsgEntity.setToUserName(inMsgEntity.getFromUserName());
                outMsgEntity.setFromUserName(inMsgEntity.getToUserName());
                outMsgEntity.setCreateTime(new Date().getTime());
                outMsgEntity.setMsgType("image");
                outMsgEntity.setMediaId(new String []{
                        JSONObject.fromObject(uploadResource(inMsgEntity.getPicUrl(),"image")).getString("media_id")
                });
                return outMsgEntity;
        }
        return null;
    }

    /**
     * 将文本变成语音资源
     * @param text mediaId
     * @return
     */
    private String fanyiTextToYuyin(String text){
        String url = null;
        try {
            url = "https://fanyi.baidu.com/gettts?lan=zh&text="+ URLEncoder.encode(text,"utf-8") +"&spd=5&source=web";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte [] buffer = new byte[1024];
        int length = -1;
        StringBuffer content = new StringBuffer();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedInputStream is = null;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();

            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.111 Safari/537.3");
            is = new BufferedInputStream(urlConnection.getInputStream());
            do{
                length = is.read(buffer);
                if (length !=-1){
                    //content.append(new String(buffer,0,length,encoding));
                    outputStream.write(buffer,0,length);
                }
            }while(length!=-1);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        //上传语音资源到微信公众号
        String weixinUploadurl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
        weixinUploadurl = weixinUploadurl.replaceAll("ACCESS_TOKEN", SysContext.ACCESS_TOKEN).replaceAll("TYPE","voice");

        String result = HttpRequestPostUtil.upload(weixinUploadurl,
                outputStream.toByteArray(),
                UUID.getUUID()+".mp3",
                "utf-8",
                new JSONObject());
        return result;
    }

    /**
     * 上传图片
     * 返回一个json数据，包含media_id
     * @return
     */
    public String uploadResource(String url,String type){
        String weixinUploadurl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
        weixinUploadurl = weixinUploadurl.replaceAll("ACCESS_TOKEN", SysContext.ACCESS_TOKEN).replaceAll("TYPE",type);
        String fileName = null;
        switch (type){
            case "image":
                fileName = UUID.getUUID() + ".png";
                break;
            case "voice":
                fileName = UUID.getUUID() + ".mp3";
                break;
            case "video":
                fileName = UUID.getUUID() + ".mp4";
                break;
        }
        return HttpRequestPostUtil.upload(weixinUploadurl,HttpRequestPostUtil.exchangeMusicsUrl(url),fileName,"utf-8",new JSONObject());
    }

    /**
     * 获得上传给微信服务器的资源，根据media_id
     * @param media_id
     * @return
     */
    public String getUploadResource(String media_id){
        String weixiDownloadResourceUrl = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
        weixiDownloadResourceUrl = weixiDownloadResourceUrl.replaceAll("ACCESS_TOKEN",SysContext.ACCESS_TOKEN).replaceAll("MEDIA_ID",media_id);
        return HttpRequestUtil.requestHttp(weixiDownloadResourceUrl,"utf-8","GET");
    }
}
