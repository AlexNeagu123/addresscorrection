package com.pa.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GeoNode {
    private final String asciiName;
    private int depth;
    private GeoNode parent;
    // is null if depth is equal to 1
    private List<GeoNode> children;

    public GeoNode(String asciiName, int depth, GeoNode parent) {
        this.asciiName = asciiName;
        this.depth = depth;
        this.children = new ArrayList<>();
        this.parent = parent;
    }

    public void addChild(GeoNode child) {
        children.add(child);
    }
}