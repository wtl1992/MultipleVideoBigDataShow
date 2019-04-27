package biz.service.WeiXinBackstage;

import biz.mappers.LoginUserMapper;
import model.LoginUser;
import model.LoginUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeiXinBackstageIndexService {

    private final static String PASSWORD = "wtl199201180271";

    @Autowired
    private LoginUserMapper loginUserMapper;

    /**
     * 登陆管理后台
     *
     * @return
     */
    public Map<String, Object> login(LoginUser loginUser, HttpSession session) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        LoginUserExample loginUserExample = new LoginUserExample();
        LoginUserExample.Criteria criteria = loginUserExample.createCriteria();
        criteria.andUsernameEqualTo(loginUser.getUsername());
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(loginUser.getPassword().getBytes("utf-8"));
        String md5Str = byteArrayToHexString(digest);
        criteria.andPasswordEqualTo(md5Str);

        List<LoginUser> loginUsers = loginUserMapper.selectByExample(loginUserExample);
        if (loginUsers != null && loginUsers.size() > 0) {
            map.put("status", "success");
            session.setAttribute("flag","logined");
        } else {
            map.put("status", "failed");
        }
        return map;
    }

    /**
     * 轮换字节数组为十六进制字符串
     *
     * @param b 字节数组
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6',
                '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2] + "";
    }

//    public static void main(String[] args) throws Exception {
//        MessageDigest md5 = MessageDigest.getInstance("MD5");
//        byte[] digest = md5.digest(PASSWORD.getBytes("utf-8"));
//        String md5Str = byteArrayToHexString(digest);
//        System.out.println(md5Str);
//    }
}
