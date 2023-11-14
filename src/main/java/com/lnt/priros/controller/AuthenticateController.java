package com.lnt.priros.controller;

import com.lnt.priros.jwt.JwtUtil;
import com.lnt.priros.model.dto.AuthenticUser;
import com.lnt.priros.model.request.UserLoginRequest;
import com.lnt.priros.model.response.UserLoginResponse;
import com.lnt.priros.service.UserTokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/")
public class AuthenticateController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserTokenService userTokenService;
    private final Environment env;
    @Value("${firm.license.file.path}")
    public static String FIRM_LICENSE_FILE_PATH;
    @Value("${spring.config.activate.on-profile[0]}")
    public static String PROFILE;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        userLoginRequest.getUserId(), userLoginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthenticUser authenticUser = (AuthenticUser) authentication.getPrincipal();

        String accessToken = jwtUtil.createAccessToken(authenticUser);
        String refreshToken = jwtUtil.createRefreshToken(authenticUser);
        userTokenService.upsertJwt(refreshToken, authenticUser.getUsername());

        return ResponseEntity
                            .ok(UserLoginResponse.builder()
                                    .userId(authenticUser.getUsername())
                                    .token(accessToken)
                                    .build()
                            );
    }
}