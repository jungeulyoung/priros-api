package com.lnt.priros.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lnt.priros.mapper.BoardMapper;
import com.lnt.priros.mapper.IntroMapper;
import com.lnt.priros.model.dto.IntroDto;
import com.lnt.priros.model.entity.Board;
import com.lnt.priros.model.response.IntroResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class IntroService {
    private final IntroMapper introMapper;
    private final BoardMapper boardMapper;

    public IntroResponse selectOne() {
        IntroDto introDto = introMapper.selectOne();
        List<Board> recentBoardList = boardMapper.selectRecentList();

        return IntroResponse
                .builder()
                .userCnt(introDto.getUserCnt())
                .totalCaseCnt(introDto.getTotalCaseCnt())
                .recentCaseCnt(introDto.getRecentCaseCnt())
                .recentBoardList(recentBoardList)
                .build();
    }
}
