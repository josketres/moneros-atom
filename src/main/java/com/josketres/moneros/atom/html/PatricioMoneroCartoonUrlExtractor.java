package com.josketres.moneros.atom.html;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Extracts the image URL from an html entry.
 */
public class PatricioMoneroCartoonUrlExtractor implements DataExtractor<String> {

    @Override
    public String extract(Document doc) {
        
        Element article = doc.getElementsByTag("article").first();
        Element img = article.getElementsByTag("img").first();
        return img.attr("src");
    }
}