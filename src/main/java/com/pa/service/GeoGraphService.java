package com.pa.service;

import com.pa.entity.Branch;
import com.pa.entity.GeoNode;
import org.springframework.stereotype.Service;

@Service
public class GeoGraphService {
    public Branch getBranch(GeoNode geoNode) {
        return new Branch(geoNode.getParent().getParent(), geoNode.getParent(), geoNode);
    }
}
