package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class Tremaux implements Solver {
    private static final Logger logger = LogManager.getLogger();
    private Tile west;
    private Tile east;
    private String computed_path;
    private final int[][] maze_binary;
    public Tremaux(int[][] maze_m, Tile s, Tile e)  {
        maze_binary = maze_m.clone();
        //logger.info(Arrays.deepToString(maze_binary));
        west = s;
        east = e;
    }

    @Override
    public void solve() {
        try {
            Processor processor = new Processor();
            logger.info("\nExecuting tremaux path finding");
            Player player = new Player(west, Direction.EAST, maze_binary, east);
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
        path = player.tremaux().replaceAll("null", "");

        return path;
    }
}
