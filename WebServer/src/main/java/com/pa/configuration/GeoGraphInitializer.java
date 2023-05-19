package com.pa.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pa.entity.GeoGraph;
import com.pa.entity.GeoName;
import com.pa.entity.GeoNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class GeoGraphInitializer implements CommandLineRunner {
    private final GeoGraph geoGraph;

    @Override
    public void run(String... args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<GeoName> graphData = objectMapper.readValue(new File("src/main/resources/static/geoNames.json"), new TypeReference<List<GeoName>>() {
            });
            buildGraphFromData(graphData);
        } catch (IOException ex) {
            log.error("Couldn't deserialize the resource JSON file: " + ex.getMessage());
            System.exit(13);
        }
    }

    private void buildGraphFromData(List<GeoName> graphData) {
        for (GeoName rootData : graphData) {
            if (rootData == null) {
                continue;
            }
            GeoGraph tree = generateTree(rootData);
            reduceTreeLevels(tree);
            insertTreeIntoGraph(tree);
        }
        log.info("Graph successfully initialized!");
    }

    private GeoGraph generateTree(GeoName rootData) {
        GeoGraph tree = new GeoGraph();
        GeoNode root = new GeoNode(rootData.getAsciiName(), 1, null);
        tree.addRoot(root);
        dfsTraversal(tree, root, rootData);
        return tree;
    }

    private void dfsTraversal(GeoGraph tree, GeoNode currentNode, GeoName nodeData) {
        for (GeoName childData : nodeData.getChildren()) {
            if (childData == null) {
                continue;
            }
            GeoNode childNode = new GeoNode(childData.getAsciiName(), currentNode.getDepth() + 1, currentNode);
            currentNode.addChild(childNode);
            dfsTraversal(tree, childNode, childData);
        }
    }

    private void reduceTreeLevels(GeoGraph tree) {
        for (GeoNode root : tree.getRoots()) {
            // only one iteration expected
            levelReducingTraversal(root, root);
        }
    }

    private void levelReducingTraversal(GeoNode currentNode, GeoNode parentNode) {
        List<GeoNode> children = new ArrayList<>(currentNode.getChildren());
        if (currentNode.getDepth() < 3) {
            for (GeoNode child : children) {
                levelReducingTraversal(child, currentNode);
            }
            return;
        }

        currentNode.setChildren(new ArrayList<>());
        for (GeoNode child : children) {
            child.setDepth(3);
            child.setParent(parentNode);
            parentNode.addChild(child);
            levelReducingTraversal(child, parentNode);
        }
    }

    private void insertTreeIntoGraph(GeoGraph tree) {
        for (GeoNode root : tree.getRoots()) {
            this.geoGraph.addRoot(root);
        }
    }
}
