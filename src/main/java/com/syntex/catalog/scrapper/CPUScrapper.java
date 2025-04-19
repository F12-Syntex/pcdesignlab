package com.syntex.catalog.scrapper;

import com.syntex.configuration.Configuration;

public class CPUScrapper extends ComponentScrapper {
    public CPUScrapper(Configuration config) {
        super(config, "cpu");
    }

    @Override
    public void load() {
        String content = read();
        String fileName = "cpu_" + pageNumber + ".html";

        System.out.println(content);
        try {
            // Save the content to a file
            java.nio.file.Files.write(java.nio.file.Paths.get(fileName), content.getBytes());
            System.out.println("Saved CPU data to " + fileName);
        } catch (java.io.IOException e) {
            System.err.println("Failed to save CPU data: " + e.getMessage());
        }


        
    }
}