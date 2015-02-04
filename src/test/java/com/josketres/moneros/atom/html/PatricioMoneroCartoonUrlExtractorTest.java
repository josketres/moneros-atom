package com.josketres.moneros.atom.html;

import org.junit.Test;

import java.nio.file.Paths;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class PatricioMoneroCartoonUrlExtractorTest {

    @Test
    public void should_extract_the_image_url_from_the_html_page() {

        assertThat(new PatricioMoneroCartoonUrlExtractor()
                        .extract(JsoupHelper.connectAndGet(pathOf("src/test/resources/patricio-monero-article.html"))),
                equalTo("http://lajornadajalisco.com.mx/wp-content/uploads/2015/02/Jornadas-1344.jpg"));
    }

    private String pathOf(String relativeUrl) {

        return Paths.get(relativeUrl).toAbsolutePath().toUri().toString();
    }
}