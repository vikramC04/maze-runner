package ca.mcmaster.se2aa4.mazerunner.configurations;

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
    private Algorithm solution;
    private Mode mode;
    private Algorithm baselineAlgorithm;

    public Config(String[] args) {

    }

    public String assessValidPath() throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        reader.close();
        return filepath;
    }

    public Algorithm configureAlgorithm() {
        return solution;
    }

    public Mode getBaseline() {
        return mode;
    }

    public Algorithm getBaselineAlgorithm() {
        return baselineAlgorithm;
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
        String baseline = cmd.getOptionValue("baseline");
        String algorithm = cmd.getOptionValue("method");

        if(baseline != null) {
            mode = Mode.BASELINE;
            if(baseline.equals("righthand")) {
                baselineAlgorithm = Algorithm.RIGHTHAND;
            } else {
                baselineAlgorithm = Algorithm.BFS;
            }
        } else if(cmd.getOptionValue("p") != null) {
            mode = Mode.VERIFY;
        } else {
            mode = Mode.TEST;
        }

        if(algorithm != null && algorithm.equals("righthand")) {
            solution = Algorithm.RIGHTHAND;
        } else {
            solution = Algorithm.BFS;
        }
        logger.info("**** Reading the maze from file " + filepath);
        return this.path_sequence;
    }
}
