package com.josketres.moneros.atom.rss;

import com.josketres.moneros.atom.Cartoon;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

public class QuchoRss extends CartoonRss {

    public static final String FEED_URL = "http://opinion.informador.com.mx/Cartuchos/feed/";

    @Override
    protected Cartoon createCartoon(SyndEntry e) {

        return new Cartoon("Qucho Monero",
                e.getPublishedDate(),
                e.getLink(),
                extractImageFromHtml(e.getContents()),
                e.getTitle());
    }

    private String extractImageFromHtml(List<SyndContent> contents) {

        SyndContent content = contents.get(0);
        Document doc = Jsoup.parse(content.getValue());
        return doc.getElementsByTag("img").attr("src");
    }
}
