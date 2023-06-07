package com.pa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * The <tt>GeoGraph</tt> stores are the roots (viewed as {@link GeoNode} objects) in the countries forest.
 *
 * @author Alex Neagu
 * @author Cristian Fiodorov
 */
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