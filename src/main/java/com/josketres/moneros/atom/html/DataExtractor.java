package com.josketres.moneros.atom.html;

import org.jsoup.nodes.Document;

@FunctionalInterface
public interface DataExtractor<T> {

    T extract(HtmlDocumentReader reader, String url);
}
