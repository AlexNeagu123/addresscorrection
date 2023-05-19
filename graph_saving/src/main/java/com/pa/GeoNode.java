package com.pa;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class GeoNode implements Serializable {
    private final String asciiName;
    private int depth;
    private List<GeoNode> children;

    public GeoNode(String asciiName, int depth) {
        this.asciiName = asciiName;
        this.depth = depth;
        this.children = new ArrayList<>();
    }

    public void addChild(GeoNode child) {
        children.add(child);
    }
}
