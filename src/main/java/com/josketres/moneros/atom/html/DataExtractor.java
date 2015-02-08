package com.josketres.moneros.atom.html;

@FunctionalInterface
public interface DataExtractor<T> {

    HtmlDocumentReader DEFAULT_READER = new CachedHtmlDocumentReader();

    T extract(String url);
}
