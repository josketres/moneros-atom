package com.josketres.moneros.atom.html;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class LaJornadaCartoonUrlExtractor implements DataExtractor<String> {

    private HtmlDocumentReader reader = DEFAULT_READER;

    public DataExtractor<String> setReader(HtmlDocumentReader reader) {

        this.reader = reader;
        return this;
    }

    @Override
    public String extract(String url) {

        Document doc = reader.readFromUrl(url);
        Element article = doc.getElementsByAttributeValue("class", "carton").first();
        Element img = article.getElementsByTag("img").first();
        return img.attr("abs:src");
    }

}
