package com.syntex.catalog;

import com.syntex.catalog.scrapper.PCPPScrapper;

public class Catalog {

    private PCPPScrapper pcpPScrapper;

    public void loadCatalog() {
        this.pcpPScrapper = new PCPPScrapper();
        this.pcpPScrapper.loadParts();
    }

}
