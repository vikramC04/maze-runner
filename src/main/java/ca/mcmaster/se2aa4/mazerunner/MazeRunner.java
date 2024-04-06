package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.benchmarking.Baseline;
import ca.mcmaster.se2aa4.mazerunner.benchmarking.Performance;

import java.io.IOException;

public class MazeRunner {
    private String pathSequence;
    private static final Logger logger = LogManager.getLogger();
    private Tile west;
    private Tile east;
    private String algorithm;
    private Maze maze;
    private String mode;
    Processor processor = new Processor();
    public MazeRunner(String pathSequence, Maze maze, Tile s, Tile e, String algorithm, String mode)  {
        this.pathSequence = pathSequence;
        west = s;
        east = e;
        this.algorithm = algorithm;
        this.maze = maze;
        this.mode = mode;
    }

    public void play() {
        try {
            MazeChar[][] mazeBinary = maze.getMaze();
            if(mode != null) {
                baselineMode(mazeBinary);
            } else if(pathSequence != null) {
                verifyMode(mazeBinary);
            } else {
                solveMode(mazeBinary);
            }
        }catch(Exception e) {
            logger.error("error occured");
            logger.error(e.getMessage());
        }
    }

    private void baselineMode(MazeChar[][] mazeBinary)  {
        Performance baseline = new Baseline(maze, algorithm, mode, east, west);
        baseline.benchmark();
        
    }

    private void verifyMode(MazeChar[][] mazeBinary) throws IOException {
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

    private void solveMode(MazeChar[][] mazeBinary) throws IOException {
        if(algorithm == null || algorithm.equals("righthand")) {
            Solver rightHand = new RightHandSolver(mazeBinary,  west, east);
            rightHand.solve();
        } else {
            Solver bfs = new BreadthFirstSearch(mazeBinary, west, east);
            bfs.solve();
        }
    }

}
