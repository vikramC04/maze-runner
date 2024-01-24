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
            //logger.info("\nPrint map");
            //maze.printMap();
            MazeSolver solver = new MazeSolver(filepath, path_sequence, maze);
            solver.solve();
        } catch(Exception e) {
            //logger.error("/!\\ An error has occured /!\\");
            logger.error(e.getMessage());
        }
//        logger.info("**** Computing path");
//        logger.info("PATH NOT COMPUTED");
//        logger.info("** End of MazeRunner");
    }
}
