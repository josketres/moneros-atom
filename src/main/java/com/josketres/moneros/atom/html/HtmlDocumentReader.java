package com.josketres.moneros.atom.html;

import org.jsoup.nodes.Document;

@FunctionalInterface
public interface HtmlDocumentReader {

    Document readFromUrl(String url);
}
