package com.josketres.moneros.atom.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Caches already fetched html documents to avoid duplicated http requests.
 */
public class CachedHtmlDocumentReader implements HtmlDocumentReader {

    private Map<String, Document> cache = new HashMap<>();

    @Override
    public Document readFromUrl(String url) {

        if (!cache.containsKey(url)) {
            cache.put(url, fetchDocument(url));
        }
        return cache.get(url);
    }

    private Document fetchDocument(String url) {

        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException("Error connecting to url " + url, e);
        }
    }
}
