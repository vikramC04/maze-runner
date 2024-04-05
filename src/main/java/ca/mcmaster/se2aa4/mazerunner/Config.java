package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Config {
    private static final Logger logger = LogManager.getLogger();
    private String filepath;
    private String path_sequence;
    private String algorithm;
    private String baseline;

    public Config(String[] args) {

    }

    public String assessValidPath() throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        reader.close();
        return filepath;
    }

    public String configureAlgorithm() {
        return algorithm;
    }

    public String getBaseline() {
        return baseline;
    }

    public String configure(String[] args) throws ParseException  {
        logger.info("** Starting Maze Runner");
        Options options = new Options();
        options.addOption("i", true, "Path to file containing maze");
        options.addOption("p", true, "Path Sequence to verify");
        options.addOption("method", true, "Path Algorithm");
        options.addOption("baseline", true, "Path Algorithm");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        this.filepath = cmd.getOptionValue("i");
        this.path_sequence = cmd.getOptionValue("p");
        if(cmd.getOptionValue("p") != null) {
            this.path_sequence = cmd.getOptionValue("p").replaceAll(" ", "");
        }
        this.baseline = cmd.getOptionValue("baseline");
        this.algorithm = cmd.getOptionValue("method");
        logger.info("**** Reading the maze from file " + filepath);
        return this.path_sequence;
    }
}
