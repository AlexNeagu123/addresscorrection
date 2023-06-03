package com.pa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Branch {
    private GeoNode countryNode;
    private GeoNode stateNode;
    private GeoNode cityNode;

    @Override
    public String toString() {
        return this.countryNode.getAsciiName() + ", " + this.stateNode.getAsciiName() + ", " + this.cityNode.getAsciiName();
    }
}
