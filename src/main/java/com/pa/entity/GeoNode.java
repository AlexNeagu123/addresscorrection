package com.pa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * The <tt>GeoNode</tt> class represents a node in the <b>forest</b> that results after the initialization process.
 * <p>
 * All the locations model by the API (countries, states, cities) are viewed as nodes and are encapsulated in a <tt>GeoNode</tt> object.
 * <p>
 * All the hierarchical relationships between locations are viewed as edges and are modeled by the {@code children} attribute
 * stored in a GeoNode object.
 *
 * @author Alex Neagu
 * @author Cristian Fiodorov
 */
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