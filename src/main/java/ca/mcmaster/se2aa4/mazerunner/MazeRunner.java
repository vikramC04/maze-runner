package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

public class MazeRunner {
    private String path_sequence;
    private static final Logger logger = LogManager.getLogger();
    private Tile west;
    private Tile east;
    private String algorithm;
    private Maze maze;
    private String mode;
    Processor processor = new Processor();
    public MazeRunner(String path_sequence, Maze maze, Tile s, Tile e, String algorithm, String mode)  {
        this.path_sequence = path_sequence;
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
            } else if(path_sequence != null) {
                verifyMode(mazeBinary);
            } else {
                solveMode(mazeBinary);
            }
        }catch(Exception e) {
            logger.error("error occured");
            logger.error(e.getMessage());
        }
    }
    private boolean verifyPath(Tile end, Player player) throws IOException {
        for(char c : path_sequence.toCharArray()) {
            if(!player.isPositionValid()) return false;
            if(c == 'F') {
                player.moveForward();
            } else if(c == 'R') {
                player.turnRight();
            } else if(c == 'L') {
                player.turnLeft();
            }
        }
        return player.isEnd(end);
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
        path_sequence = processor.processPathSequence(path_sequence);
        Player p = new Player(west, Direction.EAST, mazeBinary);
        Player p2 = new Player(east, Direction.WEST, mazeBinary);
        boolean verdict = verifyPath(east, p)
                || verifyPath(west, p2);
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
