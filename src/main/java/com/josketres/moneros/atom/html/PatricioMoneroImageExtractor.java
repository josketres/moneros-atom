package com.josketres.moneros.atom.html;

import java.awt.Image;
import java.io.IOException;
import java.util.function.Function;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Extracts the image URL from an html entry.
 */
public class PatricioMoneroImageExtractor implements ImageExtractor {

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
        Element article = doc.getElementsByTag("article").first();
        Element img = article.getElementsByTag("img").first();
        return img.attr("src");
    }

}
