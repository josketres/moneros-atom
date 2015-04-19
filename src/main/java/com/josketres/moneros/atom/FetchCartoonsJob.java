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

import java.io.*;
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

        LocalDate initialDate = LocalDate.of(2015, Month.FEBRUARY, 1);

        LOG.info("Fetching cartoons, using current atom url: ({}) and initial-date: ({}) ",
                CURRENT_ATOM_URL, initialDate);

        SyndFeed feed = new FeedBuilder()
                .initialDate(initialDate)
                .entries(new PatricioRss(),
                        new LaJornadaRss(),
                        new QuchoRss())
                .mergeWith(readCurrentFromServer())
                .build();

        LOG.info("Writing feed");
        writeFeed(feed);

        LOG.info("Publishing feed to GitHub pages");
        publishInGitHubPages();

        LOG.info("Done");
    }

    private void publishInGitHubPages() {

        try {
            Process process = new ProcessBuilder(System.getProperty("user.dir") + "/deploy_website.sh").start();
            log(process.getInputStream());
            log(process.getErrorStream());

            if (process.exitValue() == 0) {
                LOG.info("Publish successful");
            } else {
                LOG.info("Publish step exited with code {}", process.exitValue());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void log(InputStream stream) throws IOException {
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line = null;
        while ((line = reader.readLine()) != null) {
            LOG.info(line);
        }
    }

    private void writeFeed(SyndFeed feed) {

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
