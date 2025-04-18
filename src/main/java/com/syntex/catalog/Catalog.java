package com.syntex.catalog;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntex.catalog.scrapper.PCPPScrapper;
import com.syntex.configuration.CachedData;

public class Catalog {

    private PCPPScrapper pcpPScrapper;
    private CachedData config;

    public void loadCatalog() {
        this.loadConfig();

        this.pcpPScrapper = new PCPPScrapper();
        this.pcpPScrapper.loadParts();
    }

    private void loadConfig() {
        ObjectMapper mapper = new ObjectMapper();
        File configFile = new File("config.json");

        if (configFile.exists()) {
            try {
                this.config = mapper.readValue(configFile, CachedData.class);
                System.out.println("Loaded config from file.");
            } catch (IOException e) {
                System.err.println("Failed to load config, using empty config. Reason: " + e.getMessage());
                this.config = CachedData.DEFAULT_DATA();
                saveConfig();
            }
        } else {
            System.out.println("Config file not found, creating empty config.");
            this.config = CachedData.DEFAULT_DATA();
            saveConfig();
        }
    }

    private void saveConfig() {
        ObjectMapper mapper = new ObjectMapper();
        File configFile = new File("config.json");
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(configFile, this.config);
            System.out.println("Saved new (empty) config to file.");
        } catch (IOException e) {
            System.err.println("Failed to save config: " + e.getMessage());
        }
    }

    public CachedData getConfig() {
        return config;
    }
}