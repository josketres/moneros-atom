package com.josketres.moneros.atom.html;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class LaJornadaCartoonTitleExtractor implements DataExtractor<String> {

    private HtmlDocumentReader reader = DEFAULT_READER;

    public DataExtractor<String> setReader(HtmlDocumentReader reader) {

        this.reader = reader;
        return this;
    }

    @Override
    public String extract(String url) {

        Document doc = reader.readFromUrl(url);
        Element article = doc.getElementsByClass("cartones-cont").first();
        Element title = article.getElementsByClass("title").first();
        return title.text();
    }
}
