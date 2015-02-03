package com.josketres.moneros.atom.html;

import java.io.IOException;
import java.net.URL;
import java.util.function.Function;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class LaJornadaImageExtractor implements ImageExtractor {


    @Override
    public String extractImageFromUrl(String url) {

        try {
            return doGetCartoonUrl(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String doGetCartoonUrl(String url) throws IOException {

        Document doc = JsoupHelper.connectAndGet(url);
        Element article = doc.getElementsByAttributeValue("class", "carton").first();
        Element img = article.getElementsByTag("img").first();
        return img.attr("abs:src");
    }
}
