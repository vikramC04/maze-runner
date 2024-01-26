package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MazeSolver {
    private final String filepath;
    private String path_sequence;
    private static final Logger logger = LogManager.getLogger();
    Maze maze;
    private final int[][] maze_binary;
    public MazeSolver(String filepath, String path_sequence, Maze maze) throws FileNotFoundException {
        this.filepath = filepath;
        this.path_sequence = path_sequence;
        maze_binary = maze.getMaze().clone();
        logger.info("Printing from maze solver: ");
        logger.info(Arrays.deepToString(maze_binary));
        this.maze = maze;
    }
    public void solve() {
        try {
            Processor processor = new Processor();
            Tile west = maze.getStartingCoordinates();
            Tile east = maze.getEndingCoordinates();
            if(path_sequence != null) {
                path_sequence = processor.processPathSequence(path_sequence);
                Player p = new Player(west, Direction.EAST, maze_binary);
                Player p2 = new Player(east, Direction.WEST, maze_binary);
                boolean verdict = verifyPath(west,east, p)
                        || verifyPath(east,west, p2);
                if(verdict) {
                    System.out.println("correct path");
                } else {
                    System.out.println("incorrect path");
                }
            } else {
                logger.info("\nExecuting right hand path finding");
                Player player = new Player(west, Direction.EAST, maze_binary);
                String computed_path = rightHandPathFinding(east, player);
                System.out.println("The path is: " + computed_path);
                System.out.println("Factorized path is: " + processor.factorizePath(computed_path));

            }
        }catch(Exception e) {
            logger.error("error occured");
            logger.error(e.getMessage());
        }
    }
    public boolean verifyPath(Tile start, Tile end, Player player) throws IOException {
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
    public String rightHandPathFinding(Tile end, Player player) {
        String path = "";
        while(!player.isEnd(end)) {
            path += player.movePlayer();
        }

        return path;
    }
}


