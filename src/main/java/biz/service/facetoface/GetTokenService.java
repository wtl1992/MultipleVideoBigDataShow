package biz.service.facetoface;


import net.sf.json.JSONObject;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import utils.HttpRequestUtil;

@Service
@Lazy(value = true)
@Scope("singleton")
public class GetTokenService {

    public String getToken(){
        String url = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=HlTtsyFFTiwEKr97BhpmgnrK&client_secret=q0QcEtPbzszWCtAEZfYPUhCWRhMMSm0X";


        String result = HttpRequestUtil.requestHttp(url, "utf-8", "GET");

        JSONObject jsonObject = JSONObject.fromObject(result);


        return jsonObject.getString("access_token");
    }
}
