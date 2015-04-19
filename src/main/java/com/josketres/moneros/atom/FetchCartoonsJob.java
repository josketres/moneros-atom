package com.josketres.moneros.atom;

import com.josketres.moneros.atom.rss.LaJornadaRss;
import com.josketres.moneros.atom.rss.PatricioRss;
import com.josketres.moneros.atom.rss.QuchoRss;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;
import com.rometools.rome.io.XmlReader;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;

public class FetchCartoonsJob implements Job {

    public static final String CURRENT_ATOM_URL = "http://josketres.github.io/moneros-atom/current.atom";

    final static Logger LOG = LoggerFactory.getLogger(FetchCartoonsJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        LOG.info("Fetching cartoons, using current atom url: " + CURRENT_ATOM_URL);

        SyndFeed feed = new FeedBuilder()
                .initialDate(LocalDate.of(2015, Month.FEBRUARY, 1))
                .entries(new PatricioRss(),
                        new LaJornadaRss(),
                        new QuchoRss())
                .mergeWith(readCurrentFromServer())
                .build();

        try {
            if (!Files.exists(Paths.get("website"))) {
                Files.createDirectory(Paths.get("website"));
                Files.createFile(Paths.get("website/current.atom"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Writer writer = new FileWriter("website/current.atom", false)) {
            new SyndFeedOutput().output(feed, writer, false);
        } catch (IOException | FeedException e) {
            throw new RuntimeException(e);
        }
    }

    private static SyndFeed readCurrentFromServer() {

        try (Reader reader = new XmlReader(new URL(CURRENT_ATOM_URL))) {
            return new SyndFeedInput().build(reader);
        } catch (FeedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}