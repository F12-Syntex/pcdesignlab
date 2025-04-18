package com.syntex.catalog.scrapper;

public class CPUScrapper extends ComponentScrapper {

    public CPUScrapper() {
        super("cpu");
    }

    @Override
    public void load() {
        System.out.println(this.url);
    }

}
