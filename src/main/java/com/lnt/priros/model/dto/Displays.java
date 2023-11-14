package com.lnt.priros.model.dto;

import com.lnt.priros.model.response.IntroResponse;
import lombok.Getter;

@Getter
public class Displays {
    private IntroResponse introResponse;

    public void updateIntroResponse(IntroResponse introResponse) {
        this.introResponse = introResponse;
    }
        
}
