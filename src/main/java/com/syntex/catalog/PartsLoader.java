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
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.syntex.types.Part;
import com.syntex.types.PcParts.CaseAccessoryPart;
import com.syntex.types.PcParts.CaseFanPart;
import com.syntex.types.PcParts.CasePart;
import com.syntex.types.PcParts.CpuCoolerPart;
import com.syntex.types.PcParts.CpuPart;
import com.syntex.types.PcParts.ExternalHardDrivePart;
import com.syntex.types.PcParts.FanControllerPart;
import com.syntex.types.PcParts.HeadphonesPart;
import com.syntex.types.PcParts.InternalHardDrivePart;
import com.syntex.types.PcParts.KeyboardPart;
import com.syntex.types.PcParts.MemoryPart;
import com.syntex.types.PcParts.MonitorPart;
import com.syntex.types.PcParts.MotherboardPart;
import com.syntex.types.PcParts.MousePart;
import com.syntex.types.PcParts.OpticalDrivePart;
import com.syntex.types.PcParts.PowerSupplyPart;
import com.syntex.types.PcParts.SoundCardPart;
import com.syntex.types.PcParts.SpeakersPart;
import com.syntex.types.PcParts.ThermalPastePart;
import com.syntex.types.PcParts.UpsPart;
import com.syntex.types.PcParts.VideoCardPart;
import com.syntex.types.PcParts.WebcamPart;
import com.syntex.types.PcParts.WiredNetworkCardPart;
import com.syntex.types.PcParts.WirelessNetworkCardPart;

public class PartsLoader {
    private static final String DATASET_PATH = "src/main/resources/pc-part-dataset/";
    private ObjectMapper mapper;
    private Map<String, List<? extends Part>> partsMap;
    private Map<String, ArrayNode> rawJsonMap;

    public PartsLoader() {
        this.mapper = new ObjectMapper();
        // Configure mapper for field access
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        // Ignore unknown properties in JSON
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // Allow polymorphic deserialization without type info
        mapper.disable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE);

        // In the PartsLoader constructor, add this line:
        mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);

        this.partsMap = new HashMap<>();
        this.rawJsonMap = new HashMap<>();
    }

    public void loadAllParts() {
        try (Stream<Path> paths = Files.list(Paths.get(DATASET_PATH))) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".json"))
                    .forEach(path -> {
                        try {
                            loadPartFile(path);
                        } catch (Exception e) {
                            System.err.println("Error loading file: " + path.getFileName());
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            System.err.println("Error accessing dataset directory: " + e.getMessage());
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

            // Store the raw JSON data
            rawJsonMap.put(partType, jsonArray);

            // Determine the correct class based on partType
            Class<? extends Part> partClass = getPartClassForType(partType);

            List<Part> validParts = new ArrayList<>();
            int failed = 0;
            int nullParts = 0; // Count of null parts

            for (int i = 0; i < jsonArray.size(); i++) {
                ObjectNode node = (ObjectNode) jsonArray.get(i);

                // Add type field if not present
                if (!node.has("type")) {
                    node.put("type", partType);
                }

                try {
                    // Convert node to the appropriate Part subclass
                    Part part = mapper.treeToValue(node, partClass);
                    if (part != null) {
                        validParts.add(part);
                    } else {
                        nullParts++;
                    }
                } catch (Exception ex) {
                    failed++;
                }
            }

            partsMap.put(partType, validParts);

            // System.out.println("Loaded " + validParts.size() + " items of type: " +
            // partType);
            // if (failed > 0) {
            // System.out.println("Skipped " + failed + " faulty items of type: " +
            // partType);
            // }
            // if (nullParts > 0) {
            // System.out.println("Skipped " + nullParts + " null items of type: " +
            // partType);
            // }

        } catch (Exception e) {
            System.err.println("Error loading " + partType);
            e.printStackTrace();
        }
    }

    private Class<? extends Part> getPartClassForType(String partType) {
        switch (partType) {
            case "cpu":
                return CpuPart.class;
            case "cpu-cooler":
                return CpuCoolerPart.class;
            case "motherboard":
                return MotherboardPart.class;
            case "memory":
                return MemoryPart.class;
            case "internal-hard-drive":
                return InternalHardDrivePart.class;
            case "video-card":
                return VideoCardPart.class;
            case "case":
                return CasePart.class;
            case "power-supply":
                return PowerSupplyPart.class;
            case "monitor":
                return MonitorPart.class;
            case "sound-card":
                return SoundCardPart.class;
            case "wired-network-card":
                return WiredNetworkCardPart.class;
            case "wireless-network-card":
                return WirelessNetworkCardPart.class;
            case "headphones":
                return HeadphonesPart.class;
            case "keyboard":
                return KeyboardPart.class;
            case "mouse":
                return MousePart.class;
            case "speakers":
                return SpeakersPart.class;
            case "webcam":
                return WebcamPart.class;
            case "case-accessory":
                return CaseAccessoryPart.class;
            case "case-fan":
                return CaseFanPart.class;
            case "fan-controller":
                return FanControllerPart.class;
            case "thermal-paste":
                return ThermalPastePart.class;
            case "external-hard-drive":
                return ExternalHardDrivePart.class;
            case "optical-drive":
                return OpticalDrivePart.class;
            case "ups":
                return UpsPart.class;
            default:
                // Fall back to generic Part if type is unknown
                System.err.println("Unknown part type: " + partType + ", falling back to generic Part");
                return Part.class;
        }
    }

    public <T extends Part> List<T> getPartsOfType(String partType, Class<T> clazz) {
        @SuppressWarnings("unchecked")
        List<T> parts = (List<T>) partsMap.getOrDefault(partType, new ArrayList<>());
        return parts;
    }

    public Map<String, List<? extends Part>> getAllPartsMap() {
        return partsMap;
    }

    public List<? extends Part> getAllParts() {
        List<Part> allParts = new ArrayList<>();
        for (List<? extends Part> partList : partsMap.values()) {
            allParts.addAll(partList);
        }
        return allParts;
    }

    public void printSummary() {
        System.out.println("Loaded parts summary:");
        for (Map.Entry<String, List<? extends Part>> entry : partsMap.entrySet()) {
            String partType = entry.getKey();
            List<? extends Part> parts = entry.getValue();

            if (parts.isEmpty()) {
                continue;
            }

            int count = parts.size();
            System.out.println(partType + ": " + count + " items");
        }
    }

    /**
     * Prints the raw JSON data for the first three elements from each part type in
     * the dataset.
     * If fewer than three elements exist, prints all available elements.
     */
    public void printFirstThreeElements() {
        try {
            System.out.println("First three elements (raw JSON) of each part type:");
            for (Map.Entry<String, ArrayNode> entry : rawJsonMap.entrySet()) {
                String partType = entry.getKey();
                ArrayNode jsonArray = entry.getValue();

                System.out.println("\n=== " + partType.toUpperCase() + " (" + jsonArray.size() + " total) ===");

                // Get the minimum between 3 and the actual size of the array
                int elementsToShow = Math.min(3, jsonArray.size());

                if (elementsToShow == 0) {
                    System.out.println("No elements available");
                } else {
                    for (int i = 0; i < elementsToShow; i++) {
                        // Pretty print JSON with indentation
                        String prettyJson = mapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(jsonArray.get(i));
                        System.out.println((i + 1) + ".\n" + prettyJson);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error printing JSON elements: " + e.getMessage());
            e.printStackTrace();
        }
    }
}