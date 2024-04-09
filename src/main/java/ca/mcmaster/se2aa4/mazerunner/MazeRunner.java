package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.algorithms.BreadthFirstSearch;
import ca.mcmaster.se2aa4.mazerunner.algorithms.RightHandSolver;
import ca.mcmaster.se2aa4.mazerunner.algorithms.Solver;
import ca.mcmaster.se2aa4.mazerunner.benchmarking.Baseline;
import ca.mcmaster.se2aa4.mazerunner.benchmarking.Performance;
import ca.mcmaster.se2aa4.mazerunner.configurations.Algorithm;
import ca.mcmaster.se2aa4.mazerunner.configurations.Mode;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeChar;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeExtract;
import ca.mcmaster.se2aa4.mazerunner.maze.Tile;
import ca.mcmaster.se2aa4.mazerunner.movement.Direction;

public class MazeRunner {
    private String pathSequence;
    private static final Logger logger = LogManager.getLogger();
    private Tile west;
    private Tile east;
    private Algorithm algorithm;
    private Algorithm baselineAlgorithm;
    private MazeExtract maze;
    private Mode mode;
    Processor processor = new Processor();
    public MazeRunner(String pathSequence, MazeExtract maze, Tile s, Tile e, Algorithm algorithm, Mode mode, Algorithm baselineAlgorithm)  {
        this.pathSequence = pathSequence;
        west = s;
        east = e;
        this.algorithm = algorithm;
        this.maze = maze;
        this.mode = mode;
        this.baselineAlgorithm = baselineAlgorithm;
    }

    public void play() {
        try {
            MazeChar[][] mazeBinary = maze.getMaze();
            if(mode == Mode.BASELINE) {
                baselineMode();
            } else if(mode == Mode.VERIFY) {
                verifyMode(mazeBinary);
            } else {
                solveMode(mazeBinary);
            }
        }catch(Exception e) {
            logger.error("error occured");
            logger.error(e.getMessage());
        }
    }

    private void baselineMode()  {
        Performance baseline = new Baseline(maze, algorithm, east, west, baselineAlgorithm);
        baseline.benchmark();
    }

    private void verifyMode(MazeChar[][] mazeBinary) {
        pathSequence = processor.processPathSequence(pathSequence);
        Player p = new Player(west, Direction.EAST, mazeBinary);
        Player p2 = new Player(east, Direction.WEST, mazeBinary);
        Verifier verify = new Verifier(pathSequence);
        boolean verdict = verify.verifyPath(east, p, mazeBinary)
                || verify.verifyPath(west, p2, mazeBinary);
        if(verdict) {
            System.out.println("correct path");
        } else {
            System.out.println("incorrect path");
        }
    }

    private void solveMode(MazeChar[][] mazeBinary) {
        Solver algo;
        if(algorithm == null || algorithm == Algorithm.RIGHTHAND) {
            algo = new RightHandSolver(mazeBinary,  west, east);
        } else {
            algo = new BreadthFirstSearch(mazeBinary, west, east);
        }
        algo.solve();
        String path = algo.getPath();
        System.out.println(processor.factorizePath(path));
    }

}
