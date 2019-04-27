package biz.service;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = {"biz"})
//@ControllerAdvice 捕获全局异常
public class GloableErrorHandler {

    @ExceptionHandler(Exception.class)
    public String captureException(Exception e){
        System.out.println(e);
        return "error";
    }

}
