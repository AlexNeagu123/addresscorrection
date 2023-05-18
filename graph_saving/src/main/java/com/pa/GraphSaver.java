package com.pa;

import java.io.IOException;
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

        }
    }

    private void levelTree(GeoGraph tree) {

    }

    private GeoGraph generateTree(GeoName nodeData) {
        GeoGraph tree = new GeoGraph();
        GeoNode currentNode = new GeoNode(nodeData.getAsciiName(), 0);
        tree.addRoot(currentNode);
        dfsTraversal(tree, currentNode, nodeData);
        return tree;
    }

    private void dfsTraversal(GeoGraph tree, GeoNode currentNode, GeoName nodeData) {
        for (GeoName childData : nodeData.getChildren()) {
            GeoNode childNode = new GeoNode(nodeData.getAsciiName(), currentNode.getDepth() + 1);
            currentNode.addChild(childNode);
            tree.addNode(childNode);
            dfsTraversal(tree, childNode, childData);
        }
    }

    public void saveTo(String savePath) throws IOException {
        this.graph.saveToFile(savePath);
    }
}
