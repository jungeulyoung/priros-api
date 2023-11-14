package com.lnt.priros.model.response;

import java.util.List;
import com.lnt.priros.model.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class IntroResponse {
    private final int totalCaseCnt;
    private final int recentCaseCnt;
    private final int userCnt;
    private final List<Board> recentBoardList;
}
