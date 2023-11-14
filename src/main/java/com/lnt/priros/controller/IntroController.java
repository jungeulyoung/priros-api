package com.lnt.priros.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lnt.priros.model.dto.Displays;
import com.lnt.priros.model.response.IntroResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/intro")
public class IntroController {
    private final Displays displays;
    //private final IntroConfig introConfig;

    @GetMapping("")
    public IntroResponse intro() {
        return displays.getIntroResponse();
    }
}