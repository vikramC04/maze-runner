package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class RightHandSolver implements Solver {
    private static final Logger logger = LogManager.getLogger();
    Tile west;
    Tile east;
    private String computed_path;
    private final int[][] maze_binary;
    public RightHandSolver(int[][] maze_m, Tile s, Tile e)  {
        maze_binary = maze_m.clone();
        logger.info("Printing from maze solver: ");
        logger.info(Arrays.deepToString(maze_binary));
        west = s;
        east = e;
    }
    @Override
    public void solve() {
        try {
            Processor processor = new Processor();
            logger.info("\nExecuting right hand path finding");
            Player player = new Player(west, Direction.EAST, maze_binary);
            computed_path = pathFinding(east, player);
            System.out.println("Factorized path is: " + processor.factorizePath(computed_path));
        }catch(Exception e) {
            logger.error("error occured");
            logger.error(e.getMessage());
        }
    }

    @Override
    public String pathFinding(Tile end, Player player) {
        String path = "";
        while(!player.isEnd(end)) {
            path += player.movePlayer();
        }

        return path;
    }
}


