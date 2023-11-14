package com.lnt.priros.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.lnt.priros.model.dto.Displays;
import com.lnt.priros.service.IntroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {
    private final IntroService introService;
    private final Displays displays;

    @Scheduled(cron = "0 0 4,22 * * ?")
    public void refreshIntroRespond() { 
        displays.updateIntroResponse(this.introService.selectOne());
    }

}
