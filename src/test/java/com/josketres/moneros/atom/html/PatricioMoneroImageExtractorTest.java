package com.josketres.moneros.atom.html;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class PatricioMoneroImageExtractorTest {

    @Test
    public void should_extract_the_image_url_from_the_html_page() {

        assertThat(new PatricioMoneroImageExtractor().extractImageFromUrl(pathOf("src/test/resources/patricio-monero-article.html")),
                equalTo("http://lajornadajalisco.com.mx/wp-content/uploads/2015/02/Jornadas-1344.jpg"));
    }

    private String pathOf(String relativeUrl) {

        return Paths.get(relativeUrl).toAbsolutePath().toUri().toString();
    }
}