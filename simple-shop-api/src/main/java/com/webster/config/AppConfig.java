package com.webster.config;

import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@NoArgsConstructor
public class AppConfig {

    @Bean
    public ModelMapper generateModelMapper() {
        return new ModelMapper();
    }
}
