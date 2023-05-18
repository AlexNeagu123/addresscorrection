package com.pa;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

@RequiredArgsConstructor
@Data
public class GeoNode implements Serializable {
    private final String asciiName;
    private final int depth;
    private List<GeoNode> children;

    public void addChild(GeoNode child) {
        children.add(child);
    }
}
