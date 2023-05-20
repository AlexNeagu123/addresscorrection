package com.pa.configuration;

import com.pa.entity.GeoGraph;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public GeoGraph geoGraph() {
        return new GeoGraph();
    }
}
