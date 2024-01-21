package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Config {
    private static final Logger logger = LogManager.getLogger();
    private String path;

    public Config(String[] args) {

    }

    public void assessValidPath() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
    }

    public void configure(String[] args) throws ParseException  {
        logger.info("** Starting Maze Runner");
        Options options = new Options();
        options.addOption("i", true, "Path to file containing maze");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        this.path = cmd.getOptionValue("i", "./examples/small.maz.txt");
        logger.info("**** Reading the maze from file " + path);
    }
}
