package com.josketres.moneros.atom.html;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class LaJornadaCartoonUrlExtractor implements DataExtractor<String> {

    @Override
    public String extract(HtmlDocumentReader reader, String url) {

        Document doc = reader.readFromUrl(url);
        Element article = doc.getElementsByAttributeValue("class", "carton").first();
        Element img = article.getElementsByTag("img").first();
        return img.attr("abs:src");
    }

}
