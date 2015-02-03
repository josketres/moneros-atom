package com.josketres.moneros.atom.html;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupHelper {

    public static Document connectAndGet(String url) {

        try {
            String protocol = new URL(url).getProtocol();
            if ("http".equals(protocol) || "https".equals(protocol)) {
                return Jsoup.connect(url).get();
            } else if ("file".equals(protocol)) {
                return Jsoup.parse(new File(new URL(url).toURI()), "UTF-8", "http://moneros-atom.com");
            } else {
                throw new IllegalArgumentException("Only file, http or https protocols are supported.");
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}

