package com.syntex.types;

import lombok.Data;
import lombok.EqualsAndHashCode;

public class PcParts {

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class CpuPart extends Part {
        private int core_count;
        private double core_clock;
        private double boost_clock;
        private int tdp;
        private String graphics;
        private boolean smt;
    }
}