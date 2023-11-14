package com.lnt.priros.controller;

import com.lnt.priros.model.dto.JoinFirmDto;
import com.lnt.priros.model.request.ExpertApplyRequest;
import com.lnt.priros.model.response.ExpertApplyResponse;
import com.lnt.priros.usercases.ApplyFirmUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("join-firm")
public class JoinFirmController {
    private final ApplyFirmUseCase applyFirmUseCase;

    @PostMapping("")
    public ExpertApplyResponse apply(ExpertApplyRequest expertApplyRequest) throws Exception {
        // 가입신청 확인 및 저장
        JoinFirmDto joinFirmDto = JoinFirmDto.fromRequest(expertApplyRequest);
        ExpertApplyResponse response = applyFirmUseCase.applyFirm(joinFirmDto);




        return null;
    }





}
