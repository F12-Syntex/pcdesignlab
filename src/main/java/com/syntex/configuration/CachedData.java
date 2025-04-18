package com.syntex.configuration;

import com.syntex.types.Part;

import lombok.Data;

@Data
public class CachedData {
    public CachedData() {
    }

    private CachedPartsEntry cpuEntry;

    public static CachedData DEFAULT_DATA() {
        CachedData data = new CachedData();

        CachedPartsEntry cpuEntry = new CachedPartsEntry();
        cpuEntry.setPage(1);
        cpuEntry.setCpuParts(new Part[0]);

        data.setCpuEntry(cpuEntry);

        return data;
    }
}