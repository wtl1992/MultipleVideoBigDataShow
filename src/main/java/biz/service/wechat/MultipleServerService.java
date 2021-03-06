package biz.service.wechat;

import biz.filter.SysContext;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.HttpRequestUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class MultipleServerService {


    static {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    String json = getAccessToken();
                    System.out.println(json);
                    SysContext.ACCESS_TOKEN = JSONObject.fromObject(json).getString("access_token");
                    try {
                        Thread.sleep((long) (1.8 * 60 * 60 * 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 获取access_token
     * @return
     */
    private static String getAccessToken(){
        String access_tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+SysContext.AppID+"&secret="+SysContext.AppSecret;
        String json = HttpRequestUtil.requestHttp(access_tokenUrl, "utf-8", "GET");
        return json;
    }

    /**
     * 获取微信服务器IP地址
     * @return
     */
    @RequestMapping("/getWeChatServerIPAddress")
    public @ResponseBody
    List<String> getWeChatServerIPAddress(){
        String url = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=" + SysContext.ACCESS_TOKEN;
        List<String> ip_list = new ArrayList<String>();
        String json = HttpRequestUtil.requestHttp(url,"utf-8","GET");

        JSONArray jsonArray = JSONObject.fromObject(json).getJSONArray("ip_list");

        for (int i=0;i<jsonArray.size();i++){
            ip_list.add(jsonArray.getString(i));
        }
        return ip_list;
    }
}
