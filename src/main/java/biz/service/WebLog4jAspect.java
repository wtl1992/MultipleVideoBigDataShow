package biz.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


@Aspect
@Component
@Slf4j
public class WebLog4jAspect {

    @Pointcut("execution(* biz.*.*..*.*(..))")
    public void webLog(){

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        //接收到请求，记录请求内容
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        //记录下请求内容
        System.out.println("URL : "+request.getRequestURL().toString());
        System.out.println("HTTP_METHOD : "+request.getMethod());
        System.out.println("IP : "+request.getRemoteAddr());

        Enumeration<String> parameterNames = request.getParameterNames();

        while(parameterNames.hasMoreElements()){
            String name = parameterNames.nextElement();
            log.info("name : {},value : {}",name,request.getParameter(name));
        }
    }


    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable{
        //处理完请求，返回内容
        System.out.println("RESPONSE : "+ret);
    }

}
