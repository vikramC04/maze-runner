package ca.mcmaster.se2aa4.mazerunner.benchmarking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeChar;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeExtract;
import ca.mcmaster.se2aa4.mazerunner.maze.Tile;
import ca.mcmaster.se2aa4.mazerunner.algorithms.BreadthFirstSearch;
import ca.mcmaster.se2aa4.mazerunner.algorithms.RightHandSolver;
import ca.mcmaster.se2aa4.mazerunner.algorithms.Solver;
import ca.mcmaster.se2aa4.mazerunner.configurations.Algorithm;
import ca.mcmaster.se2aa4.mazerunner.configurations.Mode;

public class Baseline implements Performance {
    private MazeExtract maze;
    private Tile east;
    private Tile west;
    private Algorithm algorithm;
    private Algorithm baselineAlgorithm;
    private Mode mode;
    private static final Logger logger = LogManager.getLogger();
    
    public Baseline(MazeExtract maze, Algorithm algorithm, Mode baseline, Tile east, Tile west, Algorithm baselineAlgorithm) {
        this.maze = maze;
        this.algorithm = algorithm;
        this.mode = baseline;
        this.east = east;
        this.west = west;
        this.baselineAlgorithm = baselineAlgorithm;
    }

    @Override
    public void benchmark() {
        logger.info("Algorithm is: " + algorithm);
        logger.info("Baseline is: " + mode);
        MazeChar[][] mazeBinary = maze.getMaze();
        double mazeLoading = maze.getExecutionTime();
        Solver method;
        Solver baseline;
        if(algorithm == Algorithm.RIGHTHAND) {
            method = new RightHandSolver(mazeBinary, west, east);
        } else {
            method = new BreadthFirstSearch(mazeBinary, west, east);
        }

        if(baselineAlgorithm == Algorithm.RIGHTHAND) {
            baseline = new RightHandSolver(mazeBinary, west, east);
        } else {
            baseline = new BreadthFirstSearch(mazeBinary, west, east);
        }
        method.solve();
        baseline.solve();
        System.out.println(String.format("Maze loading time:  %1.1e", mazeLoading));
        double methodExec = method.getExecutionTime();
        System.out.println(String.format("Method Execution Time: %1.1e", methodExec));
        System.out.println(String.format("Baseline Execution Time: : %1.1e", baseline.getExecutionTime()));
        double speedup =  baseline.getPath().length()/(double)method.getPath().length();
        String output = String.format("Speedup: %1.1E", speedup);
        System.out.println(output);

    }
    
}
