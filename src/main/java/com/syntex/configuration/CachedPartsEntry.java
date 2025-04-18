package com.syntex.configuration;

import com.syntex.types.Part;

import lombok.Data;

@Data
public class CachedPartsEntry {
    public CachedPartsEntry() {}
    private int page;
    private Part[] cpuParts;
}
