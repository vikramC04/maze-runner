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
        Config configure = new Config();
        configure.configure();

        MazeSolver solver = new MazeSolver();
        solver.solve();
        logger.info("** Starting Maze Runner");
        Options options = new Options();
        options.addOption("i", true, "Path to file containing maze");
        CommandLineParser parser = new DefaultParser();
        try {
            String maze_construct = "\n";
            CommandLine cmd = parser.parse(options, args);
            String path = cmd.getOptionValue("i");
            logger.info("**** Reading the maze from file " + path);
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        maze_construct += "WALL";
                    } else if (line.charAt(idx) == ' ') {
                        maze_construct += "PASS ";
                    }
                }
                maze_construct += "\n";
            }
            logger.info(maze_construct);
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");

    }
}
