package com.huadiao.web.filter;

import com.huadiao.utils.FestivalHandler;
import com.huadiao.utils.log.Loggers;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Function 该过滤器拦截所有请求, 是定制的特有拦截器
 *          功能有:
 *              1. 设置同一字符编码
 *              2. 跨域请求是否接受
 *              3. 判断是游客还是已注册用户、管理员, 给予不同的响应
 * @Start_Time 2022 07 25 14:31
 */
@WebFilter(urlPatterns = "/*", filterName = "SpecificFilter")
public class SpecificFilter implements Filter, Loggers {

    /**
     * 允许访问的 url 片段
     */
    private static final String[] ALLOW_ACCESS_URL_PARTS = {
            "/",
            "/dispatcherServlet",
            "/systemLoginServlet",
            "/systemMessageServlet",
            "/operateUserServlet",
            "/operateAdministratorServlet",
            "/HTML/index.html",
            "/HTML/404.html",
            "/CSS/index.css",
            "/CSS/scrollBar.min.css",
            "/CSS/404.css",
            "/CSS/jedate.css",
            "/CSS/common.css",
            "/CSS/swiper-bundle.min.css",
            "/JS/404.js",
            "/JS/jquery.min.js",
            "/JS/index.js",
            "/JS/jquery.indexfullpage.js",
            "/JS/axios.js",
            "/JS/anime.min.js",
            "/JS/swiper-bundle.min.js",
            "/JS/scrollBar.min.js",
            "/JS/jedate.js",
            "/JS/common.js",
            "/images/",
            "/iconfont/",
            "/favicon.ico",
            "/webSocket"
    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("{} 过滤器初始化...", this.getClass().getName());
        try {
            new FestivalHandler();
        } catch (IOException e) {
            throw new ServletException(e);
        }
        LOGGER.debug("节日处理器创建完成!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 设置统一编码
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");

        // 允许所有请求跨域
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Allow-Methods","GET,POST,PUT,DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma,Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,User_ID,token");

        //  获取客户端发送的 url 请求
        String url = request.getServletPath();
        String ip = request.getRemoteAddr();
        LOGGER.debug("来自 ip 为 {} 的请求路径为 {}", ip, url);
        for (String u : ALLOW_ACCESS_URL_PARTS) {
            if (url.contains(u)) {
                // 如果用户请求的路径是上述路径中的，就放行,否则执行下面判断用户是否登录的逻辑
                LOGGER.debug("来自 ip 为 {} 的请求路径为 {}, 但属于游客访问项, 已匹配 {} 路径, 允许访问！", ip, url, u);
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");
        Object administratorId = session.getAttribute("administratorID");
        String manageStr = "/manage/";
        // 包含网站后台路径
        if(url.contains(manageStr)) {
            // 具有管理员 id
            if(administratorId != null) {
                LOGGER.debug("ip 为 {} 的管理员 {} 访问网站后台!", ip, administratorId);
                filterChain.doFilter(request, response);
            } else {
                LOGGER.info("ip 为 {} 并且 userId 为 {} 的用户企图访问网站后台！", ip, userId);
            }
        } else {
            // 如果是已登录用户
            if(userId != null) {
                LOGGER.debug("ip 为 {} 并且 userId 为 {} 的用户访问路径为 {}, 已放行！", ip, userId, url);
                filterChain.doFilter(request, response);
            } else {
                LOGGER.debug("ip 为 {} 的游客访问非游客访问的网站内容, 重定向到 404！", ip);
            }
        }
        LOGGER.debug("ip 为 {} 的未知用户被重定向到 404 页面！", ip);
        ((HttpServletResponse) servletResponse).sendRedirect(request.getContextPath() + "/HTML/404.html");
    }

    @Override
    public void destroy() {
        LOGGER.debug("{} 过滤器销毁！", this.getClass().getName());
    }
}
