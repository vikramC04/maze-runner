package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class MazeRunner {
    private String path_sequence;
    private static final Logger logger = LogManager.getLogger();
    Tile west;
    Tile east;
    private final int[][] maze_binary;
    public MazeRunner(String path_sequence, int[][] maze_m, Tile s, Tile e)  {
        this.path_sequence = path_sequence;
        maze_binary = maze_m.clone();
        //logger.info("Printing from maze solver: ");
        //logger.info(Arrays.deepToString(maze_binary));
        west = s;
        east = e;
    }

    public void play() {
        try {
            Processor processor = new Processor();
            if(path_sequence != null) {
                path_sequence = processor.processPathSequence(path_sequence);
                Player p = new Player(west, Direction.EAST, maze_binary);
                Player p2 = new Player(east, Direction.WEST, maze_binary);
                boolean verdict = verifyPath(east, p)
                        || verifyPath(west, p2);

                if(verdict) {
                    System.out.println("correct path");
                } else {
                    System.out.println("incorrect path");
                }
            } else {
                Solver rightHand = new MazeSolver(maze_binary,  west, east);
                rightHand.solve();
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
