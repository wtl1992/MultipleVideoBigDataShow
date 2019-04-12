package biz.service.facetoface;

import net.sf.json.JSONObject;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import utils.HttpRequestUtil;

@Service
@Lazy(value = true)
@Scope("singleton")
public class GetHeadImageRecService {

    private final static String apiKey = "qGI7BGzXRCeInCqHkm75kmBO";

    private final static String secretKey = "EoRiUN8X3HN2vFaNjSzaT0BFw1yItFgl";

    public String getHeadImageToken(){
        String url = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id="+apiKey+"&client_secret="+secretKey;

        String result = HttpRequestUtil.requestHttp(url, "utf-8", "GET");

        JSONObject jsonObject = JSONObject.fromObject(result);

        result = jsonObject.getString("access_token");

        return result;
    }

}
