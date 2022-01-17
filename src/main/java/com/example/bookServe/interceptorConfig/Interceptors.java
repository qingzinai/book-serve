package com.example.bookServe.interceptorConfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Interceptors implements AsyncHandlerInterceptor {
    public static final ThreadLocal<Map<String,Object>>  threadLocal= new ThreadLocal<>();
    Interceptors(){
        log.info("interceptors Create");
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("startTime",System.currentTimeMillis());

        HashMap<String, Object> threadMap = new HashMap<>();
        threadMap.put("begin", System.currentTimeMillis());
        threadLocal.set(threadMap);

        // 只拦截方法
        if(! (handler instanceof HandlerMethod) ){
            return true ;
        }
        if(!request.getRequestURL().substring(request.getRequestURL().indexOf("9020/")+5).equals("api/user/login")){
            if(request.getHeader("token")==null){
                throw new MyException("-1", "token失效");
            }
        }
        System.out.println(request.getHeader("content-type"));
        System.out.println(request.getRequestURL().substring(request.getRequestURL().indexOf("9020/")+5));
        System.out.println(request.getRequestURL());
        System.out.println("进入拦截器了");
        //中间写逻辑代码，比如判断是否登录成功，失败则返回false
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {


        System.out.println("controller 执行完了");
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Long begin = (Long)threadLocal.get().get("begin");
        long l = System.currentTimeMillis() - begin;

        System.out.println(l+"ms");
        System.out.println("我获取到了一个返回的结果：" + response);
        System.out.println("请求结束了");
    }
}
