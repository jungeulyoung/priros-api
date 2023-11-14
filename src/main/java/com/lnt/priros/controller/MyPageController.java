package com.lnt.priros.controller;

import com.lnt.priros.model.dto.MyPage;
import com.lnt.priros.model.dto.MyPageFirm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage/")
public class MyPageController {
    public MyPage myPage(MyPage myPage) {

        return myPage;
    }

    @GetMapping("firm/{firmCode}")
    public MyPageFirm myPageFirm(MyPageFirm myPageFirm, @PathVariable String firmCode) {


        return myPageFirm;
    }

    @PostMapping("user/{userId}/check")
    public MyPage pwCheck(MyPage myPage,@PathVariable String userId) {
        return myPage;
    }

    @GetMapping("user/{userId}")
    public MyPage userInfo(MyPage myPage,@PathVariable String userId) {

        return myPage;
    }


}
