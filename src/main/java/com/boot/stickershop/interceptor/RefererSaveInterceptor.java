package com.boot.stickershop.interceptor;

import com.boot.stickershop.domain.User;
import com.boot.stickershop.security.LoginUserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RefererSaveInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.getPrincipal() instanceof LoginUserInfo){
            LoginUserInfo loginUserInfo = (LoginUserInfo)authentication.getPrincipal();
            User user = new User();
            user.setId(loginUserInfo.getId());
            user.setEmail(loginUserInfo.getUsername());
            request.setAttribute("loginUser", loginUserInfo);
        }

        // referer 주소를 설정한다.
        /*String referer = request.getHeader("referer");
        String uri = request.getRequestURI();
        if("/users/login".equals(uri)){
            uri = referer;
        }
        request.setAttribute("loginRedirect", uri);*/
        return true;
    }
}
