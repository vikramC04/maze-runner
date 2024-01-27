package ca.mcmaster.se2aa4.mazerunner;

public class Player {
    private int x;
    private int y;
    private Direction direction;

    private final int maze_height;

    private final int maze_width;

    int[][] maze_map;

    public Player(Tile position, Direction start_direction, int[][] maze_binary) {
        maze_map= maze_binary.clone();
        direction = start_direction;
        int coords[] = position.findCoords().clone();
        x = coords[0];
        y = coords[1];
        maze_height = maze_map.length;
        maze_width = maze_map[0].length;
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
}
