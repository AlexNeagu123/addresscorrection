package com.pa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldToken {
    private String token;
    private Integer fieldId;
}
