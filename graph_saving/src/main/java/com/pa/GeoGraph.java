package com.pa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class GeoGraph implements Serializable {
    private final List<GeoNode> nodes;
    private final List<GeoNode> roots;

    public GeoGraph() {
        this.nodes = new ArrayList<>();
        this.roots = new ArrayList<>();
    }

    public void addNode(GeoNode node) {
        nodes.add(node);
    }

    public void addRoot(GeoNode node) {
        roots.add(node);
        addNode(node);
    }

    public void saveToFile(String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(this);
        }
    }

    public static GeoGraph loadFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (GeoGraph) ois.readObject();
        }
    }
}
