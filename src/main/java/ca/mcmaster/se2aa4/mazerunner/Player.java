package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    private int x;
    private int y;
    private Direction direction;
    private final int maze_height;
    private final int maze_width;
    private static final Logger logger = LogManager.getLogger();
    int[][] maze_map;

    public Player(Tile position, Direction start_direction, int[][] maze_binary) {
        logger.info("Solving maze");
        maze_map= maze_binary.clone();
        direction = start_direction;
        int coords[] = position.findCoords().clone();
        x = coords[0];
        y = coords[1];
        maze_height = maze_map.length;
        maze_width = maze_map[0].length;
    }

    private boolean isWall(int x, int y) {
        if(maze_map[y][x] == 1) return true;
        return false;
    }
    public boolean isPositionValid() {
        if((y >= maze_height || x >= maze_width || x < 0 || y < 0 || isWall(x,y))) {
            return false;
        }
        return true;
    }
    public void moveForward() {
        if(direction == Direction.EAST) {
            x++;
        } else if(direction == Direction.SOUTH) {
            y++;
        } else if(direction == Direction.WEST) {
            x--;
        } else if(direction == Direction.NORTH) {
            y--;
        }
    }
    public void turnRight() {
        direction = direction.nextRight();
    }
    public void turnLeft() {
        direction = direction.nextLeft();
    }

    public boolean isEnd(Tile end) {
        return end.isX(x) && end.isY(y);
    }
    

}
