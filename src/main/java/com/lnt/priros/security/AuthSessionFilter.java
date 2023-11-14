package com.lnt.priros.security;

import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.lnt.priros.resource.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthSessionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {                

        HttpSession session = request.getSession(false);
        if (session == null) {            
            request.setAttribute("exception", ErrorCode.USER_AUTHENTICATION_EXCEPT);            
        }
        else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || ! authentication.isAuthenticated()) {
                request.setAttribute("exception", ErrorCode.USER_AUTHENTICATION_EXCEPT);            
            }
        }                
        filterChain.doFilter(request, response);       
    }
    
}
