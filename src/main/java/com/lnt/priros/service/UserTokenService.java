package com.lnt.priros.service;

import com.lnt.priros.mapper.UserTokenMapper;
import com.lnt.priros.model.dto.TokenClaims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserTokenService {
    private final UserTokenMapper userTokenMapper;

    public int count(TokenClaims tokenClaims) {
        return userTokenMapper.count(tokenClaims);
    }

    public void upsertJwt(String refreshJwt, String userId) {
        userTokenMapper.upsertJwt(refreshJwt, userId);
    }
}
