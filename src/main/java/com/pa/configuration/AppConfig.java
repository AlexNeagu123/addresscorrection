package com.pa.configuration;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.pa.entity.GeoGraph;
import com.pa.entity.GeoNode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Initializing two {@link Bean} objects that are maintained by <b>SpringBoot</b>
 * <ul>
 *     <li>The <b>nameToNodeMap</b> {@link Multimap} that maps an asciiName from a {@link com.pa.entity.FieldToken} to an
 *     existing {@link GeoNode}</li>
 *
 *     <li>The <b>nodeToAlternativeMap</b> {@link Multimap} that maps a {@link GeoNode} object to a {@link java.util.List}
 *     of alternative names for the node's ascii names</li>
 * </ul>
 *
 * @author Alex Neagu
 * @author Cristian Fiodorov
 */
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

    @Bean
    public Multimap<GeoNode, String> nodeToAlternativeMap() {
        return ArrayListMultimap.create();
    }
}
