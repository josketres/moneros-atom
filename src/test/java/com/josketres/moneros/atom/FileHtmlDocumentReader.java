package com.josketres.moneros.atom;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class FileHtmlDocumentReader {

    public static Document readFromFile(String relativeUrl) {

        try {
            String url = pathOf(relativeUrl);
            return Jsoup.parse(new File(new URL(url).toURI()), "UTF-8", "http://moneros-atom.com");
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Reading relativeUrl " + relativeUrl, e);
        }
    }

    private static String pathOf(String relativeUrl) {

        return Paths.get(relativeUrl).toAbsolutePath().toUri().toString();
    }

}

