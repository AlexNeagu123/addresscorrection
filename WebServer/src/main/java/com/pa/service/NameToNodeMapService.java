package com.pa.service;

import com.google.common.collect.Multimap;
import com.pa.entity.GeoNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NameToNodeMapService {
    private final Multimap<String, GeoNode> nameToNodeMap;

    /**
     * For testing purposes only
     */
    public void printBranch(String countryAsciiName) {
        for (GeoNode node : nameToNodeMap.get(countryAsciiName)) {
            GeoNode root = getRoot(node);
            dfsTraversal(root);
        }
    }

    private GeoNode getRoot(GeoNode node) {
        if (node.getParent() == null) {
            return node;
        }
        return getRoot(node.getParent());
    }

    private void dfsTraversal(GeoNode currentNode) {
        System.out.println(currentNode.getAsciiName() + " at depth " + currentNode.getDepth());
        for (GeoNode child : currentNode.getChildren()) {
            dfsTraversal(child);
        }
    }

}
