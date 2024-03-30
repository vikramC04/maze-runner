package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class Tremaux implements Solver {
    private static final Logger logger = LogManager.getLogger();
    private Tile west;
    private Tile east;
    private String computed_path;
    private final int[][] maze_map;
    private int x;
    private int y;
    private String final_path;
    private final int maze_height;
    private final int maze_width;
    private Tile finish;
    private Direction direction = Direction.EAST;
    public Tremaux(int[][] maze_m, Tile s, Tile e)  {
        maze_map = maze_m.clone();
        west = s;
        east = e;
        int coords[] = west.findCoords().clone();
        x = coords[0];
        y = coords[1];
        maze_height = maze_m.length;
        maze_width = maze_m[0].length;
    }

    @Override
    public void solve() {
        try {
            Processor processor = new Processor();
            logger.info("Executing tremaux path finding");
            Player player = new Player(west, Direction.EAST, maze_map);
            computed_path = pathFinding(east, player);
            System.out.println(processor.factorizePath(computed_path));
        }catch(Exception e) {
            logger.error("error occured");
            logger.error(e.getMessage());
        }
    }

    @Override
    public String pathFinding(Tile end, Player player) {
        String path = "";
        path = tremaux(end).replaceAll("null", "");
        return path;
    }
    private boolean isWall(int x, int y) {
        if(maze_map[y][x] == 1) return true;
        return false;
    }
    public String tremaux(Tile end) {
        finish = end;
        playerdfs(x,y,maze_map);

        return final_path;
    }
    private boolean isMazePathValid(int x, int y) {
        if((isNotWithinBounds(x,y) || isWall(x,y))) {
            return false;
        }
        return maze_map[y][x] == 0;
    }
    private boolean isNotWithinBounds(int x, int y) {
        return (y >= maze_height || x >= maze_width || x < 0 || y < 0);
    }
    public boolean playerdfs(int x, int y, int[][] maze_map) {
        if(!isMazePathValid(x,y)) {
            return false;
        }
        maze_map[y][x] = 2;

        if(isFinished(x,y)) {
            maze_map[y][x] = 7;
            return true;
        }

        boolean result = playerdfs(x+1, y, maze_map) || playerdfs(x, y-1, maze_map)
                || playerdfs(x-1, y, maze_map) || playerdfs(x, y+1, maze_map);
        if(result) {
            maze_map[y][x] = 7;
            trackPath(x,y);
        }
        return result;
    }
    private void trackPath(int x, int y) {
        if(!isNotWithinBounds(x, y) && maze_map[y][x+1] == 7) {
            right();
        } else if(!isNotWithinBounds(x-1, y) && maze_map[y][x-1] == 7) {
            left();
        } else if(!isNotWithinBounds(x, y+1) && maze_map[y+1][x] == 7) {
            down();
        }else if(!isNotWithinBounds(x, y-1) && maze_map[y-1][x] == 7) {
            up();
        }
    }
    private void right() {
        if(direction == Direction.EAST) {
            final_path += "F";
        } else if(direction == Direction.SOUTH) {
            final_path += "LF";
        } else if(direction == Direction.NORTH) {
            final_path += "RF";
        }
        direction = Direction.EAST;
    }

    private void left() {
        if(direction == Direction.WEST) {
            final_path += "F";
        } else if(direction == Direction.NORTH) {
            final_path += "LF";
        } else if(direction == Direction.SOUTH) {
            final_path += "RF";
        }
        direction = Direction.WEST;
    }

    private void up() {
        if(direction == Direction.NORTH) {
            final_path += "F";
        } else if(direction == Direction.EAST) {
            final_path += "LF";
        } else if(direction == Direction.WEST) {
            final_path += "RF";
        }
        direction = Direction.NORTH;
    }
    private void down() {
        if(direction == Direction.SOUTH) {
            final_path += "F";
        } else if(direction == Direction.WEST) {
            final_path += "LF";
        } else if(direction == Direction.EAST) {
            final_path += "RF";
        }
        direction = Direction.SOUTH;
    }

    private boolean isFinished(int x,int y) {
        return finish.isX(x) && finish.isY(y);
    }
}
