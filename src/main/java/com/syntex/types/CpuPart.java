package com.syntex.types;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CpuPart extends Part {
    private String name;
    private int rating;
    private int price;
    private int cores;
    private int performanceCoreClock;
    private int performanceCoreBoostClock;
    private String architecture;
    private int tdp;
    private String integratedGraphics;
}
