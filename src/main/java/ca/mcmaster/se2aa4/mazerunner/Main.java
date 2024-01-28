package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            Config conf = new Config(args);
            String path_sequence = conf.configure(args);
            String filepath = conf.assessValidPath();
            Maze maze = new Maze(filepath);
            MazeRunner runner = new MazeRunner(path_sequence, maze.getMaze(),
                    maze.getStartingCoordinates(), maze.getEndingCoordinates(), conf.configureAlgorithm());
            runner.play();
        } catch(Exception e) {
            logger.error(e.getMessage());
        }
    }
}
