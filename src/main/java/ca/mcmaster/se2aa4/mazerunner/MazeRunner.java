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
            Processor processor = new Processor();
            int[][] mazeBinary = maze.getMaze();
            if(mode != null) {
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
                } else  {
                    baseline = new BreadthFirstSearch(mazeBinary, west, east);
                }
                method.solve();
                baseline.solve();
                System.out.println(String.format("Maze loading time:  %1.1e", mazeLoading));
                System.out.println("Method Path: " + method.getPath());
                double methodExec = method.getExecutionTime();
                System.out.println(String.format("Method Execution Time: %1.1e", methodExec));
                System.out.println("Baseline Path: " + baseline.getPath());
                System.out.println(String.format("Baseline Execution Time: : %1.1e", baseline.getExecutionTime()));
                double speedup =  baseline.getPath().length()/method.getPath().length();
                String output = String.format("Speedup: %1.1E", speedup);
                System.out.println(output);
            }
            if(path_sequence != null) {
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
            } else {
                if(algorithm == null || algorithm.equals("righthand")) {
                    Solver rightHand = new RightHandSolver(mazeBinary,  west, east);
                    rightHand.solve();
                } else if(algorithm.equals("tremaux")) {
                    Solver trem = new Tremaux(mazeBinary, west, east);
                    trem.solve();
                } else if(algorithm.equals("bfs")) {
                    Solver bfs = new BreadthFirstSearch(mazeBinary, west, east);
                    bfs.solve();
                }

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
}
