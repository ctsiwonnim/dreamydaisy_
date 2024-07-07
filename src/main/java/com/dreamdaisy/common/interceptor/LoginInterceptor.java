//package com.dreamdaisy.common.interceptor;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//public class LoginInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        HttpSession session = request.getSession(false); // 세션이 없을 때는 null
//
//        if (session == null || session.getAttribute("member") == null) {
//            response.sendRedirect("/member/login");
//
//            return false;
//        }
//
//        return true;
//    }
//}
