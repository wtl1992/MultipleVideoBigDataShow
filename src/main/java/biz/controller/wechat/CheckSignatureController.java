package biz.controller.wechat;

import biz.service.wechat.CheckSignatureService;
import model.wechat.InMsgEntity;
import model.wechat.OutMsgEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Date;

@Controller
@Lazy
@Scope("singleton")
public class CheckSignatureController {

    static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Resource(name = "checkSignatureService")
    private CheckSignatureService checkSignatureService;

    @RequestMapping(value = "/wx",method = RequestMethod.GET)
    public void checkToken(String signature,
                           String timestamp,
                           String nonce,
                           String echostr,
                           HttpServletResponse httpServletResponse){

        logger.info(signature +";"+timestamp+";"+nonce+";"+echostr);
        boolean bool = checkSignatureService.checkToken(signature, timestamp, nonce, echostr);

        if (bool){
            BufferedOutputStream bufferedOutputStream = null;
            try {
                bufferedOutputStream = new BufferedOutputStream(httpServletResponse.getOutputStream());
                bufferedOutputStream.write(echostr.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (bufferedOutputStream != null){
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }



    @RequestMapping(value = "/wx",method = RequestMethod.POST)
    public @ResponseBody
    Object dealMultipleMessage(@RequestBody InMsgEntity inMsgEntity) throws IOException {
        return checkSignatureService.dealMultipleMessage(inMsgEntity);
    }
}
