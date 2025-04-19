package com.syntex.catalog.scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.syntex.configuration.Configuration;

public abstract class ComponentScrapper {

    protected Configuration config;

    public ComponentScrapper(Configuration config) {
        this.config = config;
    }

    protected String read(String url) {

        String html = "";
        String targetUrl = url;
        String referer = url.substring(0, url.lastIndexOf("/"));
        if (referer.contains("?")) {
            referer = referer.substring(0, referer.lastIndexOf("?"));
        }
        if (referer.contains("#")) {
            referer = referer.substring(0, referer.lastIndexOf("#"));
        }
        if (referer.contains("/")) {
            referer = referer.substring(0, referer.lastIndexOf("/"));
        }

        try {
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
                    .referrer(referer)
                    .timeout(30_000)
                    .followRedirects(true)
                    .get();

            try {
                long randomDelay = 1000 + (long) (Math.random() * 500);
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