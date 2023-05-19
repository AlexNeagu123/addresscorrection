package com.pa.service;

import com.pa.entity.GeoGraph;
import com.pa.entity.GeoNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeoGraphService {
    private final GeoGraph geoGraph;

    @Autowired
    public GeoGraphService(GeoGraph geoGraph) {
        this.geoGraph = geoGraph;
    }

    /**
     * For testing purposes only
     */
    public void printCountryTree(String countryAsciiName) {
        for (GeoNode root : this.geoGraph.getRoots()) {
            if (countryAsciiName.equals(root.getAsciiName())) {
                dfsTraversal(root);
            }
        }
    }

    /**
     * For testing purposes only
     */
    private void dfsTraversal(GeoNode currentNode) {
        System.out.println(currentNode.getAsciiName() + " at depth " + currentNode.getDepth());
        for (GeoNode child : currentNode.getChildren()) {
            dfsTraversal(child);
        }
    }
}
