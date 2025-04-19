package com.syntex.catalog.scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.syntex.configuration.Configuration;

public abstract class ComponentScrapper {

    protected Configuration config;
    protected String partName;
    protected String url;
    protected int pageNumber = 1;

    public ComponentScrapper(Configuration config, String partName) {
        this.config = config;
        this.partName = partName;
        this.url = "https://pcpartpicker.com/products/" + partName + "/#xcx=0";
    }

    protected String read() {
        String html = "";
        String targetUrl = this.url + "&page=" + pageNumber; // Changed ?page to &page since URL already has a ?
                                                             // parameter

        try {
            // Add more browser-like headers and behaviors
            Document doc = Jsoup.connect(targetUrl)
                    .userAgent(
                            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
                    .header("Accept",
                            "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
                    .header("Accept-Language", "en-US,en;q=0.5")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Connection", "keep-alive")
                    .header("Upgrade-Insecure-Requests", "1")
                    .header("Sec-Fetch-Dest", "document")
                    .header("Sec-Fetch-Mode", "navigate")
                    .header("Sec-Fetch-Site", "none")
                    .header("Sec-Fetch-User", "?1")
                    .header("Cache-Control", "max-age=0")
                    .referrer("https://pcpartpicker.com/")
                    .timeout(30_000)
                    .followRedirects(true)
                    .get();

            // Add a random delay between 1-3 seconds to appear more human-like
            try {
                // Random delay between 1000ms and 3000ms
                long randomDelay = 1000 + (long) (Math.random() * 2000);
                Thread.sleep(randomDelay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Get the HTML content after waiting
            html = doc.html();
        } catch (Exception e) {
            System.err.println("Error fetching URL: " + targetUrl);
            System.err.println("Error details: " + e.getMessage());
            e.printStackTrace();
        }

        return html;
    }

    public abstract void load();
}