package com.pa.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Multimap;
import com.pa.entity.GeoGraph;
import com.pa.entity.GeoName;
import com.pa.entity.GeoNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Component
@RequiredArgsConstructor
public class GeoInitializer implements CommandLineRunner {
    private final GeoGraph geoGraph;
    private final Multimap<String, GeoNode> nameToNodeMap;
    private final Multimap<GeoNode, String> nodeToAlternativeMap;

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
        GeoNode root = new GeoNode(rootData.getAsciiName(), rootData.getGeoNameId(), 1, null);
        tree.addRoot(root);
        dfsTraversal(root, rootData);
        return tree;
    }

    private void dfsTraversal(GeoNode currentNode, GeoName nodeData) {
        // extract unique alternate names
        Set<String> uniqueAlternateNames = nodeData.getAlternateNames().stream()
                .map(String::toLowerCase)
                .filter(x -> !x.equals("") && !x.equals(currentNode.getAsciiName().toLowerCase()))
                .collect(Collectors.toSet());

        // add all unique alternative names to the [node - alternative names] multimap with currentNode as key
        nodeToAlternativeMap.putAll(currentNode, new ArrayList<>(uniqueAlternateNames));

        //add to the [name - node] multimap with current ascii name of the node as key
        nameToNodeMap.put(currentNode.getAsciiName().toLowerCase(), currentNode);
        for (String lowercaseAlternateName : nodeToAlternativeMap.get(currentNode)) {
            nameToNodeMap.put(lowercaseAlternateName, currentNode);
        }

        // go deeper in the tree
        for (GeoName childData : nodeData.getChildren()) {
            if (childData == null) {
                continue;
            }
            GeoNode childNode = new GeoNode(childData.getAsciiName(), childData.getGeoNameId(), currentNode.getDepth() + 1, currentNode);
            currentNode.addChild(childNode);
            dfsTraversal(childNode, childData);
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

        // erase children if the node is on the third level
        if (currentNode.getDepth() == 3) {
            currentNode.setChildren(new ArrayList<>());
        }

        for (GeoNode child : children) {
            if (currentNode.getDepth() < 3) {
                // go deeper if node is not on third level
                levelReducingTraversal(child, currentNode);
                continue;
            }
            // if node is on third level, reassign the parent and the depth of the child and go deeper
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
