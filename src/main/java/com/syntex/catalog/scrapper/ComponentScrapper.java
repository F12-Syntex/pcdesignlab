package com.syntex.catalog.scrapper;

import com.syntex.configuration.Configuration;

public abstract class ComponentScrapper {

    protected Configuration config;

    protected String partName;
    protected String url;

    protected int pageNumber = 1;

    public ComponentScrapper(Configuration config, String partName) {
        this.partName = partName;
        this.url = "https://pcpartpicker.com/products/" + partName + "/#xcx=0";
    }

    public abstract void load();
}
