package com.syntex.catalog;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntex.configuration.Configuration;

public class Catalog {

    private static final String CONFIG_FILE = "config.json";
    // private PCPPScrapper pcpPScrapper;
    private Configuration config;
    private PartsLoader dataSetLoader;

    public void loadCatalog() {
        this.config = loadConfig();

        // load the predownloaded data
        this.dataSetLoader = new PartsLoader();
        this.dataSetLoader.loadAllParts();
        this.dataSetLoader.printSummary();
        // this.dataSetLoader.printFirstThreeElements();

        // this.pcpPScrapper = new PCPPScrapper(config);
        // this.pcpPScrapper.loadParts();
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