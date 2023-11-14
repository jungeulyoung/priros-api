package com.lnt.priros.controller;

import com.lnt.priros.model.dto.JoinFirmDto;
import com.lnt.priros.model.request.ExpertApplyRequest;
import com.lnt.priros.model.response.ExpertApplyResponse;
import com.lnt.priros.usercases.ApplyFirmUseCase;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lnt.priros.model.dto.AuthenticUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/")
public class UserController {
    private final ApplyFirmUseCase applyFirmUseCase;


    @GetMapping("/principal")
    public AuthenticUser principal(@AuthenticationPrincipal AuthenticUser authenticUser) {
        return authenticUser;
    }

    @PostMapping("firm")
    public ExpertApplyResponse signUp(ExpertApplyRequest expertApplyRequest) throws Exception {
        // 가입신청 확인 및 저장
        JoinFirmDto joinFirmDto = JoinFirmDto.fromRequest(expertApplyRequest);
        ExpertApplyResponse response = applyFirmUseCase.applyFirm(joinFirmDto);




        return null;
    }






}
