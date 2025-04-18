package com.syntex;

import com.syntex.catalog.Catalog;


@Data
public class PcDesignLab {

    public static void main(String[] args) {

        Catalog catalog = new Catalog();
        catalog.loadCatalog();
    }

}
