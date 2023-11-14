package com.lnt.priros.model.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class IntroDto {
    private final int totalCaseCnt;
    private final int recentCaseCnt;
    private final int userCnt;
}
