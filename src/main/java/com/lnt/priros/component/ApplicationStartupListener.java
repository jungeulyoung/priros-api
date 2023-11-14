package com.lnt.priros.component;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import com.lnt.priros.model.dto.Displays;
import com.lnt.priros.service.IntroService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplicationStartupListener {
    private final IntroService introService;
    private final Displays displays;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        displays.updateIntroResponse(this.introService.selectOne());
    }
    
}
