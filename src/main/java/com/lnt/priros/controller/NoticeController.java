package com.lnt.priros.controller;

import com.lnt.priros.model.dto.AuthenticUser;
import com.lnt.priros.model.dto.Paging;
import com.lnt.priros.model.entity.Board;
import com.lnt.priros.model.response.NoticeResponse;
import com.lnt.priros.service.NoticeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("list")
    public NoticeResponse list(int pageNo) {
        int totCnt = noticeService.selectTotCnt();
        Paging paging = new Paging(pageNo, totCnt);
        List<Board> noticeList = noticeService.selectList(paging);

        return NoticeResponse
                .builder()
                .noticeList(noticeList)
                .paging(paging)
                .build();
    }
}