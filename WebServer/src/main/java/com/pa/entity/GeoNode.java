package com.pa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
public class GeoNode {
    private final String asciiName;
    private final Long geonameId;
    @EqualsAndHashCode.Exclude
    private int depth;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private GeoNode parent;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<GeoNode> children;

    public GeoNode(String asciiName, Long geonameId, int depth, GeoNode parent) {
        this.asciiName = asciiName;
        this.geonameId = geonameId;
        this.depth = depth;
        this.children = new ArrayList<>();
        this.parent = parent;
    }

    public void addChild(GeoNode child) {
        children.add(child);
    }
}