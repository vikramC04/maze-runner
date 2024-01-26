package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MazeSolver implements Solver {
    private static final Logger logger = LogManager.getLogger();
    Tile west;
    Tile east;
    private final int[][] maze_binary;
    public MazeSolver(int[][] maze_m, Tile s, Tile e) throws FileNotFoundException {
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
            String computed_path = pathFinding(east, player);
            System.out.println("The path is: " + computed_path);
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


