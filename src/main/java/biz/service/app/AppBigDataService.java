package biz.service.app;

import model.app.App;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import utils.HttpRequestUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Lazy
@Scope("singleton")
public class AppBigDataService {

    /**
     * 获得分类并分页后的apps
     * @return
     */
    public Map<String,Object> getClassifyApps(String classifyName,
                                              int categoryId,
                                              int pageSize,
                                              int pageContext){
        Map<String,Object> map = new HashMap<String, Object>();
        List<App> apps = new ArrayList<App>();
        map.put("apps",apps);
        map.put("classifyName",classifyName);
        String url = "https://sj.qq.com/myapp/cate/appList.htm?orgame=1&categoryId="+categoryId+"&pageSize="+pageSize+"&pageContext="+pageContext;

        String json = HttpRequestUtil.requestHttp(url,"utf-8","GET");

        if (json != null && !"".equalsIgnoreCase(json)){
            try {
                JSONArray jsonArray = (JSONArray) JSONObject.fromObject(json).get("obj");

//            System.out.println(jsonArray.size());
                for (int i=0;i<jsonArray.size();i++){
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    List<String> images = new ArrayList<String>();
                    JSONArray imageJsonArray = (JSONArray) jsonObject.get("images");
                    for (int j=0;j<imageJsonArray.size();j++){
                        images.add(imageJsonArray.getString(j));
                    }
                    App app = new App(jsonObject.getString("apkMd5")
                            ,jsonObject.getLong("apkPublishTime")
                            ,jsonObject.getString("apkUrl")
                            ,jsonObject.getLong("appDownCount")
                            ,jsonObject.getInt("appId")
                            ,jsonObject.getString("appName")
                            ,jsonObject.getInt("authorId")
                            ,jsonObject.getString("authorName")
                            ,jsonObject.getDouble("averageRating")
                            ,jsonObject.getInt("categoryId")
                            ,jsonObject.getString("categoryName")
                            ,jsonObject.getString("description")
                            ,jsonObject.getString("editorIntro")
                            ,jsonObject.getLong("fileSize")
                            ,jsonObject.getString("iconUrl")
                            ,images
                            ,jsonObject.getString("newFeature")
                            ,jsonObject.getString("pkgName")
                            ,jsonObject.getString("snapshotsUrl")
                            ,jsonObject.getString("versionName"));

                    apps.add(app);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        return map;
    }
}
