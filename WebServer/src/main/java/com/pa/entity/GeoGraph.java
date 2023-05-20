package com.pa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class GeoGraph {
    private final List<GeoNode> roots;

    public GeoGraph() {
        this.roots = new ArrayList<>();
    }

    public void addRoot(GeoNode node) {
        roots.add(node);
    }
}