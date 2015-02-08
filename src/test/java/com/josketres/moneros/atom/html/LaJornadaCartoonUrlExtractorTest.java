package com.josketres.moneros.atom.html;

import com.josketres.moneros.atom.FileHtmlDocumentReader;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class LaJornadaCartoonUrlExtractorTest {

    @Test
    public void should_extract_the_image_url_from_the_html_page() {

        assertThat(new LaJornadaCartoonUrlExtractor()
                        .extract(FileHtmlDocumentReader::readFromFile,
                                "src/test/resources/jornada-unam-mx-article.html"),
                equalTo("http://moneros-atom.com/cartones/magu.jpg"));
    }
}