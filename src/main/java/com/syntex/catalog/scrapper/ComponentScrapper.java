package com.syntex.catalog.scrapper;

public abstract class ComponentScrapper {

    protected String partName;
    protected String url;

    protected int pageNumber = 1;

    public ComponentScrapper(String partName) {
        this.partName = partName;
        this.url = "https://pcpartpicker.com/products/" + partName + "/#xcx=0";
    }

    public abstract void load();
}
