package com.syntex.catalog.scrapper;

import com.syntex.configuration.Configuration;

public class PCPPScrapper {

    private CPUScrapper cpuScrapper;
    private Configuration config;

    public PCPPScrapper(Configuration config) {
        this.config = config;
    }

    public void loadParts() {
        this.cpuScrapper = new CPUScrapper(config);

        this.cpuScrapper.load();
    }

}
