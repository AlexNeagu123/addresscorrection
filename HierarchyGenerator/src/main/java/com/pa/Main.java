package com.pa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    private static final Map<String, String> isoToGeoNameId = new HashMap<>();
    private static final Set<String> geoNameIds = new HashSet<>();
    private static final Map<String, String> admCodeToGeoNameId = new HashMap<>();

    public static void main(String[] args) {
        mapIsoCodesWithIds();
        getGeoNameIds();
        getAdmCodesIds();
        appendToHierarchy();
    }

    private static void mapIsoCodesWithIds() {
        try (Scanner sc = new Scanner(new File("src/main/resources/countryInfo.txt"))) {
            String line = sc.nextLine();
            while (line.startsWith("#")) {
                line = sc.nextLine();
            }

            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String[] split = line.split("\t");
                isoToGeoNameId.put(split[0], split[16]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getGeoNameIds() {
        try (Scanner sc = new Scanner(new File("src/main/resources/hierarchy.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] split = line.split("\t");
                geoNameIds.add(split[1]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getAdmCodesIds() {
        try (Scanner sc = new Scanner(new File("src/main/resources/admin1CodesASCII.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] split = line.split("\t");
                admCodeToGeoNameId.put(split[0], split[3]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void appendToHierarchy(){
        try(Scanner sc = new Scanner(new File("src/main/resources/allCountries.txt"));
            FileWriter fw = new FileWriter(new File("src/main/resources/hierarchy.txt"), true)){

            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] split = line.split("\t");
                String geoNameId = split[0];
                String admType = split[6];
                String admCode = split[8] + "." + split[10];
                String admCodeGeoNameId = admCodeToGeoNameId.get(admCode);

                if(!Objects.equals(admType, "P") || geoNameIds.contains(geoNameId) || admCodeGeoNameId == null){
                    continue;
                }

                if(!geoNameIds.contains(admCodeGeoNameId)){
                    appendRelationToHierarchy(fw, isoToGeoNameId.get(split[8]), admCodeGeoNameId);
                }
                appendRelationToHierarchy(fw, admCodeGeoNameId, geoNameId);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void appendRelationToHierarchy(FileWriter fs, String parentId, String childId){
        try {
            fs.append(parentId).append("\t").append(childId).append("\n");
            geoNameIds.add(childId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}