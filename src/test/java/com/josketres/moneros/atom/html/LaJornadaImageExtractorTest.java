package com.josketres.moneros.atom.html;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class LaJornadaImageExtractorTest {

    @Test
    public void should_extract_the_image_url_from_the_html_page() {

        assertThat(new LaJornadaImageExtractor().extractImageFromUrl(pathOf("src/test/resources/jornada-unam-mx-article.html")),
                equalTo("http://moneros-atom.com/cartones/magu.jpg"));
    }

    private String pathOf(String relativeUrl) {

        return Paths.get(relativeUrl).toAbsolutePath().toUri().toString();
    }
}