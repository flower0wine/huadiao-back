package com.huadiao.utils.microspring.requestprocess;

import com.huadiao.utils.log.Loggers;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @projectName 花凋
 * @author flowerwine
 * @version  1.1
 * @description 核心拦截器
 */
@WebFilter(urlPatterns = "/dispatcherServlet", filterName = "DispatcherFilter")
public class DispatcherFilter implements Filter, DispatcherInterface, Loggers {

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 由于生成 sqlSession 可能会报错, 所以 threadLocal 可能没有 sqlSession,
        // 放入 try 语句中, 抛出错误后会获取 sqlSession 并关闭, 此时会抛出空指针异常
        // 故不放入 try 语句中
        THREAD_LOCAL.set(SqlSessionFactory.generateSqlSession());
        try {
            chain.doFilter(req, resp);
        } catch (Exception e) {
            // 出现异常回滚
            THREAD_LOCAL.get().rollback();
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;
            // 获取 ip
            String ip = request.getRemoteAddr();
            HttpSession session = request.getSession();
            Object uid = session.getAttribute("uid");
            Object userId = session.getAttribute("userId");
            Object administratorId = request.getAttribute("administratorID");
            LOGGER.atWarn().withThrowable(e).log("ip 为 {} 并且 userId, uid, administratorId 分别为 {}, {}, {} 的请求处理出现异常, 已对数据库操作进行回滚！调用堆栈如下：", ip, userId, uid, administratorId);
            // 抛出异常时, 返回提示信息
            response.getWriter().write(ERROR_TIP);
        } finally {
            // 一定要关闭 sqlSession
            SqlSession sqlSession = THREAD_LOCAL.get();
            sqlSession.commit();
            sqlSession.close();
            // 因为 tomcat 处理客户端的单次请求是创建一个线程去执行的
            // 其次 tomcat 使用了线程池, 上一个处理请求的线程不会被销毁, 而是等待下一个请求
            // 所以线程的 ThreadLocal.ThreadLocalMap 变量是不会销毁的, 需要取消引用
            THREAD_LOCAL.remove();
            LOGGER.debug("SqlSession 提交并关闭, 移除 THREAD_LOCAL 中的 sqlSession, 移除后 THREAD_LOCAL 调用 get() 为 {}", THREAD_LOCAL.get());
        }
    }
}
