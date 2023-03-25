package com.huadiao.utils.microspring.requestprocess;

import com.huadiao.utils.log.Loggers;
import com.huadiao.utils.microspring.xmlresolver.BeansXMLResolver;
import com.huadiao.utils.microspring.xmlresolver.MethodSignatureMap;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * @projectName 花凋
 * @author flowerwine
 * @version  1.1
 * @description 中央控制器
 */
@WebServlet(urlPatterns = {"/dispatcherServlet"}, name = "DispatcherServlet")
public class DispatcherServlet extends HttpServlet implements BeansMapInterface, DispatcherInterface, Loggers {
    @Override
    public void init() throws ServletException {
        try {
            beanMap.putAll(BeansXMLResolver.resolveXML("huadiaoconfig/beans-config.xml"));
            LOGGER.debug("beanMap 初始化完成! 目前有 {} 个对象, 分别名为 {}", beanMap.size(), beanMap.keySet());
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 请求处理
            String processResult = dispatch(request, response);
            LOGGER.debug("请求处理后的数据未经过视图处理前为 {}", processResult);
            // 视图处理
            processResult = viewProcess(request, response, processResult);
            LOGGER.debug("请求最终经过视图处理, 得到的结果是 {}", processResult);
            response.getWriter().write(processResult);
        } catch (IOException e) { // IO 异常
            throw new IOException(e);
        } catch (Exception e) { // 更多异常
            throw new ServletException(e);
        }
    }

    /**
     * 对请求进行处理, 请求参数映射相应的 Controller 中的相应方法
     * @param request 请求对象
     * @param response 响应对象
     * @return 返回处理结果
     * @exception Exception 可能抛出异常
     */
    private String dispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取 ip
        String ip = request.getRemoteAddr();
        // 1. 注意这里的 bean id 映射和 方法注解名映射
        // 2. 只用填写 bean id 的一部分, 不用包含 Controller, requestType 对应 Controller 前面部分
        // 3. operation 与方法注解相对应
        String requestType = request.getParameter("requestType");
        String operation = request.getParameter("operation");
        String controllerName = requestType + "Controller";
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");
        // 获取指定的 Controller
        Object o = beanMap.get(controllerName);
        LOGGER.debug("DispatcherServlet 收到请求, requestType 为 {}, operation 为 {}", requestType, operation);
        if(o == null) {
            throw new NullPointerException(String.format("未在 beanMap 中找到名为 %s 的 Controller", controllerName));
        }
        Method[] methods = o.getClass().getDeclaredMethods();

        // 通过反射获取方法注解, 并匹配上方法
        for (Method method : methods) {
            MethodSignatureMap methodAnnotation = method.getAnnotation(MethodSignatureMap.class);
            // 没有使用注解
            if(methodAnnotation == null) {
                throw new NoAnnotationException(String.format("%s 没有 @MethodSignatureMap 注解", method.getName()));
            }
            // 如果使用了该注解, 并且匹配上
            if (methodAnnotation.methodSignature().equals(operation)) {
                // 如果不允许游客访问, 如果是游客, 重定向到首页
                if(!methodAnnotation.allowTourAccess() && userId == null) {
                    LOGGER.debug("ip 为 {} 的游客企图访问非游客内容, 重定向到主页!", ip);
                    return "redirect:" + request.getContextPath();
                }
                // 获取方法的参数, 来获取前端传递的 request 参数
                Parameter[] parameters = method.getParameters();
                Object[] params = new Object[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    String paramType = parameters[i].getType().getName();
                    // 根据参数的类型传递值
                    if (paramType.lastIndexOf("Request") != -1) {
                        params[i] = request;
                    } else if (paramType.lastIndexOf("Response") != -1) {
                        params[i] = response;
                    } else {
                        // 根据方法参数名来获取前端传递的参数
                        params[i] = request.getParameter(parameters[i].getName());
                    }
                }
                LOGGER.debug("反射得到 {} 方法的参数名为 {}", method.getName(), Arrays.toString(params));
                // 返回处理结果, 结果均为 String
                return (String) method.invoke(o, params);
            }
        }
        LOGGER.info("ip 为 {} 并且 userId 为 {} 的用户的访问参数 requestType 为 {}, operation 为 {}, 没有匹配的方法！重定向到 404 页面", ip, userId, requestType, operation);
        // 如果没有匹配上方法, 说明不是预先确定的接口, 防止别人发现进攻入口, 返回 404 页面
        return "redirect:" + request.getContextPath() + "/HTML/404.html";
    }

    /**
     * 对处理结果进行视图处理
     * @param request 请求对象
     * @param response 响应对象
     * @param processResult 处理结果
     * @return 返回视图处理结果
     * @throws IOException 重定向和请求内部转发可能抛出
     * @throws ServletException 请求内部转发抛出
     */
    private String viewProcess(HttpServletRequest request, HttpServletResponse response, String processResult) throws IOException, ServletException {

        // 重定向
        if(processResult.startsWith(REDIRECT)) {
            response.sendRedirect(processResult.split(":")[1]);
            // 请求内部转发
        } else if(processResult.startsWith(FORWARD)) {
            request.getRequestDispatcher(processResult.split(":")[1]).forward(request, response);
        } else { // 用户数据
            return processResult;
        }
        // 执行无误
        return CORRECT_TIP;
    }
}
