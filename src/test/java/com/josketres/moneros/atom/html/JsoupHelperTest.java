package com.josketres.moneros.atom.html;

import org.junit.Test;

import java.nio.file.Paths;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class JsoupHelperTest {

    @Test
    public void should_connect_http_urls() {

        assertThat(JsoupHelper.connectAndGet("http://www.google.com"), notNullValue());
    }

    @Test
    public void should_connect_non_http_urls() {

        assertThat(JsoupHelper.connectAndGet(pathOf("src/test/resources/patricio-monero-article.html")), notNullValue());
    }

    private String pathOf(String relativeUrl) {

        return Paths.get(relativeUrl).toAbsolutePath().toUri().toString();
    }
}