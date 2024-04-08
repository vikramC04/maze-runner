package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.configurations.Algorithm;
import ca.mcmaster.se2aa4.mazerunner.configurations.Config;
import ca.mcmaster.se2aa4.mazerunner.configurations.Mode;
import ca.mcmaster.se2aa4.mazerunner.maze.*;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            Config conf = new Config(args);
            String path_sequence = conf.configure(args);
            String filepath = conf.assessValidPath();
            Algorithm algo = conf.configureAlgorithm();
            Mode baseline = conf.getBaseline();

            MazeExtract maze = new Maze(filepath);
            Tile start = maze.getStartingCoordinates();
            Tile end = maze.getEndingCoordinates();
            Algorithm baselineAlgorithm = conf.getBaselineAlgorithm();

            MazeRunner runner = new MazeRunner(path_sequence, maze,
            start, end, algo, baseline, baselineAlgorithm);
            runner.play();
        } catch(Exception e) {
            logger.error(e.getMessage());
        }
    }
}
