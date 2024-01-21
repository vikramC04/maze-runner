package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Config {
    private static final Logger logger = LogManager.getLogger();
    private String filepath;
    private String path_sequence;

    public Config(String[] args) {

    }

    public void assessValidPath() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
    }

    public void configure(String[] args) throws ParseException  {
        logger.info("** Starting Maze Runner");
        Options options = new Options();
        options.addOption("i", true, "Path to file containing maze");
        options.addOption("p", true, "Path Sequence to verify");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        this.filepath = cmd.getOptionValue("i", "./examples/small.maz.txt");
        this.path_sequence = cmd.getOptionValue("p");

        logger.info("**** Reading the maze from file " + filepath);
    }
}
