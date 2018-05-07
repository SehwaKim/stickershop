package com.boot.stickershop.security.oauth2;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import java.io.IOException;

public class AlreadyLoginCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            String currentPrincipalName = authentication.getName();
            System.out.println("##########################################################");
            System.out.println(currentPrincipalName);
            System.out.println("##########################################################");
            if(!"annonymousUser".equals(currentPrincipalName)) { // 인증된 사용자일 경우

                request.setAttribute("alreadyLoginId", currentPrincipalName);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
