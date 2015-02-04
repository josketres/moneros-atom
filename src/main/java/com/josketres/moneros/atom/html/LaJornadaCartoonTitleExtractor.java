package com.josketres.moneros.atom.html;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class LaJornadaCartoonTitleExtractor implements DataExtractor<String> {

    @Override
    public String extract(Document doc) {

        Element article = doc.getElementsByClass("cartones-cont").first();
        Element title = article.getElementsByClass("title").first();
        return title.text();
    }
}
