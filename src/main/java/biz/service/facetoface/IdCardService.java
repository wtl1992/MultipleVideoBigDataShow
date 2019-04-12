package biz.service.facetoface;

import net.sf.json.JSONObject;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import utils.HttpRequestPostUtil;

import java.util.HashMap;
import java.util.Map;

@Service
@Lazy(value = true)
@Scope("singleton")
public class IdCardService {

    public Map<String,String> getIdCardInfor(String access_token,
                                 String id_card_side,
                                 String image){

        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard?access_token="+access_token;

        Map<String,String> params = new HashMap<String, String>();

        params.put("id_card_side",id_card_side);
        params.put("image",image);

        String result = HttpRequestPostUtil.requestHttp(url, "utf-8", params, null);


        JSONObject jsonObject = JSONObject.fromObject(result);

        Map<String,String> map = new HashMap<String, String>();

        if ("front".equalsIgnoreCase(id_card_side)){
            try {
                String idCardNumber = jsonObject.getJSONObject("words_result").getJSONObject("公民身份号码").getString("words");

                String name = jsonObject.getJSONObject("words_result").getJSONObject("姓名").getString("words");

                String address = jsonObject.getJSONObject("words_result").getJSONObject("住址").getString("words");

                String birthday = jsonObject.getJSONObject("words_result").getJSONObject("出生").getString("words");

                String sex = jsonObject.getJSONObject("words_result").getJSONObject("性别").getString("words");

                String minzu = jsonObject.getJSONObject("words_result").getJSONObject("民族").getString("words");
                if (idCardNumber != null && !"".equalsIgnoreCase(idCardNumber)
                        && name != null && !"".equalsIgnoreCase(name)
                        && address != null && !"".equalsIgnoreCase(address)
                        && birthday != null && !"".equalsIgnoreCase(birthday)
                        && sex != null && !"".equalsIgnoreCase(sex)
                        && minzu != null && !"".equalsIgnoreCase(minzu)){
                    map.put("idCardNumber",idCardNumber);
                    map.put("name",name);
                    map.put("address",address);
                    map.put("birthday",birthday);
                    map.put("sex",sex);
                    map.put("minzu",minzu);
                    map.put("status","success");
                }
                else{
                    throw new Exception("信息读取失败!!!");
                }
            }
            catch (Exception e){
                map.put("status","failed");
            }
        }
        else{
            try {
                JSONObject words_result = jsonObject.getJSONObject("words_result");

                //失效日期
                String expirationDate = words_result.getJSONObject("失效日期").getString("words");

                //签发日期
                String dateOfIssue = words_result.getJSONObject("签发日期").getString("words");

                //签发机关
                String signingAndIssuingOrganization = words_result.getJSONObject("签发机关").getString("words");

                if (expirationDate != null && !"".equalsIgnoreCase(expirationDate)
                && dateOfIssue != null && !"".equalsIgnoreCase(dateOfIssue)
                && signingAndIssuingOrganization != null && !"".equalsIgnoreCase(signingAndIssuingOrganization)){
                    map.put("expirationDate",expirationDate);
                    map.put("dateOfIssue",dateOfIssue);
                    map.put("signingAndIssuingOrganization",signingAndIssuingOrganization);
                    map.put("status","success");
                }
            }
            catch (Exception e){
                map.put("status","failed");
            }
        }
        return map;
    }

}
