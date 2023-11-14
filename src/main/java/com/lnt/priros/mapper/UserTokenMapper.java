package com.lnt.priros.mapper;

import com.lnt.priros.model.dto.TokenClaims;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserTokenMapper {
    int count(TokenClaims tokenClaims);

    void upsertJwt(@Param("refreshJwt") String refreshJwt, @Param("userId") String userId);
}
