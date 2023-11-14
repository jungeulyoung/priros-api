package com.lnt.priros.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lnt.priros.model.dto.Paging;
import com.lnt.priros.model.dto.SearchResult;
import com.lnt.priros.model.entity.Board;
import com.lnt.priros.model.response.FaqResponse;
import com.lnt.priros.service.FaqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/faq")
public class FaqController {
    private final FaqService faqService;

    @GetMapping("list")
    public FaqResponse list(int pageNo) {
        int totCnt = faqService.selectTotCnt();
        Paging paging = new Paging(pageNo, totCnt);
        List<Board> faqList = faqService.selectList(paging);
        return FaqResponse
                .builder()
                .faqList(faqList)
                .paging(paging)
                .build();
    }

    @GetMapping("list-test")
    public SearchResult<Board> listTest(int pageNo) {
        int totCnt = faqService.selectTotCnt();
        SearchResult<Board> searchResult = SearchResult.create(pageNo, totCnt); 
        if (totCnt > 0)
            searchResult.setList(
                faqService.selectList(searchResult.getPaging())
            );        
        return searchResult;
    }    
}