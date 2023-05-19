package com.pa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GraphSaver {
    private final List<GeoName> graphData;
    private final GeoGraph graph;

    public GraphSaver(List<GeoName> graphData) {
        this.graphData = graphData;
        this.graph = new GeoGraph();
        buildGraphFromData();
    }

    private void buildGraphFromData() {
        for (GeoName geoName : this.graphData) {
            GeoGraph tree = generateTree(geoName);
            levelTree(tree);
            insertTreeIntoGraph(tree);
        }
    }

    private void insertTreeIntoGraph(GeoGraph tree) {
        for (GeoNode root : tree.getRoots()) {
            this.graph.addRoot(root);
        }
        for (GeoNode node : tree.getNodes()) {
            this.graph.addNode(node);
        }
    }

    private void levelTree(GeoGraph tree) {
        GeoNode geoNode = tree.getRoots().get(0);
        levelTraversal(geoNode, geoNode);
    }

    private void levelTraversal(GeoNode currentNode, GeoNode parentNode) {
        if (currentNode.getDepth() == 3) {
            List<GeoNode> children = new ArrayList<>(currentNode.getChildren());
            currentNode.setChildren(new ArrayList<>());
            for (GeoNode child : children) {
                parentNode.addChild(child);
                child.setDepth(3);
                levelTraversal(child, parentNode);
            }
        } else {
            for (GeoNode child : currentNode.getChildren()) {
                levelTraversal(child, currentNode);
            }
        }
    }

    private GeoGraph generateTree(GeoName rootData) {
        GeoGraph tree = new GeoGraph();
        GeoNode root = new GeoNode(rootData.getAsciiName(), 0);
        tree.addRoot(root);
        tree.addNode(root);
        dfsTraversal(tree, root, rootData);
        return tree;
    }

    private void dfsTraversal(GeoGraph tree, GeoNode currentNode, GeoName nodeData) {
        for (GeoName childData : nodeData.getChildren()) {
            if(childData == null) {
                System.out.println("Mda");
                return;
            }
            GeoNode childNode = new GeoNode(childData.getAsciiName(), currentNode.getDepth() + 1);
            currentNode.addChild(childNode);
            tree.addNode(childNode);
            dfsTraversal(tree, childNode, childData);
        }
    }

    public void saveTo(String savePath) throws IOException {
        this.graph.saveToFile(savePath);
    }
}
