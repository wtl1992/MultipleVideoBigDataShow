package biz.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String logined = (String) httpServletRequest.getSession().getAttribute("flag");
        if (("logined").equalsIgnoreCase(logined)){
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else{
            httpServletResponse.sendRedirect("WeiXinBackstage/index");
        }

    }

    @Override
    public void destroy() {

    }
}
