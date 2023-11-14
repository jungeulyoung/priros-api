package com.lnt.priros.jwt;

import com.lnt.priros.model.dto.AuthenticUser;
import com.lnt.priros.model.dto.TokenClaims;
import com.lnt.priros.resource.ErrorCode;
import com.lnt.priros.service.UserTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserTokenService userTokenService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        try {
            String accessToken = jwtUtil.extractBearerFromRequest(request);
            if (StringUtils.isBlank(accessToken)) {
                request.setAttribute("exception", ErrorCode.USER_AUTHENTICATION_EXCEPT);
                filterChain.doFilter(request, response);
                return;
            }
            if (jwtUtil.isTokenExpired(accessToken)) {
                request.setAttribute("exception", ErrorCode.USER_ACCESS_TOKEN_EXPIRED);
                filterChain.doFilter(request, response);
                return;
            }
            TokenClaims tokenClaims = jwtUtil.getTokenClaims(accessToken);
            int cnt = userTokenService.count(tokenClaims);
            if (cnt == 0) {
                request.setAttribute("exception", ErrorCode.USER_ACCESS_TOKEN_ENABLED);
                filterChain.doFilter(request, response);
                return;
            }

            UserDetails userDetails = new AuthenticUser(tokenClaims);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            log.error("Cannot set user authentication: {}", e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

}
