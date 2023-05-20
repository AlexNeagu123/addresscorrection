package com.pa.configuration;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.pa.entity.GeoGraph;
import com.pa.entity.GeoNode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public GeoGraph geoGraph() {
        return new GeoGraph();
    }

    @Bean
    public Multimap<String, GeoNode> nameToNodeMap() {
        return ArrayListMultimap.create();
    }
}
