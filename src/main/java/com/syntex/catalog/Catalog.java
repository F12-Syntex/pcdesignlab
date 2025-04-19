package com.syntex.catalog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntex.catalog.scrapper.PassmarkScrapper;
import com.syntex.configuration.Configuration;
import com.syntex.types.Part;

import lombok.Data;

@Data
public class Catalog {

    private static final String CONFIG_FILE = "config.json";
    // private PCPPScrapper pcpPScrapper;
    private Configuration config;
    private PartsLoader dataSetLoader;
    private List<? extends Part> catalog = new ArrayList<>();

    private PassmarkScrapper passmarkScrapper;

    public void loadCatalog() {
        this.config = loadConfig();
        this.passmarkScrapper = new PassmarkScrapper(config);

        // load the predownloaded data
        this.dataSetLoader = new PartsLoader();
        this.dataSetLoader.loadAllParts();
        this.catalog = this.dataSetLoader.getAllParts();

        // now expand our dataset with benchmarks and prices aswell as other data
        this.passmarkScrapper.loadParts();
    }

    private Configuration loadConfig() {
        ObjectMapper mapper = new ObjectMapper();
        File configFile = new File(CONFIG_FILE);

        if (configFile.exists()) {
            try {
                Configuration loaded = mapper.readValue(configFile, Configuration.class);
                System.out.println("Loaded config from file.");
                return loaded;
            } catch (IOException e) {
                System.err.println("Failed to load config, using default. Reason: " + e.getMessage());
            }
        } else {
            System.out.println("Config file not found, creating default config.");
        }

        Configuration defaultConfig = Configuration.DEFAULT_DATA();
        try {
            defaultConfig.save(CONFIG_FILE);
            System.out.println("Default config saved to " + CONFIG_FILE + ".");
        } catch (IOException e) {
            System.err.println("Failed to save default config: " + e.getMessage());
        }
        return defaultConfig;
    }

    public Configuration getConfig() {
        return config;
    }
}