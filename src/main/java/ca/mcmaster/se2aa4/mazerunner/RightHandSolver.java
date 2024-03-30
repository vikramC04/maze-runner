package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class RightHandSolver implements Solver {
    private static final Logger logger = LogManager.getLogger();
    private Tile west;
    private Tile east;
    private String computed_path;
    private final int[][] maze_map;
    private int x;
    private int y;
    private final int maze_height;
    private final int maze_width;
    private String final_path;
    private Direction direction = Direction.EAST;

    public RightHandSolver(int[][] maze_m, Tile s, Tile e)  {
        maze_map = maze_m.clone();
        west = s;
        east = e;
        maze_height = maze_m.length;
        maze_width = maze_m[0].length;
        int coords[] = west.findCoords().clone();
        x = coords[0];
        y = coords[1];
    }
    @Override
    public void solve() {
        try {
            Processor processor = new Processor();
            logger.info("Executing right hand path finding");
            Player player = new Player(west, Direction.EAST, maze_map);
            computed_path = pathFinding(east, player);
            System.out.println(processor.factorizePath(computed_path));
        }catch(Exception e) {
            logger.error("error occured");
            logger.error(e.getMessage());
        }
    }
    public boolean isPositionValid() {
        if((y >= maze_height || x >= maze_width || x < 0 || y < 0 || isWall(x,y))) {
            return false;
        }
        return true;
    }

    private boolean isWall(int x, int y) {
        if(maze_map[y][x] == 1) return true;
        return false;
    }
    public String movePlayer() {
        String path = "";
        if(direction == Direction.EAST) {
            path = eastAction();
        }else if(direction == Direction.SOUTH) {
            path = southAction();
        }else if(direction == Direction.WEST) {
            path = westAction();
        } else if(direction == Direction.NORTH) {
            path = northAction();
        }
        return path;
    }
    private String eastAction() {
        String path = "";
        if(maze_map[y+1][x] == 0) {
            direction = Direction.SOUTH;
            y++;
            path = "RF";
        } else if(maze_map[y][x+1] == 0) {
            x++;
            path = "F";
        } else if(maze_map[y-1][x] == 0) {
            direction = Direction.NORTH;
            y--;
            path = "LF";
        } else if(maze_map[y][x-1] == 0) {
            direction = Direction.WEST;
            x--;
            path = "RRF";
        }
        return path;
    }
    private String southAction() {
        String path = "";
        if (maze_map[y][x-1] == 0) {
            direction = Direction.WEST;
            x--;
            path = "RF";
        } else if (maze_map[y+1][x] == 0) {
            y++;
            path = "F";
        } else if (maze_map[y][x + 1] == 0) {
            direction = Direction.EAST;
            x++;
            path = "LF";
        } else if (maze_map[y-1][x] == 0) {
            direction = Direction.NORTH;
            y--;
            path = "RRF";
        }
        return path;
    }
    private String westAction() {
        String path = "";
        if(maze_map[y-1][x] == 0) {
            direction = Direction.NORTH;
            y--;
            path = "RF";
        } else if(maze_map[y][x-1] == 0) {
            x--;
            path = "F";
        } else if(maze_map[y+1][x] == 0) {
            direction = Direction.SOUTH;
            y++;
            path = "LF";
        } else if(maze_map[y][x+1] == 0) {
            direction = Direction.EAST;
            x++;
            path = "RRF";
        }
        return path;
    }
    private String northAction() {
        String path = "";
        if(maze_map[y][x+1] == 0) {
            direction = Direction.EAST;
            x++;
            path = "RF";
        } else if(maze_map[y-1][x] == 0) {
            y--;
            path = "F";
        } else if(maze_map[y][x-1] == 0) {
            direction = Direction.WEST;
            x--;
            path = "LF";
        } else if(maze_map[y+1][x] == 0) {
            direction = Direction.SOUTH;
            y++;
            path = "RRF";
        }
        return path;
    }
    public boolean isEnd(Tile end) {
        return end.isX(x) && end.isY(y);
    }

    @Override
    public String pathFinding(Tile end, Player player) {
        String path = "";
        logger.info(path);
        while(!isEnd(end)) {
            path += movePlayer();
        }

        return path;
    }
   

}


