package com.pa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The <tt>GeoName</tt> class models an object deserialized from the <b>geoNames.json</b> file stored in Azure.
 * <p>
 * The <b>geoNames.json</b> file is crucial in the initialization process
 * <p>
 * When the server is initialized, a request is being made to the Azure container and the <tt>json</tt> file is received via http
 * as list of <tt>GeoName</tt> object
 *
 * @author Alex Neagu
 * @author Cristian Fiodorov
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeoName {
    private String name;
    private String asciiName;
    private Long geoNameId;
    private String type;
    private List<String> alternateNames;
    private List<GeoName> children;
}