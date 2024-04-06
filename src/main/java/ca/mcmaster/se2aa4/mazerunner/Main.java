package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.configurations.Config;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            Config conf = new Config(args);
            String path_sequence = conf.configure(args);
            String filepath = conf.assessValidPath();
            Maze maze = new Maze(filepath);
            MazeRunner runner = new MazeRunner(path_sequence, maze,
                    maze.getStartingCoordinates(), maze.getEndingCoordinates(), 
                    conf.configureAlgorithm(), conf.getBaseline(), conf.getBaselineAlgorithm());
            runner.play();
        } catch(Exception e) {
            logger.error(e.getMessage());
        }
    }
}
