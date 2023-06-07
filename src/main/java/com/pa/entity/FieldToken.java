package com.pa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The <tt>FieldToken</tt> class models a token resulted after the normalization process, with its corresponding field
 * <ul>
 * <li><tt>fieldId = 0</tt> means <b>country</b> field</li>
 * <li><tt>fieldId = 1</tt> means <b>state</b> field</li>
 * <li><tt>fieldId = 2</tt> means <b>city</b> field</li>
 * </ul>
 *
 * @author Alex Neagu
 * @author Cristian Fiodorov
 */
@Data
@AllArgsConstructor
public class FieldToken {
    private String token;
    private Integer fieldId;
}
