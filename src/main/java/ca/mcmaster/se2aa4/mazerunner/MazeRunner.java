package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
