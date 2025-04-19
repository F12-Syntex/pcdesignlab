package com.syntex.catalog;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.syntex.types.Part;
import com.syntex.types.PcParts;

public class PartsLoader {
    private static final String DATASET_PATH = "src/main/resources/pc-part-dataset/";
    private ObjectMapper mapper;
    private Map<String, List<? extends Part>> partsMap;

    public PartsLoader() {
        this.mapper = new ObjectMapper();
        // Configure mapper for field access
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        // Ignore unknown properties in JSON
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // Allow polymorphic deserialization without type info
        mapper.disable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE);

        this.partsMap = new HashMap<>();
    }

    public void loadAllParts() {
        try (Stream<Path> paths = Files.list(Paths.get(DATASET_PATH))) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".json"))
                    .filter(path -> path.getFileName().toString().startsWith("cpu.")) // Only load CPU files
                    .forEach(this::loadPartFile);
        } catch (IOException e) {
            System.err.println("Error loading parts: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadPartFile(Path filePath) {
        String fileName = filePath.getFileName().toString();
        String partType = fileName.substring(0, fileName.lastIndexOf('.'));

        try {
            // Read the JSON file into a String
            String jsonContent = new String(Files.readAllBytes(filePath));

            // Parse JSON to add type field if it's missing
            ArrayNode jsonArray = mapper.readValue(jsonContent, ArrayNode.class);
            for (int i = 0; i < jsonArray.size(); i++) {
                ObjectNode node = (ObjectNode) jsonArray.get(i);
                // Add type field if not present
                if (!node.has("type")) {
                    node.put("type", "cpu");
                }
            }

            // Convert the modified JSON back to string
            String modifiedJson = mapper.writeValueAsString(jsonArray);

            // Read the modified JSON into a list of CPUs
            List<PcParts.CpuPart> cpuParts = mapper.readValue(modifiedJson,
                    mapper.getTypeFactory().constructCollectionType(List.class, PcParts.CpuPart.class));

            partsMap.put(partType, cpuParts);

        } catch (Exception e) {
            System.err.println("Error loading " + partType + " parts: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public <T extends Part> List<T> getPartsOfType(String partType, Class<T> clazz) {
        @SuppressWarnings("unchecked")
        List<T> parts = (List<T>) partsMap.getOrDefault(partType, new ArrayList<>());
        return parts;
    }

    public Map<String, List<? extends Part>> getAllParts() {
        return partsMap;
    }

    public void printSummary() {
        System.out.println("Loaded parts summary:");
        for (Map.Entry<String, List<? extends Part>> entry : partsMap.entrySet()) {
            String partType = entry.getKey();
            List<? extends Part> parts = entry.getValue();
            int count = parts.size();
            System.out.println(partType + ": " + count + " items");
            System.out.println("Sample: " + (count > 0 ? parts.get(0).toString() : "No items available"));
        }
    }
}