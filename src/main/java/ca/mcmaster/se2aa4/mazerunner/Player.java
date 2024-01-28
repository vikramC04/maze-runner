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
    private Tile finish;
    int[][] maze_map;
    private String final_path;

    public Player(Tile position, Direction start_direction, int[][] maze_binary) {
        maze_map= maze_binary.clone();
        direction = start_direction;
        int coords[] = position.findCoords().clone();
        x = coords[0];
        y = coords[1];
        maze_height = maze_map.length;
        maze_width = maze_map[0].length;
    }
    public Player(Tile position, Direction start_direction, int[][] maze_binary, Tile end) {
        maze_map= maze_binary.clone();
        direction = start_direction;
        int coords[] = position.findCoords().clone();
        x = coords[0];
        y = coords[1];
        maze_height = maze_map.length;
        maze_width = maze_map[0].length;
        finish = end;
        int[] ending = end.findCoords().clone();
        System.out.println("Ending: x" + ending[0] + " Ending: y:" + ending[1]);
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
        if(direction == Direction.EAST) {
            direction = Direction.SOUTH;
        } else if(direction == Direction.SOUTH) {
            direction = Direction.WEST;
        } else if(direction == Direction.WEST) {
            direction = Direction.NORTH;
        }else if(direction == Direction.NORTH) {
            direction = Direction.EAST;
        }
    }
    public void turnLeft() {
        if(direction == Direction.EAST) {
            direction = Direction.NORTH;
        } else if(direction == Direction.NORTH) {
            direction = Direction.WEST;
        } else if(direction == Direction.WEST) {
            direction = Direction.SOUTH;
        } else if(direction == Direction.SOUTH) {
            direction = Direction.EAST;
        }
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

    /*
    Treamux Methods: All methods here are used only for the tremaux algorithm.
     */
    public String tremaux() {
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
