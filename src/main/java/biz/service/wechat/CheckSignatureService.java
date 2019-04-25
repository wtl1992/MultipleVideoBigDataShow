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
                return dealTextMessage(inMsgEntity);
            case "event":
                return dealSubscribeEvent(inMsgEntity);
        }
        return null;
    }

    /**
     * 处理关注公众号后返回的消息
     * @return
     */
    private Object dealSubscribeEvent(InMsgEntity inMsgEntity){
        String fromUserName = inMsgEntity.getToUserName();
        String toUserName = inMsgEntity.getFromUserName();

        OutMsgEntity outMsgEntity = new OutMsgEntity();
        outMsgEntity.setFromUserName(fromUserName);
        outMsgEntity.setToUserName(toUserName);
        outMsgEntity.setCreateTime(new Date().getTime());
        outMsgEntity.setMsgType("news");
        outMsgEntity.setArticleCount(8);

        ArticleItem [] articleItems = new ArticleItem[8];

        String titles [] = new String[]{"千度一下","电视剧","电影","app","图片","音乐","迅雷下载","百度云盘"};
        String urls []= new String [] {
                "http://ljxwtl.cn/index",
                "http://ljxwtl.cn/m/tvResult?pageIndex=1",
                "http://ljxwtl.cn/m/movieResult?pageIndex=1",
                "http://ljxwtl.cn/m/appResult?categoryId=-10&pageSize=60&pageContext=1",
                "http://ljxwtl.cn/m/imageResult?keyword=%E7%BE%8E%E5%A5%B3&pageIndex=1&pageSize=60",
                "http://ljxwtl.cn/m/musicResult?keyword=%E9%9F%B3%E4%B9%90&pageIndex=1&pageSize=20",
                "http://ljxwtl.cn/m/getThunderPagingResult?classify=%E6%9C%80%E6%96%B0%E5%BD%B1%E7%89%87&pageIndex=1",
                "http://ljxwtl.cn/m/baiduyunwangpanSearchResult?keyword=%E6%95%B0%E6%8D%AE%E5%BA%93&pageIndex=1"
        };
        String descriptions [] = new String []{
                "千度一下","电视剧","电影","app","图片","音乐","迅雷下载","百度云盘"
        };

        String picUrls [] = new String[]{
                "https://ljxwtl.cn/images/logo/index_logo.png",
                "https://ljxwtl.cn/images/logo/index_logo.png",
                "https://ljxwtl.cn/images/logo/index_logo.png",
                "https://ljxwtl.cn/images/logo/index_logo.png",
                "https://ljxwtl.cn/images/logo/index_logo.png",
                "https://ljxwtl.cn/images/logo/index_logo.png",
                "https://ljxwtl.cn/images/logo/index_logo.png",
                "https://ljxwtl.cn/images/logo/index_logo.png"
        };

        for (int i =0;i<articleItems.length;i++){
            ArticleItem articleItem = new ArticleItem();
            articleItem.setTitle(titles[i]);
            articleItem.setDescription(descriptions[i]);
            articleItem.setUrl(urls[i]);
            articleItem.setPicUrl(picUrls[i]);

            articleItems[i] = articleItem;
        }

        outMsgEntity.setItem(articleItems);

        return outMsgEntity;
    }

    /**
     * 处理text类型消息
     * @param inMsgEntity
     * @return
     */
    private Object dealTextMessage(InMsgEntity inMsgEntity){
        String content = inMsgEntity.getContent();

        switch (content){
            case "电视剧":
                OutMsgEntity outMsgEntity = new OutMsgEntity();
                outMsgEntity.setFromUserName(inMsgEntity.getToUserName());
                outMsgEntity.setToUserName(inMsgEntity.getFromUserName());
                outMsgEntity.setCreateTime(new Date().getTime());
                outMsgEntity.setMsgType("news");
                outMsgEntity.setArticleCount(1);
                ArticleItem[] articleItems = new ArticleItem[1];
                ArticleItem articleItem = new ArticleItem();

                articleItem.setTitle("电视剧");
                articleItem.setDescription("利用电视剧服务进行搜索数据！！！");
                articleItem.setUrl("http://ljxwtl.cn/m/tvResult?pageIndex=1");
                articleItem.setPicUrl("https://ljxwtl.cn/images/logo/index_logo.png");
                articleItems[0] = articleItem;

                outMsgEntity.setItem(articleItems);

                return outMsgEntity;

            case "电影":
                OutMsgEntity outMsgEntity_movie = new OutMsgEntity();
                outMsgEntity_movie.setFromUserName(inMsgEntity.getToUserName());
                outMsgEntity_movie.setToUserName(inMsgEntity.getFromUserName());
                outMsgEntity_movie.setCreateTime(new Date().getTime());
                outMsgEntity_movie.setMsgType("news");
                outMsgEntity_movie.setArticleCount(1);
                ArticleItem[] articleItems_movie = new ArticleItem[1];
                ArticleItem articleItem_movie = new ArticleItem();

                articleItem_movie.setTitle("电影");
                articleItem_movie.setDescription("利用电影服务进行搜索数据！！！");
                articleItem_movie.setUrl("http://ljxwtl.cn/m/movieResult?pageIndex=1");
                articleItem_movie.setPicUrl("https://ljxwtl.cn/images/logo/index_logo.png");
                articleItems_movie[0] = articleItem_movie;

                outMsgEntity_movie.setItem(articleItems_movie);

                return outMsgEntity_movie;

            case "app":
                OutMsgEntity outMsgEntity_app = new OutMsgEntity();
                outMsgEntity_app.setFromUserName(inMsgEntity.getToUserName());
                outMsgEntity_app.setToUserName(inMsgEntity.getFromUserName());
                outMsgEntity_app.setCreateTime(new Date().getTime());
                outMsgEntity_app.setMsgType("news");
                outMsgEntity_app.setArticleCount(1);
                ArticleItem[] articleItems_app = new ArticleItem[1];
                ArticleItem articleItem_app = new ArticleItem();

                articleItem_app.setTitle("app");
                articleItem_app.setDescription("利用app服务进行搜索数据！！！");
                articleItem_app.setUrl("http://ljxwtl.cn/m/appResult?categoryId=-10&pageSize=60&pageContext=1");
                articleItem_app.setPicUrl("https://ljxwtl.cn/images/logo/index_logo.png");
                articleItems_app[0] = articleItem_app;

                outMsgEntity_app.setItem(articleItems_app);

                return outMsgEntity_app;

            case "图片":
                OutMsgEntity outMsgEntity_image = new OutMsgEntity();
                outMsgEntity_image.setFromUserName(inMsgEntity.getToUserName());
                outMsgEntity_image.setToUserName(inMsgEntity.getFromUserName());
                outMsgEntity_image.setCreateTime(new Date().getTime());
                outMsgEntity_image.setMsgType("news");
                outMsgEntity_image.setArticleCount(1);
                ArticleItem[] articleItems_image = new ArticleItem[1];
                ArticleItem articleItem_image = new ArticleItem();

                articleItem_image.setTitle("图片");
                articleItem_image.setDescription("利用图片服务进行搜索数据！！！");
                articleItem_image.setUrl("http://ljxwtl.cn/m/imageResult?keyword=%E7%BE%8E%E5%A5%B3&pageIndex=1&pageSize=60");
                articleItem_image.setPicUrl("https://ljxwtl.cn/images/logo/index_logo.png");
                articleItems_image[0] = articleItem_image;

                outMsgEntity_image.setItem(articleItems_image);

                return outMsgEntity_image;

            case "音乐":
                OutMsgEntity outMsgEntity_music = new OutMsgEntity();
                outMsgEntity_music.setFromUserName(inMsgEntity.getToUserName());
                outMsgEntity_music.setToUserName(inMsgEntity.getFromUserName());
                outMsgEntity_music.setCreateTime(new Date().getTime());
                outMsgEntity_music.setMsgType("news");
                outMsgEntity_music.setArticleCount(1);
                ArticleItem[] articleItems_music = new ArticleItem[1];
                ArticleItem articleItem_music = new ArticleItem();

                articleItem_music.setTitle("电视剧");
                articleItem_music.setDescription("利用电视剧服务进行搜索数据！！！");
                articleItem_music.setUrl("http://ljxwtl.cn/m/tvResult?pageIndex=1");
                articleItem_music.setPicUrl("https://ljxwtl.cn/images/logo/index_logo.png");
                articleItems_music[0] = articleItem_music;

                outMsgEntity_music.setItem(articleItems_music);

                return outMsgEntity_music;

            case "迅雷下载":
                OutMsgEntity outMsgEntity_thunder = new OutMsgEntity();
                outMsgEntity_thunder.setFromUserName(inMsgEntity.getToUserName());
                outMsgEntity_thunder.setToUserName(inMsgEntity.getFromUserName());
                outMsgEntity_thunder.setCreateTime(new Date().getTime());
                outMsgEntity_thunder.setMsgType("news");
                outMsgEntity_thunder.setArticleCount(1);
                ArticleItem[] articleItems_thunder = new ArticleItem[1];
                ArticleItem articleItem_thunder = new ArticleItem();

                articleItem_thunder.setTitle("迅雷下载");
                articleItem_thunder.setDescription("利用迅雷下载服务进行搜索数据！！！");
                articleItem_thunder.setUrl("http://ljxwtl.cn/m/getThunderPagingResult?classify=%E6%9C%80%E6%96%B0%E5%BD%B1%E7%89%87&pageIndex=1");
                articleItem_thunder.setPicUrl("https://ljxwtl.cn/images/logo/index_logo.png");
                articleItems_thunder[0] = articleItem_thunder;

                outMsgEntity_thunder.setItem(articleItems_thunder);

                return outMsgEntity_thunder;

            case "百度云盘":
                OutMsgEntity outMsgEntity_baidu = new OutMsgEntity();
                outMsgEntity_baidu.setFromUserName(inMsgEntity.getToUserName());
                outMsgEntity_baidu.setToUserName(inMsgEntity.getFromUserName());
                outMsgEntity_baidu.setCreateTime(new Date().getTime());
                outMsgEntity_baidu.setMsgType("news");
                outMsgEntity_baidu.setArticleCount(1);
                ArticleItem[] articleItems_baidu = new ArticleItem[1];
                ArticleItem articleItem_baidu = new ArticleItem();

                articleItem_baidu.setTitle("电视剧");
                articleItem_baidu.setDescription("利用电视剧服务进行搜索数据！！！");
                articleItem_baidu.setUrl("http://ljxwtl.cn/m/baiduyunwangpanSearchResult?keyword=%E6%95%B0%E6%8D%AE%E5%BA%93&pageIndex=1");
                articleItem_baidu.setPicUrl("https://ljxwtl.cn/images/logo/index_logo.png");
                articleItems_baidu[0] = articleItem_baidu;

                outMsgEntity_baidu.setItem(articleItems_baidu);

                return outMsgEntity_baidu;
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

        return null;
    }
}
