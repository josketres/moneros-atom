package com.josketres.moneros.atom.html;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Extracts the image URL from an html entry.
 */
public class PatricioCartoonUrlExtractor implements DataExtractor<String> {

    private HtmlDocumentReader reader = DEFAULT_READER;

    public DataExtractor<String> setReader(HtmlDocumentReader reader) {

        this.reader = reader;
        return this;
    }

    @Override
    public String extract(String url) {

        Document doc = reader.readFromUrl(url);
        Element article = doc.getElementsByTag("article").first();
        Element img = article.getElementsByTag("img").first();
        return img.attr("src");
    }
}
