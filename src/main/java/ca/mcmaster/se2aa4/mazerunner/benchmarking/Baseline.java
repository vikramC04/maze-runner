package ca.mcmaster.se2aa4.mazerunner.benchmarking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.BreadthFirstSearch;
import ca.mcmaster.se2aa4.mazerunner.Maze;
import ca.mcmaster.se2aa4.mazerunner.MazeChar;
import ca.mcmaster.se2aa4.mazerunner.RightHandSolver;
import ca.mcmaster.se2aa4.mazerunner.Solver;
import ca.mcmaster.se2aa4.mazerunner.Tile;

public class Baseline implements Performance {
    private Maze maze;
    private Tile east;
    private Tile west;
    private String algorithm;
    private String mode;
    private static final Logger logger = LogManager.getLogger();
    
    public Baseline(Maze maze, String algorithm, String baseline, Tile east, Tile west) {
        this.maze = maze;
        this.algorithm = algorithm;
        this.mode = baseline;
        this.east = east;
        this.west = west;
    }

    @Override
    public void benchmark() {
        logger.info("Algorithm is: " + algorithm);
        logger.info("Baseline is: " + mode);
        MazeChar[][] mazeBinary = maze.getMaze();
        double mazeLoading = maze.getExecutionTime();
        Solver method;
        Solver baseline;
        if(algorithm.equals("righthand")) {
            method = new RightHandSolver(mazeBinary, west, east);
        } else {
            method = new BreadthFirstSearch(mazeBinary, west, east);
        }

        if(mode.equals("righthand")) {
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
