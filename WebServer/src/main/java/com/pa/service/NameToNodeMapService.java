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
    public void printBranches(String locationAsciiName) {
        for (GeoNode node : nameToNodeMap.get(locationAsciiName)) {
            if (node.getDepth() != 3) {
                continue;
            }
            System.out.println("A candidate branch was found");
            System.out.println(node.getParent().getParent().getAsciiName() + " - " +
                    node.getParent().getAsciiName() + " - " + node.getAsciiName());
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
