package com.syntex.catalog.scrapper;

import com.syntex.configuration.Configuration;

public class CPUScrapper extends ComponentScrapper {

    public CPUScrapper(Configuration config) {
        super(config, "cpu");
    }

    @Override
    public void load() {
    }

}
