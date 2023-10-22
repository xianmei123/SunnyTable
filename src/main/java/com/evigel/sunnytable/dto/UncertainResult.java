package com.evigel.sunnytable.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UncertainResult {
    private double ua;
    private double ub;
    private double u;
}
