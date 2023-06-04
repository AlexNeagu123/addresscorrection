package com.pa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeoName {
    private String name;
    private String asciiName;
    private Long geoNameId;
    private String type;
    private List<String> alternateNames;
    private List<GeoName> children;
}