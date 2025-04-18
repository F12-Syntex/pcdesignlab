package com.syntex.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import com.syntex.types.Part;

import lombok.Data;

@Data
public class Configuration {
    public Configuration() {
    }

    private CachedPartsEntry cpuEntry;

    public static Configuration DEFAULT_DATA() {
        Configuration data = new Configuration();

        CachedPartsEntry cpuEntry = new CachedPartsEntry();
        cpuEntry.setPage(1);
        cpuEntry.setCpuParts(new Part[0]);

        data.setCpuEntry(cpuEntry);

        return data;
    }

    /**
     * Saves the current configuration to a file (default: "config.json")
     * 
     * @throws IOException if save fails
     */
    public void save() throws IOException {
        save("config.json");
    }

    /**
     * Saves the current configuration to the specified file.
     * 
     * @param filePath Path to the file (e.g., "config.json")
     * @throws IOException if save fails
     */
    public void save(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File(filePath), this);
    }
}