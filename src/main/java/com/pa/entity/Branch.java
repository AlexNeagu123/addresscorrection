package com.pa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The <tt>Branch</tt> class models a candidate solution found by the algorithm
 * <p>
 * A candidate solution consists of three {@link GeoNode} objects corresponding to a <b>country</b>, <b>state</b> and <b>city</b> field
 * that are in relationship with each other (an edge between country-state and state-city exists).
 *
 * @author Alex Neagu
 * @author Cristian Fiodorov
 */
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
