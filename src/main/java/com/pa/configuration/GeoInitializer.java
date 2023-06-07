package com.pa.configuration;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class is a {@link CommandLineRunner} that is executed when the server is started
 * <p>
 * A list of {@link GeoName} objects is extracted from the <b>geoNames.json</b> file located in the <b>resources</b> folder
 * <p>
 * All the data is parsed and a <tt>forest</tt> is built in memory
 * <p>
 * The {@code nameToNodeMap} and {@code nodeToAlternativeMap} beans are also initialized
 * <p>
 * If some tree has more than three levels, it is leveled to three levels by keeping the nodes on the first two levels the same
 * and reattaching the rest of the nodes
 *
 * @author Alex Neagu
 * @author Cristian Fiodorov
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class GeoInitializer implements CommandLineRunner {
    private final GeoGraph geoGraph;
    private final Multimap<String, GeoNode> nameToNodeMap;
    private final Multimap<GeoNode, String> nodeToAlternativeMap;
    private static final String URI = "https://addresscorrection.blob.core.windows.net/geonames/deployment_geoNames.json";

    @Override
    public void run(String... args) {
        try {
            List<GeoName> graphData = getGeoDataFromAzure();
            buildGraphFromData(graphData);
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }

    public List<GeoName> getGeoDataFromAzure() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<GeoName>> response = restTemplate.exchange(
                URI, HttpMethod.GET, null, new ParameterizedTypeReference<List<GeoName>>() {
                }
        );
        if (response.getStatusCode().isError()) {
            throw new IOException("Couldn't read the file from Azure");
        }
        return response.getBody();
    }

    /**
     * Initializes the beans and builds the entire graph in memory
     *
     * @param graphData The list of {@link GeoName} deserialized from the <b>geoNames.json</b> file
     */
    private void buildGraphFromData(List<GeoName> graphData) {
        for (GeoName rootData : graphData) {
            if (rootData == null) {
                continue;
            }
            GeoNode root = generateRoot(rootData);
            this.geoGraph.addRoot(root);
        }
        reduceTreeLevels();

        log.info("Graph successfully initialized!");
    }

    /**
     * Generates a tree whose root is mapped by the {@link GeoName} object received as parameter
     *
     * @param rootData The {@link GeoName} that corresponds to the root of this tree
     * @return The root of the tree represented as a {@link GeoNode} object
     */
    private GeoNode generateRoot(GeoName rootData) {
        GeoNode root = new GeoNode(rootData.getAsciiName(), rootData.getGeoNameId(), 1, null);
        dfsTraversal(root, rootData);
        return root;
    }

    /**
     * Using the information in each {@link GeoName} object, performs a dfs traversal in order to generate the entire tree
     * <p>
     * At each step an update in the <tt>nodeToAlternativeMap</tt> and <tt>nameToNodeMap</tt> beans is made
     *
     * @param currentNode The current node of the tree represented by a {@link GeoNode} object
     * @param nodeData    Further data about the current node stored as a {@link GeoName} object
     */
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

    private void reduceTreeLevels() {
        for (GeoNode root : geoGraph.getRoots()) {
            levelReducingTraversal(root, root);
        }
    }

    /**
     * If the tree generated by the {@code generateRoot} method has more than three levels,
     * only the nodes located on the first two levels are kept, the rest of the nodes are being reattached to the
     * node on second level
     * <p>
     * All the above verifications are performed in a <tt>dfs</tt> type traversal
     *
     * @param currentNode The current node of the tree represented by a {@link GeoNode} object
     * @param parentNode  The node that should be the parent of {@code currentNode}
     */
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
}
