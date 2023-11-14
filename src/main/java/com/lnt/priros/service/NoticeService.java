package com.lnt.priros.service;

import com.lnt.priros.mapper.NoticeMapper;
import com.lnt.priros.model.dto.Paging;
import com.lnt.priros.model.entity.Board;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeMapper noticeMapper;

    public List<Board> selectList(Paging paging) {
        return noticeMapper.selectList(paging);
    }

    public int selectTotCnt() {
        return noticeMapper.selectTotCnt();
    }
}
