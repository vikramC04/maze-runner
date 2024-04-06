package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Player {
    private Direction direction;
    private static final Logger logger = LogManager.getLogger();
    MazeChar[][] maze_map;
    Location location;

    public Player(Tile position, Direction start_direction, MazeChar[][] maze_binary) {
        logger.info("Solving maze");
        maze_map= maze_binary.clone();
        direction = start_direction;
        int coords[] = position.findCoords().clone();
        int x = coords[0];
        int y = coords[1];
        location = new Location(x,y);
    }

    public void moveForward() {
        location = location.move(direction);
    }
    public void turnRight() {
        direction = direction.nextRight();
    }
    public void turnLeft() {
        direction = direction.nextLeft();
    }

    public Location playerLocation() {
        return location;
    }
    

}
