package com.lnt.priros.service;

import com.lnt.priros.mapper.FaqMapper;
import com.lnt.priros.model.dto.Paging;
import com.lnt.priros.model.entity.Board;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FaqService {
    private final FaqMapper faqMapper;

    public List<Board> selectList(Paging paging) {
        return faqMapper.selectList(paging);
    }

    public int selectTotCnt() {
        return faqMapper.selectTotCnt();
    }
}
