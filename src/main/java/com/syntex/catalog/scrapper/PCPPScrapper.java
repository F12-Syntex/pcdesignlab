package com.syntex.catalog.scrapper;

public class PCPPScrapper {

    private CPUScrapper cpuScrapper;

    public void loadParts() {
        this.cpuScrapper = new CPUScrapper();

        
        this.cpuScrapper.load();
    }

}
