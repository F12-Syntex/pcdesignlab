package com.syntex.catalog.scrapper;

import com.syntex.configuration.Configuration;

public class PassmarkScrapper {

    private Configuration config;

    public PassmarkScrapper(Configuration config) {
        this.config = config;
    }

    public void loadParts() {
        System.out.println("Loading parts from Passmark...");   
    }

}
