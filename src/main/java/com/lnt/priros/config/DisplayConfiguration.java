package com.lnt.priros.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.lnt.priros.model.dto.Displays;

@Configuration
public class DisplayConfiguration {

    @Bean
    Displays displays(){
        return new Displays();
    }    
    
}
