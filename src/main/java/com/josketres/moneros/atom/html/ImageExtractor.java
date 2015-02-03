package com.josketres.moneros.atom.html;

@FunctionalInterface
public interface ImageExtractor {

    String extractImageFromUrl(String url);
}
