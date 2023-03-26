package com.example.springbootstartertestmvc.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

/**
 * @author Zhurenkov Pavel 24.03.2023
 */

@Value
@FieldNameConstants
public class PersonCreateEditDto {

    String name;

}
