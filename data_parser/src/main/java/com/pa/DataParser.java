package com.pa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DataParser {
    private final List<GeoName> allGeoNames;
    private final Map<Long, List<Long>> parentToChildMap;
    private final Map<Long, GeoName> allGeoNamesMap;

    public DataParser() {
        this.allGeoNames = new ArrayList<>();
        this.parentToChildMap = new HashMap<>();
        this.allGeoNamesMap = new HashMap<>();
    }

    public List<GeoName> getAllGeoNames() {
        if (allGeoNames.isEmpty()) {
            setAllGeoNames();
        }
        return allGeoNames;
    }


    private void setAllGeoNames() {
        getAllGeoNamesMap();
        getHierarchy();
        List<Long> countryIds = getAllCountryIds();

        for (Long countryId : countryIds) {
            allGeoNames.add(allGeoNamesMap.get(countryId));
            Queue<Long> queue = new LinkedList<>();
            queue.add(countryId);

            while (!queue.isEmpty()) {
                Long currentId = queue.poll();
                List<Long> children = parentToChildMap.get(currentId);
                parentToChildMap.remove(currentId);

                GeoName geoName = allGeoNamesMap.get(currentId);
                if(geoName == null) {
                    // It can be some cases where same id have more than one parent
                    continue;
                }

                allGeoNamesMap.remove(currentId);

                if (children != null) {
                    List<GeoName> childrenList = geoName.getChildren();
                    for (Long childId : children) {
                        GeoName child = allGeoNamesMap.get(childId);
                        childrenList.add(child);
                        queue.add(childId);
                    }
                }
            }
        }
    }


    private void getHierarchy() {
        try (Scanner sc = new Scanner(new File("data_parser/src/main/resources/hierarchy.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] split = line.split("\t");
                Long parentId = Long.parseLong(split[0]);
                Long childId = Long.parseLong(split[1]);

                if (!parentToChildMap.containsKey(parentId)) {
                    parentToChildMap.put(parentId, new ArrayList<>());
                }
                parentToChildMap.get(parentId).add(childId);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Long> getAllCountryIds() {
        List<Long> countryIds = new ArrayList<>();
        try (Scanner sc = new Scanner(new File("data_parser/src/main/resources/countryInfo.txt"))) {
            String line = sc.nextLine();
            while (line.startsWith("#")) {
                line = sc.nextLine();
            }

            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String[] split = line.split("\t");
                countryIds.add(Long.parseLong(split[16]));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return countryIds;
    }

    private void getAllGeoNamesMap() {
        try (Scanner sc = new Scanner(new File("data_parser/src/main/resources/allCountries.txt"))) {
            String line = sc.nextLine();
            while (line.startsWith("#")) {
                line = sc.nextLine();
            }

            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String[] split = line.split("\t");

                if (!split[6].trim().equals("P") && !split[6].trim().equals("A")) {
                    continue;
                }

                GeoName geoName = GeoName.builder()
                        .name(split[1])
                        .asciiName(split[2])
                        .geoNameId(Long.parseLong(split[0]))
                        .type(split[6])
                        .alternateNames(new ArrayList<>())
                        .children(new ArrayList<>())
                        .build();

                String[] alternateNames = split[3].split(",");
                for (String alternateName : alternateNames) {
                    geoName.getAlternateNames().add(alternateName.trim());
                }

                allGeoNamesMap.put(geoName.getGeoNameId(), geoName);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
